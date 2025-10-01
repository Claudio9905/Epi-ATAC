package claudiopostiglione.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "Biglietto")
public class Biglietto extends GestioneVendita {

    //Attributi
    @Column(name = "Data_Obliterazione")
    private LocalDate DataObliterazione;

//    @ManyToOne
//    @JoinColumn(name = "Mezzo_di_Obliterazione")
//    private Mezzo mezzoObliterazione;

    //Costruttori
    public Biglietto() {
    }

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione puntoEmissione, Mezzo mezzoObliterazione) {
        super(dataAcquisto, puntoEmissione, mezzoObliterazione);
    }

    public Biglietto(LocalDate dataAcquisto, PuntoEmissione puntoEmissione, LocalDate dataObliterazione, Mezzo mezzoObliterazione) {
        super(dataAcquisto, puntoEmissione, mezzoObliterazione);
        DataObliterazione = dataObliterazione;
    }

    //Metodi

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
                ", Mezzo di obliterazione= " + mezzo +
                ", Obliterato= " + DataObliterazione +
                " --|";
    }
}
