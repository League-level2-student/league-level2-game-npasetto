
public class Armor extends Item{
	int bonusHealth;
	boolean isActive;
	Armor(String name, int bonusHealth, boolean isActive) {
		super(name);
		this.bonusHealth=bonusHealth;
		this.isActive=isActive;
	}
	
}
