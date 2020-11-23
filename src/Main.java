import file.CsvFileWriter;
import file.FileWriterConfig;
import file.GraphicsFileWriter;
import file.TextFileWriter;
import gui.Gui;
import gui.UiConfigParams;
import gui.table.TableModelParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.WindowConstants;

public class Main {
    public static void main(String[] args) {
        List<Double> coefficients = new ArrayList<>();
        for (String arg : args) {
            coefficients.add(Double.valueOf(arg));
        }
        FileWriterConfig config1 = new FileWriterConfig(new TextFileWriter(), "Save to text file");
        FileWriterConfig config2 = new FileWriterConfig(new GraphicsFileWriter(), "Save to graphics file");
        FileWriterConfig config3 = new FileWriterConfig(new CsvFileWriter(), "Save to csv file");
        List<FileWriterConfig> configs = Arrays.asList(config1, config2, config3);
        String[] columnNames = new String[]{"x", "polynomial value(Horner's method)",
                "polynomial value(naive method)", "difference between results"};
        TableModelParams tableModelParams = new TableModelParams(coefficients, columnNames);
        UiConfigParams uiConfigParams = new UiConfigParams(1300, 700, "Horner's table",
                "resources/my_photo.jpg");
        Gui gui = new Gui(uiConfigParams, tableModelParams, configs);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}