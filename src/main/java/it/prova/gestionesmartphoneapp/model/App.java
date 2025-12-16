package it.prova.gestionesmartphoneapp.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;

@Entity
@Table(name = "app")
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "datainstallazione")
    private LocalDate dataInstallazione;
    @Column(name = "dataultimoaggiornamento")
    private LocalDate dataUltimoAggiornamento;
    @Column(name = "versione")
    private Float versione;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinTable(name = "app_smartphone", joinColumns = @JoinColumn(name = "app_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "smartphone_id", referencedColumnName = "id"))
    private Set<Smartphone> telefoni = new HashSet<Smartphone>();

    public App(){}

    public App(String nome, LocalDate dataInstallazione, LocalDate dataUltimoAggiornamento, Float versione) {
        this.nome = nome;
        this.dataInstallazione = dataInstallazione;
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
        this.versione = versione;
    }

    public Set<Smartphone> getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(Set<Smartphone> telefoni) {
        this.telefoni = telefoni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInstallazione() {
        return dataInstallazione;
    }

    public void setDataInstallazione(LocalDate dataInstallazione) {
        this.dataInstallazione = dataInstallazione;
    }

    public LocalDate getDataUltimoAggiornamento() {
        return dataUltimoAggiornamento;
    }

    public void setDataUltimoAggiornamento(LocalDate dataUltimoAggiornamento) {
        this.dataUltimoAggiornamento = dataUltimoAggiornamento;
    }

    public Float getVersione() {
        return versione;
    }

    public void setVersione(Float versione) {
        this.versione = versione;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof App)) return false;
        App a = (App) o;
        return this.id.equals(a.getId());
    }
    @Override
    public int hashCode(){
        return getClass().hashCode();
    }
}
