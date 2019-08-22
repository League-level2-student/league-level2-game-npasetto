import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class World implements ActionListener {
ArrayList<Enemy> enemies;
ArrayList<Teleporter> teleporters;
ArrayList<HealingTile> tiles;
ArrayList<ArmorPlatform> platforms;
Color backgroundColor;
Timer enemyAttack;
Player player;
boolean isActive;
public World(ArrayList<Enemy> enemies, Color backgroundColor, Player player, boolean isActive) {
	this.enemies=enemies;
	this.backgroundColor=backgroundColor;
	this.player=player;
	this.isActive=isActive;
	enemyAttack=new Timer(1000,this);
	enemyAttack.start();
	teleporters=new ArrayList<Teleporter>();
	tiles=new ArrayList<HealingTile>();
	platforms=new ArrayList<ArmorPlatform>();
}
public void draw(Graphics g) {
	g.setColor(backgroundColor);
	g.fillRect(0, 0, RPGgame.WIDTH, RPGgame.HEIGHT);
	for (Enemy enemy : enemies) {
		if(enemy.isActive) {
			enemy.draw(g);
		}
	}
	for (Teleporter t : teleporters) {
		t.draw(g);
	}
	for (HealingTile h : tiles) {
		h.draw(g);
	}
	g.setColor(new Color(0,0,0));
	g.drawString("Level: "+player.level, 10, 20);
	g.drawString("XP: "+player.XP+"/"+player.level*20, 10, 40);
}
public void update() {
	for (Enemy enemy : enemies) {
		if(enemy.isAngry) {
		double xdiff=player.x-enemy.x;
		double ydiff=player.y-enemy.y;
		double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
		enemy.x=enemy.x+xdiff/distance;
		enemy.y=enemy.y+ydiff/distance;
		}
		enemy.update();
	}
}
public Enemy checkIntersection(Player player) {
	for (int i = 0; i < enemies.size(); i++) {
		if (enemies.get(i).isActive && enemies.get(i).collisionBox.intersects(player.collisionBox)){
			return enemies.get(i);
		}
	}
	return null;
}
public Teleporter checkTeleport(Player player) {
	for (int i = 0; i < teleporters.size(); i++) {
		if (teleporters.get(i).collisionBox.intersects(player.collisionBox)){
			return teleporters.get(i);
		}
	}
	return null;
}
public HealingTile checkHealingTile(Player player) {
	for (int i = 0; i < tiles.size(); i++) {
		if (tiles.get(i).collisionBox.intersects(player.collisionBox)){
			return tiles.get(i);
		}
	}
	return null;
}
public ArmorPlatform checkArmorPlatform(Player player) {
	for (int i = 0; i < platforms.size(); i++) {
		if (platforms.get(i).collisionBox.intersects(player.collisionBox)){
			return platforms.get(i);
		}
	}
	return null;
}
public void addTeleporter(Teleporter t) {
	teleporters.add(t);
}
public void addHealingTile(HealingTile h) {
	tiles.add(h);
}
public void addArmorPLatform(ArmorPlatform a) {
	platforms.add(a);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(checkIntersection(player)!=null && isActive) {
		checkIntersection(player).attack(player);
	}
	
}
}
