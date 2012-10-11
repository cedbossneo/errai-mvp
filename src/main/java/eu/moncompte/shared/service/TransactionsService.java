package eu.moncompte.shared.service;
import eu.moncompte.model.Transaction;
import org.jboss.errai.bus.server.annotations.Remote;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Cedric
 * Date: 9/29/12
 * Time: 4:33 AM
 * To change this template use File | Settings | File Templates.
 */
@Remote
public interface TransactionsService {
    public List<Transaction> listTransactions();
    public Transaction newTransaction();
    public void removeTransaction(Transaction Transaction);
    public void updateTransaction(Transaction Transaction);
}
