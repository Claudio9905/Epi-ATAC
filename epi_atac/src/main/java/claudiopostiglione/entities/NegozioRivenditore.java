package claudiopostiglione.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;
@Entity
@Table(name = "Rivenditore_Autorizzato")
public class NegozioRivenditore extends PuntoEmissione {

    @Column(name = "Nome_Rivenditore", nullable = false)
    private String proprietario;
    @Column(name = "Nome_Locale", nullable = false)
    private String nomeLocale;
    @Column(name = "Orario_Apertura", nullable = false)
    private LocalTime orarioApertura;
    @Column(name = "Orario_Chiusura", nullable = false)
    private LocalTime orarioChiusura;


    public NegozioRivenditore(String posizione, String proprietario, String nomeLocale, LocalTime orarioApertura, LocalTime orarioChiusura) {
        super(posizione);
        this.proprietario = proprietario;
        this.nomeLocale = nomeLocale;
        this.orarioApertura = orarioApertura;
        this.orarioChiusura = orarioChiusura;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public String getNomeLocale() {
        return nomeLocale;
    }

    public void setNomeLocale(String nomeLocale) {
        this.nomeLocale = nomeLocale;
    }

    public LocalTime getOrarioApertira() {
        return orarioApertura;
    }

    public void setOrarioApertira(LocalTime orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public LocalTime getOrarioChiusura() {
        return orarioChiusura;
    }

    public void setOrarioChiusura(LocalTime orarioChiusura) {
        this.orarioChiusura = orarioChiusura;
    }

    @Override
    public String toString() {
        return "NegozioRivenditore{" +
                "Id=" + Id +
                ", nomeLocale='" + nomeLocale + '\'' +
                ", Posizione=" + Posizione +
                ", orarioApertura=" + orarioApertura +
                ", orarioChiusura=" + orarioChiusura +
                ", proprietario='" + proprietario + '\'' +

                '}';
    }
}
