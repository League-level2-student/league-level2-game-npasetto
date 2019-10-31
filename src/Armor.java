
public class Armor extends Item{
	long bonusHealth;
	Armor(String name, long bonusHealth, boolean isActive) {
		super(name,isActive);
		this.bonusHealth=bonusHealth;
	}
	
}
