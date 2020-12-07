package troops;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import jj.Entity;

public class Cavalry extends Troop{
	
	private int x,y,size,speed,damage,health,team;
	private double healthRatio;
	private int opp=0,adj=0;
	private double angle;
	private Rectangle rect;
	private Point mid;
	private boolean hasNear=false,attack=false;
	private Entity near;
	private int attackTimer=0;
	public Cavalry(int x,int y, int size,int speed,int damage,int health,int attackSpeed,int team)
	{
		super(x,y,size,speed,damage,health,attackSpeed,team);
		super.setType("Cavalry");
		
	}
	public void move()
	{
		super.move();
		setRect();
	}
	
	public void timerCount()
	{
		super.timerCount();
		
		attackTimer++;
	}
	
	public void setAttack(boolean a)
	{
		super.setAttack(a);
	}
	
	public void setRect()
	{
		super.setRect();
	}
	
	public void setNear(int nearX,int nearY,Entity near,int nX,int nY)
	{
		//System.out.println(nearX+" "+nearY);
		//System.out.println("g");
		super.setNear(nearX,nearY,near,nX,nY);
	}
	
	public void setAngle()
	{
		super.setAngle();
	}
	
	public Entity getNear()
	{
		return super.getNear();
	}
	
	public boolean getAttack()
	{
		return super.getAttack();
	}
	
	public int getDamage()
	{
		return super.getDamage();
	}
	
	public boolean hasNear()
	{
		return super.hasNear();
	}
	
	public Point getMid()
	{
		return super.getMid();
	}
	
	public int getTeam()
	{
		return super.getTeam();
	}
	
	public int getX()
	{
		return super.getX();
	}
	
	public int getY()
	{
		return super.getX();
	}
	
	public Rectangle getRect()
	{
		return super.getRect();
	}
	
	public void damage(int damage)
	{
		super.damage(damage);
	}
	
	public void reset()
	{
		super.reset();
	}
	
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.white);

		super.draw(g);
	}

}
