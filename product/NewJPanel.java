// 
// Decompiled by Procyon v0.5.36
// 

package product;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.JProgressBar;
import javax.swing.JPanel;

public class NewJPanel extends JPanel
{
    private JProgressBar jProgressBar1;
    
    public NewJPanel() {
        this.initComponents();
    }
    
    private void initComponents() {
        this.jProgressBar1 = new JProgressBar();
        final GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jProgressBar1, -1, 400, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jProgressBar1, -2, 50, -2).addGap(0, 0, 32767)));
    }
    
    public void setMaxProcess(final int i) {
        this.jProgressBar1.setMaximum(i);
    }
    
    public void setProgress(final int i) {
        this.jProgressBar1.setValue(i);
    }
    
    int getProgress() {
        return this.jProgressBar1.getValue();
    }
}
