
package app;


import java.awt.Image;

import java.io.File;

/*import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.awt.Component;
import java.io.IOException;
import javax.swing.JPanel;*/

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


import jxl.*;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author fedec
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /** Creates new form VentanaPrincipal */

    public static final String URL = "jdbc:mysql://localhost:3306/picar_db";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "1508";

    public VentanaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(this);
        SetImageLabel(jLabel1, "src/imagenes/picarlogo.png");
        //SetImageButton60x60(btnclientes, "src/imagenes/usuarios.png");
        //SetImageButton60x60(btnproductos, "src/imagenes/rodillo.png");
        //SetImageButton60x60(btnventas, "src/imagenes/money-cheque-editar.png");
        jLabel3.setVisible(false);
        try {
            Connection con = null;
            con = getConection();
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connection getConection() {
        Connection con = null;
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            btnclientes.setEnabled(true);
            btnproductos.setEnabled(true);
            btnventas.setEnabled(true);
            btncompras.setEnabled(true);
            btninformes.setEnabled(true);
            jLabel2.setVisible(false);
            btncargar.setEnabled(true);
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents

        jLabel1 = new javax.swing.JLabel();
        btnclientes = new javax.swing.JButton();
        btnproductos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnventas = new javax.swing.JButton();
        btncargar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btncompras = new javax.swing.JButton();
        btninformes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Distribuidora PICAR");
        setMinimumSize(new java.awt.Dimension(625, 561));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        btnclientes.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cliente.png"))); // NOI18N
        btnclientes.setText("CLIENTES");
        btnclientes.setEnabled(false);
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        btnproductos.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/etiqueta.png"))); // NOI18N
        btnproductos.setText("PRODUCTOS");
        btnproductos.setEnabled(false);
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("NO HAY CONEXI�N A LA BASE DE DATOS");

        btnsalir.setBackground(new java.awt.Color(255, 0, 0));
        btnsalir.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnventas.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btnventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/controlar.png"))); // NOI18N
        btnventas.setText("VENTAS");
        btnventas.setEnabled(false);
        btnventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnventasActionPerformed(evt);
            }
        });

        btncargar.setBackground(java.awt.Color.green);
        btncargar.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btncargar.setText("Cargar lista");
        btncargar.setEnabled(false);
        btncargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("Cargando...");

        btncompras.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btncompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/carrito-de-compras.png"))); // NOI18N
        btncompras.setText("COMPRAS");
        btncompras.setEnabled(false);
        btncompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprasActionPerformed(evt);
            }
        });

        btninformes.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        btninformes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lista-de-verificacion.png"))); // NOI18N
        btninformes.setText("INFORMES");
        btninformes.setEnabled(false);
        btninformes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btninformesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btncargar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btncompras, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(197, 197, 197)
                                .addComponent(btninformes, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir)
                    .addComponent(btncargar))
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnproductos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnventas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btncompras, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btninformes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        if(btncargar.isEnabled()){
            btncargar.requestFocus();
        }else{
            btnsalir.requestFocus();
        };
    }//GEN-LAST:event_formWindowActivated
    String codigo;
    String descripcion;
    String lista;
    public class MigrarDatos {
       private void LeerArchivos(String archivoDestino) throws SQLException {
            int contador = 1;
            Connection conectar = getConection();
            String sql0 = "SET SQL_SAFE_UPDATES=0;";
            PreparedStatement sqlcero = conectar.prepareStatement(sql0);
            sqlcero.executeUpdate();
            //String sql1 = "DELETE FROM productos;";
            //PreparedStatement sqluno = conectar.prepareStatement(sql1);
            //sqluno.executeUpdate();
            //String sql2 = "ALTER TABLE prueba1 auto_increment=0;";
            /*PreparedStatement sqldos = conectar.prepareStatement(sql2);
            sqldos.executeUpdate();*/
            String iniciovarchar = "ALTER TABLE productos CHANGE COLUMN precio precio VARCHAR(10) NULL DEFAULT NULL;";
            PreparedStatement pst = conectar.prepareStatement(iniciovarchar);
            pst.executeUpdate();
            btnclientes.setEnabled(false);
            btnproductos.setEnabled(false);
            btnventas.setEnabled(false);
            btncargar.setEnabled(false);
            btncompras.setEnabled(false);
            btninformes.setEnabled(false);
            try {
                Workbook archivoExcel = Workbook.getWorkbook(new File(archivoDestino));
                for (int hojas = 0; hojas < archivoExcel.getNumberOfSheets(); hojas++) {
                    Sheet hoja = archivoExcel.getSheet(hojas);
                    //int numColumnas = hoja.getColumns();
                    int numFilas = hoja.getRows();
                    String dato;
                    for (int fila = 0; fila < numFilas; fila++) {
                        for (int columna = 1; columna <=3; columna++) {
                            dato = hoja.getCell(columna, fila).getContents();
                            System.out.print(dato + " ");
                            switch (contador) {
                            case 1:
                                codigo = dato;
                                contador++;
                                break;
                            case 2:
                                descripcion = dato;
                                contador++;
                                break;
                            case 3:
                                lista = dato;
                                contador = 1;
                                break;
                            }
                        }

                        System.out.println("");
                        String sentencia = "INSERT IGNORE productos (codigo,descripcion,precio) VALUES (UPPER(REPLACE('"+codigo+"',' ','')),TRIM(UPPER('"+descripcion+"')),REPLACE('"+lista+"',' ',''));";
                        //INSERT CONDICIONAL String sentencia = "INSERT INTO prueba1 (codigo,descripcion,precio) SELECT '"+codigo+"','"+descripcion+"','"+lista+"' FROM dual WHERE NOT EXISTS (SELECT * FROM prueba1 WHERE codigo='' or descripcion='' or precio='');";
                        PreparedStatement pst1 = conectar.prepareStatement(sentencia);
                        pst1.executeUpdate();
                        String limpiarvacios = "DELETE FROM productos WHERE codigo='' OR descripcion='' OR precio='' OR precio='LISTA' OR precio='PRECIO';";
                        PreparedStatement pst2 = conectar.prepareStatement(limpiarvacios);
                        pst2.executeUpdate();
                        //String limpiar1 = "UPDATE productos SET codigo=REPLACE(codigo,' ','') WHERE codigo='"+codigo+"';";
                        //PreparedStatement limpiarcodigo = conectar.prepareStatement(limpiar1);
                        // limpiarcodigo.executeUpdate();
                        String updatenuevo = "UPDATE productos SET descripcion=TRIM(UPPER('"+descripcion+"')),precio=REPLACE('"+lista+"',' ','') WHERE codigo=UPPER(REPLACE('"+codigo+"',' ',''));";
                        PreparedStatement pst3 = conectar.prepareStatement(updatenuevo);
                        pst3.executeUpdate();
                    }
                }
                //String convertirdecimales = "UPDATE prueba1 SET precio=CAST(REPLACE(precio,',','.') as DECIMAL(10,3));";
                String eliminarsigno = "UPDATE productos SET precio=REPLACE(precio,'$','');";
                PreparedStatement pst3 = conectar.prepareStatement(eliminarsigno);
                pst3.executeUpdate();
                //String eliminarsigno = "UPDATE prueba1 SET precio=CAST(REPLACE(precio,'$','') as DECIMAL(10,3));";
                String convertirdecimales = "UPDATE productos SET precio=REPLACE(precio,',','.');";
                PreparedStatement pst5 = conectar.prepareStatement(convertirdecimales);
                pst5.executeUpdate();
                //String limpiar1 = "UPDATE productos SET codigo=REPLACE(codigo,' ','');";
                //PreparedStatement limpiarcodigo = conectar.prepareStatement(limpiar1);
                //limpiarcodigo.executeUpdate();
                
                //String limpiar4 = "UPDATE productos SET descripcion=trim(descripcion);";
                //PreparedStatement limpiarcostados = conectar.prepareStatement(limpiar4);
                //limpiarcostados.executeUpdate();
                String limpiar2 = "UPDATE productos SET descripcion=REPLACE(descripcion,'  ',' ');";
                PreparedStatement limpiardescripcion = conectar.prepareStatement(limpiar2);
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                limpiardescripcion.executeUpdate();
                                
                //String limpiar3 = "UPDATE productos SET precio=REPLACE(precio,' ','');";
                //PreparedStatement limpiarprecio = conectar.prepareStatement(limpiar3);
                //limpiarprecio.executeUpdate();
                
                //String convertirmayusculas = "UPDATE productos SET descripcion=UPPER(descripcion);";
                //PreparedStatement conmayus = conectar.prepareStatement(convertirmayusculas);
                //conmayus.executeUpdate();
                String convertircolumna = "ALTER TABLE productos CHANGE COLUMN precio precio DECIMAL(10,2) NULL DEFAULT NULL;";
                PreparedStatement pst4 = conectar.prepareStatement(convertircolumna);
                pst4.executeUpdate();
                getConection().close();
            } catch (Exception ioe) {
                ioe.printStackTrace();
            }
        }
    }
    /*
    public static void main(String[] args){
        MigrarDatos excel = new MigrarDatos();
        excel.LeerArchivos("C:\\JDeveloper\\mywork\\picar\\picar_app\\src\\librerias\\prueba1picar.xls");
    }*/
    /*  public static void crearArchivoExcel() {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Java");
        try {
            FileOutputStream archivo =
                new FileOutputStream(new File("C:\\JDeveloper\\mywork\\picar\\picar_app\\src\\librerias\\1.xlsx"));
            libro.write(archivo);
            archivo.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void leerArchivoExcel() throws FileNotFoundException, IOException {
        FileInputStream archivo =
            new FileInputStream("C:\\JDeveloper\\mywork\\picar\\picar_app\\src\\librerias\\exceloriginal.xlsx");
        XSSFWorkbook libro = new XSSFWorkbook(archivo);
        XSSFSheet hoja = libro.getSheetAt(0);

        int numFilas = hoja.getLastRowNum();
        for (int i = 0; i <= numFilas; i++) {
            Row fila = hoja.getRow(i);
            int numColumnas = fila.getLastCellNum();

            for (int j = 0; j < numColumnas; j++) {
                Cell celda = fila.getCell(j);

                switch (celda.getCellTypeEnum().toString()) {
                case "NUMERIC":
                    System.out.print(celda.getNumericCellValue() + " ");
                    break;
                case "STRING":
                    System.out.print(celda.getStringCellValue() + " ");
                    break;
                case "FORMULA":
                    System.out.print(celda.getCellFormula() + " ");
                    break;
                }
            }
            System.out.println("");
        }

    }

    public static void cargarDatos() throws FileNotFoundException, IOException, SQLException {
        FileInputStream archivo =
            new FileInputStream("C:\\JDeveloper\\mywork\\picar\\picar_app\\src\\librerias\\exceloriginal2.xlsx");
        XSSFWorkbook libro = new XSSFWorkbook(archivo);
        XSSFSheet hoja = libro.getSheetAt(0);

        int numFilas = hoja.getLastRowNum();

        for (int i = 0; i <= numFilas; i++) {
            Row fila = hoja.getRow(i);
            Connection conectar = getConection();
            PreparedStatement insertar = conectar.prepareStatement("insert into prueba1 values (?,?);");
            insertar.setString(1, fila.getCell(0).getStringCellValue());
            insertar.setString(2, fila.getCell(1).getStringCellValue());
            insertar.executeUpdate();
        }
        getConection().close();
    }*/

    private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        /*
        //crearArchivoExcel();
        //leerArchivoExcel();
        //cargarDatos();
        try {
            //leerArchivoExcel();
            //cargarDatos();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
*/      
        Clientes cliente = new Clientes();
        cliente.setLocationRelativeTo(null);
        cliente.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnclientesActionPerformed

    private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
        /*
        try {
            //leerArchivoExcel();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }*/
        Productos productos = new Productos();
        productos.setLocationRelativeTo(null);
        productos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnproductosActionPerformed

    private void btnventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnventasActionPerformed
        // TODO add your handling code here:
        Ventas ventas = new Ventas();
        ventas.setLocationRelativeTo(null);
        ventas.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnventasActionPerformed
    //private Cargar cargando = new Cargar(this, true);
    private void btncargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarActionPerformed
        // TODO add your handling code here:
        /*
        cargando.setDefaultCloseOperation(cargando.HIDE_ON_CLOSE);
        cargando.setModal(true);
        cargando.setLocationRelativeTo(null);
        cargando.setVisible(true);
        */
        new Thread(){
            @Override
            public void run(){
                btnclientes.setEnabled(false);
                btnproductos.setEnabled(false);
                btnventas.setEnabled(false);
                btncompras.setEnabled(false);
                btninformes.setEnabled(false);
                btncargar.setEnabled(false);
                jLabel3.setVisible(true);
                try {
                    MigrarDatos excel = new MigrarDatos();
                    excel.LeerArchivos("C:\\JDeveloper\\mywork\\picar\\picar_app\\src\\librerias\\picar unificada.xls");
                    JOptionPane.showMessageDialog(null,"El archivo ha sido correctamente cargado a la base de datos.","CARGA EXITOSA",JOptionPane.INFORMATION_MESSAGE);
                    jLabel3.setVisible(false);
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                    JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                    jLabel3.setVisible(false);
                }
            }
        }.start();
    }//GEN-LAST:event_btncargarActionPerformed

    private void btncomprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprasActionPerformed
        // TODO add your handling code here:
        Compras compras = new Compras();
        compras.setLocationRelativeTo(null);
        compras.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btncomprasActionPerformed

    private void btninformesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btninformesActionPerformed
        // TODO add your handling code here:
        Informes informes = new Informes();
        informes.setLocationRelativeTo(null);
        informes.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btninformesActionPerformed

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
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(VentanaPrincipal.class.getName())
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
                    new VentanaPrincipal().setVisible(true);
                }
            });
    }

    private void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon =
            new ImageIcon(image.getImage()
                          .getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH));
        labelName.setIcon(icon);
        this.repaint();
    }

    private void SetImageButton60x60(JButton buttonName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));
        buttonName.setIcon(icon);
        this.repaint();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btncargar;
    public static javax.swing.JButton btnclientes;
    public static javax.swing.JButton btncompras;
    public static javax.swing.JButton btninformes;
    public static javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    public static javax.swing.JButton btnventas;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

}
