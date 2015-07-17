/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libertador.bean;

/**
 *
 * @author StOn
 */
public class ReciboPagoBean {
    private int cod;
    private int dni;
    private int monto;

    public ReciboPagoBean() {
    }

    public ReciboPagoBean(int cod, int dni, int monto) {
        this.cod = cod;
        this.dni = dni;
        this.monto = monto;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }
}
