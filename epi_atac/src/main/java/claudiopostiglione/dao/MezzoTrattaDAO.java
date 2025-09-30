package claudiopostiglione.dao;

import claudiopostiglione.entities.MezzoTratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezzoTrattaDAO {
    private final EntityManager em;

    public MezzoTrattaDAO(EntityManager em){
        this.em = em;
    }

    public void save (MezzoTratta mezzoTratta){

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzoTratta);
        transaction.commit();
        System.out.println(" Percorrenza del mezzo registrata!");
    }
}
