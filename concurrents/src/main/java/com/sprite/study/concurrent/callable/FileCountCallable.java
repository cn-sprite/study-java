package com.sprite.study.concurrent.callable;

import com.sprite.study.util.FileHelper;

import java.io.File;
import java.util.concurrent.Callable;

/**
 * 计算文件的数量
 */
public class FileCountCallable implements Callable<Integer>{
    //根目录
    private File root;

    public Integer call() {
        long t1 = System.currentTimeMillis();
        int count = FileHelper.searchFiles(root);
        return count;
    }

    public FileCountCallable(String pathName) {
        root = new File(pathName);
    }

}
