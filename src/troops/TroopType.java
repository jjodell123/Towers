package troops;

public class TroopType {

	private int size,speed,damage,health,attackSpeed,power;
	private String name;
	public TroopType(String name, int size,int speed,int damage,int attackSpeed,int health,int power)
	{
		this.size=size;
		this.speed=speed;
		this.damage=damage;
		this.health=health;
		this.attackSpeed=attackSpeed;
		this.name=name;
		this.power=power;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public int getSpeed()
	{
		return speed;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public int getAttackSpeed()
	{
		return attackSpeed;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getPower()
	{
		return power;
	}
	
	
}
