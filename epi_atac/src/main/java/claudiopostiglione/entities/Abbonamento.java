package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Abbonamento")
public class Abbonamento extends GestioneVendita {

    //Attributi
    @Column(name = "Tipo_Abbonamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipo;
    @Column(name = "Data_Scadenza", nullable = false)
    private LocalDate dataScadenza;

    @ManyToOne
    @JoinColumn(name = "tessera", nullable = false)
    private TesseraUtente tessera;

    //Costruttori
    public Abbonamento() {
    }

    public Abbonamento(TipoAbbonamento tipo, LocalDate dataAcquisto, PuntoEmissione puntoEmissione, TesseraUtente tessera, Mezzo mezzo) {
        super(dataAcquisto, puntoEmissione, mezzo);
        this.tipo = tipo;
        if (tipo.equals(TipoAbbonamento.MENSILE)) {
            this.dataScadenza = dataAcquisto.plusDays(30);
        } else {
            this.dataScadenza = dataAcquisto.plusDays(7);
        }
        this.tessera = tessera;
    }

    //Metodi
    public TipoAbbonamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoAbbonamento tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public TesseraUtente getTessera() {
        return tessera;
    }

    public void setTessera(TesseraUtente tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "|-- Abbonamento:" +
                " Id= " + id +
                ", Tipo= " + tipo +
                ", Tessera= " + tessera +
                ", Data Acquisto= " + dataAcquisto +
                ", Punto emissione= " + puntoEmissione +
                ", Data scadenza= " + dataScadenza +
                " --|";
    }
}
