package claudiopostiglione;

import claudiopostiglione.dao.*;
import claudiopostiglione.entities.*;
import claudiopostiglione.exceptions.IdNotFoundException;
import claudiopostiglione.exceptions.emailUserNotFoundException;
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
import java.time.LocalTime;
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
        GestioneVenditaDAO gd = new GestioneVenditaDAO(em);
        MezzoDAO md = new MezzoDAO(em);
        TrattaDAO trd = new TrattaDAO(em);
        StatoMezzoDAO std = new StatoMezzoDAO(em);

        // Creazione oggetto Utente
        Supplier<Utente> utenteSupplier = () -> {
            boolean rdnBoolean = r.nextBoolean();
            return new Utente(f.name().firstName(), f.name().lastName(), f.internet().emailAddress(), getRandomDate(LocalDate.of(1945, 1, 1), LocalDate.now()), rdnBoolean);
        };

        for (int i = 0; i < 20; i++) {
            uDao.save(utenteSupplier.get());
        }


        //Creazione oggetto tesseraUtente
        List<Long> longUsciti = new ArrayList<>();
        Supplier<TesseraUtente> tesseraUtenteSupplier = () -> {
            LocalDate dataTessera = getRandomDate(LocalDate.of(2015, 1, 1), LocalDate.now());

            long num;
            while (true) {
                num = r.nextLong(1, uDao.findNumeroUtenti() + 1);
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

        //Creazione oggetto Mezzo
        Supplier<Mezzo> mezzoSupplier = () -> {

            String[] tipoMezzo = {"TRAM", "AUTOBUS"};
            TipoMezzo mezzo = TipoMezzo.valueOf(tipoMezzo[r.nextInt(0, 2)]);

            return new Mezzo(mezzo, r.nextInt(0, 95));
        };

        for (int i = 0; i < 20; i++) {
            md.save(mezzoSupplier.get());
        }

        //Creazione oggetto DistributoreAutomatico
        Supplier<DistributoreAutomatico> distributoreAutomaticoSupplier = () -> new DistributoreAutomatico(f.address().cityName(), r.nextBoolean());

        for (int i = 0; i < 10; i++) {
            ped.save(distributoreAutomaticoSupplier.get());
        }

        //Creazione oggetto NegozioRivenditore
        Supplier<NegozioRivenditore> negozioRivenditoreSupplier = () -> new NegozioRivenditore(f.address().cityName(), f.lordOfTheRings().character(), f.company().name(), LocalTime.of(9, 9), LocalTime.of(18, 0));
        for (int i = 0; i < 10; i++) {
            ped.save(negozioRivenditoreSupplier.get());
        }

        //Creazione oggetto Biglietto
        Supplier<Biglietto> bigliettoSupplier = () -> {

            List<Mezzo> listaMezzo = md.findAllMezzo();
            Mezzo mezzo = listaMezzo.get(r.nextInt(0, listaMezzo.size()));

            List<PuntoEmissione> listaPuntiEmissione = ped.findAllPuntoEmissione();
            PuntoEmissione punto = listaPuntiEmissione.get(r.nextInt(0, listaPuntiEmissione.size()));

            boolean rdmBoolean = r.nextBoolean();

            LocalDate dataAcquisto = getRandomDate(LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1));

            if (rdmBoolean) {
                return new Biglietto(dataAcquisto, punto, dataAcquisto.plusDays(r.nextLong(0, 5)), mezzo);
            } else {
                return new Biglietto(getRandomDate(LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1)), punto, mezzo);
            }

        };

        for (int i = 0; i < 20; i++) {
            gd.save(bigliettoSupplier.get());
        }

        //Creazione oggetto abbonamento
        Supplier<Abbonamento> abbonamentoSupplier = () -> {

            String[] tipoAbbonamento = {"MENSILE", "SETTIMANALE"};
            TipoAbbonamento tipo = TipoAbbonamento.valueOf(tipoAbbonamento[r.nextInt(0, 2)]);

            List<PuntoEmissione> listaPuntiEmissione = ped.findAllPuntoEmissione();
            PuntoEmissione punto = listaPuntiEmissione.get(r.nextInt(0, listaPuntiEmissione.size()));

            List<TesseraUtente> listaTesseraUtente = td.findAllTesseraUtente();
            TesseraUtente tessera = listaTesseraUtente.get(r.nextInt(0, listaTesseraUtente.size()));

            return new Abbonamento(tipo, getRandomDate(LocalDate.of(2024, 1, 1), LocalDate.now()), punto, tessera);
        };

        for (int i = 0; i < 20; i++) {
            gd.save(abbonamentoSupplier.get());
        }

        //Creazione oggetto Tratta
        Supplier<Tratta> trattaSupplier = () -> {
            return new Tratta(r.nextInt(45, 120), f.lordOfTheRings().location(), f.lordOfTheRings().location());
        };

        for (int i = 0; i < 10; i++) {
            trd.save(trattaSupplier.get());
        }

        //Creazione oggetto MezzaTratta
        Supplier<MezzoTratta> mezzoTrattaSupplier = () -> {

            int orarioPh = r.nextInt(1, 23);
            int orarioPm = r.nextInt(1, 59);
            LocalTime orarioP = LocalTime.of(orarioPh, orarioPm);

            List<Tratta> listaTratta = trd.findAllTratta();
            Tratta tratta = listaTratta.get(r.nextInt(0, listaTratta.size()));

            List<Mezzo> listaMezzo = md.findAllMezzo();
            Mezzo mezzo = listaMezzo.get(r.nextInt(0, listaMezzo.size()));

            return new MezzoTratta(orarioP, orarioP.plusHours(4), tratta, mezzo);
        };

        //Creazione oggetto StatoMezzo
        Supplier<StatoMezzo> statoMezzoSupplier = () -> {

            List<Mezzo> listaMezzo = md.findAllMezzo();
            Mezzo mezzo = listaMezzo.get(r.nextInt(0, listaMezzo.size()));

            String[] tipoStato = {"IN_SERVIZIO", "IN_MANUTENZIONE"};
            TipoStatoMezzo tipo = TipoStatoMezzo.valueOf(tipoStato[r.nextInt(0, 2)]);

            boolean rdmBoolean = r.nextBoolean();
            LocalDate dataInizio = getRandomDate(LocalDate.of(2024, 1, 1), LocalDate.now());

            if (rdmBoolean) {
                return new StatoMezzo(mezzo, tipo, dataInizio, dataInizio.plusYears(4));
            } else {
                return new StatoMezzo(mezzo, tipo, dataInizio, dataInizio.minusMonths(2), "Guasto al veicolo");
            }

        };

        for(int i = 0; i < 20; i++){
            std.save(statoMezzoSupplier.get());
        }


        System.out.println("Connessione al database riuscita!");

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

        System.out.println("|- Login -|");
        System.out.println("| Salve, benvenuto ad EPI-ATAC, prego, identificarsi come utente o amministratore: |--(1 Utente) / (2 Amministratore) / (0 EXIT) --| ");
        int scelta = Integer.parseInt(scanner.nextLine());

        switch (scelta) {

            case 0:
                break;
            case 1:
                //Sezione utente
                System.out.println("Sei già registrato? |--(1 - SI) / (2 - NO)--|");
                scelta = Integer.parseInt(scanner.nextLine());
                if (scelta == 1) {
                    try {
                        System.out.println("| Inserisci le credenziali: --( ID Utente )--");
                        String ID = scanner.nextLine();
                        Utente utenteFound = uDao.findUtenteById(Long.parseLong(ID));
                        utenteRegistrato(utenteFound);
                    } catch (IdNotFoundException er) {
                        System.out.println(er.getMessage());
                    }

                } else {
                    //utenteNonRegistrato();
                }
                break;
            case 2:
                //Sezione amministratore
                break;
            default:
                System.out.println("Attenzione, scelta non disponibile, prego riprovare");
        }


        // System.out.println(isTheSubValid("b9b39507-1522-4da7-87ff-13ed178ceb3a", "10b72c5d-3767-4210-8cde-913ed88f4012", em));
        //System.out.println(isTheSubValid("b9b39507-1522-4da7-87ff-13ed178ceb3a", "10b72c5d-3767-4210-8cde-913ed88f4012", em));

//        obliteratiPerMezzo("33ace2d7-6ed5-4dba-94d8-5b5e7f77d130", em);

//        gvDao.totalSoldTicketsInAPeriod(LocalDate.of(2023, 9, 18), LocalDate.of(2023, 9, 20));
        //       tuDao.renewTessera(1);

        //peDao.findAllPuntoEmissione();
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
            System.out.println("|  - Acquistare biglietto (1) ");
            System.out.println("|  - Acquistare abbonamento (2) ");
            System.out.println("|  - Usare abbonamento (3) ");
            System.out.println("|  - EXIT (0) ");
            scelta = Integer.parseInt(scanner.nextLine());

            switch (scelta) {
                case 1:
                    // Bisogna creare un nuovo biglietto, con mezzo e tratta, tutti da salvare nel database
                    System.out.println("Mi dispiace per l'inconveniente ma il nostro sistema è attualmente in manutenzione e le tratte disponibili sono solo: ");
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
        int numeroBigliettiTrovati = mezzo.getListaGestioneVendità().size();
        if (mezzo.getListaGestioneVendità().isEmpty()) {
            System.out.println("Non trovati");
            System.out.println("(╯°□°）╯︵ ┻━┻");
        } else {
            System.out.println("Trovati n: " + numeroBigliettiTrovati);
            System.out.println("(✿◠‿◠)");
        }
        return mezzo.getListaGestioneVendità().size();
    }


}
