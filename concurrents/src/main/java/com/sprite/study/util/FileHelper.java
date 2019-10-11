package com.sprite.study.util;

import java.io.File;

public class FileHelper {

    /**
     * 递归统计目录下的文件总数量
     * @param searchFile
     * @return
     */
    public static int searchFiles(File searchFile) {
        int count = 0;
        File[] files = null;
        if(searchFile.isDirectory()){
            files = searchFile.listFiles();
        }else{
            count=1;
        }
        if(files != null){
            int length = files.length;
            for (int i = 0; i < length; i++) {
                if (files[i].isDirectory()) {
                    File file = files[i];
                    count += searchFiles(file);
                } else {
                    count++;
                }
            }
        }
        return count;
    }
}
