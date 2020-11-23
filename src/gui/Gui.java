package gui;


import file.FileWriterConfig;
import gui.table.HornersTableCellRenderer;
import gui.table.HornersTableModel;
import gui.table.TableModelParams;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;


public class Gui extends JFrame {
    private final ComponentCreator componentCreator;
    private JTextField fromText;
    private JTextField toText;
    private JTextField incrementText;
    private Box tableBox;
    private HornersTableModel data;
    private JMenu tableMenu;
    private JMenu fileMenu;
    private final TableModelParams tableModelParams;
    private final HornersTableCellRenderer renderer;
    private final List<FileWriterConfig> configs;
    private final UiConfigParams uiConfigParams;


    public Gui(UiConfigParams uiConfigParams, TableModelParams tableModelParams, List<FileWriterConfig> configs) {
        super(uiConfigParams.title);
        setSize(uiConfigParams.width, uiConfigParams.height);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - uiConfigParams.width) / 2,
                (kit.getScreenSize().height - uiConfigParams.height) / 2);
        componentCreator = new ComponentCreator();
        renderer = new HornersTableCellRenderer();
        this.tableModelParams = tableModelParams;
        this.configs = configs;
        this.uiConfigParams = uiConfigParams;
        createMenuBar();
        Box rangeBox = createRangeBox();
        Box tableBox = createTableBox();
        Box buttonBox = createButtonBox();
        Box box = Box.createVerticalBox();
        List<Box> boxes = Arrays.asList(rangeBox, tableBox, buttonBox);
        componentCreator.insertBoxes(box, boxes);
        this.getContentPane().add(box);
        this.setVisible(true);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = createFileMenu();
        menuBar.add(fileMenu);
        tableMenu = createTableMenu();
        menuBar.add(tableMenu);
        menuBar.add(createHelpMenu());
    }

    private JMenu createTableMenu() {
        JMenu tableMenu = new JMenu("Table");
        tableMenu.add(new AbstractAction("Value search") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String value = JOptionPane.showInputDialog(Gui.this,
                        "Enter a value", "Value search",
                        JOptionPane.QUESTION_MESSAGE);
                renderer.setEqualsNeedle(value);
                getContentPane().repaint();
            }
        });
        tableMenu.add(new AbstractAction("Simple numbers") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                renderer.setPrimeNeedle(true);
                getContentPane().repaint();
            }
        });
        tableMenu.setEnabled(false);
        return tableMenu;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        for (FileWriterConfig config : configs) {
            fileMenu.add(createFileAction(config));
        }
        fileMenu.setEnabled(false);
        return fileMenu;
    }

    private AbstractAction createFileAction(FileWriterConfig config) {
        return new AbstractAction(config.name) {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                if (fileChooser.showSaveDialog(Gui.this) == JFileChooser.APPROVE_OPTION) {
                    config.writer.write(fileChooser.getSelectedFile(), tableModelParams.coefficients, data);
                }
            }
        };
    }

    private JMenu createHelpMenu() {
        JMenu tableMenu = new JMenu("Help");
        tableMenu.add(new AbstractAction("About") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ImageIcon icon = new ImageIcon(uiConfigParams.imageFilePath);
                JOptionPane.showMessageDialog(Gui.this, "Lubneuskaya\n10 group",
                        "About", JOptionPane.INFORMATION_MESSAGE, icon);
                getContentPane().repaint();
            }
        });
        return tableMenu;
    }

    private JTable createTable() {
        double from = Double.parseDouble(fromText.getText());
        double to = Double.parseDouble(toText.getText());
        double increment = Double.parseDouble(incrementText.getText());
        data = new HornersTableModel(from, to, increment, tableModelParams);
        JTable table = new JTable(data);
        table.setDefaultRenderer(Double.class, renderer);
        table.setRowHeight(table.getRowHeight() * 4);
        return table;
    }

    private Box createTableBox() {
        tableBox = Box.createHorizontalBox();
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
            renderer.setPrimeNeedle(false);
            renderer.setEqualsNeedle(null);
            tableMenu.setEnabled(true);
            fileMenu.setEnabled(true);
            getContentPane().validate();
        });
        JButton reset = componentCreator.createButton("reset");
        reset.addActionListener(actionEvent -> {
            tableBox.removeAll();
            resetRange();
            renderer.setPrimeNeedle(false);
            renderer.setEqualsNeedle(null);
            tableMenu.setEnabled(false);
            fileMenu.setEnabled(false);
            getContentPane().validate();
        });
        Box buttonBox = Box.createHorizontalBox();
        List<JComponent> components = Arrays.asList(calculate, reset);
        componentCreator.insertComponents(buttonBox, components);
        return buttonBox;
    }
}
