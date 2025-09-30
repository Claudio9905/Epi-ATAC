package claudiopostiglione;

import claudiopostiglione.dao.*;
import claudiopostiglione.entities.*;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Application {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("epi_atac");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        System.out.println("Connessione al database riuscita!");

        Faker f = new Faker();
        Random r = new Random();

        UtenteDAO uDao = new UtenteDAO(em);

        Utente u1 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1991, 1, 1), false);
        Utente u2 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1992, 1, 1), false);
        Utente u3 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1993, 1, 1), false);
        Utente u4 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1994, 1, 1), false);
        Utente u5 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(2000, 1, 1), false);
        Utente u6 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1995, 1, 1), true);
        Utente u7 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1996, 1, 1), true);

        uDao.save(u1);
        uDao.save(u2);
        uDao.save(u3);
        uDao.save(u4);
        uDao.save(u5);
        uDao.save(u6);
        uDao.save(u7);

        TesseraUtenteDAO tuDao = new TesseraUtenteDAO(em);
        TesseraUtente tu1 = new TesseraUtente(LocalDate.of(2020, 5, 18), u1);
        TesseraUtente tu2 = new TesseraUtente(LocalDate.of(2023, 10, 20), u2);
        TesseraUtente tu3 = new TesseraUtente(LocalDate.of(2025, 1, 20), u3);
        TesseraUtente tu4 = new TesseraUtente(LocalDate.of(2025, 8, 31), u4);
        TesseraUtente tu5 = new TesseraUtente(LocalDate.of(2021, 10, 12), u5);
        tuDao.save(tu1);
        tuDao.save(tu2);
        tuDao.save(tu3);
        tuDao.save(tu4);
        tuDao.save(tu5);

        MezzoDAO mDao = new MezzoDAO(em);
        Mezzo m1 = new Mezzo(TipoMezzo.AUTOBUS, 80);
        Mezzo m2 = new Mezzo(TipoMezzo.TRAM, 50);
        Mezzo m3 = new Mezzo(TipoMezzo.AUTOBUS, 65);
        Mezzo m4 = new Mezzo(TipoMezzo.TRAM, 40);
        Mezzo m5 = new Mezzo(TipoMezzo.AUTOBUS, 120);
        mDao.save(m1);
        mDao.save(m2);
        mDao.save(m3);
        mDao.save(m4);
        mDao.save(m5);

        PuntoEmissioneDAO peDao = new PuntoEmissioneDAO(em);
        PuntoEmissione pe1 = new PuntoEmissione(TipoRivenditore.DISTRIBUTORE_AUTOMATICO, true);
        PuntoEmissione pe2 = new PuntoEmissione(TipoRivenditore.DISTRIBUTORE_AUTOMATICO, true);
        PuntoEmissione pe3 = new PuntoEmissione(TipoRivenditore.RIVENDITORE_AUTORIZZATO, true);
        PuntoEmissione pe4 = new PuntoEmissione(TipoRivenditore.DISTRIBUTORE_AUTOMATICO, false);
        peDao.save(pe1);
        peDao.save(pe2);
        peDao.save(pe3);
        peDao.save(pe4);

        //        LocalDate ld = LocalDate.of(r.nextInt(2023, 2026), r.nextInt(1, 13), r.nextInt(1, 32));
        GestioneVenditaDAO gvDao = new GestioneVenditaDAO(em);
        Biglietto b1 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe1);
        Biglietto b2 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe2);
        Biglietto b3 = new Biglietto(m2, getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe1);
        Biglietto b4 = new Biglietto(m2, getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe3);
        Biglietto b5 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe1);
        Biglietto b6 = new Biglietto(m1, getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), pe4);
        gvDao.save(b1);
        gvDao.save(b2);
        gvDao.save(b3);
        gvDao.save(b4);
        gvDao.save(b5);
        gvDao.save(b6);

        Abbonamento a1 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2024, 6, 19), pe2, tu1);
        Abbonamento a2 = new Abbonamento(TipoAbbonamento.SETTIMANALE, LocalDate.of(2025, 10, 19), pe1, tu1);
        Abbonamento a3 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2024, 9, 19), pe4, tu2);
        Abbonamento a4 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2025, 9, 19), pe2, tu3);
        Abbonamento a5 = new Abbonamento(TipoAbbonamento.SETTIMANALE, LocalDate.of(2025, 5, 19), pe1, tu4);
        Abbonamento a6 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2025, 9, 29), pe3, tu5);
        gvDao.save(a1);
        gvDao.save(a2);
        gvDao.save(a3);
        gvDao.save(a4);
        gvDao.save(a5);
        gvDao.save(a6);

        TrattaDAO tDao = new TrattaDAO(em);
        Tratta t1 = new Tratta(r.nextInt(180), "Padova", "Verona");
        Tratta t2 = new Tratta(r.nextInt(180), "Bologna", "Ferrara");
        Tratta t3 = new Tratta(r.nextInt(180), "Roma centro", "Roma termini");
        Tratta t4 = new Tratta(r.nextInt(180), "Napoli Garibaldi", "Fuorigrotta");
        Tratta t5 = new Tratta(r.nextInt(180), "Roma Termini", "Colle Mattia");
        Tratta t6 = new Tratta(r.nextInt(180), "Padova", "Colli Euganei");
        tDao.save(t1);
        tDao.save(t2);
        tDao.save(t3);
        tDao.save(t4);
        tDao.save(t5);
        tDao.save(t6);

        MezzoTrattaDAO mtDao = new MezzoTrattaDAO(em);
        MezzoTratta mt1 = new MezzoTratta(LocalTime.of(8, 20, 0), LocalTime.of(9,30, 0), t1, m2);
        MezzoTratta mt2 = new MezzoTratta(LocalTime.of(12, 30, 0), LocalTime.of(15,30, 0), t3, m4);
        MezzoTratta mt3 = new MezzoTratta(LocalTime.of(9, 36, 0), LocalTime.of(9,59, 0), t2, m3);
        MezzoTratta mt4 = new MezzoTratta(LocalTime.of(15, 20, 0), LocalTime.of(19,55, 0), t4, m1);
        MezzoTratta mt5 = new MezzoTratta(LocalTime.of(16, 20, 0), LocalTime.of(18,40, 0), t6, m2);
        mtDao.save(mt1);
        mtDao.save(mt2);
        mtDao.save(mt3);
        mtDao.save(mt4);
        mtDao.save(mt5);

        StatoMezzoDAO smDao = new StatoMezzoDAO(em);
        StatoMezzo sm1 = new StatoMezzo(m1, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2021, 11, 30), LocalDate.of(2022, 1, 30), "Ruote tagliate");
        StatoMezzo sm2 = new StatoMezzo(m2, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2024, 11, 30), null);
        StatoMezzo sm3 = new StatoMezzo(m4, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2022, 11, 30), null);
        StatoMezzo sm4 = new StatoMezzo(m1, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2025, 11, 30), null, "Motore scoppiato");
        StatoMezzo sm5 = new StatoMezzo(m3, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2021, 11, 30), LocalDate.of(2023, 1, 30), "Posti da sostituire");
        StatoMezzo sm6 = new StatoMezzo(m3, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 1, 31), null);
        smDao.save(sm1);
        smDao.save(sm2);
        smDao.save(sm3);
        smDao.save(sm4);
        smDao.save(sm5);
        smDao.save(sm6);
    }

    public static LocalDate getRandomDate(LocalDate start, LocalDate end) {
        long startEpochDay = start.toEpochDay();
        long endtEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endtEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
