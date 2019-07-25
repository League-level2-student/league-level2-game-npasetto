import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Teleporter {
int x;
int y;
World teleportTo;
Rectangle collisionBox;
String wallSide;
int requirement;
int width;
int height;
public Teleporter(int x, int y, World teleportTo, String wallSide, int requirement) {
	this.x=x;
	this.y=y;
	this.teleportTo=teleportTo;
	this.wallSide=wallSide;
	this.requirement=requirement;
	if(wallSide.equals("top") || wallSide.equals("bottom")) {
		width=25;
		height=5;
	}else {
		height=25;
		width=5;
	}
	collisionBox=new Rectangle(x,y,width,height);
}
public void draw(Graphics g) {
	g.setColor(new Color(0,0,127));
	g.fillRect(x, y, width, height);
	String text;
	if(requirement==0) {
		text="Boss";
	}else {
		text="Level "+requirement+" required";
	}
	int textLength=g.getFontMetrics().stringWidth(text);
	g.setColor(new Color(0,0,0));
	if(wallSide.equals("top")) {
		g.drawString(text, x+12-(textLength/2), y+15);
	}else if(wallSide.equals("bottom")) {
		g.drawString(text, x+12-(textLength/2), y-10);
	}else if(wallSide.equals("left")) {
		g.drawString(text, x+15, y+12);
	}else {
		g.drawString(text, x-10-textLength, y+12);
	}
}
}
