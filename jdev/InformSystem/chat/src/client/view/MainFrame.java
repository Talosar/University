package client.view;

import client.ConfigFile;

import client.ServerConnection;

import java.awt.*;
import java.awt.event.*;

import java.net.ConnectException;

import javax.swing.*;

public class MainFrame extends JFrame {
    private JMenuBar menuBar = new JMenuBar();
    private JMenu menuFile = new JMenu();
    private JMenuItem menuFileExit = new JMenuItem();
    private JMenu menuHelp = new JMenu();
    private JMenuItem menuHelpAbout = new JMenuItem();
    private CardLayout cardLo = new CardLayout();
    private JPanel chatWindow = new ChatWindow();
    private RegsAndLogon regsWindow = new RegsAndLogon();
    private Container contentPan;
    private JMenuItem menuFileSettings = new JMenuItem();
    private ConfigFile configFile;
    private ServerConnection sc;
    
    public MainFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        configFile = new ConfigFile();
        
        this.setJMenuBar( menuBar );
        contentPan = this.getContentPane();
        contentPan.setLayout( cardLo );
        menuFileSettings.setText("Settings");
        menuFileSettings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    menuFileSettings_actionPerformed(e);
                }
            });
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "SimpleChat" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText( "Help" );
        menuHelpAbout.setText( "About" );
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        menuFile.add(menuFileSettings);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);

        this.getContentPane().add("reg", regsWindow);
        regsWindow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (ae.getActionCommand().equals("Logon")) {
                        sc.login(regsWindow.getLogin(), regsWindow.getPass());
                        //cardLo.show(contentPan, "chat");
                    } else {
                        sc.registration(regsWindow.getLogin(), regsWindow.getPass());
                    }
                }
            });
        this.getContentPane().add("chat", chatWindow);
        
        try {
            sc = new ServerConnection("127.0.0.1", this);
            sc.start();
        } catch (ConnectException e){
            regsWindow.writeServerAnswer("Connection refused");
        }
    }//jbInit
    
    public ConfigFile getConfigFile(){
        return configFile;
    }
    
    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new MainFrame_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
    }

    private void menuFileSettings_actionPerformed(ActionEvent e) {
        this.setEnabled(false);
        JFrame settingsFrame = new SettingsFrame(this);
        settingsFrame.setVisible(true);
    }
}
