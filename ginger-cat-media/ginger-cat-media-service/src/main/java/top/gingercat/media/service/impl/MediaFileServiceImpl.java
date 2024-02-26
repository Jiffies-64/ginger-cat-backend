package top.gingercat.media.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.j256.simplemagic.ContentInfo;
import com.j256.simplemagic.ContentInfoUtil;
import top.gingercat.base.exception.GingerCatException;
import top.gingercat.base.model.PageParams;
import top.gingercat.base.model.PageResult;
import top.gingercat.base.model.RestResponse;
import top.gingercat.base.utils.StringUtil;
import top.gingercat.media.mapper.MediaFileMapper;
import top.gingercat.media.model.dto.QueryMediaParamsDto;
import top.gingercat.media.model.dto.UploadFileParamsDto;
import top.gingercat.media.model.dto.UploadFileResultDto;
import top.gingercat.media.model.po.MediaFile;
import top.gingercat.media.service.MediaFileService;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Mr.M
 * @version 1.0
 * @description TODO
 * @date 2022/9/10 8:58
 */
@Slf4j
@Service
@RefreshScope
public class MediaFileServiceImpl implements MediaFileService {

    @Autowired
    MediaFileMapper mediaFileMapper;

    @Autowired
    MinioClient minioClient;

    @Autowired
    @Lazy
    MediaFileService mediaFileService;

    // 存储图片的桶
    @Value("${minio.bucket.images}")
    private String bucketImages;

    // 存储静态页面的桶
    @Value("${minio.bucket.static}")
    private String bucketStatic;

    // 存储其他文件的桶
    @Value("${minio.bucket.others}")
    private String bucketOthers;

    @Override
    public PageResult<MediaFile> queryMediaFiles(Long uploaderId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto) {
        // 构建查询条件对象
        LambdaQueryWrapper<MediaFile> queryWrapper = new LambdaQueryWrapper<>();
        // 分页对象
        Page<MediaFile> page = new Page<>(pageParams.getPageNo(), pageParams.getPageSize());
        // 查询数据内容获得结果
        Page<MediaFile> pageResult = mediaFileMapper.selectPage(page, queryWrapper);
        // 获取数据列表
        List<MediaFile> list = pageResult.getRecords();
        // 获取数据总数
        long total = pageResult.getTotal();
        // 构建结果集
        return new PageResult<>(list, total, pageParams.getPageNo(), pageParams.getPageSize());
    }

    @Transactional
    @Override
    public UploadFileResultDto uploadFile(Long uploaderId, UploadFileParamsDto uploadFileParamsDto, byte[] bytes, String folder, String objectName) {
        // 生成文件id，文件的md5值
        String fileId = DigestUtils.md5Hex(bytes);
        // 文件名称
        String filename = uploadFileParamsDto.getFilename();
        // 构造objectName
        if (StringUtils.isEmpty(objectName)) {
            objectName = fileId + filename.substring(filename.lastIndexOf("."));
        }
        // 构造文件夹
        if (StringUtils.isEmpty(folder)) {
            //通过日期构造文件存储路径
            folder = getFileFolder(new Date(), true, true, true);
        } else if (!folder.contains("/")) {
            folder = folder + "/";
        }
        // 获取MimeType
        String extension = filename.substring(filename.lastIndexOf("."));
        String mimeType = getMimeType(extension);
        // 对象名称
        objectName = folder + objectName;
        // 获取待上传的桶名称
        String targetBucket = getTargetBucket(uploadFileParamsDto.getFileType());
        // 转为流
        boolean b = addMediaFilesToMinIO(bytes, mimeType, targetBucket, objectName);
        MediaFile mediaFiles = mediaFileService.addMediaFilesToDb(uploaderId, fileId, uploadFileParamsDto, targetBucket, objectName);
        UploadFileResultDto uploadFileResultDto = new UploadFileResultDto();
        BeanUtils.copyProperties(mediaFiles, uploadFileResultDto);
        return uploadFileResultDto;
    }

    /**
     * 存储文件信息到数据库
     *
     * @param uploaderId          上传者id
     * @param fileMd5             文件的md5值
     * @param uploadFileParamsDto 上传参数
     * @param bucket              桶名称
     * @param objectName          对象名称
     * @return 文件
     */
    @Transactional
    public MediaFile addMediaFilesToDb(Long uploaderId, String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName) {
        //从数据库查询文件
        MediaFile mediaFile = mediaFileMapper.selectById(fileMd5);
        if (mediaFile == null) {
            mediaFile = new MediaFile();
            //拷贝基本信息
            BeanUtils.copyProperties(uploadFileParamsDto, mediaFile);
            mediaFile.setId(fileMd5);
            mediaFile.setFileId(fileMd5);
            mediaFile.setUploaderId(uploaderId);
            mediaFile.setFilePath(objectName);
            mediaFile.setUrl("/" + bucket + "/" + objectName);
            mediaFile.setBucket(bucket);
            mediaFile.setCreateDate(LocalDateTime.now());
            mediaFile.setAuditStatus("002003");
            mediaFile.setStatus("1");
            //保存文件信息到文件表
            int insert = mediaFileMapper.insert(mediaFile);
            if (insert < 0) {
                GingerCatException.cast("保存文件信息失败");
            }
        }
        // 记录待处理（转码）任务
        addWaitingTask(mediaFile);
        return mediaFile;
    }

    private void addWaitingTask(MediaFile mediaFiles) {

    }

    @Override
    public RestResponse<Boolean> checkFile(String fileMd5) {
        MediaFile mediaFiles = mediaFileMapper.selectById(fileMd5);
        if (mediaFiles != null) {
            // 如果数据库存在，再查询Minio
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(mediaFiles.getBucket())
                    .object(mediaFiles.getFilePath())
                    .build();
            try {
                InputStream fileInputStream = minioClient.getObject(args);
                if (fileInputStream != null) {
                    return RestResponse.success(true);
                }
            } catch (Exception e) {
                GingerCatException.cast("查找文件时出错");
            }
        }
        return RestResponse.success(false);
    }

    @Override
    public InputStream downloadFileByStreamFromMinIO(String bucketVideos, String objectName) {
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketVideos).object(objectName).build());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean addMediaFilesToMinIO(String localFilePath, String mimeType, String bucket, String objectName) {
        try {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .contentType(mimeType)
                    .filename(localFilePath)
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
            return true;
        } catch (Exception e) {
            GingerCatException.cast("上传过程中出错");
        }
        return false;
    }

    /**
     * 根据id从数据查找对应的文件信息
     * @param fileId 文件id
     * @return 文件信息
     */
    @Override
    public MediaFile getFileById(String fileId) {
        return mediaFileMapper.selectById(fileId);
    }

    /**
     * 私有方法，把文件上传到MinIO
     *
     * @param bytes      文件内容字节数组
     * @param mimeType   mimeType
     * @param bucket     桶
     * @param objectName 文件名
     * @return 是否上传成功
     */
    private boolean addMediaFilesToMinIO(byte[] bytes, String mimeType, String bucket, String objectName) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(objectName)
                    .contentType(mimeType)
                    // -1表示文件分片按5M(不小于5M,不大于5T),分片数量最大10000，
                    .stream(byteArrayInputStream, byteArrayInputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            return true;
        } catch (Exception e) {
            GingerCatException.cast("上传过程中出错");
        }
        return false;
    }

    /**
     * 根据文件类型决定待上传的桶名称
     * @param fileType 文件类型（详见字典数据库）
     * @return 待上传的桶名称
     */
    private String getTargetBucket(String fileType) {
        String targetBucket = bucketOthers;
        if (StringUtil.isBlank(fileType)) {
            return targetBucket;
        }
        if (fileType.equals("001001")) {
            targetBucket = bucketImages;
        } else if (fileType.equals("001002")) {
            targetBucket = bucketStatic;
        }
        return targetBucket;
    }

    /**
     * 工具类：获取mimeType
     *
     * @param extension 扩展名
     * @return mimeType
     */
    private String getMimeType(String extension) {
        if (extension == null) {
            extension = "";
        }
        ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extension);
        String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;  // 通用MimeType，字节流
        if (extensionMatch != null) {
            mimeType = extensionMatch.getMimeType();
        }
        return mimeType;
    }

    /**
     * 工具类，根据日期拼接目录
     *
     * @param date  日期
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 拼接好的路径
     */
    private String getFileFolder(Date date, boolean year, boolean month, boolean day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期字符串
        String dateString = sdf.format(new Date());
        //取出年、月、日
        String[] dateStringArray = dateString.split("-");
        StringBuffer folderString = new StringBuffer();
        if (year) {
            folderString.append(dateStringArray[0]);
            folderString.append("/");
        }
        if (month) {
            folderString.append(dateStringArray[1]);
            folderString.append("/");
        }
        if (day) {
            folderString.append(dateStringArray[2]);
            folderString.append("/");
        }
        return folderString.toString();
    }
}
