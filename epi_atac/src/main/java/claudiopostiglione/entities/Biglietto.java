package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Biglietto")
public class Biglietto extends GestioneVendita{

    //Attributi
    @Column(name = "Data_Obliterazione")
    private LocalDate DataObliterazione;

    @ManyToOne
    @JoinColumn(name = "Mezzo_di_Obliterazione")
    private Mezzo mezzoObliterazione;

    //Costruttori
    public Biglietto(){}

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione puntoEmissione) {
        super(dataAcquisto, puntoEmissione);
    }

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione puntoEmissione, LocalDate dataObliterazione, Mezzo mezzoObliterazione) {
        super(dataAcquisto, puntoEmissione);
        DataObliterazione = dataObliterazione;
        this.mezzoObliterazione = mezzoObliterazione;
    }

    //Metodi
    public Mezzo getMezzoObliterazione() {
        return mezzoObliterazione;
    }

    public void setMezzoObliterazione(Mezzo mezzoObliterazione) {
        this.mezzoObliterazione = mezzoObliterazione;
    }

    public LocalDate getDataObliterazione() {
        return DataObliterazione;
    }

    public void setDataObliterazione(LocalDate dataObliterazione) {
        DataObliterazione = dataObliterazione;
    }

    @Override
    public String toString() {
        return "|-- Biglietto: " +
                "ID= " + id +
                ", Punto emissione=" + puntoEmissione +
                ", Data acquisto= " + dataAcquisto +
                ", Mezzo di obliterazione= " + mezzoObliterazione +
                ", Obliterato= " + DataObliterazione +
                " --|";
    }
}
