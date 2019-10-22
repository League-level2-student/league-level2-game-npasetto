import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class ArmorPlatform {
Armor giveArmor;
int requiredLevel;
Rectangle collisionBox;
int x;
int y;
ArmorPlatform(Armor giveArmor, int requiredLevel, int x, int y){
	this.x=x;
	this.y=y;
	this.giveArmor=giveArmor;
	this.requiredLevel=requiredLevel;
	collisionBox=new Rectangle(x,y,50,50);
}
void giveArmor(Player player) {
	if(player.items.contains(giveArmor)==false && player.level>=requiredLevel) {
	player.items.add(giveArmor);
	}
}
void draw(Graphics g) {
	g.setColor(new Color(127,127,127));
	g.fillRect(x, y, 50, 50);
	String bonusHealthText;
	if(giveArmor.bonusHealth<1000) {
		bonusHealthText=giveArmor.bonusHealth+"";
	}else {
		bonusHealthText=((double) ((int) (giveArmor.bonusHealth/10)))/100+"K";
	}
	String text=giveArmor.name+"Level: "+requiredLevel+" Health: "+bonusHealthText;
	int textLength=g.getFontMetrics().stringWidth(text);
	g.drawString(text, x+25-textLength/2, y-15);
}
}
