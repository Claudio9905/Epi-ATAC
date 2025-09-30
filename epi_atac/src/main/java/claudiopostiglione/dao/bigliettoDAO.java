package claudiopostiglione.dao;

import claudiopostiglione.entities.Biglietto;
import jakarta.persistence.*;

public class BigliettoDAO {

    private final EntityManager em;

    public BigliettoDAO(EntityManager em){
        this.em = em;
    }

    public void save (Biglietto biglietto){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Biglietto comprato!");
    }
}