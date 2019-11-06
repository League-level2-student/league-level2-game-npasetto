import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Enemy implements ActionListener {
double health;
double maxHealth;
int damage;
double x;
double y;
double spawnX;
double spawnY;
boolean boss;
Rectangle collisionBox;
Timer spawnTimer;
boolean isActive=true;
long XPboost;
long goldReward;
Item reward;
Key keyReward;
Item rareReward;
boolean isAngry=false;
boolean hasGun;
int timer=0;
boolean canShoot=false;
int gunType;
int dropChance;
String name;
public Enemy(double spawnX, double spawnY, double maxHealth, int damage, long XPboost, long goldReward, boolean boss, Item reward, Item rareReward, Key keyReward, boolean hasGun, int gunType, int dropChance, String name) {
	this.spawnX=spawnX;
	this.spawnY=spawnY;
	collisionBox=new Rectangle((int) spawnX,(int) spawnY,30,30);
	this.maxHealth=maxHealth;
	this.damage=damage;
	this.XPboost=XPboost;
	this.boss=boss;
	this.reward=reward;
	this.keyReward=keyReward;
	this.goldReward=goldReward;
	this.rareReward=rareReward;
	this.hasGun=hasGun;
	this.gunType=gunType;
	this.dropChance=dropChance;
	this.name=name;
	health=maxHealth;
	x=spawnX;
	y=spawnY;
	spawnTimer=new Timer(5000,this);
}
public void draw(Graphics g) {
	g.setColor(new Color(255,0,0));
	g.fillRect((int) x, (int) y, 30, 30);
	g.setColor(new Color(127,0,0));
	g.fillRect((int) x-20, (int) y+50, 70, 10);
	g.setColor(new Color(0,127,0));
	g.fillRect((int) x-20, (int) y+50, (int) (70*(health/maxHealth)), 10);
	g.setColor(new Color(0,0,0));
	String healthText;
	String maxHealthText;
	if(health<1000) {
		healthText=((int) health)+"";
	}else if(health<1000000){
		healthText=((double) ((int) (health/10)))/100+"K";
	}else if(health<1000000000){
		healthText=((double) ((int) (health/10000)))/100+"M";
	}else if(health<1000000000000L){
		healthText=((double) ((int) (health/10000000)))/100+"B";
	}else if(health<1000000000000000L){
		healthText=((double) ((int) (health/10000000000L)))/100+"T";
	}else {
		healthText=((double) ((int) (health/10000000000000L)))/100+"Qd";
	}
	if(maxHealth<1000) {
		maxHealthText=((int) maxHealth)+"";
	}else if(maxHealth<1000000){
		maxHealthText=((double) ((int) (maxHealth/10)))/100+"K";
	}else if(maxHealth<1000000000){
		maxHealthText=((double) ((int) (maxHealth/10000)))/100+"M";
	}else if(maxHealth<1000000000000L){
		maxHealthText=((double) ((int) (maxHealth/10000000)))/100+"B";
	}else if(maxHealth<1000000000000000L){
		maxHealthText=((double) ((int) (maxHealth/10000000000L)))/100+"T";
	}else {
		maxHealthText=((double) ((int) (maxHealth/10000000000000L)))/100+"Qd";
	}
	String text=name+" "+healthText+"/"+maxHealthText;
	int textLength=g.getFontMetrics().stringWidth(text);
	g.drawString(text, ((int) x)+15-textLength/2, (int) y-10); 
}
public void update() {
	if(canShoot==false) {
		timer++;
		if(timer==50) {
			canShoot=true;
		}
	}
	if(health<=0) {
		isActive=false;
		spawnTimer.start();
	}
	collisionBox.setBounds((int) x, (int) y, 30, 30);
}
public void attack(Player player) {
	player.health=player.health-damage;
}
@Override
public void actionPerformed(ActionEvent arg0) {
	spawnTimer.stop();
	isActive=true;
	health=maxHealth;
	x=spawnX;
	y=spawnY;
	isAngry=false;
}
}
