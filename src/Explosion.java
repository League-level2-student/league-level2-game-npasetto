import java.awt.Color;
import java.awt.Graphics;

public class Explosion {
double x;
double y;
double speedx;
double speedy;
int width;
int height;
Explosion(double x, double y, double speedx, double speedy, int width, int height){
	this.x=x;
	this.y=y;
	this.speedx=speedx;
	this.speedy=speedy;
	this.width=width;
	this.height=height;
}
void draw(Graphics g) {
	g.setColor(new Color(127,0,0));
	g.fillRect((int) x, (int) y, width, height);
	x+=speedx;
	y+=speedy;
}
boolean checkWalls() {
	if(x<0) {
		return true;
	}
	if(y<0) {
		return true;
	}
	if(x>RPGgame.WIDTH) {
		return true;
	}
	if(y>RPGgame.HEIGHT) {
		return true;
	}
	return false;
}
}
