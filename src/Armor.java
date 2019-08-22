
public class Armor extends Item{
	int bonusHealth;
	Armor(String name, int bonusHealth, boolean isActive) {
		super(name,isActive);
		this.bonusHealth=bonusHealth;
	}
	
}
