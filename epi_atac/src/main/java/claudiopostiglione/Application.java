package claudiopostiglione;

import claudiopostiglione.dao.*;
import claudiopostiglione.entities.*;
import claudiopostiglione.exceptions.emailUserNotFoundException;
import claudiopostiglione.dao.GestioneVenditaDAO;
import claudiopostiglione.dao.MezzoDAO;
import claudiopostiglione.dao.TesseraUtenteDAO;
import claudiopostiglione.entities.Abbonamento;
import claudiopostiglione.entities.Mezzo;
import claudiopostiglione.entities.TesseraUtente;
import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class Application {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("epi_atac");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        Faker f = new Faker();
        Random r = new Random();

        //Oggetti DAO per interaggire con il DB
        UtenteDAO uDao = new UtenteDAO(em);
        TesseraUtenteDAO td = new TesseraUtenteDAO(em);
        PuntoEmissioneDAO ped = new PuntoEmissioneDAO(em);

        // Creazione oggetto Utente
        Supplier<Utente> utenteSupplier = () -> {
            boolean rdnBoolean = r.nextBoolean();
            return new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), getRandomDate(LocalDate.of(1945, 1, 1), LocalDate.now()), rdnBoolean);
        };

        for (int i = 0; i < 10; i++) {
            uDao.save(utenteSupplier.get());
        }


        //Creazione oggetto tesseraUtente
        Supplier<TesseraUtente> tesseraUtenteSupplier = () -> {
            LocalDate dataTessera = getRandomDate(LocalDate.of(2015, 1, 1), LocalDate.now());

            List<Long> longUsciti = new ArrayList<>();
            long num;
            while (true) {
                num = r.nextLong(1, uDao.findNumeroUtenti() + 1);
                System.out.println(num);
                if (!longUsciti.contains(num)) {
                    longUsciti.add(num);
                    break;
                }
            }

            return new TesseraUtente(dataTessera, uDao.findUtenteById(num));
        };

        for (int i = 0; i < uDao.findNumeroUtenti(); i++) {
            td.save(tesseraUtenteSupplier.get());
        }

        //Creazione oggetto DistributoreAutomatico
        Supplier<DistributoreAutomatico> distributoreAutomaticoSupplier = () -> new DistributoreAutomatico(f.address().cityName(),r.nextBoolean());

        for(int i = 0; i < 10; i++){
            ped.save(distributoreAutomaticoSupplier.get());
        }

//        //Creazione oggetto NegozioRivenditore
//        Supplier<NegozioRivenditore> negozioRivenditoreSupplier = () ->{
//            return new NegozioRivenditore(f.address().cityName(),f.company().name(),LocalTime.of(9,9),LocalTime.of(18,0));
//        };
//
//
//        //Creazione oggetto abbonamento
//        Supplier<Abbonamento> abbonamentoSupplier = () -> {
//
//            String[] tipoAbbonamento = {"MENSILE", "SETTIMANALE"};
//            TipoAbbonamento tipo = TipoAbbonamento.valueOf(tipoAbbonamento[r.nextInt(0,2)]);
//
//                return new Abbonamento(tipo, getRandomDate(LocalDate.of(2024,1,1), LocalDate.now()),)
//        };


        System.out.println("Connessione al database riuscita!");


//        UtenteDAO uDao = new UtenteDAO(em);
//
//        Utente u1 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1991, 1, 1), false);
//        Utente u2 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1992, 1, 1), false);
//        Utente u3 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1993, 1, 1), false);
//        Utente u4 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1994, 1, 1), false);
//        Utente u5 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(2000, 1, 1), false);
//        Utente u6 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1995, 1, 1), true);
//        Utente u7 = new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), LocalDate.of(1996, 1, 1), true);
//
//        uDao.save(u1);
//        uDao.save(u2);
//        uDao.save(u3);
//        uDao.save(u4);
//        uDao.save(u5);
//        uDao.save(u6);
//        uDao.save(u7);
//
        TesseraUtenteDAO tuDao = new TesseraUtenteDAO(em);
//        TesseraUtente tu1 = new TesseraUtente(LocalDate.of(2020, 5, 18), u1);
//        TesseraUtente tu2 = new TesseraUtente(LocalDate.of(2023, 10, 20), u2);
//        TesseraUtente tu3 = new TesseraUtente(LocalDate.of(2025, 1, 20), u3);
//        TesseraUtente tu4 = new TesseraUtente(LocalDate.of(2025, 8, 31), u4);
//        TesseraUtente tu5 = new TesseraUtente(LocalDate.of(2021, 10, 12), u5);
//        tuDao.save(tu1);
//        tuDao.save(tu2);
//        tuDao.save(tu3);
//        tuDao.save(tu4);
//        tuDao.save(tu5);
//
//        MezzoDAO mDao = new MezzoDAO(em);
//        Mezzo m1 = new Mezzo(TipoMezzo.AUTOBUS, 80);
//        Mezzo m2 = new Mezzo(TipoMezzo.TRAM, 50);
//        Mezzo m3 = new Mezzo(TipoMezzo.AUTOBUS, 65);
//        Mezzo m4 = new Mezzo(TipoMezzo.TRAM, 40);
//        Mezzo m5 = new Mezzo(TipoMezzo.AUTOBUS, 120);
//        mDao.save(m1);
//        mDao.save(m2);
//        mDao.save(m3);
//        mDao.save(m4);
//        mDao.save(m5);
//
//        PuntoEmissioneDAO peDao = new PuntoEmissioneDAO(em);
//
//        NegozioRivenditore Negozio1 = new NegozioRivenditore("Napoli", "Claudio", "BigliettiMatti", LocalTime.of(8, 30), LocalTime.of(20, 30));
//        NegozioRivenditore Negozio2 = new NegozioRivenditore("Padova", "Cristina", "BigliettiMatti", LocalTime.of(12, 30), LocalTime.of(18, 30));
//        NegozioRivenditore Negozio3 = new NegozioRivenditore("Monte Compatri", "Mattia", "BigliettiMatti", LocalTime.of(8, 30), LocalTime.of(9, 30));
//        NegozioRivenditore Negozio4 = new NegozioRivenditore("Pavia", "Thomas", "BigliettiMatti", LocalTime.of(11, 30), LocalTime.of(19, 30));
//
//        peDao.save(Negozio1);
//        peDao.save(Negozio2);
//        peDao.save(Negozio3);
//        peDao.save(Negozio4);
//
//        DistributoreAutomatico Distributore1 = new DistributoreAutomatico("Napoli", false);
//        DistributoreAutomatico Distributore2 = new DistributoreAutomatico("Padova", false);
//        DistributoreAutomatico Distributore3 = new DistributoreAutomatico("Pavia", false);
//        DistributoreAutomatico Distributore4 = new DistributoreAutomatico("Pavia di Udine", true);
//        DistributoreAutomatico Distributore5 = new DistributoreAutomatico("Roma", false);
//
//        peDao.save(Distributore1);
//        peDao.save(Distributore2);
//        peDao.save(Distributore3);
//        peDao.save(Distributore4);
//        peDao.save(Distributore5);
//
//                LocalDate ld = LocalDate.of(r.nextInt(2023, 2026), r.nextInt(1, 13), r.nextInt(1, 32));
        GestioneVenditaDAO gvDao = new GestioneVenditaDAO(em);
//        Biglietto b1 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), Distributore4,TipoMezzo.AUTOBUS);
//        Biglietto b2 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), Distributore4,TipoMezzo.AUTOBUS);
//        Biglietto b3 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), Negozio1,TipoMezzo.AUTOBUS);
//        Biglietto b4 = new Biglietto(getRandomDate(LocalDate.now().minusYears(2), LocalDate.now()), Negozio3, TipoMezzo.AUTOBUS);
//        Biglietto b5 = new Biglietto(LocalDate.of(2024, 11, 23), Negozio2, getRandomDate(LocalDate.of(2024, 11, 23), LocalDate.now()),TipoMezzo.AUTOBUS, m2);
//        Biglietto b6 = new Biglietto(LocalDate.of(2023, 11, 23), Negozio4, getRandomDate(LocalDate.of(2023, 11, 23), LocalDate.now()),TipoMezzo.TRAM, m1);
//
//        gvDao.save(b1);
//        gvDao.save(b2);
//        gvDao.save(b3);
//        gvDao.save(b4);
//        gvDao.save(b5);
//        gvDao.save(b6);
//
//        Abbonamento a1 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2024, 6, 19), Distributore1, tu1, TipoMezzo.AUTOBUS);
//        Abbonamento a2 = new Abbonamento(TipoAbbonamento.SETTIMANALE, LocalDate.of(2025, 10, 19), Distributore4, tu1, TipoMezzo.AUTOBUS);
//        Abbonamento a3 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2024, 9, 19), Negozio1, tu2, TipoMezzo.TRAM);
//        Abbonamento a4 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2025, 9, 19), Negozio2, tu3, TipoMezzo.TRAM);
//        Abbonamento a5 = new Abbonamento(TipoAbbonamento.SETTIMANALE, LocalDate.of(2025, 5, 19), Distributore4, tu4, TipoMezzo.TRAM);
//        Abbonamento a6 = new Abbonamento(TipoAbbonamento.MENSILE, LocalDate.of(2025, 9, 29), Negozio2, tu5, TipoMezzo.AUTOBUS);
//        gvDao.save(a1);
//        gvDao.save(a2);
//        gvDao.save(a3);
//        gvDao.save(a4);
//        gvDao.save(a5);
//        gvDao.save(a6);
//
//        TrattaDAO tDao = new TrattaDAO(em);
//        Tratta t1 = new Tratta(r.nextInt(180), "Padova", "Verona");
//        Tratta t2 = new Tratta(r.nextInt(180), "Bologna", "Ferrara");
//        Tratta t3 = new Tratta(r.nextInt(180), "Roma centro", "Roma termini");
//        Tratta t4 = new Tratta(r.nextInt(180), "Napoli Garibaldi", "Fuorigrotta");
//        Tratta t5 = new Tratta(r.nextInt(180), "Roma Termini", "Colle Mattia");
//        Tratta t6 = new Tratta(r.nextInt(180), "Padova", "Colli Euganei");
//        tDao.save(t1);
//        tDao.save(t2);
//        tDao.save(t3);
//        tDao.save(t4);
//        tDao.save(t5);
//        tDao.save(t6);
//
//        MezzoTrattaDAO mtDao = new MezzoTrattaDAO(em);
//        MezzoTratta mt1 = new MezzoTratta(LocalTime.of(8, 20, 0), LocalTime.of(9, 30, 0), t1, m2);
//        MezzoTratta mt2 = new MezzoTratta(LocalTime.of(12, 30, 0), LocalTime.of(15, 30, 0), t3, m4);
//        MezzoTratta mt3 = new MezzoTratta(LocalTime.of(9, 36, 0), LocalTime.of(9, 59, 0), t2, m3);
//        MezzoTratta mt4 = new MezzoTratta(LocalTime.of(15, 20, 0), LocalTime.of(19, 55, 0), t4, m1);
//        MezzoTratta mt5 = new MezzoTratta(LocalTime.of(16, 20, 0), LocalTime.of(18, 40, 0), t6, m2);
//        mtDao.save(mt1);
//        mtDao.save(mt2);
//        mtDao.save(mt3);
//        mtDao.save(mt4);
//        mtDao.save(mt5);
//
//        StatoMezzoDAO smDao = new StatoMezzoDAO(em);
//        StatoMezzo sm1 = new StatoMezzo(m1, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2021, 11, 30), LocalDate.of(2022, 1, 30), "Ruote tagliate");
//        StatoMezzo sm2 = new StatoMezzo(m2, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2024, 11, 30), null);
//        StatoMezzo sm3 = new StatoMezzo(m4, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2022, 11, 30), null);
//        StatoMezzo sm4 = new StatoMezzo(m1, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2025, 11, 30), null, "Motore scoppiato");
//        StatoMezzo sm5 = new StatoMezzo(m3, TipoStatoMezzo.IN_MATUTENZIONE, LocalDate.of(2021, 11, 30), LocalDate.of(2023, 1, 30), "Posti da sostituire");
//        StatoMezzo sm6 = new StatoMezzo(m3, TipoStatoMezzo.IN_SERVIZIO, LocalDate.of(2023, 1, 31), null);
//        smDao.save(sm1);
//        smDao.save(sm2);
//        smDao.save(sm3);
//        smDao.save(sm4);
//        smDao.save(sm5);
//        smDao.save(sm6);


//        System.out.println(gvDao.totalSoldTickcetsAndSubsInAPeriod(LocalDate.of(2026, 7, 20), LocalDate.of(2026,10, 31)));

//        System.out.println(gvDao.totalSoldTicketsInSalePlace("25d38f55-1c5c-453c-9da6-a0ea24df1089"));


        //-----------------------------------------------------------------------------------------------------------

        System.out.println("|---               |----------|                                 |------------------------------------|                              ---|");
        System.out.println("||---------------- | EPI-ATAC | --------------------------------||Home|| / ||About Us|| / ||Services|| /------------------------------||");
        System.out.println("|---               |----------|                                 |------------------------------------|                              ---|");
        System.out.println("\n");

        // Qui verranno mostrate le tratte disponibili
        System.out.println("|- Sezione Tratte -|");
        System.out.println("|--------------------------------");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|");
        System.out.println("|--------------------------------");

        System.out.println("|- Fase Login -|");
        System.out.println("| Salve, benvenuto ad EPI-ATAC, prego, identificarsi come utente o amministratore: |--(1 Utente) / (2 Amministratore)--| ");
        int scelta = Integer.parseInt(scanner.nextLine());

        switch (scelta) {

            case 1:
                System.out.println("Sei già registrato? |--(1 - SI) / (2 - NO)--|");
                scelta = Integer.parseInt(scanner.nextLine());
                if (scelta == 1) {
                    try {
                        System.out.println("| Inserisci le credenziali: --( E-mail )--");
                        String emailUtente = scanner.nextLine();
                        Utente utenteFound = uDao.findUtenteByEmail(emailUtente);
                        utenteRegistrato(utenteFound);
                    } catch (emailUserNotFoundException er) {
                        System.out.println(er.getMessage());
                    }

                } else {
                    //utenteNonRegistrato();
                }
        }


        System.out.println(isTheSubValid("b9b39507-1522-4da7-87ff-13ed178ceb3a", "10b72c5d-3767-4210-8cde-913ed88f4012", em));
        //System.out.println(isTheSubValid("b9b39507-1522-4da7-87ff-13ed178ceb3a", "10b72c5d-3767-4210-8cde-913ed88f4012", em));

//        obliteratiPerMezzo("33ace2d7-6ed5-4dba-94d8-5b5e7f77d130", em);

//        gvDao.totalSoldTicketsInAPeriod(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 20));
        tuDao.renewTessera(1);
    }

    public static LocalDate getRandomDate(LocalDate start, LocalDate end) {
        long startEpochDay = start.toEpochDay();
        long endtEpochDay = end.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endtEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static void utenteRegistrato(Utente utente) {

        Scanner scanner = new Scanner(System.in);
        int scelta;

        do {
            System.out.println("| Ciao " + utente.getNome());
            System.out.println("| Queste sono le opzioni disponibili:");
            System.out.println("|  - Acquista biglietto (1) ");
            System.out.println("|  - Acquistare abbonamento (2) ");
            System.out.println("|  - Usare abbonamento (3) ");
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    // Bisogna creare un nuovo biglietto, con mezzo e tratta, tutti da salvare nel database
                    break;
                case 2:
                    // Creazione di un abbonamento, stabilendo anche la tessera utente
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Attenzione, scelta non disponibile, prego, riprovare");
            }
        } while (scelta != 0);


    }

    public static void utenteNonRegistrato() {
        System.out.println("");
    }

    
    
    public static boolean isTheSubValid(String idAbbonamento, long idTessera, EntityManager em) {
        try {
            TesseraUtenteDAO tuDao = new TesseraUtenteDAO(em);
            TesseraUtente tu = tuDao.findTesseraUtenteById(idTessera);

            UUID idAbbonamentoUuid = UUID.fromString(idAbbonamento);

            List<Abbonamento> abbonamenti = tu.getAbbonamento();
            Abbonamento abbCercato = abbonamenti.stream().filter(a ->
                    a.getId().equals(idAbbonamentoUuid)
            ).toList().getFirst();
            if (abbCercato.getDataScadenza().isAfter(LocalDate.now())) {
                System.out.println("L'abbonamento è valido!");
            } else {
                System.out.println("L'abbonamento non è valido");
            }
            return abbCercato.getDataScadenza().isAfter(LocalDate.now());
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public static int obliteratiPerMezzo(String id, EntityManager em) {
        UUID mezzoid = UUID.fromString(id);

        MezzoDAO mezzoOB = new MezzoDAO(em);
        Mezzo mezzo = mezzoOB.findMezzoById(mezzoid);
        int numeroBigliettiTrovati = mezzo.getListaBiglietti().size();
        if (mezzo.getListaBiglietti().isEmpty()) {
            System.out.println("Non trovati");
            System.out.println("(╯°□°）╯︵ ┻━┻");
        } else {
            System.out.println("Trovati n: " + numeroBigliettiTrovati);
            System.out.println("(✿◠‿◠)");
        }
        return mezzo.getListaBiglietti().size();
    }


}
