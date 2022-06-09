
package client;


import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.rmi.ConnectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

import interfaces.Conference;
import interfaces.Participant;

public class Client extends javax.swing.JFrame{

 
	private static final long serialVersionUID = 1L;
	
	    private javax.swing.JPanel ButtonPanel;
	    
	    private javax.swing.JButton Clear;
	    private javax.swing.JButton Finish;
	    private javax.swing.JButton GetInfo;
	    private javax.swing.JButton Register;
	    
	    private javax.swing.JLabel LabelEmail;
	    private javax.swing.JLabel LabelHost;
	    private javax.swing.JLabel LabelName;
	    private javax.swing.JLabel LabelOrganization;
	    private javax.swing.JLabel LabelParticipants;
	    private javax.swing.JLabel LabelPort;
	    private javax.swing.JLabel LabelReport;
	    private javax.swing.JLabel LabelSurname;
	    private javax.swing.JPanel MainPanel;
	    
	    private javax.swing.JTextField TextEmail;
	    private javax.swing.JTextField TextHost;
	    private javax.swing.JTextField TextName;
	    private javax.swing.JTextField TextOrg;
	    private javax.swing.JTextField TextParticipants;
	    private javax.swing.JTextField TextPort;
	    private javax.swing.JTextField TextReport;
	    private javax.swing.JTextField TextSurname;  
	    
	    private Conference stub;
	    
	    private boolean flag=true;
	
	
	public Client() {
		
		
        initComponents();
    }

                          
    private void initComponents() {

        ButtonPanel = new javax.swing.JPanel();
        
        Register = new javax.swing.JButton();
        Clear = new javax.swing.JButton();
        GetInfo = new javax.swing.JButton();
        Finish = new javax.swing.JButton();
        
        MainPanel = new javax.swing.JPanel();
        LabelHost = new javax.swing.JLabel();
        LabelPort = new javax.swing.JLabel();
        LabelParticipants = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        LabelSurname = new javax.swing.JLabel();
        LabelOrganization = new javax.swing.JLabel();
        LabelEmail = new javax.swing.JLabel();
        LabelReport = new javax.swing.JLabel();
        
        TextHost = new javax.swing.JTextField();
        TextPort = new javax.swing.JTextField();
        TextName = new javax.swing.JTextField();
        TextSurname = new javax.swing.JTextField();
        TextOrg = new javax.swing.JTextField();
        TextReport = new javax.swing.JTextField();
        TextEmail = new javax.swing.JTextField();
        TextParticipants = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Register.setText("Register");
        Register.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {registerParticipant();
                if(flag)
                JOptionPane.showMessageDialog(Client.this, "Спасибо за регистрацию", "Successful", JOptionPane.INFORMATION_MESSAGE);} 
                catch (NumberFormatException | RemoteException | NotBoundException e) {//e.printStackTrace();
                	JOptionPane.showMessageDialog(Client.this, "Не удалось произвести регистрацию", "Error", JOptionPane.ERROR_MESSAGE);}
                }
            }
        );

        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear();
            }
        });

        GetInfo.setText("Get info");
        GetInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {
            	   getInfo();	
			} catch (RemoteException |NullPointerException | NotBoundException e) {//e.printStackTrace(); 
				JOptionPane.showMessageDialog(Client.this, "Не удалось получить информацию", "Error", JOptionPane.ERROR_MESSAGE);} 
			}
            
        });

        Finish.setText("Finish");
        Finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               System.exit(0);
            }
        });

        
        ButtonPanel.setLayout(new FlowLayout());
        ButtonPanel.add(Register);
        ButtonPanel.add(Clear);
        ButtonPanel.add(GetInfo);
        ButtonPanel.add(Finish);
        
        

        LabelHost.setText("Host:");

        TextHost.setText("localhost");
        

        LabelPort.setText("Port:");

        TextPort.setText("1234");
      

        LabelParticipants.setText("Participants:");

        LabelName.setText("Name:");

        TextName.setText("Имя");
       

        LabelSurname.setText("Surname:");

        TextSurname.setText("Фамилия");

        LabelOrganization.setText("Organization:");

        TextOrg.setText("Место работы");

        LabelReport.setText("Report:");

        TextReport.setText("Цель доклада");

        LabelEmail.setText("Email:");

        TextEmail.setText("Почта");
       

        TextParticipants.setEditable(false);
        TextParticipants.setText("0");

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addComponent(LabelHost)
                        .addGap(18, 18, 18)
                        .addComponent(TextHost, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelPort)
                        .addGap(18, 18, 18)
                        .addComponent(TextPort, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelParticipants)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TextParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelSurname)
                            .addComponent(LabelName)
                            .addComponent(LabelOrganization)
                            .addComponent(LabelReport)
                            .addComponent(LabelEmail))
                        .addGap(29, 29, 29)
                        .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextName)
                            .addComponent(TextSurname)
                            .addComponent(TextOrg)
                            .addComponent(TextReport, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(TextEmail))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelHost)
                    .addComponent(TextHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPort)
                    .addComponent(TextPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelParticipants)
                    .addComponent(TextParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelName)
                    .addComponent(TextName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelSurname)
                    .addComponent(TextSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelOrganization)
                    .addComponent(TextOrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelReport)
                    .addComponent(TextReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail)
                    .addComponent(TextEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        this.setResizable(false);
        //System.out.println(this.getWidth() + " " + this.getHeight());
        
        
        //this.setPreferredSize(new Dimension(400,350));
        pack();
    }
    
    private void registerParticipant() throws NumberFormatException, RemoteException, NotBoundException
    {
    	try {
    	String findName = "Registrable";
		Registry reg = LocateRegistry.getRegistry(TextHost.getText(), Integer.parseInt(TextPort.getText()));
    	stub = (Conference)reg.lookup(findName);
    	
    	
    	String name = TextName.getText();
    	String surname = TextSurname.getText();
    	String org = TextOrg.getText();
    	String report = TextReport.getText();
    	String email = TextEmail.getText();
    	Participant p = new Participant(name,surname,org,report,email);
    	TextParticipants.setText(stub.register(p)+"");
    	System.out.println("Client registered");
    	flag=true;
    	}catch(ConnectException e) {JOptionPane.showMessageDialog(Client.this, "Не удалось произвести регистрацию", "Error", JOptionPane.ERROR_MESSAGE); flag=false;}
    				
    }
    
    private void getInfo() throws NumberFormatException, RemoteException, NotBoundException
    {
    	if(stub==null) 
 	   {
 		   String findName = "Registrable";
 		   Registry reg = LocateRegistry.getRegistry(TextHost.getText(), Integer.parseInt(TextPort.getText()));
 		   stub = (Conference)reg.lookup(findName);
 	   }
		String res = stub.getInfo();
		TextParticipants.setText(stub.getSize()+"");
     new TextClient(res).setVisible(true);
    }
    
    private void clear()
    {
    	TextName.setText("");
    	TextSurname.setText("");
    	TextOrg.setText("");
    	TextReport.setText("");
    	TextEmail.setText("");
    }
    
    

                                                  
    public static void main(String args[]) {
       
    	
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	Client client = new Client();
                client.setVisible(true);
                client.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width-client.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().height/2-client.getHeight()/2);
            }
        });
    }
                  
                 
}
