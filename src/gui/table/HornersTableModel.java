package gui.table;

import calculator.PowCalculator;
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

    public double getFrom() {
        return from;
    }

    public double getIncrement() {
        return increment;
    }

    public double getTo() {
        return to;
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
        return 4;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + increment * row;
        if (col == 0) {
            return x;
        } else if (col == 1) {
            HornerCalculator calculator = new HornerCalculator();
            return calculator.calculate(x, params.coefficients);
        } else if (col == 2) {
            PowCalculator calculator = new PowCalculator();
            return calculator.calculate(x, params.coefficients);
        } else {
            return Math.abs((Double) getValueAt(row, 1) - (Double) getValueAt(row, 2));
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