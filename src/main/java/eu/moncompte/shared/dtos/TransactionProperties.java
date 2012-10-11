package eu.moncompte.shared.dtos;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import eu.moncompte.model.Category;
import eu.moncompte.model.Transaction;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: hauber_c
 * Date: 12/07/12
 * Time: 11:13
 */
public interface TransactionProperties extends PropertyAccess<Transaction> {
    ModelKeyProvider<Transaction> id();
    ValueProvider<Transaction, String> name();
    ValueProvider<Transaction, Date> date();
    ValueProvider<Transaction, Float> price();
    ValueProvider<Transaction, Category> category();
}
