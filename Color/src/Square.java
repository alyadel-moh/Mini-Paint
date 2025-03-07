import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Square extends Shape implements Serializable {
    public Square(Minipaint minipaint)
    {
        this.minipaint = minipaint;
                String x1 = JOptionPane.showInputDialog("enter x : ");
                validatestring(x1);
                int  x = Integer.parseInt(x1);
                String y1 = JOptionPane.showInputDialog("enter y : ");
                validatestring(y1);
                int  y = Integer.parseInt(y1);
                setPosition(new Point(x,y));
                String side = JOptionPane.showInputDialog("enter side length : ");
                validatestring(side);
                this.properties = new HashMap<>();
                properties.put("side",Double.parseDouble(side));
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
        if(properties.containsKey("side"))
        this.properties = properties;
    }
    @Override
    public void draw(Graphics canvas) {
        if (position != null && properties.containsKey("side")) {
            Graphics2D g2d = (Graphics2D) canvas;
            if(this.color == null)
                this.color = Color.BLACK;;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawRect(position.x, position.y, properties.get("side").intValue(), properties.get("side").intValue());
            if(this.color1 == null) this.color1 = Color.white;
            g2d.setColor(color1);
            g2d.fillRect(position.x , position.y, properties.get("side").intValue(), properties.get("side").intValue());
        }
    }
    public String toString() {
        return "Square at pos ("+position.x+","+position.y+")"+" with side = " +  properties.get("side").intValue();
    }

}
