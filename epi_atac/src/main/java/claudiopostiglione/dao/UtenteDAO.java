package claudiopostiglione.dao;

import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.entities.Utente;
import claudiopostiglione.exceptions.IdNotFoundException;
import claudiopostiglione.exceptions.emailUserNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

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

    public Utente findUtenteById(long id){
        Utente found = em.find(Utente.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }

    public Utente findUtenteByEmail(String email){
        Utente found = em.find(Utente.class, email);
        if(found==null) throw new emailUserNotFoundException(email);
        return found;
    }

    public long findNumeroUtenti(){
        TypedQuery<Utente> query = em.createQuery("SELECT COUNT(ut) FROM Utente ut", Utente.class);
        long found = query.getFirstResult();
        if(found == 0){
            System.out.println("Nessun utente creato");
        }
        return found;
    }
}
