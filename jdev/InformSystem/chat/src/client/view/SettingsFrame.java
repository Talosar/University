package client.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;

public class SettingsFrame extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jCardPan = new JPanel();
    private CardLayout cardLayout1 = new CardLayout();
    private JList settingsList = new JList();
    private JLabel jLabel1 = new JLabel();
    private JPanel jConnectPan = new JPanel();
    private JLabel jLabel2 = new JLabel();
    private JTextField jTextField1 = new JTextField();
    private GridBagLayout gridBagLayout1 = new GridBagLayout();

    public SettingsFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.getContentPane().setLayout(borderLayout1);
        this.setSize( new Dimension(400, 300) );
        this.setTitle( "Settings" );
        jCardPan.setLayout(cardLayout1);
        settingsList.setMinimumSize(new Dimension(115, 0));
        settingsList.setPreferredSize(new Dimension(115, 0));
        settingsList.setMaximumSize(new Dimension(115, 0));
        //settingsList.getMo
        jLabel1.setText("jLabel1");
        jConnectPan.setLayout(gridBagLayout1);
        jLabel2.setText("TestLabel");
        jConnectPan.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                    new Insets(0, 0, 0, 0), 95, 263));
        jConnectPan.add(jTextField1, new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
                    new Insets(0, 0, 0, 1), 137, 258));
        jCardPan.add(jConnectPan, "jConnectPan");
        this.getContentPane().add(jCardPan, BorderLayout.CENTER);
        this.getContentPane().add(settingsList, BorderLayout.WEST);
    }
}
