/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.metodo;

import com.libertador.bean.ReciboPagoBean;
import com.libertador.dao.ReciboPagoBeanDAO;
import javax.jms.Message;
import javax.jms.*;
import javax.jms.MessageListener;
public class Consumer implements MessageListener {
    public void onMessage(Message arg0) {
        try {
            if (arg0 instanceof TextMessage) {
                System.out.println("Mensaje Recibido = "+((TextMessage)arg0).getText());
            }
            else if (arg0 instanceof ObjectMessage) {
                String[] parts = ((ObjectMessage)arg0).getObject().toString().split("/");
                ReciboPagoBean r = new ReciboPagoBean(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),parts[3]);
                ReciboPagoBeanDAO recibo = new ReciboPagoBeanDAO();
                recibo.guardarReciboPagoBD(r);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
