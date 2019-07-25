import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Player player=new Player(250,600,100,1);
Timer frameDraw;
Font titleFont;
Font textFont;
final int MENU=0;
final int GAME=1;
int currentState=0;
Random rand=new Random();
World world1;
World bossWorld1;
World world2;
World currentWorld;
JButton toSpawn=new JButton();
public GamePanel() {
	frameDraw=new Timer(1000/100, this);
	frameDraw.start();
	titleFont=new Font("Arial",Font.PLAIN,48);
	textFont=new Font("Arial",Font.PLAIN,12);
	world1=new World(generateEnemies(10,20,20,10),new Color(255,255,0),player,true);
	bossWorld1=new World(generateEnemies(1,40,100,30), new Color(255,0,255),player,false);
	world1.addTeleporter(new Teleporter(495,350,bossWorld1,"right",0));
	currentWorld=world1;
}
@Override
public void paintComponent(Graphics g) {
	if(currentState==MENU) {
		drawMenu(g);
	}else {
		drawGame(g);
	}
}
void drawGame(Graphics g) {
	currentWorld.draw(g);
	player.draw(g);
}
void drawMenu(Graphics g) {
	g.setColor(new Color(0,0,255));
	g.fillRect(0, 0, RPGgame.WIDTH, RPGgame.HEIGHT);
	g.setFont(titleFont);
	g.setColor(new Color(255,255,255));
	g.drawString("RPGgame", 130, 120);
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource().equals(toSpawn)) {
		teleportBack();
	}else {
		player.update();
		if(player.health<=0) {
			teleportBack();
			player.health=player.maxHealth;
		}
		currentWorld.update();
		if(currentWorld.checkTeleport(player)!=null) {
			World newWorld=currentWorld.checkTeleport(player).teleportTo;
			currentWorld.isActive=false;
			newWorld.isActive=true;
			currentWorld=newWorld;
		}
		repaint();
	}
}
public void teleportBack() {
	player.x=250;
	player.y=600;
	currentWorld.isActive=false;
	world1.isActive=true;
	currentWorld=world1;
}
@Override
public void keyPressed(KeyEvent arg0) {
	if(arg0.getKeyCode()==KeyEvent.VK_ENTER && currentState==MENU) {
		currentState=GAME;
		toSpawn.setBounds(350, 0, 150, 50);
		toSpawn.setText("Go to spawn");
		toSpawn.addActionListener(this);
		toSpawn.addKeyListener(this);
		toSpawn.addMouseListener(this);
		this.add(toSpawn);
	}
	if(arg0.getKeyCode()==KeyEvent.VK_UP) {
		player.up();
	}
	if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		player.down();
	}
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		player.left();
	}
	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		player.right();
	}
	
}
public ArrayList<Enemy> generateEnemies(int number, int damage, int XPboost, int health){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		newEnemies.add(new Enemy(rand.nextInt(RPGgame.WIDTH-50),100+rand.nextInt(RPGgame.HEIGHT-250),health,damage,XPboost));
	}
	return newEnemies;
}
@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseClicked(MouseEvent arg0) {
	System.out.println("Clicked");
	if(player.canAttack && currentWorld.checkIntersection(player)!=null) {
		player.attack(currentWorld.checkIntersection(player));
		System.out.println("Attacked");
	}
	
}
@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}
