package jj;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import buildings.Base;
import buildings.Tower;
import troops.Bowman;
import troops.Cavalry;
import troops.Gladiator;
import troops.Tank;
import troops.Troop;

public class Driver extends JFrame implements Runnable,MouseListener
{
Container con = getContentPane();
Thread t = new Thread(this);

private int[] team=new int[]{1,2};
private Tower[] redT=new Tower[2];
private Tower[] blueT=new Tower[5];
private Base blueB;
private Base redB;
private Rectangle side[]=new Rectangle[2];
private ArrayList<Troop>redTroops=new ArrayList<Troop>();
private Info info;
private PlayerHandler /*If you know what I mean*/ p;
private int drawY=0;
private int currentId=0;
private int x,y;
private boolean win=false;
    public Driver(String name)
    {
    	
    super(name);
   // System.out.println();
    JOptionPane.showMessageDialog(null, "You can only place troops by clicking on your half (the bottom). Switch troops by clicking the different troops in the top left");
    JOptionPane.showMessageDialog(null, "Troops cost power. Your power is on the right");
 //   JOptionPane.showMessageDialog(null, "");


    /*  System.out.println(Math.toDegrees(Math.atan2(Math.sqrt(3.0)/2,0.5 )));
    double w=(Math.atan2(Math.sqrt(3.0)/2,0.5 ));
    System.out.println(Math.cos(w));*/
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    addMouseListener(this);
    con.setLayout(new FlowLayout());
    Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
    Dimension windowSize = new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight());//Set the dimensions of your window here!
    setSize(windowSize);
   
    //setCursor(Cursor.getPredefinedCursor(0));
    
    x=(int)screenSize.getWidth();
	y=(int)screenSize.getHeight();
	
	int height=(int)screenSize.getHeight()/20;
	int width=height;
	info=new Info(width,height);
	p=new PlayerHandler(x,y);
	for(int i=0;i<blueT.length;i++)
	{
		int xx=(x/8)*((i+2));
		int yy=2*y/7+(Math.abs((y/10)*((i-2))))*-1;
		blueT[i]=new Tower(xx-(width/2),yy-(height/2),width,height,1,1000);
	}
	for(int i=0;i<redT.length;i++)
	{
		int xx=(x/8)*((6*i+1));
		int yy=y/2+(Math.abs((y/10)*((4*i-2))));
		redT[i]=new Tower(xx-(width/2),yy-(height/2),width,height,2,1000);
	}
	redB=new Base(blueT[2].getX()-(width/2),redT[0].getY()+(3*height/2),2*width,2*height,2,1500);
	blueB=new Base(blueT[2].getX()-(width/2),blueT[0].getY()-(height/2),2*width,2*height,1,1500);
	for(int i=0;i<side.length;i++)
		side[i]=new Rectangle(0,0+(i*y/2),x,(i+1)*y/2);
    t.start();
    }
    public void run()
    {
    try{
        while(true)
            {
            t.sleep(67);
            if(!win)
            {
            p.addPower();
         //   System.out.println(drawY);
            	for(int i=0;i<redTroops.size();i++)
            	{
            		//checks is there is a near, if not finds it
            			findNear(redTroops.get(i));
            			redTroops.get(i).move();
            		//checks if it should stop
            		if(!redTroops.get(i).getAttack())
            			checkStop(redTroops.get(i));
            		//otherwise does attack
            		else if(redTroops.get(i).getX()<x)
            			doAttack(redTroops.get(i));
            		checkNearDeath(redTroops.get(i));
            	}
            	
            	for(int i=0;i<blueT.length;i++)
            	{
            		if(!blueT[i].hasNear())
            			findNear(blueT[i]);
            		else if(blueT[i].getX()<x)
            			doAttack(blueT[i]);
            		
            		checkNearDeath(blueT[i]);
            		
            	}
            	if(!blueB.hasNear())
        			findNear(blueB);
        		else if(blueB.getX()<x)
        			doAttack(blueB);
        		checkNearDeath(blueB);
        		checkWin();
            }
            if(win)
            {
            	JOptionPane.showMessageDialog(null,"~YOU WIN~");
            	System.exit(0);
            }
            repaint();
            }
        }
    catch(Exception e)
        {
        e.printStackTrace();
        }
    }
    
    public void checkWin()
    {
    	win=true;
    	for(int i=0;i<blueT.length;i++)
    	{
    		if(blueT[i].getX()<x)
    			win=false;
    	}
    	if(blueB.getX()<x)
			win=false;
    }
    
    public void checkStop(Troop tr)
    {
    	if(tr.getTeam()==2)
    	{
    		if(tr.getNear().getRect().intersects(tr.getRect()))
    		{
    			tr.setAttack(true);
    		}
    		
    		else if(tr.getType().equals("Bowman"))
    		{
    			
    			if(tr.getNear().getRect().intersects(tr.getBRect()))
        		{
    				//System.out.println("here");
        			tr.setAttack(true);
        		}
    		}
    			
    		
    		
    	}
    }
    
    public void doAttack(Entity e)
    {
    	//System.out.println(e.doAttack());
    	if(e!=null)
    	{
    	if(e.doAttack())
    	{
    		e.getNear().damage(e.getDamage());
    	}
    	else
    	{
    		e.timerCount();
    	}
    	}
    }
    
    public void checkNearDeath(Entity e)
    {
    //	System.out.println(e.getNear().getHealth());
    	if(e.getNear()!=null)
    	{
    		if(e.getNear().getHealth()<=0)
    		{
    			e.reset();
    		//e.getNear().damage(1);
    		}
    	}
    }
    
    public void paint(Graphics gr)
    {
    	Image ii=createImage(getSize().width, getSize().height);
    	Graphics2D g = (Graphics2D)ii.getGraphics();
    	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    	g.setColor(Color.magenta);
    	g.fillRect(0,0,2000,2000);
    	g.setColor(Color.blue);
    //	
    	for(int i=0;i<blueT.length;i++)
    	{
    		//System.out.println("h");
    		blueT[i].draw(g);
    	}
    	
    	blueB.draw(g);
    	g.setColor(Color.red);
    	for(int i=0;i<redT.length;i++)
    		redT[i].draw(g);
    	redB.draw(g); 
    	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
    	g.setColor(Color.black);
    	g.fill(side[0]);
    	g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    	for(int i=0;i<redTroops.size();i++)
    		redTroops.get(i).draw(g);
    	//g.drawLine(0, drawY, 2000, drawY);
    	//g.drawLine(0, 318, 2000, 318);
    	p.draw(g);
    	g.dispose();
    	gr.drawImage(ii, 0, 0, this);
    }
   
    public void addTroop(int id,int x,int y)
	{
		if(id==0)
		{
			
			int type=info.getIndex("Gladiator");
			//System.out.println(y+ " "+(y-((info.getSize(type))/2)));
			if(p.getPower()>=info.getPower(type))
			{
				p.removePower(info.getPower(type));
				redTroops.add(new Gladiator(x-((info.getSize(type))/2),y-((info.getSize(type))/2),info.getSize(type),info.getSpeed(type),info.getDamage(type),info.getHealth(type),info.getAttackSpeed(type),2));
			}
		}
		else if(id==1)
		{
			int type=info.getIndex("Tank");
			//System.out.println(y+ " "+(y-((info.getSize(type))/2)));
			if(p.getPower()>=info.getPower(type))
			{
				p.removePower(info.getPower(type));
				redTroops.add(new Tank(x-((info.getSize(type))/2),y-((info.getSize(type))/2),info.getSize(type),info.getSpeed(type),info.getDamage(type),info.getHealth(type),info.getAttackSpeed(type),2));
			}
		}
		else if(id==2)
		{
			int type=info.getIndex("Bowman");
			//System.out.println(y+ " "+(y-((info.getSize(type))/2)));
			if(p.getPower()>=info.getPower(type))
			{
				p.removePower(info.getPower(type));
				redTroops.add(new Bowman(x-((info.getSize(type))/2),y-((info.getSize(type))/2),info.getSize(type),info.getSpeed(type),info.getDamage(type),info.getHealth(type),info.getAttackSpeed(type),2));
			}
		}
		else if(id==3)
		{
			int type=info.getIndex("Cavalry");
			//System.out.println(y+ " "+(y-((info.getSize(type))/2)));
			if(p.getPower()>=info.getPower(type))
			{
				p.removePower(info.getPower(type));
				redTroops.add(new Cavalry(x-((info.getSize(type))/2),y-((info.getSize(type))/2),info.getSize(type),info.getSpeed(type),info.getDamage(type),info.getHealth(type),info.getAttackSpeed(type),2));
			}
		}
	}
    
    public void findNear(Entity e)
    {
    	int adj=10000,opp=10000,hyp=10000;
    	int nearX=0,nearY=0;
    	Entity temp=e.getNear();
    	Entity near=null;
    	//System.out.println(tr.getTeam());
    	if(e.getTeam()==2)
    	{
    		//System.out.println("hiiiiii");
    		for(int i=0;i<blueT.length;i++)
    		{
    			int bAdj=blueT[i].getMid().x;
    			int bOpp=blueT[i].getMid().y;
    
    			if(Math.hypot(e.getMid().x-bAdj, e.getMid().y-bOpp)<hyp)
    			{
    				
    				hyp=(int)Math.hypot(e.getMid().x-bAdj, e.getMid().y-bOpp);
    				adj=e.getMid().x-bAdj;
    				opp=e.getMid().y-bOpp;
    				nearX=bAdj;
    				nearY=bOpp;
    				near=blueT[i];
    			}
    		}
    		
    		int bAdj=blueB.getMid().x;
			int bOpp=blueB.getMid().y;
			if(Math.hypot(e.getMid().x-bAdj, e.getMid().y-bOpp)<hyp)
			{
				
				hyp=(int)Math.hypot(e.getMid().x-bAdj, e.getMid().y-bOpp);
				adj=e.getMid().x-bAdj;
				opp=e.getMid().y-bOpp;
				nearX=bAdj;
				nearY=bOpp;
				near=blueB;
			}
    	}
    	else if(e.getTeam()==1)
    	{
    		for(int i=0;i<redTroops.size();i++)
    		{
    			int rAdj=redTroops.get(i).getMid().x;
    			int rOpp=redTroops.get(i).getMid().y;
    
    			if(Math.hypot(e.getMid().x-rAdj, e.getMid().y-rOpp)<hyp)
    			{
    				if(e.getBRect().intersects(redTroops.get(i).getRect()) || e.getBRect().contains(redTroops.get(i).getRect()))
    				{
    				hyp=(int)Math.hypot(e.getMid().x-rAdj, e.getMid().y-rOpp);
    				adj=e.getMid().x-rAdj;
    				opp=e.getMid().y-rOpp;
    				nearX=rAdj;
    				nearY=rOpp;
    				near=redTroops.get(i);
    				
    				}
    			}
    		}
    	}
    	
    	
    	if(e.isMovable() && e.hasNear())
    	{
    		if(e.getType().equals("Bowman"))
    		{
    			if(!e.getBRect().intersects(e.getNear().getRect()))
    				e.setAttack(false);
    		}
    			
    		else if(!e.getRect().intersects(e.getNear().getRect()))
    			e.setAttack(false);
    	}
    	else if(!e.isMovable() && e.hasNear())
    	{
    		if(e.getType().equals("Base"))
    		{
    			if(!e.getBRect().intersects(e.getNear().getRect()) || !e.getBRect().contains(e.getNear().getRect()))
    				e.setAttack(false);
    		}
    			
    		else if(e.getType().equals("Tower"))
    		{
    			if(!e.getBRect().intersects(e.getNear().getRect())|| !e.getBRect().contains(e.getNear().getRect()))
    				e.setAttack(false);
    		}
    	}
    	if(near!=temp && near!=null)
    	{
    		//System.out.println(near.getMid().x);
    		//System.out.println(near+ "1");
    		e.setNear(adj, opp,near,nearX,nearY);
    	}
    }
    
     public void update(Graphics g)
    {
        paint(g);
    }
     
	public void mouseClicked(MouseEvent j) {
		for(int i=0;i<p.getRect().length;i++)
		{
			if(p.getRect()[i].contains(j.getPoint()))
			{
				p.setSelected(i);
				currentId=i;
			}
		}
	}
	
	public void mouseEntered(MouseEvent j) {
		
	}
	public void mouseExited(MouseEvent j) {
		
	}
	public void mousePressed(MouseEvent j) {

	}
	public void mouseReleased(MouseEvent j) {
	//	System.out.println(j.getX()+" "+j.getY());
		if(side[1].contains(j.getPoint()))
		{
			if(!redB.getRect().contains(j.getPoint()) && !redT[0].getRect().contains(j.getPoint()) && !redT[1].getRect().contains(j.getPoint()))
				addTroop(currentId,j.getX(),j.getY());
		}
	} 
	
	public static void main(String[] args)
    {
		Driver frame = new Driver("Towers");
		//frame.setSize(500, 500);//determines size of screen: Phillips' max is 800, 800
    	frame.setVisible(true);
    }
}
