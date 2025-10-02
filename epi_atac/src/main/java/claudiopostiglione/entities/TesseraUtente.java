package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Tessera_Utente")
public class TesseraUtente {

    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "Data_Inizio", nullable = false)
    private LocalDate dataInizio;
    @Column(name = "Data_Scadenza", nullable = false)
    private LocalDate dataScadenza;

    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> abbonamento;

    @OneToOne
    @JoinColumn(name = "utente_id", unique = true)
    private Utente utente;

    //Costruttori
    public TesseraUtente() {
    }

    public TesseraUtente(LocalDate dataInizio, Utente utente) {
        this.dataInizio = dataInizio;
        this.dataScadenza = dataInizio.plusYears(1);
        this.utente = utente;
    }

    //Metodi
    public long getTesseraId() {
        return Id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public List<Abbonamento> getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(List<Abbonamento> abbonamento) {
        this.abbonamento = abbonamento;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "|-- Tessera Utente: " +
                " ID tessera= " + Id +
                ", Data inizio= " + dataInizio +
                ", Data scadenza= " + dataScadenza +
                " --|";
    }
}
