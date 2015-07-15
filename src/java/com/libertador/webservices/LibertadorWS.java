/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.webservices;

import com.libertador.dao.AlumnoDAO;
import com.libertador.pojos.Alumno;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
     */
    @WebMethod(operationName = "RegistrarAlumno")
    public String RegistrarAlumno(@WebParam(name = "dni") int dni, @WebParam(name = "nombre") String nombre, @WebParam(name = "apellido") String apellido) {
        Set recibos = new HashSet(0);
        Alumno a = new Alumno(dni,nombre,apellido,recibos);
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        alumnoDAO.registrarAlumno(a);
        return "Producto ingresado";
        
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "buscarAlumno")
    public String buscarAlumno(@WebParam(name = "dni") int dni) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        return alumnoDAO.consultarAlumno(dni);
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "ConsultarAlumnos")
    public List ConsultarAlumnos() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        List<Alumno> listaAlumnos = alumnoDAO.verAlumno();
        return listaAlumnos;
        
    }
}
