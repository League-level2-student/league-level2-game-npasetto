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
int x;
int y;
boolean boss;
Rectangle collisionBox;
Timer spawnTimer;
boolean isActive=true;
int XPboost;
Sword reward;
public Enemy(int x, int y, int maxHealth, int damage, int XPboost, boolean boss, Sword reward) {
	this.x=x;
	this.y=y;
	this.maxHealth=maxHealth;
	this.damage=damage;
	this.XPboost=XPboost;
	this.boss=boss;
	this.reward=reward;
	health=maxHealth;
	collisionBox=new Rectangle();
	spawnTimer=new Timer(5000,this);
}
public void draw(Graphics g) {
	g.setColor(new Color(255,0,0));
	g.fillRect(x, y, 30, 30);
	g.setColor(new Color(127,0,0));
	g.fillRect(x-20, y+50, 70, 10);
	g.setColor(new Color(0,127,0));
	g.fillRect(x-20, y+50, (int) (70*(health/maxHealth)), 10);
}
public void update() {
	if(health<=0) {
		isActive=false;
		spawnTimer.start();
	}
	collisionBox.setBounds(x, y, 30, 30);
}
public void attack(Player player) {
	player.health=player.health-damage;
}
@Override
public void actionPerformed(ActionEvent arg0) {
	spawnTimer.stop();
	isActive=true;
	health=maxHealth;
}
}
