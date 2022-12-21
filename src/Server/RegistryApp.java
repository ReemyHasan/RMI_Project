package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RegistryApp {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(5097);
        registry.rebind("ChatServer",new ServerApp());

    }
}
