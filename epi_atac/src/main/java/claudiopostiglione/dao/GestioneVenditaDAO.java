package claudiopostiglione.dao;

import claudiopostiglione.entities.Biglietto;
import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.Mezzo;
import claudiopostiglione.entities.PuntoEmissione;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class GestioneVenditaDAO {

    private final EntityManager em;

    public GestioneVenditaDAO(EntityManager em){
        this.em = em;
    }

    public void save (GestioneVendita biglietto){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Acquisto completato! =ˆ.ˆ=");
    }

    public GestioneVendita findGestioneVenditaById(UUID id){
        GestioneVendita found = em.find(GestioneVendita.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }

    public int totalSoldTickcetsAndSubsInAPeriod(LocalDate startPeriod, LocalDate endPeriod) {
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
    }

    public int totalSoldTicketsInSalePlace(String id) {
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
    }

//    public boolean isTheSubValid(String idAbbonamento, String idTessera) {
//        TypedQuery<GestioneVendita> query = em.createQuery("SELECT a FROM Abbonamento a WHERE tessera = :tessera AND id = :idTessera", GestioneVendita.class);
//        query.setParameter("mezzo", mezzo);
//        int found = query.getResultList().toArray().length;
//        if (found != 0) {
//            System.out.println("Sono stati venduti dei biglietti nel mezzo con id " + id);
//            System.out.println("(✿◠‿◠)");
//        } else {
//            System.out.println("Non sono stati venduti biglietti nel mezzo con id " + id);
//            System.out.println("(╯°□°）╯︵ ┻━┻");
//        }
//        return found;
//    }
}