package claudiopostiglione.dao;

import claudiopostiglione.entities.PuntoEmissione;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class PuntoEmissioneDAO {
    private final EntityManager em;

    public PuntoEmissioneDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PuntoEmissione puntoEmissione) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(puntoEmissione);
        transaction.commit();
        System.out.println("Punto di emissione convenzionato! :) ");
    }

    public PuntoEmissione findPuntoEmissioneById(UUID id) {
        try {
            PuntoEmissione found = em.find(PuntoEmissione.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<PuntoEmissione> findAllPuntoEmissione() {
        try {
            TypedQuery<PuntoEmissione> query = em.createQuery("SELECT pe FROM PuntoEmissione pe", PuntoEmissione.class);
            List<PuntoEmissione> found = query.getResultList();

            // Stampa ogni UUID su una nuova riga
//            for (PuntoEmissione pe : found) {
//                System.out.println(pe);
//            }
            if (found.isEmpty()) System.out.println("Non sono stati trovati punti di emissione.");
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}