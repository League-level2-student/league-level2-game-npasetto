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
Timer damageTimer;
Timer regenerateTimer;
long level=1;
double XP=0;
double gold=0;
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
double previousDamage=0;
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
	damageTimer=new Timer(1000,this);
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
	}else if(health<1000000000000L){
		healthText=((double) ((int) (health/10000000)))/100+"B";
	}else {
		healthText=((double) ((int) (health/10000000000L)))/100+"T";
	}
	if(maxHealth<1000) {
		maxHealthText=((int) maxHealth)+"";
	}else if(maxHealth<1000000){
		maxHealthText=((double) ((int) (maxHealth/10)))/100+"K";
	}else if(maxHealth<1000000000){
		maxHealthText=((double) ((int) (maxHealth/10000)))/100+"M";
	}else if(maxHealth<1000000000000L){
		maxHealthText=((double) ((int) (maxHealth/10000000)))/100+"B";
	}else {
		maxHealthText=((double) ((int) (maxHealth/10000000000L)))/100+"T";
	}
	String text=healthText+"/"+maxHealthText;
	int textLength=g.getFontMetrics().stringWidth(text);
	g.drawString(text, x+25-textLength/2, y-20);
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
	double damage=strengthMultiplier*(rand.nextDouble()*(maxDamage-minDamage)+minDamage);
	enemy.health-=damage;
	enemy.previousDamage=damage;
	enemy.damageTimer.restart();
	if(enemy.health<=0) {
		enemy.isAngry=false;
		gainXP((double) (enemy.XPboost*XPMultiplier));
		gold+=(long) (enemy.goldReward*goldMultiplier);
	}
}
public void gainXP(double XPboost) {
	XP+=XPboost;
	double boost=20*level-10;
	double levelBoost=Math.floor((-boost+Math.sqrt(boost*boost+40*XP))/20);
	XP-=20*level*levelBoost+10*levelBoost*(levelBoost-1);
	level+=levelBoost;
	if(level>2147483647 && prestiges<10) {
		level=2147483647;
	}
	maxHealth+=levelBoost*25;
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
		previousDamage=0;
		damageTimer.stop();
	}
}
}
