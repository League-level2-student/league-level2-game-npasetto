import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Teleporter {
int x;
int y;
World teleportTo;
Rectangle collisionBox;
public Teleporter(int x, int y, World teleportTo) {
	this.x=x;
	this.y=y;
	this.teleportTo=teleportTo;
	collisionBox=new Rectangle(x,y,5,25);
}
public void draw(Graphics g) {
	g.setColor(new Color(0,0,127));
	g.fillRect(x, y, 5, 25);
}
}
