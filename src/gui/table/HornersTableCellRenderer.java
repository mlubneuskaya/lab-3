package gui.table;


import java.awt.Component;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class HornersTableCellRenderer implements TableCellRenderer {
    private final DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();
    private String needle = null;

    public HornersTableCellRenderer() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int col) {
        String formatted = formatter.format(value);
        JTextField field = new JTextField(formatted);
        if (formatted.contains("-")) {
            field.setHorizontalAlignment(SwingConstants.LEFT);
        } else if (formatted.equals("0")) {
            field.setHorizontalAlignment(SwingConstants.CENTER);
        } else {
            field.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        if (col == 1 && needle != null && needle.equals(formatted)) {
            field.setBackground(new Color(193, 218, 245));
        } else {
            field.setBackground(Color.WHITE);
        }
        return field;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}