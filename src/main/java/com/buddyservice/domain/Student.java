package com.buddyservice.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "STUDENT ")
public class Student {

    private Long id;
    private String xname;
    private String jmeno;
    private String prijmeni;
    private String titul;
    private Date datumNarozeni;
    private Pohlavi pohlavi;
    private String statniPrislusnost;
    private List<Adresa> adresy;
    private String telefon;
    private String email;


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "XNAME")
    public String getXname() {
        return xname;
    }

    public void setXname(String xname) {
        this.xname = xname;
    }

    @Column(name = "JMENO")
    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    @Column(name = "PRIJMENI")
    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    @Column(name = "titul")
    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    @Column(name = "DATUM_NAROZENI")
    public Date getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(Date datumNarozeni) {
        this.datumNarozeni = datumNarozeni;
    }

    @Column(name = "POHLAVI")
    public Pohlavi getPohlavi() {
        return pohlavi;
    }

    public void setPohlavi(Pohlavi pohlavi) {
        this.pohlavi = pohlavi;
    }

    @Column(name = "STATNI_PRISLUSNOST")
    public String getStatniPrislusnost() {
        return statniPrislusnost;
    }

    public void setStatniPrislusnost(String statniPrislusnost) {
        this.statniPrislusnost = statniPrislusnost;
    }

    @Column(name = "ADRESA")
    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Adresa> getAdresy() {
        if (adresy == null ) {
            adresy = new ArrayList<>();
        }
        return adresy;
    }

    public void setAdresy(List<Adresa> adresy) {
        for(Adresa adresa : adresy) {
            adresa.setStudent(this);
        }
        this.adresy = adresy;
    }

    public Adresa addAdresa(Adresa adresa) {
        if (adresa != null) {
            getAdresy().add(adresa);
            adresa.setStudent(this);
        }
        return adresa;
    }

    @Column(name = "TELEFON")
    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
