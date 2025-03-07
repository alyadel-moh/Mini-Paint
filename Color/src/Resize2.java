import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resize2 extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton button1;
    private JPanel panel1;
    private Minipaint minipaint;

    public Resize2(Shape shape, Minipaint minipaint) {
        this.minipaint = minipaint;
        setContentPane(panel1);
        setBounds(100, 100, 550, 350);
        setLocationRelativeTo(null);
        button1.setFocusable(false);
        setVisible(true);
        if (shape instanceof Rectangle) {
            setTitle("Resize Rectangle ");
            textField4.setText("      Enter new height  ");
            textField1.setText("      Enter new width  ");
            button1.setText("Resize Rectangle ");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        shape.getproperties().put("height",Double.parseDouble(textField3.getText()));
                        shape.getproperties().put("width", Double.parseDouble(textField2.getText()));
                        //minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
            });
        } else {
            setTitle("Resize line segment ");
            textField4.setText("      Enter X2  ");
            textField1.setText("      Enter Y2 ");
            button1.setText("Resize line segment ");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        double x2 = Double.parseDouble(textField3.getText());
                        double y2 = Double.parseDouble(textField2.getText());
                        shape.getproperties().put("x2", x2);
                        shape.getproperties().put("y2", y2);
                        ((Line)shape).setPosition2(new Point((int)x2,(int)y2));
                        //minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
            });
        }
    }
}
