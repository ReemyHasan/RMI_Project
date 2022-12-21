package Client;

import Server.IChatServer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class SignUpForm extends JFrame{
    private JTextField First;
    private JTextField Last;
    private JButton Register;
    private JLabel FirstName;
    private JLabel LastName;
    private JLabel passwordlabel;
    private JPanel panel;
    private JTextField passwordfield;

    public SignUpForm(IChatServer iChat) {
        this.setContentPane(panel);
        Register.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (First.getText()!=null && Last.getText()!=null && passwordfield.getText()!= null) {
                    String Name = First.getText()+"."+Last.getText();

                    IChatClient c = null;
                    try {
                        c = iChat.ShowClient().stream().filter(
                                iChatClient -> {
                                    try {
                                        return Name.equals(iChatClient.ShowUserDetails().get(0));
                                    } catch (RemoteException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                        ).findAny().orElse(null);
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (c==null) {
                        try {
                            ClientApp client = new ClientApp(First.getText().toString(),Last.getText().toString(),passwordfield.getText().toString(),iChat);
                            dispose();
                            ChatRoom chatRoom = new ChatRoom(client, iChat);
                            chatRoom.setVisible(true);
                            chatRoom.setSize(500, 400);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                    else {
                        JOptionPane.showMessageDialog( null,"UserName was already Used \n please change it and try again");
                    }
                }
                else {
                    JOptionPane.showMessageDialog( null,"there is an empty field \n please fill it and try again");
                }
            }
        });
    }


}
