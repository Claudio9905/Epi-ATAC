package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Abbonamento")
public class Abbonamento extends Biglietto {

    //Attributi
    @Column(name = "Tipo_Abbonamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAbbonamento tipo;
    @Column(name = "Data_Scadenza", nullable = false)
    private LocalDate dataScadenza;

    //Costruttori
    public Abbonamento() {
    }

    public Abbonamento(TipoAbbonamento tipo, LocalDate dataAcquisto) {
        super(dataAcquisto, true);
        this.tipo = tipo;
        if (tipo.equals(TipoAbbonamento.MENSILE)) {

            this.dataScadenza = dataAcquisto.plusDays(30);
        } else {
            this.dataScadenza = dataAcquisto.plusDays(7);
        }
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

    @Override
    public String toString() {
        return "|-- Abbonamento: " +
                "ID_abbonamento= " +
                ", Tipo= " + tipo +
                ", Data Scadenza= " + dataScadenza +
                " --|";
    }
}
