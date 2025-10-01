package claudiopostiglione.dao;

import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.PuntoEmissione;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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

    public PuntoEmissione findPuntoEmissioneById(UUID id){
        PuntoEmissione found = em.find(PuntoEmissione.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }
}