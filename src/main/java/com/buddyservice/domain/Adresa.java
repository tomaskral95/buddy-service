package com.buddyservice.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ADRESA")
public class Adresa {

    private Long id;
    private String stat;
    private String mesto;
    private String ulice;
    private int cisloPopisne;
    private Student student;
    private Set<Akce> akce;

    @Id
    @Column(name = "ID_ADRESA")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "STAT")
    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Column(name = "MESTO")
    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    @Column(name = "ULICE")
    public String getUlice() {
        return ulice;
    }

    public void setUlice(String ulice) {
        this.ulice = ulice;
    }

    @Column(name = "CISLO_POPISNE")
    public int getCisloPopisne() {
        return cisloPopisne;
    }

    public void setCisloPopisne(int cisloPopisne) {
        this.cisloPopisne = cisloPopisne;
    }

    @OneToOne(mappedBy = "adresa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @OneToMany(mappedBy = "misto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Akce> getAkce() {
        if (akce == null) {
            akce = new HashSet<>();
        }
        return akce;
    }

    public void setAkce(Set<Akce> akce) {
        if (akce != null) {
            for (Akce a : akce) {
                a.setMisto(this);
            }
        }
        this.akce = akce;
    }
}
