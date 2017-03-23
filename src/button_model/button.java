package button_model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class button extends JPanel     {
    private figurs current_figur;
	private static List<figurs> pieces = new ArrayList<figurs>();

	 private static final int black=0,white=1;
	 private static int x=0,y=0;
	 private static JFrame f;
	 private Image bl=null,wl=null,bk=null,wk=null,bs=null,ws=null,bq=null,wq=null,bking=null,wking=null,bp=null,wp=null;
     private static final int LADIA=1,KON=2,SLON=3,QUEEN=4,KING=5,PESHKA=6;
     
	private Image imgBackground;
	public button() {
		
	
		imgBackground = null;
	        try {
	        	
	        	imgBackground = ImageIO.read(new File("src/img/board.png"));
	        } catch (IOException e) {}

		
	        addMouseListener(new MouseAdapter() {
	            
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                x = e.getX();
	                y = e.getY();
	                System.out.println(x+" , "+y);
	            }
	        });
	        createimages();
	        create_coordinates();
	               
			 
	       
	        change_place change= new change_place(this.pieces,this);
	        
	        this.addMouseListener(change);
	      
	      
	        this.addMouseMotionListener(change);
	        
	      
	       //x=128 y=63
		 f = new JFrame();
		
        
		f.setVisible(true);
		f.setTitle("Abdrazak Almas");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		 
		//f.add(jb);
		f.setResizable(true);
		f.setSize(this.imgBackground.getWidth(null), this.imgBackground.getHeight(null)+30);
	}
	public static void deleteframe()
	{
		
		f.dispose();
		
		
	}
    private void create_coordinates()
    {
    	 createAndAddPiece(151,45,bl,black,LADIA,1);
	       
	        createAndAddPiece(592,45,bl,black,LADIA,2);
	       
	        //kon
	        createAndAddPiece(214,45,bk,black,KON,3);
	        createAndAddPiece(529,45,bk,black,KON,4);
	        //slon
	        createAndAddPiece(466,45,bs,black,SLON,5);
	        createAndAddPiece(277,45,bs,black,SLON,6);
	       
	        //king queen
	        createAndAddPiece(340,45,bq,black,QUEEN,7);
	        createAndAddPiece(403,45,bking,black,KING,8);
	        int X_cord=151;
	        int counter=9;
	        for(int i=0;i<8;i++)
	        {
	        	createAndAddPiece(X_cord,108,bp,black,PESHKA,counter);
	        	counter++;
	        	X_cord+=63;
	        }
	        X_cord=151;
	        //white
	        
	        createAndAddPiece(151,486,wl,white,LADIA,17);
	        createAndAddPiece(592,486,wl,white,LADIA,18);
	        createAndAddPiece(214,486,wk,white,KON,19);
	        createAndAddPiece(529,486,wk,white,KON,20);
	        createAndAddPiece(466,486,ws,white,SLON,21);
	        createAndAddPiece(277,486,ws,white,SLON,22);
	        createAndAddPiece(340,486,wq,white,QUEEN,23);
	        createAndAddPiece(403,486,wking,white,KING,24);
	        counter=25;
	        for(int i=0;i<8;i++)
	        {
	        	createAndAddPiece(X_cord,423,wp,white,PESHKA,counter);
	        	counter++;
	        	X_cord+=63;
	        }
    }
	private void createimages(){
		 try {
			
	        	bs = ImageIO.read(new File("src/img/bb.png"));
	        	bk=ImageIO.read(new File("src/img/bk.png"));
	        	bking=ImageIO.read(new File("src/img/bking.png"));
	        	bq=ImageIO.read(new File("src/img/bq.png"));
	        	bl=ImageIO.read(new File("src/img/bl.png"));
	        	bp=ImageIO.read(new File("src/img/bp.png"));
	        	ws=ImageIO.read(new File("src/img/ws.png"));
	        	wk=ImageIO.read(new File("src/img/wk.png"));
	        	wking=ImageIO.read(new File("src/img/wking.png"));
	        	wq=ImageIO.read(new File("src/img/wq.png"));
	        	wl=ImageIO.read(new File("src/img/wl.png"));
	        	wp=ImageIO.read(new File("src/img/wp.png"));
	        } catch (IOException e) {}
		
	}


	

	@Override
	protected void paintComponent(Graphics g) {
		
		
		   Graphics2D g2 = (Graphics2D) g;
		   
		g.drawImage(this.imgBackground, 0, 0, null);
		for (figurs piece : this.pieces) {
			g.drawImage(piece.getImage(), piece.getX(), piece.getY(), this);
			
		}
		
		if(change_place.getter_for_title_client()==true)
		{
			f.setTitle("Cleint");
		}
		if(change_place.getter_for_title_server()==true)
		{
			f.setTitle("Server");
		}
		if(change_place.get_draw_x()!=0 && change_place.get_draw_y()!=0 )
		{
			
			//draw_possible_coord move=new draw_possible_coord(current_figur.getX()-63,current_figur.getY()-63);
			g2.setStroke(new BasicStroke(4.0f));
			g2.setColor(Color.GREEN.brighter());
			if(change_place.get_logic()==PESHKA && change_place.get_color()==white )
			{
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean check_top_mates=false;
				boolean check_top_left_mates=false;
				boolean check_top_right_mates=false;
				
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==y-63)
						{
							check_top_mates=true;
						}
						else if(this.pieces.get(i).getX()==(x-63) &&this.pieces.get(i).getY()==(y-63)&& this.pieces.get(i).getType()!=white )
						{
							 check_top_left_mates=true;
						}
						else if(this.pieces.get(i).getX()==(x+63) &&this.pieces.get(i).getY()==(y-63)&& this.pieces.get(i).getType()!=white )
						{
							check_top_right_mates=true;
						}
					}
					
					
					 if(check_top_mates==false)
					{
					g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
					}
					if(check_top_left_mates==true)
					{
						g2.drawRoundRect(x-63, y-59, 60, 63, 10, 10);
					}
					if(check_top_right_mates==true)
					{
						g2.drawRoundRect(x+63, y-59, 60, 63, 10, 10);
					}
				
			}
			else if(change_place.get_logic()==PESHKA && change_place.get_color()==black)
			{
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean check_bottom_mates=false;
				boolean check_bot_left_mates=false;
				boolean check_bot_right_mates=false;
				while(true)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==y+63)
						{
							check_bottom_mates=true;
						}
						else if(this.pieces.get(i).getX()==(x-63) &&this.pieces.get(i).getY()==(y+63)&& this.pieces.get(i).getType()!=black )
						{
							check_bot_left_mates=true;
						}
						else if(this.pieces.get(i).getX()==(x+63) &&this.pieces.get(i).getY()==(y+63)&& this.pieces.get(i).getType()!=black )
						{
							check_bot_right_mates=true;
						}
						
					}
					if(check_bottom_mates==false)
					{
						g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
					}
					if(check_bot_left_mates==true)
					{
						g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
					}
					if(check_bot_right_mates==true)
					{
						g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
					}
					break;
				}
				/*for(int i=this.pieces.size()-1;i>=0;i--)
				{
					
					if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==y+63)
					{
						
					}
					else if(this.pieces.get(i).getX()==(x-63) &&this.pieces.get(i).getY()==(y+63)&& this.pieces.get(i).getType()!=black )
					{
						g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
					}
					else if(this.pieces.get(i).getX()==(x+63) &&this.pieces.get(i).getY()==(y+63)&& this.pieces.get(i).getType()!=black )
					{
						g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
					}
					
				}*/
				
			}
			if(change_place.get_logic()==LADIA && change_place.get_color()==white)
			{
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean check_top_mates_if_it_white=false;
				boolean check_top_mates_if_it_black=false;
				boolean check_top_mates=false;
				//1)top
				while(y>100)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()==white)
						{
							check_top_mates_if_it_white=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()==black)
						{
							check_top_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63))
						{
							check_top_mates=true;
						}
					}
					if(check_top_mates==false)
					{
						g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
					}
					if(check_top_mates_if_it_white==true)
					{
						break;
					}
					if(check_top_mates_if_it_black==true)
					{
						g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
						break;
					}
					y-=63;
					check_top_mates=false;
				}
				
				y=change_place.get_draw_y();
				boolean check_bottom_if_it_white=false;
				boolean check_bottom_mates_if_it_black=false;
				boolean check_bottom_mates=false;
				//2)bottom
				while(y<=423)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()==white)
						{
							check_bottom_if_it_white=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()==black)
						{
							check_bottom_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63))
						{
							check_bottom_mates=true;
						}
					}
					if(check_bottom_mates==false)
					{
						g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
					}
					if(check_bottom_if_it_white==true)
					{
						break;
					}
					if(check_bottom_mates_if_it_black==true)
					{
						g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
						break;
					}
					check_bottom_mates=false;
					y+=63;
				}
				y=change_place.get_draw_y();
				boolean check_left_if_it_white=false;
				boolean check_left_mates_if_it_black=false;
				boolean check_left_mates=false;
				//3)left
				while(x>=214)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()==white)
						{
							check_left_if_it_white=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()==black)
						{
							check_left_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) &&(this.pieces.get(i).getType()==black || this.pieces.get(i).getType()==white) )
						{
							
							check_left_mates=true;
						}
					}
					if(check_left_mates==false)
					{
						g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
					}
					if(check_left_if_it_white==true)
					{
						break;
					}
					if(check_left_mates_if_it_black==true)
					{
						g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
						break;
					}
					check_left_mates=false;
					x-=63;
				}
				x=change_place.get_draw_x();
				boolean check_right_if_it_white=false;
				boolean check_right_mates_if_it_black=false;
				boolean check_right_mates=false;
				//4)right
				while(x<=529)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()==white)
						{
							check_right_if_it_white=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()==black)
						{
							check_right_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63))
						{
							check_right_mates=true;
						}
					}
					if(check_right_mates==false)
					{
						g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
					}
					if(check_right_if_it_white==true)
					{
						break;
					}
					if(check_right_mates_if_it_black==true)
					{
						g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
						break;
					}
					check_right_mates=false;
					x+=63;
				}
				
					
				
				
			}
			else	if(change_place.get_logic()==LADIA && change_place.get_color()==black)
			{
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean check_top_mates_if_it_white=false;
				boolean check_top_mates_if_it_black=false;
				boolean check_top_mates=false;
				//1)top
				while(y>100)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()==black)
						{
							check_top_mates_if_it_white=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()!=black)
						{
							check_top_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63))
						{
							check_top_mates=true;
						}
					}
					if(check_top_mates==false)
					{
						g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
					}
					if(check_top_mates_if_it_white==true)
					{
						break;
					}
					if(check_top_mates_if_it_black==true)
					{
						g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
						break;
					}
					y-=63;
					check_top_mates=false;
				}
				
				y=change_place.get_draw_y();
				boolean check_bottom_if_it_white=false;
				boolean check_bottom_mates_if_it_black=false;
				boolean check_bottom_mates=false;
				//2)bottom
				while(y<=423)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()==black)
						{
							check_bottom_if_it_white=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()!=black)
						{
							check_bottom_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63))
						{
							check_bottom_mates=true;
						}
					}
					if(check_bottom_mates==false)
					{
						g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
					}
					if(check_bottom_if_it_white==true)
					{
						break;
					}
					if(check_bottom_mates_if_it_black==true)
					{
						g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
						break;
					}
					check_bottom_mates=false;
					y+=63;
				}
				y=change_place.get_draw_y();
				boolean check_left_if_it_white=false;
				boolean check_left_mates_if_it_black=false;
				boolean check_left_mates=false;
				//3)left
				while(x>=214)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()==black)
						{
							check_left_if_it_white=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()!=black)
						{
							check_left_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) &&(this.pieces.get(i).getType()==black || this.pieces.get(i).getType()!=black) )
						{
							
							check_left_mates=true;
						}
					}
					if(check_left_mates==false)
					{
						g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
					}
					if(check_left_if_it_white==true)
					{
						break;
					}
					if(check_left_mates_if_it_black==true)
					{
						g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
						break;
					}
					check_left_mates=false;
					x-=63;
				}
				x=change_place.get_draw_x();
				boolean check_right_if_it_white=false;
				boolean check_right_mates_if_it_black=false;
				boolean check_right_mates=false;
				//4)right
				while(x<=529)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()==black)
						{
							check_right_if_it_white=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()==white)
						{
							check_right_mates_if_it_black=true;
						}
						if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63))
						{
							check_right_mates=true;
						}
					}
					if(check_right_mates==false)
					{
						g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
					}
					if(check_right_if_it_white==true)
					{
						break;
					}
					if(check_right_mates_if_it_black==true)
					{
						g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
						break;
					}
					check_right_mates=false;
					x+=63;
				}
				
					
				
				
			}
			//if((Math.abs(save_x-ifxincorrect)==63 && Math.abs(save_y-ifyincorrect)==126)|| (Math.abs(save_y-ifyincorrect)==63 && Math.abs(save_x-ifxincorrect)==126))
			if(change_place.get_logic()==KON )
			{
				boolean left_top_white=false;
				boolean right_top_white=false;
				boolean if_left_top_empty=false;
				boolean if_right_top_empty=false;
				boolean left_bot_white=false;
				boolean right_bot_white=false;
				boolean if_left_bot_empty=false;
				boolean if_right_bot_empty=false;
				boolean if_left_white=false;
				boolean if_left_empty=false;
				boolean if_right_white=false;
				boolean if_right_empty=false;
				boolean if_right_bot_white=false;
				boolean if_right_bot_empty_v1=false;
				boolean if_left_bottom_white=false;
				boolean if_left_bottom_empty=false;
				for(int i=this.pieces.size()-1;i>=0;i--)
				{
					//left top 
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()-63)
							&& this.pieces.get(i).getY()==change_place.get_draw_y()-126
							&& this.pieces.get(i).getType()!=change_place.get_color()
							
							)
					{
						left_top_white=true;
						
					}
			
					
					if( this.pieces.get(i).getX()==(change_place.get_draw_x()-63)&& this.pieces.get(i).getY()==change_place.get_draw_y()-126)
					{
						if_left_top_empty=true;
					}
					//right top
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()+63)&& this.pieces.get(i).getY()==change_place.get_draw_y()-126 && this.pieces.get(i).getType()!=change_place.get_color())
					{
						right_top_white=true;
					}
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()+63)&& this.pieces.get(i).getY()==change_place.get_draw_y()-126)
					{
						if_right_top_empty=true;
					}
					//bottom left
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()-63)&& this.pieces.get(i).getY()==change_place.get_draw_y()+126 && this.pieces.get(i).getType()!=change_place.get_color() )
					{
						left_bot_white=true;
						
					}
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()-63)&& this.pieces.get(i).getY()==change_place.get_draw_y()+126)
					{
						if_left_bot_empty=true;
					}
					//bottom right
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()+63)&& this.pieces.get(i).getY()==change_place.get_draw_y()+126 && this.pieces.get(i).getType()!=change_place.get_color())
					{
						right_bot_white=true;
					}
					if(this.pieces.get(i).getX()==(change_place.get_draw_x()+63)&& this.pieces.get(i).getY()==change_place.get_draw_y()+126)
					{
						if_right_bot_empty=true;
					}
					//left
					//left top
					if(this.pieces.get(i).getY()==(change_place.get_draw_y()-63)
					&& this.pieces.get(i).getX()==(change_place.get_draw_x()-126) && 
					this.pieces.get(i).getType()!=change_place.get_color() )
					{
						if_left_white=true;
						
					}
					if(this.pieces.get(i).getY()==(change_place.get_draw_y()-63)
							&& this.pieces.get(i).getX()==(change_place.get_draw_x()-126) 
							 )
					{
						if_left_empty=true;
						
					}
					// right top
					if(this.pieces.get(i).getY()==(change_place.get_draw_y()-63)
							&& this.pieces.get(i).getX()==(change_place.get_draw_x()+126) && 
							this.pieces.get(i).getType()!=change_place.get_color() )
							{
								if_right_white=true;
								
							}
							if(this.pieces.get(i).getY()==(change_place.get_draw_y()-63)
									&& this.pieces.get(i).getX()==(change_place.get_draw_x()+126) 
									 )
							{
								if_right_empty=true;
								
							}
							//left bottom
							if(this.pieces.get(i).getY()==(change_place.get_draw_y()+63)
									&& this.pieces.get(i).getX()==(change_place.get_draw_x()+126) && 
									this.pieces.get(i).getType()!=change_place.get_color() )
							{
								if_right_bot_white=true;
							}
							if(this.pieces.get(i).getY()==(change_place.get_draw_y()+63)
									&& this.pieces.get(i).getX()==(change_place.get_draw_x()+126) 
									 )
							{
								if_right_bot_empty_v1=true;
							}
							if(this.pieces.get(i).getY()==(change_place.get_draw_y()+63)
									&& this.pieces.get(i).getX()==(change_place.get_draw_x()-126) && 
									this.pieces.get(i).getType()!=change_place.get_color() )
							{
								if_left_bottom_white=true;
							}
							if(this.pieces.get(i).getY()==(change_place.get_draw_y()+63)
									&& this.pieces.get(i).getX()==(change_place.get_draw_x()-126) 
									 )
							{
								if_left_bottom_empty=true;
							}
							//right bottom
							
							
					
					
				}
				if(if_left_bottom_empty==false)
				{
					if(change_place.get_draw_x()-126>130 && change_place.get_draw_y()+63<500)
					g2.drawRoundRect(change_place.get_draw_x()-126, change_place.get_draw_y()+63, 60, 63, 10, 10);
				}
				if(if_left_bottom_white==true)
				{
					g2.drawRoundRect(change_place.get_draw_x()-126, change_place.get_draw_y()+63, 60, 63, 10, 10);
					
				}
				if(if_right_bot_empty_v1==false)
				{
					if(change_place.get_draw_x()+126<600 && change_place.get_draw_y()+63<490)
						g2.drawRoundRect(change_place.get_draw_x()+126, change_place.get_draw_y()+63, 60, 63, 10, 10);
				}
				if(if_right_bot_white==true)
				{
					//if(change_place.get_draw_x()+126<600 && change_place.get_draw_y()+63<490)
						g2.drawRoundRect(change_place.get_draw_x()+126, change_place.get_draw_y()+63, 60, 63, 10, 10);
				}
				if(if_left_empty==false)
				{
					if(change_place.get_draw_x()-126>130 && change_place.get_draw_y()-63>40)
					g2.drawRoundRect(change_place.get_draw_x()-126, change_place.get_draw_y()-63, 60, 63, 10, 10);
				}
				if(if_left_white==true)
				{
					g2.drawRoundRect(change_place.get_draw_x()-126, change_place.get_draw_y()-63, 60, 63, 10, 10);
				}
				if(if_right_empty==false)
				{
					if(change_place.get_draw_x()+126<600 && change_place.get_draw_y()-63>40)
					g2.drawRoundRect(change_place.get_draw_x()+126, change_place.get_draw_y()-63, 60, 63, 10, 10);
				}
				if(if_right_white==true)
				{
					g2.drawRoundRect(change_place.get_draw_x()+126, change_place.get_draw_y()-63, 60, 63, 10, 10);
				}
				if(if_right_top_empty==false)
				{
					
					g2.drawRoundRect(change_place.get_draw_x()+63, change_place.get_draw_y()-126, 60, 63, 10, 10);
				}
				if(left_top_white==true)
				{
					
					g2.drawRoundRect(change_place.get_draw_x()-63, change_place.get_draw_y()-126, 60, 63, 10, 10);
				}
				if(right_top_white==true)
				{
					g2.drawRoundRect(change_place.get_draw_x()+63, change_place.get_draw_y()-126, 60, 63, 10, 10);
				}
				if(if_left_top_empty==false)
				{
					
					//e.getX()<130 || e.getX()>660)|| (e.getY()<40 || e.getY()>580
					if(change_place.get_draw_x()-63>130 && change_place.get_draw_y()-126>40)
					{
					g2.drawRoundRect(change_place.get_draw_x()-63, change_place.get_draw_y()-126, 60, 63, 10, 10);
					}
				}
				//bottom
				if(if_right_bot_empty==false)
				{
					if(change_place.get_draw_x()+63<600 && change_place.get_draw_y()+126<550)
					g2.drawRoundRect(change_place.get_draw_x()+63, change_place.get_draw_y()+126, 60, 63, 10, 10);
					
					}
				if(left_bot_white==true)
				{
					
					g2.drawRoundRect(change_place.get_draw_x()-63, change_place.get_draw_y()+126, 60, 63, 10, 10);
				}
				if(right_bot_white==true)
				{
					g2.drawRoundRect(change_place.get_draw_x()+63, change_place.get_draw_y()+126, 60, 63, 10, 10);
				}
				if(if_left_bot_empty==false)
				{
					if(change_place.get_draw_x()-63>130 && change_place.get_draw_y()+126<500)
					g2.drawRoundRect(change_place.get_draw_x()-63, change_place.get_draw_y()+126, 60, 63, 10, 10);
				}
				
			}
			if(change_place.get_logic()==SLON)
			{
				
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean if_right_top_not_empty=false;
				boolean if_right_top_empty=false;
				boolean if_right_top_has_friend=false;
				//1)top right
				while(x<=529 && y>100)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()!=change_place.get_color())
						{
							if_right_top_not_empty=true;
						}
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63)
						{
							if_right_top_empty=true;
						}
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()==change_place.get_color())
						{
							if_right_top_has_friend=true;
						}
					}
					if(if_right_top_has_friend==true)
					{
						break;
					}
					if(if_right_top_not_empty==true)
					{
						g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
						break;
					}
					if(if_right_top_empty==false)
					{
						g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
					}
					x+=63;
					y-=63;
				}
				boolean if_left_top_not_empty=false;
				boolean if_left_top_empty=false;
				boolean if_left_top_has_friend=false;
				
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				//2)top left
				 while(x>=214 && y>100)
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_left_top_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63)
							{
								if_left_top_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_left_top_has_friend=true;
							}
						}
						if(if_left_top_has_friend==true)
						{
							break;
						}
						if(if_left_top_not_empty==true)
						{
							g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
							break;
						}
						if(if_left_top_empty==false)
						{
							g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
						}
						x-=63;
						y-=63;
				 }
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				 boolean if_right_bot_not_empty=false;
					boolean if_right_bot_empty=false;
					boolean if_right_bot_has_friend=false;
				 //3)bottom right
				 while(x<=529 && y<=423 )
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_right_bot_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63)
							{
								if_right_bot_empty=true;
							}
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_right_bot_has_friend=true;
							}
						}
						if(if_right_bot_has_friend==true)
						{
							break;
						}
						if(if_right_bot_not_empty==true)
						{
							g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
							break;
						}
						if(if_right_bot_empty==false)
						{
							g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
						}
						x+=63;
						y+=63;
				 }
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				 boolean if_left_bot_not_empty=false;
					boolean if_left_bot_empty=false;
					boolean if_left_bot_has_friend=false;
				 while(x>=214 && y<=423 )
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_left_bot_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63)
							{
								if_left_bot_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_left_bot_has_friend=true;
							}
						}
						if(if_left_bot_has_friend==true)
						{
							break;
						}
						if(if_left_bot_not_empty==true)
						{
							g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
							break;
						}
						if(if_left_bot_empty==false)
						{
							g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
						}
						x-=63;
						y+=63;
				 }
				 
				
				
			}
			if(change_place.get_logic()==KING)
			{
			int	 x=change_place.get_draw_x();
			int	 y=change_place.get_draw_y();
				boolean top=false,bot=false,left=false,right=false,top_right=false,top_left=false,bot_left=false,bot_right=false;
				boolean top_e=false,bot_e=false,left_e=false,right_e=false,
						top_right_e=false,top_left_e=false,bot_left_e=false,bot_right_e=false;
				for(int i=this.pieces.size()-1;i>=0;i--)
				 {
					 if(this.pieces.get(i).getX()==x 
					&& this.pieces.get(i).getY()==y-63 &&
					this.pieces.get(i).getType()!=change_place.get_color())
					 {
						 top=true;
					 }
					 if(this.pieces.get(i).getX()==x-63 
								&& this.pieces.get(i).getY()==y &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 left=true;
								 }
					 if(this.pieces.get(i).getX()==x+63 
								&& this.pieces.get(i).getY()==y &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 right=true;
								 }
					 if(this.pieces.get(i).getX()==x 
								&& this.pieces.get(i).getY()==y+63 &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 bot=true;
								 }
					 if(this.pieces.get(i).getX()==x+63 
								&& this.pieces.get(i).getY()==y-63 &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 top_right=true;
								 }
					 if(this.pieces.get(i).getX()==x-63 
								&& this.pieces.get(i).getY()==y-63 &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 top_left=true;
								 }
					 if(this.pieces.get(i).getX()==x+63 
								&& this.pieces.get(i).getY()==y+63 &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 bot_right=true;
								 }
					 if(this.pieces.get(i).getX()==x-63 
								&& this.pieces.get(i).getY()==y+63 &&
								this.pieces.get(i).getType()!=change_place.get_color())
								 {
									 bot_left=true;
								 }
					 //empty
					 if(this.pieces.get(i).getX()==x 
								&& this.pieces.get(i).getY()==y-63 
								)
								 {
									 top_e=true;
								 }
								 if(this.pieces.get(i).getX()==x-63 
											&& this.pieces.get(i).getY()==y 
											)
											 {
												 left_e=true;
											 }
								 if(this.pieces.get(i).getX()==x+63 
											&& this.pieces.get(i).getY()==y 
											)
											 {
												 right_e=true;
											 }
								 if(this.pieces.get(i).getX()==x 
											&& this.pieces.get(i).getY()==y+63 
											)
											 {
												 bot_e=true;
											 }
								 if(this.pieces.get(i).getX()==x+63 
											&& this.pieces.get(i).getY()==y-63 
											)
											 {
												 top_right_e=true;
											 }
								 if(this.pieces.get(i).getX()==x-63 
											&& this.pieces.get(i).getY()==y-63 
											)
											 {
												 top_left_e=true;
											 }
								 if(this.pieces.get(i).getX()==x+63 
											&& this.pieces.get(i).getY()==y+63 
											)
											 {
												 bot_right_e=true;
											 }
								 if(this.pieces.get(i).getX()==x-63 
											&& this.pieces.get(i).getY()==y+63 
											)
											 {
												 bot_left_e=true;
											 }
					 
					 
				 }
				 if(top==true)
				 {
					 g2.drawRoundRect(x, y-63, 60, 63, 10, 10);
				 }
				 if(bot==true)
				 {
					 g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
				 }
				 if(left==true)
				 {
					 g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
				 }
				 if(right==true)
				 {
					 g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
				 }
				 if(top_right==true)
				 {
					 g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
				 }
				 if(top_left==true)
				 {
					 g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
				 }
				 if(bot_right==true)
				 {
					 g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
				 }
				 if(bot_left==true)
				 {
					 g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
				 }
				 //empty
				 if(top_e==false)
				 {
					 if(y-63>40)
					 g2.drawRoundRect(x, y-63, 60, 63, 10, 10);
				 }
				 if(bot_e==false)
				 {
					 if(y+63<500)
					 g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
				 }
				 if(left_e==false)
				 {
					if( x-63>150)
					 g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
				 }
				 if(right_e==false)
				 {
					if( x+63<600)
					 g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
				 }
				 if(top_right_e==false)
				 {
					 if(y-63>40 && x+63<550)
					 g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
				 }
				 if(top_left_e==false)
				 {
					 if(y-63>40 &&  x-63>150)
					 g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
				 }
				 if(bot_right_e==false)
				 {
					 if( y+63<500 && x+63<550)
					 g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
				 }
				 if(bot_left_e==false)
				 {
					if( y+63<500 && x-63>150)
					 g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
				 }
				
			}
			if(change_place.get_logic()==QUEEN)
			{
				int x=change_place.get_draw_x();
				int y=change_place.get_draw_y();
				boolean if_right_top_not_empty=false;
				boolean if_right_top_empty=false;
				boolean if_right_top_has_friend=false;
				//1)top right
				while(x<=529 && y>100)
				{
					for(int i=this.pieces.size()-1;i>=0;i--)
					{
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()!=change_place.get_color())
						{
							if_right_top_not_empty=true;
						}
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63)
						{
							if_right_top_empty=true;
						}
						if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()==change_place.get_color())
						{
							if_right_top_has_friend=true;
						}
					}
					if(if_right_top_has_friend==true)
					{
						break;
					}
					if(if_right_top_not_empty==true)
					{
						g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
						break;
					}
					if(if_right_top_empty==false)
					{
						g2.drawRoundRect(x+63, y-63, 60, 63, 10, 10);
					}
					x+=63;
					y-=63;
				}
				boolean if_left_top_not_empty=false;
				boolean if_left_top_empty=false;
				boolean if_left_top_has_friend=false;
				
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				//2)top left
				 while(x>=214 && y>100)
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_left_top_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63)
							{
								if_left_top_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y-63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_left_top_has_friend=true;
							}
						}
						if(if_left_top_has_friend==true)
						{
							break;
						}
						if(if_left_top_not_empty==true)
						{
							g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
							break;
						}
						if(if_left_top_empty==false)
						{
							g2.drawRoundRect(x-63, y-63, 60, 63, 10, 10);
						}
						x-=63;
						y-=63;
				 }
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				 boolean if_right_bot_not_empty=false;
					boolean if_right_bot_empty=false;
					boolean if_right_bot_has_friend=false;
				 //3)bottom right
				 while(x<=529 && y<=423 )
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_right_bot_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63)
							{
								if_right_bot_empty=true;
							}
							if(this.pieces.get(i).getX()==x+63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_right_bot_has_friend=true;
							}
						}
						if(if_right_bot_has_friend==true)
						{
							break;
						}
						if(if_right_bot_not_empty==true)
						{
							g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
							break;
						}
						if(if_right_bot_empty==false)
						{
							g2.drawRoundRect(x+63, y+63, 60, 63, 10, 10);
						}
						x+=63;
						y+=63;
				 }
				 x=change_place.get_draw_x();
				 y=change_place.get_draw_y();
				 boolean if_left_bot_not_empty=false;
					boolean if_left_bot_empty=false;
					boolean if_left_bot_has_friend=false;
				 while(x>=214 && y<=423 )
				 {
					 for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()!=change_place.get_color())
							{
								if_left_bot_not_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63)
							{
								if_left_bot_empty=true;
							}
							if(this.pieces.get(i).getX()==x-63 && this.pieces.get(i).getY()==y+63 && this.pieces.get(i).getType()==change_place.get_color())
							{
								if_left_bot_has_friend=true;
							}
						}
						if(if_left_bot_has_friend==true)
						{
							break;
						}
						if(if_left_bot_not_empty==true)
						{
							g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
							break;
						}
						if(if_left_bot_empty==false)
						{
							g2.drawRoundRect(x-63, y+63, 60, 63, 10, 10);
						}
						x-=63;
						y+=63;
				 }
				  x=change_place.get_draw_x();
					 y=change_place.get_draw_y();
					boolean check_top_mates_if_it_white=false;
					boolean check_top_mates_if_it_black=false;
					boolean check_top_mates=false;
					//1)top
					while(y>100)
					{
						for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()==change_place.get_color())
							{
								check_top_mates_if_it_white=true;
							}
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63) && this.pieces.get(i).getType()!=change_place.get_color())
							{
								check_top_mates_if_it_black=true;
							}
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y-63))
							{
								check_top_mates=true;
							}
						}
						if(check_top_mates==false)
						{
							g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
						}
						if(check_top_mates_if_it_white==true)
						{
							break;
						}
						if(check_top_mates_if_it_black==true)
						{
							g2.drawRoundRect(x, y-59, 60, 63, 10, 10);
							break;
						}
						y-=63;
						check_top_mates=false;
					}
					
					y=change_place.get_draw_y();
					boolean check_bottom_if_it_white=false;
					boolean check_bottom_mates_if_it_black=false;
					boolean check_bottom_mates=false;
					//2)bottom
					while(y<=423)
					{
						for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()==change_place.get_color())
							{
								check_bottom_if_it_white=true;
							}
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63) && this.pieces.get(i).getType()!=change_place.get_color())
							{
								check_bottom_mates_if_it_black=true;
							}
							if(this.pieces.get(i).getX()==x && this.pieces.get(i).getY()==(y+63))
							{
								check_bottom_mates=true;
							}
						}
						if(check_bottom_mates==false)
						{
							g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
						}
						if(check_bottom_if_it_white==true)
						{
							break;
						}
						if(check_bottom_mates_if_it_black==true)
						{
							g2.drawRoundRect(x, y+63, 60, 63, 10, 10);
							break;
						}
						check_bottom_mates=false;
						y+=63;
					}
					y=change_place.get_draw_y();
					boolean check_left_if_it_white=false;
					boolean check_left_mates_if_it_black=false;
					boolean check_left_mates=false;
					//3)left
					while(x>=214)
					{
						for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()==change_place.get_color())
							{
								check_left_if_it_white=true;
							}
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) && this.pieces.get(i).getType()!=change_place.get_color())
							{
								check_left_mates_if_it_black=true;
							}
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x-63) &&(this.pieces.get(i).getType()==change_place.get_color() || this.pieces.get(i).getType()!=change_place.get_color()) )
							{
								
								check_left_mates=true;
							}
						}
						if(check_left_mates==false)
						{
							g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
						}
						if(check_left_if_it_white==true)
						{
							break;
						}
						if(check_left_mates_if_it_black==true)
						{
							g2.drawRoundRect(x-63, y, 60, 63, 10, 10);
							break;
						}
						check_left_mates=false;
						x-=63;
					}
					x=change_place.get_draw_x();
					boolean check_right_if_it_white=false;
					boolean check_right_mates_if_it_black=false;
					boolean check_right_mates=false;
					//4)right
					while(x<=529)
					{
						for(int i=this.pieces.size()-1;i>=0;i--)
						{
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()==change_place.get_color())
							{
								check_right_if_it_white=true;
							}
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63) && this.pieces.get(i).getType()!=change_place.get_color())
							{
								check_right_mates_if_it_black=true;
							}
							if(this.pieces.get(i).getY()==y && this.pieces.get(i).getX()==(x+63))
							{
								check_right_mates=true;
							}
						}
						if(check_right_mates==false)
						{
							g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
						}
						if(check_right_if_it_white==true)
						{
							break;
						}
						if(check_right_mates_if_it_black==true)
						{
							g2.drawRoundRect(x+63, y, 60, 63, 10, 10);
							break;
						}
						check_right_mates=false;
						x+=63;
					}
				 
			}
				
		
		
		}
	
		
	}
	

	private  void createAndAddPiece( int x, int y,Image img,int type,int logic_of_fig,int number) {
	 
		figurs figura = new figurs(img, x, y,type,logic_of_fig,number);
		pieces.add(figura);
		
	}
	public static void main(String[] args) {
		new button();
	}

}
