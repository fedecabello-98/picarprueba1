
package app;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.*;
import java.awt.print.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fedec
 */
public class InformeGenerado extends javax.swing.JFrame implements Printable {
    int diainicia = Informes.jCalendar1.getCalendar().get(Calendar.DAY_OF_MONTH);
    int mesinicia =  Informes.jCalendar1.getCalendar().get(Calendar.MARCH)+1;
    int anioinicia = Informes.jCalendar1.getCalendar().get(Calendar.YEAR);

    int diatermina = Informes.jCalendar2.getCalendar().get(Calendar.DAY_OF_MONTH);
    int mestermina =  Informes.jCalendar2.getCalendar().get(Calendar.MARCH)+1;
    int aniotermina = Informes.jCalendar2.getCalendar().get(Calendar.YEAR);
    
    Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String fechahoy = formatter.format(fecha);
    
        /** Creates new form InformeGenerado */
    public class Ventas {
        public DefaultTableModel ventasGeneradas()
        {
            String [] nombresColumnas = {"N° remito","Total remito"};
            Object [] registros = new Object[2];
            DefaultTableModel modelo = new DefaultTableModel(null,nombresColumnas);
            try{
               Statement st = LlenarCombo.conexion.createStatement();
               ResultSet tventas = st.executeQuery("select remitos.idremitos,sum(cantidad*precio_remito) from picar_db.remitos where idremitos!=0 AND fecharemito BETWEEN '"+anioinicia+"-"+mesinicia+"-"+diainicia+"' AND '"+aniotermina+"-"+mestermina+"-"+diatermina+"' group by idremitos;");
               while(tventas.next()){
                        registros[0] = tventas.getString("idremitos");
                        registros[1] = tventas.getFloat("sum(cantidad*precio_remito)");
                        modelo.addRow(registros);
                    }
                }catch (SQLException e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            return modelo;
         }
         }
    
    public void ventasGeneradas()
    {
        Ventas ventas = new Ventas();
        DefaultTableModel modelo = ventas.ventasGeneradas();
        jTable1.setModel(modelo);
        TableColumnModel columnmodel = jTable1.getColumnModel();
        columnmodel.getColumn(1).setPreferredWidth(200);
        JTableHeader th;
        th = jTable1.getTableHeader();
        Font fuente = new Font("Tahoma", Font.PLAIN, 8);
        th.setFont(fuente);
    }
    public InformeGenerado() {
        initComponents();
        LlenarCombo.conectar();
        String mesString;
        switch (mesinicia) {
                case 1:  mesString = "ENE";
                         break;
                case 2:  mesString  = "FEB";
                         break;
                case 3:  mesString = "MAR";
                         break;
                case 4:  mesString = "ABR";
                         break;
                case 5:  mesString = "MAY";
                         break;
                case 6:  mesString = "JUN";
                         break;
                case 7:  mesString = "JUL";
                         break;
                case 8:  mesString = "AGO";
                         break;
                case 9:  mesString = "SEP";
                         break;
                case 10: mesString = "OCT";
                         break;
                case 11: mesString = "NOV";
                         break;
                case 12: mesString = "DIC";
                         break;
                default: mesString = "NULL";
                         break;
                }

        String mesterminaString;
        switch (mestermina) {
                case 1:  mesterminaString = "ENE";
                         break;
                case 2:  mesterminaString  = "FEB";
                         break;
                case 3:  mesterminaString = "MAR";
                         break;
                case 4:  mesterminaString = "ABR";
                         break;
                case 5:  mesterminaString = "MAY";
                         break;
                case 6:  mesterminaString = "JUN";
                         break;
                case 7:  mesterminaString = "JUL";
                         break;
                case 8:  mesterminaString = "AGO";
                         break;
                case 9:  mesterminaString = "SEP";
                         break;
                case 10: mesterminaString = "OCT";
                         break;
                case 11: mesterminaString = "NOV";
                         break;
                case 12: mesterminaString = "DIC";
                         break;
                default: mesterminaString = "NULL";
                         break;
                }
        
        jLabel1.setText("INFORME DESDE "+diainicia+"-"+mesString+"-"+anioinicia+" HASTA "+diatermina+"-"+mesterminaString+"-"+aniotermina+".");
        
        ventasGeneradas();
        float total = 0;
        for(int i = 0;i< jTable1.getRowCount();i++){
            total += Float.parseFloat(jTable1.getValueAt(i,1).toString());
        }
        jLabel5.setText("TOTAL: $"+total);
        
        PrinterJob gap = PrinterJob.getPrinterJob();
        gap.setJobName("DISTRIBUIDORA PICAR | INFORME DESDE "+diainicia+"-"+mesString+"-"+anioinicia+" HASTA "+diatermina+"-"+mesterminaString+"-"+aniotermina);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Informe generado | "+fechahoy+"");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(595, 842));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.setMinimumSize(new java.awt.Dimension(595, 842));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TÍTULO");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 570, 14);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ventas generadas:");
        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel2);
        jLabel2.setBounds(10, 40, 180, 20);

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
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
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 180, 230);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Compras realizadas:");
        jLabel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel3);
        jLabel3.setBounds(200, 40, 180, 30);

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
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(200, 70, 180, 210);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Comisiones por vendedor:");
        jLabel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel4);
        jLabel4.setBounds(390, 40, 190, 30);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(390, 70, 190, 210);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TOTAL DE VENTAS: $");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel5);
        jLabel5.setBounds(10, 290, 180, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("TOTAL DE COMPRAS: $");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel6);
        jLabel6.setBounds(200, 280, 180, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("TOTAL DE COMISIONES: $");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel7);
        jLabel7.setBounds(390, 280, 190, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );

        pack();
    }//GEN-END:initComponents

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
                .getLogger(InformeGenerado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util
                .logging
                .Logger
                .getLogger(InformeGenerado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util
                .logging
                .Logger
                .getLogger(InformeGenerado.class.getName())
                .log(java.util
                         .logging
                         .Level
                         .SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util
                .logging
                .Logger
                .getLogger(InformeGenerado.class.getName())
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
                    new InformeGenerado().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
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
