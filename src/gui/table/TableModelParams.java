package gui.table;

import java.util.List;

public class TableModelParams {
    public List<Double> coefficients;
    String[] columnNames;

    public TableModelParams(List<Double> coefficients, String[] columnNames) {
        this.coefficients = coefficients;
        this.columnNames = columnNames;
    }
}
