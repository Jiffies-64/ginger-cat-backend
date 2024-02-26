package top.gingercat.media.service;

import top.gingercat.base.model.PageParams;
import top.gingercat.base.model.PageResult;
import top.gingercat.base.model.RestResponse;
import top.gingercat.media.model.dto.QueryMediaParamsDto;
import top.gingercat.media.model.dto.UploadFileParamsDto;
import top.gingercat.media.model.dto.UploadFileResultDto;
import top.gingercat.media.model.po.MediaFile;

import java.io.InputStream;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理业务类
 * @date 2022/9/10 8:55
 */
public interface MediaFileService {

    /**
     * @param pageParams          分页参数
     * @param queryMediaParamsDto 查询条件
     * @return top.gingercat.base.model.PageResult<top.gingercat.media.model.po.MediaFiles>
     * @description 媒资文件查询方法
     * @author Jiffies
     * @date 2022/9/10 8:57
     */
    PageResult<MediaFile> queryMediaFiles(Long uploaderId, PageParams pageParams, QueryMediaParamsDto queryMediaParamsDto);

    /**
     * @param uploadFileParamsDto 上传文件信息
     * @param folder              文件目录,如果不传则默认年、月、日
     * @return top.gingercat.media.model.dto.UploadFileResultDto 上传文件结果
     * @description 上传文件
     * @author Jiffies
     * @date 2022/9/12 19:31
     */
    UploadFileResultDto uploadFile(Long uploaderId, UploadFileParamsDto uploadFileParamsDto, byte[] bytes, String folder, String objectName);


    /**
     * @param fileMd5 文件的md5
     * @return top.gingercat.base.model.RestResponse<java.lang.Boolean> false不存在，true存在
     * @description 检查文件是否存在
     * @author Jiffies
     * @date 2022/9/13 15:38
     */
    public RestResponse<Boolean> checkFile(String fileMd5);

    /**
     * 将文件信息保存到数据库
     * @param uploaderId 上传者id
     * @param fileMd5 文件的md5值
     * @param uploadFileParamsDto 上传参数
     * @param bucket 桶名称
     * @param objectName 对象名称
     * @return 文件
     */
    MediaFile addMediaFilesToDb(Long uploaderId, String fileMd5, UploadFileParamsDto uploadFileParamsDto, String bucket, String objectName);

    /**
     * 从桶中下载文件
     * @param bucketVideos 桶名称
     * @param objectName 对象名称
     * @return 输入流
     */
    InputStream downloadFileByStreamFromMinIO(String bucketVideos, String objectName);

    boolean addMediaFilesToMinIO(String localFilePath, String mimeType, String bucket, String ObjectName);

    /**
     * 根据id从数据查找对应的文件信息
     * @param fileId 文件id
     * @return 文件信息
     */
    MediaFile getFileById(String fileId);
}
