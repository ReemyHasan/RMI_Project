package Client;

import Server.IChatServer;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientApp extends UnicastRemoteObject implements IChatClient, Serializable {
    private IChatServer iChatServer;
    public ClientGUI gui = null;
    private String UserName;
    private String FirstName;
    private String LastName;
    private String PSW;
    private boolean status;

    protected ClientApp(String firstName, String lastName, String pSW, IChatServer iChatServer) throws RemoteException {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.PSW = pSW;
        this.UserName = this.FirstName + "." + this.LastName;
        this.iChatServer = iChatServer;
        //this.gui = Gui;
        iChatServer.RegisterChatClient(this);
        status = true;
    }

    @Override
    public void GUI(ClientGUI clientGUI)throws RemoteException {
        this.gui = clientGUI;
    }

    @Override
    public void RetrieveMessage(String Message) throws RemoteException {
        gui.getChat().setText(gui.getChat().getText()+"\n"+Message);
    }

    @Override
    public void RetrieveClients(String Message) throws RemoteException {

            gui.getClients().setText(Message);

    }

    @Override
    public ArrayList<String> ShowUserDetails()throws RemoteException {
        ArrayList<String> details = new ArrayList<>();
        details.add(UserName);
        details.add(PSW);
        return details;
    }

    @Override
    public void ChangeClientStatuesToOnline()throws RemoteException {
        status = true;
    }


    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
        IChatServer iChat = (IChatServer) Naming.lookup("rmi://localhost:5097/ChatServer");
        HomePage homePage = new HomePage(iChat);
        homePage.setVisible(true);
        homePage.setSize(500,400);

    }
}
