package com.code.service;


import io.minio.*;
import io.minio.errors.MinioException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinIOService {

    final static String endPoint = "https://play.min.io:9000";
    final static String accessKey = "BrEc1JU4Qr3xwJI5";
    final static String secretKey = "t8592qlgrEhXIDKxbkh7ylC3r6rX26Wg";
    final static String bucketName = "documente-admitere";

    final static String localFileFolderBuletin = "D:\\Fac\\IP\\platforma-de-admitere\\src\\main\\resources\\pdfs\\buletine\\";
    final static String localFileFolderDiploma = "D:\\Fac\\IP\\platforma-de-admitere\\src\\main\\resources\\pdfs\\diplome\\";
    final static String localFileFolderCertificat = "D:\\Fac\\IP\\platforma-de-admitere\\src\\main\\resources\\pdfs\\certificate\\";

    //Aici trebuie modificat cand stabilim un path final pt fisiere.
    public void WriteToMinIO(String fileName,String tip)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();

            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            String fileToUpload = switch (tip) {
                case "buletin" -> localFileFolderBuletin + fileName;
                case "diploma" -> localFileFolderDiploma + fileName;
                case "certificat" -> localFileFolderCertificat + fileName;
                default -> "no name given";
            };

            UploadObjectArgs args = UploadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(fileToUpload).build();
            minioClient.uploadObject(args);

            System.out.println(fileToUpload + "  uploaded to:");
            System.out.println("   container: " + bucketName);
            System.out.println("   blob: " + fileName);
            System.out.println();
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
        }
    }

    public String ReadFromMinIO(String fileName,String tip)
            throws InvalidKeyException, IllegalArgumentException, NoSuchAlgorithmException, IOException {
        try {
            MinioClient minioClient = MinioClient.builder().endpoint(endPoint)
                    .credentials(accessKey, secretKey).build();
            String downloadedFile = switch (tip) {
                case "buletin" -> localFileFolderBuletin + fileName;
                case "diploma" -> localFileFolderDiploma + fileName;
                case "certificat" -> localFileFolderCertificat + fileName;
                default -> "no name given";
            };

            File f = new File(downloadedFile);
            if(f.exists() && !f.isDirectory()) {
                return downloadedFile;
            }

            DownloadObjectArgs args = DownloadObjectArgs.builder().bucket(bucketName).object(fileName)
                    .filename(downloadedFile).build();
            minioClient.downloadObject(args);

            System.out.println("Downloaded file to ");
            System.out.println(" " + downloadedFile);
            System.out.println();

            return downloadedFile;
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            return null;
        }
    }
}
