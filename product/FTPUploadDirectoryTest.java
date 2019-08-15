// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;

public class FTPUploadDirectoryTest
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
            System.out.println("Connected");
            final String remoteDirPath = "/public_html/";
            final String localDirPath = "C:\\Users\\bodur\\OneDrive\\Belgeler\\netb";
            FTPUtil.uploadDirectory(ftpClient, remoteDirPath, localDirPath, "");
            ftpClient.logout();
            ftpClient.disconnect();
            System.out.println("Disconnected");
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
