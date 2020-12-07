package jj;

import java.awt.Color;
import java.awt.Graphics2D;

public class Card {
	
	private int size,power,x,y,width,height;
	private Color c;
	private String name;
	
	public Card(int size,int power, Color c,int x,int y,int width,int height,String name)
	{
		this.size=size;
		this.power=power;
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
		this.c=c;
		this.name=name;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.gray);
		g.fillRect(x,y,width,height);
		g.setColor(c);
		g.fillRect(x+width/2-size/2,y+height/2-size/2,size,size);
		g.drawString("Power", x+2, y+width/6);
		g.drawString("needed:", x+2, y+width/6+15);
		g.drawString(""+power, x+2, y+width/6+30);
		g.drawString(name, x+2, y+height-2);
	}

}
