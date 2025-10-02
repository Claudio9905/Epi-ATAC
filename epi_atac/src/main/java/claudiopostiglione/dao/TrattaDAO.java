package claudiopostiglione.dao;

import claudiopostiglione.entities.PuntoEmissione;
import claudiopostiglione.entities.Tratta;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();
        System.out.println("Tratta salvata! :)");
    }

    public Tratta findTrattaById(UUID id) {
        try {
            Tratta found = em.find(Tratta.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Tratta> findAllTratte() {
        try {
            TypedQuery<Tratta> query = em.createQuery("SELECT t FROM Tratta t", Tratta.class);
            List<Tratta> found = query.getResultList();
            if (found.isEmpty()) System.out.println("Non sono stati trovate tratte.");
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}