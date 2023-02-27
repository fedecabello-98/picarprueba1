
package app;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.*;
import java.awt.print.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;


import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;


/**
 *
 * @author fedec
 */
public class RemitoModificado extends javax.swing.JFrame implements Printable {
        public class GenerarVenta{
            public GenerarVenta(){
            }
            public String generarVenta(TableModel tablaproductos, String idcliente, String nremito, int idvendedor){
                try{
                    Statement st = LlenarCombo.conexion.createStatement();
                    ResultSet registrocliente = st.executeQuery("SELECT clientes.idcliente,clientes.nombre,clientes.apellido,clientes.cuil,clientes.alias,clientes.direccion,clientes.telefono,clientes.localidad_id,localidades.localidad,clientes.provincia_id,provincias.provincia FROM picar_db.clientes JOIN picar_db.localidades ON clientes.localidad_id=localidades.idlocalidades JOIN picar_db.provincias ON clientes.provincia_id=provincias.idprovincias WHERE clientes.idcliente="+idcliente+";");
                    float total = 0;
                    if(registrocliente.next()){
                    String traernombre = registrocliente.getString("nombre");
                    String traerapellido = registrocliente.getString("apellido");
                    String traercuil = registrocliente.getString("cuil");
                    String traeralias = registrocliente.getString("alias");
                    String traerdireccion = registrocliente.getString("direccion");
                    String traertelefono = registrocliente.getString("telefono");
                    String traeridlocalidad = registrocliente.getString("localidad_id");
                    String traerlocalidad = registrocliente.getString("localidad");
                    String traeridprovincia = registrocliente.getString("provincia_id");
                    String traerprovincia = registrocliente.getString("provincia");
                    jTable1.setModel(tablaproductos);
                    jLabel17.setText("Cliente: "+idcliente+"-"+traerapellido+" "+traernombre);
                    jLabel14.setText("C.U.I.T.: "+traercuil);
                    jLabel18.setText("Alias: "+traeralias);
                    jLabel20.setText("Domicilio: "+traerdireccion);
                    jLabel21.setText("Teléfono: "+traertelefono);
                    jLabel19.setText("Localidad: "+traeridlocalidad+"-"+traerlocalidad+". "+traeridprovincia+"-"+traerprovincia);
                    for(int i = 0;i< jTable1.getRowCount();i++){
                        total += Float.parseFloat(jTable1.getValueAt(i,4).toString());
                        try{
                            st.executeUpdate("INSERT INTO picar_db.remitos VALUES ("+nremito+","+idcliente+",'"+jTable1.getValueAt(i,0)+"','"+jTable1.getValueAt(i,1)+"',"+jTable1.getValueAt(i,2)+","+jTable1.getValueAt(i, 3)+",current_date(),"+idvendedor+");");
                            st.executeUpdate("UPDATE picar_db.productos SET stock = stock-"+jTable1.getValueAt(i,2)+" WHERE codigo='"+jTable1.getValueAt(i, 0)+"';");
                        }catch(SQLException ex){
                            System.out.println(ex);
                            JOptionPane.showMessageDialog(null, "ERROR: "+ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    jLabel15.setText("TOTAL: $"+total);
                    }
                } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                return idcliente;
            }
        }
        private transient GenerarVenta generarremito = new GenerarVenta();
    /** Creates new form RemitoOculto */
    public RemitoModificado() {
        initComponents();
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String fechasistema = formatter.format(fecha);
        jLabel8.setText("Fecha: "+fechasistema);
        String nremito = FacturacionRemitoModificado.titulo1.getText();
        jLabel7.setText("N° "+nremito);

        TableModel tablaproductos = FacturacionRemitoModificado.jTable1.getModel();
        //DefaultTableModel tablaproductos = (DefaultTableModel)FacturacionRemito.jTable1.getModel();
        String idcliente = FacturacionRemitoModificado.jLabel2.getText();
        int idvendedor = FacturacionRemitoModificado.jComboBox1.getSelectedIndex()+1;
        generarremito.generarVenta(tablaproductos,idcliente.replace(" -",""),nremito,idvendedor);
        TableColumnModel columnmodel = jTable1.getColumnModel();
        columnmodel.getColumn(0).setPreferredWidth(70);
        columnmodel.getColumn(0).setHeaderValue("CÓD.");
        columnmodel.getColumn(1).setPreferredWidth(380);
        columnmodel.getColumn(2).setPreferredWidth(55);
        columnmodel.getColumn(2).setHeaderValue("CANT.");
        //columnmodel.getColumn(3).setPreferredWidth(20);
        columnmodel.getColumn(3).setHeaderValue("P/UNIT.");
        //columnmodel.getColumn(4).setPreferredWidth(20);
        jScrollPane1.getViewport().setBackground(Color.white);
        
        JTableHeader th;
        th = jTable1.getTableHeader();
        Font fuente = new Font("Arial", Font.BOLD, 8);
        th.setFont(fuente);
        
        jButton1.requestFocus();
        SetImageLabel(jLabel2, "src/imagenes/picarlogo.png");
            PrinterJob gap = PrinterJob.getPrinterJob();
            gap.setJobName("DISTRIBUIDORA PICAR | REMITO N° "+nremito);
            gap.setPrintable(this);
            boolean top = gap.printDialog();
            if(top){
            try{
                gap.print();
                this.dispose();
            }                  
        catch(Exception PrintException){
                PrintException.printStackTrace();
                JOptionPane.showMessageDialog(null, "ERROR AL IMPRIMIR: "+PrintException, "ERROR", JOptionPane.ERROR_MESSAGE);
                this.dispose();
                }            
            }
    }
        private void SetImageLabel(JLabel labelName, String root) {
            ImageIcon image = new ImageIcon(root);
            Icon icon =
                new ImageIcon(image.getImage()
                              .getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH));
            labelName.setIcon(icon);
            this.repaint();
        }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas | Último remito generado");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setPreferredSize(new java.awt.Dimension(420, 595));
        jPanel1.setRequestFocusEnabled(false);
        jPanel1.setVerifyInputWhenFocusTarget(false);
        jPanel1.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(180, 0, 50, 50);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 10, 150, 40);

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("IVA: INSCRIPTO");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 70, 150, 20);

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Documento no válido");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(150, 50, 120, 14);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("como factura");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(150, 60, 120, 14);

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("REMITO");
        jLabel6.setToolTipText("");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(270, 10, 150, 10);

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("N°");
        jLabel7.setToolTipText("");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(270, 30, 150, 10);

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Fecha:");
        jLabel8.setToolTipText("");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(270, 40, 150, 14);

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("C.U.I.T.: 20081574902");
        jLabel9.setToolTipText("");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(270, 50, 150, 14);

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Ingr. Brutos:");
        jLabel10.setToolTipText("");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(270, 60, 150, 14);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Fecha Inic. Actividades:");
        jLabel11.setToolTipText("");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(270, 90, 150, 14);

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Establecimiento:");
        jLabel12.setToolTipText("");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(270, 70, 150, 14);

        jLabel13.setBackground(new java.awt.Color(255, 255, 255));
        jLabel13.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Sede Timbrado:");
        jLabel13.setToolTipText("");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(270, 80, 150, 14);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(null);

        jLabel14.setBackground(new java.awt.Color(255, 255, 255));
        jLabel14.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("C.U.I.T.: 20-41368043-4");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(290, 30, 120, 10);

        jLabel16.setBackground(new java.awt.Color(255, 255, 255));
        jLabel16.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Condición de venta: CONTADO");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(260, 40, 150, 10);

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Arial", 1, 15)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Cliente:");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(10, 10, 400, 20);

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Alias:");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(10, 30, 280, 10);

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Localidad:");
        jPanel2.add(jLabel19);
        jLabel19.setBounds(10, 50, 290, 10);

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Domicilio:");
        jPanel2.add(jLabel20);
        jLabel20.setBounds(10, 40, 250, 10);

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Teléfono: 2222222222");
        jPanel2.add(jLabel21);
        jLabel21.setBounds(300, 50, 110, 10);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 110, 420, 70);

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("TOTAL: $");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(10, 575, 400, 15);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Arial", 0, 8)); // NOI18N
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
        jTable1.setEditingColumn(0);
        jTable1.setEditingRow(0);
        jTable1.setEnabled(false);
        jTable1.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(4, 182, 410, 390);

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("República de Siria 364");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(10, 50, 150, 15);

        jButton1.setBackground(new java.awt.Color(0, 153, 0));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Volver a Ventas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        String nremito = FacturacionRemitoModificado.titulo1.getText();
        JOptionPane.showMessageDialog(null,"REMITO N°"+nremito+" modificado con éxito.","ACTUALIZACIÓN EXITOSA",JOptionPane.INFORMATION_MESSAGE);
        Ventas ventas = new Ventas();
        ventas.setLocationRelativeTo(null);
        ventas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        jButton1.requestFocus();
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
                .getLogger(RemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(RemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(RemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(RemitoModificado.class.getName())
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
                    new RemitoModificado().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public int print(Graphics graf, PageFormat pagfor, int index) throws PrinterException {
        if(index>0){
            return NO_SUCH_PAGE;
        }
        Graphics2D hub = (Graphics2D) graf;
        hub.translate(pagfor.getImageableX(), pagfor.getImageableY());
        hub.scale(1.0, 1.0);
        this.jPanel1.printAll(graf);
        return PAGE_EXISTS;
    }
    }

