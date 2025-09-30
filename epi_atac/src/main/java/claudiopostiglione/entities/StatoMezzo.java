package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Stato_Mezzo")
public class StatoMezzo {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Tipo_Stato", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoStatoMezzo stato;
    @Column(name = "Data_Inizio", nullable = false)
    private LocalDate dataInizio;
    @Column(name = "Data_Fine")
    private LocalDate dataFine;
    @Column(name = "Motivo_Manutenzione")
    private String motivoManutenzione;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Mezzo mezzo;

    //Costruttori
    public StatoMezzo() {
    }

    public StatoMezzo(Mezzo mezzo, TipoStatoMezzo stato, LocalDate dataInizio, LocalDate dataFine) {
        this.mezzo = mezzo;
        this.stato = stato;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;

    }

    public StatoMezzo(Mezzo mezzo, TipoStatoMezzo stato, LocalDate dataInizio, LocalDate dataFine, String motivoManutenzione) {
        this(mezzo, stato, dataInizio, dataFine);
        this.motivoManutenzione = motivoManutenzione;

    }

    //Metodi
    public UUID getStatoId() {
        return Id;
    }

    public TipoStatoMezzo getStato() {
        return stato;
    }

    public void setStato(TipoStatoMezzo stato) {
        this.stato = stato;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getMotivoManutenzione() {
        return motivoManutenzione;
    }

    public void setMotivoManutenzione(String motivoManutenzione) {
        this.motivoManutenzione = motivoManutenzione;
    }

    @Override
    public String toString() {
        return "|-- StatoMezzo: " +
                " ID stato= " + Id +
                ", Stato del mezzo= " + stato +
                ", Data inizio= " + dataInizio +
                ", Data fine= " + dataFine +
                ", Motivo della manutenzione= " + motivoManutenzione + '\'' +
                " --|";
    }
}
