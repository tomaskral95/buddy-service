package com.buddyservice.domain;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "STUDENT")
public class Student {

    private String xname;
    private String rodneCislo;
    private String heslo;
    private String jmeno;
    private String prijmeni;
    private String titul;
    private String datumNarozeni;
    private Pohlavi pohlavi;
    private String statniPrislusnost;
    private Adresa adresa;
    private String telefon;
    private String email;
    private boolean zahranicni;
    private Set<Akce> akce;

    @Id
    @Column(name = "ROCNE_CISLO")
    public String getRodneCislo() {
        return rodneCislo;
    }

    public void setRodneCislo(String rodneCislo) {
        this.rodneCislo = rodneCislo;
    }

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

    @Column(name = "TITUL")
    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    @Column(name = "DATUM_NAROZENI")
    public String getDatumNarozeni() {
        return datumNarozeni;
    }

    public void setDatumNarozeni(String datumNarozeni) {
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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADRESA")
    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        if (adresa != null) {
            adresa.setStudent(this);
        }
        this.adresa = adresa;
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

    @Column(name = "ZAHRANICNI")
    public boolean isZahranicni() {
        return zahranicni;
    }

    public void setZahranicni(boolean zahranicni) {
        if (zahranicni){
            xname = null;
        }
        this.zahranicni = zahranicni;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "STUDENT_AKCE",
            joinColumns = {@JoinColumn(name = "RODNE_CISLO")},
            inverseJoinColumns = {@JoinColumn(name = "ID_AKCE")}
    )
    public Set<Akce> getAkce() {
        if (akce == null) {
            this.akce = new HashSet<>();
        }
        return akce;
    }

    public void setAkce(Set<Akce> akce) {
        for (Akce a : akce) {
            a.getStudenti().add(this);
        }
        this.akce = akce;
    }

    public void addAkce(Akce akce) {
        if (akce != null) {
            getAkce().add(akce);
            akce.getStudenti().add(this);
        }
    }

    public void removeAkce(Akce akce) {
        if (akce != null) {
            getAkce().remove(akce);
            akce.getStudenti().remove(this);
        }
    }

    @Column(name = "HESLO")
    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }
}
