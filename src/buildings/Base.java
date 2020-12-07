package buildings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import jj.Entity;

public class Base extends Entity{

	private int x,y,height,width,team, health;
	double healthRatio;
	private Point middle;
	private Rectangle rect;
	private int attackTimer=0,attackSpeed;
	private boolean hasNear=false,attack=false;
	
	public Base(int x,int y,int width,int height,int team,int health)
	{
		super(x,y,width,height,team,health,15);
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.team=team;
		this.health=health;
		attackSpeed=15;
		super.setType("Base");
		middle=new Point (x+width/2,y+height/2);
		rect=new Rectangle(x,y,width,height);
		healthRatio=(double)health/width;
	}
	
	public void setNear(int nearX,int nearY,Entity near,int nX,int nY)
	{
		

		super.setNear(nearX, nearY, near,nX,nY);
		hasNear=true;
		//System.out.println("hi");
	}
	
	public Entity getNear()
	{
		return super.getNear();
	}
	
	public Point getMid()
	{
		return super.getMid();
	}
	
	public int getTeam()
	{
		return super.getTeam();
	}
	
	public Rectangle getBRect()
	{
		return super.getBRect();
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
	
	public void damage(int damage)
	{
		super.damage(damage);
	}
	
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		if(super.getHealth()>0)
		{
			if(team==1)
				g.setColor(Color.blue);
			else if(team==2)
				g.setColor(Color.red);
			g.fill(rect);
			if(team==1)
				g.setColor(Color.red);
			else if(team==2)
				g.setColor(Color.blue);
			if(super.hasNear()&& super.doAttack())
				g.drawLine(getMid().x, getMid().y, super.getNear().getMid().x, super.getNear().getMid().y);
			/*g.fillRect(x-(width/10), y+(height*11/10), width*12/10, height/4);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
			g.setColor(Color.black);
			g.fillRect(x, y+(height*11/10)+(height*7/112), (int)(health/healthRatio), height/7);*/
			if(team==1)
				g.setColor(Color.blue);
			else if(team==2)
				g.setColor(Color.red);
			super.draw(g);
		}
	}
}
