import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class World implements ActionListener {
ArrayList<Enemy> enemies;
ArrayList<Teleporter> teleporters;
Color backgroundColor;
Timer enemyAttack;
Player player;
public World(ArrayList<Enemy> enemies, Color backgroundColor, Player player) {
	this.enemies=enemies;
	this.backgroundColor=backgroundColor;
	this.player=player;
	enemyAttack=new Timer(1000,this);
	enemyAttack.start();
	teleporters=new ArrayList<Teleporter>();
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
	g.setColor(new Color(0,0,0));
	g.drawString("Level: "+player.level, 10, 20);
	g.drawString("XP: "+player.XP+"/"+player.level*20, 10, 40);
}
public void update() {
	for (Enemy enemy : enemies) {
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
public void addTeleporter(Teleporter t) {
	teleporters.add(t);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(checkIntersection(player)!=null) {
		checkIntersection(player).attack(player);
	}
	
}
}
