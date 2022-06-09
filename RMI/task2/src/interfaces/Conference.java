package interfaces;


import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Conference  extends Remote {
	
	public int register(Participant p) throws RemoteException;
	public String getInfo() throws RemoteException;
	public int getSize()throws RemoteException;

}
