package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Mezzo_Tratto")
public class MezzoTratta {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Percorrenza_Effettiva", nullable = false)
    private int percorrenzaEffettiva;
    @Column(name = "Orario_Partenza", nullable = false)
    private LocalDateTime orarioPartenza;
    @Column(name = "Orario_Arrivo", nullable = false)
    private LocalDateTime orarioArrivo;


    @ManyToOne
    private Tratta tratta;

    @ManyToOne
    private Mezzo mezzo;


    //Costruttori
    public MezzoTratta() {
    }

    public MezzoTratta(int percorrenzaEffettiva, LocalDateTime orarioPartenza, LocalDateTime orarioArrivo) {
        this.percorrenzaEffettiva = percorrenzaEffettiva;
        this.orarioPartenza = orarioPartenza;
        this.orarioArrivo = orarioArrivo;
    }

    //Metodi
    public UUID getMezzoTrattaId() {
        return Id;
    }

    public void setMezzoTrattaId(UUID mezzoTrattaId) {
        this.Id = mezzoTrattaId;
    }

    public int getPercorrenzaEffettiva() {
        return percorrenzaEffettiva;
    }

    public void setPercorrenzaEffettiva(int percorrenzzaEffettiva) {
        this.percorrenzaEffettiva = percorrenzzaEffettiva;
    }

    public LocalDateTime getOrarioPartenza() {
        return orarioPartenza;
    }

    public void setOrarioPartenza(LocalDateTime orarioPartenza) {
        this.orarioPartenza = orarioPartenza;
    }

    public LocalDateTime getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(LocalDateTime orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    @Override
    public String toString() {
        return "|-- Mezzo-Tratta: " +
                " ID del mezzo= " + mezzo +
                ", ID della tratta= " + tratta +
                ", Percorrenza Effettiva= " + percorrenzaEffettiva +
                ", Orario partenza= " + orarioPartenza +
                ", Orario arrivo=" + orarioArrivo +
                '}';
    }
}
