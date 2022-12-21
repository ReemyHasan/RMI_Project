package Client;

import Server.IChatServer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class LogInPage extends JFrame{
    private JTextField UserName;
    private JPasswordField PasswordField;
    private JLabel Username;
    private JLabel Password;
    private JPanel panel;
    private JButton logInButton;
    private JButton signUPButton;

    public LogInPage(IChatServer iChat) {
        this.setContentPane(panel);
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (Username.getText()!=null && PasswordField.getText()!=null ) {

                    IChatClient c = null;
                    try {
                        String UserName1 =  UserName.getText().toString();
                        String psw = PasswordField.getText().toString();
                        //System.out.println(UserName1+", "+psw);
                        for(IChatClient client:iChat.ShowClient()){
                                  if(client.ShowUserDetails().get(0).equals(UserName1)
                                          && client.ShowUserDetails().get(1).equals(psw))
                                  {
                                     // System.out.println("Hellllllllllooooooooooo");
                                      client.ChangeClientStatuesToOnline();
                                      c = client;
                                  }

                                }
                    } catch (RemoteException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (c!=null) {
                        dispose();
                        ChatRoom chatRoom = null;
                        try {
                            chatRoom = new ChatRoom(c, iChat);
                        } catch (RemoteException ex) {
                            throw new RuntimeException(ex);
                        }
                        chatRoom.setVisible(true);
                        chatRoom.setSize(500, 400);

                    }
                    else {
                        JOptionPane.showMessageDialog( null,"UserName or Password is not correct \n try again or sign up");
                    }
                }
                else {
                    JOptionPane.showMessageDialog( null,"there is an empty field \n please fill it and try again");
                }
            }
        });
        signUPButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                SignUpForm signUpForm = new SignUpForm( iChat);
                signUpForm.setVisible(true);
                signUpForm.setSize(500,500);
            }
        });
    }


}
