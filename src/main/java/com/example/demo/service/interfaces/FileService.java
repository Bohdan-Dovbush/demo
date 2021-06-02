package com.example.demo.service.interfaces;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {

    public static final String IMAGE =
            System.getProperty("user.home") + File.separator +
                    "dealer-images" + File.separator;//

    public String saveFile(String img, String name) throws IOException {
        createDir();//create folder if not exists

        String[] data = img.split(",");
        String metaInfo = data[0];
        String base64File = data[1];

        String fileName = createFileName(name,
                getFileExtensionFromMetaInfo(metaInfo));

        Files.write(
                Paths.get(IMAGE, fileName),
                Base64.getDecoder().decode(base64File.getBytes())
        );
        return fileName;
    }

    private String createFileName(String fileName, String fileExtension) {
        if (fileName == null) {
            fileName = UUID.randomUUID().toString();
        }
        return String.format("%s.%s", fileName, fileExtension);
    }
    //data:image/jpeg;base64
    private String getFileExtensionFromMetaInfo(String metaInfo) {
        return metaInfo.split("/")[1].split(";")[0];
    }

    private void createDir() {
        File file = new File(FileService.IMAGE);
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
