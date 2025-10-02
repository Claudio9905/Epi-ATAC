package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Punto_di_Emissione")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PuntoEmissione {

    //Attributi
    @Id
    @GeneratedValue
    protected UUID Id;
    @Column(name = "Posizione", nullable = false)
    protected String Posizione;

    @OneToMany(mappedBy = "puntoEmissione")
    protected List<GestioneVendita> listaVendite;

    //Costruttori
    public PuntoEmissione() {
    }

    public PuntoEmissione( String posizione) {
        Posizione = posizione;

    }

//metodi


    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getPosizione() {
        return Posizione;
    }

    public void setPosizione(String posizione) {
        Posizione = posizione;
    }

    public List<GestioneVendita> getListaVendite() {
        return listaVendite;
    }

    public void setListaVendite(List<GestioneVendita> listaVendite) {
        this.listaVendite = listaVendite;
    }

    @Override
    public String toString() {
        return "PuntoEmissione{" +
                "Id=" + Id +
                ", Posizione='" + Posizione + '\'' +
                ", listaVendite=" + listaVendite +
                '}';
    }
}
