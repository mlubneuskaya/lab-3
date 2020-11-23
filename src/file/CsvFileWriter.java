package file;

import gui.table.HornersTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class CsvFileWriter implements FileWriter {
    @Override
    public void write(File selectedFile, List<Double> coefficients, HornersTableModel data) {
        try {
            PrintStream out = new PrintStream(selectedFile);
            out.println("x, polynomial value");
            for (int i = 0; i < data.getRowCount(); i++) {
                out.println(data.getValueAt(i, 0) +
                        "," + data.getValueAt(i, 1));
            }
            out.close();
        } catch (FileNotFoundException ignore) {
        }
    }
}
