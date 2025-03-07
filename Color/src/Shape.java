import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class  Shape implements Shapeee, Serializable {
    protected Map<String, Double> properties;
    protected Point position;
    protected Color color;
    protected Color color1;
    protected Minipaint minipaint;


    @Override
    public void setPosition(Point position) {
        this.position = position;
    }
    @Override
    public Color getFillColor() {
        return color1;
    }
    @Override
    public void setFillColor(Color color) {
        this.color1 = color;
    }
    @Override
    public Point getPosition(){
        return this.position;
    }
    @Override
    public  void setProperties(Map<String,Double> properties){
            this.properties = properties;
    }

    @Override
    public Map<String, Double> getproperties() {
        return properties;
    }
    @Override
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void validatestring(String x1) {
        try {
            Integer.parseInt(x1);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input, enter a valid integer.");
        }
    }
    public void draw(java.awt.Graphics canvas){
    }

}
