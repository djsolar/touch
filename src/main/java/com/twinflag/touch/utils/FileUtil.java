package com.twinflag.touch.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            boolean makeDir = targetFile.mkdirs();
            System.out.println("makeDir = " + makeDir);
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /*public static void main(String[] args) {
        File file = new File("/Users/zhouyiran")
    }*/

    public static int getType(String fileName) {
        String lowerName = fileName.toLowerCase();
        if (lowerName.toLowerCase().endsWith(".png") || lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")) {
            return SourceType.IMAGE.getType();
        } else if (lowerName.endsWith(".txt")) {
            return SourceType.TEXT.getType();
        } else {
            return SourceType.NONE.getType();
        }
    }

    public static String getMd5Path(String filePath, String md5) {
        File file = new File(filePath);
        String dirPath = file.getParentFile().getAbsolutePath();
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        return dirPath + File.separator + md5 + suffix;
    }

    public static void main(String[] args) {
        System.out.println(getMd5Path("/Users/zhouyiran/Desktop/aa.txt", "defghijklmn"));
    }
}
