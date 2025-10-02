package claudiopostiglione.dao;

import claudiopostiglione.entities.Utente;
import claudiopostiglione.exceptions.IdNotFoundException;
import claudiopostiglione.exceptions.emailUserNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class UtenteDAO {

    private EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("Utente registrato");
    }

    public Utente findUtenteById(long id) {
        try {
            Utente found = em.find(Utente.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Utente findUtenteByEmail(String email) {
        Utente found = em.find(Utente.class, email);
        if (found == null) throw new emailUserNotFoundException(email);
        return found;
    }

    public long findNumeroUtenti() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(*) FROM Utente ut", Long.class);
        return query.getSingleResult();
    }
}
