package it.prova.gestionesmartphoneapp.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "smartphone")
public class Smartphone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "marca")
    private String marca;
    @Column(name = "modello")
    private String modello;
    @Column(name = "prezzo")
    private Float prezzo;
    @Column(name = "versioneos")
    private Float versioneOS;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "telefoni")
    private Set<App> apps = new HashSet<App>();

    public Smartphone(){}

    public Smartphone(String marca, String modello, Float prezzo, Float versioneOS) {
        this.marca = marca;
        this.modello = modello;
        this.prezzo = prezzo;
        this.versioneOS = versioneOS;
    }

    public Set<App> getApps() {
        return apps;
    }

    public void setApps(Set<App> apps) {
        this.apps = apps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getVersioneOS() {
        return versioneOS;
    }

    public void setVersioneOS(Float versioneOS) {
        this.versioneOS = versioneOS;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Smartphone)) return false;
        Smartphone s = (Smartphone) o;
        return this.id.equals(s.getId());
    }
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
