package com.twinflag.touch.utils;

import com.twinflag.touch.config.Config;
import com.twinflag.touch.model.LevelOne;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProgramZipUtils {

    private Config config;

    private List<LevelOne> levelOnes;

    private String programName;

    public ProgramZipUtils(List<LevelOne> levelOnes, String programName, Config config) {
        this.levelOnes = levelOnes;
        this.programName = programName;
        this.config = config;
    }

    public String zipProgram() throws IOException {
        long nanoTime = System.nanoTime();
        String menuPath = config.getUploadTemplatePath() + nanoTime + ".xml";
        XmlGenerator xmlGenerator = new XmlGenerator();
        xmlGenerator.levelOneElements(this.levelOnes, menuPath);
        List<FileZipDir> fileZipDirs = new ArrayList<>();
        FileZipDir menuZipDir = new FileZipDir(config.getParentAssets() + "\\" + "menu.xml", new File(menuPath));
        fileZipDirs.add(menuZipDir);
        Set<String> md5Names = xmlGenerator.getMaterialUrls();
        for(String md5Name: md5Names) {
            File tempFile = new File(config.getUploadMaterialPath() + "/" + md5Name);
            FileZipDir fzd = new FileZipDir(config.getParentAssets() + "/Res/" + tempFile.getName(), tempFile);
            fileZipDirs.add(fzd);
        }
        File zipFile = new File(config.getProgramZipPath() + programName + ".zip");
        ZipUtils.zipFileZipDirs(fileZipDirs, zipFile, true);
        return zipFile.getAbsolutePath();
    }
}
