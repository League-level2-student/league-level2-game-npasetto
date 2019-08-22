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
	player.items.add(giveArmor);
}
void draw(Graphics g) {
	g.setColor(new Color(127,127,127));
	g.fillRect(x, y, 50, 50);
	g.drawString(giveArmor.name+" Level: "+requiredLevel+" Health: "+giveArmor.bonusHealth, x-50, y-15);
}
}
