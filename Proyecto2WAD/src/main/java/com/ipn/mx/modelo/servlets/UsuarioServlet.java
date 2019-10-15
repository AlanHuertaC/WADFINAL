/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.servlets;

import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.entidades.Usuario;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Emiliano
 */
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
@MultipartConfig(maxFileSize = 16777215)
public class UsuarioServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String accion = request.getParameter("accion");
        
        if(accion.equals("nuevo")){
            agregarUsuario(request, response);
        }else{
            if(accion.equals("guardar")){
            almacenarUsuario(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher vista = request.getRequestDispatcher("usuarioForm.jsp");
            vista.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario u = new Usuario();
            u.setNombre(request.getParameter("txtNombre"));
            u.setPaterno(request.getParameter("txtPaterno"));
            u.setMaterno(request.getParameter("txtMaterno"));
            u.setWmail(request.getParameter("txtWmail"));
            InputStream is = null;
            Part archivo = request.getPart("txtFoto");
            if(archivo != null){
                is = archivo.getInputStream();
                u.setFoto(is);            
            }
            u.setWmail(request.getParameter("txt"));
            dao.create(u);
            RequestDispatcher vista = request.getRequestDispatcher("index.html");
        } catch (IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}
