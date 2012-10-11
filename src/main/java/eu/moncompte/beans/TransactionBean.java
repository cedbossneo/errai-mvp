package eu.moncompte.beans;

import eu.moncompte.model.Transaction;
import eu.moncompte.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: hauber_c
 * Date: 12/07/12
 * Time: 12:01
 */
@Stateless
@LocalBean
public class TransactionBean {
    @PersistenceContext
    EntityManager entityManager;

    public List<Transaction> getTransactionsForUserWithRange(User user, Integer offset, Integer limit){
        return entityManager.createQuery("SELECT t FROM Transaction t WHERE t.user = :user").setParameter("user", user).setMaxResults(limit).setFirstResult(offset).getResultList();
    }

    public List<Transaction> getTransactionsForUser(User user) {
        return entityManager.createQuery("SELECT t FROM Transaction t WHERE t.user = :user").setParameter("user", user).getResultList();
    }

    public Long getTrasactionsCountForUser(User user){
        return (Long) entityManager.createQuery("SELECT COUNT(t) FROM Transaction t WHERE t.user = :user").setParameter("user", user).getSingleResult();
    }

    public void removeTransaction(User user, Integer id) {
        entityManager.createQuery("DELETE FROM Transaction t WHERE t.user = :user AND t.id = :id").setParameter("user", user).setParameter("id", id).executeUpdate();
    }

    public void updateTransaction(User user, Transaction transaction) {
        if (entityManager.find(Transaction.class, transaction.getId()).getUser().getId().equals(user.getId())){
            entityManager.merge(transaction);
        }
    }

    public Transaction newTransaction(User user){
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setDate(new java.util.Date());
        transaction.setPrice(0f);
        entityManager.persist(transaction);
        return transaction;
    }
}
