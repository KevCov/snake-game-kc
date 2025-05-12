package persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Persistence;
import model.Record;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecordDAO {
    private static EntityManagerFactory emf;

    public RecordDAO() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("game-persistence-unit");
        }
    }

    public String persist(Record record) {
        String msg = "";
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                em.persist(record);
                em.getTransaction().commit();

                msg = "Se registro su puntaje";
            } catch (Exception e) {
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();

                msg = "Error al registrar el puntaje";
                e.printStackTrace();
            }
        } catch (Exception e) {
            msg = "Error creando el EntityManager";
            e.printStackTrace();
        }

        return msg;
    }

    public List<Record> findAll() {
        List<Record> records = Collections.emptyList();
        try (EntityManager em = emf.createEntityManager()) {
            records = em.createQuery("SELECT r FROM Record r", Record.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }

    public List<Long> getIds() {
        List<Long> ids = Collections.emptyList();
        try (EntityManager em = emf.createEntityManager()) {
            ids = em.createQuery("SELECT r.id FROM Record r", Long.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ids;
    }

    public String remove(Long id) {
        String msg = "";
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            try {
                Record record = em.find(Record.class, id);
                if (record != null) {
                    em.remove(record);
                    em.getTransaction().commit();

                    msg = "Se elimino el registro con exito";
                } else {
                    msg = "No se encontro el registro";
                }
            } catch (Exception e) {
                if (em.getTransaction().isActive())
                    em.getTransaction().rollback();

                msg = "Error al eliminar el registro";
                e.printStackTrace();
            }
        } catch (Exception e) {
            msg = "Error al crear el EntityManager";
            e.printStackTrace();
        }

        return msg;
    }

    public static void closeEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("EntityManagerFactory cerrada.");
        }
    }
}
