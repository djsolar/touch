package com.twinflag.touch.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;

@Component
@Data
public class Config {
    @Value("${upload-template-path}")
    private String uploadTemplatePath;

    @Value("${upload-material-path}")
    private String uploadMaterialPath;

    @PostConstruct
    public void init() {
        File templateFile = new File(uploadTemplatePath);
        if (!templateFile.exists()) {
            boolean isSuccess = templateFile.mkdirs();
            if (isSuccess) {
                System.out.println("Template folder create successfully!");
            }
        }

        File materialFile = new File(uploadMaterialPath);
        if (!materialFile.exists()) {
            boolean isSuccess = materialFile.mkdirs();
            if (isSuccess) {
                System.out.println("Material folder create successfully!");
            }
        }
    }
}
