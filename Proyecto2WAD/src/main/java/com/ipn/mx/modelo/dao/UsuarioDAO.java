/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.entidades.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Emiliano
 */
public class UsuarioDAO {
    private static final String SQL_INSERT = "insert into usuario (nombre, paterno, materno, email, imagen) svalues (?,?,?,?,?,?)";
    
    private Connection con = null;
    
    public UsuarioDAO(){
    }
    
    public void create(Usuario u) throws SQLException{
        obtenerConexion();
        PreparedStatement ps = null;
        try{
            ps = con.prepareStatement(SQL_INSERT);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getPaterno());
            ps.setString(3, u.getMaterno());
            ps.setString(4, u.getWmail());
            ps.setBlob(5, u.getFoto());
            ps.executeUpdate();
        }finally{
            if(ps != null) ps.close();
            if(con != null) con.close();
        }
    }

    private Connection obtenerConexion() throws SQLException {
        String driverSQL = "com.mysql.cj.jdbc.Driver";
        String userDB = "root";
        String passwordDB="";
        String urlDB="jdbc:mysql://localhost:3306/eventosescom?useLegacyDatetimeCode=false&serverTimezone=UTC";
        
        try {
            Class.forName(driverSQL);
            con = DriverManager.getConnection(urlDB, userDB, passwordDB);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;

    }
}
