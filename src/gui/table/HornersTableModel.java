package gui.table;

import javax.swing.table.AbstractTableModel;
import calculator.HornerCalculator;

public class HornersTableModel extends AbstractTableModel {
    private final double from;
    private final double to;
    private final double increment;
    private final TableModelParams params;

    public HornersTableModel(double from, double to, double increment, TableModelParams params) {
        this.from = from;
        this.to = to;
        this.increment = increment;
        this.params = params;
    }

    @Override
    public int getRowCount() {
        int rows = 0;
        double i = from;
        while (i <= to) {
            rows++;
            i += Math.abs(increment);
        }
        return rows;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + increment * row;
        if (col == 0) {
            return x;
        } else {
            HornerCalculator calculator = new HornerCalculator();
            return calculator.calculate(x, params.coefficients);
        }
    }

    @Override
    public String getColumnName(int column) {
        return params.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }
}