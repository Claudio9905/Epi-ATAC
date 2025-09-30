package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Punto_di_Emissione")
public class PuntoEmissione {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Tipo_Rivenditore", nullable = false)
    private TipoRivenditore tipo;
    @Column(name = "In_Servizio")
    private boolean inServizio;

    @OneToMany(mappedBy = "puntoEmissione")
    private List<GestioneVendita> listaVendite;

    //Costruttori
    public PuntoEmissione() {
    }

    public PuntoEmissione(TipoRivenditore tipo, boolean inServizio) {
        this.tipo = tipo;
        this.inServizio = inServizio;
    }

    //Metodi
    public UUID getPuntoEmissioneId() {
        return Id;
    }

    public TipoRivenditore getTipo() {
        return tipo;
    }

    public void setTipo(TipoRivenditore tipo) {
        this.tipo = tipo;
    }

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public List<GestioneVendita> getListaVendite() {
        return listaVendite;
    }

    public void setListaVendite(List<GestioneVendita> listaVendite) {
        this.listaVendite = listaVendite;
    }

    @Override
    public String toString() {
        return "|-- Punto di Emissione: " +
                " ID punto emissione= " + Id +
                ", Tipo= " + tipo +
                ", In servizio= " + inServizio +
                " --|";
    }
}
