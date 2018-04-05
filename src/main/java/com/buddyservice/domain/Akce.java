package com.buddyservice.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AKCE")
public class Akce {

    private Long id;
    // TODO Druh akce
    private DruhAkce druhAkce;
    private String nazev;
    private Date datum;
    private Date casOd;
    private Date casDo;
    private Adresa misto;
    private String popis;
    private double cena;
    private int kapacita;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DRUH")
    public DruhAkce getDruhAkce() {
        return druhAkce;
    }

    public void setDruhAkce(DruhAkce druhAkce) {
        this.druhAkce = druhAkce;
    }

    @Column(name = "NAZEV")
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATUM")
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "CAS_OD")
    public Date getCasOd() {
        return casOd;
    }

    public void setCasOd(Date casOd) {
        this.casOd = casOd;
    }

    @Temporal(TemporalType.TIME)
    @Column(name = "CAS_DO")
    public Date getCasDo() {
        return casDo;
    }

    public void setCasDo(Date casDo) {
        this.casDo = casDo;
    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID")
    public Adresa getMisto() {
        return misto;
    }

    public void setMisto(Adresa misto) {
        this.misto = misto;
    }

    @Column(name = "POPIS")
    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @Column(name = "CENA")
    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Column(name = "KAPACITA")
    public int getKapacita() {
        return kapacita;
    }

    public void setKapacita(int kapacita) {
        this.kapacita = kapacita;
    }

}
