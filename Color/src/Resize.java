import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Resize extends JFrame{
    private JTextField textField1;
    private JPanel panel1;
    private JTextField textField2;
    private JButton button1;
    private Minipaint minipaint;
    public Resize(Shape shape,Minipaint minipaint) {
        this.minipaint = minipaint;
        setBounds(100, 100, 550, 280);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);
        button1.setFocusable(false);
        if (shape instanceof Circle) {
            setTitle("Resize Circle ");
            textField1.setText("      Enter new radius  ");
            button1.setText("Resize Circle ");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        shape.getproperties().put("radius", Double.parseDouble(textField2.getText()));
                       // minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
            });
        }
        else
        {
            setTitle("Resize Square ");
            textField1.setText("      Enter new side : ");
            button1.setText("Resize Square ");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        shape.getproperties().put("side", Double.parseDouble(textField2.getText()));
                       // minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
            });
        }
    }
}
