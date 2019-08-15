// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.File;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUploadFileDemo
{
    public static void main(final String[] args) {
        final String server = "ftp.darkenlight.esy.es";
        final int port = 21;
        final String user = "u496012924";
        final String pass = "m54321";
        final FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(2);
            final File firstLocalFile = new File("FileWriter.txt");
            final String firstRemoteFile = "FileWriter.txt";
            InputStream inputStream = new FileInputStream(firstLocalFile);
            System.out.println("Start uploading first file");
            final boolean done = ftpClient.storeFile(firstRemoteFile, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }
            final File secondLocalFile = new File("FileWriter.sql");
            final String secondRemoteFile = "FileWriter.sql";
            inputStream = new FileInputStream(secondLocalFile);
            System.out.println("Start uploading second file");
            final OutputStream outputStream = ftpClient.storeFileStream(secondRemoteFile);
            final byte[] bytesIn = new byte[4096];
            int read = 0;
            while ((read = inputStream.read(bytesIn)) != -1) {
                outputStream.write(bytesIn, 0, read);
            }
            inputStream.close();
            outputStream.close();
            final boolean completed = ftpClient.completePendingCommand();
            if (completed) {
                System.out.println("The second file is uploaded successfully.");
            }
        }
        catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            }
            catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
    }
}
