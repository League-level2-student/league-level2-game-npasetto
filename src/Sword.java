
public class Sword extends Item{
	int minDamage;
	int maxDamage;
	boolean isShop;
	int cost;
	Sword(String name, int minDamage, int maxDamage, boolean isActive, boolean isShop, int cost) {
		super(name,isActive);
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
		this.isShop=isShop;
		this.cost=cost;
	}
	
}
