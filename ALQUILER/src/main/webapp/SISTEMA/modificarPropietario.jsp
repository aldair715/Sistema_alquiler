<%-- 
    Document   : modificarPropietario
    Created on : 25-ago-2021, 1:05:24
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MODIFICANDO DATOS DE PROPIETARIO</h1>
        <jsp:useBean id="persona" scope="session" class="com.test.bean.PersonaBean" />
        <jsp:useBean id="ciudad" scope="session" class="com.test.bean.CiudadBean"/>
        <%
            //rescatar el parametro del codigo de categoria
            String id_Persona=request.getParameter("id_persona");
            persona.buscarUnPropietario(id_Persona);
            //verificando si se acciono el botono de modificar
            if(request.getParameter("modificar")!=null)
            {
                
                String Salida=persona.modificarPersona(request, id_Persona);
                out.print(Salida);
            }
        %>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">DATOS DEL REGISTRO PERSONA</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>CEDULA DE IDENTIDAD</td>
                        <td><input type="text" name="ci" value="<%=persona.getPersona_modificar().getCi() %>"></td>
                    </tr>
                    <tr>
                        <td>TELEFONO</td>
                        <td><input type="text" name="telefono" value="<%=persona.getPersona_modificar().getTelefono() %>"></td>
                    </tr>
                    <tr>
                        <td>LUGAR DE PROCEDENCIA</td>
                        <td><input type="text" name="ciudad" value="<%=persona.getPersona_modificar().getCiudad() %>"></td>
                    </tr>
                    <tr>
                        <td>NOMBRE DE LA PERSONA</td>
                        <td><input type="text" name="nombre" value="<%=persona.getPersona_modificar().getNombre() %>"></td>
                    </tr>
                    <tr>
                        <td>CIUDAD DE PROCEDENCIA</td>
                        <td>
                            <select name="id_ciudad">
                                <%=ciudad.listarCiudadSelect() %>
                            </select>
                        </td>
                    </tr>
               
                    <tr>
                        <td colspan="2">
                            <input type="submit" name="modificar" value="modificar" />
                        </td>

                    </tr>
                </tbody>
            </table>
        </form>
    </body>
</html>
