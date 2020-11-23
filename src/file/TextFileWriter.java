package file;

import gui.table.HornersTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

public class TextFileWriter implements FileWriter {
    @Override
    public void write(File selectedFile, List<Double> coefficients, HornersTableModel data) {
        try {
            PrintStream out = new PrintStream(selectedFile);
            out.println("polynomial values at given points");
            out.print("polynomial: ");
            int power = 0;
            double lastCoefficient = coefficients.get(coefficients.size() - 1);
            coefficients.remove(lastCoefficient);
            for (double coefficient : coefficients) {
                out.print(coefficient + "*x^" + power + " + ");
                power++;
            }
            out.println(lastCoefficient + "*x^" + power);
            out.println("x changes in range from " + data.getFrom() + " to " + data.getTo() +
                    " with increment of " + data.getIncrement());
            for (int j = 0; j < data.getRowCount(); j++) {
                out.println("x at point " + data.getValueAt(j, 0) + " equals " + data.getValueAt(j, 1));
            }
            out.close();
        } catch (FileNotFoundException ignored) {
        }
    }
}