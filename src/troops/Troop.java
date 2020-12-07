package troops;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import jj.Entity;

public class Troop extends Entity{
	
	//private int size,speed,damage,health,team;
	private double x,y;
	private int nearX=0,nearY=0;
//	private double healthRatio;
//	private int opp=0,adj=0;
//	private double angle;
	private Rectangle rect;
	private Point mid;
	private boolean hasNear=false,attack=false;
	//private boolean skip=false;
	//private Entity near;
	//private int attackTimer,attackSpeed;
	public Troop(int x,int y, int size,int speed,int damage,int health,int attackSpeed,int team)
	{
		super(x,y,size,speed,damage,health,attackSpeed,team);
		this.x=x;
		this.y=y;
	/*	this.size=size;
		this.speed=speed;
		this.damage=damage;
		this.health=health;
		this.team=team;
		this.attackSpeed=attackSpeed;*/
		//System.out.println(this.team);
		rect=new Rectangle(x,y,size,size);
		mid=new Point(x+(size/2),y+(size/2));
	//	healthRatio=(double)health/size;
	}
	
	public void setType(String type)
	{
		super.setType(type);
	}
	
	public void timerCount()
	{
		super.timerCount();
//		attackTimer++;
	}
	
	public void move()
	{
		//System.out.println(attack);
		if(!attack)
		{
			//System.out.println(x);
			super.move();
	//		x-=Math.cos(angle)*speed;
		//	y-=Math.sin(angle)*speed;
			setRect();
		}
	}
	
	public void setRect()
	{
		super.setRect();
		rect.setLocation((int)x, (int)y);
		mid.setLocation(x, y);
	}
	
	public void setAttack(boolean a)
	{
		super.setAttack(a);
		attack=a;
	}
	
	public void reset()
	{
		super.reset();
		/*attack=false;
		hasNear=false;
		attackTimer=0;
		if(near!=null)
			near.damage(0);
		near=null;*/
	}
	
	public void setNear(int nearX,int nearY,Entity near,int nX,int nY)
	{
		
	//	System.out.println("t");
		super.setNear(nearX, nearY, near,nX,nY);
	//	this.near=near;
	//	opp=nearY;
	//	adj=nearX;
		this.nearX=nX;
		this.nearY=nY;
		hasNear=true;
		setAngle();
	}
	
	public boolean getSkip()
	{
		return super.getSkip();
	}
	
	public void setSkip(boolean b)
	{
		super.setSkip(b);
	}
	
	public boolean hasNear()
	{
		return hasNear;
	}
	
	public boolean doAttack()
	{
		return super.doAttack();
	}
	
	public boolean getAttack()
	{
		return super.getAttack();
	}
	
	public int getDamage()
	{
		return super.getDamage();
	}
	
	public Entity getNear()
	{
		return super.getNear();
	}
	
	public void setAngle()
	{
		super.setAngle();
//		angle=Math.atan2(opp,adj);
		//System.out.println(nearX+" "+nearY+" "+adj+" "+opp+" "+angle+" "+x+" "+y);
//	    double w=(Math.atan2(opp,adj ));
	    //System.out.println(Math.toDegrees(w));
	    //System.out.println(Math.sin(w));
	}
	
	public Point getMid()
	{
		return super.getMid();
	}
	
	public int getTeam()
	{
		//System.out.println(team);
		return super.getTeam();
	}
	
	public int getX()
	{
		return super.getX();
	}
	
	public int getY()
	{
		return super.getY();
	}
	
	public Rectangle getRect()
	{
		return super.getRect();
	}
	
	public Rectangle getBRect()
	{
		return super.getBRect();
	}
	
	public int getnX()
	{
		return nearX;
	}
	
	public int getnY()
	{
		return nearY;
	}
	
	public void damage(int damage)
	{
		super.damage(damage);
	}
	
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.fill(super.getRect());
		super.draw(g);
	//	near.draw(g);
		//g.draw(near.getRect());
		//g.drawLine(getMid().x, getMid().y, nearX, nearY);
		//g.drawLine(getMid().x, getMid().y, getMid().x-adj, getMid().y);
		//g.drawLine(getMid().x-adj, getMid().y, getMid().x-adj, getMid().y-opp);
	}

}
