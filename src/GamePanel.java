import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
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
boolean mousePressed=false;
boolean upPressed=false;
boolean downPressed=false;
boolean rightPressed=false;
boolean leftPressed=false;
int mouseX=0;
int mouseY=0;
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
World superboss1;
World arena1;
World arenaBoss1;
World arenaBoss2;
World arena2;
World secretWorld1;
World arenaBoss3;
World secretWorld2;
World arenaFinalBoss;
World secretWorld3;
World secretWorld4;
World portalWorld;
World skyRealm;
World windRealm;
World stormRealm;
World skyRealmBoss;
World void1;
World void2;
World void3;
World void4;
World void5;
World void6;
World void7;
World void8;
World void9;
World voidBoss;
World grassCorruption;
World oceanCorruption;
World ironCorruption;
World lavaCorruption;
World corruptionBoss;
World chaos;
World chaosBoss;
World glitchWorld;
World glitch2;
World glitch3;
World glitchBoss;
World infinityTower;
World infinity2;
World secretTower;
World infinity3;
World infinity4;
World infinity5;
World secretTower2;
World secretTower3;
World secretTower4;
World secretTower5;
World towerBoss;
World end1;
World end2;
World end3;
World endBoss1;
World end4;
World secretEnd1;
World endMiniBoss1;
World end5;
World secretEnd2;
World end6;
World endBoss2;
World end7;
World secretEnd3;
World secretEnd4;
World end8;
World secretEnd5;
World endMiniBoss2;
World end9;
World endBoss3;
World endDropWorld;
World endDrop1;
World endDrop2;
World endDrop3;
World endDrop4;
Key key1;
Key key2;
Key key3;
Key key4;
Key key5;
Key key6;
Key arenaKey;
Key darknessKey;
Key destructionKey;
Key deathKey;
Key ultimateKey;
Key skyKey;
Key voidKey;
Key infinityKey;
Key corruptedKey;
Key chaosKey;
Key glitchKey;
Key secretTowerKey;
Key secretTowerKey2;
Key secretTowerKey3;
Key secretTowerKey4;
Key secretTowerKey5;
Key towerKey;
Key solarKey;
Key unknownKey;
Key guardianKey;
Key azureKey;
Key vortexKey;
Key crimsonKey;
Key secondCrimsonKey;
Key thirdCrimsonKey;
Key key;
Key endSecretKey;
Key asteriskKey;
Key pulsarKey;
Key quasarKey;
JButton toSpawn=new JButton();
JButton inventory=new JButton();
JButton shop=new JButton();
JButton prestige=new JButton();
JButton menu=new JButton();
JButton save=new JButton();
JButton load=new JButton();
JButton resetSave=new JButton();
JFrame inventoryWindow;
JFrame shopWindow;
JFrame frame;
JFrame menuFrame;
ArrayList<Sword> weapons=new ArrayList<Sword>();
ArrayList<Enemy> glitchEnemies;
ArrayList<Enemy> glitchEnemies2;
ArrayList<Enemy> glitchEnemies3;
ArrayList<Enemy> endEnemies;
ArrayList<Enemy> endEnemies2;
ArrayList<Enemy> endEnemies3;
ArrayList<Enemy> secretEnemies1;
ArrayList<Enemy> endEnemies4;
ArrayList<Enemy> endEnemies5;
boolean[][] random=new boolean[10][10];
boolean[][] endWalls1=new boolean[10][10];
boolean[][] endWalls2=new boolean[10][10];
boolean[][] endWalls3=new boolean[10][10];
boolean[][] endWalls4=new boolean[10][10];
boolean[][] endWalls5=new boolean[10][10];
boolean[][] endWalls6=new boolean[10][10];
public GamePanel() {
	titleFont=new Font("Arial",Font.PLAIN,48);
	textFont=new Font("Arial",Font.PLAIN,12);
	key1=new Key("Forest Key",false);
	key2=new Key("Water Key",false);
	key3=new Key("Ocean Key",false);
	key4=new Key("Iron Key",false);
	key5=new Key("Lava Key",false);
	key6=new Key("Volcano Key",false);
	arenaKey=new Key("Arena Key",false);
	darknessKey=new Key("Darkness Key",false);
	destructionKey=new Key("Destruction Key",false);
	deathKey=new Key("Death Key",false);
	ultimateKey=new Key("Ultimate Key",false);
	skyKey=new Key("Sky Key",false);
	voidKey=new Key("Void Key",false);
	corruptedKey=new Key("Corrupted Key",false);
	infinityKey=new Key("Infinity Key",false);
	chaosKey=new Key("Chaos Key",false);
	glitchKey=new Key("Glitch Key",false);
	secretTowerKey=new Key("Secret Tower Key",false);
	secretTowerKey2=new Key("Ice Tower Key",false);
	secretTowerKey3=new Key("Fire Tower Key",false);
	secretTowerKey4=new Key("Earth Tower Key",false);
	secretTowerKey5=new Key("Water Tower Key",false);
	towerKey=new Key("Final Tower Key",false);
	solarKey=new Key("Solar Key",false);
	unknownKey=new Key("Unknown Key",false);
	guardianKey=new Key("Guardian Key",false);
	azureKey=new Key("Azure Key",false);
	vortexKey=new Key("Vortex Key",false);
	crimsonKey=new Key("Crimson Key",false);
	secondCrimsonKey=new Key("Second Crimson Key",false);
	thirdCrimsonKey=new Key("Third Crimson Key",false);
	key=new Key("???",false);
	endSecretKey=new Key("Secret End Key",false);
	asteriskKey=new Key("Asterisk Key",false);
	pulsarKey=new Key("Pulsar Key",false);
	quasarKey=new Key("Quasar Key",false);
	for (int i = 0; i < random.length; i++) {
		for (int j = 0; j < random[i].length; j++) {
			random[i][j]=false;
		}
	}
	for (int i = 0; i < endWalls1.length; i++) {
		for (int j = 0; j < endWalls1[i].length; j++) {
			if(j==5) {
				endWalls1[i][j]=true;
			}else {
				endWalls1[i][j]=false;
			}
		}
	}
	for (int i = 0; i < endWalls2.length; i++) {
		for (int j = 0; j < endWalls2[i].length; j++) {
			if((j==5&&i!=0 && i!=9 && i!=5 && i!=4) || (i==5&&j!=0 && j!=9 && j!=5)) {
				endWalls2[i][j]=true;
			}else {
				endWalls2[i][j]=false;
			}
		}
	}
	for (int i = 0; i < endWalls3.length; i++) {
		for (int j = 0; j < endWalls3[i].length; j++) {
			if((j==5&&i==5) || (i==4&&j==9) || (i==3&&j==2) || (j==8 && i==7) || (i==6 && j==3)) {
				endWalls3[i][j]=true;
			}else {
				endWalls3[i][j]=false;
			}
		}
	}
	for (int i = 0; i < endWalls4.length; i++) {
		for (int j = 0; j < endWalls4[i].length; j++) {
			if((j%4==1 && i>0) || (j%4==3 && i<9)) {
				endWalls4[i][j]=true;
			}else {
				endWalls4[i][j]=false;
			}
		}
	}
	for (int i = 0; i < endWalls5.length; i++) {
		for (int j = 0; j < endWalls5[i].length; j++) {
			if((i==8 && j!=0 && j!=9) || ((j==1 || j==8) && i!=0 && i!=9) || (i==1 && j!=0 && j!=9 && j!=5)) {
				endWalls5[i][j]=true;
			}else {
				endWalls5[i][j]=false;
			}
		}
	}
	for (int i = 0; i < endWalls6.length; i++) {
		for (int j = 0; j < endWalls6[i].length; j++) {
			if((i==1 && j!=0 && j!=9) || (j%2==1 && i!=0 && i!=9)) {
				endWalls6[i][j]=false;
			}else {
				endWalls6[i][j]=false;
			}
		}
	}
	world1=new World(generateEnemies(10,20,20,15,false,null,null,null,25,false,"",10,"grass monster",random),new Color(255,255,0),player,true);
	bossWorld1=new World(generateEnemies(1,40,100,45,true,new Sword("grass sword",2,3,false,false,0,"sword"),new Sword("forest blade",2,6,false,false,0,"sword"),key1,120,false,"",10,"grass titan",random), new Color(255,0,255),player,false);
	world2=new World(generateEnemies(10,40,50,30,false,null,null,null,70,false,"",10,"ocean monster",random),new Color(255,255,0),player,false);
	world3=new World(generateEnemies(10,60,100,50,false,null,null,null,150,false,"",10,"iron monster",random),new Color(255,255,0),player,false);
	world4=new World(generateEnemies(10,175,350,100,false,null,null,null,500,false,"",10,"lava monster",random),new Color(255,255,0),player,false);
	arena1=new World(generateEnemies(10,350,5000,1500,false,null,arenaKey,null,5000,false,"",30,"arena monster",random),new Color(255,255,0),player,false);
	arena2=new World(generateEnemies(10,1000,25000,5000,false,null,null,null,25000,false,"",30,"arena titan",random),new Color(255,255,0),player,false);
	skyRealm=new World(generateEnemies(10,10000,1800000,1500000,false,null,null,null,6000000,false,"",10,"sky monster",random),new Color(255,255,0),player,false);
	windRealm=new World(generateEnemies(10,15000,3000000,2500000,false,null,null,null,10000000,false,"",10,"wind monster",random),new Color(255,255,0),player,false);
	stormRealm=new World(generateEnemies(10,20000,5000000,3500000,false,null,null,null,17777777,false,"",10,"storm monster",random),new Color(255,255,0),player,false);
	grassCorruption=new World(generateEnemies(10,100000,100000000,100000000,false,null,null,null,300000000,false,"",10,"corrupted grass monster",random),new Color(255,255,0),player,false);
	oceanCorruption=new World(generateEnemies(10,200000,175000000,150000000,false,null,null,null,500000000,false,"",10,"corrupted ocean monster",random),new Color(255,255,0),player,false);
	ironCorruption=new World(generateEnemies(10,250000,250000000,225000000,false,null,null,null,750000000,false,"",10,"corrupted iron monster",random),new Color(255,255,0),player,false);
	lavaCorruption=new World(generateEnemies(10,300000,750000000,750000000,false,null,null,null,2147483647,false,"",10,"corrupted lava monster",random),new Color(255,255,0),player,false);
	chaos=new World(generateEnemies(10,3000000,3000000000L,3000000000D,false,null,null,null,10000000000L,false,"",10,"chaos monster",random),new Color(255,255,0),player,false);
	glitchEnemies=generateEnemies(5,5000000,8000000000L,7500000000D,false,null,new Sword("glitch gun",800000000,800000000,false,false,0,"gun"),null,20000000000L,false,"",10,"glitch monster",random);
	glitchEnemies.addAll(generateEnemies(5,5000000,12000000000L,10000000000D,false,null,new Sword("glitch laser",100000000,100000000,false,false,0,"laser"),null,30000000000L,false,"",10,"glitch giant",random));
	glitchWorld=new World(glitchEnemies,new Color(255,255,0),player,false);
	glitchEnemies2=generateEnemies(5,5000000,20000000000L,20000000000D,false,null,new Sword("glitch blade",3000000000L,5000000000L,false,false,0,"sword"),null,25000000000L,false,"",10,"glitch titan",random);
	glitchEnemies2.addAll(generateEnemies(5,5000000,30000000000L,30000000000D,false,null,new Sword("glitch exploder",700000000,700000000,false,false,0,"exploder"),null,40000000000L,false,"",10,"glitch lord",random));
	glitch2=new World(glitchEnemies2,new Color(255,255,0),player,false);
	glitchEnemies3=generateEnemies(5,5000000,60000000000L,60000000000D,false,null,new Sword("glitch megalaser",250000000,250000000,false,false,0,"megalaser"),null,25000000000L,false,"",10,"glitch king",random);
	glitchEnemies3.addAll(generateEnemies(5,5000000,100000000000L,100000000000D,false,null,new Sword("virus blade",20000000000L,30000000000L,false,false,0,"sword"),null,40000000000L,false,"",10,"glitch overlord",random));
	glitch3=new World(glitchEnemies3,new Color(255,255,0),player,false);
	secretEnemies1=generateEnemies(30,1,0,1000000000000000D,false,null,null,null,0,false,"",10,"unknown guardian",random);
	secretEnemies1.addAll(generateEnemies(1,1,0,1,false,null,null,unknownKey,0,false,"",10,"unknown key",random));
	secretEnd1=new World(secretEnemies1,new Color(255,255,0),player,false);
	infinityTower=new World(generateEnemies(10,30000000,200000000000L,250000000000D,false,null,new Sword("tower splitter",600000000,750000000,false,false,0,"splitter"),null,300000000000L,false,"",30,"tower monster",random),new Color(255,255,0),player,false);
	infinity2=new World(generateEnemies(15,40000000,400000000000L,600000000000D,false,null,new Sword("tower slicer",10000000000L,10000000000L,false,false,0,"slicer"),null,500000000000L,false,"",30,"tower giant",random),new Color(255,255,0),player,false);
	infinity3=new World(generateEnemies(20,50000000,750000000000L,1200000000000D,false,null,new Sword("tower laser",10000000000L,15000000000L,false,false,0,"laser"),null,987654321098L,false,"",30,"tower titan",random),new Color(255,255,0),player,false);
	infinity4=new World(generateEnemies(20,60000000,1500000000000L,2000000000000D,false,null,new Sword("tower invisigun",100000000000L,300000000000L,false,false,0,"invisigun"),null,1000000000000L,false,"",30,"tower lord",random),new Color(255,255,0),player,false);
	infinity5=new World(generateEnemies(20,60000000,7000000000000L,10000000000000D,false,null,new Sword("tower dual slicer",100000000000L,300000000000L,false,false,0,"dual slicer"),null,5000000000000L,false,"",30,"tower king",random),new Color(255,255,0),player,false);
	secretTower=new World(generateEnemies(50,100000000,0,1,false,null,secretTowerKey,null,0,false,"",300,"???",random),new Color(255,255,0),player,false);
	secretTower2=new World(generateEnemies(1,100000000,0,100000000000000L,true,null,secretTowerKey2,null,0,false,"",3,"ice king",random),new Color(255,255,0),player,false);
	secretTower3=new World(generateEnemies(1,100000000,0,150000000000000L,true,null,secretTowerKey3,null,0,false,"",1,"fire king",random),new Color(255,255,0),player,false);
	secretTower4=new World(generateEnemies(1,100000000,0,200000000000000L,true,null,secretTowerKey4,null,0,false,"",3,"earth king",random),new Color(255,255,0),player,false);
	secretTower5=new World(generateEnemies(1,100000000,0,250000000000000L,true,null,secretTowerKey5,null,0,false,"",3,"water king",random),new Color(255,255,0),player,false);
	endEnemies=generateEnemies(10,250000000,10000000000000L,50000000000000D,false,null,null,null,10000000000000L,false,"",10,"end monster",random);
	endEnemies.addAll(generateEnemies(3,400000000,40000000000000L,200000000000000D,false,null,new Sword("end striker",40000000000L,60000000000L,false,false,0,"splitter laser"),null,50000000000000L,false,"",1,"end titan",random));
	end1=new World(endEnemies,new Color(255,255,0),player,false);
	endEnemies2=generateEnemies(10,300000000,15000000000000L,80000000000000D,false,null,null,null,15000000000000L,false,"",10,"end giant",random);
	endEnemies2.addAll(generateEnemies(2,500000000,60000000000000L,300000000000000D,false,null,new Sword("end invisilaser",100000000000L,100000000000L,false,false,0,"invisilaser"),null,80000000000000L,false,"",1,"end lord",random));
	end2=new World(endEnemies2,new Color(255,255,0),player,false);
	endEnemies3=generateEnemies(10,400000000,20000000000000L,120000000000000D,false,null,null,null,15000000000000L,false,"",10,"end king",random);
	endEnemies3.addAll(generateEnemies(1,600000000,80000000000000L,420000000000000D,false,null,new Sword("end destroyer",60000000000L,70000000000L,false,false,0,"quad invisislicer"),null,111000000000000L,false,"",1,"end overlord",random));
	end3=new World(endEnemies3,new Color(255,255,0),player,false);
	end4=new World(generateEnemies(10,1000000000,200000000000000L,500000000000000D,false,null,null,null,75000000000000L,false,"",10,"crystal titan",random),new Color(255,255,0),player,false);
	endEnemies4=generateEnemies(10,1200000000,280000000000000L,750000000000000D,false,null,null,null,100000000000000L,false,"",10,"unknown giant",random,0,9,0,4);
	endEnemies4.addAll(generateEnemies(10,1350000000,350000000000000L,900000000000000D,false,null,null,null,125000000000000L,false,"",10,"unknown king",random,0,9,6,9));
	end5=new World(endEnemies4,new Color(255,255,0),player,false,endWalls1);
	end6=new World(generateEnemies(15,2000000000,1000000000000000L,5000000000000000D,false,null,null,null,350000000000000L,false,"",10,"azure monster",random),new Color(255,255,0),player,false);
	end7=new World(generateEnemies(10,1200000000,2500000000000000L,10000000000000000D,false,null,crimsonKey,null,1000000000000000L,true,"split",20,"crimson guardian",random),new Color(255,255,0),player,false);
	end8=new World(generateEnemies(10,300000000,6000000000000000L,22220000000000000D,false,null,null,null,3000000000000000L,true,"double split",20,"asterisk giant",random),new Color(255,255,0),player,false);
	end9=new World(generateEnemies(10,1000000000,8000000000000000L,40000000000000000D,false,null,pulsarKey,null,10000000000000000L,true,"split",10,"pulsar titan",random),new Color(255,255,0),player,false);
	endEnemies5=generateEnemies(10,Long.MAX_VALUE,0,Long.MAX_VALUE,false,null,null,key,0,false,"",20,"",random);
	endEnemies5.addAll(generateEnemies(1,0,0,1,false,null,null,endSecretKey,0,false,"",20,"key",random));
	secretEnd5=new World(endEnemies5,new Color(255,255,0),player,false,random);
	endDropWorld=new World(generateEnemies(0,0,0,0,false,null,null,null,0,false,"",2,"",random),new Color(255,0,255),player,false);
	endDrop1=new World(generateEnemies(1,0,0,1,true,null,new Sword("enhanced quasar destroyer",4000000000000L,7000000000000L,false,false,0,"triple split"),null,0,false,"",2,"chance 50%",random),new Color(255,0,255),player,false);
	endDrop2=new World(generateEnemies(1,0,0,1,true,null,new Sword("echo laser",0,400000000000000L,false,false,0,"laser"),null,0,false,"",6,"chance 16.67%",random),new Color(255,0,255),player,false);
	endDrop3=new World(generateEnemies(1,0,0,1,true,null,new Sword("enhanced echo laser",0,550000000000000L,false,false,0,"laser"),null,0,false,"",12,"chance 8.33%",random),new Color(255,0,255),player,false);
	endDrop4=new World(generateEnemies(1,0,0,1,true,null,new Sword("final echo",0,999000000000000L,false,false,0,"circle"),null,0,false,"",300,"chance 0.33%",random,0,6,0,9,false,true,50),new Color(255,0,255),player,false);
	secretEnd2=new World(generateEnemies(1,1500000000,2000000000000000L,20000000000000000D,true,new Sword("azure laser",7500000000000L,12500000000000L,false,false,0,"laser"),null,azureKey,2500000000000000L,false,"",4,"azure titan",endWalls2,5,5,5,5),new Color(255,0,255),player,false,endWalls2);
	secretEnd3=new World(generateEnemies(1,1500000000,5555500000000000L,55555000000000000D,true,new Sword("crimson slicer",50000000000000L,75000000000000L,false,false,0,"slicer"),null,secondCrimsonKey,7500000000000000L,true,"slicer",2,"secret crimson titan",endWalls4,0,9,0,9,true),new Color(255,0,255),player,false,endWalls4);
	secretEnd4=new World(generateEnemies(10,100000000,1000000000000000L,12000000000000000D,true,null,thirdCrimsonKey,null,2000000000000000L,true,"slicer split",8,"crimsonwrath",random),new Color(255,0,255),player,false);
	endBoss3=new World(generateEnemies(1,500000000,33300000000000000L,250000000000000000D,true,new Sword("quasar quad slicer",170000000000000L,200000000000000L,false,false,0,"dual slicer"),new Sword("quasar destroyer",3000000000000L,6000000000000L,false,false,0,"triple split"),quasarKey,50000000000000000L,true,"triple split",4,"QUASAR OVERLORD",endWalls6,0,7,0,9,false,true),new Color(255,0,255),player,false,endWalls6);
	endBoss2=new World(generateEnemies(1,750000000,7500000000000000L,60000000000000000D,true,new Sword("vortex dual slicer",7500000000000L,13000000000000L,false,false,0,"dual slicer"),new Sword("vortex megalaser",8000000000000L,13000000000000L,false,false,0,"megalaser"),vortexKey,10000000000000000L,true,"double split",4,"THE VORTEX",endWalls3),new Color(255,0,255),player,false,endWalls3);
	endMiniBoss1=new World(generateEnemies(1,1000000000,1200000000000000L,12000000000000000D,true,new Sword("unknown striker",200000000000L,250000000000L,false,false,0,"splitter laser"),new Sword("hyper lunaris",300000000000L,400000000000L,false,false,0,"quad invisislicer"),guardianKey,1500000000000000L,false,"",4,"powerful unknown guardian",random),new Color(255,0,255),player,false);
	endMiniBoss2=new World(generateEnemies(1,1000000000,10000000000000000L,100000000000000000D,true,new Sword("asterisk sliceploder",60000000000000L,75000000000000L,false,false,0,"slicer exploder"),null,asteriskKey,13000000000000000L,true,"dual slicer",4,"asterisk king",endWalls5,2,7,2,7),new Color(255,0,255),player,false,endWalls5);
	endBoss1=new World(generateEnemies(1,100000000,1000000000000000L,10000000000000000D,true,new Sword("solaris",150000000000L,200000000000L,false,false,0,"splitter slicer"),new Sword("lunaris",150000000000L,200000000000L,false,false,0,"dual invisislicer"),solarKey,1000000000000000L,true,"split",2,"SOLARIS",random),new Color(255,0,255),player,false);
	towerBoss=new World(generateEnemies(1,100000000,150000000000000L,999000000000000D,true,new Sword("tower quad slicer",150000000000L,350000000000L,false,false,0,"quad slicer"),new Sword("tower invisislicer",100000000000L,100000000000L,false,false,0,"invisislicer"),towerKey,500000000000000L,false,"",4,"elemental overlord",random),new Color(255,0,255),player,false);
	glitchBoss=new World(generateEnemies(1,10000000,5000000000000L,5000000000000D,true,new Sword("virus laser",1500000000,2500000000L,false,false,0,"laser"),new Sword("virus exploder",2147483647,2147483647,false,false,0,"exploder"),glitchKey,2000000000000L,false,"",4,"virus king",random),new Color(255,0,255),player,false);
	corruptionBoss=new World(generateEnemies(1,1000000,5000000000L,5000000000D,true,new Sword("corrupted gun",30000000,50000000,false,false,0,"gun"),new Sword("corrupted exploder",20000000,40000000,false,false,0,"exploder"),corruptedKey,12000000000L,false,"",4,"corruption king",random),new Color(255,0,255),player,false);
	skyRealmBoss=new World(generateEnemies(1,30000,15000000,7500000,true,new Sword("sky blade",0,1000000,false,false,0,"sword"),new Sword("sky gun",600000,800000,false,false,0,"gun"),skyKey,50000000,false,"",6,"sky overlord",random),new Color(255,0,255),player,false);
	void1=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void2=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void3=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void4=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void5=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void destroyer",0,3500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void6=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void7=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void8=new World(generateEnemies(1,70000,25000000,25000000,true,new Sword("void blade",1000000,2000000,false,false,0,"sword"),new Sword("void gun",1000000,1500000,false,false,0,"gun"),null,100000000,false,"",6,"void king",random),new Color(255,0,255),player,false);
	void9=new World(generateEnemies(1,120000,100000000,100000000,true,new Sword("guardian sword",2000000,3000000,false,false,0,"sword"),new Sword("ultimate void gun",0,6000000,false,false,0,"gun"),null,500000000,false,"",6,"void guardian",random),new Color(255,0,255),player,false);
	voidBoss=new World(generateEnemies(1,150000,250000000,250000000,true,new Sword("black hole blade",5000000,10000000,false,false,0,"sword"),new Sword("black hole gun",8000000,8000000,false,false,0,"gun"),voidKey,800000000,false,"",6,"black hole overlord",random),new Color(255,0,255),player,false);
	bossWorld2=new World(generateEnemies(1,100,250,100,true,new Sword("water blade",5,6,false,false,0,"sword"),new Sword("ocean sword",7,10,false,false,0,"sword"),key2,250,false,"",10,"water giant",random),new Color(255,0,255),player,false);
	secretWorld1=new World(generateEnemies(1,1500,0,9999,true,darknessKey,new Sword("blade of darkness",200,1000,false,false,0,"sword"),null,0,false,"",5,"THE DARKNESS",random),new Color(255,0,255),player,false);
	secretWorld2=new World(generateEnemies(1,2500,0,15000,true,destructionKey,new Sword("blade of destruction",700,900,false,false,0,"sword"),null,0,false,"",5,"THE DESTROYER",random),new Color(255,0,255),player,false);
	secretWorld3=new World(generateEnemies(1,4000,0,30000,true,deathKey,new Sword("death sword",0,5000,false,false,0,"sword"),null,0,false,"",5,"Death",random),new Color(255,0,255),player,false);
	secretWorld4=new World(generateEnemies(1,5000,0,60000,true,new Sword("chaos blade",5000,10000,false,false,0,"sword"),new Sword("chaos gun",20000,20000,false,false,0,"gun"),null,0,false,"",5,"Chaos",random),new Color(255,0,255),player,false);
	bossWorld3=new World(generateEnemies(1,100,400,175,true,new Sword("super water blade",7,9,false,false,0,"sword"),new Sword("ocean gun",7,12,false,false,0,"gun"),key3,500,false,"",10,"ocean titan",random),new Color(255,0,255),player,false);
	bossWorld4=new World(generateEnemies(1,160,750,300,true,new Sword("iron gun",6,10,false,false,0,"gun"),new Sword("steel sword",16,20,false,false,0,"sword"),key4,800,false,"",10,"steel giant",random),new Color(255,0,255),player,false);
	bossWorld5=new World(generateEnemies(1,200,2000,500,true,new Sword("lava blade",30,40,false,false,0,"sword"),new Sword("lava broadsword",50,65,false,false,0,"sword"),key5,3000,true,"split",10,"lava titan",random),new Color(255,0,255),player,false);
	arenaBoss1=new World(generateEnemies(1,1000,20000,5000,true,new Sword("arena blade",200,300,false,false,0,"sword"),arenaKey,null,30000,false,"",10,"overseer lord",random),new Color(255,0,255),player,false);
	arenaBoss2=new World(generateEnemies(1,2000,30000,4000,true,new Sword("time sword",0,700,false,false,0,"sword"),new Sword("wormhole gun",200,400,false,false,0,"gun"),null,50000,false,"",10,"time destroyer",random),new Color(255,0,255),player,false);
	arenaBoss3=new World(generateEnemies(1,3500,50000,20000,true,new Sword("omicron blade",500,1200,false,false,0,"sword"),new Sword("omicron gun",800,1200,false,false,0,"gun"),null,100000,false,"",10,"omicron giant",random),new Color(255,0,255),player,false);
	arenaFinalBoss=new World(generateEnemies(1,6000,1000000,999000,true,ultimateKey,new Sword("ultimate sword",50000,150000,false,false,0,"sword"),null,3000000,false,"",10,"arena king",random),new Color(255,0,255),player,false);
	superboss1=new World(generateEnemies(1,500,5000,2000,true,new Sword("volcano gun",0,120,false,false,0,"gun"),new Sword("volcano blade",100,200,false,false,0,"sword"),key6,7500,false,"",10,"volcano giant",random),new Color(255,0,255),player,false);
	chaosBoss=new World(generateEnemies(1,5000000,25000000000L,20000000000L,true,new Sword("chaos laser",0,50000000,false,false,0,"laser"),new Sword("chaos megalaser",0,25000000,false,false,0,"megalaser"),chaosKey,100000000000L,false,"",6,"chaos overlord",random),new Color(255,0,255),player,false);
	portalWorld=new World(generateEnemies(0,0,0,0,false,null,null,null,0,false,"",1,"",random),new Color(255,0,255),player,false);
	ultraboss1=new World(generateEnemies(1,1000000,2147483647,2147483647,true,new Sword("infinity laser",0,4000000,false,false,0,"laser"), null,null,2147483647,false,"",3,"INFINITY",random),new Color(255,255,255),player,false);
	world2.addTeleporter(new Teleporter(495,350,bossWorld2,"right",0,null,"Second Boss",false,0));
	world2.addTeleporter(new Teleporter(0,400,bossWorld3,"left",13,key2,"Third Boss Req. Second Boss",false,0));
	world2.addTeleporter(new Teleporter(50,0,world3,"top",18,key3,"Req. Level 18 and Third Boss",false,0));
	world2.addTeleporter(new Teleporter(350,715,secretWorld4,"bottom",450,deathKey,"",true,0));
	world2.addArmorPlatform(new ArmorPlatform(new Armor("water armor",75,false),12,350,600));
	world3.addArmorPlatform(new ArmorPlatform(new Armor("iron armor",250,false),24,350,600));
	world3.addTeleporter(new Teleporter(100,0,bossWorld4,"top",0,null,"Fourth Boss",false,0));
	world3.addTeleporter(new Teleporter(495,350,world4,"right",35,key4,"Req. Level 35 and Boss 4",false,0));
	world3.addTeleporter(new Teleporter(0,350,secretWorld1,"left",200,arenaKey,"",true,0));
	world4.addArmorPlatform(new ArmorPlatform(new Armor("lava armor",600,false),42,350,600));
	world4.addArmorPlatform(new ArmorPlatform(new Armor("volcano armor",1500,false),60,100,600));
	world4.addTeleporter(new Teleporter(495,350,bossWorld5,"right",0,null,"Fifth Boss",false,0));
	world4.addTeleporter(new Teleporter(0,350,superboss1,"left",65,key5,"Req. Level 65 and Boss 5",false,0));
	world4.addTeleporter(new Teleporter(100,0,arena1,"top",100,key6,"Req. Level 100 and Volcano Key",false,0));
	arena1.addArmorPlatform(new ArmorPlatform(new Armor("arena armor",3000,false),130,350,600));
	arena1.addTeleporter(new Teleporter(0,350,arenaBoss1,"left",150,null,"Req. Level 150",false,0));
	arena1.addTeleporter(new Teleporter(495,350,arenaBoss2,"right",200,arenaKey,"Req. Level 200 and Arena Key",false,0));
	arena1.addTeleporter(new Teleporter(250,715,secretWorld2,"bottom",300,darknessKey,"",true,0));
	arena2.addArmorPlatform(new ArmorPlatform(new Armor("elite arena armor",10000,false),400,350,600));
	arena2.addTeleporter(new Teleporter(495,350,arenaBoss3,"right",450,destructionKey,"Req. Level 450 & Destruction Key",false,0));
	arena2.addTeleporter(new Teleporter(100,0,secretWorld3,"top",450,destructionKey,"",true,0));
	world1.addTeleporter(new Teleporter(495,350,bossWorld1,"right",0,null,"First Boss",false,0));
	world1.addTeleporter(new Teleporter(100,0,world2,"top",8,key1,"Req. Level 8 and Boss",false,0));
	world1.addTeleporter(new Teleporter(0,300,ultraboss1,"left",0,null,"???",true,0));
	world1.addTeleporter(new Teleporter(0,400,arena1,"left",110,key6,"Lv.110 Arena QT",false,0));
	world1.addTeleporter(new Teleporter(495,450,portalWorld,"right",2000,ultimateKey,"Portal World Lv.2000",false,0));
	world1.addHealingTile(new HealingTile(100,600));
	world1.addArmorPlatform(new ArmorPlatform(new Armor("grass armor",50,false),5,350,600));
	skyRealm.addArmorPlatform(new ArmorPlatform(new Armor("sky armor",30000,false),2222,350,600));
	skyRealm.addArmorPlatform(new ArmorPlatform(new Armor("wind armor",75000,false),3333,100,600));
	skyRealm.addTeleporter(new Teleporter(495,350,windRealm,"right",3000,null,"Wind Realm Lv.3000",false,0));
	skyRealm.addTeleporter(new Teleporter(0,350,stormRealm,"left",4000,null,"Storm Realm Lv.4000",false,0));
	skyRealm.addTeleporter(new Teleporter(200,0,skyRealmBoss,"top",5000,null,"Boss Lv.5000",false,0));
	stormRealm.addArmorPlatform(new ArmorPlatform(new Armor("storm armor",150000,false),4500,350,600));
	skyRealmBoss.addArmorPlatform(new ArmorPlatform(new Armor("ultra sky armor",300000,false),8000,350,600));
	grassCorruption.addArmorPlatform(new ArmorPlatform(new Armor("corrupted armor",500000,false),30000,350,600));
	grassCorruption.addTeleporter(new Teleporter(200,0,oceanCorruption,"top",33333,null,"Corruption Lv.33333",false,0));
	oceanCorruption.addTeleporter(new Teleporter(200,0,ironCorruption,"top",40000,null,"Corruption Lv.40000",false,0));
	ironCorruption.addTeleporter(new Teleporter(200,0,lavaCorruption,"top",50000,null,"Corruption Lv.50000",false,0));
	lavaCorruption.addTeleporter(new Teleporter(200,0,corruptionBoss,"top",75000,null,"Corruption King Lv.75000",false,0));
	chaos.addArmorPlatform(new ArmorPlatform(new Armor("chaos armor",10000000,false),444444,350,600));
	infinityTower.addArmorPlatform(new ArmorPlatform(new Armor("tower armor",100000000,false),6500000,350,600));
	infinityTower.addArmorPlatform(new ArmorPlatform(new Armor("elite tower armor",250000000,false),20000000,100,600));
	end1.addArmorPlatform(new ArmorPlatform(new Armor("end armor",1500000000,false),60000000,350,600));
	end4.addArmorPlatform(new ArmorPlatform(new Armor("crystal armor",3500000000L,false),155555555,350,600));
	end7.addArmorPlatform(new ArmorPlatform(new Armor("crimson armor",50000000000L,false),543210123,350,600));
	void1.addTeleporter(new Teleporter(200,0,void3,"top",7500,null,"Void",false,0));
	void1.addTeleporter(new Teleporter(495,350,void4,"right",7500,null,"Void",false,0));
	void1.addTeleporter(new Teleporter(0,350,void2,"left",7500,null,"Void",false,0));
	void2.addTeleporter(new Teleporter(200,0,void1,"top",7500,null,"Void",false,0));
	void2.addTeleporter(new Teleporter(495,350,void4,"right",7500,null,"Void",false,0));
	void2.addTeleporter(new Teleporter(0,350,void3,"left",7500,null,"Void",false,0));
	void3.addTeleporter(new Teleporter(200,0,void4,"top",7500,null,"Void",false,0));
	void3.addTeleporter(new Teleporter(495,350,void1,"right",7500,null,"Void",false,0));
	void3.addTeleporter(new Teleporter(0,350,void2,"left",7500,null,"Void",false,0));
	void4.addTeleporter(new Teleporter(200,0,void5,"top",7500,null,"Void",false,0));
	void4.addTeleporter(new Teleporter(495,350,void1,"right",7500,null,"Void",false,0));
	void4.addTeleporter(new Teleporter(0,350,void3,"left",7500,null,"Void",false,0));
	void5.addTeleporter(new Teleporter(200,0,void2,"top",7500,null,"Void",false,0));
	void5.addTeleporter(new Teleporter(495,350,void4,"right",7500,null,"Void",false,0));
	void5.addTeleporter(new Teleporter(0,350,void6,"left",7500,null,"Void",false,0));
	void6.addTeleporter(new Teleporter(200,0,void5,"top",7500,null,"Void",false,0));
	void6.addTeleporter(new Teleporter(495,350,void7,"right",7500,null,"Void",false,0));
	void6.addTeleporter(new Teleporter(0,350,void2,"left",7500,null,"Void",false,0));
	void7.addTeleporter(new Teleporter(200,0,void8,"top",7500,null,"Void",false,0));
	void7.addTeleporter(new Teleporter(495,350,void3,"right",7500,null,"Void",false,0));
	void7.addTeleporter(new Teleporter(0,350,void1,"left",7500,null,"Void",false,0));
	void8.addTeleporter(new Teleporter(200,0,void1,"top",7500,null,"Void",false,0));
	void8.addTeleporter(new Teleporter(495,350,void9,"right",7500,null,"Void",false,0));
	void8.addTeleporter(new Teleporter(0,350,void2,"left",7500,null,"Void",false,0));
	void9.addTeleporter(new Teleporter(200,0,voidBoss,"top",15000,null,"Void Boss Lv.15000",false,0));
	bossWorld1.addTeleporter(new Teleporter(250,715,world1,"bottom",0,null,"Go Back",false,0));
	bossWorld2.addTeleporter(new Teleporter(250,715,world2,"bottom",0,null,"Go Back",false,0));
	bossWorld3.addTeleporter(new Teleporter(250,715,world2,"bottom",0,null,"Go Back",false,0));
	bossWorld4.addTeleporter(new Teleporter(250,715,world3,"bottom",0,null,"Go Back",false,0));
	bossWorld5.addTeleporter(new Teleporter(250,715,world4,"bottom",0,null,"Go Back",false,0));
	superboss1.addTeleporter(new Teleporter(250,715,world4,"bottom",0,null,"Go Back",false,0));
	arenaBoss1.addTeleporter(new Teleporter(250,715,arena1,"bottom",0,null,"Go Back",false,0));
	arenaBoss2.addTeleporter(new Teleporter(495,350,arena2,"right",300,darknessKey,"Req. Lv.300 & Darkness Key",false,0));
	arenaBoss2.addTeleporter(new Teleporter(250,715,arena1,"bottom",0,null,"Go Back",false,0));
	arenaBoss3.addTeleporter(new Teleporter(250,715,arena2,"bottom",0,null,"Go Back",false,0));
	arenaBoss3.addTeleporter(new Teleporter(495,350,arenaFinalBoss,"right",450,null,"Final Arena Boss",false,0));
	portalWorld.addTeleporter(new Teleporter(495,550,skyRealm,"right",2000,null,"Sky Realm Lv.2K",false,0));
	portalWorld.addTeleporter(new Teleporter(495,450,void1,"right",7500,skyKey,"Void Labyrinth Lv.7.5K",false,0));
	portalWorld.addTeleporter(new Teleporter(495,350,grassCorruption,"right",25000,voidKey,"Corruption Lv.25K",false,0));
	portalWorld.addTeleporter(new Teleporter(495,250,chaos,"right",400000,corruptedKey,"The Chaos Lv.400K Prestige 1",false,1));
	portalWorld.addTeleporter(new Teleporter(495,150,glitchWorld,"right",600000,chaosKey,"Glitch World Lv.600K Prestige 3",false,3));
	portalWorld.addTeleporter(new Teleporter(0,550,infinityTower,"left",5000000,glitchKey,"Infinity Tower Lv.5M Prestige 4",false,4));
	portalWorld.addTeleporter(new Teleporter(0,450,end1,"left",50000000,towerKey,"End Part 1 Lv.50M Prestige 5",false,5));
	portalWorld.addTeleporter(new Teleporter(0,350,end4,"left",150000000,solarKey,"End Part 2 Lv.150M Prestige 5",false,5));
	portalWorld.addTeleporter(new Teleporter(0,250,end7,"left",500000000,vortexKey,"End Part 3 Lv.500M Prestige 5",false,5));
	chaos.addTeleporter(new Teleporter(495,350,chaosBoss,"right",500000,null,"Chaos Overlord Lv.500000 Prestige 2",false,2));
	glitchWorld.addTeleporter(new Teleporter(495,350,glitch2,"right",750000,null,"Glitch World 2 Lv.750000",false,3));
	glitchWorld.addTeleporter(new Teleporter(0,350,glitch3,"left",1400000,null,"Glitch World 3 Lv.1400000",false,3));
	glitchWorld.addTeleporter(new Teleporter(200,0,glitchBoss,"top",2500000,null,"Glitch Boss Lv.2500000",false,3));
	infinityTower.addTeleporter(new Teleporter(200,0,infinity2,"top",8000000,null,"Floor 2 Lv.8000000",false,4));
	infinityTower.addTeleporter(new Teleporter(495,200,secretTower,"right",9000000,null,"",true,4));
	infinity2.addTeleporter(new Teleporter(200,0,infinity3,"top",10000000,secretTowerKey,"Floor 3 Lv.10000000 Req.Secret Tower Key",false,4));
	infinity2.addTeleporter(new Teleporter(0,550,secretTower2,"left",20000000,secretTowerKey,"",true,4));
	infinity3.addTeleporter(new Teleporter(200,0,infinity4,"top",12500000,null,"Floor 4 Lv.12500000",false,4));
	infinity3.addTeleporter(new Teleporter(250,715,secretTower3,"bottom",20000000,secretTowerKey2,"",true,4));
	infinity4.addTeleporter(new Teleporter(200,0,infinity5,"top",15000000,null,"Floor 5 Lv.15000000",false,4));
	infinity4.addTeleporter(new Teleporter(495,715,secretTower4,"bottom",20000000,secretTowerKey3,"",true,4));
	infinity5.addTeleporter(new Teleporter(495,0,secretTower5,"top",20000000,secretTowerKey4,"",false,4));
	infinity5.addTeleporter(new Teleporter(200,0,towerBoss,"top",30000000,secretTowerKey5,"Top Floor Lv.30000000 Req.Elemental Keys",false,4));
	end1.addTeleporter(new Teleporter(495,350,end2,"right",75000000,null,"Lv.75M",false,5));
	end1.addTeleporter(new Teleporter(0,350,end3,"left",100000000,null,"Lv.100M",false,5));
	end1.addTeleporter(new Teleporter(200,0,endBoss1,"top",125000000,null,"ENDBOSS Lv.125M",false,5));
	end4.addTeleporter(new Teleporter(0,0,secretEnd1,"top",156789999,null,"",true,5));
	end4.addTeleporter(new Teleporter(0,350,endMiniBoss1,"left",180000000,unknownKey,"Miniboss Lv.180M Req.Unknown Key",false,5));
	end4.addTeleporter(new Teleporter(200,0,end5,"top",225000000,guardianKey,"Lv.225M Req.Guardian Key",false,5,100,600));
	end5.addTeleporter(new Teleporter(0,350,end5,"left",260000000,null,"Lv.260M",false,5,450,350));
	end5.addTeleporter(new Teleporter(300,0,secretEnd2,"top",275000000,null,"",true,5));
	end5.addTeleporter(new Teleporter(400,715,end6,"bottom",333000000,azureKey,"Lv.333M Req.Azure Key",false,5));
	end6.addTeleporter(new Teleporter(200,0,endBoss2,"top",400000000,null,"ENDBOSS Lv.400M",false,5));
	end7.addTeleporter(new Teleporter(200,0,secretEnd3,"top",555555555,crimsonKey,"",true,5,300,600));
	end7.addTeleporter(new Teleporter(0,350,endDropWorld,"left",933000000,quasarKey,"End Drops Lv.933M Req.Quasar Key",false,5,300,600));
	end7.addTeleporter(new Teleporter(495,350,end8,"right",625000000,thirdCrimsonKey,"Lv.625M Req.Third Crimson Key",false,5));
	end8.addTeleporter(new Teleporter(495,250,secretEnd5,"right",666666666,null,"",true,5));
	end8.addTeleporter(new Teleporter(0,350,endMiniBoss2,"left",700000000,endSecretKey,"Lv.700M Req.Secret End Key",false,5,250,650));
	end8.addTeleporter(new Teleporter(200,0,end9,"top",777000000,asteriskKey,"Lv.777M Req.Asterisk Key",false,5));
	end9.addTeleporter(new Teleporter(200,0,endBoss3,"top",900000000,asteriskKey,"Lv.900M Req.Pulsar Key",false,5));
	secretEnd3.addTeleporter(new Teleporter(370,715,secretEnd4,"bottom",567898765,secondCrimsonKey,"",true,5));
	endDropWorld.addTeleporter(new Teleporter(495,550,endDrop1,"right",950000000,null,"Lv.950M",false,5));
	endDropWorld.addTeleporter(new Teleporter(495,450,endDrop2,"right",970000000,null,"Lv.970M",false,5));
	endDropWorld.addTeleporter(new Teleporter(495,350,endDrop3,"right",1110000000,null,"Lv.1.11B",false,5));
	endDropWorld.addTeleporter(new Teleporter(495,250,endDrop4,"right",1400000000,null,"Lv.1.4B",false,5));
	currentWorld=world1;
	weapons.add(new Sword("grass broadsword",2,5,false,true,1000,"sword"));
	weapons.add(new Sword("weak gun",4,5,false,true,5000,"gun"));
	weapons.add(new Sword("omega sword",15,25,false,true,20000,"sword"));
	weapons.add(new Sword("alpha blade",20,25,false,true,25000,"sword"));
	weapons.add(new Sword("lava sword",80,100,false,true,60000,"sword"));
	weapons.add(new Sword("???",500,500,false,true,999999,"sword"));
	weapons.add(new Sword("portal blade",123456,123456,false,true,25000000,"sword"));
	weapons.add(new Sword("ultra chaos gun",0,300000000,false,true,1500000000000L,"gun"));
	weapons.add(new Sword("secret exploder",75000000000L,125000000000L,false,true,5000000000000000L,"exploder"));
	weapons.add(new Sword("hyper solaris",200000000000L,300000000000L,false,true,300000000000000000L,"splitter slicer"));
	weapons.add(new Sword("mega echo laser",0,800000000000000L,false,true,Long.MAX_VALUE,"laser"));
	frameDraw=new Timer(1000/100, this);
	frameDraw.start();
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
	g.setFont(textFont);
	g.drawString("Press ENTER to start", 190, 200);
}
void setupGui() {
	inventoryWindow=new JFrame();
	inventoryWindow.setVisible(true);
	JPanel inventoryPanel=new JPanel();
	inventoryPanel.setLayout(new GridLayout(20,10));
	inventoryWindow.setSize(new Dimension(150+player.items.size()*15,1000));
	for (Item item : player.items) {
		JButton itemButton=new JButton();
		itemButton.setBackground(new Color(0,0,0));
		itemButton.setPreferredSize(new Dimension(150,50));
		itemButton.setForeground(new Color(255,255,255));
		itemButton.addActionListener(this);
		if(item instanceof Sword) {
			Sword swordItem=(Sword) item;
			String minDamageText;
			String maxDamageText;
			if(swordItem.minDamage<1000) {
				minDamageText=((int) swordItem.minDamage)+"";
			}else if(swordItem.minDamage<1000000){
				minDamageText=((double) ((int) (swordItem.minDamage/10)))/100+"K";
			}else if(swordItem.minDamage<1000000000){
				minDamageText=((double) ((int) (swordItem.minDamage/10000)))/100+"M";
			}else if(swordItem.minDamage<1000000000000L){
				minDamageText=((double) ((int) (swordItem.minDamage/10000000)))/100+"B";
			}else {
				minDamageText=((double) ((int) (swordItem.minDamage/10000000000L)))/100+"T";
			}
			if(swordItem.maxDamage<1000) {
				maxDamageText=((int) swordItem.maxDamage)+"";
			}else if(swordItem.maxDamage<1000000){
				maxDamageText=((double) ((int) (swordItem.maxDamage/10)))/100+"K";
			}else if(swordItem.maxDamage<1000000000){
				maxDamageText=((double) ((int) (swordItem.maxDamage/10000)))/100+"M";
			}else if(swordItem.maxDamage<1000000000000L){
				maxDamageText=((double) ((int) (swordItem.maxDamage/10000000)))/100+"B";
			}else {
				maxDamageText=((double) ((int) (swordItem.maxDamage/10000000000L)))/100+"T";
			}
			itemButton.setText(swordItem.name+"  "+minDamageText+"-"+maxDamageText);
			if (swordItem.isActive) {
				itemButton.setBackground(new Color(0,255,0));
			}
		}else if(item instanceof Armor) {
			Armor armorItem=(Armor) item;
			String bonusHealthText;
			if(armorItem.bonusHealth<1000) {
				bonusHealthText=((int) armorItem.bonusHealth)+"";
			}else if(armorItem.bonusHealth<1000000){
				bonusHealthText=((double) ((int) (armorItem.bonusHealth/10)))/100+"K";
			}else if(armorItem.bonusHealth<1000000000){
				bonusHealthText=((double) ((int) (armorItem.bonusHealth/10000)))/100+"M";
			}else {
				bonusHealthText=((double) ((int) (armorItem.bonusHealth/10000000)))/100+"B";
			}
			itemButton.setText(armorItem.name+"  "+bonusHealthText);
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
	upPressed=false;
	downPressed=false;
	rightPressed=false;
	leftPressed=false;
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
		if(upPressed) {
			player.up();
			for (int i = 0; i < currentWorld.walls.length; i++) {
				for (int j = 0; j <  currentWorld.walls[i].length; j++) {
					if(currentWorld.walls[i][j]) {
						Rectangle wallBox=new Rectangle(j*RPGgame.WIDTH/10,i*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
						if(player.collisionBox.intersects(wallBox)) {
							player.down();
							if(player.collisionBox.intersects(wallBox)) {
								player.down();
							}
						}
					}
				}
			}
		}
		if(downPressed) {
			player.down();
			for (int i = 0; i < currentWorld.walls.length; i++) {
				for (int j = 0; j <  currentWorld.walls[i].length; j++) {
					if(currentWorld.walls[i][j]) {
						Rectangle wallBox=new Rectangle(j*RPGgame.WIDTH/10,i*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
						if(player.collisionBox.intersects(wallBox)) {
							player.up();
							if(player.collisionBox.intersects(wallBox)) {
								player.up();
							}
						}
					}
				}
			}
		}
		if(rightPressed) {
			player.right();
			for (int i = 0; i < currentWorld.walls.length; i++) {
				for (int j = 0; j <  currentWorld.walls[i].length; j++) {
					if(currentWorld.walls[i][j]) {
						Rectangle wallBox=new Rectangle(j*RPGgame.WIDTH/10,i*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
						if(player.collisionBox.intersects(wallBox)) {
							player.left();
							if(player.collisionBox.intersects(wallBox)) {
								player.left();
							}
						}
					}
				}
			}
		}
		if(leftPressed) {
			player.left();
			for (int i = 0; i < currentWorld.walls.length; i++) {
				for (int j = 0; j <  currentWorld.walls[i].length; j++) {
					if(currentWorld.walls[i][j]) {
						Rectangle wallBox=new Rectangle(j*RPGgame.WIDTH/10,i*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
						if(player.collisionBox.intersects(wallBox)) {
							player.right();
							if(player.collisionBox.intersects(wallBox)) {
								player.right();
							}
						}
					}
				}
			}
		}
		if(player.health<=0) {
			teleportBack();
			player.health=player.maxHealth;
		}
		currentWorld.update();
		if(currentWorld.checkTeleport(player)!=null) {
			Key requiredKey=(Key) (currentWorld.checkTeleport(player).requiredKey);
			boolean hasKey=false;
			if(requiredKey==null) {
				hasKey=true;
			}else {
				for (Item item : player.items) {
					if(item.name.equals(requiredKey.name)) {
						hasKey=true;
					}
				}
			}
			if(player.level>=currentWorld.checkTeleport(player).requirement && player.prestiges>=currentWorld.checkTeleport(player).prestigeRequired && (hasKey || currentWorld.checkTeleport(player).requiredKey==null)) {
				player.x=currentWorld.checkTeleport(player).teleportX;
				player.y=currentWorld.checkTeleport(player).teleportY;
				World newWorld=currentWorld.checkTeleport(player).teleportTo;
				currentWorld.isActive=false;
				try {
					newWorld.isActive=true;
				}catch(NullPointerException e) {
					System.err.println("World does not exist");
				}
				currentWorld=newWorld;
			}
		}
		if(currentWorld.checkHealingTile(player)!=null) {
			player.health=player.maxHealth;
		}
		if(currentWorld.checkArmorPlatform(player)!=null) {
			currentWorld.checkArmorPlatform(player).giveArmor(player);
		}
		long minDamage=0;
		long maxDamage=0;
		String weaponType="";
		for(Item item:player.items) {
			if(item instanceof Sword && item.isActive==true) {
				minDamage=((Sword) item).minDamage;
				maxDamage=((Sword) item).maxDamage;
				weaponType=((Sword) item).weaponType;
			}
		}
		if(mousePressed) {
			if(weaponType.equals("laser")) {
				int x=mouseX;
				int y=mouseY;
				int xdiff=x-player.x;
				int ydiff=y-player.y;
				double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
				currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,10,"default",0));
			}else if(weaponType.equals("megalaser")) {
				int x=mouseX;
				int y=mouseY;
				int xdiff=x-player.x;
				int ydiff=y-player.y;
				double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
				for (int i = 0; i < 5; i++) {
					currentWorld.projectiles.add(new Projectile(player.x+rand.nextDouble()*50,player.y+rand.nextDouble()*50,xdiff/distance,ydiff/distance,minDamage,maxDamage,10,"default",0));
				}
			}else if(weaponType.equals("slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"default",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("dual slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"default",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,10,"default",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("quad slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"default",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI/2),Math.sin(currentWorld.slicerAngle+Math.PI/2),minDamage,maxDamage,10,"default",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,10,"default",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+3*Math.PI/2),Math.sin(currentWorld.slicerAngle+3*Math.PI/2),minDamage,maxDamage,10,"default",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("invisislicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("splitter laser")) {
				int x=mouseX;
				int y=mouseY;
				int xdiff=x-player.x;
				int ydiff=y-player.y;
				double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
				currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,10,"splitter",3));
			}else if(weaponType.equals("invisilaser")) {
				int x=mouseX;
				int y=mouseY;
				int xdiff=x-player.x;
				int ydiff=y-player.y;
				double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
				currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,3,"invisigun",3));
			}else if(weaponType.equals("dual invisislicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("quad invisislicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI/2),Math.sin(currentWorld.slicerAngle+Math.PI/2),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+3*Math.PI/2),Math.sin(currentWorld.slicerAngle+3*Math.PI/2),minDamage,maxDamage,3,"invisigun",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("splitter slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"splitter",3));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("dual splitter slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"splitter",3));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,10,"splitter",3));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("quad splitter slicer")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"splitter",3));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI/2),Math.sin(currentWorld.slicerAngle+Math.PI/2),minDamage,maxDamage,10,"splitter",3));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+Math.PI),Math.sin(currentWorld.slicerAngle+Math.PI),minDamage,maxDamage,10,"splitter",3));
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle+3*Math.PI/2),Math.sin(currentWorld.slicerAngle+3*Math.PI/2),minDamage,maxDamage,10,"splitter",3));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("slicer exploder")) {
				currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,10,"exploder",0));
				currentWorld.slicerAngle+=Math.PI/30;
			}else if(weaponType.equals("circle")) {
				for (int i = 0; i < 3; i++) {
					currentWorld.projectiles.add(new Projectile(player.x+25,player.y+25,Math.cos(currentWorld.slicerAngle),Math.sin(currentWorld.slicerAngle),minDamage,maxDamage,3,"default",0));
					currentWorld.slicerAngle+=4*Math.PI/(1+Math.sqrt(5));
				}
			}
		}
	}else if(arg0.getSource().equals(shop)) {
		setupShop();
	}else if(frame.equals(shopWindow)) {
		JButton source=(JButton) arg0.getSource();
		String[] textSplit=source.getText().split("  ");
		String name=textSplit[0];
		for(Sword weapon : weapons) {
			if(weapon.name.equals(name) && weapon.cost<=player.gold) {
				boolean contains=false;
				for(Item item:player.items) {
					if(weapon.name.equals(item.name)) {
						contains=true;
					}
				}
				if(contains==false) {
					player.gold-=weapon.cost;
					player.items.add(weapon);
				}
			}
		}
		shopWindow.removeAll();
		shopWindow.dispose();
	}else if(arg0.getSource().equals(prestige)){
		if(player.level>=player.levelRequired) {
			player.level=1;
			player.XP=0;
			player.gold=0;
			teleportBack();
			player.items.clear();
			player.items.add(new Sword("bronze sword",1,2,true,false,0,"sword"));
			player.items.add(new Sword("prestige sword 1",1,250,false,false,0,"sword"));
			if(player.prestiges>0) {
				player.items.add(new Sword("prestige sword 2",1,2000,false,false,0,"sword"));
			}
			if(player.prestiges>1) {
				player.items.add(new Sword("prestige sword 3",1,25000,false,false,0,"sword"));
			}
			if(player.prestiges>2) {
				player.items.add(new Sword("prestige sword 4",1,500000,false,false,0,"sword"));
			}
			if(player.prestiges>3) {
				player.items.add(new Sword("the final prestige",1,2147483647,false,false,0,"slicer"));
			}
			player.XPMultiplier*=1.5;
			player.goldMultiplier*=1.5;
			player.prestiges++;
			player.levelRequired*=10;
			prestige.setText("Prestige: Level "+player.levelRequired);
			player.minDamage=1;
			player.maxDamage=2;
			player.health=100;
			player.maxHealth=100;
		}
	}else if(arg0.getSource().equals(menu)){
		setupMenu();
	}else if(arg0.getSource().equals(save)){
		createSave();
	}else if(arg0.getSource().equals(load)){
		loadSave();
	}else if(arg0.getSource().equals(resetSave)) {
		resetSave();
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
public void resetSave() {
	try {
		FileOutputStream out=new FileOutputStream("./src/SaveData.txt");
		out.write("".getBytes());
		out.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
public void setupMenu() {
	menuFrame=new JFrame();
	menuFrame.setVisible(true);
	JPanel menuPanel=new JPanel();
	menuPanel.add(toSpawn);
	menuPanel.add(inventory);
	menuPanel.add(shop);
	menuPanel.add(prestige);
	menuPanel.add(save);
	menuPanel.add(load);
	menuPanel.add(resetSave);
	menuFrame.add(menuPanel);
	menuFrame.pack();
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
		String minDamageText;
		String maxDamageText;
		String costText;
		if(weapon.minDamage<1000) {
			minDamageText=((int) weapon.minDamage)+"";
		}else if(weapon.minDamage<1000000){
			minDamageText=((double) ((int) (weapon.minDamage/10)))/100+"K";
		}else if(weapon.minDamage<1000000000){
			minDamageText=((double) ((int) (weapon.minDamage/10000)))/100+"M";
		}else if(weapon.minDamage<1000000000000L){
			minDamageText=((double) ((int) (weapon.minDamage/10000000)))/100+"B";
		}else {
			minDamageText=((double) ((int) (weapon.minDamage/10000000000L)))/100+"T";
		}
		if(weapon.maxDamage<1000) {
			maxDamageText=((int) weapon.maxDamage)+"";
		}else if(weapon.maxDamage<1000000){
			maxDamageText=((double) ((int) (weapon.maxDamage/10)))/100+"K";
		}else if(weapon.maxDamage<1000000000){
			maxDamageText=((double) ((int) (weapon.maxDamage/10000)))/100+"M";
		}else if(weapon.maxDamage<1000000000000L){
			maxDamageText=((double) ((int) (weapon.maxDamage/10000000)))/100+"B";
		}else {
			maxDamageText=((double) ((int) (weapon.maxDamage/10000000000L)))/100+"B";
		}
		if(weapon.cost<1000) {
			costText=((int) weapon.cost)+"";
		}else if(weapon.cost<1000000){
			costText=((double) ((int) (weapon.cost/10)))/100+"K";
		}else if(weapon.cost<1000000000){
			costText=((double) ((int) (weapon.cost/10000)))/100+"M";
		}else if(weapon.cost<1000000000000L){
			costText=((double) ((int) (weapon.cost/10000000)))/100+"B";
		}else if(weapon.cost<1000000000000000L) {
			costText=((double) ((int) (weapon.cost/10000000000L)))/100+"T";
		}else if(weapon.cost<1000000000000000000L){
			costText=((double) ((int) (weapon.cost/10000000000000L)))/100+"Qd";
		}else {
			costText=((double) ((int) (weapon.cost/10000000000000000L)))/100+"Qn";
		}
		shopButton.setText(weapon.name+"  "+minDamageText+"-"+maxDamageText+" "+costText+" Gold");
		if(player.items.contains(weapon)) {
			shopButton.setBackground(new Color(0,255,0));
		}
		shopPanel.add(shopButton);
	}
	shopWindow.add(shopPanel);
}
public void createSave() {
	String data=player.level+"~"+player.XP+"~"+player.gold+"~"+player.prestiges+"~";
	for (Item item : player.items) {
		if(item instanceof Key) {
			data+=(item.name+"*");
		}
		if(item instanceof Armor) {
			Armor armor=(Armor) item;
			data+=(armor.name+"#"+armor.bonusHealth+"*");
		}
		if(item instanceof Sword) {
			Sword sword=(Sword) item;
			data+=(sword.name+"#"+sword.minDamage+"#"+sword.maxDamage+"#"+sword.weaponType+"*");
		}
	}
	try {
		FileOutputStream out=new FileOutputStream("./src/SaveData.txt");
		out.write(data.getBytes());
		out.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public void loadSave() {
	File file=new File("./src/SaveData.txt");
	try {
		BufferedReader br=new BufferedReader(new FileReader(file));
		String data=br.readLine();
		br.close();
		if(data!=null) {
			String[] firstSplit=data.split("~");
			player.level=Long.parseLong(firstSplit[0]);
			player.maxHealth=(player.level*25)+75;
			player.health=player.maxHealth;
			player.XP=Long.parseLong(firstSplit[1]);
			player.gold=Long.parseLong(firstSplit[2]);
			player.prestiges=Integer.parseInt(firstSplit[3]);
			player.XPMultiplier=Math.pow(1.5, player.prestiges);
			player.goldMultiplier=Math.pow(1.5, player.prestiges);
			player.levelRequired=(int) (1000*Math.pow(10, player.prestiges));
			player.items.clear();
			String[] secondSplit=firstSplit[4].split("\\*");
			for (String string : secondSplit) {
				String[] thirdSplit=string.split("#");
				if(thirdSplit.length==1) {
					player.items.add(new Key(thirdSplit[0],false));
				}else if(thirdSplit.length==2) {
					player.items.add(new Armor(thirdSplit[0],Long.parseLong(thirdSplit[1]),false));
				}else {
					player.items.add(new Sword(thirdSplit[0],Long.parseLong(thirdSplit[1]),Long.parseLong(thirdSplit[2]),false,false,0,thirdSplit[3]));
				}
			}
		}
	}catch(Exception e) {
		e.printStackTrace();
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
		toSpawn.setText("Go to spawn");
		toSpawn.addActionListener(this);
		toSpawn.addKeyListener(this);
		toSpawn.addMouseListener(this);
		inventory.setText("Inventory");
		inventory.addActionListener(this);
		inventory.addKeyListener(this);
		inventory.addMouseListener(this);
		shop.setText("Shop");
		shop.addActionListener(this);
		shop.addKeyListener(this);
		shop.addMouseListener(this);
		prestige.setText("Prestige: Level 1000");
		prestige.addActionListener(this);
		prestige.addKeyListener(this);
		prestige.addMouseListener(this);
		menu.setBounds(350,0,150,50);
		menu.setText("Menu");
		menu.addActionListener(this);
		menu.addKeyListener(this);
		menu.addMouseListener(this);
		save.setText("Save");
		save.addActionListener(this);
		save.addKeyListener(this);
		save.addMouseListener(this);
		load.setText("Load");
		load.addActionListener(this);
		load.addKeyListener(this);
		load.addMouseListener(this);
		resetSave.setText("Reset Save");
		resetSave.addActionListener(this);
		resetSave.addKeyListener(this);
		resetSave.addMouseListener(this);
		this.add(menu);
	}
	if(arg0.getKeyCode()==KeyEvent.VK_UP) {
		upPressed=true;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		downPressed=true;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		leftPressed=true;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		rightPressed=true;
	}
}
public ArrayList<Enemy> generateEnemies(int number, long damage, long XPboost, double health, boolean boss, Item reward, Item rareReward, Key keyReward, long goldReward, boolean hasGun, String gunType, int dropChance, String enemyName,boolean[][] walls){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		int newi=rand.nextInt(8);
		int newj=rand.nextInt(10);
		while(walls[newi][newj]) {
			newi=rand.nextInt(8);
			newj=rand.nextInt(10);
		}
		int xstart=newj*RPGgame.WIDTH/10;
		int ystart=newi*RPGgame.HEIGHT/10;
		int xend=xstart+RPGgame.WIDTH/10-30;
		int yend=ystart+RPGgame.HEIGHT/10-30;
		if(newj<9) {
			if(walls[newi][newj+1]==false) {
				xend+=30;
			}
		}
		if(newi<9) {
			if(walls[newi+1][newj]==false) {
				yend+=30;
			}
		}
		int randomX=xstart+rand.nextInt(xend-xstart);
		int randomY=ystart+rand.nextInt(yend-ystart);
		newEnemies.add(new Enemy(randomX,randomY,health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType,dropChance,enemyName,false,false,5000));
	}
	return newEnemies;
}
public ArrayList<Enemy> generateEnemies(int number, long damage, long XPboost, double health, boolean boss, Item reward, Item rareReward, Key keyReward, long goldReward, boolean hasGun, String gunType, int dropChance, String enemyName,boolean[][] walls, int imin, int imax, int jmin, int jmax){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		int newi=rand.nextInt(imax-imin+1)+imin;
		int newj=rand.nextInt(jmax-jmin+1)+jmin;
		while(walls[newi][newj]) {
			newi=rand.nextInt(imax-imin+1)+imin;
			newj=rand.nextInt(jmax-jmin+1)+jmin;
		}
		int xstart=newj*RPGgame.WIDTH/10;
		int ystart=newi*RPGgame.HEIGHT/10;
		int xend=xstart+RPGgame.WIDTH/10-30;
		int yend=ystart+RPGgame.HEIGHT/10-30;
		if(newj<jmax) {
			if(walls[newi][newj+1]==false) {
				xend+=30;
			}
		}
		if(newi<imax) {
			if(walls[newi+1][newj]==false) {
				yend+=30;
			}
		}
		int randomX=xstart+rand.nextInt(xend-xstart);
		int randomY=ystart+rand.nextInt(yend-ystart);
		newEnemies.add(new Enemy(randomX,randomY,health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType,dropChance,enemyName,false,false,5000));
	}
	return newEnemies;
}
public ArrayList<Enemy> generateEnemies(int number, long damage, long XPboost, double health, boolean boss, Item reward, Item rareReward, Key keyReward, long goldReward, boolean hasGun, String gunType, int dropChance, String enemyName,boolean[][] walls, int imin, int imax, int jmin, int jmax, boolean isSecret){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		int newi=rand.nextInt(imax-imin+1)+imin;
		int newj=rand.nextInt(jmax-jmin+1)+jmin;
		while(walls[newi][newj]) {
			newi=rand.nextInt(imax-imin+1)+imin;
			newj=rand.nextInt(jmax-jmin+1)+jmin;
		}
		int xstart=newj*RPGgame.WIDTH/10;
		int ystart=newi*RPGgame.HEIGHT/10;
		int xend=xstart+RPGgame.WIDTH/10-30;
		int yend=ystart+RPGgame.HEIGHT/10-30;
		if(newj<jmax) {
			if(walls[newi][newj+1]==false) {
				xend+=30;
			}
		}
		if(newi<imax) {
			if(walls[newi+1][newj]==false) {
				yend+=30;
			}
		}
		int randomX=xstart+rand.nextInt(xend-xstart);
		int randomY=ystart+rand.nextInt(yend-ystart);
		newEnemies.add(new Enemy(randomX,randomY,health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType,dropChance,enemyName,isSecret,false,5000));
	}
	return newEnemies;
}
public ArrayList<Enemy> generateEnemies(int number, long damage, long XPboost, double health, boolean boss, Item reward, Item rareReward, Key keyReward, long goldReward, boolean hasGun, String gunType, int dropChance, String enemyName,boolean[][] walls, int imin, int imax, int jmin, int jmax, boolean isSecret, boolean infiniteDamage){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		int newi=rand.nextInt(imax-imin+1)+imin;
		int newj=rand.nextInt(jmax-jmin+1)+jmin;
		while(walls[newi][newj]) {
			newi=rand.nextInt(imax-imin+1)+imin;
			newj=rand.nextInt(jmax-jmin+1)+jmin;
		}
		int xstart=newj*RPGgame.WIDTH/10;
		int ystart=newi*RPGgame.HEIGHT/10;
		int xend=xstart+RPGgame.WIDTH/10-30;
		int yend=ystart+RPGgame.HEIGHT/10-30;
		if(newj<jmax) {
			if(walls[newi][newj+1]==false) {
				xend+=30;
			}
		}
		if(newi<imax) {
			if(walls[newi+1][newj]==false) {
				yend+=30;
			}
		}
		int randomX=xstart+rand.nextInt(xend-xstart);
		int randomY=ystart+rand.nextInt(yend-ystart);
		newEnemies.add(new Enemy(randomX,randomY,health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType,dropChance,enemyName,isSecret,infiniteDamage,5000));
	}
	return newEnemies;
}
public ArrayList<Enemy> generateEnemies(int number, long damage, long XPboost, double health, boolean boss, Item reward, Item rareReward, Key keyReward, long goldReward, boolean hasGun, String gunType, int dropChance, String enemyName,boolean[][] walls, int imin, int imax, int jmin, int jmax, boolean isSecret, boolean infiniteDamage, int respawn){
	ArrayList<Enemy> newEnemies=new ArrayList<Enemy>();
	for (int i = 0; i < number; i++) {
		int newi=rand.nextInt(imax-imin+1)+imin;
		int newj=rand.nextInt(jmax-jmin+1)+jmin;
		while(walls[newi][newj]) {
			newi=rand.nextInt(imax-imin+1)+imin;
			newj=rand.nextInt(jmax-jmin+1)+jmin;
		}
		int xstart=newj*RPGgame.WIDTH/10;
		int ystart=newi*RPGgame.HEIGHT/10;
		int xend=xstart+RPGgame.WIDTH/10-30;
		int yend=ystart+RPGgame.HEIGHT/10-30;
		if(newj<jmax) {
			if(walls[newi][newj+1]==false) {
				xend+=30;
			}
		}
		if(newi<imax) {
			if(walls[newi+1][newj]==false) {
				yend+=30;
			}
		}
		int randomX=xstart+rand.nextInt(xend-xstart);
		int randomY=ystart+rand.nextInt(yend-ystart);
		newEnemies.add(new Enemy(randomX,randomY,health,damage,XPboost,goldReward,boss,reward,rareReward,keyReward,hasGun,gunType,dropChance,enemyName,isSecret,infiniteDamage,respawn));
	}
	return newEnemies;
}
@Override
public void keyReleased(KeyEvent arg0) {
	if(arg0.getKeyCode()==KeyEvent.VK_UP) {
		upPressed=false;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
		downPressed=false;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
		rightPressed=false;
	}
	if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
		leftPressed=false;
	}
}
@Override
public void keyTyped(KeyEvent arg0) {
}
@Override
public void mouseClicked(MouseEvent arg0) {
	String weaponType = "";
	long minDamage=0;
	long maxDamage=0;
	for(Item item:player.items) {
		if(item instanceof Sword && item.isActive==true) {
			weaponType=((Sword) item).weaponType;
			minDamage=((Sword) item).minDamage;
			maxDamage=((Sword) item).maxDamage;
		}
	}
	if(weaponType.equals("sword")) {
		Enemy intersection=currentWorld.checkIntersection(player);
		if(player.canAttack && intersection!=null) {
			player.attack(intersection);
			int random=rand.nextInt(intersection.dropChance);
			Item reward;
			if(random<intersection.dropChance-1) {
				reward=intersection.reward;
			}else {
				reward=intersection.rareReward;
			}
			if(intersection.health<=0 && intersection.isActive) {
				boolean contains=false;
				if(reward!=null) {
					for (Item item : player.items) {
						if(reward.name.equals(item.name)) {
							contains=true;
						}
					}
				}
				if(contains==false && reward!=null) {
					player.items.add(reward);
				}
				contains=false;
				if(intersection.keyReward!=null) {
					for (Item item : player.items) {
						if(intersection.keyReward.name.equals(item.name)) {
							contains=true;
						}
					}
				}
				if(contains==false && intersection.keyReward!=null) {
					player.items.add(intersection.keyReward);
				}
			}
		}
	}else if(weaponType.equals("gun") || weaponType.equals("splitter") || weaponType.equals("invisigun") || weaponType.equals("triple split")){
		int x=arg0.getX();
		int y=arg0.getY();
		int xdiff=x-player.x;
		int ydiff=y-player.y;
		double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
		if(weaponType.equals("gun")) {
			currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,3,"default",0));
		}else if(weaponType.equals("splitter")) {
			currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,3,"splitter",5));
		}else if(weaponType.equals("invisigun")) {
			currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,3,"invisigun",0));
		}else if(weaponType.equals("triple split")) {
			currentWorld.projectiles.add(new Projectile(player.x,player.y,xdiff/distance,ydiff/distance,minDamage,maxDamage,3,"triple split",0));
		}
	}else if(weaponType.equals("exploder")) {
		for (double theta = 0; theta < 2*Math.PI; theta+=Math.PI/20) {
			currentWorld.projectiles.add(new Projectile(player.x,player.y,Math.cos(theta),Math.sin(theta),minDamage,maxDamage,10,"default",0));
		}
	}
}
@Override
public void mouseEntered(MouseEvent arg0) {
}
@Override
public void mouseExited(MouseEvent arg0) {	
}
@Override
public void mousePressed(MouseEvent arg0) {
	mousePressed=true;
	mouseX=arg0.getX();
	mouseY=arg0.getY();
}
@Override
public void mouseReleased(MouseEvent e) {
	mousePressed=false;
}
}
