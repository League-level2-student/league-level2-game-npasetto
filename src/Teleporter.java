import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Teleporter {
int x;
int y;
World teleportTo;
Rectangle collisionBox;
String wallSide;
Item requiredKey;
int requirement;
int width;
int height;
String description;
boolean isSecret;
int prestigeRequired;
int teleportX;
int teleportY;
public Teleporter(int x, int y, World teleportTo, String wallSide, int requirement, Item requiredKey, String description, boolean isSecret, int prestigeRequired) {
	teleportX=250;
	teleportY=600;
	this.x=x;
	this.y=y;
	this.teleportTo=teleportTo;
	this.wallSide=wallSide;
	this.requirement=requirement;
	this.requiredKey=requiredKey;
	this.description=description;
	this.isSecret=isSecret;
	this.prestigeRequired=prestigeRequired;
	if(wallSide.equals("top") || wallSide.equals("bottom")) {
		width=25;
		height=5;
	}else {
		height=25;
		width=5;
	}
	collisionBox=new Rectangle(x,y,width,height);
}
public Teleporter(int x, int y, World teleportTo, String wallSide, int requirement, Item requiredKey, String description, boolean isSecret, int prestigeRequired, int teleportX, int teleportY) {
	this.x=x;
	this.y=y;
	this.teleportTo=teleportTo;
	this.wallSide=wallSide;
	this.requirement=requirement;
	this.requiredKey=requiredKey;
	this.description=description;
	this.isSecret=isSecret;
	this.prestigeRequired=prestigeRequired;
	if(wallSide.equals("top") || wallSide.equals("bottom")) {
		width=25;
		height=5;
	}else {
		height=25;
		width=5;
	}
	collisionBox=new Rectangle(x,y,width,height);
	this.teleportX=teleportX;
	this.teleportY=teleportY;
}
public void draw(Graphics g) {
	if(isSecret==false) {
	g.setColor(new Color(0,0,127));
	g.fillRect(x, y, width, height);
	int textLength=g.getFontMetrics().stringWidth(description);
	g.setColor(new Color(0,0,0));
	if(wallSide.equals("top")) {
		g.drawString(description, x+12-(textLength/2), y+15);
	}else if(wallSide.equals("bottom")) {
		g.drawString(description, x+12-(textLength/2), y-10);
	}else if(wallSide.equals("left")) {
		g.drawString(description, x+15, y+12);
	}else {
		g.drawString(description, x-10-textLength, y+12);
	}
	}
}
}
