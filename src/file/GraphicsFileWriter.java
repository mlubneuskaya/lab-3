package file;

import gui.table.HornersTableModel;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class GraphicsFileWriter implements FileWriter {
    @Override
    public void write(File selectedFile, List<Double> coefficients, HornersTableModel data) {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(selectedFile));
            for (int i = 0; i < data.getRowCount(); i++) {
                out.writeDouble((Double) data.getValueAt(i, 0));
                out.writeDouble((Double) data.getValueAt(i, 1));
                out.close();
            }
        } catch (Exception ignored) {

        }
    }
}
