package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.ShoppingCartDao;
import com.dev.cinema.exceptoin.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.util.HibernateUtil;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long rowId = (Long) session.save(shoppingCart);
            transaction.commit();
            shoppingCart.setId(rowId);
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't create Shopping-Cart ", e);
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            ShoppingCart shoppingCart = session.createQuery(
                    "FROM ShoppingCart WHERE user = :user", ShoppingCart.class)
                    .setParameter("user", user)
                    .uniqueResult();
            List<Ticket> tickets = session.createQuery(
                    "FROM Ticket WHERE user = :user", Ticket.class)
                    .setParameter("user", user)
                    .getResultList();
            shoppingCart.setTickets(tickets);
            return shoppingCart;
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shoppingCart of user ", e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update ShoppingCart ", e);
        }
    }
}