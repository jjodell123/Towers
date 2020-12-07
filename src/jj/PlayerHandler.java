package jj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PlayerHandler {

	private int width,height;
	private Rectangle rect[]=new Rectangle[4];
	private Card card[]=new Card[4];
	private int selected=0;
	private int power,pCount=50,pCountMax=100;
	private double powerH;
	public PlayerHandler(int width,int height)
	{
		//System.out.println(height);
		this.width=width;
		this.height=height;
		for(int i=0;i<rect.length;i++)
			rect[i]=new Rectangle(width/100,(2*i+1)*height/24+i*height/48,width/25,height/12);
		/*two=new Rectangle(width/100,3*height/24+height/48,width/25,height/12);
		three=new Rectangle(width/100,5*height/24+2*height/48,width/25,height/12);
		four=new Rectangle(width/100,7*height/24+3*height/48,width/25,height/12);*/
		card[0]=new Card(Info.getSize(0),Info.getPower(0),Color.black,rect[0].x,rect[0].y,rect[0].width,rect[0].height,"Gladiator");
		card[1]=new Card(Info.getSize(1),Info.getPower(1),Color.pink,rect[1].x,rect[1].y,rect[1].width,rect[1].height,"Tank");
		card[2]=new Card(Info.getSize(2),Info.getPower(2),Color.orange,rect[2].x,rect[2].y,rect[2].width,rect[2].height,"Bowman");
		card[3]=new Card(Info.getSize(3),Info.getPower(3),Color.white,rect[3].x,rect[3].y,rect[3].width,rect[3].height,"Cavalry");
		powerH=(double)pCountMax/height*4;
	}
	
	public void setSelected(int i)
	{
		selected=i;
	}
	
	public Rectangle[] getRect()
	{
		return rect;
	}
	
	public void removePower(int i)
	{
		pCount-=10*i;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public void addPower()
	{
		if(pCount<pCountMax)
			pCount++;
		power=(int)pCount/10;
	}
	
	public void draw(Graphics2D g)
	{
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.black);
		for(int i=0;i<rect.length;i++)
		{
			card[i].draw(g);
			g.draw(rect[i]);
		}
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		if(selected<5)
			g.fill(rect[selected]);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		g.setColor(Color.black);
		g.fillRect(width-width/15, height/12, width/30, height/4);
		g.setColor(Color.red);
		g.fillRect(width-width/15+1, height/12+ height/4-(int)(pCount/powerH), width/30-2,(int)(pCount/powerH));
		//System.out.println(height/12+ height/4-(int)(pCount/powerH)+" "+pCountMax+" "+powerH+ " "+(int)(pCountMax/powerH));
		g.setColor(Color.white);
		for(int i=0;i<11;i++)
		g.fillRect(width-width/15, height/12+i*height/40, width/30, 1);
	}
}
