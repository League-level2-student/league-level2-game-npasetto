import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class Player implements ActionListener {
int x;
int y;
double maxHealth;
int minDamage;
int maxDamage;
double health;
Rectangle collisionBox;
boolean canAttack=true;
Timer attackTimer;
Timer regenerateTimer;
int level=5;
int XP=0;
int gold=0;
ArrayList<Item> items;
Random rand=new Random();
Player(int x, int y, int maxHealth, int minDamage, int maxDamage){
	this.x=x;
	this.y=y;
	this.maxHealth=maxHealth;
	health=maxHealth;
	this.minDamage=minDamage;
	this.maxDamage=maxDamage;
	items=new ArrayList<Item>();
	items.add(new Sword("sword1",1,2,true,false,0,false));
	collisionBox=new Rectangle();
	attackTimer=new Timer(1000,this);
	attackTimer.start();
	regenerateTimer=new Timer(759,this);
	regenerateTimer.start();
}
void draw(Graphics g) {
	g.setColor(new Color(0,0,255));
	g.fillRect(x, y, 50, 50);
	g.setColor(new Color(127,0,0));
	g.fillRect(x-30, y+70, 110, 10);
	g.setColor(new Color(0,127,0));
	g.fillRect(x-30, y+70, (int) (110*(health/maxHealth)),10);
	g.setColor(new Color(0,0,0));
	int textLength=g.getFontMetrics().stringWidth(((int) health)+"/"+((int) maxHealth));
	g.drawString(((int) health)+"/"+((int) maxHealth), x+25-textLength/2, y-20);
}
void up() {
	if(y>0) {
		y=y-5;
	}
}
void down() {
	if(y<RPGgame.HEIGHT-50) {
		y=y+5;
	}
}
void left() {
	if(x>0) {
		x=x-5;
	}
}
void right() {
	if(x<RPGgame.WIDTH-50) {
		x=x+5;
	}
}
public void attack(Enemy enemy) {
	enemy.health=enemy.health-(rand.nextInt(maxDamage-minDamage+1)+minDamage);
	if(enemy.health<=0) {
		enemy.isAngry=false;
		gainXP(enemy.XPboost);
		gold+=enemy.goldReward;
	}
	attackTimer.start();
}
public void gainXP(int XPboost) {
	XP+=XPboost;
	while(XP>=level*20) {
		XP-=level*20;
		level++;
		maxHealth=maxHealth+25;
	}
}
void update() {
	collisionBox.setBounds(x, y, 50,50);
}
@Override
public void actionPerformed(ActionEvent arg0) {
	Timer timer=(Timer) arg0.getSource();
	if(timer==regenerateTimer) {
		double regeneration=maxHealth/30;
		if(health+regeneration<=maxHealth) {
			health+=regeneration;
		}else {
			health=maxHealth;
		}
	}else {
		canAttack=true;
		attackTimer.stop();
	}
}
}
