package jj;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

import buildings.Tower;
/**
 * The actual JFrame
 * For most programs, no code needs to be added in this class
 * When running a program, this is the class from which you start
 */
public class Window extends JFrame
{
    public Window(String name)
    {
        super(name);//actually sets the title to a JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension((int)screenSize.getWidth(), (int)screenSize.getHeight());//Set the dimensions of your window here!
        setSize(windowSize);
        setCursor(Cursor.getPredefinedCursor(3));
    }
    
 /*   public static void main(String[] args)
    {
    
    
    /*
        
    
   
    
    
    
        Window window = new Window("Towers");//Set the window header/title of your program here      
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new FlowLayout());
        Driver d = new Driver(window.getSize());
        contentPane.add(d);
        window.setVisible(true);
        /*Window window2 = new Window("Towers");//Set the window header/title of your program here      
        Container contentPane2 = window2.getContentPane();
        contentPane2.setLayout(new GridLayout(1,1));
        Driver d2 = new Driver(window2.getSize());
        contentPane.add(d2);
        window2.setVisible(true);
        
    }*/
}
