// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.EventQueue;
import javax.swing.UnsupportedLookAndFeelException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import product.res.R;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import java.awt.Component;
import javax.swing.table.TableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JFrame;

public class translateManager extends JFrame
{
    DefaultTableModel dft;
    private JButton jButton1;
    private JButton jButton2;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTable jTable1;
    
    public translateManager() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.jTable1 = new JTable();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.setDefaultCloseOperation(3);
        this.jTable1.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null }, { null, null, null, null, null } }, new String[] { "type", "AZ", "EN", "RU", "TR" }));
        this.jTable1.getTableHeader().setReorderingAllowed(false);
        this.jScrollPane1.setViewportView(this.jTable1);
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 920, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, -1, 486, 32767));
        this.jButton1.setText("save");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                translateManager.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("load");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                translateManager.this.jButton2ActionPerformed(evt);
            }
        });
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jButton2).addGap(32, 32, 32).addComponent(this.jButton1).addGap(28, 28, 28)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -1, -1, 32767).addGap(18, 18, 18).addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton2)).addGap(10, 10, 10)));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        this.dft = (DefaultTableModel)this.jTable1.getModel();
        this.saveLanguage("AZ");
        this.saveLanguage("EN");
        this.saveLanguage("RU");
        this.saveLanguage("TR");
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        try {
            final File fXmlFile = new File("res/strings/AZ.xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            int i = 0;
            for (final R value : R.values()) {
                this.jTable1.setValueAt(value, i++, 0);
                System.out.println("case \"" + value + "\":\n                            " + value + ".value = eElement.getTextContent();\n                            break;");
            }
            for (final R value : R.values()) {
                System.out.println(value + ".setText(R." + value + ".getValue());");
            }
            final NodeList nList = doc.getElementsByTagName("string");
            for (int temp = 0; temp < nList.getLength(); ++temp) {
                final Node nNode = nList.item(temp);
                if (nNode.getNodeType() == 1) {
                    final Element eElement = (Element)nNode;
                    this.jTable1.setValueAt(eElement.getTextContent(), temp, 1);
                }
            }
        }
        catch (Exception ex) {}
        try {
            final File fXmlFile = new File("res/strings/EN.xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            final NodeList nList2 = doc.getElementsByTagName("string");
            for (int temp2 = 0; temp2 < nList2.getLength(); ++temp2) {
                final Node nNode2 = nList2.item(temp2);
                if (nNode2.getNodeType() == 1) {
                    final Element eElement2 = (Element)nNode2;
                    this.jTable1.setValueAt(eElement2.getTextContent(), temp2, 2);
                }
            }
        }
        catch (Exception ex2) {}
        try {
            final File fXmlFile = new File("res/strings/RU.xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            final NodeList nList2 = doc.getElementsByTagName("string");
            for (int temp2 = 0; temp2 < nList2.getLength(); ++temp2) {
                final Node nNode2 = nList2.item(temp2);
                if (nNode2.getNodeType() == 1) {
                    final Element eElement2 = (Element)nNode2;
                    this.jTable1.setValueAt(eElement2.getTextContent(), temp2, 3);
                }
            }
        }
        catch (Exception ex3) {}
        try {
            final File fXmlFile = new File("res/strings/TR.xml");
            final DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            final Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            final NodeList nList2 = doc.getElementsByTagName("string");
            for (int temp2 = 0; temp2 < nList2.getLength(); ++temp2) {
                final Node nNode2 = nList2.item(temp2);
                if (nNode2.getNodeType() == 1) {
                    final Element eElement2 = (Element)nNode2;
                    this.jTable1.setValueAt(eElement2.getTextContent(), temp2, 4);
                }
            }
        }
        catch (Exception ex4) {}
    }
    
    public void saveLanguage(final String lang) {
        try (final BufferedWriter bw = new BufferedWriter(new FileWriter("res/strings/" + lang + ".xml"))) {
            bw.write("<?xml version=\"1.0\"?>\n");
            bw.write("<strings>\n");
            for (int i = 0; i < this.jTable1.getRowCount() && this.jTable1.getValueAt(i, 0) != null && !this.jTable1.getValueAt(i, 0).equals(""); ++i) {
                bw.write("\t<string name=\"" + this.jTable1.getValueAt(i, 0) + "\">" + this.jTable1.getValueAt(i, this.dft.findColumn(lang)) + "</string>\n");
            }
            bw.write("</strings>");
            System.out.println("Done");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try (final BufferedWriter bw2 = new BufferedWriter(new FileWriter("res/strings/" + lang + ".properties"))) {
            bw2.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            for (int i = 0; i < this.jTable1.getRowCount() && this.jTable1.getValueAt(i, 0) != null && !this.jTable1.getValueAt(i, 0).equals(""); ++i) {
                bw2.write("" + this.jTable1.getValueAt(i, 0) + " = " + this.dft.getValueAt(i, this.dft.findColumn(lang)) + "\n");
            }
            System.out.println("Done1");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(translateManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(translateManager.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(translateManager.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(translateManager.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new translateManager().setVisible(true);
            }
        });
    }
}
