package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long rowId = (Long) session.save(user);
            transaction.commit();
            user.setId(rowId);
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert User entity ", e);
        }
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }
}
