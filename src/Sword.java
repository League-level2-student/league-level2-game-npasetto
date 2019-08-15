
public class Sword extends Item{
	int minDamage;
	int maxDamage;
	boolean isActive;
	Sword(String name, int minDamage, int maxDamage, boolean isActive) {
		super(name);
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
		this.isActive=isActive;
	}
	
}
