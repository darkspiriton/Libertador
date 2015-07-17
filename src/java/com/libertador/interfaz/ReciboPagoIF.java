/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.interfaz;

import com.libertador.bean.ReciboPagoBean;
import java.util.List;

/**
 *
 * @author StOn
 */
public interface ReciboPagoIF {
    public List<ReciboPagoBean> buscarReciboPago(int dni);
    public void importarReciboPago(); 
    public void guardarReciboPagoBD(ReciboPagoBean r);
}
