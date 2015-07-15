/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.dao;

import com.libertador.pojos.Alumno;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import java.util.List;
/**
 *
 * @author StOn
 */
public class AlumnoDAO {
    public void registrarAlumno(Alumno a){
        SessionFactory sf=null;
        Session sesion=null;
        Transaction tx=null;
        try{
            sf = HibernateUtil.getSessionFactory();
            sesion = sf.openSession();
            tx = sesion.beginTransaction();
            sesion.save(a);
            tx.commit();
            sesion.close();
        }
        catch (Exception ex){
            tx.rollback();
            throw new RuntimeException("No se pudo guardar el producto");
        }
    }
    
    public String consultarAlumno(int dni){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session sesion=sf.openSession();
        Alumno a = (Alumno)sesion.get(Alumno.class, dni);
        sesion.close();
        if(a!=null){
            return "El DNI del Alumno es "+ a.getDni() + " cuyo nombre es" + a.getNombre() + " " + a.getApellido();
        }else{
            return "El Alumno con dni "+dni+ " no existe";
        }        
    }
    public List<Alumno> verAlumno(){
        SessionFactory sf=HibernateUtil.getSessionFactory();
        Session sesion=sf.openSession();
        Query query=sesion.createQuery("from Alumno");
        List<Alumno> lista = query.list();
        sesion.close();  
        return lista;
    }        
}
