package client.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import myUtils.GUITools;

public class RegsAndLogon extends JPanel {
    private JButton logonBt = new JButton("Logon");
    private JButton regsBt = new JButton("Registration");
    private JLabel loginLb = new JLabel("Login:");
    private JLabel passLb = new JLabel("Pass:");
    private JPasswordField passFld = new JPasswordField();
    private JTextField loginFld = new JTextField();
    private JTextPane serverAnswerPan = new JTextPane();

    public void addActionListener(ActionListener al){
        logonBt.addActionListener(al);
        regsBt.addActionListener(al);
    }
    
    public RegsAndLogon() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        Box mainBox = Box.createVerticalBox();
        serverAnswerPan.setEditable(false);
        // Установка одинаковых размеров компонентов. Надписей. Кнопок.
        GUITools.makeSameSize(new JComponent[]{loginLb, passLb});
        GUITools.makeSameSize(new JComponent[]{logonBt, regsBt});
        passFld.setMinimumSize(new Dimension(115, 20));
        passFld.setPreferredSize(new Dimension(115, 20));
        passFld.setMaximumSize(new Dimension(115, 20));
        loginFld.setMinimumSize(new Dimension(115, 20));
        loginFld.setPreferredSize(new Dimension(115, 20));
        loginFld.setMaximumSize(new Dimension(115, 20));
        serverAnswerPan.setMinimumSize(new Dimension(300, 100));
        serverAnswerPan.setPreferredSize(new Dimension(300, 100));
        serverAnswerPan.setMaximumSize(new Dimension(300, 100));
        //Настройка первой полоски
        Box logBox = Box.createHorizontalBox();
        logBox.add(loginLb);
        logBox.add(Box.createHorizontalStrut(5));
        logBox.add(loginFld);
        //Настройка второй полоски
        Box passBox = Box.createHorizontalBox();
        passBox.add(passLb);
        passBox.add(Box.createHorizontalStrut(5));
        passBox.add(passFld);
        //Настройка третьей полоски
        Box btBox = Box.createHorizontalBox();
        btBox.add(logonBt);
        btBox.add(Box.createHorizontalStrut(12));
        btBox.add(regsBt);
        //Добавление всех элементов в основную панель
        mainBox.add(serverAnswerPan);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(logBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(passBox);
        mainBox.add(Box.createVerticalStrut(17));
        mainBox.add(btBox);
        this.add(mainBox);
    }
    
    public void writeServerAnswer(String s){
        serverAnswerPan.setText(s);
    }
    
    public String getLogin (){
        return loginFld.getText();
    }
    
    public String getPass(){
        return new String(passFld.getPassword());
    }
}
