package app;

import java.awt.Component;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LlenarCombo {
       static Connection conexion=null;
       static Statement sentencia;
       static ResultSet resultado;
       public static void conectar(){
           //String ruta="jdbc:mysql://";
           //String user="root";
           //String pass="";
           String URLSQLSERVER = "jdbc:sqlserver://FEDERYZEN3\\SQLEXPRESS:49500;"
                        + "database=picar_db;"
                        + "user=usuarionuevo;"
                        + "password=losredondos123;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=15;";
           try {
               //Class.forName("com.mysql.cj.jdbc.Driver");
               conexion=DriverManager.getConnection(URLSQLSERVER);
               sentencia= conexion.createStatement();
           } catch (Exception e) {
               System.out.println("ERROR: "+e);
               JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
       }
       
       public static ArrayList<String> llenar_combo1(){
           ArrayList<String> traercategoria = new ArrayList<String>();
           try {
               resultado = sentencia.executeQuery("SELECT * FROM picar_db.categorias;");
           } catch (Exception e) {
               System.out.println("ERROR: "+e);
               JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           try {
               while(resultado.next()){
                   traercategoria.add(resultado.getString("categoria"));
               }
           } catch (Exception e) {
               System.out.println("ERROR: "+e);
               JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
               System.exit(0);
           }
           return traercategoria;
       }
    public static ArrayList<String> llenar_combo2(){
        ArrayList<String> traervendedor = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.vendedores;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traervendedor.add(resultado.getString("vendedor"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traervendedor;
    }
    public static ArrayList<String> llenar_combo3(){
        ArrayList<String> traerzona = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.zonas;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerzona.add(resultado.getString("zona"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerzona;
    }
    public static ArrayList<String> llenar_combo5(){
        ArrayList<String> traerprovincia = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.provincias;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerprovincia.add(resultado.getString("provincia"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerprovincia;
    }
    public static ArrayList<String> llenar_combo4(){
        ArrayList<String> traerpais = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.paises;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerpais.add(resultado.getString("pais"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerpais;
    }
    public static ArrayList<String> llenar_combo6(){
        ArrayList<String> traerlocalidad = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.localidades;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerlocalidad.add(resultado.getString("localidad"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerlocalidad;
    }
    public static ArrayList<String> llenar_combo7(){
        ArrayList<String> traerestado = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.estados;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerestado.add(resultado.getString("descripcion"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerestado;
    }
    public static ArrayList<String> llenar_combo8(){
        ArrayList<String> traerproveedor = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("SELECT * FROM picar_db.proveedores;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerproveedor.add(resultado.getString("proveedor"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerproveedor;
    }
    public static ArrayList<String> llenar_combo9(){
        ArrayList<String> traerfechas = new ArrayList<String>();
        try {
            sentencia.executeUpdate("SET lc_time_names = 'es_AR';");
            resultado = sentencia.executeQuery("select UPPER(DATE_FORMAT(fecharemito,'%M')) from picar_db.remitos group by remitos.fecharemito order by month(fecharemito) asc;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traerfechas.add(resultado.getString("UPPER(DATE_FORMAT(fecharemito,'%M'))"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traerfechas;
    }
    public static ArrayList<String> llenar_combo10(){
        ArrayList<String> traeranios = new ArrayList<String>();
        try {
            resultado = sentencia.executeQuery("select DATE_FORMAT(fecharemito,'%Y') from picar_db.remitos group by YEAR(fecharemito) order by YEAR(fecharemito) asc;");
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        try {
            while(resultado.next()){
                traeranios.add(resultado.getString("DATE_FORMAT(fecharemito,'%Y')"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
            JOptionPane.showMessageDialog(null, "ERROR: "+e, "ERROR", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        return traeranios;
    }
}
