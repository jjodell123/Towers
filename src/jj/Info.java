package jj;

import java.util.ArrayList;

import troops.TroopType;

public class Info {
	
	private static ArrayList<TroopType> troops=new ArrayList<TroopType>();
	private static int width,height;
	public Info(int x,int y)
	{
		width=x;
		height=y;
		troops.add(new TroopType("Gladiator",x/4,x/16,50,30,150,2));
		troops.add(new TroopType("Tank",x/2,x/20,80,40,500,5));
		troops.add(new TroopType("Bowman",x/5,x/14,35,15,75,3));
		troops.add(new TroopType("Cavalry",x/3,x/10,60,35,250,4));
	}
	
	public static int getIndex(String name)
	{
		for(int i=0;i<troops.size();i++)
		{
			if(troops.get(i).getName().equals(name))
				return i;
		}
		return 0;
	}
	
	public static int getSize(int i)
	{
		return troops.get(i).getSize();
	}
	
	public static int getSpeed(int i)
	{
		return troops.get(i).getSpeed();
	}
	
	public static int getDamage(int i)
	{
		return troops.get(i).getDamage();
	}
	
	public static int getHealth(int i)
	{
		return troops.get(i).getHealth();
	}
	
	public static int getAttackSpeed(int i)
	{
		return troops.get(i).getAttackSpeed();
	}
	
	public static String getName(int i)
	{
		return troops.get(i).getName();
	}
	
	public static int getPower(int i)
	{
		return troops.get(i).getPower();
	}
	

}
