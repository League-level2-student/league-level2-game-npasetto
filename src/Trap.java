import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Trap {
int x;
int y;
int width;
int height;
int speed;
int moveLength;
int moveTimer;
String moveDirection;
Rectangle collisionBox;
Trap(int x, int y, int width, int height, int speed, int moveLength, String moveDirection,int delay){
	this.x=x;
	this.y=y;
	this.width=width;
	this.height=height;
	this.speed=speed;
	this.moveLength=moveLength;
	this.moveDirection=moveDirection;
	moveTimer=0;
	for(int i=0; i<delay; i++) {
		move();
		checkMove();
	}
	collisionBox=new Rectangle(x,y,width,height);
}
void draw(Graphics g) {
	g.setColor(new Color(255,0,0));
	g.fillRect(x, y, width, height);
	checkMove();
	move();
	collisionBox=new Rectangle(x,y,width,height);
}
void move() {
	if(moveDirection.equals("left")) {
		x-=speed;
	}
	if(moveDirection.equals("right")) {
		x+=speed;
	}
	if(moveDirection.equals("up")) {
		y-=speed;
	}
	if(moveDirection.equals("down")) {
		y+=speed;
	}
	moveTimer++;
}
void checkMove() {
	if(moveTimer>=moveLength) {
		System.out.println("moved");
		if(moveDirection.equals("left")) {
			moveDirection="right";
		}else if(moveDirection.equals("right")) {
			moveDirection="left";
		}else if(moveDirection.equals("up")) {
			moveDirection="down";
		}else if(moveDirection.equals("down")) {
			moveDirection="up";
		}
		moveTimer=0;
	}
}
}
