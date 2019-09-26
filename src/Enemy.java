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
int XPboost;
int goldReward;
Sword reward;
Key keyReward;
Sword rareReward;
boolean isAngry=false;
boolean hasGun;
int timer=0;
boolean canShoot=false;
int gunType;
public Enemy(double spawnX, double spawnY, int maxHealth, int damage, int XPboost, int goldReward, boolean boss, Sword reward, Sword rareReward, Key keyReward, boolean hasGun, int gunType) {
	this.spawnX=spawnX;
	this.spawnY=spawnY;
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
	health=maxHealth;
	x=spawnX;
	y=spawnY;
	collisionBox=new Rectangle();
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
	g.drawString(((int) health)+"/"+((int) maxHealth), (int) x-10, (int) y-10); 
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
