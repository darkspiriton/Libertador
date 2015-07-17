/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.webservices;

import com.libertador.bean.AlumnoBean;
import com.libertador.bean.ReciboPagoBean;
import com.libertador.dao.AlumnoBeanDAO;
import com.libertador.dao.ReciboPagoBeanDAO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author StOn
 */
@WebService(serviceName = "LibertadorWS")
public class LibertadorWS {

    /**
     * Web service operation
     * @param dni
     * @param nombre
     * @param apellido
     * @return 
     */
    @WebMethod(operationName = "RegistrarAlumno")
    public String RegistrarAlumno(@WebParam(name = "dni") int dni, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        
        AlumnoBean a = new AlumnoBean(dni,nombre,apellido);
        AlumnoBeanDAO alumnoBeanDAO = new AlumnoBeanDAO();
        alumnoBeanDAO.registrarAlumno(a);
        return "Alumno registrado";
        
    }


    /**
     * Web service operation
     */
    @WebMethod(operationName = "ImportarRegistroPago")
    public String ImportarRegistroPago() {
        ReciboPagoBeanDAO recibo = new ReciboPagoBeanDAO();
        recibo.importarReciboPago();   
        return "Importe realizado correctamente";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "BuscarRegistroPago")
    public String BuscarRegistroPago(@WebParam(name = "dni") int dni) {
        ReciboPagoBeanDAO recibo = new ReciboPagoBeanDAO();      
        return recibo.buscarReciboPago(dni);
    }

}
