package client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TestFrame extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JList jList1 = new JList();
    private JTextField jTextField1 = new JTextField();

    public TestFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(borderLayout1);
        this.setSize(new Dimension(400, 300));
        jScrollPane1.getViewport().add(jList1, null);
        this.getContentPane().add(jScrollPane1, BorderLayout.CENTER);
        this.getContentPane().add(jTextField1, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args){
        JFrame frame = new TestFrame();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation((d.width-frameSize.width)/2, (d.height-frameSize.height)/2);
        frame.setVisible(true);
    }
}
