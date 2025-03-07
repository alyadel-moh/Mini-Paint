import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Move extends JFrame {
    private JPanel panel1;
    private JTextField textField2;
    private JTextField enterNewYTextField;
    private JTextField textField4;
    private JTextField enterNewXTextField;
    private JButton moveShapeButton;
    private Minipaint minipaint;
    Move(Shape shape,Minipaint minipaint){
        this.minipaint = minipaint;
        setTitle("Move shape ");
        setContentPane(panel1);
        setBounds(100,100,550,330);
        setLocationRelativeTo(null);
        moveShapeButton.setFocusable(false);
        setVisible(true);
        moveShapeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int x = 0, y = 0;
                if (shape instanceof Line) {
                    try {
                        x = Integer.parseInt(textField4.getText());
                        y = Integer.parseInt(textField2.getText());
                        shape.setPosition(new Point(shape.getPosition().x+x,shape.getPosition().y+y));
                        ((Line) shape).setPosition2(new Point(((Line) shape).getPosition2().x+x,((Line) shape).getPosition2().y+y));
                        shape.properties.put("x1",(double)shape.getPosition().x);
                        shape.properties.put("x2",(double)(((Line)shape).getPosition2().x));
                        shape.properties.put("y1",(double)shape.getPosition().y);
                        shape.properties.put("y2",(double)(((Line)shape).getPosition2().y));
                        //minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
                else
                {
                    try {
                        shape.setPosition(new Point(Integer.parseInt(textField4.getText()), Integer.parseInt(textField2.getText())));
                       // minipaint.shapess.push(shape);
                        minipaint.repaint();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
                    }
                }
            }
        });
    }
}
