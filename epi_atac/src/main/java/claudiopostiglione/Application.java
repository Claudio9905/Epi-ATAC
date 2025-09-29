package claudiopostiglione;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("epi_atac");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();


        System.out.println("Connessione al database riuscita!");
    }
}
