package claudiopostiglione.dao;

import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.Utente;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em){
        this.em = em;
    }

    public void save(Utente utente){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Utente registrato");
    }

    public Utente findUtenteById(UUID id){
        Utente found = em.find(Utente.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }
}
