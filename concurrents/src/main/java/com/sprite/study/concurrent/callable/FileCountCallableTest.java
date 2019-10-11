package com.sprite.study.concurrent.callable;

import com.sprite.study.util.FileHelper;
import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FileCountCallableTest {

    public static void main(String[] args){
        //填写你想统计的文件夹目录
        File root = new File("D:\\");
        countByConcurrent(root);
        countBySingle(root);
    }

    /**
     * 多线程计算
     * @param root
     */
    static void countByConcurrent(File root){
        try {
            long t1 = System.currentTimeMillis();
            File[] files = root.listFiles();
            ExecutorService es = Executors.newFixedThreadPool(4);
            List<FutureTask> futureTaskList = new ArrayList<FutureTask>();
            int sumCount = 0;
            for(File file:files){
                if(file.isDirectory()){
                    FileCountCallable fileCountCallable = new FileCountCallable(file.getPath());
                    FutureTask<Integer> futureTask = new FutureTask(fileCountCallable);
                    es.submit(futureTask);
                    futureTaskList.add(futureTask);
                }else{
                    sumCount++;
                }
            }
            for(FutureTask<Integer> futureTask:futureTaskList){
                sumCount += futureTask.get();
            }
            es.shutdown();
            System.out.println("sumCount:"+sumCount);
            System.out.println("countByConcurrent finish takes:"+(System.currentTimeMillis() - t1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 单线程计算
     * @param root
     */
    static void countBySingle(File root){
        try {
            long t1 = System.currentTimeMillis();
            int sumCount = FileHelper.searchFiles(root);
            System.out.println("sumCount:"+sumCount);
            System.out.println("countBySingle finish takes:"+(System.currentTimeMillis() - t1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
