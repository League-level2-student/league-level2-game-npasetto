import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
int health;
int x;
int y;
public Enemy(int x, int y, int health) {
	this.x=x;
	this.y=y;
	this.health=health;
}
public void draw(Graphics g) {
	g.setColor(new Color(255,0,0));
	g.fillRect(x, y, 30, 30);
}
}
