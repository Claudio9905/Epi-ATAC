package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Tratta")
public class Tratta {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Percorrenza_prevista", nullable = false)
    private int percorrenzaPrevista;
    @Column(name = "Zona_Partenza", nullable = false)
    private String zonaPartenza;
    @Column(name = "Capolinea", nullable = false)
    private String capolinea;

    @OneToMany(mappedBy = "tratta")
    private List<MezzoTratta> listaMezzoTratta;


    //Costruttori
    public Tratta() {
    }

    public Tratta(int percorrenzaPrevista, String zonaPartenza, String capolinea) {
        this.percorrenzaPrevista = percorrenzaPrevista;
        this.zonaPartenza = zonaPartenza;
        this.capolinea = capolinea;
    }

    //Metodi
    public UUID getTrattaId() {
        return Id;
    }

    public int getPercorrenzaPrevista() {
        return percorrenzaPrevista;
    }

    public void setPercorrenzaPrevista(int percorrenzaPrevista) {
        this.percorrenzaPrevista = percorrenzaPrevista;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    @Override
    public String toString() {
        return "|-- Tratta: " +
                " ID tratta= " + Id +
                ", Percorrenza prevista= " + percorrenzaPrevista +
                ", Zona partenza= " + zonaPartenza + '\'' +
                ", Capolinea= " + capolinea + '\'' +
                " --|";
    }
}
