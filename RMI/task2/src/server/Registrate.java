package server;

import java.rmi.RemoteException;

import interfaces.Conference;

import interfaces.Participant;

public class Registrate implements Conference {

	private volatile DataParticipant dp;
	
	
	public Registrate()
	{
		dp=new DataParticipant();
	}
	
	
	@Override
	public synchronized int register(Participant p) throws RemoteException {
		dp.addParticipant(p);
		return dp.getSize();
	}

	@Override
	public String getInfo() throws RemoteException {
		return dp.toString();
	}
	
	public int getSize()
	{
		return dp.getSize();
	}
	
	public DataParticipant getData() {
		return dp;
	}
	
	public void setData(DataParticipant dp)
	{
		this.dp=dp;
	}
	
	


}
