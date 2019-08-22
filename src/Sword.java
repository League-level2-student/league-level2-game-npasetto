
public class Sword extends Item{
	int minDamage;
	int maxDamage;
	Sword(String name, int minDamage, int maxDamage, boolean isActive) {
		super(name,isActive);
		this.minDamage=minDamage;
		this.maxDamage=maxDamage;
	}
	
}
