
public class Sword extends Item{
	long minDamage;
	long maxDamage;
	boolean isShop;
	long cost;
	String weaponType;
	Sword(String name, long minDamage, long maxDamage, boolean isActive, boolean isShop, long cost, String weaponType) {
		super(name,isActive);
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
		this.isShop=isShop;
		this.cost=cost;
		this.weaponType=weaponType;
	}
	
}
