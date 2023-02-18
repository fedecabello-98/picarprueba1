
package app;

import java.awt.*;
import java.awt.event.KeyEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author fedec
 */
public class FacturacionRemitoModificado extends javax.swing.JFrame {
DefaultTableModel modelo;
    public class Logica {
        public DefaultTableModel mostrarRemitos()
        {
            String [] nombresColumnas = {"CÓDIGO","DESCRIPCIÓN","CANTIDAD","PRECIO UNITARIO","TOTAL"};
            Object [] registros = new Object[5];
            DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
            String nremito = BuscarRemito.jTextField1.getText();
            String sql = "select remitos.codigo_id,remitos.descripcion_remito,remitos.cantidad,remitos.precio_remito,remitos.cantidad*remitos.precio_remito from picar_db.remitos where remitos.idremitos="+nremito+";";
            Connection cn = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            try
            {
                cn = LlenarCombo.conexion;
                pst = cn.prepareStatement(sql);                        
                rs = pst.executeQuery();
                while(rs.next())
                {
                    registros[0] = rs.getString("codigo_id");
                    registros[1] = rs.getString("descripcion_remito");
                    registros[2] = rs.getString("cantidad");
                    registros[3] = rs.getString("precio_remito");
                    registros[4] = rs.getFloat("remitos.cantidad*remitos.precio_remito");
                    modelo.addRow(registros);
                }
            }
            catch(SQLException e)
            {
                System.out.println("ERROR: "+e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
             return modelo;
        }
    }
    
    public void mostrarRemitos()
    {
        Logica logica = new Logica();
        DefaultTableModel modelo = logica.mostrarRemitos();
        jTable1.setModel(modelo);
    }
    /** Creates new form FacturacionRemito */
    public FacturacionRemitoModificado() {
        initComponents();
        LlenarCombo.conectar();
        jComboBox1.removeAllItems();
        ArrayList<String> traervendedor = new ArrayList<String>();
        traervendedor = LlenarCombo.llenar_combo2();
        for(int i = 0; i<traervendedor.size();i++){
        jComboBox1.addItem(traervendedor.get(i));
        }
        String formateoremito = BuscarRemito.jTextField1.getText();
        titulo1.setText(formateoremito);
        try{
        Statement st = LlenarCombo.conexion.createStatement();
        ResultSet remitoinicial = st.executeQuery("select remitos.cliente_id,clientes.apellido,clientes.nombre,clientes.alias,clientes.localidad_id,localidades.localidad,clientes.provincia_id,provincias.provincia,remitos.vendedor_id from picar_db.remitos JOIN picar_db.clientes ON remitos.cliente_id=clientes.idcliente JOIN picar_db.localidades ON clientes.localidad_id=localidades.idlocalidades JOIN picar_db.provincias ON clientes.provincia_id=provincias.idprovincias where idremitos="+formateoremito+" group by idremitos;");
            if(remitoinicial.next()){
                String sqlclienteid = remitoinicial.getString("cliente_id");
                String sqlnombre = remitoinicial.getString("nombre");
                String sqlapellido = remitoinicial.getString("apellido");
                String sqlalias = remitoinicial.getString("alias");
                String sqllocalidadid = remitoinicial.getString("localidad_id");
                String sqllocalidad = remitoinicial.getString("localidad");
                String sqlprovinciaid = remitoinicial.getString("provincia_id");
                String sqlprovincia = remitoinicial.getString("provincia");
                int vendedorid = remitoinicial.getInt("vendedor_id");
                this.jLabel2.setText(sqlclienteid+" -");
                this.jLabel3.setText(sqlapellido);
                this.jLabel4.setText(sqlnombre);
                this.jLabel6.setText(sqlalias);
                this.jLabel8.setText(sqllocalidadid+"-"+sqllocalidad+". "+sqlprovinciaid+"-"+sqlprovincia);
                this.jComboBox1.setSelectedIndex(vendedorid-1);
            }
        } catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        mostrarRemitos();

        TableColumnModel columnmodel = jTable1.getColumnModel();
        columnmodel.getColumn(0).setPreferredWidth(100);
        columnmodel.getColumn(1).setPreferredWidth(800);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        titulo1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventas | Remito generado");
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 255));
        jButton2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton2.setText("Limpiar campos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        titulo.setBackground(new java.awt.Color(204, 255, 51));
        titulo.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        titulo.setForeground(new java.awt.Color(0, 153, 0));
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("REMITO N°");
        titulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton3.setBackground(java.awt.Color.yellow);
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/imprimir.png"))); // NOI18N
        jButton3.setText("Imprimir");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel1.setText("Cliente:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("N° -");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel3.setText("APELLIDO");

        titulo1.setBackground(new java.awt.Color(204, 255, 51));
        titulo1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        titulo1.setForeground(new java.awt.Color(0, 153, 0));
        titulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo1.setText("-");
        titulo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel4.setText("NOMBRE");

        jLabel5.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel5.setText("Alias:");

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel6.setText("-");

        jLabel7.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel7.setText("Localidad:");

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel8.setText("NO DISPONIBLE");

        jLabel9.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel9.setText("Código de producto:");

        jTextField1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel10.setForeground(java.awt.Color.red);
        jLabel10.setText("NO ENCONTRADO");

        jLabel11.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel11.setText("Cantidad:");

        jTextField2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(0, 153, 0));
        jTextField2.setEnabled(false);
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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setEditingColumn(0);
        jTable1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTable1HierarchyChanged(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable1FocusLost(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable1PropertyChange(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable1KeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setBackground(new java.awt.Color(0, 153, 255));
        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Productos");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(java.awt.Color.green);
        jButton5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(java.awt.Color.red);
        jButton6.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar.png"))); // NOI18N
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Calibri", 3, 18)); // NOI18N
        jLabel12.setText("TOTAL: $0.0");

        jLabel13.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        jLabel13.setText("Vendedor:");

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VENDEDOR" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jComboBox1MousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(titulo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addGap(0, 482, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo)
                        .addComponent(titulo1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(jLabel11)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Ventas ventas = new Ventas();
        this.dispose();
        ventas.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        ventas.setLocationRelativeTo(null);
        ventas.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        /*jTextField1.setText("");
        jTable1.setRowSorter(null);
        jTable1.removeEditor();
        jTextField1.requestFocus();
        mostrarClientes();
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.LEFT);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);*/
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField2.setEnabled(false);
        jTable1.clearSelection();
        jLabel10.setText("NO ENCONTRADO");
        jLabel10.setForeground(Color.red);
        jTextField1.setForeground(Color.black);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String nremito = titulo1.getText();
        try{
            Statement st = LlenarCombo.conexion.createStatement();
            ResultSet count = st.executeQuery("SELECT COUNT(*) from picar_db.remitos WHERE idremitos="+nremito+";");
            if(count.next()){
                int cantidadregistros = count.getInt("COUNT(*)");
                for(int i=0;i<=cantidadregistros;i++){
                    int contador = cantidadregistros+1;
                    ResultSet registroremito = st.executeQuery("SELECT remitos.codigo_id,remitos.cantidad FROM picar_db.remitos WHERE remitos.idremitos="+nremito+" LIMIT "+i+","+contador+";");
                    if(registroremito.next()){
                    String traerid = registroremito.getString("codigo_id");
                    String traercant = registroremito.getString("cantidad");
                    st.executeUpdate("UPDATE picar_db.productos SET stock = stock+"+traercant+" WHERE productos.codigo='"+traerid+"';");
                    }
                }
            st.executeUpdate("DELETE FROM picar_db.remitos WHERE idremitos="+nremito+";");
            }
        }catch(SQLException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "ERROR: "+ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        RemitoModificado editarremito = new RemitoModificado();
        this.dispose();
        editarremito.setLocationRelativeTo(null);
        editarremito.setDefaultCloseOperation(EXIT_ON_CLOSE);
        editarremito.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        this.setEnabled(true);
        jTextField1.requestFocus();
    }//GEN-LAST:event_formWindowActivated
    public class BuscarProductoC{
        public BuscarProductoC(){
        }
        public String buscarProducto(String idproducto){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registroproducto = st.executeQuery("SELECT * FROM picar_db.productos WHERE productos.codigo='"+idproducto+"';");
                if(registroproducto.next()){
                String traerdescripcion = registroproducto.getString("descripcion");
                jTextField1.setForeground(new Color(0,153,0));
                jLabel10.setForeground(new Color(0,153,0));
                jLabel10.setText(traerdescripcion);
                jTextField2.setEnabled(true);
                }else{
                    jTextField1.setForeground(Color.red);
                    jLabel10.setForeground(Color.red);
                    jLabel10.setText("NO ENCONTRADO");
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jTextField1.requestFocus();
                    jButton5.setEnabled(false);
                }
            } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return idproducto;
        }
    }
    private transient BuscarProductoC bproducto = new BuscarProductoC();
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String idproducto = this.jTextField1.getText();
        bproducto.buscarProducto(idproducto.replace(" ", "").trim());
        jTextField2.requestFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
        if(jLabel10.getForeground()==Color.red){
            jComboBox1.requestFocus();
        }else{
            String idproducto = this.jTextField1.getText();
            bproducto.buscarProducto(idproducto.replace(" ", "").trim());
        }
    }//GEN-LAST:event_jTextField1FocusLost

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.setEnabled(false);
        Productos productos = new Productos();
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);
        productos.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        productos.jButton4.setVisible(false);
        productos.jButton3.setVisible(false);
        productos.jTextField3.setEditable(false);
        //this.setFocusable(false);
        //productos.jTextField1.requestFocus();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusGained

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_formFocusLost
    public class LlenarTabla{
        public LlenarTabla(){
        }
        public String llenarTabla(String idproducto){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registroproducto = st.executeQuery("SELECT * FROM picar_db.productos WHERE productos.codigo='"+idproducto+"';");
                float total = 0;
                if(registroproducto.next()){
                float traerprecio = registroproducto.getFloat("precio");
                DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
                    Object info[]=new Object[5];
                    info[0]=idproducto;
                    info[1]=jLabel10.getText();
                    float convertircantidad = Float.parseFloat(jTextField2.getText());
                    info[2]=convertircantidad;
                    info[3]=traerprecio;
                    float multiplica=convertircantidad*traerprecio;
                    info[4]=multiplica;
                    modelo.addRow(info);
                    for(int i = 0;i< jTable1.getRowCount();i++){
                        total += Float.parseFloat(jTable1.getValueAt(i,4).toString());
                    }
                    jLabel12.setText("TOTAL: $"+total);
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.black);
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jButton5.setEnabled(false);
                    jLabel10.setForeground(Color.red);
                    jLabel10.setText("NO ENCONTRADO");
                    jTable1.clearSelection();
                    jButton6.setEnabled(false);
                    jTextField1.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    jTextField1.requestFocus();
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jButton5.setEnabled(false);
                }
            } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return idproducto;
        }
    }
    private transient LlenarTabla lltabla = new LlenarTabla();
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
                String idproducto = this.jTextField1.getText();
                lltabla.llenarTabla(idproducto.replace(" ", "").trim());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int[] filaeliminar = jTable1.getSelectedRows();
        /*if(filaeliminar>=0){
            modelo.removeRow(filaeliminar);
            jButton6.setEnabled(false);
            jTextField1.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado ningún registro en la tabla.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }*/
        //TableModel modeloeliminar = jTable1.getModel();
        while (filaeliminar.length>0){

            DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
            ((DefaultTableModel) modelo).removeRow(jTable1.convertRowIndexToModel(filaeliminar[0]));
            filaeliminar = jTable1.getSelectedRows();
        }
        float total = 0;
        for(int i = 0;i< jTable1.getRowCount();i++){
            total += Float.parseFloat(jTable1.getValueAt(i,4).toString());
        }
        jButton6.setEnabled(false);
        jLabel12.setText("TOTAL: $"+total);
        jTextField1.setForeground(Color.black);
        jTextField1.requestFocus();
        jTable1.clearSelection();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        if(jTextField2.getText().length()==0){
            jButton5.setEnabled(false);
        }else{
        jButton5.setEnabled(true);
        jButton5.requestFocus();
            }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
        // TODO add your handling code here:
        int validartablavacia = jTable1.getRowCount();
        if(validartablavacia!=0){
            jButton3.setEnabled(true);
            //jButton6.setEnabled(true);
        }else{
            jButton3.setEnabled(false);
            //jButton6.setEnabled(false);
        }
    }//GEN-LAST:event_jTable1FocusGained

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:
        if(jTextField2.getText().length()==0){
            jButton5.setEnabled(false);
        }else{
            jButton5.setEnabled(true);
            }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        int validartablavacia = jTable1.getRowCount();
        if(validartablavacia!=0){
            jButton3.setEnabled(true);
            //jButton6.setEnabled(true);
        }else{
            jButton3.setEnabled(false);
            //jButton6.setEnabled(false);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:

        jButton6.setEnabled(true);
    }//GEN-LAST:event_jTable1MousePressed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
        // TODO add your handling code here:
        jComboBox1.requestFocus();
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MousePressed
        // TODO add your handling code here:
        jComboBox1.requestFocus();
    }//GEN-LAST:event_jComboBox1MousePressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        jTextField1.requestFocus();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTable1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1FocusLost

    private void jTable1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTable1HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1HierarchyChanged

    private void jTable1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyTyped

    private void jTable1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable1PropertyChange
        // TODO add your handling code here:
        float total = 0;
        for(int i = 0;i< jTable1.getRowCount();i++){
            float cant = Float.parseFloat(jTable1.getValueAt(i,2).toString());
            float punitario = Float.parseFloat(jTable1.getValueAt(i,3).toString());
            float multipla = cant*punitario;
            jTable1.setValueAt(multipla,i,4);
            total += Float.parseFloat(jTable1.getValueAt(i,4).toString());
        }
        jLabel12.setText("TOTAL: $"+total);
    }//GEN-LAST:event_jTable1PropertyChange

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
                .getLogger(FacturacionRemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(FacturacionRemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(FacturacionRemitoModificado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(FacturacionRemitoModificado.class.getName())
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
                    new FacturacionRemitoModificado().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    public static javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel titulo;
    public static javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables

}
