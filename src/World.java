import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class World {
ArrayList<Enemy> enemies;
Color backgroundColor;
public World(ArrayList<Enemy> enemies, Color backgroundColor) {
	this.enemies=enemies;
	this.backgroundColor=backgroundColor;
}
public void draw(Graphics g) {
	for (Enemy enemy : enemies) {
		enemy.draw(g);
	}
}
}
