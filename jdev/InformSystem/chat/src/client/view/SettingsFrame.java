package client.view;

import client.ConfigFile;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import myUtils.GUITools;

public class SettingsFrame extends JFrame {
    private BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jCardPan = new JPanel();
    private CardLayout cardLayout1 = new CardLayout();
    private DefaultListModel listModel = new DefaultListModel();
    private JList settingsList = new JList(listModel);
    private JLabel connectLb = new JLabel("Server address");
    private JButton saveBt = new JButton("Save");
    private JButton cancelBt = new JButton("Cancel");
    private JTextField addressFld = new JTextField();
    private JFrame parentFrame; //вызывающее окно
    private ConfigFile confFile;
    
    public SettingsFrame(JFrame f) {
        parentFrame = f;
        confFile = ((MainFrame)parentFrame).getConfigFile();
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
        this.setMinimumSize(new Dimension(350,200));
        //Настройка размеров компонентов
        settingsList.setMinimumSize(new Dimension(115, 0));
        settingsList.setPreferredSize(new Dimension(115, 0));
        settingsList.setMaximumSize(new Dimension(115, 0));
        addressFld.setMinimumSize(new Dimension(115, 20));
        addressFld.setPreferredSize(new Dimension(115, 20));
        addressFld.setMaximumSize(new Dimension(115, 20));
        GUITools.makeSameSize(new JComponent[]{saveBt, cancelBt});
        
        addressFld.setText(confFile.getServerAddress());
        
        Box connectBox = Box.createVerticalBox();
        Box addressBox = Box.createHorizontalBox();
        
        addressBox.add(Box.createHorizontalStrut(12));
        addressBox.add(connectLb);
        addressBox.add(Box.createHorizontalStrut(5));
        addressBox.add(addressFld);
        addressBox.add(Box.createHorizontalGlue());
        
        
        Box btBox = Box.createHorizontalBox();
        
        btBox.add(Box.createHorizontalGlue());
        btBox.add(saveBt);
        btBox.add(Box.createHorizontalStrut(12));
        btBox.add(cancelBt);
        btBox.add(Box.createHorizontalStrut(12));
        connectBox.add(addressBox);
        connectBox.add(Box.createVerticalGlue());
        connectBox.add(btBox);
        
        jCardPan.add(connectBox, "connectBox");
        this.getContentPane().add(jCardPan, BorderLayout.CENTER);
        
        listModel.addElement("Connection");
        this.getContentPane().add(settingsList, BorderLayout.WEST);
        
        //добавление блоков прослушивания для кнопок
        saveBt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jSaveBt_actionPerformed(e);
                }
            });
        cancelBt.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    jCancelBt_actionPerformed(e);
                }
            });
    }

    private void jCancelBt_actionPerformed(ActionEvent e) {
        parentFrame.setEnabled(true);
        this.setVisible(false);
        this.dispose();
    }

    private void jSaveBt_actionPerformed(ActionEvent e) {
        confFile.saveConfig(addressFld.getText());  //добавить обработку ;;
        parentFrame.setEnabled(true);
        this.setVisible(false);
        this.dispose();
    }
}
