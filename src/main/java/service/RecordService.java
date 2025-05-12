package service;

import model.Record;
import persistence.RecordDAO;

import java.util.List;

public class RecordService {
    private final RecordDAO dao;

    public RecordService() {
        this.dao = new RecordDAO();
    }

    public String createRecord(Record record) {
        return dao.persist(record);
    }

    public List<Record> getAllRecords() {
        return dao.findAll();
    }

    public List<Long> getAllIds() {
        return dao.getIds();
    }

    public String deleteRecord(Long id) {
        return dao.remove(id);
    }
}
