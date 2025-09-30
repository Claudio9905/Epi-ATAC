package claudiopostiglione.dao;

import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.TesseraUtente;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

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
        System.out.println("Tessera salvata! :)");
    }

    public TesseraUtente findTesseraUtenteById(UUID id){
        TesseraUtente found = em.find(TesseraUtente.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }
}
