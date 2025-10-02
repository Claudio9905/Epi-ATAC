package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "Mezzo_Tratto")
public class MezzoTratta {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Percorrenza_Effettiva", nullable = false)
    private long percorrenzaEffettiva;
    @Column(name = "Orario_Partenza", nullable = false)
    private LocalTime orarioPartenza;
    @Column(name = "Orario_Arrivo", nullable = false)
    private LocalTime orarioArrivo;


    @ManyToOne
    private Tratta tratta;

    @ManyToOne
    private Mezzo mezzo;


    //Costruttori
    public MezzoTratta() {
    }

    public MezzoTratta(LocalTime orarioPartenza, LocalTime orarioArrivo, Tratta tratta, Mezzo mezzo) {
        this.orarioPartenza = orarioPartenza;
        this.tratta = tratta;
        this.mezzo = mezzo;
        this.orarioArrivo = orarioArrivo;
        this.percorrenzaEffettiva = ChronoUnit.MINUTES.between(orarioPartenza, orarioArrivo);
    }

    //Metodi
    public UUID getMezzoTrattaId() {
        return Id;
    }

    public void setMezzoTrattaId(UUID mezzoTrattaId) {
        this.Id = mezzoTrattaId;
    }

    public long getPercorrenzaEffettiva() {
        return percorrenzaEffettiva;
    }

    public void setPercorrenzaEffettiva(int percorrenzzaEffettiva) {
        this.percorrenzaEffettiva = percorrenzzaEffettiva;
    }

    public void setPercorrenzaEffettiva(long percorrenzaEffettiva) {
        this.percorrenzaEffettiva = percorrenzaEffettiva;
    }

    public LocalTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public LocalTime getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(LocalTime orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    @Override
    public String toString() {
        return "|-- Mezzo-Tratta: " +
                "ID del mezzo= " + mezzo +
                ", ID della tratta= " + tratta +
                ", Percorrenza Effettiva= " + percorrenzaEffettiva +
                ", Orario partenza= " + orarioPartenza +
                ", Orario arrivo=" + orarioArrivo +
                '}';
    }
}
