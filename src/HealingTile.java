import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class HealingTile {
int x;
int y;
Rectangle collisionBox;
HealingTile(int x,int y){
	this.x=x;
	this.y=y;
	collisionBox=new Rectangle(x,y,50,50);
}
void draw(Graphics g) {
	g.setColor(new Color(127,127,127));
	g.fillRect(x, y, 50, 50);
	g.drawString("Healing Tile", x-10, y-20);
}
}
