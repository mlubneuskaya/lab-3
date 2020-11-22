import gui.Gui;
import gui.UiConfigParams;
import gui.table.TableModelParams;
import java.util.ArrayList;
import java.util.List;
import javax.swing.WindowConstants;

public class Main {
    public static void main(String[] args) {
        List<Double> coefficients = new ArrayList<>();
        for(String arg: args){
            coefficients.add(Double.valueOf(arg));
        }
        String[] columnNames = new String[]{"x", "polynomial value(Horner's method)",
                "polynomial value(naive method)", "difference between results"};
        TableModelParams tableModelParams = new TableModelParams(coefficients, columnNames);
        UiConfigParams uiConfigParams = new UiConfigParams(1300, 700, "Horner's table");
        Gui gui = new Gui(uiConfigParams, tableModelParams);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
