// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.sql.ResultSet;
import java.time.temporal.TemporalAccessor;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import product.res.R;

public class tablecreatingsql
{
    public static void main(final String[] args) {
        Connection conn = null;
        Statement stm = null;
        try {
            R.setLanguage("AZ");
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/product_db", "root", "m54321");
            stm = (Statement)conn.createStatement();
            stm.executeUpdate("set sql_mode='';");
            String query = "";
            query = "SHOW TABLES;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final File file = new File("FileWriter.sql");
                FileWriter fr = null;
                try {
                    fr = new FileWriter(file);
                    final int say = 0;
                    rs.previous();
                    while (rs.next()) {
                        final Statement s = (Statement)conn.createStatement();
                        final ResultSet rs2 = s.executeQuery("SHOW CREATE TABLE  " + rs.getString(1) + ";");
                        while (rs2.next()) {
                            System.out.println(rs2.getString(2));
                            fr.write(rs2.getString(2) + "\n");
                        }
                        s.close();
                        System.out.println("================================");
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                    try {
                        fr.close();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                finally {
                    try {
                        fr.close();
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            else {
                System.out.println(R.ProductNotFound.getValue());
            }
        }
        catch (ClassNotFoundException ex3) {}
        catch (SQLException ex4) {}
        catch (NumberFormatException ex5) {}
        catch (HeadlessException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException ex2) {
                ex2.printStackTrace();
            }
            try {
                if (stm != null) {
                    stm.close();
                }
            }
            catch (SQLException ex6) {}
        }
        final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy-HH:mm:ss");
        final LocalDateTime now = LocalDateTime.now();
        System.out.println(dtf.format(now));
    }
}
