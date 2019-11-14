import java.awt.Color;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class World implements ActionListener {
ArrayList<Enemy> enemies;
ArrayList<Teleporter> teleporters;
ArrayList<HealingTile> tiles;
ArrayList<ArmorPlatform> platforms;
ArrayList<Projectile> projectiles;
ArrayList<EnemyProjectile> enemyShots;
Color backgroundColor;
Timer enemyAttack;
Player player;
boolean isActive;
Random rand=new Random();
double slicerAngle=0;
boolean[][] walls=new boolean[10][10];
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
	projectiles=new ArrayList<Projectile>();
	enemyShots=new ArrayList<EnemyProjectile>();
	for (int i = 0; i < walls.length; i++) {
		for (int j = 0; j < walls[i].length; j++) {
			walls[i][j]=false;
		}
	}
}
public World(ArrayList<Enemy> enemies, Color backgroundColor, Player player, boolean isActive,boolean[][] walls) {
	this.enemies=enemies;
	this.backgroundColor=backgroundColor;
	this.player=player;
	this.isActive=isActive;
	this.walls=walls;
	enemyAttack=new Timer(1000,this);
	enemyAttack.start();
	teleporters=new ArrayList<Teleporter>();
	tiles=new ArrayList<HealingTile>();
	platforms=new ArrayList<ArmorPlatform>();
	projectiles=new ArrayList<Projectile>();
	enemyShots=new ArrayList<EnemyProjectile>();
	
}
public void draw(Graphics g) {
	g.setColor(backgroundColor);
	g.fillRect(0, 0, RPGgame.WIDTH, RPGgame.HEIGHT);
	g.setColor(new Color(0,0,0));
	for (int i = 0; i < walls.length; i++) {
		for (int j = 0; j < walls[i].length; j++) {
			if(walls[i][j]) {
				g.fillRect(j*RPGgame.WIDTH/10, i*RPGgame.HEIGHT/10, RPGgame.WIDTH/10, RPGgame.HEIGHT/10);
			}
		}
	}
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
	for (ArmorPlatform a:platforms) {
		a.draw(g);
	}
	for(Projectile p:projectiles) {
		p.draw(g);
	}
	for(EnemyProjectile e:enemyShots) {
		e.draw(g);
	}
	g.setColor(new Color(0,0,0));
	String levelText;
	if(player.level<1000000000) {
		levelText=((int) player.level)+"";
	}else {
		levelText=((double) ((int) (player.level/100000)))/10000+"B";
	}
	g.drawString("Level: "+levelText, 10, 40);
	String textXP;
	String textMaxXP;
	if(player.XP<1000) {
		textXP=((int) player.XP)+"";
	}else if(player.XP<1000000){
		textXP=((double) ((int) (player.XP/10)))/100+"K";
	}else if(player.XP<1000000000){
		textXP=((double) ((int) (player.XP/10000)))/100+"M";
	}else {
		textXP=((double) ((int) (player.XP/10000000)))/100+"B";
	}
	if(player.level*20<1000) {
		textMaxXP=((int) player.level*20)+"";
	}else if(player.level*20<1000000){
		textMaxXP=((double) ((int) (player.level*2)))/100+"K";
	}else if(player.level*20<1000000000){
		textMaxXP=((double) ((int) (player.level/500)))/100+"M";
	}else {
		textMaxXP=((double) ((int) (player.level/500000)))/100+"B";
	}
	String text="XP: "+textXP+"/"+textMaxXP;
	g.drawString(text, 10, 60);
	String textGold;
	if(player.gold<1000) {
		textGold=((int) player.gold)+"";
	}else if(player.gold<1000000){
		textGold=((double) ((int) (player.gold/10)))/100+"K";
	}else if(player.gold<1000000000){
		textGold=((double) ((int) (player.gold/10000)))/100+"M";
	}else if(player.gold<1000000000000L){
		textGold=((double) ((int) (player.gold/10000000)))/100+"B";
	}else if(player.gold<1000000000000000L){
		textGold=((double) ((int) (player.gold/10000000000L)))/100+"T";
	}else if(player.gold<1000000000000000000L){
		textGold=((double) ((int) (player.gold/10000000000000L)))/100+"Qd";
	}else {
		textGold=((double) ((int) (player.gold/10000000000000000L)))/100+"Qn";
	}
	g.drawString("Gold: "+textGold, 10, 80);
	g.drawString("Prestiges: "+player.prestiges, 10, 100);
}
public void update() {
	for (Enemy enemy : enemies) {
		if(enemy.isAngry) {
			double xdiff=player.x+10-enemy.x;
			double ydiff=player.y+10-enemy.y;
			double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
			enemy.x=enemy.x+xdiff/distance;
			enemy.y=enemy.y+ydiff/distance;
			enemy.updateCollisionBox();
			for (int i = 0; i < walls.length; i++) {
				for (int j = 0; j < walls[i].length; j++) {
					if(walls[i][j]) {
						Rectangle wallBox=new Rectangle(j*RPGgame.WIDTH/10,i*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
						if(enemy.collisionBox.intersects(wallBox)) {
							enemy.x=enemy.x-xdiff/distance;
							enemy.y=enemy.y-ydiff/distance;
							enemy.updateCollisionBox();
							if(enemy.collisionBox.intersects(wallBox)) {
								enemy.x=enemy.x-xdiff/distance;
								enemy.y=enemy.y-ydiff/distance;
								enemy.updateCollisionBox();
							}
						}
						
					}
				}
			} 
			if(enemy.isActive && enemy.hasGun) {
			if(enemy.gunType.equals("slicer") || enemy.gunType.equals("slicer split")) {
				enemyShots.add(new EnemyProjectile(enemy.x+15,enemy.y+15,Math.cos(enemy.slicerAngle),Math.sin(enemy.slicerAngle),enemy.damage,enemy.gunType,true));
				enemy.slicerAngle+=Math.PI/40;
			}else if(enemy.canShoot){
				double speedx=xdiff/distance;
				double speedy=ydiff/distance;
				enemy.timer=0;
				enemy.canShoot=false;
				enemyShots.add(new EnemyProjectile(enemy.x+15,enemy.y+15,speedx,speedy,enemy.damage,enemy.gunType,true));
			}else if(enemy.gunType.equals("dual slicer")) {
				enemyShots.add(new EnemyProjectile(enemy.x+15,enemy.y+15,Math.cos(enemy.slicerAngle),Math.sin(enemy.slicerAngle),enemy.damage,enemy.gunType,true));
				enemyShots.add(new EnemyProjectile(enemy.x+15,enemy.y+15,Math.cos(enemy.slicerAngle+Math.PI),Math.sin(enemy.slicerAngle+Math.PI),enemy.damage,enemy.gunType,true));
				enemy.slicerAngle+=Math.PI/40;
			}
		}
		}
		if(enemy.isActive) {
			double xdiff=player.x-enemy.x;
			double ydiff=player.y-enemy.y;
			double distance=Math.sqrt(xdiff*xdiff+ydiff*ydiff);
			if(distance<50 && player.confusionTimer==0) {
				enemy.isAngry=true;
			}
		}
		enemy.update();
	}
	for (int i = enemyShots.size()-1; i >= 0; i--) {
		enemyShots.get(i).move();
		boolean touchingWall=false;
		for (int j = 0; j < walls.length; j++) {
			for (int k = 0; k < walls[j].length; k++) {
				if(walls[j][k]) {
					Rectangle wallBox=new Rectangle(k*RPGgame.WIDTH/10,j*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
					if(enemyShots.get(i).collisionBox.intersects(wallBox)) {
						touchingWall=true;
					}
				}
			}
		}
		if(enemyShots.get(i).checkWalls() || touchingWall) {
			enemyShots.remove(i);
		}else if(checkEnemyProjectile(enemyShots.get(i))) {
			System.out.println(player.defenseMultiplier);
			player.health-=enemyShots.get(i).damage/player.defenseMultiplier;
			enemyShots.remove(i);
		}else if((enemyShots.get(i).type.equals("split") || enemyShots.get(i).type.equals("double split") || enemyShots.get(i).type.equals("slicer split") || enemyShots.get(i).type.equals("triple split"))  && (enemyShots.get(i).timer>=30 || enemyShots.get(i).isStarting)) {
			if(enemyShots.get(i).type.equals("split")) {
				for (int j = 0; j < 8; j++) {
					enemyShots.add(new EnemyProjectile(enemyShots.get(i).x,enemyShots.get(i).y,rand.nextDouble()*10-5,rand.nextDouble()*10-5,enemyShots.get(i).damage,"normal",false));
				}
				enemyShots.remove(i);
			}else if(enemyShots.get(i).type.equals("double split")) {
				for (int j = 0; j < 8; j++) {
					enemyShots.add(new EnemyProjectile(enemyShots.get(i).x,enemyShots.get(i).y,rand.nextDouble()*10-5,rand.nextDouble()*10-5,enemyShots.get(i).damage,"split",false));
				}
				enemyShots.remove(i);
			}else if(enemyShots.get(i).type.equals("slicer split") && enemyShots.get(i).timer>=30) {
				for (int j = 0; j < 8; j++) {
					enemyShots.add(new EnemyProjectile(enemyShots.get(i).x,enemyShots.get(i).y,rand.nextDouble()*10-5,rand.nextDouble()*10-5,enemyShots.get(i).damage,"normal",false));
				}
				enemyShots.remove(i);
			}else if(enemyShots.get(i).type.equals("triple split")) {
				for (int j = 0; j < 8; j++) {
					enemyShots.add(new EnemyProjectile(enemyShots.get(i).x,enemyShots.get(i).y,rand.nextDouble()*10-5,rand.nextDouble()*10-5,enemyShots.get(i).damage,"double split",false));
				}
				enemyShots.remove(i);
			}
		}
	}
	for (int i = projectiles.size()-1; i >= 0; i--) {
		projectiles.get(i).move();
		boolean touchingWall=false;
		for (int j = 0; j < walls.length; j++) {
			for (int k = 0; k < walls[j].length; k++) {
				if(walls[j][k]) {
					Rectangle wallBox=new Rectangle(k*RPGgame.WIDTH/10,j*RPGgame.HEIGHT/10,RPGgame.WIDTH/10,RPGgame.HEIGHT/10);
					if(projectiles.get(i).collisionBox.intersects(wallBox)) {
						touchingWall=true;
					}
				}
			}
		}
		if((projectiles.get(i).projectileType.equals("exploder") || projectiles.get(i).projectileType.equals("triple split") || projectiles.get(i).projectileType.equals("double split") || projectiles.get(i).projectileType.equals("split")) && projectiles.get(i).exploderTimer>=30) {
			for (double angle = 0; angle<2*Math.PI; angle+=Math.PI/15) {
				if(projectiles.get(i).projectileType.equals("exploder")) {
					projectiles.add(new Projectile(projectiles.get(i).x,projectiles.get(i).y,Math.cos(angle),Math.sin(angle),projectiles.get(i).minDamage,projectiles.get(i).maxDamage,10,"default",0));
				}else if(projectiles.get(i).projectileType.equals("triple split")) {
					projectiles.add(new Projectile(projectiles.get(i).x,projectiles.get(i).y,Math.cos(angle),Math.sin(angle),projectiles.get(i).minDamage,projectiles.get(i).maxDamage,10,"double split",0));
				}else if(projectiles.get(i).projectileType.equals("double split")) {
					projectiles.add(new Projectile(projectiles.get(i).x,projectiles.get(i).y,Math.cos(angle),Math.sin(angle),projectiles.get(i).minDamage,projectiles.get(i).maxDamage,10,"exploder",0));
				}
			}
			projectiles.remove(i);
		}else if(projectiles.get(i).checkWalls() || touchingWall) {
			projectiles.remove(i);
		}else if(checkProjectile(projectiles.get(i))!=null) {
			Enemy intersection=checkProjectile(projectiles.get(i));
			intersection.health-=player.strengthMultiplier*(rand.nextDouble()*projectiles.get(i).maxDamage+projectiles.get(i).minDamage);
			if(player.confusionTimer==0) {
				intersection.isAngry=true;
			}
			if(intersection.health<=0) {
				intersection.isActive=false;
				Item reward;
				int random=rand.nextInt(intersection.dropChance);
				if(random<intersection.dropChance-1) {
					reward=intersection.reward;
				}else {
					reward=intersection.rareReward;
				}
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
				player.gainXP((long) (intersection.XPboost*player.XPMultiplier));
				player.gold+=(long) (intersection.goldReward*player.goldMultiplier);
				if(player.gold<0) {
					player.gold=Long.MAX_VALUE;
				}
			}
			Projectile shot=projectiles.get(i);
			double x=shot.x;
			double y=shot.y;
			if(shot.projectileType.equals("splitter") && shot.splitterCount>0) {
				for(int j=0; j<3; j++) {
					projectiles.add(new Projectile(x+(rand.nextDouble()-0.5)*20,y+(rand.nextDouble()-0.5)*20,(rand.nextDouble()-0.5)*10,(rand.nextDouble()-0.5)*10,shot.minDamage,shot.maxDamage,1,"splitter",shot.splitterCount-1));
				}
			}
			if(shot.projectileType.equals("invisigun")==false) {
				projectiles.remove(i);
			}
		}
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
public Enemy checkProjectile(Projectile p) {
	for (int i = 0; i < enemies.size(); i++) {
		if (enemies.get(i).isActive && enemies.get(i).collisionBox.intersects(p.collisionBox)){
			return enemies.get(i);
		}
	}
	return null;
}
public boolean checkEnemyProjectile(EnemyProjectile e) {
	if (player.collisionBox.intersects(e.collisionBox)){
		return true;
	}
	return false;
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
public void addArmorPlatform(ArmorPlatform a) {
	platforms.add(a);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(checkIntersection(player)!=null && isActive) {
		if(checkIntersection(player).infiniteDamage) {
			player.health=0;
		}
		checkIntersection(player).attack(player);
	}
	
}
}
