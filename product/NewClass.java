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
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import product.res.R;

public class NewClass
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
            query = "SELECT *, ANY_VALUE(sale_date) , count(sale_date) as `s` FROM sale where sale_date>'01/11/2017-11:01'  group by seller_id,customer_id,sale_date;";
            final ResultSet rs = stm.executeQuery(query);
            if (rs.next()) {
                final int say = 0;
                rs.previous();
                while (rs.next()) {
                    final Statement s = (Statement)conn.createStatement();
                    final ResultSet rs2 = s.executeQuery("SELECT * FROM product_db.sale where customer_id='" + rs.getString("customer_id") + "' and seller_id='" + rs.getString("seller_id") + "' and sale_date='" + rs.getString("sale_date") + "';");
                    System.out.println("seller_name=" + rs.getString("seller_name") + "\tseller id=" + rs.getString("seller_id") + "\tcustomer id=" + rs.getString("customer_id") + "\tcustomer name=" + rs.getString("customer_name") + "\tsale count=" + rs.getString("s"));
                    while (rs2.next()) {
                        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("mehsul barcode=" + rs2.getString("product_barcode"));
                        System.out.println("mehsul name=" + rs2.getString("product_name"));
                        System.out.println("count=" + rs2.getString("count") + " " + rs2.getString("unit"));
                        System.out.println("price=" + rs2.getString("price") + " AZN");
                        System.out.println("umumi deyer=" + String.format("%.2f", rs2.getDouble("price") * rs2.getDouble("count")));
                        System.out.println("mehsul barcode=" + rs2.getString("product_barcode"));
                    }
                    s.close();
                    System.out.println("================================");
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
