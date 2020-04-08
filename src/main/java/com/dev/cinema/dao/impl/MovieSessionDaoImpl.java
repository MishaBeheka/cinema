package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.MovieSessionDao;
import com.dev.cinema.exceptoin.DataProcessingException;
import com.dev.cinema.model.MovieSession;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {

    private final SessionFactory sessionFactory;

    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Long itemId = (Long) session.save(movieSession);
            transaction.commit();
            movieSession.setId(itemId);
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert MovieSession entity ", e);
        }
    }

    @Override
    public MovieSession getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MovieSession.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't find MovieSession with ID " + id, e);
        }
    }

    @Override
    public List<MovieSession> findAvailableSession(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<MovieSession> query = session.createQuery(
                    "FROM MovieSession WHERE movie.id = :movieId"
                            + " AND year(showTime) = :year AND month(showTime) = :month"
                            + " AND day(showTime) = :day");
            query.setParameter("movieId", movieId);
            query.setParameter("year", date.getYear());
            query.setParameter("month", date.getMonthValue());
            query.setParameter("day", date.getDayOfMonth());
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Error retrieving all MovieSessions ", e);
        }
    }
}
