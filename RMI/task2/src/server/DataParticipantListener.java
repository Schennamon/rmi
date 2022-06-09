package server;


import java.util.EventListener;

public interface DataParticipantListener extends EventListener{
		public void dataChanged(DataChangeEvent e);
	}
