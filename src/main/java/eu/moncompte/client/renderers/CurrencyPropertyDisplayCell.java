package eu.moncompte.client.renderers;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.PropertyDisplayCell;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

/**
* Created with IntelliJ IDEA.
* User: cedric
* Date: 07/08/12
* Time: 17:06
* To change this template use File | Settings | File Templates.
*/
public class CurrencyPropertyDisplayCell extends PropertyDisplayCell<Float> {
    public CurrencyPropertyDisplayCell(NumberFormat currencyFormat) {
        super(new NumberPropertyEditor.FloatPropertyEditor(currencyFormat));
    }

    @Override
    public void render(Context context, Float value, SafeHtmlBuilder sb) {
        String style = value < 0 ? "red" : "green";
        sb.appendHtmlConstant("<span style='color:" + style + "'>");
        super.render(context, value, sb);
        sb.appendHtmlConstant("</span>");
    }
}
