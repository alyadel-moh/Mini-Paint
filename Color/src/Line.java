import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Line extends Shape implements Serializable {
    private Point position2;
    public Line(Minipaint minipaint)
    {
        this.minipaint = minipaint;
        String x1 = JOptionPane.showInputDialog("enter x1 : ");
        validatestring(x1);
        int x11 = Integer.parseInt(x1);
        String y1 = JOptionPane.showInputDialog("enter y1 : ");
        validatestring(y1);
        int y11 = Integer.parseInt(y1);
        String x2 = JOptionPane.showInputDialog("enter x2 : ");
        validatestring(x2);
        int x22 = Integer.parseInt(x2);
        String y2 = JOptionPane.showInputDialog("enter y2 : ");
        validatestring(y2);
        int y22 = Integer.parseInt(y2);
        setPosition(new Point(x11,y11));
        setPosition2(new Point(x22,y22));
        this.properties = new HashMap<>();
        properties.put("x1",Double.parseDouble(x1));
        properties.put("y1",Double.parseDouble(y1));
        properties.put("x2",Double.parseDouble(x2));
        properties.put("y2",Double.parseDouble(y2));
    }
    @Override
    public void setProperties(Map<String, Double> properties) {
        if(properties.containsKey("x1") && properties.containsKey("x2") &&  properties.containsKey("y1") && properties.containsKey("y2"))
        this.properties = properties;
    }
    public void setPosition2(Point position2) {
        this.position2 = position2;
    }
    public Point getPosition2(){
        return this.position2;
    }
    @Override
    public void draw(Graphics canvas) {
        if(position != null && position2 != null)
        {
            Graphics2D g2d = (Graphics2D) canvas;
            if(this.color1 == null)
                this.color1 = Color.white;
            g2d.setColor(color1);
            g2d.fillRect(Math.min(position.x, position2.x),
                    Math.min(position.y, position2.y),
                    Math.abs(position2.x - position.x),
                    Math.abs(position2.y - position.y));
            if(this.color == null)
                this.color = Color.BLACK;;
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(position.x,position.y,position2.x, position2.y);
        }
    }
    public String toString() {
        return "Line from ("+ position.x +"," +position.y +") to ("+ position2.x + "," + position2.y +")";
    }
}
