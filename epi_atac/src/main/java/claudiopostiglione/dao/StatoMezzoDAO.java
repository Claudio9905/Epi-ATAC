package claudiopostiglione.dao;

import claudiopostiglione.entities.StatoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class StatoMezzoDAO {
    private final EntityManager em;

    public StatoMezzoDAO(EntityManager em){
        this.em = em;
    }

    public void save (StatoMezzo statoMezzo){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(statoMezzo);
        transaction.commit();
        System.out.println("Aggiunta allo storico del mezzo! :)");
    }
}
