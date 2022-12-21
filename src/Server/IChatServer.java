package Server;

import Client.ChatRoomClass;
import Client.IChatClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IChatServer extends Remote {
        void RegisterChatClient(IChatClient iChatClient)throws RemoteException;
        void BroadCastMessage(String msg) throws RemoteException;
        void BroadCastClient( ChatRoomClass chatRoomClass)throws RemoteException;
        void AddClientToChatRoom(ChatRoomClass chatRoomClass,IChatClient iChatClient) throws RemoteException;
        void AddNewChatRoom(ChatRoomClass chatRoomClass)throws RemoteException;
        void deleteChatRoom(int j) throws RemoteException;
        ArrayList<IChatClient> ShowClient()throws RemoteException;
        ArrayList<ChatRoomClass> ShowChatRoom()throws RemoteException;
}
