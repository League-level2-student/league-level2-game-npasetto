import java.awt.Color;
import java.awt.Graphics;

public class Player {
int x;
int y;
Player(int x, int y){
	this.x=x;
	this.y=y;
}
void draw(Graphics g) {
	g.setColor(new Color(0,0,255));
	g.fillRect(x, y, 50, 50);
}
void up() {
	if(y>0) {
		y=y-5;
	}
}
void down() {
	if(y<RPGgame.HEIGHT) {
		y=y+5;
	}
}
void left() {
	if(x>0) {
		x=x-5;
	}
}
void right() {
	if(x<RPGgame.WIDTH) {
		x=x+5;
	}
}
}
