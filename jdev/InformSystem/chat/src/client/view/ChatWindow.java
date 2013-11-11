package client.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ChatWindow extends JPanel {
    private JPanel leftPanel = new JPanel(),
               rightPanel = new JPanel();
    private JList jList1 = new JList();
    private JLabel listLabel = new JLabel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JTextField msgTField = new JTextField();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTextArea chatTArea = new JTextArea();
    private JLabel msgsLabel = new JLabel();

    public ChatWindow() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setLayout(new BorderLayout());
        jList1.setBounds(new Rectangle(350, 25, 130, 335));
        listLabel.setText("Список пользователей");
        listLabel.setHorizontalAlignment(SwingConstants.CENTER);
        jScrollPane1.setBounds(new Rectangle(0, 305, 345, 55));
        jScrollPane2.setBounds(new Rectangle(0, 25, 345, 280));
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        chatTArea.setEditable(false);
        msgsLabel.setText("История сообщений");
        msgsLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        msgsLabel.setHorizontalAlignment(SwingConstants.CENTER);
         
        /**Настройка поля ввода сообщений*/
        jScrollPane1.getViewport().add(msgTField, null);
        msgTField.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    chatTArea.append("Name: "+msgTField.getText()+'\n');
                    msgTField.setText("");
                }
            });
        msgTField.setFont(new Font("TimesRoman", Font.PLAIN,12));  
        
        /**Настройка поля истории сообщений*/
        msgTField.setSize(new Dimension(282, 42));
        msgTField.setPreferredSize(new Dimension(4, 25));
        msgTField.setMinimumSize(new Dimension(4, 25));
        chatTArea.setLineWrap(true);
        chatTArea.setFont(new Font("TimesRoman", Font.PLAIN,12));        
        
        jScrollPane2.getViewport().add(chatTArea, null);
                
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(msgsLabel, BorderLayout.NORTH);
        leftPanel.add(jScrollPane2, BorderLayout.CENTER);
        leftPanel.add(jScrollPane1, BorderLayout.SOUTH);
        this.add(leftPanel, BorderLayout.CENTER);
        
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(listLabel, BorderLayout.NORTH);
        rightPanel.add(jList1, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    void fileExit_ActionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
