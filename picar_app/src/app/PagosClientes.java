
package app;

import java.awt.Color;
import java.awt.event.KeyEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fedec
 */
public class PagosClientes extends javax.swing.JFrame {
    DefaultTableModel modelo;
    /** Creates new form PagosClientes */
    public PagosClientes() {
        initComponents();
        LlenarCombo.conectar();
        jLabel7.setVisible(false);
        jLabel8.setVisible(false);
        jTextField2.setVisible(false);
        jLabel9.setVisible(false);
        jComboBox2.setVisible(false);
        try{
        Statement st = LlenarCombo.conexion.createStatement();
        ResultSet pagoinicial = st.executeQuery("select (pagosclientes.idpagosclientes+1) from picar_db.pagosclientes order by pagosclientes.idpagosclientes desc limit 1;");
            if(pagoinicial.next()){
                String formateopago = pagoinicial.getString("(pagosclientes.idpagosclientes+1)");
                jLabel1.setText(formateopago);
            }
        } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        modelo = new DefaultTableModel();
        modelo.addColumn("Tipo de pago");
        modelo.addColumn("Porcentaje %");
        modelo.addColumn("N� de cheque");
        modelo.addColumn("Importe");
        this.jTable1.setModel(modelo);
        jComboBox2.removeAllItems();
        ArrayList<String> traercheques = new ArrayList<String>();
        traercheques = LlenarCombo.llenar_combo11();
        for(int i = 0; i<traercheques.size();i++){
        jComboBox2.addItem(traercheques.get(i));
        }    
        jTextField2.setText("");
        jTextField1.requestFocus();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas | Pagos de clientes");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 255, 51));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("-");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("Limpiar campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel3.setForeground(java.awt.Color.red);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("-");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jTextField1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(0, 153, 0));
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(204, 255, 51));
        jLabel2.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("PAGO N�");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel4.setText("N� de cliente:");

        jLabel5.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("-");
        jLabel5.setFocusable(false);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "DESCUENTO", "CHEQUE", "CHEQUE RECHAZADO", "TRANSFERENCIA" }));
        jComboBox1.setEnabled(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("TOTAL DEL PAGO: $");

        jButton5.setBackground(java.awt.Color.green);
        jButton5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/dolar.png"))); // NOI18N
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("%");

        jTextField2.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel8.setText("Importe:");

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("N� de cheque:");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 0));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Registrar pago");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jMenu1.setText("Cheques");

        jMenuItem1.setText("Registrar cheque");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem3.setText("Mostrar todos");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Pagos registrados");

        jMenuItem2.setText("Mostrar todos");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(6, 6, 6)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jLabel7.setVisible(false);
        jLabel9.setVisible(false);
        jComboBox2.setVisible(false);
        jComboBox1.setEnabled(false);
        jButton5.setEnabled(false);
        jLabel3.setText("-");
        jLabel5.setText("-");
        jComboBox1.setSelectedIndex(0);
        jTextField2.setText("");
        jTextField2.setVisible(false);
        jLabel8.setVisible(false);
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Ventas ventas = new Ventas();
        this.dispose();
        ventas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventas.setLocationRelativeTo(null);
        ventas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        String idcliente = this.jTextField1.getText();
        bcliente.buscarCliente(idcliente.replace(" ", "").trim());
    }//GEN-LAST:event_jTextField1FocusLost
    public class BuscarClienteC{
        public BuscarClienteC(){
        }
        public String buscarCliente(String idcliente){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registro = st.executeQuery("SELECT * FROM picar_db.clientes WHERE clientes.idcliente="+idcliente+";");
                if(registro.next()){
                String traernombre = registro.getString("nombre");
                String traerapellido = registro.getString("apellido");
                String traercuil = registro.getString("cuil");
                String completo = traernombre+" "+traerapellido+". CUIL: "+traercuil;
                jLabel5.setForeground(new Color(0,153,0));
                jLabel5.setText(completo);
                jComboBox1.setEnabled(true);
                jButton5.setEnabled(true);
                    ResultSet saldo = st.executeQuery("SELECT (t2.ventas - t1.cobros) AS saldo FROM (SELECT SUM( DISTINCT importepago) AS cobros FROM pagosclientes) AS t1 CROSS JOIN (SELECT SUM( cantidad * precio_remito ) AS ventas FROM remitos where remitos.cliente_id="+idcliente+") AS t2;");
                    if(saldo.next()){
                    float traersaldo = saldo.getFloat("saldo");
                jLabel3.setText("SALDO: $ "+traersaldo);
                }
                jComboBox1.setSelectedIndex(0);
                jComboBox1.requestFocus();
                    jLabel8.setVisible(true);
                    jTextField2.setVisible(true);
                    jLabel7.setVisible(false);
                    jLabel9.setVisible(false);
                    jComboBox2.setVisible(false);
                }else{
                    jLabel5.setForeground(Color.red);
                    jLabel5.setText("CLIENTE NO ENCONTRADO");
                    jLabel3.setText("-");
                    jLabel7.setVisible(false);
                    jLabel8.setVisible(false);
                    jTextField2.setVisible(false);
                    jLabel9.setVisible(false);
                    jComboBox2.setVisible(false);
                    jComboBox1.setEnabled(false);
                    jButton5.setEnabled(false);
                    jTextField1.requestFocus();
                }
            } catch (SQLException e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return idcliente;
        }
    }
    private transient BuscarClienteC bcliente = new BuscarClienteC();
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String idcliente = this.jTextField1.getText();
        bcliente.buscarCliente(idcliente);
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int item = jComboBox1.getSelectedIndex();
        switch(item){
        case 0:
            jLabel8.setVisible(true);
            jTextField2.setVisible(true);
            jLabel7.setVisible(false);
            jLabel9.setVisible(false);
            jComboBox2.setVisible(false);
            break;
        case 1:
            jLabel8.setVisible(false);
            jTextField2.setVisible(true);
            jLabel7.setVisible(true);
            jLabel9.setVisible(false);
            jComboBox2.setVisible(false);
            break;
        case 2:
            jLabel8.setVisible(true);
            jTextField2.setVisible(true);
            jLabel7.setVisible(false);
            jLabel9.setVisible(true);
            jComboBox2.setVisible(true);
            String chequecombo = (String) jComboBox2.getSelectedItem();
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registro = st.executeQuery("SELECT cheques.importecheque FROM picar_db.cheques WHERE cheques.idcheques="+chequecombo+";");
                if(registro.next()){
                    String traerncheque = registro.getString("importecheque");
                    jTextField2.setText(traerncheque);
                }
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            jButton5.requestFocus();
            break;
        case 3:
            jLabel8.setVisible(true);
            jTextField2.setVisible(true);
            jLabel7.setVisible(false);
            jLabel9.setVisible(true);
            jComboBox2.setVisible(true);
            String chequecombodos = (String) jComboBox2.getSelectedItem();
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registro = st.executeQuery("SELECT cheques.importecheque FROM picar_db.cheques WHERE cheques.idcheques="+chequecombodos+";");
                if(registro.next()){
                    String traerncheque = registro.getString("importecheque");
                    jTextField2.setText(traerncheque);
                }
            } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            jButton5.requestFocus();
            break;
        case 4:
            jLabel8.setVisible(true);
            jTextField2.setVisible(true);
            jLabel7.setVisible(false);
            jLabel9.setVisible(false);
            jComboBox2.setVisible(false);
            break;
        }
        jTextField2.requestFocus();
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public class LlenarTabla{
        public LlenarTabla(){
        }
        public String llenarTabla(String tipopago, String porcentaje, String importe, String ncheque){
                float total = 0;
                Object info[]=new Object[4];
                info[0]=tipopago;
                info[1]=porcentaje;
                info[2]=ncheque;
                info[3]=importe;
                modelo.addRow(info);
                    for(int i = 0;i< jTable1.getRowCount();i++){
                        total += Float.parseFloat(jTable1.getValueAt(i,3).toString());
                    }
                    jLabel6.setText("TOTAL DEL PAGO: $"+total);
                    jTextField2.setText("");
                    jButton3.setEnabled(true);
                    jComboBox1.requestFocus();
        return tipopago;
    }
    }
    private transient LlenarTabla lltabla = new LlenarTabla();
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int index = this.jComboBox1.getSelectedIndex();
        if(index==0||index==4){
            String tipopago = (String) this.jComboBox1.getSelectedItem();
            String porcentaje = "null";
            String importe = this.jTextField2.getText();
            String ncheque = "null";
            lltabla.llenarTabla(tipopago,porcentaje,importe,ncheque);
        }
        if(index==1){
            String tipopago = (String) this.jComboBox1.getSelectedItem();
            String porcentaje = this.jTextField2.getText();
            int porciento = Integer.parseInt(porcentaje);
            float total = 0;
            for(int i = 0;i< jTable1.getRowCount();i++){
                total += Float.parseFloat(jTable1.getValueAt(i,3).toString());
            }
            float multiplicado = (total*porciento)/100;
            String importe = Float.toString(multiplicado);
            String ncheque = "null";
            lltabla.llenarTabla(tipopago,porcentaje,importe,ncheque);
        }
        if(index==2){
            String tipopago = (String) this.jComboBox1.getSelectedItem();
            String porcentaje = "null";
            String importe = this.jTextField2.getText();
            String ncheque = (String) this.jComboBox2.getSelectedItem();
            lltabla.llenarTabla(tipopago,porcentaje,importe,ncheque);
        }
        if(index==3){
            String tipopago = (String) this.jComboBox1.getSelectedItem();
            String porcentaje = "null";
            String importe = "-"+this.jTextField2.getText();
            String ncheque = (String) this.jComboBox2.getSelectedItem();
            lltabla.llenarTabla(tipopago,porcentaje,importe,ncheque);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        jButton5.requestFocus();
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == '.') || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        String chequecombo = (String) jComboBox2.getSelectedItem();
        try{
            Statement st = LlenarCombo.conexion.createStatement();
            ResultSet registro = st.executeQuery("SELECT cheques.importecheque FROM picar_db.cheques WHERE cheques.idcheques="+chequecombo+";");
            if(registro.next()){
                String traerncheque = registro.getString("importecheque");
                jTextField2.setText(traerncheque);
            }
        } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        jButton5.requestFocus();
    }//GEN-LAST:event_jComboBox2ActionPerformed
    public class GenerarPagoCliente{
        public GenerarPagoCliente(){
        }
        public String generarPagoCliente(String npago, String idcliente){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                for(int i = 0;i< jTable1.getRowCount();i++){
                st.executeUpdate("INSERT INTO picar_db.pagosclientes VALUES ("+npago+","+idcliente+",'"+jTable1.getValueAt(i,0)+"',"+jTable1.getValueAt(i,1)+","+jTable1.getValueAt(i,2)+","+jTable1.getValueAt(i,3)+",current_date());");
                }
                
                ResultSet registro = st.executeQuery("SELECT * FROM picar_db.clientes WHERE clientes.idcliente="+idcliente+";");
                if(registro.next()){
                String traernombre = registro.getString("nombre");
                String traerapellido = registro.getString("apellido");
                    ResultSet saldo = st.executeQuery("SELECT (t2.ventas - t1.cobros) AS saldo FROM (SELECT SUM( DISTINCT importepago) AS cobros FROM pagosclientes) AS t1 CROSS JOIN (SELECT SUM( cantidad * precio_remito ) AS ventas FROM remitos where remitos.cliente_id="+idcliente+") AS t2;");
                    if(saldo.next()){
                    float traersaldo = saldo.getFloat("saldo");
            JOptionPane.showMessageDialog(null,"PAGO N�"+npago+" registrado con �xito. Cliente: "+idcliente+" - "+traernombre+" "+traerapellido+". SALDO: $"+traersaldo,"PAGO REGISTRADO",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                Ventas ventas = new Ventas();
                dispose();
                ventas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                ventas.setLocationRelativeTo(null);
                ventas.setVisible(true);
            } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return npago;
        }
    }
    private transient GenerarPagoCliente generarpagocliente = new GenerarPagoCliente();
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String npago = this.jLabel1.getText();
        String idcliente = this.jTextField1.getText();
        generarpagocliente.generarPagoCliente(npago,idcliente);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Cheques cheque = new Cheques(this,true);
        //cheque.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        cheque.setLocationRelativeTo(null);
        cheque.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if(jComboBox2.isVisible()){
            jComboBox2.removeAllItems();
            ArrayList<String> traercheques = new ArrayList<String>();
            traercheques = LlenarCombo.llenar_combo11();
            for(int i = 0; i<traercheques.size();i++){
            jComboBox2.addItem(traercheques.get(i));
            }    
        }
    }//GEN-LAST:event_formWindowActivated

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing
                                                                   .UIManager
                                                                   .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing
                         .UIManager
                         .setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util
                .logging
                .Logger
                .getLogger(PagosClientes.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(PagosClientes.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(PagosClientes.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(PagosClientes.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt
            .EventQueue
            .invokeLater(new Runnable() {
                public void run() {
                    new PagosClientes().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}