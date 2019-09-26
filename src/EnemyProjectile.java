import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyProjectile {
double x;
double y;
double speedX;
double speedY;
int damage;
Rectangle collisionBox;
int type;
int timer=0;
EnemyProjectile(double x, double y, double speedX, double speedY, int damage, int type){
	this.x=x;
	this.y=y;
	this.speedX=speedX;
	this.speedY=speedY;
	this.damage=damage;
	this.type=type;
	collisionBox=new Rectangle((int) x,(int) y,5,5);
}
void move() {
	if(type>0) {
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
