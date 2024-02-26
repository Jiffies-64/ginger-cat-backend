package top.gingercat.media;


import io.minio.*;
import io.minio.errors.*;
import org.apache.commons.compress.utils.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class MinioTest {

    static MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://127.0.0.1:9000")
                    .credentials("jiffies", "trymybest1")
                    .build();


    @Test
    //上传文件
    public void uploadTest() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("test-bucket").build());
            //检查test-bucket桶是否创建，没有创建自动创建
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("test-bucket").build());
            } else {
                System.out.println("Bucket 'test-bucket' already exists.");
            }
            //上传1.mp4
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("test-bucket")
                            .object("1.mp4")
                            .filename("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\1.mp4")
                            .build());
            //上传1.avi,上传到avi子目录
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("test-bucket")
                            .object("avi/1.avi")
                            .filename("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\1.mp4")
                            .build());
            System.out.println("上传成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }


    //删除文件
    public void delete(String bucket, String filepath) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucket).object(filepath).build());
            System.out.println("删除成功");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }

    @Test
    public void deleteTest() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        //  upload();
        delete("test-bucket", "1.mp4");
        delete("test-bucket", "avi/1.avi");
    }


    //下载文件outFile就是下载到本地的路径
    public void getFile(String bucket, String filepath, String outFile) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            try (InputStream stream = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(filepath)
                            .build());
                 FileOutputStream fileOutputStream = new FileOutputStream(new File(outFile));
            ) {

                // Read data from stream
                IOUtils.copy(stream, fileOutputStream);
                System.out.println("下载成功");
            }

        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }

    }


    @Test
    public void downloadTest() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        uploadTest();
        // delete("test-bucket","1.mp4");
        // delete("test-bucket","avi/1.avi");
        getFile("test-bucket", "avi/1.avi", "D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\2.mp4");
    }


    @Test
    public void uploadChunk() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        for (int i = 0; i < 24; i++) {
            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                    .bucket("test-bucket")
                    .object("chunk/" + i)
                    .filename("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\chunk\\" + i)
                    .build();
            minioClient.uploadObject(uploadObjectArgs);
        }
    }

    @Test
    public void testMerge() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        //        List<ComposeSource> sources = new ArrayList<>();
        //        for (int i = 0; i < 24; i++) {
        //            sources.add(ComposeSource.builder()
        //                    .bucket("test-bucket")
        //                    .object("chunk/" + i)
        //                    .build());
        //        }
        List<ComposeSource> sources = Stream.iterate(0, i -> ++i).limit(24)
                .map(i -> ComposeSource.builder().bucket("test-bucket")
                        .object("chunk/" + i)
                        .build()).collect(Collectors.toList());
        ComposeObjectArgs args = ComposeObjectArgs.builder()
                .bucket("test-bucket")
                .object("3.mp4")
                .sources(sources)
                .build();
        minioClient.composeObject(args);
    }


}
