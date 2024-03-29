import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyProjectile {
double x;
double y;
double speedX;
double speedY;
long damage;
Rectangle collisionBox;
String type;
int timer=0;
boolean isStarting;
EnemyProjectile(double x, double y, double speedX, double speedY, long damage, String type, boolean isStarting){
	this.x=x;
	this.y=y;
	this.speedX=speedX;
	this.speedY=speedY;
	this.damage=damage;
	this.type=type;
	this.isStarting=isStarting;
	collisionBox=new Rectangle((int) x,(int) y,5,5);
}
void move() {
	if(type.equals("split") || type.equals("double split") || type.equals("slicer split") || type.equals("triple split")) {
		timer++;
	}
	x+=speedX*3;
	y+=speedY*3;
	collisionBox.setBounds((int) x, (int) y, 5, 5);
}
void draw(Graphics g) {
	g.setColor(new Color(127,0,0));
	g.fillRect((int) x,(int) y, 5, 5);
}
boolean checkWalls() {
	if(x<0) {
		return true;
	}
	if(y<0) {
		return true;
	}
	if(x>495) {
		return true;
	}
	if(y>715) {
		return true;
	}
	return false;
}
}
