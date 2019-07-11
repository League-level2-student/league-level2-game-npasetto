import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class World implements ActionListener {
ArrayList<Enemy> enemies;
Color backgroundColor;
Timer enemyAttack;
Player player;
public World(ArrayList<Enemy> enemies, Color backgroundColor, Player player) {
	this.enemies=enemies;
	this.backgroundColor=backgroundColor;
	this.player=player;
	enemyAttack=new Timer(1000,this);
	enemyAttack.start();
}
public void draw(Graphics g) {
	g.setColor(backgroundColor);
	g.fillRect(0, 0, RPGgame.WIDTH, RPGgame.HEIGHT);
	for (Enemy enemy : enemies) {
		enemy.draw(g);
	}
}
public void update() {
	for (Enemy enemy : enemies) {
		enemy.update();
	}
}
public Enemy checkIntersection(Player player) {
	for (int i = 0; i < enemies.size(); i++) {
		if (enemies.get(i).collisionBox.intersects(player.collisionBox)){
			return enemies.get(i);
		}
	}
	return null;
}
@Override
public void actionPerformed(ActionEvent e) {
	if(checkIntersection(player)!=null) {
		checkIntersection(player).attack(player);
	}
	
}
}
