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

    @ManyToOne
    protected  PuntoEmissione punto_emissione;

    @ManyToOne
    protected TesseraUtente tessera;

    @ManyToOne
    protected Mezzo mezzo_di_obliterazione;


    //Costruttore
    public Biglietto() {
    }

    public Biglietto(LocalDate dataAcquisto, boolean obliterato) {
        this.dataAcquisto = dataAcquisto;
        this.obliterato = obliterato;
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

    @Override
    public String toString() {
        return "|-- Biglietto: " +
                " ID biglietto= " + Id +
                ", Data acquisto= " + dataAcquisto +
                ", Obliterato= " + obliterato +
                " --|";
    }
}
