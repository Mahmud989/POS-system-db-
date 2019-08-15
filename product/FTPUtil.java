// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUtil
{
    public static void uploadDirectory(final FTPClient ftpClient, final String remoteDirPath, String localParentDir, final String remoteParentDir) throws IOException {
        System.out.println("LISTING directory: " + localParentDir);
        final File localDir = new File(localParentDir);
        final File[] subFiles = localDir.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (final File item : subFiles) {
                String remoteFilePath = remoteDirPath + "/" + remoteParentDir + "/" + item.getName();
                if (remoteParentDir.equals("")) {
                    remoteFilePath = remoteDirPath + "/" + item.getName();
                }
                if (item.isFile()) {
                    final String localFilePath = item.getAbsolutePath();
                    System.out.println("About to upload the file: " + localFilePath);
                    final boolean uploaded = uploadSingleFile(ftpClient, localFilePath, remoteFilePath);
                    if (uploaded) {
                        System.out.println("UPLOADED a file to: " + remoteFilePath);
                    }
                    else {
                        System.out.println("COULD NOT upload the file: " + localFilePath);
                    }
                }
                else {
                    final boolean created = ftpClient.makeDirectory(remoteFilePath);
                    if (created) {
                        System.out.println("CREATED the directory: " + remoteFilePath);
                    }
                    else {
                        System.out.println("COULD NOT create the directory: " + remoteFilePath);
                    }
                    String parent = remoteParentDir + "/" + item.getName();
                    if (remoteParentDir.equals("")) {
                        parent = item.getName();
                    }
                    localParentDir = item.getAbsolutePath();
                    uploadDirectory(ftpClient, remoteDirPath, localParentDir, parent);
                }
            }
        }
    }
    
    public static boolean uploadSingleFile(final FTPClient ftpClient, final String localFilePath, final String remoteFilePath) throws IOException {
        final File localFile = new File(localFilePath);
        final InputStream inputStream = new FileInputStream(localFile);
        try {
            ftpClient.setFileType(2);
            return ftpClient.storeFile(remoteFilePath, inputStream);
        }
        finally {
            inputStream.close();
        }
    }
}
