package file;

import gui.table.HornersTableModel;
import java.io.File;
import java.util.List;

public interface FileWriter {
    public void write(File selectedFile, List<Double> coefficients, HornersTableModel data);
}
