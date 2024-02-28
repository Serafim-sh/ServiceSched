/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.train_schedule.dto;

/**
 *
 * @author simik
 */
public class TrainWagons {

    private String typeWagon;
    private Integer plase;
    private double priceTicketBeg;
    private double priceTicketEnd;

    public String getTypeWagon() {
        return typeWagon;
    }

    public void setTypeWagon(String typeWagon) {
        this.typeWagon = typeWagon;
    }

    public Integer getPlase() {
        return plase;
    }

    public void setPlase(Integer plase) {
        this.plase = plase;
    }

    public double getPriceTicketBeg() {
        return priceTicketBeg;
    }

    public void setPriceTicketBeg(double priceTicketBeg) {
        this.priceTicketBeg = priceTicketBeg;
    }

    public double getPriceTicketEnd() {
        return priceTicketEnd;
    }

    public void setPriceTicketEnd(double priceTicketEnd) {
        this.priceTicketEnd = priceTicketEnd;
    }
    
    
}
