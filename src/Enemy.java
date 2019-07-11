import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {
int health;
int damage;
int x;
int y;
Rectangle collisionBox;
public Enemy(int x, int y, int health, int damage) {
	this.x=x;
	this.y=y;
	this.health=health;
	this.damage=damage;
	collisionBox=new Rectangle();
}
public void draw(Graphics g) {
	g.setColor(new Color(255,0,0));
	g.fillRect(x, y, 30, 30);
}
public void update() {
	collisionBox.setBounds(x, y, 30, 30);
}
public void attack(Player player) {
	player.health=player.health-damage;
}
}
