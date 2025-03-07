import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Graphics;
import java.util.Stack;

public class Minipaint extends JFrame implements DrawingEngine{
    private ArrayList<Shape> shapes = new ArrayList<>();
    public Stack<Shape> shapess = new Stack<>();
    public Stack<Shape> redo = new Stack<>();
    public  JPanel panel;
    private JButton circleButton;
    private JButton linesegmentButton;
    private JButton squareButton;
    private JButton rectangleButton;
    private JButton resizeButton;
    private JButton moveButton;
    private JLabel label;
    private JComboBox<Shape> comboBox;
    private JButton colorize;
    private JButton delete;
    private JButton undoButton;
    private JButton redoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resetButton;
    public Shape shape;
    private JFileChooser jFileChooser = new JFileChooser();
    File selectedFile = null;
    public Minipaint()
    {
        this.jFileChooser.setCurrentDirectory(new File("Shape.txt"));
        panel = new DrawingPanel();
        panel.setBounds(290,100,680,640);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        setLayout(null);
        add(panel);

        label = new JLabel("choose a shape : ");
        label.setBounds(60, 320, 190, 70);
        add(label);

        circleButton = createbutton("Circle",340,50,120,35);
        linesegmentButton = createbutton("Line segment",500,50,140,35);
        squareButton = createbutton("Square",680,50,110,35);
        rectangleButton = createbutton("rectangle",820,50,120,35);
        colorize = createbutton("Colorize",30,430,100,35);
        delete = createbutton("delete",160,430,100,35);
        resizeButton = createbutton("Resize",30,480,100,35);
        moveButton = createbutton("Move",160,480,100,35);
        undoButton = createbutton("Undo",30,220,100,35);
        redoButton = createbutton("Redo",160,220,100,35);
        saveButton = createbutton("Save",30,270,100,35);
        loadButton = createbutton("Load",160,270,100,35);
        resetButton = createbutton("Reset",100,530,100,35);

        comboBox = new JComboBox<>();
        comboBox.setLocation(10,370);
        comboBox.setSize(260,32);
        comboBox.setVisible(true);
        add(comboBox);

        setTitle("Vector drawing Application ");
        setBounds(370,100,1000,800);
        setLocationRelativeTo(null);
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Shape shape = new Circle(Minipaint.this);
               addShape(shape);
            }
        });
       linesegmentButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = new Line(Minipaint.this);
                addShape(shape);
            }
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = new Square(Minipaint.this);
                addShape(shape);
            }
        });
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape = new Rectangle(Minipaint.this);
               addShape(shape);
            }
        });
        colorize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"no shapes !");
                else {
                    shape = (Shape) comboBox.getSelectedItem();
                   // shapess.push(shape.saveState(shape));
                    Color color2 = JColorChooser.showDialog(null, "pick a frame color", null);
                    shape.setColor(color2);
                    if (color2 != null)
                        panel.repaint();
                    color2 = JColorChooser.showDialog(null, "pick a fill color", null);
                    shape.setFillColor(color2);
                    if (color2 != null)
                        panel.repaint();
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"no shapes !");
                else
                removeShape((Shape) comboBox.getSelectedItem());
            }
        });
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"no shapes !");
                else {
                    Shape shape = (Shape) comboBox.getSelectedItem();
                    if (shape instanceof Circle || shape instanceof Square)
                        new Resize(shape, Minipaint.this);
                    else
                        new Resize2(shape, Minipaint.this);
                }
            }
        });
        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"no shapes !");
                else {
                    new Move((Shape) comboBox.getSelectedItem(), Minipaint.this);
                }
            }
        });
        saveButton.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!shapes.isEmpty())
                {
                try {
                    int result = jFileChooser.showSaveDialog(Minipaint.this);
                    if(result == jFileChooser.APPROVE_OPTION) {
                        selectedFile = jFileChooser.getSelectedFile();
                        if (selectedFile.exists()) {
                            int response = JOptionPane.showConfirmDialog(null, "File already exists. Do you want to overwrite?", "Confirm", JOptionPane.YES_NO_OPTION);
                            if (response != JOptionPane.YES_OPTION) {
                                return; // User chose not to overwrite
                            }
                        }
                        else
                            JOptionPane.showMessageDialog(null, "File not Found !");
                        ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(selectedFile));
                        save.writeObject(shapes);
                        save.close();
                        JOptionPane.showMessageDialog(null, "Shapes saved successfully !");
                        setVisible(false);
                    }
                } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null,"File not Found !");
                }
            }
            else JOptionPane.showMessageDialog(null,"Draw a shape !");
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int result = jFileChooser.showOpenDialog(Minipaint.this);
                    if(result == jFileChooser.APPROVE_OPTION) {
                        selectedFile = jFileChooser.getSelectedFile();
                        if (selectedFile.exists()) {
                            ObjectInputStream save = new ObjectInputStream(new FileInputStream(selectedFile));
                            shapes = (ArrayList<Shape>) save.readObject();
                            if (shapes.isEmpty())
                                JOptionPane.showMessageDialog(null, "no shapes !");
                            else {
                                for (Shape shape : shapes)
                                    comboBox.addItem(shape);
                                panel.repaint();
                            }

                            loadButton.setEnabled(false);
                            save.close();
                        }
                        else
                            JOptionPane.showMessageDialog(null, "File not Found !");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"File not found ! ");
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null,"File not Found !");
                }
            }
        });
       resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"Shapes Already empty !");
                else {
                    shapes.clear();
                    comboBox.removeAllItems();
                    JOptionPane.showMessageDialog(null,"Shapes deleted successfully ! ");
                    panel.repaint();
                }
            }
        });
        /*undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(shapes.isEmpty())
                    JOptionPane.showMessageDialog(null,"no shapes !");
                else
                if (!shapess.isEmpty()) {
                    if (!shapess.isEmpty()) {
                        Shape lastShape = shapess.pop();
                        redo.push(lastShape); // Save to redo stack

                        // Get the previous state
                        Shape previousShape = shapess.peek(); // Get the previous state
                        shapes.remove(lastShape);
                        comboBox.removeItem(lastShape);

                        if (previousShape != null) {
                            // Restore the previous shape
                            shapes.add(previousShape);
                            comboBox.addItem(previousShape);
                        }

                        // Redraw the panel with the updated shape states
                        panel.repaint();
                    }
                }
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!redo.isEmpty())
                {
                    Shape shape1 = redo.pop();
                    shapess.push(shape1);
                    shapes.add(shape1);
                    comboBox.addItem(shape1);
                    panel.repaint();
                }
            }
        });*/
        setVisible(true);
    }
    @Override
    public void refresh(Graphics canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }
    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        comboBox.addItem(shape);
        panel.repaint();
    }
    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
        comboBox.removeItem(shape);
        panel.repaint();
    }
    @Override
    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    private JButton createbutton(String text, int x, int y , int w, int h)
    {
        JButton button = new JButton();
        button.setBackground(Color.black);
        button.setForeground(Color.white);
        button.setFocusable(false);
        button.setBounds(x,y,w,h);
        button.setText(text);
        add(button);
        return button;
    }
    public class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            setBackground(Color.WHITE);
            super.paintComponent(g);
            refresh(g);
        }
    }
    public static void main(String[] args)
    {
        Minipaint minipaint = new Minipaint();
    }
}
