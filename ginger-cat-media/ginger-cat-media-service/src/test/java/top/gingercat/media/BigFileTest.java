package top.gingercat.media;

import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试大文件分块
 */
@SpringBootTest
public class BigFileTest {

    /**
     * 分块测试
     */
    @Test
    public void testTrunk() throws IOException {
        File sourceFile = new File("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\3.mp4");
        String chunkFilePath = "D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\chunk\\";
        int chunkSize = 1024 * 1024 * 5;
        int chunkNum = (int) Math.ceil(sourceFile.length() * 1.0 / chunkSize);  // 计算得到需要分多少个块
        try (RandomAccessFile raf_r = new RandomAccessFile(sourceFile, "r")) {
            byte[] bytes = new byte[1024];
            for (int i = 0; i < chunkNum; i++) {
                File chunkFile = new File(chunkFilePath + i);
                try (RandomAccessFile raf_rw = new RandomAccessFile(chunkFile, "rw")) {
                    int len = -1;
                    while ((len = raf_r.read(bytes)) != -1) {
                        raf_rw.write(bytes, 0, len);
                        if (chunkFile.length() >= chunkSize) {
                            break;
                        }
                    }
                }
            }
        }
    }

    /**
     * 合并测试
     */
    @Test
    public void testMerge() throws IOException {
        File chunkFolder = new File("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\chunk");
        File source = new File("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\3.mp4");
        File merge = new File("D:\\Code\\course\\学成在线项目-资料\\day04 媒资管理 Nacos Gateway MinIO\\资料\\videos\\3_2.mp4");
        File[] files = chunkFolder.listFiles();
        assert files != null;
        Arrays.sort(files, Comparator.comparingInt(o -> Integer.parseInt(o.getName())));
        byte[] bytes = new byte[1024];
        try (RandomAccessFile raf_rw = new RandomAccessFile(merge, "rw")) {
            for (File file : files) {
                try (RandomAccessFile raf_r = new RandomAccessFile(file, "r")) {
                    int len = -1;
                    while ((len = raf_r.read(bytes)) != -1) {
                        raf_rw.write(bytes, 0, len);
                    }
                }
            }
        }
        String sourceMD5 = DigestUtils.md5DigestAsHex(new BufferedInputStream(Files.newInputStream(source.toPath())));
        String mergeMD5 = DigestUtils.md5DigestAsHex(new BufferedInputStream(Files.newInputStream(merge.toPath())));
        assertEquals(sourceMD5, mergeMD5);
    }

}
