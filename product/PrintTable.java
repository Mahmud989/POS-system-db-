// 
// Decompiled by Procyon v0.5.36
// 

package product;

import javax.swing.event.TableModelListener;
import javax.swing.Icon;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Container;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Font;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PrintTable extends JFrame
{
    private static final ImageIcon passedIcon;
    private static final ImageIcon failedIcon;
    private JPanel contentPane;
    private JLabel gradesLabel;
    private JTable gradesTable;
    private JScrollPane scroll;
    private JCheckBox showPrintDialogBox;
    private JCheckBox interactiveBox;
    private JCheckBox fitWidthBox;
    private JButton printButton;
    protected JCheckBox headerBox;
    protected JCheckBox footerBox;
    protected JTextField headerField;
    protected JTextField footerField;
    
    protected JTable createTable(final TableModel model) {
        return new JTable(model);
    }
    
    protected TableCellRenderer createPassedColumnRenderer() {
        return new PassedColumnRenderer();
    }
    
    public PrintTable() {
        super("Table Printing Demo 1");
        (this.gradesLabel = new JLabel("Final Grades - CSC 101")).setFont(new Font("Dialog", 1, 16));
        (this.gradesTable = this.createTable(new GradesModel())).setFillsViewportHeight(true);
        this.gradesTable.setRowHeight(24);
        this.gradesTable.getColumn("Passed").setCellRenderer(this.createPassedColumnRenderer());
        this.scroll = new JScrollPane(this.gradesTable);
        String tooltipText = "Include a page header";
        (this.headerBox = new JCheckBox("Header:", true)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                PrintTable.this.headerField.setEnabled(PrintTable.this.headerBox.isSelected());
            }
        });
        this.headerBox.setToolTipText(tooltipText);
        tooltipText = "Page Header (Use {0} to include page number)";
        (this.headerField = new JTextField("Final Grades - CSC 101")).setToolTipText(tooltipText);
        tooltipText = "Include a page footer";
        (this.footerBox = new JCheckBox("Footer:", true)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                PrintTable.this.footerField.setEnabled(PrintTable.this.footerBox.isSelected());
            }
        });
        this.footerBox.setToolTipText(tooltipText);
        tooltipText = "Page Footer (Use {0} to Include Page Number)";
        (this.footerField = new JTextField("Page {0}")).setToolTipText(tooltipText);
        tooltipText = "Show the Print Dialog Before Printing";
        (this.showPrintDialogBox = new JCheckBox("Show print dialog", true)).setToolTipText(tooltipText);
        this.showPrintDialogBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (!PrintTable.this.showPrintDialogBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "If the Print Dialog is not shown, the default printer is used.", "Printing Message", 1);
                }
            }
        });
        tooltipText = "Keep the GUI Responsive and Show a Status Dialog During Printing";
        (this.interactiveBox = new JCheckBox("Interactive (Show status dialog)", true)).setToolTipText(tooltipText);
        this.interactiveBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
                if (!PrintTable.this.interactiveBox.isSelected()) {
                    JOptionPane.showMessageDialog(null, "If non-interactive, the GUI is fully blocked during printing.", "Printing Message", 1);
                }
            }
        });
        tooltipText = "Shrink the Table to Fit the Entire Width on a Page";
        (this.fitWidthBox = new JCheckBox("Fit width to printed page", true)).setToolTipText(tooltipText);
        tooltipText = "Print the Table";
        (this.printButton = new JButton("Print")).setToolTipText(tooltipText);
        this.printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent ae) {
            }
        });
        this.setContentPane(this.contentPane = new JPanel());
        this.setDefaultCloseOperation(3);
        this.setSize(700, 600);
        this.setLocationRelativeTo(null);
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UIManager.put("swing.boldMetal", true);
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
                catch (Exception ex) {}
                new PrintTable().setVisible(true);
            }
        });
    }
    
    static {
        passedIcon = new ImageIcon(TablePrintDemo1.class.getResource("images/passed.png"));
        failedIcon = new ImageIcon(TablePrintDemo1.class.getResource("images/failed.png"));
    }
    
    protected static class PassedColumnRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            this.setText("");
            this.setHorizontalAlignment(0);
            final boolean status = (boolean)value;
            this.setIcon(status ? PrintTable.passedIcon : PrintTable.failedIcon);
            return this;
        }
    }
    
    private static class GradesModel implements TableModel
    {
        private final Object[][] GRADES;
        
        private GradesModel() {
            this.GRADES = new Object[][] { { "Agnew", "Kieran", 80, 74, 75, 93 }, { "Albertson", "Jack", 90, 65, 88, 79 }, { "Anderson", "Mischa", 34, 45, 48, 59 }, { "Andrews", "Danielle", 50, 56, 55, 44 }, { "Baja", "Ron", 32, 23, 55, 67 }, { "Barry", "Douglas", 46, 66, 77, 90 }, { "Davis", "Lacy", 99, 100, 98, 97 }, { "Egelstein", "Harold", 34, 58, 76, 89 }, { "Elens", "Xavier", 35, 66, 49, 47 }, { "Elmer", "Thomas", 50, 32, 51, 60 }, { "Farras", "Elena", 77, 51, 75, 55 }, { "Filison", "Paulo", 88, 87, 77, 52 }, { "Gabon", "Parvati", 9, 15, 35, 86 }, { "Hickey", "Shannon", 95, 89, 95, 100 }, { "Ingles", "Jaime", 75, 65, 55, 95 }, { "Instein", "Payton", 51, 56, 51, 84 }, { "Jackson", "Donald", 94, 78, 97, 13 }, { "Jefferson", "Lynn", 21, 51, 20, 74 }, { "Johnson", "Tanya", 11, 52, 80, 48 }, { "Kimble", "James", 18, 50, 90, 54 }, { "Kolson", "Laura", 98, 88, 97, 99 }, { "Leigh", "Tasha", 85, 78, 48, 100 }, { "Lombardi", "Leonard", 68, 54, 97, 24 }, { "Manning", "Havvy", 59, 54, 9, 18 }, { "McNichol", "Vivian", 88, 99, 58, 87 }, { "Michaels", "Daniel", 97, 95, 54, 99 }, { "Nicholson", "Alex", 24, 100, 55, 100 }, { "Nimble", "Tonya", 41, 33, 33, 81 }, { "Onning", "Wehhoh", 45, 65, 32, 24 }, { "Opals", "Diamond", 98, 59, 12, 11 }, { "Osser", "Michael", 55, 54, 31, 87 }, { "Paton", "Geoff", 68, 22, 31, 80 }, { "Plumber", "Ester", 58, 20, 28, 98 }, { "Robbins", "Noah", 99, 12, 87, 64 }, { "Robinson", "Jenny", 65, 10, 98, 66 }, { "Ronald", "Dmitri", 25, 9, 98, 61 }, { "Sabo", "Polly", 20, 68, 82, 50 }, { "Silverstone", "Dina", 31, 62, 54, 58 }, { "Singleton", "Alyssa", 50, 30, 98, 88 }, { "Stevens", "Cameron", 89, 8, 81, 56 }, { "Talbert", "Will", 34, 80, 81, 84 }, { "Trimble", "Vicky", 58, 65, 98, 54 }, { "Tussle", "Paulo", 55, 55, 88, 55 }, { "Umber", "Gus", 90, 87, 85, 55 }, { "Valhalla", "Yohan", 60, 77, 62, 89 }, { "Viola", "Michel", 31, 84, 62, 68 }, { "Wanderer", "Sean", 24, 51, 85, 95 }, { "West", "North", 88, 23, 81, 87 }, { "Xavier", "Kerry", 91, 99, 24, 84 }, { "Xu", "Richard", 99, 58, 20, 24 }, { "Ying", "Lina", 85, 99, 89, 90 }, { "Yee", "Tong", 95, 65, 74, 64 } };
        }
        
        @Override
        public void setValueAt(final Object aValue, final int rowIndex, final int columnIndex) {
        }
        
        @Override
        public void addTableModelListener(final TableModelListener l) {
        }
        
        @Override
        public void removeTableModelListener(final TableModelListener l) {
        }
        
        @Override
        public boolean isCellEditable(final int rowIndex, final int columnIndex) {
            return false;
        }
        
        @Override
        public Class<?> getColumnClass(final int col) {
            switch (col) {
                case 0:
                case 1: {
                    return String.class;
                }
                case 2:
                case 3:
                case 4:
                case 5:
                case 6: {
                    return Integer.class;
                }
                case 7: {
                    return Boolean.class;
                }
                default: {
                    throw new AssertionError((Object)"invalid column");
                }
            }
        }
        
        @Override
        public int getRowCount() {
            return this.GRADES.length;
        }
        
        @Override
        public int getColumnCount() {
            return 8;
        }
        
        @Override
        public String getColumnName(final int col) {
            switch (col) {
                case 0: {
                    return "Last Name";
                }
                case 1: {
                    return "First Name";
                }
                case 2: {
                    return "Assign. 1";
                }
                case 3: {
                    return "Midterm";
                }
                case 4: {
                    return "Assign. 2";
                }
                case 5: {
                    return "Exam";
                }
                case 6: {
                    return "Final";
                }
                case 7: {
                    return "Passed";
                }
                default: {
                    throw new AssertionError((Object)"invalid column");
                }
            }
        }
        
        @Override
        public Object getValueAt(final int row, final int col) {
            switch (col) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5: {
                    return this.GRADES[row][col];
                }
                case 6:
                case 7: {
                    int fin = 0;
                    fin += (int)this.GRADES[row][2];
                    fin += (int)this.GRADES[row][3];
                    fin += (int)this.GRADES[row][4];
                    fin += (int)this.GRADES[row][5];
                    fin = Math.round(fin / 4.0f);
                    if (col == 6) {
                        return fin;
                    }
                    return fin > 50;
                }
                default: {
                    throw new AssertionError((Object)"invalid column");
                }
            }
        }
    }
}
