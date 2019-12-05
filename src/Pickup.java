import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

public class Pickup implements ActionListener {
	int x;
	int y;
	int levelGive;
	Rectangle collisionBox;
	boolean isActive=true;
	Timer respawnTimer;
	Random rand=new Random();
	String giveType;
	int level;
	Pickup(int x,int y,int levelGive,int level){
		this.x=x;
		this.y=y;
		this.levelGive=levelGive;
		this.level=level;
		collisionBox=new Rectangle(x,y,25,25);
		randomizeReward();
		respawnTimer=new Timer(10000,this);
		
	}
	void draw(Graphics g) {
		if(giveType.equals("prestiges")) {
			g.setColor(new Color(0,127,160));
		}else {
			g.setColor(new Color(127,127,50));
		}
		g.fillRect(x, y, 25, 25);
	}
	void giveReward(Player player) {
		if(giveType.equals("gold")) {
			player.gold+=20*level*levelGive;
		}
		if(giveType.equals("XP")) {
			player.gainXP(20*level*levelGive);
		}
		if(giveType.equals("level")) {
			player.level+=levelGive;
			if(player.level>2147483647 && player.prestiges<10) {
				player.level=2147483647;
			}
		}
		if(giveType.equals("prestiges")) {
			if(player.prestiges<10) {
				player.prestiges+=1;
			}
		}
		isActive=false;
		respawnTimer.start();
	}
	void randomizeReward() {
		int chance=rand.nextInt(200);
		if(chance<66) {
			giveType="gold";
		}else if(chance<132) {
			giveType="XP";
		}else if(chance<199) {
			giveType="level";
		}else {
			giveType="prestiges";
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		isActive=true;
		randomizeReward();
		respawnTimer.stop();
	}
}
