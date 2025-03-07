import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Circle extends Shape implements Serializable {
    public Circle(Minipaint minipaint)
    {
        this.minipaint = minipaint;
        String x1 = JOptionPane.showInputDialog("enter x : ");
         validatestring(x1);
        int  x = Integer.parseInt(x1);
        String y1 = JOptionPane.showInputDialog("enter y : ");
        validatestring(y1);
        int  y = Integer.parseInt(y1);
        setPosition(new Point(x,y));
        String radius = JOptionPane.showInputDialog("enter radius : ");
        validatestring(radius);
        double radius1 = Double.parseDouble(radius);
        this.properties = new HashMap<>();
        properties.put("radius",radius1);
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
        if(properties.containsKey("radius"))
        this.properties = properties;
    }
    @Override
    public void draw(Graphics canvas) {
        if (position != null && properties.containsKey("radius")) {
            Graphics2D g2d = (Graphics2D) canvas;
            if(this.color == null) this.color = Color.BLACK;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawOval(position.x,position.y,2*properties.get("radius").intValue(),2*properties.get("radius").intValue());
            if(this.color1 == null) this.color1 = Color.white;
            g2d.setColor(color1);
            g2d.fillOval(position.x,position.y,2*properties.get("radius").intValue(),2*properties.get("radius").intValue());
        }
}
    public String toString() {
        return "Circle at pos ("+position.x+","+position.y+")"+" with r = "+ properties.get("radius").intValue();
    }}
