package client.view;

import java.awt.Dimension;

import java.awt.Rectangle;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class RegsAndLogon extends JPanel {
    private JButton logonButton = new JButton();
    private JButton regsButton = new JButton();
    private JLabel jLabel2 = new JLabel();
    private JLabel jLabel3 = new JLabel();
    private JPasswordField jPasswordField1 = new JPasswordField();
    private JTextField jTextField1 = new JTextField();
    private JTextPane jTextPane1 = new JTextPane();

    public void addActionListener(ActionListener al){
        logonButton.addActionListener(al);
        regsButton.addActionListener(al);
    }
    
    public RegsAndLogon() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout( null );
        logonButton.setText("Logon");
        logonButton.setBounds(new Rectangle(80, 215, 95, 20));
        regsButton.setText("Registration");
        regsButton.setBounds(new Rectangle(205, 215, 95, 20));
        jLabel2.setText("Login:");
        jLabel2.setBounds(new Rectangle(55, 155, 34, 14));
        jLabel3.setText("Pass:");
        jLabel3.setBounds(new Rectangle(55, 180, 34, 14));
        jPasswordField1.setBounds(new Rectangle(105, 175, 150, 20));
        jTextField1.setBounds(new Rectangle(105, 150, 150, 20));
        jTextPane1.setBounds(new Rectangle(60, 30, 270, 75));
        jTextPane1.setEditable(false);
        this.add(jTextPane1, null);
        this.add(jTextField1, null);
        this.add(jPasswordField1, null);
        this.add(jLabel3, null);
        this.add(jLabel2, null);
        this.add(regsButton, null);
        this.add(logonButton, null);
    }
}
