package client;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TestFrame extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private JScrollPane jScrollPane1 = new JScrollPane(); 
    
    private DefaultListModel listModel = new DefaultListModel();
    private JList jList1 = new JList(listModel);
    private JTextField jTextField1 = new JTextField();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTextArea jTextArea1 = new JTextArea();

    private ServerConnection sc;
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
        jScrollPane1.setPreferredSize(new Dimension(130, 130));
        jTextField1.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jTextField1_actionPerformed(e);
                }
            });
        jTextArea1.setEditable(false);
        jScrollPane1.getViewport().add(jList1, null);
        this.getContentPane().add(jScrollPane1, BorderLayout.EAST);
        this.getContentPane().add(jTextField1, BorderLayout.SOUTH);

        jScrollPane2.getViewport().add(jTextArea1, null);
        this.getContentPane().add(jScrollPane2, BorderLayout.CENTER);
        sc = new ServerConnection("127.0.0.1", this);
        sc.start();
        sc.setName("test");
    }
    
    public static void main(String[] args){
        JFrame frame = new TestFrame();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation((d.width-frameSize.width)/2, (d.height-frameSize.height)/2);
        frame.setVisible(true);
    }

    private void jTextField1_actionPerformed(ActionEvent e) {
        sc.commonChat(jTextField1.getText());
    }
    
    public void append(String s){
        jTextArea1.append(s + "\n");
    }
    
    public void addElement(String name, String id){
        listModel.addElement(name + " " + id);
    }
    public void removeElement(String id){
        listModel.removeElement(Integer.parseInt(id));
    }
}
