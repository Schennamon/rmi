package server;

import java.io.Serializable;
import java.util.ArrayList;

import interfaces.Participant;

public class DataParticipant implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Participant> arr;
	private ArrayList<DataParticipantListener> listeners = new ArrayList<>();
	private DataChangeEvent event=new DataChangeEvent(this);
	
	
	public DataParticipant()
	{
		setArr(new ArrayList<>());
	}

	public ArrayList<Participant> getArr() {
		return arr;
	}
	
	public void clear()
	{
		arr.clear();
	}

	public void setArr(ArrayList<Participant> arr) {
		this.arr = arr;
		fireDataChange();
	}
	
	public void addParticipant(Participant p)
	{
		arr.add(p);
		fireDataChange();
	}
	
	public int getSize()
	{
		return arr.size();
	}
	
	public Participant getParticipant(int i)
	{
		return arr.get(i);
	}
	
	public Participant removeParticipant(int i)
	{
		fireDataChange();
		return arr.remove(i);
	}
	
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i=0;i<arr.size();i++)
		{
			sb.append(i+1 + ") ");
			sb.append(getParticipant(i).toString());
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void addDataParticipantListener(DataParticipantListener l) {
		listeners.add(l);
	}

	public void removeDataParticipantListener(DataParticipantListener l) {
		listeners.remove(l);
	}
	
	public void fireDataChange() {
		for (DataParticipantListener l : listeners)
			l.dataChanged(event);
	}
	
	

}
