package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Tessera_Utente")
public class TesseraUtente {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Data_Inizio", nullable = false)
    private LocalDate dataInizio;
    @Column(name = "Data_Scadenza", nullable = false)
    private LocalDate dataScadenza;

    @OneToMany(mappedBy = "tessera")
    private List<Biglietto> listaBiglietti;


    //Costruttori
    public TesseraUtente() {
    }

    public TesseraUtente(LocalDate dataInizio, LocalDate dataScadenza) {
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
    }

    //Metodi
    public UUID getTesseraId() {
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

    @Override
    public String toString() {
        return "|-- Tessera Utente: " +
                " ID tessera= " + Id +
                ", Data inizio= " + dataInizio +
                ", Data scadenza= " + dataScadenza +
                " --|";
    }
}
