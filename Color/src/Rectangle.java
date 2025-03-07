import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Rectangle extends Shape implements Serializable {
    public Rectangle(Minipaint minipaint)
    {
        this.minipaint = minipaint;
        String x1 = JOptionPane.showInputDialog("enter x : ");
        validatestring(x1);
        int  x = Integer.parseInt(x1);
        String y1 = JOptionPane.showInputDialog("enter y : ");
        validatestring(y1);
        int  y = Integer.parseInt(y1);
        setPosition(new Point(x,y));
        String length2 = JOptionPane.showInputDialog("enter height : ");
        validatestring(length2);
        String width2 = JOptionPane.showInputDialog("enter width  : ");
        validatestring(width2);
         this.properties = new HashMap<>();
         properties.put("height",Double.parseDouble(length2));
         properties.put("width",Double.parseDouble(width2));
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
        if(properties.containsKey("height") &&  properties.containsKey("width"))
        this.properties = properties;
    }
    @Override
    public void draw(Graphics canvas) {
        if (position != null && properties.containsKey("height") && properties.containsKey("width")) {
            Graphics2D g2d = (Graphics2D) canvas;
            if(this.color == null)
                this.color = Color.BLACK;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawRect(position.x,position.y, properties.get("width").intValue(), properties.get("height").intValue());
            if(this.color1 == null) this.color1 = Color.white;
            g2d.setColor(color1);
            g2d.fillRect(position.x,position.y,properties.get("width").intValue(),properties.get("height").intValue());
        }
    }
    public String toString() {
        return "Rect at pos ("+position.x+","+position.y+")"+" with h = "+properties.get("height").intValue()+" w = " +properties.get("width").intValue();
    }

}
