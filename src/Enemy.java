import java.awt.Color;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Enemy implements ActionListener {
double health;
double maxHealth;
long damage;
double x;
double y;
double spawnX;
double spawnY;
boolean boss;
Rectangle collisionBox;
Timer spawnTimer;
Timer damageTimer;
boolean isActive=true;
double XPboost;
double goldReward;
Item reward;
Key keyReward;
Item rareReward;
boolean isAngry=false;
boolean hasGun;
int timer=0;
boolean canShoot=false;
String gunType;
int dropChance;
String name;
boolean isSecret;
double slicerAngle=0;
boolean infiniteDamage;
int respawn;
int stages;
int stageNum=1;
Enemy parent=null;
int enemiesDefeated=0;
boolean immune=false;
double previousDamage=0;
Random rand=new Random();
public Enemy(double spawnX, double spawnY, double maxHealth, long damage, double XPboost, double goldReward, boolean boss, Item reward, Item rareReward, Key keyReward, boolean hasGun,String gunType, int dropChance, String name, boolean isSecret, boolean infiniteDamage, int respawn, int stages) {
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
	this.isSecret=isSecret;
	this.infiniteDamage=infiniteDamage;
	this.respawn=respawn;
	this.stages=stages;
	health=maxHealth;
	x=spawnX;
	y=spawnY;
	spawnTimer=new Timer(respawn,this);
	damageTimer=new Timer(1000,this);
}
public void draw(Graphics g) {
	if(isSecret==false || isAngry) {
		if(immune) {
			g.setColor(new Color(0,0,0));
			g.fillRect((int) x, (int) y, 30, 30);
		}
		if(boss) {
			g.setColor(new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
		}else {
			g.setColor(new Color(255,0,0));
		}
		if(immune) {
			g.fillRect((int) x+2, (int) y+2, 26, 26);
		}else {
			g.fillRect((int) x, (int) y, 30, 30);
		}
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
		}else if(health<1000000000000000000L){
			healthText=((double) ((int) (health/10000000000000L)))/100+"Qd";
		}else {
			healthText=((double) ((int) (health/10000000000000000L)))/100+"Qn";
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
		}else if(maxHealth<1000000000000000000L){
			maxHealthText=((double) ((int) (maxHealth/10000000000000L)))/100+"Qd";
		}else {
			maxHealthText=((double) ((int) (maxHealth/10000000000000000L)))/100+"Qn";
		}
		String text=name+" "+healthText+"/"+maxHealthText;
		int textLength=g.getFontMetrics().stringWidth(text);
		g.drawString(text, ((int) x)+15-textLength/2, (int) y-10);
		String damageText;
		if(previousDamage<1000) {
			damageText=((int) previousDamage)+"";
		}else if(previousDamage<1000000){
			damageText=((double) ((int) (previousDamage/10)))/100+"K";
		}else if(previousDamage<1000000000){
			damageText=((double) ((int) (previousDamage/10000)))/100+"M";
		}else if(previousDamage<1000000000000L){
			damageText=((double) ((int) (previousDamage/10000000)))/100+"B";
		}else if(previousDamage<1000000000000000L){
			damageText=((double) ((int) (previousDamage/10000000000L)))/100+"T";
		}else if(previousDamage<1000000000000000000L){
			damageText=((double) ((int) (previousDamage/10000000000000L)))/100+"Qd";
		}else {
			damageText=((double) ((int) (previousDamage/10000000000000000L)))/100+"Qn";
		}
		int damageTextLength=g.getFontMetrics().stringWidth("-"+damageText);
		g.setColor(new Color(0,255,0));
		if(previousDamage!=0) {
			g.drawString("-"+damageText, ((int) x)+15-damageTextLength/2, (int) y+20);
		}
	}
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
	updateCollisionBox();
}
public void updateCollisionBox() {
	collisionBox.setBounds((int) x, (int) y, 30, 30);
}
public void attack(Player player) {
	player.health=player.health-damage/player.defenseMultiplier;
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource().equals(spawnTimer)) {
		spawnTimer.stop();
		isActive=true;
		health=maxHealth;
		x=spawnX;
		y=spawnY;
		isAngry=false;
	}else {
		damageTimer.stop();
		previousDamage=0;
	}
}
}
