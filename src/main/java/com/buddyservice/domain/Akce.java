package com.buddyservice.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "AKCE")
public class Akce {
    private Long idAkce;
    private DruhAkce druhAkce;
    private String nazev;
    private String datum;
    private String casOd;
    private String casDo;
    private Adresa misto;
    private String popis;
    private double cena;
    private int kapacita;
    private Set<Student> studenti;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID_AKCE")
    public Long getIdAkce() {
        return idAkce;
    }

    public void setIdAkce(Long idAkce) {
        this.idAkce = idAkce;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "ID_DRUH")
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

    @Column(name = "DATUM")
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    @Column(name = "CAD_OD")
    public String getCasOd() {
        return casOd;
    }

    public void setCasOd(String casOd) {
        this.casOd = casOd;
    }

    @Column(name = "CAS_DO")
    public String getCasDo() {
        return casDo;
    }

    public void setCasDo(String casDo) {
        this.casDo = casDo;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADRESA")
    public Adresa getMisto() {
        return misto;
    }

    public void setMisto(Adresa misto) {
        if (misto != null) {
            misto.getAkce().add(this);
        }
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

    @ManyToMany(mappedBy = "akce")
    public Set<Student> getStudenti() {
        if (studenti == null) {
            this.studenti = new HashSet<>();
        }
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }

}
