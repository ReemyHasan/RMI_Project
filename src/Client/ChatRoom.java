package Client;

import Server.IChatServer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class ChatRoom extends JFrame {
    private JComboBox comboBox1;
    private JTextField roomName;
    private JButton deleteChatRoomButton;
    private JButton createNewChatRoomButton;
    private JPanel panel;
    private JButton goToRoomDetailsButton;

    public ChatRoom(IChatClient iChatClient, IChatServer iChatServer) throws RemoteException {
        this.setContentPane(panel);
        ArrayList<ChatRoomClass> rooms = iChatServer.ShowChatRoom();
        for (ChatRoomClass room : rooms){
            comboBox1.addItem(room.getName()+"     created_by                                        "+room.getCreatedBy().ShowUserDetails().get(0));
        }

        createNewChatRoomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChatRoomClass CRC = null;
                try {
                    CRC = new ChatRoomClass(roomName.getText(), iChatClient);
                    //System.out.println("Chat room is "+CRC.getCreatedBy() +"        "+ CRC.getName() + "\n");

                    iChatServer.AddNewChatRoom(CRC);
                    dispose();
                    ChatRoom chatRoom1 = new ChatRoom(iChatClient, iChatServer);
                    chatRoom1.setVisible(true);
                    chatRoom1.setSize(500, 400);
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        deleteChatRoomButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String c = comboBox1.getSelectedItem().toString();
                //System.out.println("combo box is    "+ c);
                String[] cc = c.split(" ",2);
                //System.out.println(cc[0]+"mmmm");
                int i = 0;
                try {
                    for (int j = 0 ; j<iChatServer.ShowChatRoom().size();j++){
                        //System.out.println("Nameeeeeeeeee   "+ICS.getName()+",,,,,"+ICS.getCreatedBy());
                        //System.out.println(iChatClient.ShowUserDetails().get(0));
                        if (iChatServer.ShowChatRoom().get(j).getName().equals(cc[0])  && iChatServer.ShowChatRoom().get(j).getCreatedBy().ShowUserDetails().get(0).equals(iChatClient.ShowUserDetails().get(0)))
                        {
                            i = 1;
                            iChatServer.deleteChatRoom(j);
                            JOptionPane.showMessageDialog( null,"Room deleted Successfully");
                            dispose();
                            ChatRoom chatRoom = new ChatRoom(iChatClient, iChatServer);
                            chatRoom.setVisible(true);
                            chatRoom.setSize(500, 400);
                            break;
                        }

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
                if (i == 0){
                    JOptionPane.showMessageDialog( null,"Sorry, You don't have permission to delete this room\n");
                }
            }
        });
        goToRoomDetailsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String c = comboBox1.getSelectedItem().toString();
                String[] cc = c.split(" ",2);
                try {
                    for (int j = 0 ; j<iChatServer.ShowChatRoom().size();j++){

                        if (iChatServer.ShowChatRoom().get(j).getName().equals(cc[0]) )
                        {
                            dispose();
                            ChatRoomDetails chatRoomDetails = new ChatRoomDetails(iChatClient, iChatServer,iChatServer.ShowChatRoom().get(j));
                            chatRoomDetails.setVisible(true);
                            chatRoomDetails.setSize(500, 400);
                            break;
                        }

                    }
                } catch (RemoteException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }


}
