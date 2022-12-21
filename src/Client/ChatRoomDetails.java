package Client;

import Server.IChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.rmi.RemoteException;

public class ChatRoomDetails extends JFrame implements Serializable {
    private JPanel panel;
    private JTextArea Details;
    private JButton JOINButton;
    private JButton backButton;
    private JLabel RoomName;

    public ChatRoomDetails(IChatClient iChatClient, IChatServer iChatServer,ChatRoomClass chatRoomClass) throws HeadlessException, RemoteException {
        this.setContentPane(panel);
        String Users = "";
        for(IChatClient iChatClient1: chatRoomClass.getJoinedUsers())
        {
            Users+=iChatClient1.ShowUserDetails().get(0)+"\n";
        }
        this.RoomName.setText( chatRoomClass.getName()+" Details");
        Details.setText("Created by "+chatRoomClass.getCreatedBy().ShowUserDetails().get(0)+"\nJoined Users:\n"+ Users);
        JOINButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    chatRoomClass.AddNewUser(iChatClient);
                    iChatServer.AddClientToChatRoom(chatRoomClass,iChatClient);
                    dispose();
                    ClientGUI clientGUI = new ClientGUI(iChatServer,iChatClient, chatRoomClass);
                    clientGUI.setVisible(true);
                    clientGUI.setSize(500, 400);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
