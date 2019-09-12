import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Player player=new Player(250,600,100,1,2);
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
World bossWorld2;
World currentWorld;
Key key1;
Key key2;
JButton toSpawn=new JButton();
JButton inventory=new JButton();
JButton shop=new JButton();
JFrame inventoryWindow;
JFrame shopWindow;
JFrame frame;
ArrayList<Sword> weapons=new ArrayList<Sword>();
public GamePanel() {
	frameDraw=new Timer(1000/100, this);
	frameDraw.start();
	titleFont=new Font("Arial",Font.PLAIN,48);
	textFont=new Font("Arial",Font.PLAIN,12);
	key1=new Key("key1",false);
	key2=new Key("key2",false);
	world1=new World(generateEnemies(10,20,20,15,false,null,null,null,25),new Color(255,255,0),player,true);
	bossWorld1=new World(generateEnemies(1,40,100,45,true,new Sword("sword2",2,3,false,false,0,false),new Sword("sword3",2,6,false,false,0,false),key1,120), new Color(255,0,255),player,false);
	world2=new World(generateEnemies(10,40,50,30,false,null,null,null,70),new Color(255,255,0),player,false);
	bossWorld2=new World(generateEnemies(1,100,250,100,true,new Sword("sword4",5,6,false,false,0,false),new Sword("sword5",7,10,false,false,0,false),key2,250),new Color(255,0,255),player,false);
	world2.addTeleporter(new Teleporter(495,350,bossWorld2,"right",0,null));
	world2.addArmorPlatform(new ArmorPlatform(new Armor("armor2",75,false),12,350,600));
	world1.addTeleporter(new Teleporter(495,350,bossWorld1,"right",0,null));
	world1.addTeleporter(new Teleporter(100,0,world2,"top",8,key1));
	world1.addHealingTile(new HealingTile(100,600));
	world1.addArmorPlatform(new ArmorPlatform(new Armor("armor1",50,false),5,350,600));
	currentWorld=world1;
	weapons.add(new Sword("shop1",2,5,false,true,1000,false));
	weapons.add(new Sword("shopgun1",4,5,false,true,5000,true));
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
void setupGui() {
	inventoryWindow=new JFrame();
	inventoryWindow.setVisible(true);
	JPanel inventoryPanel=new JPanel();
	for (Item item : player.items) {
		JButton itemButton=new JButton();
		itemButton.setBackground(new Color(0,0,0));
		itemButton.setPreferredSize(new Dimension(150,50));
		itemButton.setForeground(new Color(255,255,255));
		itemButton.addActionListener(this);
		if(item instanceof Sword) {
			Sword swordItem=(Sword) item;
			itemButton.setText(swordItem.name+" "+swordItem.minDamage+"-"+swordItem.maxDamage);
			if (swordItem.isActive) {
				itemButton.setBackground(new Color(0,255,0));
			}
		}else if(item instanceof Armor) {
			Armor armorItem=(Armor) item;
			itemButton.setText(armorItem.name+" "+armorItem.bonusHealth);
			if(armorItem.isActive) {
				itemButton.setBackground(new Color(0,255,0));
			}
		}else if(item instanceof Key) {
			Key keyItem=(Key) item;
			itemButton.setText(keyItem.name);
			if(keyItem.isActive) {
				itemButton.setBackground(new Color(0,255,0));
			}
		}
		inventoryPanel.add(itemButton);
	}
	inventoryWindow.add(inventoryPanel);
	inventoryWindow.pack();
}
@Override
public void actionPerformed(ActionEvent arg0) {
	if(arg0.getSource() instanceof JButton) {
		frame=(JFrame) ((JButton) arg0.getSource()).getParent().getParent().getParent().getParent().getParent();
	}
	if(arg0.getSource().equals(toSpawn)) {
		teleportBack();
	}else if(arg0.getSource().equals(inventory)) {
		setupGui();
	}else if(arg0.getSource().equals(frameDraw)){
		player.update();
		if(player.health<=0) {
			teleportBack();
			player.health=player.maxHealth;
		}
		currentWorld.update();
		if(currentWorld.checkTeleport(player)!=null) {
			if(player.level>=currentWorld.checkTeleport(player).requirement && (player.items.contains(currentWorld.checkTeleport(player).requiredKey) || currentWorld.checkTeleport(player).requiredKey==null)) {
			World newWorld=currentWorld.checkTeleport(player).teleportTo;
			currentWorld.isActive=false;
			newWorld.isActive=true;
			currentWorld=newWorld;
			}
		}
		if(currentWorld.checkHealingTile(player)!=null) {
			player.health=player.maxHealth;
		}
		if(currentWorld.checkArmorPlatform(player)!=null) {
			currentWorld.checkArmorPlatform(player).giveArmor(player);
		}
	}else if(arg0.getSource().equals(shop)) {
		setupShop();
	}else if(frame.equals(shopWindow)) {
		JButton source=(JButton) arg0.getSource();
		String[] textSplit=source.getText().split(" ");
		String name=textSplit[0];
		for(Sword weapon : weapons) {
			if(weapon.name.equals(name) && weapon.cost<=player.gold) {
				player.gold-=weapon.cost;
				player.items.add(weapon);
			}
		}
		shopWindow.removeAll();
		shopWindow.dispose();
	}else {
		JButton source=(JButton) arg0.getSource();
		String[] damages=source.getText().split(" ");
		String name=damages[0];
		for (Item item : player.items) {
			if(item.name.equals(name)) {
				String type="";
				if(item instanceof Sword) {
					type="sword";
				}else if(item instanceof Armor) {
					type="armor";
				}else if(item instanceof Key) {
					type="key";
				}
				for (int i = 0; i < player.items.size(); i++) {
					if(player.items.get(i) instanceof Sword && type.equals("sword")) {
						player.items.get(i).isActive=false;
					}else if(player.items.get(i) instanceof Armor && type.equals("armor")) {
						Armor armorItem=(Armor) player.items.get(i);
						if (armorItem.isActive) {
							player.maxHealth-=armorItem.bonusHealth;
							player.health=player.maxHealth;
						}
						player.items.get(i).isActive=false;
					}else if(player.items.get(i) instanceof Key && type.equals("key")) {
						player.items.get(i).isActive=false;
					}
					if(item.equals(player.items.get(i))) {
						player.items.get(i).isActive=true;
					}
				}
				if(type.equals("sword")) {
					Sword swordItem=(Sword) item;
					player.minDamage=swordItem.minDamage;
					player.maxDamage=swordItem.maxDamage;
				}else if(type.equals("armor")) {
					Armor armorItem=(Armor) item;
					player.maxHealth+=armorItem.bonusHealth;
				}
			}
			
		}
		inventoryWindow.removeAll();
		inventoryWindow.dispose();
	}
	repaint();
}
public void setupShop() {
	shopWindow=new JFrame();
	shopWindow.setVisible(true);
	JPanel shopPanel=new JPanel();
	for (Sword weapon : weapons) {
		JButton shopButton=new JButton();
		shopButton.setBackground(new Color(255,0,0));
		shopButton.setPreferredSize(new Dimension(200,50));
		shopButton.setForeground(new Color(0,0,0));
		shopButton.addActionListener(this);
		shopButton.setText(weapon.name+" "+weapon.minDamage+"-"+weapon.maxDamage+" "+weapon.cost+" Gold");
		if(player.items.contains(weapon)) {
			shopButton.setForeground(new Color(0,255,0));
		}
		shopPanel.add(shopButton);
	}
	shopWindow.add(shopPanel);
	shopWindow.pack();
	
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
		inventory.setBounds(180, 0, 150, 50);
		inventory.setText("Inventory");
		inventory.addActionListener(this);
		inventory.addKeyListener(this);
		inventory.addMouseListener(this);
		shop.setBounds(350, 70, 150, 50);
		shop.setText("Shop");
		shop.addActionListener(this);
		shop.addKeyListener(this);
		shop.addMouseListener(this);
		this.add(toSpawn);
		this.add(inventory);
		this.add(shop);
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
public ArrayList<Enemy> generateEnemies(int number, int damage, int XPboost, int health, boolean boss, Sword reward, Sword rareReward, Key keyReward, int goldReward){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		newEnemies.add(new Enemy((RPGgame.WIDTH-50)*rand.nextDouble(),100+(RPGgame.HEIGHT-250)*rand.nextDouble(),health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward));
	}
	return newEnemies;
}
@Override
public void keyReleased(KeyEvent arg0) {
	// TODO Auto-generated method stub TODO
	
}
@Override
public void keyTyped(KeyEvent arg0) {
	// TODO Auto-generated method stub TODO
	
}
@Override
public void mouseClicked(MouseEvent arg0) {
	boolean isGun=true;
	int minDamage=0;
	int maxDamage=0;
	for(Item item:player.items) {
		if(item instanceof Sword && item.isActive==true) {
			isGun=((Sword) item).isGun;
			minDamage=((Sword) item).minDamage;
			maxDamage=((Sword) item).maxDamage;
		}
	}
	if(isGun==false) {
	Enemy intersection=currentWorld.checkIntersection(player);
	if(player.canAttack && intersection!=null) {
		player.attack(intersection);
		int random=rand.nextInt(10);
		Sword reward;
		if(random<9) {
			reward=intersection.reward;
		}else {
			reward=intersection.rareReward;
		}
		if(intersection.health<=0 && intersection.boss) {
			if(player.items.contains(reward)==false) {
			player.items.add(reward);
			}
			if(player.items.contains(intersection.keyReward)==false) {
				player.items.add(intersection.keyReward);
			}
		}
	}
	}else {
	int x=arg0.getX();
	int y=arg0.getY();
	int xdiff=x-player.x;
	int ydiff=y-player.y;
	double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
	currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage));
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
