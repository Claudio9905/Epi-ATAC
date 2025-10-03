package claudiopostiglione.dao;

import claudiopostiglione.entities.Mezzo;
import claudiopostiglione.entities.MezzoTratta;
import claudiopostiglione.entities.Tratta;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MezzoTrattaDAO {
    private final EntityManager em;

    public MezzoTrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(MezzoTratta mezzoTratta) {

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzoTratta);
        transaction.commit();
        System.out.println(" Percorrenza del mezzo registrata! :)");
    }

    public MezzoTratta findMezzoTrattaById(UUID id) {
        try {
            MezzoTratta found = em.find(MezzoTratta.class, id);
            if (found == null) throw new IdNotFoundException(id);
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public List<MezzoTratta> findAllMezzoTratte() {
        try {
            TypedQuery<MezzoTratta> query = em.createQuery("SELECT mt FROM MezzoTratta mt", MezzoTratta.class);
            List<MezzoTratta> found = query.getResultList();
            if (found.isEmpty()) System.out.println("Non sono stati trovate tratte.");
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void findAllTratteOfMezzo(String id) {
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        try {
            UUID idUUID = UUID.fromString(id);
            Mezzo mezzo = mezzoDAO.findMezzoById(idUUID);
            TypedQuery<Mezzo> query = em.createQuery("SELECT mt.tratta, mt.percorrenzaEffettiva FROM MezzoTratta mt WHERE mt.mezzo = :id GROUP BY mt.tratta ", Mezzo.class);
            query.setParameter("id", mezzo);
            List<Mezzo> found = query.getResultList();
            System.out.println(found);
//            Map<Tratta, MezzoTratta> orderFound = found.stream().sorted(Comparator.comparing(MezzoTratta::getTratta));
//            if (found.isEmpty()) System.out.println("Non sono stati trovate tratte.");
//            else {
//                System.out.println("Il mezzo ha percorso questa tratta " + found.size() + " volte");
//                found.forEach(System.out::println);
//            }
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }

    }
}
