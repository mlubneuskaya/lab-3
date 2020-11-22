package gui.table;


import java.awt.Component;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class HornersTableCellRenderer implements TableCellRenderer {
    private final DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public HornersTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int col) {
        String formatted = formatter.format(value);
        JTextField field = new JTextField(formatted);
        field.setHorizontalAlignment(SwingConstants.LEFT);
        return field;
    }
}