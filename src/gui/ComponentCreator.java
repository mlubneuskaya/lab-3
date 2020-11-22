package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.Box;
import java.awt.Dimension;

class ComponentCreator {
    JTextField createTextField(String text) {
        JTextField textField = new JTextField(text);
        Dimension dimension = new Dimension(10 * textField.getPreferredSize().width,
                textField.getPreferredSize().height);
        textField.setMinimumSize(dimension);
        textField.setPreferredSize(dimension);
        textField.setMaximumSize(dimension);
        textField.setEditable(true);
        return textField;
    }

    JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        Dimension dimension = new Dimension(10 * label.getPreferredSize().width,
                label.getPreferredSize().height);
        label.setMaximumSize(dimension);
        return label;
    }

    JButton createButton(String text) {
        JButton button = new JButton(text);
        Dimension dimension = new Dimension(button.getPreferredSize());
        button.setMinimumSize(dimension);
        button.setPreferredSize(dimension);
        button.setMaximumSize(dimension);
        return button;
    }

    void insertBoxes(Box container, List<Box> boxes) {
        container.add(Box.createVerticalGlue());
        boxes = new ArrayList<>(boxes);
        JComponent lastComponent = boxes.get(boxes.size() - 1);
        boxes.remove(boxes.size() - 1);
        for (JComponent component : boxes) {
            container.add(component);
            container.add(Box.createVerticalStrut(20));
        }
        container.add(lastComponent);
        container.add(Box.createVerticalGlue());
    }

    void insertComponents(Box container, List<JComponent> components) {
        container.add(Box.createHorizontalGlue());
        components = new ArrayList<>(components);
        JComponent lastComponent = components.get(components.size() - 1);
        components.remove(components.size() - 1);
        for (JComponent component : components) {
            container.add(component);
            container.add(Box.createHorizontalStrut(20));
        }
        container.add(lastComponent);
        container.add(Box.createHorizontalGlue());
    }
}