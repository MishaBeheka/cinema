package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.DbFileDao;
import com.dev.cinema.exceptoin.DataProcessingException;
import com.dev.cinema.model.DbFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class DbFileDaoImpl implements DbFileDao {

    private static final Logger LOGGER = LogManager.getLogger(DbFileDaoImpl.class);

    private final SessionFactory sessionFactory;

    public DbFileDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public DbFile add(DbFile dbFile) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long rowId = (Long) session.save(dbFile);
            transaction.commit();
            dbFile.setId(rowId);
            return dbFile;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.info("Can't insert DBFile entity");
            throw new DataProcessingException("Can't insert DBFile entity", e);
        }
    }

    @Override
    public DbFile getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DbFile.class, id);
        } catch (Exception e) {
            LOGGER.info("Can't find DBFile with ID " + id);
            throw new DataProcessingException("Can't find DBFile with ID " + id, e);
        }
    }
}
