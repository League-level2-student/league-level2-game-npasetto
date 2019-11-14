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
long minDamage;
long maxDamage;
double health;
Rectangle collisionBox;
boolean canAttack=true;
Timer attackTimer;
Timer regenerateTimer;
long level=1;
long XP=0;
long gold=0;
double XPMultiplier=1;
double goldMultiplier=1;
int prestiges=0;
long levelRequired=1000;
ArrayList<Item> items;
Random rand=new Random();
int speed=2;
int confusionTimer=0;
double strengthMultiplier=1;
double defenseMultiplier=1;
Player(int x, int y, int maxHealth, long minDamage, long maxDamage){
	this.x=x;
	this.y=y;
	this.maxHealth=maxHealth;
	health=maxHealth;
	this.minDamage=minDamage;
	this.maxDamage=maxDamage;
	items=new ArrayList<Item>();
	items.add(new Sword("bronze sword",1,2,true,false,0,"sword"));
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
	String healthText;
	String maxHealthText;
	if(health<1000) {
		healthText=((int) health)+"";
	}else if(health<1000000){
		healthText=((double) ((int) (health/10)))/100+"K";
	}else if(health<1000000000){
		healthText=((double) ((int) (health/10000)))/100+"M";
	}else {
		healthText=((double) ((int) (health/10000000)))/100+"B";
	}
	if(maxHealth<1000) {
		maxHealthText=((int) maxHealth)+"";
	}else if(maxHealth<1000000){
		maxHealthText=((double) ((int) (maxHealth/10)))/100+"K";
	}else if(maxHealth<1000000000){
		maxHealthText=((double) ((int) (maxHealth/10000)))/100+"M";
	}else {
		maxHealthText=((double) ((int) (maxHealth/10000000)))/100+"B";
	}
	String text=healthText+"/"+maxHealthText;
	int textLength=g.getFontMetrics().stringWidth(text);
	g.drawString(text, x+25-textLength/2, y-20);
}
void up() {
	if(y>0) {
		y-=speed;
	}
	update();
}
void down() {
	if(y<RPGgame.HEIGHT-50) {
		y+=speed;
	}
	update();
}
void left() {
	if(x>0) {
		x-=speed;
	}
	update();
}
void right() {
	if(x<RPGgame.WIDTH-50) {
		x+=speed;
	}
	update();
}
public void attack(Enemy enemy) {
	enemy.health=enemy.health-strengthMultiplier*(rand.nextDouble()*maxDamage+minDamage);
	if(enemy.health<=0) {
		enemy.isAngry=false;
		gainXP((long) (enemy.XPboost*XPMultiplier));
		gold+=(long) (enemy.goldReward*goldMultiplier);
	}
	//canAttack=false; Makes the game much harder
	attackTimer.start();
}
public void gainXP(long XPboost) {
	XP+=XPboost;
	//System.out.println("Level: "+level+" -- XP: "+XP);
	while(XP>=level*20) {
		XP-=level*20;
		level++;
		maxHealth=maxHealth+25;
	}
	//System.out.println("Level: "+level+" -- XP: "+XP);
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
