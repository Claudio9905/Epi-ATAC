package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Gestione_Vendita")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class GestioneVendita {

    //Attributi
    @Id
    @GeneratedValue
    protected UUID id;
    @Column(name = "Data_Acquisto", nullable = false)
    protected LocalDate dataAcquisto;

    @ManyToOne
    @JoinColumn(name = "punto_emissione", nullable = false)
    protected PuntoEmissione puntoEmissione;

    @ManyToOne
    @JoinColumn(name = "mezzo")
    protected Mezzo mezzoObliterazione;

    //Costruttori
    public GestioneVendita() {
    }

    public GestioneVendita(LocalDate dataAcquisto, PuntoEmissione puntoEmissione, Mezzo mezzoObliterazione) {
        this.dataAcquisto = dataAcquisto;
        this.puntoEmissione = puntoEmissione;
        this.mezzoObliterazione = mezzoObliterazione;
    }

    public GestioneVendita(LocalDate dataAcquisto, PuntoEmissione puntoEmissione) {
        this.dataAcquisto = dataAcquisto;
        this.puntoEmissione = puntoEmissione;
    }



    //Metodi
    public UUID getId() {
        return id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Mezzo getMezzoObliterazione() {
        return mezzoObliterazione;
    }

    public void setMezzoObliterazione(Mezzo mezzoObliterazione) {
        this.mezzoObliterazione = mezzoObliterazione;
    }

    public PuntoEmissione getPuntoEmissione() {
        return puntoEmissione;
    }

    public void setPuntoEmissione(PuntoEmissione puntoEmissione) {
        this.puntoEmissione = puntoEmissione;
    }

    @Override
    public String toString() {
        return "|-- GestioneVendita:" +
                "ID= " + id +
                ", Data acquisto= " + dataAcquisto +
                ", Punto emissione= " + puntoEmissione +
                " --|";
    }
}
