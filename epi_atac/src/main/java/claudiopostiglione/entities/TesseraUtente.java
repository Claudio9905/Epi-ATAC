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
    private boolean valido;

    @OneToMany(mappedBy = "tessera")
    private List<Biglietto> listaBiglietti;


    //Costruttori
    public TesseraUtente() {
    }

    public TesseraUtente(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
        this.dataScadenza = dataInizio.plusYears(1);
        this.valido = LocalDate.now().isAfter(dataScadenza);
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

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public List<Biglietto> getListaBiglietti() {
        return listaBiglietti;
    }

    public void setListaBiglietti(List<Biglietto> listaBiglietti) {
        this.listaBiglietti = listaBiglietti;
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
