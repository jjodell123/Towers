package jj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class Entity {

	private int size,speed,damage,health,team;
	private int height,width;
	private double x,y;
	private double healthRatio;
	private Rectangle rect,bRect;
	private Point mid;
	private boolean isMovable;
	private boolean hasNear=false,attack=false;
	private int opp=0,adj=0;
	private double angle;
	private Entity near;
	private String type=null;
	private int attackTimer,attackSpeed;
	private boolean skip=false;
	public Entity(int x,int y, int size,int speed,int damage,int health,int attackSpeed,int team)
	{
		this.x=x;
		this.y=y;
		this.size=size;
		this.speed=speed;
		this.damage=damage;
		this.health=health;
		this.team=team;
		this.attackSpeed=attackSpeed;
		healthRatio=(double)health/size;
		rect=new Rectangle(x,y,size,size);
		mid=new Point(x+(size/2),y+(size/2));
		//System.out.println(size/2);
		isMovable=true;
		
	}
	
	public Entity(int x, int y, int width,int height,int team,int health,int attackSpeed)
	{
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.team=team;
		this.health=health;
		size=width;
		this.attackSpeed=attackSpeed;
		damage=40;
		healthRatio=(double)health/width;
		mid=new Point (x+width/2,y+height/2);
		rect=new Rectangle(x,y,width,height);
		isMovable=false;
	}
	
	public void setType(String type)
	{
		this.type=type;
		if(type.equals("Bowman"))
			bRect=new Rectangle((int)x-10*size,(int)y-10*size,size*21,size*21);
		else if(type.equals("Base"))
			bRect=new Rectangle((int)x-2*width,(int)y-2*height,width*5,width*5);
		else if(type.equals("Tower"))
			bRect=new Rectangle((int)x-2*width,(int)y-2*height,width*5,width*5);
	}
	
	public void setNear(int nearX,int nearY,Entity near,int nX,int nY)
	{
		//this.nearX=nearX;
		//this.nearY=nearY;
		//System.out.println("e");
		/*if(!isMovable)
		{
		System.out.println("noooooooooo");
		System.out.println(near);
		}*/
		this.near=near;
		opp=nearY;
		adj=nearX;
		hasNear=true;
		setAngle();
	}
	
	public void move()
	{
		if(!attack)
		{
			//System.out.println(type);
		x-=Math.cos(angle)*speed;
		y-=Math.sin(angle)*speed;
		setRect();
		}
	}
	
	public void setRect()
	{
		//System.out.println(type);
		rect.setLocation((int)x, (int)y);
		if(isMovable)
		{
			mid.setLocation(x+size/2, y+size/2);
			if(type.equals("Bowman"))
				bRect.setLocation((int)(x-size*10), (int)(y-size*10));
		}

		else
			mid.setLocation(x+width/2, y+height/2);
		//System.out.println(rect);
	}
	
	public void timerCount()
	{
		attackTimer++;
	}
	
	public void setAttack(boolean a)
	{
		attack=a;
	}
	
	public void setAngle()
	{
		angle=Math.atan2(opp,adj);
		//System.out.println(nearX+" "+nearY+" "+adj+" "+opp+" "+angle+" "+x+" "+y);
	    double w=(Math.atan2(opp,adj ));
	    //System.out.println(Math.toDegrees(w));
	   // System.out.println(Math.sin(w));
	}
	
	public Entity getNear()
	{
		return near;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public boolean hasNear()
	{
		return hasNear;
	}
	
	public Point getMid()
	{
		return mid;
	}
	
	public boolean doAttack()
	{
		if(attackTimer>=attackSpeed)
		{
			//System.out.println("yay");
			return true;
		}
		return false;
	}
	
	public int getTeam()
	{
		//System.out.println(team);
		return team;
	}
	
	
	public Rectangle getBRect()
	{
		return bRect;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
	
	public Rectangle getRect()
	{
		//System.out.println(rect);
		return rect;
	}
	
	public boolean getAttack()
	{
		return attack;
	}
	
	public int getDamage()
	{
		attackTimer=0;
		return damage;
	}
	
	public void setSkip(boolean b)
	{
		skip=b;
	}
	
	public boolean getSkip()
	{
		return skip;
	}
	
	public void reset()
	{
	//	System.out.println("reset");
		attack=false;
		hasNear=false;
		//skip=true;
		attackTimer=0;
		near.damage(0);
	}
	
	public boolean isMovable()
	{
		return isMovable;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void damage(int damage)
	{
		if(health<=0)
		{
			if(type==null)
			{
				//System.out.println(health);
				x=10000;
				setRect();
			}
			else
			{
				x=10000;
				setRect();
			}
		}
		//if(isMovable)
			//System.out.println(damage+" "+health+" "+x);
		health-=damage;
	}
	
	public void draw(Graphics2D g)
	{
		
		/*if(!isMovable)
		{
		//	System.out.println(hasNear);
			if(type.equals("Base") || type.equals("Tower"))
				g.draw(bRect);
		}*/
		g.fillRect((int)x-(size/10), (int)y+(size*11/10), size*12/10, size/4);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		
		g.setColor(Color.black);
		if(isMovable)
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
			g.setColor(Color.blue);
		}
		if(isMovable)
			g.fillRect((int)x, (int)y+(size*11/10), (int)(health/healthRatio), size/4);
		else
			g.fillRect((int)x, (int)y+(size*11/10)+(size*7/112), (int)(health/healthRatio), size/7);
		g.setColor(Color.black);
		g.draw(rect);
		g.drawRect((int)x+1, (int)y+1, width-2, height-2);
		if(!isMovable && type.equals("Tower"))
		{
			g.drawLine((int)x, (int)y, (int)x+width, (int)y+height);
			g.drawLine((int)x+width, (int)y, (int)x, (int)y+height);
		}
		else if(!isMovable && type.equals("Base"))
		{
			g.drawLine((int)x, (int)y, (int)x+width/4, (int)y+height/4);
			g.drawLine((int)x+width, (int)y, (int)x+3*width/4, (int)y+height/4);
			g.drawLine((int)x, (int)y+height, (int)x+width/4, (int)y+3*height/4);
			g.drawLine((int)x+width, (int)y+height, (int)x+3*width/4, (int)y+3*height/4);
			g.drawRect((int)x+width/4, (int)y+height/4, width/2, height/2);
			g.fillRect((int)x+width/2-3,(int)y+height/2-3,7,7);
		}
				
	//	if(!isMovable)
		//	System.out.println((int)(health/healthRatio));
	}
}
