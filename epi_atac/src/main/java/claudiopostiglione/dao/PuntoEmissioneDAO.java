package claudiopostiglione.dao;

import claudiopostiglione.entities.PuntoEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}