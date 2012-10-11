package eu.moncompte.client.renderers;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.grid.AggregationNumberSummaryRenderer;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.SummaryType;
import eu.moncompte.model.Transaction;

/**
* Created with IntelliJ IDEA.
* User: cedric
* Date: 07/08/12
* Time: 17:04
* To change this template use File | Settings | File Templates.
*/
public class AggregationCurrencySummaryRenderer extends AggregationNumberSummaryRenderer<Transaction, Number> {
    public AggregationCurrencySummaryRenderer(NumberFormat currencyFormat) {
        super(currencyFormat, new SummaryType.SumSummaryType<Number>());
    }

    @Override
    public SafeHtml render(int colIndex, Grid<Transaction> grid) {
        ValueProvider<? super Transaction, Number> v = grid.getColumnModel().getValueProvider(colIndex);
        Number n = getSummaryType().calculate(grid.getStore().getAll(), v);
        String style = n.doubleValue() < 0 ? "red" : "green";
        SafeHtmlBuilder sb = new SafeHtmlBuilder();
        sb.appendHtmlConstant("<span style='color:" + style + "'>");
        sb.append(SafeHtmlUtils.fromString(getFormat().format(n)));
        sb.appendHtmlConstant("</span>");
        return sb.toSafeHtml();
    }
}
