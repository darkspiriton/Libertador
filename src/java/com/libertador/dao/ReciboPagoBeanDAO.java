/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.dao;

import com.libertador.bean.ReciboPagoBean;
import com.libertador.interfaz.ReciboPagoIF;
import com.libertador.metodo.ConnectionMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author StOn
 */
public class ReciboPagoBeanDAO implements ReciboPagoIF{
    ConnectionMySQL mysql = new ConnectionMySQL();
    
    @Override
    public List<ReciboPagoBean> buscarReciboPago(int dni) {
        Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
        String sql="SELECT * FROM libertador.recibo where alumno_dni=?;";    
        List<ReciboPagoBean> lrecibo= new <ReciboPagoBean>ArrayList();
        
                 try {
			con = mysql.getConnection();
			pstmt = con.prepareStatement(sql);		
			pstmt.setInt(1,dni);   
			rs = pstmt.executeQuery();
                        
			while ( rs.next() ) {
                            lrecibo.add( new ReciboPagoBean(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
                        }
                        
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
   
        return lrecibo; 

    }

    @Override
    public void importarReciboPago() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
