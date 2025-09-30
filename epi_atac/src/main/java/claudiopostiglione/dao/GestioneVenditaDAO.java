package claudiopostiglione.dao;

import claudiopostiglione.entities.Biglietto;
import claudiopostiglione.entities.GestioneVendita;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.*;

import java.util.UUID;

public class GestioneVenditaDAO {

    private final EntityManager em;

    public GestioneVenditaDAO(EntityManager em){
        this.em = em;
    }

    public void save (GestioneVendita biglietto){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("Acquisto completato!");
    }

    public GestioneVendita findGestioneVenditaById(UUID id){
        GestioneVendita found = em.find(GestioneVendita.class, id);
        if(found == null) throw new IdNotFoundException(id);
        return found;
    }
}