import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Projectile {
double x;
double y;
double speedX;
double speedY;
int minDamage;
int maxDamage;
Rectangle collisionBox;
Random rand=new Random();
Projectile(double x, double y, double speedX, double speedY, int minDamage, int maxDamage){
	this.x=x;
	this.y=y;
	this.speedX=speedX;
	this.speedY=speedY;
	this.minDamage=minDamage;
	this.maxDamage=maxDamage;
	collisionBox=new Rectangle((int) x,(int) y,5,5);
}
void move() {
	x+=speedX*3;
	y+=speedY*3;
	collisionBox.setBounds((int) x, (int) y, 5, 5);
}
void draw(Graphics g) {
	g.setColor(new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
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
