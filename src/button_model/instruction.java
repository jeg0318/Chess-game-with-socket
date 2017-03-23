package button_model;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
public class instruction  {

   public static JFrame frame=new JFrame();

	
	
}
 class br extends JButton implements MouseListener   {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	public br(String a)
	{
		super.setOpaque(true);
		setText(a);
		
		addMouseListener(this);
		
		
	}
	@Override
	protected void paintComponent(Graphics g)
	{
		
		Rectangle r=getBounds();
		setBorderPainted(false);
		setFocusPainted(true);
	        	int x = 0;
		//System.out.println("x is: "+x);
		        int y = 0;
		    //    System.out.println("y is: "+y);
		        int width = 150;
		      //  System.out.println("width is: "+width);
		        int height = 150;
		      //  System.out.println("height is: "+height);
		      g.setColor(Color.GREEN);
		      g.fillRect(x, y, width, height);
		  

		 
		 
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("X is: "+e.getX());
	    System.out.println("Y is: "+e.getY());
	    super.setBackground(Color.GREEN);
	    
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		super.setBackground(Color.RED);
		
	}
	
	

	

}
