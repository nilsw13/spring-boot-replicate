package com.nilsw13.spring_replicate.unitaire;
import com.nilsw13.spring_replicate.service.FileUtilsService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@Tag("unit-test")
public class FileUtilsServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void constructorTest() {
        FileUtilsService test = new FileUtilsService();
        assertThat(test).isNotNull();
    }


    @Test
    public void testFileToDataUrl() throws IOException {
        Path textFile = tempDir.resolve("test.txt");
        Files.writeString(textFile, "hello World");

        String dataUrl = FileUtilsService.fileToDataUrl(textFile.toFile());

        assertTrue(dataUrl.startsWith("data:text/plain;base64,"));
    }

    @Test
    public void testFileDataUrlWithLargeFile() throws IOException {
        Path largeFile = tempDir.resolve("large file test");
        byte[] data = new byte[FileUtilsService.MAX_DATA_URL_SIZE + 1];
        Files.write(largeFile, data);

        IOException exception = assertThrows(IOException.class, () -> {
            FileUtilsService.fileToDataUrl(largeFile.toFile());
        });

        assertTrue(exception.getMessage().contains("File size is too big"));
    }


    @Test
    void testFileToDataUrlWithNullMimeType() throws IOException {
        Path textFile = tempDir.resolve("test.txt");
        Files.writeString(textFile, "test content");


        TestFileUtils testUtils = new TestFileUtils();

        String dataUrl = testUtils.fileToDataUrlWithNullMimeType(textFile.toFile());

        assertTrue(dataUrl.startsWith("data:application/octet-stream;base64,"));


        String expectedBase64 = Base64.getEncoder().encodeToString("test content".getBytes(StandardCharsets.UTF_8));
        assertTrue(dataUrl.endsWith(expectedBase64));
    }







    @Test
    public void testImageToDataUrl() throws IOException {
        Path pngFile = tempDir.resolve("test.png");
        BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        ImageIO.write(img, "PNG", pngFile.toFile());
        String dataUrl = FileUtilsService.imageToDataUrl(pngFile.toFile());

        assertTrue(dataUrl.startsWith("data:image/png;base64,"));
    }

    @Test
    void testImageDataWithLargeFile() throws IOException {
        Path pngFile = tempDir.resolve("test.png");


        try (RandomAccessFile file = new RandomAccessFile(pngFile.toFile(), "rw")) {
            file.setLength(FileUtilsService.MAX_DATA_URL_SIZE + 1);
        }


        long fileSize = pngFile.toFile().length();
        System.out.println("Taille du fichier : " + fileSize + " octets");
        System.out.println("MAX_DATA_URL_SIZE : " + FileUtilsService.MAX_DATA_URL_SIZE + " octets");


        assertTrue(fileSize > FileUtilsService.MAX_DATA_URL_SIZE);


        IOException exception = assertThrows(IOException.class, () -> {
            FileUtilsService.imageToDataUrl(pngFile.toFile());
        });

        assertTrue(exception.getMessage().contains("Image size is too big"));
    }

    @Test
    public void testImageToDataUrlWithUnknownFormat() throws IOException {
        Path unknownFile = tempDir.resolve("test.xyz");
        Files.writeString(unknownFile, "Not an image");

        IOException exception = assertThrows(IOException.class, () -> {
            FileUtilsService.imageToDataUrl(unknownFile.toFile());
        });

        assertTrue(exception.getMessage().contains("unknowm image type"));
    }



    class TestFileUtils {
        public String fileToDataUrlWithNullMimeType(File file) throws IOException {
            // Cette méthode reproduit fileToDataUrl mais force mimeType à null
            if (file.length() > FileUtilsService.MAX_DATA_URL_SIZE) {
                throw new IOException("File size is too big, you need to host it somewhere and pass the Url instead");
            }

            byte[] fileContent = Files.readAllBytes(file.toPath());

            String mimeType = null;

            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

            String base64Encoded = Base64.getEncoder().encodeToString(fileContent);
            return "data:" + mimeType + ";base64," + base64Encoded;
        }
    }

}
