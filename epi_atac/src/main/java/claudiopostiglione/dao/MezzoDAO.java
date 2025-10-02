package claudiopostiglione.dao;

import claudiopostiglione.entities.Mezzo;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Mezzo mezzo) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();
        System.out.println("Mezzo funzionante... Per ora :)");
    }

    public Mezzo findMezzoById(UUID id) {
        try {
            Mezzo found = em.find(Mezzo.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<Mezzo> findAllMezzo() {
        try {
            TypedQuery<Mezzo> query = em.createQuery("SELECT pe FROM Mezzo pe", Mezzo.class);
            List<Mezzo> found = query.getResultList();
            if (found.isEmpty()) System.out.println("Non sono stati trovati mezzi.");
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


}
