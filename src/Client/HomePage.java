package Client;

import Server.IChatServer;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.Naming;

public class HomePage extends JFrame{
    private JButton logInButton;
    private JButton registerButton;
    private JLabel Welcom;
    private JPanel panel;

    public HomePage(IChatServer iChat) {
        this.setContentPane(panel);
        logInButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                LogInPage LogIn = new LogInPage(iChat);
                LogIn.setVisible(true);
                LogIn.setSize(500, 400);

            }
        });
        registerButton.addMouseListener(new MouseAdapter() {
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
