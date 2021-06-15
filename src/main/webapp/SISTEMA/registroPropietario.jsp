<%-- 
    Document   : registroPropietario
    Created on : 12-jun-2021, 21:57:16
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRO</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="../CSS/styles.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </head>
    <body>
        <jsp:useBean id="ciudadBean" scope="session" class="com.test.bean.CiudadBean"/>
        <jsp:useBean id="personaBean" scope="session" class="com.test.bean.PersonaBean"/>
        <%
            
            if(request.getParameter("guardar")!=null){
                String mensaje=personaBean.registrarPersona(request);
                out.print(mensaje);
            }
        %>
        <main>
            <a href="../index.jsp">VOLVER</a>
          <div class="container">
           <form id="formulario" class="form-contact">
                <h1>REGISTRO PROPIETARIO</h1>
                <div class="form-group">
                   <label for="usr">NOMBRE DEL PROPIETARIO:</label>
                   <input type="text" class="form-control form-control-sm" required name="nombre" pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]+$" title="COLOQUE UN NOMBRE CORRECTO, SOLO SE PERMITE VALORES ALFABETICOS" placeholder="ESCRIBA EL NOMBRE DEL PROPIETARIO" autocomplete="off">
                </div>
                <div class="form-group">
                   <label for="usr">LUGAR DE PROCEDENCIA:</label>
                   <input type="text" class="form-control form-control-sm" required name="lugar" pattern="^[A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]+$" title="COLOQUE UN LUGAR CORRECTO, SOLO SE PERMITE VALORES ALFABETICOS" placeholder="ESCRIBA EL LUGAR DE PROCEDENCIA DEL PROPIETARIO" autocomplete="off">
                </div>
                <div class="form-group">
                   <label for="usr">CEDULA DE IDENTIDAD:</label>
                   <input type="number" class="form-control form-control-sm" required name="cedula" pattern="^[0-9]{6,8}$" title="COLOQUE UNA CEDULA DE IDENTIDAD CORRECTA" placeholder="ESCRIBA LA CEDULA DEL PROPIETARIO" autocomplete="off">
                </div>
                <div class="form-group">
                   <label for="usr">TELEFONO:</label>
                   <input type="text" class="form-control form-control-sm" required name="telefono" pattern="^[0-9]{6,8}$" title="COLOQUE UNA TELEFONO CORRECTO, SOLO SE PERMITE VALORES NUMERICOS" placeholder="ESCRIBA EL TELEFONO DEL PROPIETARIO" autocomplete="off" >
                </div>
                <div>
                    <label>CIUDAD DONDE VIVE EL PROPIETARIO</label>
                    <select name="ciudad" class="custom-select-sm" required>
                        <%=ciudadBean.listarCiudadSelect()%>
                    </select>
                </div>
                <div class="form-group">
                   <label for="usr">TITULO PARA OFERTAR EL DOMICILIO:</label>
                   <input type="text" class="form-control form-control-sm" required name="nom_domicilio" pattern="^[0-9A-Za-zÑñÁáÉéÍíÓóÚúÜü\s]+$" title="COLOQUE UN NOMBRE CORRECTA, SOLO SE PERMITE VALORES ALFANUMERICOS" placeholder="ESCRIBA EL NOMBRE DEL DOMICILIO" autocomplete="off">
                </div>
                <div class="form-group">
                   <label for="pwd">PRECIO:</label>
                   <input type="text" min="0" class="form-control" id="pwd" required  name="precio" pattern="^[0-9]+([,][0-9]+)?$" title="COLOQUE UN PRECIO CORRECTO, SOLO SE PERMITE VALORES NUMERICOS, USE UNA COMA" placeholder="ESCRIBA EL PRECIO DEL DOMICILIO, USE LA COMA PARA DECIMALES" autocomplete="off">
                </div> 
                <div class="form-group">
                   <label for="usr">DIRECCION:</label>
                   <input type="text" class="form-control form-control-sm" required required  name="direccion" pattern="^[0-9A-Za-zÑñÁáÉéÍíÓóÚúÜü#º\s]+$" title="COLOQUE UN DIRECCION CORRECTO, SOLO SE PERMITE VALORES ALFANUMERICOS" placeholder="ESCRIBA LA DIRECCION DEL DOMICILIO" autocomplete="off">
       
                </div>
                 <div class="form-group">
                    <label for="comment">DESCRIPCION:</label>
                    <textarea class="form-control" rows="5" name="descripcion" required cols="50" data-pattern="^.{2,25}$" title="EL MENSAJE DEBE IR DESDE UNO HASTA 255"></textarea>
                 </div>
                <div>
                    <label>CIUDAD DONDE SE ENCUENTRA EL DOMICILIO</label>
                    <select name="ciudad_domicilio" class="custom-select-sm" required>
                        <%=ciudadBean.listarCiudadSelect()%>
                    </select>
                </div>
                    <div>
                        <input type="submit" value="ENVIAR" class="btn btn-success" name="guardar"/>
                    </div>
            </form>
            </div>
                <input type="hidden" class="btn btn-primary" id="myBtn"/>

                 <!-- Modal -->
                 <div class="modal fade" id="myModal" role="dialog">
                   <div class="modal-dialog">

                     <!-- Modal content-->
                     <div class="modal-content">
                     
                         <img src="../ASSETS/grid.svg" alt="CARGANDO...">
                       
                     </div>

                   </div>
                 </div>

        </main>
        
        
    </body>
    <script type="module" src="../JS/index.js"></script>
    
</html>
