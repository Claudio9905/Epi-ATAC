package claudiopostiglione.dao;

import claudiopostiglione.entities.TesseraUtente;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;

public class TesseraUtenteDAO {
    private final EntityManager em;

    public TesseraUtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(TesseraUtente tessera) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tessera);
        transaction.commit();
        System.out.println("Tessera salvata! :)");
    }

    public TesseraUtente findTesseraUtenteById(long id) {
        try {
            TesseraUtente found = em.find(TesseraUtente.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void renewTessera(long id) {
        try {
            TesseraUtente tu = findTesseraUtenteById(id);
            System.out.println(tu.isValido());
            if (tu.isValido()) {
                tu.setDataInizio(LocalDate.now());
                tu.setDataScadenza(LocalDate.now().plusYears(1));
                tu.setValido(true);
                save(tu);
                System.out.println("Tessera rinnovata!");
            } else System.out.println("La tessera è valida fino al " + tu.getDataScadenza());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
