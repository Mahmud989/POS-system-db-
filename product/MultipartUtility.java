// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.net.URLConnection;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.io.PrintWriter;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class MultipartUtility
{
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;
    
    public MultipartUtility(final String requestURL, final String charset) throws IOException {
        this.charset = charset;
        this.boundary = "===" + System.currentTimeMillis() + "===";
        final URL url = new URL(requestURL);
        (this.httpConn = (HttpURLConnection)url.openConnection()).setUseCaches(false);
        this.httpConn.setDoOutput(true);
        this.httpConn.setDoInput(true);
        this.httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.boundary);
        this.httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        this.httpConn.setRequestProperty("Test", "Bonjour");
        this.outputStream = this.httpConn.getOutputStream();
        this.writer = new PrintWriter(new OutputStreamWriter(this.outputStream, charset), true);
    }
    
    public void addFormField(final String name, final String value) {
        this.writer.append("--" + this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"" + name + "\"").append("\r\n");
        this.writer.append("Content-Type: text/plain; charset=" + this.charset).append("\r\n");
        this.writer.append("\r\n");
        this.writer.append(value).append("\r\n");
        this.writer.flush();
    }
    
    public void addFilePart(final String fieldName, final File uploadFile) throws IOException {
        final String fileName = uploadFile.getName();
        this.writer.append("--" + this.boundary).append("\r\n");
        this.writer.append("Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + fileName + "\"").append("\r\n");
        this.writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append("\r\n");
        this.writer.append("Content-Transfer-Encoding: binary").append("\r\n");
        this.writer.append("\r\n");
        this.writer.flush();
        final FileInputStream inputStream = new FileInputStream(uploadFile);
        final byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            this.outputStream.write(buffer, 0, bytesRead);
        }
        this.outputStream.flush();
        inputStream.close();
        this.writer.append("\r\n");
        this.writer.flush();
    }
    
    public void addHeaderField(final String name, final String value) {
        this.writer.append(name + ": " + value).append("\r\n");
        this.writer.flush();
    }
    
    public List<String> finish() throws IOException {
        final List<String> response = new ArrayList<String>();
        this.writer.append("\r\n").flush();
        this.writer.append("--" + this.boundary + "--").append("\r\n");
        this.writer.close();
        final int status = this.httpConn.getResponseCode();
        if (status == 200) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(this.httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            this.httpConn.disconnect();
            return response;
        }
        throw new IOException("Server returned non-OK status: " + status);
    }
}
