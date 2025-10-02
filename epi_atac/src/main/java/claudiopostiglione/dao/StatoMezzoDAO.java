package claudiopostiglione.dao;

import claudiopostiglione.entities.StatoMezzo;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class StatoMezzoDAO {
    private final EntityManager em;

    public StatoMezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(StatoMezzo statoMezzo) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(statoMezzo);
        transaction.commit();
        System.out.println("Aggiunta allo storico del mezzo! :)");
    }

    public StatoMezzo findStatoMezzoById(UUID id) {
        try {
            StatoMezzo found = em.find(StatoMezzo.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
