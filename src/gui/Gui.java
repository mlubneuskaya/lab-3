package gui;

import gui.table.HornersTableModel;
import gui.table.TableModelParams;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;


public class Gui extends JFrame {
    private final ComponentCreator componentCreator;
    private JTextField fromText;
    private JTextField toText;
    private JTextField incrementText;
    private Box tableBox;
    private final TableModelParams tableModelParams;

    public Gui(UiConfigParams uiConfigParams, TableModelParams tableModelParams) {
        super(uiConfigParams.title);
        setSize(uiConfigParams.width, uiConfigParams.height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - uiConfigParams.width) / 2,
                (kit.getScreenSize().height - uiConfigParams.height) / 2);
        componentCreator = new ComponentCreator();
        this.tableModelParams = tableModelParams;
        Box rangeBox = createRangeBox();
        Box tableBox = createTableBox();
        Box buttonBox = createButtonBox();
        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(rangeBox, tableBox, buttonBox);
        componentCreator.insertBoxes(box, boxes);
        this.getContentPane().add(box);
        this.setVisible(true);
    }

    private JTable createTable() {
        double from = Double.parseDouble(fromText.getText());
        double to = Double.parseDouble(toText.getText());
        double increment = Double.parseDouble(incrementText.getText());
        HornersTableModel data = new HornersTableModel(from, to, increment, tableModelParams);
        JTable table = new JTable(data);
        table.setRowHeight(table.getRowHeight() * 4);
        return table;
    }

    private Box createTableBox() {
        JTable table = createTable();
        tableBox = Box.createHorizontalBox();
        tableBox.add(new JScrollPane(table));
        return tableBox;
    }

    private Box createRangeBox() {
        JLabel fromLabel = componentCreator.createLabel("x is changed in the range from ");
        fromText = componentCreator.createTextField("0.0");
        JLabel toLabel = componentCreator.createLabel("to");
        toText = componentCreator.createTextField("1.0");
        JLabel incrementLabel = componentCreator.createLabel("with increment of");
        incrementText = componentCreator.createTextField("0.1");
        List<JComponent> components = Arrays.asList(fromLabel, fromText, toLabel, toText, incrementLabel, incrementText);
        Box rangeBox = Box.createHorizontalBox();
        componentCreator.insertComponents(rangeBox, components);
        return rangeBox;
    }

    private void resetRange() {
        fromText.setText("0.0");
        toText.setText("1.0");
        incrementText.setText("0.1");
    }

    private Box createButtonBox() {
        JButton calculate = componentCreator.createButton("calculate");
        calculate.addActionListener(actionEvent -> {
            tableBox.removeAll();
            JTable table = createTable();
            tableBox.add(new JScrollPane(table));
            getContentPane().validate();
        });
        JButton reset = componentCreator.createButton("reset");
        reset.addActionListener(actionEvent -> {
            tableBox.removeAll();
            resetRange();
            JTable table = createTable();
            tableBox.add(new JScrollPane(table));
            getContentPane().validate();
        });
        Box buttonBox = Box.createHorizontalBox();
        List<JComponent> components = Arrays.asList(calculate, reset);
        componentCreator.insertComponents(buttonBox, components);
        return buttonBox;
    }
}
