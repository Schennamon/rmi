
package server;

import java.awt.Toolkit;
import java.io.File;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.Permission;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import interfaces.Conference;

public class Server extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JPanel ButtonPanel;
	 
    private javax.swing.JLabel LabelHost;
    private javax.swing.JLabel LabelParticipants;
    private javax.swing.JLabel LabelPort;
    
    private javax.swing.JButton Load;
    private javax.swing.JButton Save;
    private javax.swing.JButton Start;
    private javax.swing.JButton Stop;
    private javax.swing.JButton Exit;
    
    private javax.swing.JTextField TextIp;
    private javax.swing.JTextField TextParticipants;
    private javax.swing.JTextField TextPort;
    
    private javax.swing.JScrollPane jScrollPane1;
    
    private javax.swing.JTextArea MainArea;
    
    private Registrate reg = new Registrate();
    Conference stub;
    Registry registr;
    
    
    private Document doc;
    
    private final JFileChooser fileChooser = new JFileChooser();
	

    
    public Server() {
        initComponents();
    }

    private void initComponents() 
    {
    	fileChooser.setCurrentDirectory(new File("."));
    	
        ButtonPanel = new javax.swing.JPanel();
        Start = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        Save = new javax.swing.JButton();
        Load = new javax.swing.JButton();
        Exit = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainArea = new javax.swing.JTextArea();
        LabelHost = new javax.swing.JLabel();
        LabelPort = new javax.swing.JLabel();
        LabelParticipants = new javax.swing.JLabel();
        TextIp = new javax.swing.JTextField();
        TextPort = new javax.swing.JTextField();
        TextParticipants = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Start.setText("Start");
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {startServer();
               JOptionPane.showMessageDialog(Server.this, "Сервер успешно запущен", "Successful", JOptionPane.INFORMATION_MESSAGE);}
               catch (RemoteException e) {//e.printStackTrace();
            	   JOptionPane.showMessageDialog(Server.this, "Не удалось запустить сервер", "Error", JOptionPane.ERROR_MESSAGE);}
               }
            }
        );

        Stop.setText("Stop");
        Stop.setEnabled(false);
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               try {stopServer();
               JOptionPane.showMessageDialog(Server.this, "Сервер успешно остановлен", "Successful", JOptionPane.INFORMATION_MESSAGE);}
               catch (NumberFormatException | RemoteException | NotBoundException e) {//e.printStackTrace();
            	   JOptionPane.showMessageDialog(Server.this, "Не удалось остановить сервер", "Error", JOptionPane.ERROR_MESSAGE);}
               }
            }
        );

       // Save.setEnabled(false);
        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (JFileChooser.APPROVE_OPTION == fileChooser.showDialog(null,"Save")) {
					String fileName = fileChooser.getSelectedFile().getPath();
					doc = DOMParser.transformDataToDoc(reg.getData());
					try {save(doc,fileName);
					JOptionPane.showMessageDialog(Server.this, "Сохранено", "Error", JOptionPane.INFORMATION_MESSAGE);} 
					catch (TransformerException e) {e.printStackTrace(); 
					JOptionPane.showMessageDialog(Server.this, "Не удалось сохранить", "Error", JOptionPane.ERROR_MESSAGE);}	
            }
            }});
        

        Load.setText("Load");
        Load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
					String fileName = fileChooser.getSelectedFile().getPath();
					doc = DOMParser.parse(fileName);
					DOMParser.transformDocToData(doc, reg.getData());
            }
            }});

        Exit.setText("Exit");
        Exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	System.exit(0);
            }
        });

        javax.swing.GroupLayout ButtonPanelLayout = new javax.swing.GroupLayout(ButtonPanel);
        ButtonPanel.setLayout(ButtonPanelLayout);
        ButtonPanelLayout.setHorizontalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ButtonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Start, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Stop, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Save, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Load, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Exit, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        ButtonPanelLayout.setVerticalGroup(
            ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ButtonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ButtonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Start)
                    .addComponent(Stop)
                    .addComponent(Save)
                    .addComponent(Load)
                    .addComponent(Exit))
                .addContainerGap())
        );

        MainArea.setEditable(false);
        MainArea.setColumns(20);
        MainArea.setRows(5);
        jScrollPane1.setViewportView(MainArea);

        LabelHost.setText("Host: ");

        LabelPort.setText("Port:");

        LabelParticipants.setText("Participants:");

        TextIp.setEditable(false);
        TextIp.setText("localhost");

        TextPort.setText("1234");

        TextParticipants.setEditable(false);
        TextParticipants.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ButtonPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(LabelHost, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextIp, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelPort, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextPort, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(LabelParticipants)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelHost, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPort)
                    .addComponent(LabelParticipants)
                    .addComponent(TextIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextParticipants, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        this.setResizable(false);
        
        reg.getData().addDataParticipantListener(new DataParticipantListener() {
				public void dataChanged(DataChangeEvent e) {
					try {
						  MainArea.setText(reg.getInfo());
						  TextParticipants.setText(reg.getSize()+"");
					} catch (RemoteException e1) {}
					
				}});
        
        try {stub =(Conference) UnicastRemoteObject.exportObject(reg, 0);} 
        catch (RemoteException e1) {e1.printStackTrace();}
        
        pack();
        this.setLocation(0, Toolkit.getDefaultToolkit().getScreenSize().height/2-this.getHeight()/2);
    }
    
                                     
                                      

    private void startServer() throws RemoteException
    {
    	if (registr==null) 
    	{
    		registr = LocateRegistry.createRegistry(Integer.parseInt(TextPort.getText()));
    	}
    	String name = "Registrable";
    	registr.rebind(name, stub);
    	
    	System.out.println("Server started");
    	
    	Save.setEnabled(false);
    	Start.setEnabled(false);
    	Stop.setEnabled(true);
    	Load.setEnabled(false);
    	
    	TextPort.setEditable(false);
    }
    
    private void stopServer() throws NumberFormatException, RemoteException, NotBoundException
    {
    	Registry registr = LocateRegistry.getRegistry(Integer.parseInt(TextPort.getText()));
    	String name = "Registrable";
    	registr.unbind(name);
    	System.out.println("Server stopped");
    	
    	//TextPort.setEditable(true);;
    	
    	Stop.setEnabled(false);
    	Start.setEnabled(true);
    	Save.setEnabled(true);
    	Load.setEnabled(true);
    }
    
    private void save(Document doc,String fileName) throws TransformerException
    {
    	TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
		DOMSource source = new DOMSource(doc);
		StreamResult res = new StreamResult(new File(fileName));
		t.transform(source, res);
    }
    
    
    
    public static void main(String args[]) {
    	System.setProperty("java.rmi.server.codebase", "file://D:\\Уроки\\КПП\\Practice10\\1.jar");
    	if (System.getSecurityManager() == null)
    	{
    		System.setSecurityManager(new SecurityManager()
    				{
    				   public void checkConnect(String host,int port, Object context) {}
    				   public void checkConnect(String host,int port) {}
    				   public void checkPermission(Permission perm) {}
    				});
    	}
    	  
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Server().setVisible(true);
                
            }
        });
    }
                    
                       
}
