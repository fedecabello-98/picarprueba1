
package app;

import java.awt.Color;
import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import javax.swing.table.TableModel;

/**
 *
 * @author fedec
 */
public class CompraBuscada extends javax.swing.JFrame {
DefaultTableModel modelo;
    /** Creates new form Compras */
    public CompraBuscada() {
        initComponents();
        jTextField1.requestFocus();
        LlenarCombo.conectar();
        mostrarProductos();
        jComboBox1.removeAllItems();
        ArrayList<String> traerproveedor = new ArrayList<String>();
        traerproveedor = LlenarCombo.llenar_combo8();
        for(int i = 0; i<traerproveedor.size();i++){
        jComboBox1.addItem(traerproveedor.get(i));
        }
        String formateocompra = BuscarCompra.jTextField1.getText();
        jLabel9.setText(formateocompra);
        try{
            Statement st = LlenarCombo.conexion.createStatement();
            ResultSet comprainicial = st.executeQuery("select * from picar_db.compras where compras.idcompras="+formateocompra+" group by compras.idcompras;");
            if(comprainicial.next()){
                int formateocombo = comprainicial.getInt("proveedor_id");
                jComboBox1.setSelectedIndex(formateocombo-1);
            }
        }
        catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        modelo = new DefaultTableModel();
        modelo.addColumn("C�digo");
        modelo.addColumn("Descripci�n");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio Unitario");
        modelo.addColumn("Total");
        modelo.addColumn("Proveedor");
        this.jTable2.setModel(modelo);
        
        traerCompraGenerada();
        
        float total = 0;
        for(int i = 0;i< jTable2.getRowCount();i++){
            total += Float.parseFloat(jTable2.getValueAt(i,4).toString());
        }
        jLabel7.setText("TOTAL DE LA COMPRA: $"+total);
        
        TableColumnModel columnmodel = jTable2.getColumnModel();
        columnmodel.getColumn(5).setMaxWidth(0);
        columnmodel.getColumn(5).setMinWidth(0);
        columnmodel.getColumn(5).setPreferredWidth(0);
        columnmodel.getColumn(1).setPreferredWidth(250);
    }
    public class Logica {
        public DefaultTableModel mostrarProductos()
        {
            String [] nombresColumnas = {"C�digo","Descripci�n"};
            Object [] registros = new Object [2];
            //DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
            DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas){
                @Override
                public Class getColumnClass(int columna){
                if(columna==3 || columna==4){
                    return Integer.class;
                }else{
                    return String.class;
                    }}
            };
            String sql = "SELECT productos.codigo,productos.descripcion FROM picar_db.productos where productos.codigo!='0' ORDER BY productos.descripcion ASC;";
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
                    registros[0] = rs.getString("codigo");
                    registros[1] = rs.getString("descripcion");
                    //registros[3] = Integer.parseInt(rs.getString("stock").toString());
                    //registros[3] = rs.getString("stock");
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
    
    public void mostrarProductos()
    {
        Logica logica = new Logica();
        DefaultTableModel modelo = logica.mostrarProductos();
        jTable1.setModel(modelo);
        TableColumnModel columnmodel = jTable1.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(300);
    } 
    
    public class Teoria {
        public DefaultTableModel traerCompraGenerada()
        {
            String [] nombresColumnas = {"C�digo","Descripci�n","Cantidad","Precio Unitario","Total","Proveedor"};
            Object [] registros = new Object[6];
            DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
            String ncompra = BuscarCompra.jTextField1.getText();
            String sql = "SELECT compras.producto_id,productos.descripcion,compras.cantidad_compra,compras.precio_compra,compras.cantidad_compra*compras.precio_compra,compras.proveedor_id FROM picar_db.compras JOIN picar_db.productos ON productos.codigo=compras.producto_id WHERE compras.idcompras="+ncompra+";";
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
                    registros[0] = rs.getString("producto_id");
                    registros[1] = rs.getString("descripcion");
                    registros[2] = rs.getFloat("cantidad_compra");
                    registros[3] = rs.getFloat("precio_compra");
                    registros[4] = rs.getFloat("compras.cantidad_compra*compras.precio_compra");
                    registros[5] = rs.getString("proveedor_id");
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
    
    public void traerCompraGenerada()
    {
        Teoria teoria = new Teoria();
        DefaultTableModel modelo = teoria.traerCompraGenerada();
        jTable2.setModel(modelo);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compras");

        jLabel1.setBackground(new java.awt.Color(204, 255, 51));
        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 114, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("COMPRA N�");
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

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel2.setText("C�digo:");

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

        jTextField2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
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

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel3.setText("Cantidad:");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel4.setText("Precio:");

        jTextField3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTextField3.setEnabled(false);
        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 22)); // NOI18N
        jLabel5.setText("Proveedor:");

        jComboBox1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PROVEEDOR" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 0));
        jButton3.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Actualizar compra");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setText("Filtrar producto (por DESCRIPCI�N):");

        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jButton4.setBackground(java.awt.Color.green);
        jButton4.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/agregar.png"))); // NOI18N
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(java.awt.Color.red);
        jButton5.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/borrar.png"))); // NOI18N
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable2.setEditingColumn(0);
        jTable2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTable2FocusLost(evt);
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable2MousePressed(evt);
            }
        });
        jTable2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jTable2InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jTable2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTable2PropertyChange(evt);
            }
        });
        jTable2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTable2KeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel7.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("TOTAL DE LA COMPRA: $");

        jLabel8.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("-");

        jLabel9.setBackground(new java.awt.Color(204, 255, 51));
        jLabel9.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 114, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("-");
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jMenu1.setText("Proveedores");

        jMenuItem1.setText("Gestionar proveedores");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Compras generadas");

        jMenuItem2.setText("Buscar");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(11, 11, 11)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(5, 5, 5)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton1))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap())
        );

        pack();
    }//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jTextField1.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTable2.clearSelection();
        jTable1.setRowSorter(null);
        jTable1.removeEditor();
        jTextField1.setForeground(Color.black);
        jLabel8.setText("-");
        jLabel8.setForeground(Color.black);
        jButton5.setEnabled(false);
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        VentanaPrincipal inicio = new VentanaPrincipal();
        //inicio.setDefaultCloseOperation(inicio.HIDE_ON_CLOSE);
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
        // TODO add your handling code here:
            String idproducto = this.jTextField1.getText();
            bproducto.buscarProducto(idproducto.replace(" ", "").trim());
    }//GEN-LAST:event_jTextField1FocusLost
    public class BuscarProductoC{
        public BuscarProductoC(){
        }
        public String buscarProducto(String idproducto){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registroproducto = st.executeQuery("SELECT * FROM picar_db.productos WHERE productos.codigo='"+idproducto+"';");
                if(registroproducto.next()){
                String traerdescripcion = registroproducto.getString("descripcion");
                jTextField1.setForeground(new Color(255,114,0));
                jLabel8.setForeground(new Color(255,114,0));
                jLabel8.setText(traerdescripcion);
                }else{
                    jTextField1.setForeground(Color.red);
                    jLabel8.setForeground(Color.red);
                    jLabel8.setText("PRODUCTO NO ENCONTRADO");
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jTextField3.setText("");
                    jTextField3.setEnabled(false);
                    //jTextField1.requestFocus();
                    jButton4.setEnabled(false);
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
        if(jLabel8.getForeground()==Color.red){
            jTextField1.requestFocus();
        }else{
            jTextField2.setEnabled(true);
            jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
        // TODO add your handling code here:

        if(jTextField2.getText().length()==0 || jTextField3.getText().length()==0){
            jButton4.setEnabled(false);
        }else{
            jButton4.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        if(jTextField2.getText().length()==0){
            jTextField2.requestFocus();
            jButton4.setEnabled(false);
        }else{
        jTextField3.setEnabled(true);
        jTextField3.requestFocus();
            }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
        // TODO add your handling code here:
        if(jTextField2.getText().length()==0 || jTextField3.getText().length()==0){
            jButton4.setEnabled(false);
        }else{
            jButton4.setEnabled(true);
        }
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
        jButton4.setEnabled(true);
        jButton4.requestFocus();
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int validareliminacion = jTextField1.getText().length();
        if(validareliminacion==0){
        jTextField1.setForeground(Color.black);
        jLabel8.setForeground(Color.black);
        jLabel8.setText("-");
        jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
    public class GenerarCompra{
        public GenerarCompra(){
        }
        public String generarCompra(String ncompra){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                for(int i = 0;i< jTable2.getRowCount();i++){
                    try{
                        st.executeUpdate("INSERT INTO picar_db.compras VALUES ("+ncompra+",'"+jTable2.getValueAt(i,0)+"',"+jTable2.getValueAt(i,2)+","+jTable2.getValueAt(i, 3)+","+jTable2.getValueAt(i,5)+",current_date());");
                        st.executeUpdate("UPDATE picar_db.productos SET stock = stock+"+jTable2.getValueAt(i,2)+" WHERE codigo='"+jTable2.getValueAt(i, 0)+"';");
                    }catch(SQLException ex){
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null, "ERROR: "+ex, "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
                JOptionPane.showMessageDialog(null,"COMPRA N�"+ncompra+" fue actualizada con �xito en el sistema.","ACTUALIZACI�N EXITOSA",JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return ncompra;
        }
    }
    private transient GenerarCompra generarcompra = new GenerarCompra();
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /*String clienteafacturar = this.jTextField1.getText();
        String tipofactura = this.jComboBox1.getSelectedItem().toString().toUpperCase();
        bcliente.buscarCliente(clienteafacturar.replace(" ", "").trim(),tipofactura);*/
        String ncompra = this.jLabel9.getText();
        try{
            Statement st = LlenarCombo.conexion.createStatement();
            ResultSet count = st.executeQuery("SELECT COUNT(*) from picar_db.compras WHERE idcompras="+ncompra+";");
            if(count.next()){
                int cantidadregistros = count.getInt("COUNT(*)");
                for(int i=0;i<=cantidadregistros;i++){
                    int contador = cantidadregistros+1;
                    ResultSet registrocompra = st.executeQuery("SELECT compras.producto_id,compras.cantidad_compra FROM picar_db.compras WHERE compras.idcompras="+ncompra+" LIMIT "+i+","+contador+";");
                    if(registrocompra.next()){
                    String traerid = registrocompra.getString("producto_id");
                    String traercant = registrocompra.getString("cantidad_compra");
                    st.executeUpdate("UPDATE picar_db.productos SET stock = stock-"+traercant+" WHERE productos.codigo='"+traerid+"';");
                    }
                }
            st.executeUpdate("DELETE FROM picar_db.compras WHERE idcompras="+ncompra+";");
            }
        }catch(SQLException ex){
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, "ERROR: "+ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        generarcompra.generarCompra(ncompra);
        this.dispose();
        Compras compras = new Compras();
        compras.setLocationRelativeTo(null);
        compras.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed
    private TableRowSorter TRSFiltro;
    
    public void Filtro(){
        int ColumnaTabla = 1;
        TRSFiltro.setRowFilter(RowFilter.regexFilter(jTextField4.getText().toUpperCase().trim(),ColumnaTabla));
        }
    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        jTextField4.addKeyListener(new KeyAdapter(){
            public void keyReleased (final KeyEvent e){
                String cadena = (jTextField4.getText());
                jTextField4.setText(cadena);
                Filtro();
            }
        });
        TRSFiltro = new TableRowSorter(jTable1.getModel());
        jTable1.setRowSorter(TRSFiltro);
    }//GEN-LAST:event_jTextField4KeyTyped
    public class LlenarTabla{
        public LlenarTabla(){
        }
        public String llenarTabla(String idproducto, String cantidad, String preciocompra, String descripcioncompra, int idproveedor){
            try{
                Statement st = LlenarCombo.conexion.createStatement();
                ResultSet registroproducto = st.executeQuery("SELECT * FROM picar_db.productos WHERE productos.codigo='"+idproducto+"';");
                DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
                float total = 0;
                if(registroproducto.next()){
                    //DefaultTableModel model = new DefaultTableModel(); 
                    //JTable jTable2 = new JTable(model); 
                    //DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                    Object segunda[]=new Object[6];
                    segunda[0]=idproducto;
                    segunda[1]=descripcioncompra;
                    float convertircantidad = Float.parseFloat(cantidad);
                    segunda[2]=convertircantidad;
                    float traerprecio = Float.parseFloat(preciocompra);
                    segunda[3]=traerprecio;
                    float multiplica=convertircantidad*traerprecio;
                    segunda[4]=multiplica;
                    segunda[5]=idproveedor;
                    modelo.addRow(segunda);
                    for(int i = 0;i< jTable2.getRowCount();i++){
                        total += Float.parseFloat(jTable2.getValueAt(i,4).toString());
                    }
                    jLabel7.setText("TOTAL DE LA COMPRA: $"+total);
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.black);
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jTextField3.setText("");
                    jTextField3.setEnabled(false);
                    jButton4.setEnabled(false);
                    jLabel8.setForeground(Color.black);
                    jLabel8.setText("-");
                    jTable2.clearSelection();
                    jTextField1.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null, "ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
                    jTextField1.requestFocus();
                    jTextField2.setText("");
                    jTextField2.setEnabled(false);
                    jTextField3.setText("");
                    jTextField3.setEnabled(false);
                    jButton4.setEnabled(false);
                }
            } catch (SQLException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return idproducto;
        }
    }
    private transient LlenarTabla lltabla = new LlenarTabla();
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        String idproducto = this.jTextField1.getText();
        String cantidad = this.jTextField2.getText();
        String preciocompra = this.jTextField3.getText();
        String descripcioncompra = this.jLabel8.getText();
        int idproveedor = this.jComboBox1.getSelectedIndex()+1;
        lltabla.llenarTabla(idproducto.replace(" ", "").trim(),cantidad,preciocompra,descripcioncompra.trim(),idproveedor);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        //DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int[] filaeliminar = jTable2.getSelectedRows();
        /*if(filaeliminar>=0){
            modelo.removeRow(filaeliminar);
            jButton6.setEnabled(false);
            jTextField1.requestFocus();
        }else{
            JOptionPane.showMessageDialog(null, "ERROR: No se ha seleccionado ning�n registro en la tabla.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }*/
        //TableModel modeloeliminar = jTable1.getModel();
        while (filaeliminar.length>0){
            DefaultTableModel modelo = (DefaultTableModel) jTable2.getModel();
            ((DefaultTableModel) modelo).removeRow(jTable2.convertRowIndexToModel(filaeliminar[0]));
            filaeliminar = jTable2.getSelectedRows();
        }
        float total = 0;
        for(int i = 0;i< jTable2.getRowCount();i++){
            total += Float.parseFloat(jTable2.getValueAt(i,4).toString());
        }
        jLabel7.setText("TOTAL DE LA COMPRA: $"+total);
        jButton5.setEnabled(false);
        jTable2.clearSelection();
        int validareliminacion = jTextField1.getText().length();
        if(validareliminacion==0){
        jTextField1.setForeground(Color.black);
        jLabel8.setForeground(Color.black);
        jLabel8.setText("-");
        jTextField1.requestFocus();
        }else{
        jTextField1.requestFocus();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        // TODO add your handling code here:
        int validartablavacia = jTable2.getRowCount();
        if(validartablavacia!=0){
            jButton3.setEnabled(true);
            jComboBox1.setEnabled(false);
            //jButton6.setEnabled(true);
        }else{
            jButton3.setEnabled(false);
            jComboBox1.setEnabled(true);
            //jButton6.setEnabled(false);
        }
    }//GEN-LAST:event_jTextField1FocusGained

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == '.') || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == '.') || (karakter == KeyEvent.VK_DELETE)))){
            getToolkit().beep();
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        AltaProveedor nuevoproveedor = new AltaProveedor(this,true);
        this.dispose();
        nuevoproveedor.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        nuevoproveedor.setLocationRelativeTo(null);
        nuevoproveedor.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jTable2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MousePressed
        // TODO add your handling code here:
        jButton5.setEnabled(true);
    }//GEN-LAST:event_jTable2MousePressed

    private void jTable2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTable2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2PropertyChange

    private void jTable2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jTable2InputMethodTextChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2InputMethodTextChanged

    private void jTable2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable2KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable2KeyTyped

    private void jTable2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusLost
        // TODO add your handling code here:
        float totalizado = 0;
        for(int i = 0;i<jTable2.getRowCount();i++){
            float cantt = Float.parseFloat(jTable2.getValueAt(i,2).toString());
            float punitario = Float.parseFloat(jTable2.getValueAt(i,3).toString());
            float multip = cantt*punitario;
            jTable2.setValueAt(multip,i,4);
            totalizado += Float.parseFloat(jTable2.getValueAt(i,4).toString());
        }
        jLabel7.setText("TOTAL DE LA COMPRA: $"+totalizado);
    }//GEN-LAST:event_jTable2FocusLost

    private void jTable2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable2FocusGained
        // TODO add your handling code here:
        float totalizado = 0;
        for(int i = 0;i<jTable2.getRowCount();i++){
            float cantt = Float.parseFloat(jTable2.getValueAt(i,2).toString());
            float punitario = Float.parseFloat(jTable2.getValueAt(i,3).toString());
            float multip = cantt*punitario;
            jTable2.setValueAt(multip,i,4);
            totalizado += Float.parseFloat(jTable2.getValueAt(i,4).toString());
        }
        jLabel7.setText("TOTAL DE LA COMPRA: $"+totalizado);
    }//GEN-LAST:event_jTable2FocusGained

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        BuscarCompra scompra = new BuscarCompra(this,true);
        this.dispose();
        scompra.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        scompra.setLocationRelativeTo(null);
        scompra.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
                .getLogger(CompraBuscada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(CompraBuscada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(CompraBuscada.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(CompraBuscada.class.getName())
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
                    new CompraBuscada().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
