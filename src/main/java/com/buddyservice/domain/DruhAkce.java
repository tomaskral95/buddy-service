package com.buddyservice.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "DRUH_AKCE")
public class DruhAkce {
    private Long idAkce;
    private String druh;
    private Set<Akce> akce;

    @Id
    @Column(name = "ID_DRUH")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    public Long getIdAkce() {
        return idAkce;
    }

    public void setIdAkce(Long idAkce) {
        this.idAkce = idAkce;
    }

    @Column(name = "DRUH")
    public String getDruh() {
        return druh;
    }

    public void setDruh(String druh) {
        this.druh = druh;
    }

    @OneToMany(mappedBy = "druhAkce")
    public Set<Akce> getAkce() {
        if (akce == null) {
            akce = new HashSet<>();
        }
        return akce;
    }

    public void setAkce(Set<Akce> akce) {
        for (Akce a : akce ) {
            a.setDruhAkce(this);
        }
        this.akce = akce;
    }
}
