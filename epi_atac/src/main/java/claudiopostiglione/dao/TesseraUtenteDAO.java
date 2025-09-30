package claudiopostiglione.dao;

import claudiopostiglione.entities.TesseraUtente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TesseraUtenteDAO {
    private final EntityManager em;

    public TesseraUtenteDAO(EntityManager em){
        this.em = em;
    }

    public void save (TesseraUtente tessera){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tessera);
        transaction.commit();
        System.out.println("Tessera salvata!");
    }
}
