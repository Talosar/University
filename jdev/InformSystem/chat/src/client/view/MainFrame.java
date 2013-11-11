package client.view;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
    private JMenuItem jMenuItem1 = new JMenuItem();

    public MainFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setJMenuBar( menuBar );
        contentPan = this.getContentPane();
        contentPan.setLayout( cardLo );
        jMenuItem1.setText("Settings");
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "SimpleChat" );
        menuFile.setText( "File" );
        menuFileExit.setText( "Exit" );
        menuFileExit.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { fileExit_ActionPerformed( ae ); } } );
        menuHelp.setText( "Help" );
        menuHelpAbout.setText( "About" );
        menuHelpAbout.addActionListener( new ActionListener() { public void actionPerformed( ActionEvent ae ) { helpAbout_ActionPerformed( ae ); } } );
        menuFile.add(jMenuItem1);
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);

        this.getContentPane().add("reg", regsWindow);
        regsWindow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    if (ae.getActionCommand().equals("Logon")) {
                        cardLo.show(contentPan, "chat");
                    }
                }
            });
        this.getContentPane().add("chat", chatWindow);

    }
    
    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    void helpAbout_ActionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, new MainFrame_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
    }
}
