package com.code.service;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;


public class FileCleanupService {

    @Scheduled(fixedRate = 1000, initialDelay = 1000)
    public void recursiveDeleteFile(){
        scheduledDetele(new File("..\\..\\..\\..\\main\\resources\\pdfs"));
    }

    public void scheduledDetele(File file){
        if (file != null && file.exists()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File fyle : files) {
                    if (fyle.isDirectory()) {
                        scheduledDetele(fyle);
                    }
                    else {
                        if (fyle.lastModified() > 10 * 24 * 60 * 60 * 1000) {
                            fyle.delete();
                        }
                    }
                }
            }
        }
    }
}
