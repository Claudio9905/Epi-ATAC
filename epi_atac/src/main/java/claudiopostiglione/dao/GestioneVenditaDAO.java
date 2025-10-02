package claudiopostiglione.dao;

import claudiopostiglione.entities.*;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class GestioneVenditaDAO {

    private final EntityManager em;

    public GestioneVenditaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(GestioneVendita biglietto) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Acquisto completato! =ˆ.ˆ=");
    }

    public GestioneVendita findGestioneVenditaById(UUID id) {
        try {
            GestioneVendita found = em.find(GestioneVendita.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public int totalSoldTickcetsAndSubsInAPeriod(LocalDate startPeriod, LocalDate endPeriod) {
        try {
            TypedQuery<GestioneVendita> query = em.createQuery("SELECT gv FROM GestioneVendita gv WHERE dataAcquisto BETWEEN :startPeriod AND :endPeriod", GestioneVendita.class);
            query.setParameter("startPeriod", startPeriod);
            query.setParameter("endPeriod", endPeriod);
            int found = query.getResultList().toArray().length;
            if (found != 0) {
                System.out.println("Sono stati trovati dei biglietti e/o degli abbonamenti");
                System.out.println("(✿◠‿◠)");
            } else {
                System.out.println("Non sono stati trovati biglietti o abbonamenti venduti tra " + startPeriod + " e " + endPeriod);
                System.out.println("(╯°□°）╯︵ ┻━┻");
            }
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    public int totalSoldTicketsInSalePlace(String id) {
        try {
            // Cerchiamo prima il mezzo
            UUID idUUID = UUID.fromString(id);
            PuntoEmissioneDAO peDao = new PuntoEmissioneDAO(em);
            PuntoEmissione mezzo = peDao.findPuntoEmissioneById(idUUID);

            TypedQuery<GestioneVendita> query = em.createQuery("SELECT gv FROM GestioneVendita gv WHERE puntoEmissione = :mezzo", GestioneVendita.class);
            query.setParameter("mezzo", mezzo);
            int found = query.getResultList().toArray().length;
            if (found != 0) {
                System.out.println("Sono stati venduti dei biglietti nel mezzo con id " + id);
                System.out.println("(✿◠‿◠)");
            } else {
                System.out.println("Non sono stati venduti biglietti nel mezzo con id " + id);
                System.out.println("(╯°□°）╯︵ ┻━┻");
            }
            return found;
        } catch (Exception e) {
            return 0;
        }
    }

    public int totalSoldTicketsInAPeriod(LocalDate startDate, LocalDate endDate) {
        TypedQuery<Biglietto> query = em.createQuery("SELECT b FROM Biglietto b WHERE DataObliterazione BETWEEN :startDate AND :endDate", Biglietto.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        int found = query.getResultList().size();
        if (found == 0) {
            System.out.println("Non sono stati obliterari biglietti tra " + startDate + " e " + endDate);
            System.out.println("(╯°□°）╯︵ ┻━┻");
        } else if (found == 1) {
            System.out.println("È stato obliterato " + found + " biglietto tra " + startDate + " e " + endDate);
            System.out.println("(✿◠‿◠)");
        } else {
            System.out.println("Sono stati obliterati " + found + " biglietti tra " + startDate + " e " + endDate);
            System.out.println("☞ﾟヮﾟ)☞");
        }
        return found;
    }

    public List<Abbonamento> findAbbonamentoByTessera(TesseraUtente tu) {
        try {
            TypedQuery<Abbonamento> query = em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera = :tu AND a.dataScadenza > CURRENT_DATE", Abbonamento.class);
            query.setParameter("tu", tu);
            List<Abbonamento> listAbb = query.getResultList();
            if (listAbb.isEmpty()) System.out.println("Non sono stati trovati abbonamenti connessi a questa tessera");
            return listAbb;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}