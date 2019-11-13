import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Projectile {
double x;
double y;
double speedX;
double speedY;
long minDamage;
long maxDamage;
int totalSpeed;
Rectangle collisionBox;
Random rand=new Random();
String projectileType;
int splitterCount;
int exploderTimer=0;
Projectile(double x, double y, double speedX, double speedY, long minDamage, long maxDamage, int totalSpeed, String projectileType, int splitterCount){
	this.x=x;
	this.y=y;
	this.speedX=speedX;
	this.speedY=speedY;
	this.minDamage=minDamage;
	this.maxDamage=maxDamage;
	this.totalSpeed=totalSpeed;
	this.projectileType=projectileType;
	this.splitterCount=splitterCount;
	collisionBox=new Rectangle((int) x,(int) y,5,5);
}
void move() {
	if(projectileType.equals("exploder") || projectileType.equals("triple split") || projectileType.equals("double split") || projectileType.equals("split")) {
		exploderTimer+=1;
	}
	x+=speedX*totalSpeed;
	y+=speedY*totalSpeed;
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
