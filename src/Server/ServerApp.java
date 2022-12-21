package Server;

import Client.ChatRoomClass;
import Client.IChatClient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;

public class ServerApp extends  UnicastRemoteObject implements IChatServer, Serializable {
    private ArrayList<IChatClient> iChatClients = new ArrayList<IChatClient>();
    private ArrayList<ChatRoomClass> ChatRooms = new ArrayList<ChatRoomClass>();

    protected ServerApp() throws RemoteException {

    }

    @Override
    public void RegisterChatClient(IChatClient iChatClient) throws RemoteException {
        this.iChatClients.add(iChatClient);
    }

    @Override
    public void BroadCastMessage(String msg) throws RemoteException {
        int i =0;
        while(i< iChatClients.size()){
            iChatClients.get(i++).RetrieveMessage(msg);
        }
    }

    @Override
    public void BroadCastClient( ChatRoomClass chatRoomClass) throws RemoteException {
        String Users = "";
        for(IChatClient iChatClient1: chatRoomClass.getJoinedUsers())
        {
            Users+=iChatClient1.ShowUserDetails().get(0)+"\n";
        }
        ChatRoomClass s = new ChatRoomClass();
        for (ChatRoomClass c:ChatRooms){
            if(c.getName().equals(chatRoomClass.getName())) {
                s = c;
                break;
            }
        }
        ArrayList<IChatClient> RoomClients = s.getJoinedUsers();
        for(IChatClient RoomClient: RoomClients){
            RoomClient.RetrieveClients(Users);
        }
    }

    @Override
    public void AddClientToChatRoom(ChatRoomClass chatRoomClass,IChatClient iChatClient) throws RemoteException {
        for (ChatRoomClass c:ChatRooms){
            if(c.getName().equals(chatRoomClass.getName())) {
                c.AddNewUser(iChatClient);
                break;
            }
        }
    }

    @Override
    public void AddNewChatRoom(ChatRoomClass chatRoomClass) throws RemoteException {
        this.ChatRooms.add(chatRoomClass);
    }

    @Override
    public void deleteChatRoom(int j) throws RemoteException {
        /*for(ChatRoomClass d : ChatRooms)
            System.out.print(d.getName()+",");
        System.out.println("");*/
        ChatRooms.remove(j);
        /*for(ChatRoomClass d : ChatRooms)
            System.out.print(d.getName()+",");
        System.out.println("");*/
    }

    @Override
    public ArrayList<IChatClient> ShowClient()throws RemoteException {
        /*for(IChatClient d : iChatClients) {
            //System.out.print(d.ShowUserDetails().get(0) + "," + d.ShowUserDetails().get(1));
           // System.out.println("");
        }
        System.out.println("");*/
        return iChatClients;
    }

    @Override
    public ArrayList<ChatRoomClass> ShowChatRoom() throws RemoteException{

        return ChatRooms;
    }

}
