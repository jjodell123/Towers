package buildings;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import jj.Entity;

public class Tower extends Entity{

	private int x,y,height,width,team,health;
	private double healthRatio;
	private Point mid;
	private Rectangle rect;
	private boolean hasNear=false;
	private int attackTimer=0,attackSpeed;
	
	public Tower(int x, int y, int width,int height,int team,int health)
	{
		super(x,y,width,height,team,health,20);
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.team=team;
		this.health=health;
		super.setType("Tower");
		attackSpeed=20;
		mid=new Point (x+width/2,y+height/2);
		rect=new Rectangle(x,y,width,height);
	/*	boolean done=false;
		for(double i=0.001;i<1 && !done;i+=0.001)
		{
			if(health/i<width+0.05 && health/i>width-0.05)
				
		}*/
		healthRatio=(double)health/width;
	}
	
	public void setNear(int nearX,int nearY,Entity near,int nX,int nY)
	{
		

		super.setNear(nearX, nearY, near,nX,nY);
		hasNear=true;
	}
	
	public Entity getNear()
	{
		return super.getNear();
	}
	
	public Rectangle getBRect()
	{
		return super.getBRect();
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
		//System.out.println(super.hasNear());
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
			if(super.hasNear() && super.doAttack())
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
