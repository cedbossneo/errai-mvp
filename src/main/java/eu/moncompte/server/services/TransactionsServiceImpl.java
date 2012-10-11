package eu.moncompte.server.services;

import eu.moncompte.beans.TransactionBean;
import eu.moncompte.model.Transaction;
import eu.moncompte.server.session.SessionManager;
import eu.moncompte.shared.service.TransactionsService;
import org.jboss.errai.bus.server.annotations.Service;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/29/12
 * Time: 4:59 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class TransactionsServiceImpl implements TransactionsService {
    @EJB
    TransactionBean transactionBean;

    @Inject
    SessionManager sessionManager;

    @Override
    public List<Transaction> listTransactions() {
        return transactionBean.getTransactionsForUser(sessionManager.getSession().getUser());
    }

    @Override
    public Transaction newTransaction() {
        return transactionBean.newTransaction(sessionManager.getSession().getUser());
    }

    @Override
    public void removeTransaction(Transaction Transaction) {
        transactionBean.removeTransaction(sessionManager.getSession().getUser(), Transaction.getId());
    }

    @Override
    public void updateTransaction(Transaction Transaction) {
        transactionBean.updateTransaction(sessionManager.getSession().getUser(), Transaction);
    }
}
