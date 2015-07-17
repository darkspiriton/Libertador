/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.dao;

import com.libertador.bean.ReciboPagoBean;
import com.libertador.interfaz.ReciboPagoIF;
import com.libertador.metodo.ConnectionMySQL;
import com.libertador.metodo.Consumer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.activemq.*;
import javax.jms.*;

/**
 *
 * @author StOn
 */
public class ReciboPagoBeanDAO implements ReciboPagoIF{
    ConnectionMySQL mysql = new ConnectionMySQL();
    
    @Override
    public List buscarReciboPago(int dni) {
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
                            lrecibo.add( new ReciboPagoBean(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4)));
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
       ActiveMQConnectionFactory connectionFactory;
       javax.jms.Connection connection;
       Session session;
       Destination destination; 
        
        
        try {
      
            connectionFactory = new ActiveMQConnectionFactory(
            ActiveMQConnection.DEFAULT_USER,
            ActiveMQConnection.DEFAULT_PASSWORD,
            ActiveMQConnection.DEFAULT_BROKER_URL);
            connection = connectionFactory.createConnection();
            connection.start();
            
            session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("GestorCola");
            MessageConsumer consumer = session.createConsumer(destination);
            
            Consumer myConsumer = new Consumer();
            consumer.setMessageListener(myConsumer);
            
            Thread.sleep(3000);
            session.close();
            connection.close();
        }
            catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void guardarReciboPagoBD(ReciboPagoBean r) {
        Connection con = null;
	                
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO `libertador`.`recibo` (`cod`, `alumno_dni`, `monto`, `estado`) VALUES (?,?,?,?);";
				
		try {
			con = mysql.getConnection();
			pstmt = con.prepareStatement(sql);
			
		        pstmt.setInt(1,r.getCod());
                        pstmt.setInt(2,r.getDni());
                       	pstmt.setInt(3,r.getMonto());		
			pstmt.setString(4,r.getEstado());
                        
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
