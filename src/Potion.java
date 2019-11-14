
public class Potion extends Item{
	String potionType;
	Potion(String name, boolean isActive, String potionType) {
		super(name, isActive);
		this.potionType=potionType;
	}

}
