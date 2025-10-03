package claudiopostiglione.dao;

import claudiopostiglione.entities.MezzoTratta;
import claudiopostiglione.entities.Tratta;
import claudiopostiglione.exceptions.IdNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;
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
            if (found.isEmpty()) System.out.println("Non sono state trovate tratte");
            return found;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void tempoMedioPercorrenzaTratta(Tratta tratta) {

        try {

            TypedQuery<Double> query = em.createQuery("SELECT AVG(mt.percorrenzaEffettiva) FROM MezzoTratta mt WHERE mt.tratta = :tratta", Double.class);
            query.setParameter("tratta", tratta);
            Double found = query.getSingleResult();
            if (found == 0) System.out.println("Questa tratta non è mai stata percorsa (ಥ﹏ಥ)");
            else System.out.println("Questa tratta è stata percorsa in media in " + found + " minuti");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

//    public void findAllTratteOfMezzo(String id) {
//        MezzoDAO mezzoDAO = new MezzoDAO(em);
//        try {
//            UUID idUUID = UUID.fromString(id);
////            Mezzo mezzo = mezzoDAO.findMezzoById(idUUID);
//            TypedQuery<Long> query = em.createQuery("SELECT mt.tratta, COUNT(mt.mezzo) FROM MezzoTratta mt WHERE mt.mezzo.id = :id GROUP BY mt.tratta", Long.class);
//            query.setParameter("id", idUUID);
//            List<Long> found = query.getResultList();
//            System.out.println(found);

    /// /            Map<Tratta, MezzoTratta> orderFound = found.stream().sorted(Comparator.comparing(MezzoTratta::getTratta));
    /// /            if (found.isEmpty()) System.out.println("Non sono stati trovate tratte.");
    /// /            else {
    /// /                System.out.println("Il mezzo ha percorso questa tratta " + found.size() + " volte");
    /// /                found.forEach(System.out::println);
    /// /            }
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//
//        }
//
//    }

//3 - Tenere traccia del numero di volte che un mezzo percorre una tratta e del tempo effettivo di percorrenza di esse ");
    public void findAllTratteOfMezzo(String idMezzo, UUID idTratta) {
        try {
            UUID idMezzoUUID = UUID.fromString(idMezzo);
//            UUID idTrattaUUID = UUID.fromString(idTratta);
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(mt.mezzo) FROM MezzoTratta mt WHERE mt.mezzo.id = :idMezzo AND mt.tratta.id = :idTratta", Long.class).setParameter("idMezzo", idMezzoUUID).setParameter("idTratta", idTratta);
            long find = query.getSingleResult();

            // Percorrenza effettiva di ogni mezzo-tratta
            TypedQuery<Long> query2 = em.createQuery("SELECT mt.percorrenzaEffettiva FROM MezzoTratta mt WHERE mt.mezzo.id = :idMezzo AND mt.tratta.id = :idTratta", Long.class).setParameter("idMezzo", idMezzoUUID).setParameter("idTratta", idTratta);
            List<Long> find2 = query2.getResultList();

            if (find == 0) System.out.println("Il mezzo " + idMezzo + " non ha mai percorso la tratta " + idTratta);
            else {
                System.out.println("Il mezzo " + idMezzo + " ha percorso la tratta " + idTratta + " " + find + " volte");
                for (int i = 0; i < find2.size(); i++) {
                    System.out.println("La " + (i + 1) + "a volta che il mezzo ha percorso questa tratta, ci ha impiegato " + find2.get(i) + " min");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
