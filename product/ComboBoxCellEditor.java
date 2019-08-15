// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.Component;
import javax.swing.JTable;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import java.io.Serializable;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;

public class ComboBoxCellEditor extends AbstractCellEditor implements ActionListener, TableCellEditor, Serializable
{
    private JComboBox comboBox;
    
    public ComboBoxCellEditor(final JComboBox comboBox) {
        (this.comboBox = comboBox).putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        this.comboBox.addActionListener(this);
        ((JComponent)comboBox.getEditor().getEditorComponent()).setBorder(null);
    }
    
    private void setValue(final Object value) {
        this.comboBox.setSelectedItem(value);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("comboBoxEdited")) {
            this.stopCellEditing();
        }
    }
    
    @Override
    public Object getCellEditorValue() {
        return this.comboBox.getSelectedItem();
    }
    
    @Override
    public boolean stopCellEditing() {
        if (this.comboBox.isEditable()) {
            this.comboBox.actionPerformed(new ActionEvent(this, 0, ""));
        }
        this.fireEditingStopped();
        return true;
    }
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        this.setValue(value);
        return this.comboBox;
    }
}
