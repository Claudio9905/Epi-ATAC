package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "Distributore_Automatico")
public class DistributoreAutomatico extends PuntoEmissione {


    @Column(name = "In_servizio", nullable = false)
    private boolean inServizio;

    //costruttori
    public DistributoreAutomatico() {
    }

    public DistributoreAutomatico(String posizione, boolean inServizio) {
        super(posizione);
        this.inServizio = inServizio;
    }

    //metodi

    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }


    @Override
    public String toString() {
        return "DistributoreAutomatico{" +
                "Id=" + Id +
                ", inServizio=" + inServizio +
                ", Posizione=" + Posizione +
                '}';
    }
}


