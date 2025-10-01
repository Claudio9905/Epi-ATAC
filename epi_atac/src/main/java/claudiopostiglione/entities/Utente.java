package claudiopostiglione.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Utente")
public class Utente {

    //Attributi
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "Amministratore", nullable = false)
    private boolean amministratore;
    @Column(name = "Nome", nullable = false)
    private String nome;
    @Column(name = "Cognome", nullable = false)
    private String cognome;
    @Column(name = "E-mail", nullable = false)
    private String email;
    @Column(name = "Data_Nascita", nullable = true)
    private LocalDate dataNascita;

    @OneToOne(mappedBy = "utente")
    private TesseraUtente tessera;

    //Costruttori
    public Utente() {
    }

    public Utente(String nome, String cognome, String email, LocalDate dataNascita, boolean amministratore) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.amministratore = amministratore;
    }

    //Metodi
    public long getId() {
        return id;
    }

    public boolean isAmministratore() {
        return amministratore;
    }

    public void setAmministratore(boolean amministratore) {
        this.amministratore = amministratore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    @Override
    public String toString() {
        return "|-- Utente: " +
                " ID utente= " + id +
                ", Amministratore= " + amministratore +
                ", Nome= " + nome + '\'' +
                ", Cognome= " + cognome + '\'' +
                ", Email= " + email + '\'' +
                ", Data nascita= " + dataNascita +
                " --|";
    }
}
