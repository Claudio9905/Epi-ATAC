package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Biglietto")
public class Biglietto extends GestioneVendita{

    //Attributi
    @Column(name = "Obliterato", nullable = false)
    private boolean obliterato;

    @ManyToOne
    @JoinColumn(name = "Mezzo_di_Obliterazione", nullable = false)
    private Mezzo mezzoObliterazione;

    //Costruttori
    public Biglietto(){}

    public Biglietto(Mezzo mezzoObliterazione, LocalDate dataAcquisto, PuntoEmissione puntoEmissione){
        super(dataAcquisto, puntoEmissione);
        this.mezzoObliterazione = mezzoObliterazione;
        this.obliterato = false;
    }

    //Metodi
    public Mezzo getMezzoObliterazione() {
        return mezzoObliterazione;
    }

    public void setMezzoObliterazione(Mezzo mezzoObliterazione) {
        this.mezzoObliterazione = mezzoObliterazione;
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
                ", ID= " + id +
                ", Punto emissione=" + puntoEmissione +
                ", Data acquisto= " + dataAcquisto +
                ", Mezzo di obliterazione= " + mezzoObliterazione +
                ", Obliterato= " + obliterato +
                ", Valido= " + valido +
                " --|";
    }
}
