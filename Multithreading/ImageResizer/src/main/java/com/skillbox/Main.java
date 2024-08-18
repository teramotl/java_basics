package com.skillbox;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        String srcFolder = ""; //source folder
        String dstFolder = ""; //destination folder

        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();

        if (files == null) {
            System.err.println("Source directory does not exist or is empty.");
            return;
        }

        int numCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numCores);

        // Split files array into chunks for each thread
        int chunkSize = (int) Math.ceil(files.length / (double) numCores);
        for (int i = 0; i < files.length; i += chunkSize) {
            int end = Math.min(files.length, i + chunkSize);
            File[] chunk = new File[end - i];
            System.arraycopy(files, i, chunk, 0, end - i);
            executor.execute(new ImageResizerTask(chunk, dstFolder));
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all tasks to finish
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}
