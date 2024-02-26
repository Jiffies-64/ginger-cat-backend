package top.gingercat.media.api;

import top.gingercat.base.model.PageParams;
import top.gingercat.base.model.PageResult;
import top.gingercat.media.model.dto.QueryMediaParamsDto;
import top.gingercat.media.model.dto.UploadFileParamsDto;
import top.gingercat.media.model.dto.UploadFileResultDto;
import top.gingercat.media.model.po.MediaFile;
import top.gingercat.media.service.MediaFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Mr.M
 * @version 1.0
 * @description 媒资文件管理接口
 * @date 2022/9/6 11:29
 */
@Api(value = "媒资文件管理接口", tags = "媒资文件管理接口")
@RestController
public class MediaFilesController {

    @Autowired
    MediaFileService mediaFileService;

    @ApiOperation("媒资列表查询接口")
    @PostMapping("/files")
    public PageResult<MediaFile> list(PageParams pageParams, @RequestBody QueryMediaParamsDto queryMediaParamsDto) {
        Long uploaderId = 1232141425L;
        return mediaFileService.queryMediaFiles(uploaderId, pageParams, queryMediaParamsDto);
    }


    @ApiOperation("上传文件")
    @RequestMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public UploadFileResultDto upload(@RequestPart("filedata") MultipartFile upload,
                                      @RequestParam(value = "folder", required = false) String folder,
                                      @RequestParam(value = "objectName", required = false) String objectName)
            throws IOException {
        Long uploaderId = 1232141425L;
        String contentType = upload.getContentType();
        UploadFileParamsDto uploadFileParamsDto = new UploadFileParamsDto();
        uploadFileParamsDto.setFileSize(upload.getSize());
        if (contentType != null) {
            if (contentType.contains("image")) {  // 图片
                uploadFileParamsDto.setFileType("001001");
            } else if (contentType.contains("text/html")) {  // 静态页面
                uploadFileParamsDto.setFileType("001002");
            } else {  // 其它
                uploadFileParamsDto.setFileType("001003");
            }
        } else {
            // 处理未知类型的逻辑，这里暂时设置为其它
            uploadFileParamsDto.setFileType("001003");
        }
        uploadFileParamsDto.setRemark("");
        uploadFileParamsDto.setFilename(upload.getOriginalFilename());
        uploadFileParamsDto.setContentType(contentType);
        return mediaFileService.uploadFile(uploaderId, uploadFileParamsDto, upload.getBytes(), folder, objectName);
    }

}
