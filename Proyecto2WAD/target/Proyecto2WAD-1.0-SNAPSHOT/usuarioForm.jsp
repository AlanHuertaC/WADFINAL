<%-- 
    Document   : usuarioForm
    Created on : 14 oct 2019, 14:53:03
    Author     : Emiliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <fieldset>
            <legend>Datos Usuario</legend>
            <center>
                <form method="post" action="UsuarioServlet?accion=guardar" enctype="multipart/formdata">
                    <table>
                        <tr>
                            <td>
                                Nombre
                            </td>
                            <td>
                                <input type="text" name="txtNombre"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Archivo
                            </td>
                            <td>
                                <input type="file" name="txtFoto"/>
                            </td>
                        </tr>
                    </table>
                    <input type="submit" value="CARGAR">
                </form>
            </center>
        </fieldset>
    </body>
</html>
