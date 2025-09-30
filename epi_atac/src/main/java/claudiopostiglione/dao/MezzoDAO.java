package claudiopostiglione.dao;

import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.Mezzo;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em){
        this.em = em;
    }

    public void save (Mezzo mezzo){

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();
    System.out.println("Mezzo funzionante... Per ora :)");
    }

    public Mezzo findMezzoById(UUID id){
        Mezzo found = em.find(Mezzo.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }
}
