package claudiopostiglione.dao;

import claudiopostiglione.entities.PuntoEmissione;
import claudiopostiglione.entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class TrattaDAO {
    private final EntityManager em;

    public TrattaDAO(EntityManager em){
        this.em = em;
    }

    public void save (Tratta tratta){

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();
        System.out.println("Tratta salvata! :)");
    }

    public List<Tratta> findAllTratta() {
        try {
            TypedQuery<Tratta> query = em.createQuery("SELECT pe FROM Tratta pe", Tratta.class);
            List<Tratta> found = query.getResultList();

            // Stampa ogni UUID su una nuova riga
//            for (Tratta id : found) {
//                System.out.println(id);
//            }

            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}