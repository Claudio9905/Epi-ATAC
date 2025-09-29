package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Biglietto")
@Inheritance(strategy = InheritanceType.JOINED)
public class Biglietto {

    //Attributi
    @Id
    @GeneratedValue
    protected UUID Id;
    @Column(name = "Data_Acquisto", nullable = false)
    protected LocalDate dataAcquisto;
    @Column(name = "Obliterato", nullable = false)
    protected boolean obliterato;
    @Column(name = "Annullato", nullable = false)
    protected boolean annullato;
    @Column(name = "Data_Obliterazione")
    protected LocalDate dataObliterazione;

    @ManyToOne
    protected  PuntoEmissione punto_emissione;

    @ManyToOne
    protected TesseraUtente tessera;

    @ManyToOne
    protected Mezzo mezzo_di_obliterazione;


    //Costruttore
    public Biglietto() {
    }

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione punto_emissione, TesseraUtente tessera) {
        this.dataAcquisto = dataAcquisto;
        this.obliterato = false;
        this.annullato = false;
        this.punto_emissione = punto_emissione;
        this.tessera = tessera;
    }

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione punto_emissione, TesseraUtente tessera, LocalDate dataObliterazione, Mezzo mezzo_di_obliterazione) {
        this(dataAcquisto, punto_emissione, tessera);
        this.obliterato = true;
        this.annullato = true;
        this.dataObliterazione = dataObliterazione;
        this.mezzo_di_obliterazione = mezzo_di_obliterazione;
    }

    //Metodi
    public UUID getBigliettoId() {
        return Id;
    }

    public LocalDate getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(LocalDate dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public boolean isObliterato() {
        return obliterato;
    }

    public void setObliterato(boolean obliterato) {
        this.obliterato = obliterato;
    }

    public boolean isAnnullato() {
        return annullato;
    }

    public void setAnnullato(boolean annullato) {
        this.annullato = annullato;
    }

    public PuntoEmissione getPunto_emissione() {
        return punto_emissione;
    }

    public void setPunto_emissione(PuntoEmissione punto_emissione) {
        this.punto_emissione = punto_emissione;
    }

    public TesseraUtente getTessera() {
        return tessera;
    }

    public void setTessera(TesseraUtente tessera) {
        this.tessera = tessera;
    }

    public Mezzo getMezzo_di_obliterazione() {
        return mezzo_di_obliterazione;
    }

    public void setMezzo_di_obliterazione(Mezzo mezzo_di_obliterazione) {
        this.mezzo_di_obliterazione = mezzo_di_obliterazione;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "Id=" + Id +
                ", tessera=" + tessera +
                ", dataAcquisto=" + dataAcquisto +
                ", punto_emissione=" + punto_emissione +
                ", dataObliterazione=" + dataObliterazione +
                ", mezzo_di_obliterazione=" + mezzo_di_obliterazione +
                ", obliterato=" + obliterato +
                ", annullato=" + annullato +
                '}';
    }
}