// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.util.GregorianCalendar;
import java.awt.event.ActionListener;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Component;
import javax.swing.table.TableModel;
import java.awt.LayoutManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CalendarProgram
{
    static JLabel lblMonth;
    static JLabel lblYear;
    static JButton btnPrev;
    static JButton btnNext;
    static JTable tblCalendar;
    static JComboBox cmbYear;
    static JFrame frmMain;
    static Container pane;
    static DefaultTableModel mtblCalendar;
    static JScrollPane stblCalendar;
    static JPanel pnlCalendar;
    static int realYear;
    static int realMonth;
    static int realDay;
    static int currentYear;
    static int currentMonth;
    
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException ex) {}
        catch (InstantiationException ex2) {}
        catch (IllegalAccessException ex3) {}
        catch (UnsupportedLookAndFeelException ex4) {}
        (CalendarProgram.frmMain = new JFrame("Gestionnaire de clients")).setSize(330, 375);
        (CalendarProgram.pane = CalendarProgram.frmMain.getContentPane()).setLayout(null);
        CalendarProgram.frmMain.setDefaultCloseOperation(3);
        CalendarProgram.lblMonth = new JLabel("January");
        CalendarProgram.lblYear = new JLabel("Change year:");
        CalendarProgram.cmbYear = new JComboBox();
        CalendarProgram.btnPrev = new JButton("&lt;&lt;");
        CalendarProgram.btnNext = new JButton("&gt;&gt;");
        CalendarProgram.mtblCalendar = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(final int rowIndex, final int mColIndex) {
                return false;
            }
        };
        CalendarProgram.tblCalendar = new JTable(CalendarProgram.mtblCalendar);
        CalendarProgram.stblCalendar = new JScrollPane(CalendarProgram.tblCalendar);
        (CalendarProgram.pnlCalendar = new JPanel(null)).setBorder(BorderFactory.createTitledBorder("Calendar"));
        CalendarProgram.btnPrev.addActionListener(new btnPrev_Action());
        CalendarProgram.btnNext.addActionListener(new btnNext_Action());
        CalendarProgram.cmbYear.addActionListener(new cmbYear_Action());
        CalendarProgram.pane.add(CalendarProgram.pnlCalendar);
        CalendarProgram.pnlCalendar.add(CalendarProgram.lblMonth);
        CalendarProgram.pnlCalendar.add(CalendarProgram.lblYear);
        CalendarProgram.pnlCalendar.add(CalendarProgram.cmbYear);
        CalendarProgram.pnlCalendar.add(CalendarProgram.btnPrev);
        CalendarProgram.pnlCalendar.add(CalendarProgram.btnNext);
        CalendarProgram.pnlCalendar.add(CalendarProgram.stblCalendar);
        CalendarProgram.pnlCalendar.setBounds(0, 0, 320, 335);
        CalendarProgram.lblMonth.setBounds(160 - CalendarProgram.lblMonth.getPreferredSize().width / 2, 25, 100, 25);
        CalendarProgram.lblYear.setBounds(10, 305, 80, 20);
        CalendarProgram.cmbYear.setBounds(230, 305, 80, 20);
        CalendarProgram.btnPrev.setBounds(10, 25, 50, 25);
        CalendarProgram.btnNext.setBounds(260, 25, 50, 25);
        CalendarProgram.stblCalendar.setBounds(10, 50, 300, 250);
        CalendarProgram.frmMain.setResizable(false);
        CalendarProgram.frmMain.setVisible(true);
        final GregorianCalendar cal = new GregorianCalendar();
        CalendarProgram.realDay = cal.get(5);
        CalendarProgram.realMonth = cal.get(2);
        CalendarProgram.realYear = cal.get(1);
        CalendarProgram.currentMonth = CalendarProgram.realMonth;
        CalendarProgram.currentYear = CalendarProgram.realYear;
        final String[] headers = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (int i = 0; i < 7; ++i) {
            CalendarProgram.mtblCalendar.addColumn(headers[i]);
        }
        CalendarProgram.tblCalendar.getParent().setBackground(CalendarProgram.tblCalendar.getBackground());
        CalendarProgram.tblCalendar.getTableHeader().setResizingAllowed(false);
        CalendarProgram.tblCalendar.getTableHeader().setReorderingAllowed(false);
        CalendarProgram.tblCalendar.setColumnSelectionAllowed(true);
        CalendarProgram.tblCalendar.setRowSelectionAllowed(true);
        CalendarProgram.tblCalendar.setSelectionMode(0);
        CalendarProgram.tblCalendar.setRowHeight(38);
        CalendarProgram.mtblCalendar.setColumnCount(7);
        CalendarProgram.mtblCalendar.setRowCount(6);
        for (int i = CalendarProgram.realYear - 100; i <= CalendarProgram.realYear + 100; ++i) {
            CalendarProgram.cmbYear.addItem(String.valueOf(i));
        }
        refreshCalendar(CalendarProgram.realMonth, CalendarProgram.realYear);
    }
    
    public static void refreshCalendar(final int month, final int year) {
        final String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
        CalendarProgram.btnPrev.setEnabled(true);
        CalendarProgram.btnNext.setEnabled(true);
        if (month == 0 && year <= CalendarProgram.realYear - 10) {
            CalendarProgram.btnPrev.setEnabled(false);
        }
        if (month == 11 && year >= CalendarProgram.realYear + 100) {
            CalendarProgram.btnNext.setEnabled(false);
        }
        CalendarProgram.lblMonth.setText(months[month]);
        CalendarProgram.lblMonth.setBounds(160 - CalendarProgram.lblMonth.getPreferredSize().width / 2, 25, 180, 25);
        CalendarProgram.cmbYear.setSelectedItem(String.valueOf(year));
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 7; ++j) {
                CalendarProgram.mtblCalendar.setValueAt(null, i, j);
            }
        }
        final GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        final int nod = cal.getActualMaximum(5);
        final int som = cal.get(7);
        for (int k = 1; k <= nod; ++k) {
            final int row = new Integer((k + som - 2) / 7);
            final int column = (k + som - 2) % 7;
            CalendarProgram.mtblCalendar.setValueAt(k, row, column);
        }
        CalendarProgram.tblCalendar.setDefaultRenderer(CalendarProgram.tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }
    
    static class tblCalendarRenderer extends DefaultTableCellRenderer
    {
        @Override
        public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean selected, final boolean focused, final int row, final int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0 || column == 6) {
                this.setBackground(new Color(255, 220, 220));
            }
            else {
                this.setBackground(new Color(255, 255, 255));
            }
            if (value != null && Integer.parseInt(value.toString()) == CalendarProgram.realDay && CalendarProgram.currentMonth == CalendarProgram.realMonth && CalendarProgram.currentYear == CalendarProgram.realYear) {
                this.setBackground(new Color(220, 220, 255));
            }
            this.setBorder(null);
            this.setForeground(Color.black);
            return this;
        }
    }
    
    static class btnPrev_Action implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (CalendarProgram.currentMonth == 0) {
                CalendarProgram.currentMonth = 11;
                --CalendarProgram.currentYear;
            }
            else {
                --CalendarProgram.currentMonth;
            }
            CalendarProgram.refreshCalendar(CalendarProgram.currentMonth, CalendarProgram.currentYear);
        }
    }
    
    static class btnNext_Action implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (CalendarProgram.currentMonth == 11) {
                CalendarProgram.currentMonth = 0;
                ++CalendarProgram.currentYear;
            }
            else {
                ++CalendarProgram.currentMonth;
            }
            CalendarProgram.refreshCalendar(CalendarProgram.currentMonth, CalendarProgram.currentYear);
        }
    }
    
    static class cmbYear_Action implements ActionListener
    {
        @Override
        public void actionPerformed(final ActionEvent e) {
            if (CalendarProgram.cmbYear.getSelectedItem() != null) {
                final String b = CalendarProgram.cmbYear.getSelectedItem().toString();
                CalendarProgram.currentYear = Integer.parseInt(b);
                CalendarProgram.refreshCalendar(CalendarProgram.currentMonth, CalendarProgram.currentYear);
            }
        }
    }
}
