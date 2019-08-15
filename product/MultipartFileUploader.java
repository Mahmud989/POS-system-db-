// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.io.File;

public class MultipartFileUploader
{
    public static void main(final String[] args) {
        final String charset = "UTF-8";
        final File uploadFile = new File("/home/mahmud/index.jpeg");
        final String requestURL = "http://darkenlight.esy.es/test/upload.php";
        try {
            final MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addFilePart("fileToUpload", uploadFile);
            final List<String> response = multipart.finish();
            System.out.println("SERVER REPLIED:");
            for (final String line : response) {
                System.out.println(line);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
