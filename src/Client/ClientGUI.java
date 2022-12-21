package Client;

import Server.IChatServer;
import Server.ServerApp;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.rmi.RemoteException;

public class ClientGUI extends JFrame implements Serializable {

    private JPanel panel;
    private JTextArea chat;

    public JTextArea getChat() {
        return chat;
    }
    private JTextField text;
    private JTextArea clients;
    private JButton sendButton;

    public JTextArea getClients() {
        return clients;
    }
    public ClientGUI(IChatServer iChatServer, IChatClient iChatClient, ChatRoomClass chatRoomClass) throws RemoteException {
        this.setContentPane(panel);
        iChatClient.GUI(this);
        /*String Users="";
        for(IChatClient iChatClient1: chatRoomClass.getJoinedUsers())
        {
            Users+=iChatClient1.ShowUserDetails().get(0)+"\n";
        }
        clients.setText(Users);*/
        iChatServer.BroadCastClient(chatRoomClass);
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //String resMsg;
                try {

                    iChatServer.BroadCastMessage(iChatClient.ShowUserDetails().get(0)+": "+text.getText());
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
