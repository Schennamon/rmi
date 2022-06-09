
package client;


public class TextClient extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JLabel Info;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea MainArea;

    public TextClient(String text) {
        initComponents(text);
    }

                            
    private void initComponents(String text) {

        jScrollPane1 = new javax.swing.JScrollPane();
        MainArea = new javax.swing.JTextArea();
        Info = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainArea.setColumns(20);
        MainArea.setRows(5);
        jScrollPane1.setViewportView(MainArea);

        Info.setText("Info:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Info, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(277, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(Info)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        
        MainArea.setText(text);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
    }                        

    /*public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TextClient().setVisible(true);
            }
        });
    }*/
                    
                  
}
