package com.blackphantom.blackcoinbroker;

import java.util.Date;

/**
 * Created by BPS on 09.06.2017.
 */

public class Depot {
    private int id;
    private double anzahlBlackcoins;
    private double wertBlackcoins = 10.5;
    private double kaufpreis;
    private Date datum;

    public Depot(int id, double anzahlBlackcoins, double kaufpreis, Date datum){
        this.id = id;
        this.anzahlBlackcoins = anzahlBlackcoins;
        this.kaufpreis = kaufpreis;
        this.datum = datum;
    }

    public Depot(double anzahlBlackcoins, double kaufpreis, Date datum){
        this.anzahlBlackcoins = anzahlBlackcoins;
        this.kaufpreis = kaufpreis;
        this.datum = datum;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setAnzahlBlackcoins(double anzahlBlackcoins){
        this.anzahlBlackcoins = anzahlBlackcoins;
    }

    public double getAnzahlBlackcoins(){
        return anzahlBlackcoins;
    }

    public void setWertBlackcoins(double kurs){
        this.wertBlackcoins = anzahlBlackcoins * kurs;
    }

    public double getWertBlackcoins(){
        return wertBlackcoins;
    }

    public void setKaufpreis(double kaufpreis){
        this.kaufpreis = kaufpreis;
    }

    public double getKaufpreis(){
        return kaufpreis;
    }

    public void setDatum(Date datum){
        this.datum = datum;
    }

    public Date getDatum(){
        return datum;
    }
}
