/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.dao;

import com.libertador.bean.AlumnoBean;
import com.libertador.interfaz.AlumnoIF;
import com.libertador.metodo.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author StOn
 */
public class AlumnoBeanDAO implements AlumnoIF {
    ConnectionMySQL mysql = new ConnectionMySQL();
    
    @Override
    public void registrarAlumno(AlumnoBean a) {
        Connection con = null;
	                
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO `libertador`.`alumno` (`dni`, `nombre`, `apellido`) VALUES (?,?,?);";
				
		try {
			con = mysql.getConnection();
			pstmt = con.prepareStatement(sql);
			
		        pstmt.setInt(1,a.getDni());
                        pstmt.setString(2,a.getNombre());
                        pstmt.setString(3,a.getApellido());
                       			
			
                        pstmt.executeUpdate();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
                    
		} finally {
			try {
				
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
    }
    
}
