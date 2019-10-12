import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
World ultraboss1;
World bossWorld3;
World world3;
World bossWorld4;
World world4;
World bossWorld5;
Key key1;
Key key2;
Key key3;
Key key4;
Key key5;
Key key6;
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
	key3=new Key("key3",false);
	key4=new Key("key4",false);
	key5=new Key("key5",false);
	key6=new Key("key6",false);
	world1=new World(generateEnemies(10,20,20,15,false,null,null,null,25,false,0),new Color(255,255,0),player,true);
	bossWorld1=new World(generateEnemies(1,40,100,45,true,new Sword("grass sword",2,3,false,false,0,false),new Sword("forest blade",2,6,false,false,0,false),key1,120,false,0), new Color(255,0,255),player,false);
	world2=new World(generateEnemies(10,40,50,30,false,null,null,null,70,false,0),new Color(255,255,0),player,false);
	world3=new World(generateEnemies(10,60,100,50,false,null,null,null,150,false,0),new Color(255,255,0),player,false);
	world4=new World(generateEnemies(10,120,350,100,false,null,null,null,500,false,0),new Color(255,255,0),player,false);
	bossWorld2=new World(generateEnemies(1,100,250,100,true,new Sword("water blade",5,6,false,false,0,false),new Sword("ocean sword",7,10,false,false,0,false),key2,250,false,0),new Color(255,0,255),player,false);
	bossWorld3=new World(generateEnemies(1,100,400,175,true,new Sword("super water blade",7,9,false,false,0,false),new Sword("ocean gun",7,12,false,false,0,true),key3,500,false,0),new Color(255,0,255),player,false);
	bossWorld4=new World(generateEnemies(1,160,750,300,true,new Sword("iron gun",6,10,false,false,0,true),new Sword("steel sword",16,20,false,false,0,false),key4,800,false,0),new Color(255,0,255),player,false);
	bossWorld5=new World(generateEnemies(1,200,2000,500,true,new Sword("lava blade",30,40,false,false,0,false),new Sword("lava broadsword",50,65,false,false,0,false),key4,3000,true,0),new Color(255,0,255),player,false);
	ultraboss1=new World(generateEnemies(1,1000000,2147483647,2147483647,true,new Sword("infinity",2147483647,2147483647,false,false,0,false), new Sword("infinity gun",2147483647,2147483647,false,false,0,true),null,2147483647,false,0),new Color(0,0,0),player,false);
	world2.addTeleporter(new Teleporter(495,350,bossWorld2,"right",0,null,"Second Boss",false));
	world2.addTeleporter(new Teleporter(0,400,bossWorld3,"left",13,key2,"Third Boss Req. Second Boss",false));
	world2.addTeleporter(new Teleporter(50,0,world3,"top",18,key3,"Req. Level 18 and Third Boss",false));
	world2.addArmorPlatform(new ArmorPlatform(new Armor("water armor",75,false),12,350,600));
	world3.addArmorPlatform(new ArmorPlatform(new Armor("iron armor",250,false),24,350,600));
	world3.addTeleporter(new Teleporter(100,0,bossWorld4,"top",0,null,"Fourth Boss",false));
	world3.addTeleporter(new Teleporter(495,350,world4,"right",35,key4,"Req. Level 35 and Boss 4",false));
	world4.addArmorPlatform(new ArmorPlatform(new Armor("lava armor",600,false),42,350,600));
	world4.addTeleporter(new Teleporter(495,350,bossWorld5,"right",0,null,"Fifth Boss",false));
	world1.addTeleporter(new Teleporter(495,350,bossWorld1,"right",0,null,"First Boss",false));
	world1.addTeleporter(new Teleporter(100,0,world2,"top",8,key1,"Req. Level 8 and Boss",false));
	world1.addTeleporter(new Teleporter(0,300,ultraboss1,"left",0,null,"???",true));
	world1.addHealingTile(new HealingTile(100,600));
	world1.addArmorPlatform(new ArmorPlatform(new Armor("grass armor",50,false),5,350,600));
	currentWorld=world1;
	weapons.add(new Sword("grass broadsword",2,5,false,true,1000,false));
	weapons.add(new Sword("weak gun",4,5,false,true,5000,true));
	weapons.add(new Sword("omega sword",15,25,false,true,20000,false));
	weapons.add(new Sword("alpha blade",20,25,false,true,25000,false));
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
	inventoryPanel.setLayout(new GridLayout(5,10));
	inventoryWindow.setSize(new Dimension(750,250));
	for (Item item : player.items) {
		JButton itemButton=new JButton();
		itemButton.setBackground(new Color(0,0,0));
		itemButton.setPreferredSize(new Dimension(150,50));
		itemButton.setForeground(new Color(255,255,255));
		itemButton.addActionListener(this);
		if(item instanceof Sword) {
			Sword swordItem=(Sword) item;
			itemButton.setText(swordItem.name+"  "+swordItem.minDamage+"-"+swordItem.maxDamage);
			if (swordItem.isActive) {
				itemButton.setBackground(new Color(0,255,0));
			}
		}else if(item instanceof Armor) {
			Armor armorItem=(Armor) item;
			itemButton.setText(armorItem.name+"  "+armorItem.bonusHealth);
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
			player.x=250;
			player.y=600;
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
		String[] textSplit=source.getText().split("  ");
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
		String[] damages=source.getText().split("  ");
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
	shopPanel.setLayout(new GridLayout(5,10));
	shopWindow.setSize(new Dimension(750,250));
	for (Sword weapon : weapons) {
		JButton shopButton=new JButton();
		shopButton.setBackground(new Color(255,0,0));
		shopButton.setPreferredSize(new Dimension(200,50));
		shopButton.setForeground(new Color(0,0,0));
		shopButton.addActionListener(this);
		shopButton.setText(weapon.name+"  "+weapon.minDamage+"-"+weapon.maxDamage+" "+weapon.cost+" Gold");
		if(player.items.contains(weapon)) {
			shopButton.setBackground(new Color(0,255,0));
		}
		shopPanel.add(shopButton);
	}
	shopWindow.add(shopPanel);
	
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
public ArrayList<Enemy> generateEnemies(int number, int damage, int XPboost, int health, boolean boss, Sword reward, Sword rareReward, Key keyReward, int goldReward, boolean hasGun, int gunType){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		newEnemies.add(new Enemy((RPGgame.WIDTH-50)*rand.nextDouble(),100+(RPGgame.HEIGHT-250)*rand.nextDouble(),health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType));
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
			if(player.items.contains(intersection.keyReward)==false && intersection.keyReward!=null) {
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
