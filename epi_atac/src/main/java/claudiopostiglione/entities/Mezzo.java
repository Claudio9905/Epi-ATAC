package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Mezzo")
public class Mezzo {

    //Attributi
    @Id
    @GeneratedValue
    private UUID Id;
    @Column(name = "Tipo_Mezzo", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipo;
    @Column(name = "Capienza", nullable = false)
    private int capienza;

    @OneToMany(mappedBy = "mezzo_di_obliterazione")
    private List<Biglietto> listaBiglietti;

    @OneToMany(mappedBy = "storico")
    private List<StatoMezzo> listaStorico;

    @OneToMany(mappedBy = "mezzo")
    private List<MezzoTratta> listaMezzoTratta;

    //Costruttori
    public Mezzo() {
    }

    public Mezzo(TipoMezzo tipo, int capienza) {
        this.tipo = tipo;
        this.capienza = capienza;
    }

    //Metodi
    public UUID getMezzoId() {
        return Id;
    }

    public TipoMezzo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMezzo tipo) {
        this.tipo = tipo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    @Override
    public String toString() {
        return "|-- Mezzo: " +
                " Id mezzo= " + Id +
                ", Tipo= " + tipo +
                ", Capienza= " + capienza +
                " --|";
    }
}
