package button_model;

import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.sound.sampled.FloatControl;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class change_place implements MouseListener,MouseMotionListener,ActionListener {
	private static List<figurs> figurs_v1;
	private static List<figurs> save_figurs;
    private 	ServerSocket socketserver=null;
    private Socket server=null;
    private Socket client=null;
    private static volatile king_is_alive_client check_king_client=null;
    private static volatile king_is_alive_socket check_king_server=null;
	private static button panel;
	private volatile static figurs current_figur=null;
	public volatile static boolean destroy_soccet=false;
	public volatile static boolean destroy_client=false;
	private volatile static boolean is_client_exist=false;
	
    private volatile static boolean is_server_exist=false;
    
    public volatile static boolean implement_if_server=false;
    private volatile static DataInputStream  dinput_server=null;
    private volatile static DataInputStream dinput_client=null;
    private volatile static DataOutputStream dout_server=null;
    private volatile static DataOutputStream dout_client=null;
	private JDialog dialog=new JDialog();
	public static volatile soccet new_soccet=null;
	private int tempX,tempY;
	
	public static volatile client new_client=null;
	private static int for_draw_x,for_draw_y,for_save_logic,for_save_color;
	public static boolean if_figur_is_save=false;
	private static int xPR,yPR,xR,yR;
	private  Sound sd=new Sound();
	private  JToggleButton on_off=new JToggleButton("ON");
	private int tempx=0,tempy=0;
	private boolean check_mates=false;
	private  JDialog dialog2=new JDialog();
	private  figurs savefigur;
	public static boolean title_client=false;
	public static boolean title_server=false;
	private static boolean checkforwin=false; 
	private int ifxincorrect,ifyincorrect;
    private static final int LADIA=1,KON=2,SLON=3,QUEEN=4,KING=5,PESHKA=6;
	private final static int blackcolor=0,whitecolor=1;
	public static volatile int cnt=1;
	private JLabel lab2=new JLabel("Create port : ");
	private JTextField field=new JTextField(); 
	public change_place(List<figurs> figurs_v1,button panel)
	{
		
		this.figurs_v1=figurs_v1;
		this.save_figurs=figurs_v1;
		
		this.panel=panel;
		this.panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("MENU");
	
		  lblNewLabel.setForeground(new Color(0, 100, 0));
		  lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		  lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		  lblNewLabel.setBounds(0, 130, 100, 39);
		  this.panel.add(lblNewLabel);	 
		JButton btn=new JButton("Create Server");
		JButton btn2=new JButton("Connect to Server");
		JButton btn3=new JButton("Instruction: ");
		btn.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		btn.setBorderPainted(true);
		btn.addActionListener(this);
	//	btn.setBackground(Color.ORANGE);
		btn.setForeground(Color.GREEN.darker().darker());
// 2
		
		//btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
		btn.setBounds(10, 210, 107, 23);
		 btn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		 btn.setHorizontalAlignment(SwingConstants.LEFT);
		 btn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		 btn.setHorizontalAlignment(SwingConstants.LEFT);
		 
		 
		 
		 //3
		 btn3.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		 btn3.setBorderPainted(true);
		 btn3.addActionListener(this);
		//	btn.setBackground(Color.ORANGE);
		 btn3.setForeground(Color.GREEN.darker().darker());
	// 2
			
			//btn.setContentAreaFilled(false);
		 btn3.setFocusPainted(false);
		 btn3.setBounds(10, 240, 107, 23);
		 btn3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		 btn3.setHorizontalAlignment(SwingConstants.LEFT);
		 btn3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		 btn3.setHorizontalAlignment(SwingConstants.LEFT);
		 // 2 new
		 btn2.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
			btn2.setBorderPainted(true);
			btn2.addActionListener(this);
		//	btn2.setBackground(Color.ORANGE);
			btn2.setForeground(Color.GREEN.darker().darker());
		 btn2.setFocusPainted(false);
			btn2.setBounds(10, 180, 107, 23);
			 btn2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
			 btn2.setHorizontalAlignment(SwingConstants.LEFT);
		
			 //3
			JLabel mutliplayer=new JLabel("1)MultyPlayer : ");
			mutliplayer.setOpaque(true);
			mutliplayer.setBackground(Color.WHITE);
			mutliplayer.setForeground(Color.BLACK.darker().darker());
			//btn.setContentAreaFilled(false);
			
			mutliplayer.setBounds(0, 160, 90, 23);
			
			 JLabel usual_game=new JLabel("2)Single Player : ");
			 usual_game.setOpaque(true);
			 usual_game.setBackground(Color.WHITE);
			 usual_game.setForeground(Color.BLACK.darker().darker());
			 usual_game.setBounds(0, 280, 100, 23);
			 JButton reset=new JButton("PLAY AGAIN");
			 reset.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
			 reset.setBorderPainted(true);
			
			 reset.addActionListener(this);
			//	btn2.setBackground(Color.ORANGE);
			 reset.setForeground(Color.GREEN.darker().darker());
			 reset.setFocusPainted(false);
			 reset.setBounds(0, 305, 100, 23);
			 reset.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 12));
			 reset.setHorizontalAlignment(SwingConstants.LEFT);
			 JLabel Music=new JLabel("     Play Music");
			 Music.setOpaque(true);
			 Music.setForeground(Color.BLACK);
			 Music.setBounds(10, 340, 90, 23); //300
			 Music.setBackground(Color.pink);
			
				dialog2.setTitle("Waiting for client");
				dialog2.setSize(600, 400);
				dialog2.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog2.setLocationRelativeTo(null);
				dialog2.setLayout(null);
				
				
			  JProgressBar bar = new JProgressBar();
				bar.setBounds(100, 200, 400, 40);
				bar.setStringPainted(true);
				bar.setBackground(Color.YELLOW);
				bar.setForeground(Color.red);
				bar.setIndeterminate(true);
				bar.setMinimum(0);
				bar.setMaximum(1000);
				bar.setString("Waiting for client...");
				dialog2.add(bar);
			 on_off.addActionListener(this);
				//btn.setContentAreaFilled(false);
				on_off.setBounds(10,370,90,23); //330
				on_off.setForeground(Color.green);
				on_off.setFocusPainted(false);
				JSlider valuem=new JSlider(JSlider.HORIZONTAL,0,50,50);
				valuem.setMinorTickSpacing(10);
				valuem.setPaintLabels(true);
				valuem.setPaintTicks(true);
				valuem.setMajorTickSpacing(10);
				//on_off.setBounds(10,330,90,23);
				valuem.setBounds(0,400 , 115, 40); //360
				valuem.setSnapToTicks(true);
				valuem.addChangeListener(new ChangeListener()
						{

							@Override
							public void stateChanged(ChangeEvent e) {
								int a=valuem.getValue();
								FloatControl gainControl = 
									    (FloatControl) Sound.clip.getControl(FloatControl.Type.MASTER_GAIN);
								if(a==0)
								{
									
										gainControl.setValue(gainControl.getMinimum());
								}
								
								if(a==50)
								{
									gainControl.setValue(gainControl.getMaximum());
								}
								if(a==40)
								{
									gainControl.setValue(gainControl.getMaximum()-10.0f);
								}
								if(a==30)
								{
									gainControl.setValue(gainControl.getMaximum()-15.0f);
								}
								if(a==20)
								{
									gainControl.setValue(gainControl.getMaximum()-20.0f);
								}
								if(a==10)
								{
									gainControl.setValue(gainControl.getMaximum()-25.0f);
								}
							
								
							}
					
						}
						
						
						);
				JLabel change_value=new JLabel("    Change volue");
				change_value.setOpaque(true);
				change_value.setForeground(Color.BLUE);
				
				change_value.setBounds(0, 430,115, 45);
				this.panel.add(Music);
		 this.panel.add(btn);
		 this.panel.add(btn2);
		 this.panel.add(mutliplayer);
		 this.panel.add(reset);
		 this.panel.add(on_off);
		 this.panel.add(usual_game);
		 this.panel.add(valuem);
		 this.panel.add(change_value);
		 this.panel.add(btn3);
	    sd.start();
	 //  sd.setDaemon(true);
		
		
	}
 
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent r) {
		System.out.println("Xpr: "+r.getX()+" Ypr: "+r.getY());
		int x=r.getX();
		int y=r.getY();
		xPR=r.getX();
		yPR=r.getY();
		
		for(int i=this.figurs_v1.size()-1;i>=0;i--)
		{
			figurs temp=this.figurs_v1.get(i);
			{
				if(temp.getX()<=x && temp.getY()<=y && temp.getX()+temp.getWidth()>=x && temp.getY()+temp.getHeight()>=y)
				{
					if(checkforwin==false && ((temp.getType()==whitecolor && cnt%2!=0)|| (temp.getType()==blackcolor && cnt%2==0)) && is_server_exist==false
							&& is_client_exist==false
							)
					{
						ifxincorrect=temp.getX();
						ifyincorrect=temp.getY();
					    tempX=x-temp.getX();
					    tempY=y-temp.getY();
					    savefigur=temp;
				        for_draw_x=savefigur.getX();
				        for_draw_y=savefigur.getY();
				        for_save_logic=savefigur.getLogic();
				        for_save_color=savefigur.getType();
					    break;
					}
					if(is_server_exist==true && (temp.getType()==blackcolor && cnt%2==0)&&  checkforwin==false)
					{
						ifxincorrect=temp.getX();
						ifyincorrect=temp.getY();
					    tempX=x-temp.getX();
					    tempY=y-temp.getY();
					    savefigur=temp;
				        for_draw_x=savefigur.getX();
				        for_draw_y=savefigur.getY();
				        for_save_logic=savefigur.getLogic();
				        for_save_color=savefigur.getType();
					    break;
					}
					if(is_client_exist==true && (temp.getType()==whitecolor && cnt%2!=0) && checkforwin==false)
					{
						ifxincorrect=temp.getX();
						ifyincorrect=temp.getY();
					    tempX=x-temp.getX();
					    tempY=y-temp.getY();
					    savefigur=temp;
				        for_draw_x=savefigur.getX();
				        for_draw_y=savefigur.getY();
				        for_save_logic=savefigur.getLogic();
				        for_save_color=savefigur.getType();
					    break;
					}
				}
			}
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		xR=e.getX();
		yR=e.getY();	
		boolean flag=false;
		boolean flag2=false;
		boolean flag_for_peshka_left=false;
		boolean flag_for_peshka_right=false;
		System.out.println("Xrel: "+e.getX()+" Yrel: "+e.getY());
		int save_y=0,save_x=0;
		if(savefigur!=null){
		
		  /*
		   * Create new coord
		   */
			if(e.getY()>=47 && e.getY()<107)
				save_y=45;
			else if(e.getY()>=107 && e.getY()<170)
				save_y=108;
			else if(e.getY()>=170 && e.getY()<235)
				save_y=171;
			else if(e.getY()>=235 && e.getY()<299)
				save_y=234;
			else if(e.getY()>=299 && e.getY()<363)
				save_y=297;
			else if(e.getY()>=363 && e.getY()<427)
				save_y=360;
			else if(e.getY()>=427 && e.getY()<491)
				save_y=423;
			else if(e.getY()>=491 && e.getY()<554)
				save_y=486;
		  
			
			if(e.getX()>=148 && e.getX()<207)
				save_x=151;
			else if(e.getX()>=207 && e.getX()<272)
				save_x=214;
			else if(e.getX()>=272 && e.getX()<334)
				save_x=277;
			else if(e.getX()>=334 && e.getX()<398)
				save_x=340;
			else if(e.getX()>=398 && e.getX()<462)
				save_x=403;
			else if(e.getX()>=462 && e.getX()<526)
				save_x=466;
			else if(e.getX()>=526 && e.getX()<588)
				save_x=529;
			else if(e.getX()>=588 && e.getX()<652)
				save_x=592;
	
			/*
			 * 
			 * IF we have friendly figur on cell
			 */
			for(int i=this.figurs_v1.size()-1;i>=0;i--)
			{
				if(this.figurs_v1.get(i).getX()==save_x && this.figurs_v1.get(i).getY()==save_y && this.figurs_v1.get(i).getType()==savefigur.getType())
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
					break;
				}
			}
			/*
			 * if coord out of board
			 * 
			 */
			if((e.getX()<130 || e.getX()>660)|| (e.getY()<40 || e.getY()>580))
			{
				save_x=ifxincorrect;
				save_y=ifyincorrect;
			}

			System.out.println("ifx: "+ ifxincorrect+ " ify: "+ifyincorrect);
			System.out.println("savex: "+ save_x+ " savey: "+save_y);
			if(savefigur.getLogic()==LADIA)
			{
				if(save_x!=ifxincorrect && save_y!=ifyincorrect )
				{
					System.out.println("LADIA test 1");
					save_x=ifxincorrect;
					save_y=ifyincorrect;
					
				}
				if(save_x!=ifxincorrect && save_y==ifyincorrect)
				{
					int check;
					int limit;
					if(save_x<ifxincorrect)
					{
						check=save_x;
					}
					else
					{
						check=ifxincorrect;
					}
					if(save_x>ifxincorrect)
					{
						limit=save_x;
					}
					else
					{
						limit=ifxincorrect;
					}
					
					for(int j=check;j<=limit;j+=63)
					{
						
						for(int i=this.figurs_v1.size()-1;i>=0;i--)
					{
					
						
						if(this.figurs_v1.get(i).getY()==save_y
						   && this.figurs_v1.get(i).getX()==j && j!=save_x
								)
						{
							System.out.println("ERROR: ");
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							break;
						}
						
					}
				}
					
				}
				else if(save_x==ifxincorrect && save_y!=ifyincorrect)
				{
					int check;
					int limit;
					if(save_y<ifyincorrect)
					{
						check=save_y;
					}
					else
					{
						check=ifyincorrect;
					}
					if(save_y>ifyincorrect)
					{
						limit=save_y;
					}
					else
					{
						limit=ifyincorrect;
					}
					
					for(int j=check;j<=limit;j+=63)
					{
						
						for(int i=this.figurs_v1.size()-1;i>=0;i--)
					{
					
						System.out.print("Vhozhu v usl: "+j+" ");
						if(this.figurs_v1.get(i).getX()==save_x
						   && this.figurs_v1.get(i).getY()==j && j!=save_y
								)
						{
							System.out.println("ERROR: ");
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							break;
						}
						
					}
				}
				}
				
				
			}
			else	if(savefigur.getLogic()==SLON)
			{
				if(Math.abs(save_x-ifxincorrect)!=Math.abs(save_y-ifyincorrect))
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
				}
				if(save_x==ifxincorrect || save_y==ifyincorrect )
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
					
				}
				if(ifxincorrect>save_x && ifyincorrect>save_y ) // vverh vlevo
				{
					int i,j;
					boolean check_slon=false;
					for(i=save_x,j=save_y;i<ifxincorrect && j<ifyincorrect && check_slon==false;i+=63,j+=63)
					{
						for(int k=this.figurs_v1.size()-1;k>=0;k--)
						{
							if(this.figurs_v1.get(k).getX()<ifxincorrect && this.figurs_v1.get(k).getY()<ifyincorrect
								&&	this.figurs_v1.get(k).getX()>save_x && this.figurs_v1.get(k).getY()>save_y
								&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
								this.figurs_v1.get(k).getY()==j
									)
							{
								check_slon=true;
								break;
							}
						}
					}
					if(check_slon==true)
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						System.out.println("Slon");
					}
				}
				else if(ifxincorrect>save_x && ifyincorrect<save_y)//vniz vleva
				{
					int i,j;
					boolean check_slon=false;
					for(i=save_x,j=save_y;i<ifxincorrect && j>ifyincorrect && check_slon==false;i+=63,j-=63)
					{
						for(int k=this.figurs_v1.size()-1;k>=0;k--)
						{
							if(this.figurs_v1.get(k).getX()<ifxincorrect && this.figurs_v1.get(k).getY()<save_y
								&&	this.figurs_v1.get(k).getX()>save_x && this.figurs_v1.get(k).getY()>ifyincorrect
								&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
								this.figurs_v1.get(k).getY()==j 
									)
							{
								System.out.println("TEST SLON 1");
								check_slon=true;
								break;
							}
						}
					}
					if(check_slon==true)
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						System.out.println("Slon2");
					}
					else
					{
						
					}
				}
				else if(ifxincorrect<save_x && ifyincorrect<save_y) //vniz vpravo
				{
					int i,j;
					boolean check_slon=false;
					for(i=save_x,j=save_y;i>ifxincorrect && j>ifyincorrect && check_slon==false;i-=63,j-=63)
					{
						for(int k=this.figurs_v1.size()-1;k>=0;k--)
						{
							if(this.figurs_v1.get(k).getX()<save_x && this.figurs_v1.get(k).getY()<save_y
								&&	this.figurs_v1.get(k).getX()>ifxincorrect && this.figurs_v1.get(k).getY()>ifyincorrect
								&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
								this.figurs_v1.get(k).getY()==j
									)
							{
								check_slon=true;
								break;
							}
						}
					}
					
					
					if(check_slon==true)
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						System.out.println("Slon3");
					}
					
				}
				else if(ifxincorrect<save_x && ifyincorrect>save_y)
				{
					int i,j;
					boolean check_slon=false;
					for(i=save_x,j=save_y;i>ifxincorrect && j<ifyincorrect && check_slon==false;i-=63,j+=63)
					{
						for(int k=this.figurs_v1.size()-1;k>=0;k--)
						{
							if(this.figurs_v1.get(k).getX()<save_x && this.figurs_v1.get(k).getY()<ifyincorrect
								&&	this.figurs_v1.get(k).getX()>ifxincorrect && this.figurs_v1.get(k).getY()>save_y
								&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
								this.figurs_v1.get(k).getY()==j
									)
							{
								check_slon=true;
								break;
							}
						}
					}
					
					
					if(check_slon==true)
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						System.out.println("Slon3");
					}
				}
				
			}
			else	if(savefigur.getLogic()==KON)
			{
				if((Math.abs(save_x-ifxincorrect)==63 && Math.abs(save_y-ifyincorrect)==126)|| (Math.abs(save_y-ifyincorrect)==63 && Math.abs(save_x-ifxincorrect)==126))
				{
					
				}
				else
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
				}
			}
			else if(savefigur.getLogic()==QUEEN)
			{
				if(Math.abs(save_x-ifxincorrect)==Math.abs(save_y-ifyincorrect))
				{
					if(ifxincorrect>save_x && ifyincorrect>save_y ) // vverh vlevo
					{
						int i,j;
						boolean check_slon=false;
						for(i=save_x,j=save_y;i<ifxincorrect && j<ifyincorrect && check_slon==false;i+=63,j+=63)
						{
							for(int k=this.figurs_v1.size()-1;k>=0;k--)
							{
								if(this.figurs_v1.get(k).getX()<ifxincorrect && this.figurs_v1.get(k).getY()<ifyincorrect
									&&	this.figurs_v1.get(k).getX()>save_x && this.figurs_v1.get(k).getY()>save_y
									&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
									this.figurs_v1.get(k).getY()==j
										)
								{
									check_slon=true;
									break;
								}
							}
						}
						if(check_slon==true)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							System.out.println("Slon");
						}
					}
					else if(ifxincorrect>save_x && ifyincorrect<save_y)//vniz vleva
					{
						int i,j;
						boolean check_slon=false;
						for(i=save_x,j=save_y;i<ifxincorrect && j>ifyincorrect && check_slon==false;i+=63,j-=63)
						{
							for(int k=this.figurs_v1.size()-1;k>=0;k--)
							{
								if(this.figurs_v1.get(k).getX()<ifxincorrect && this.figurs_v1.get(k).getY()<save_y
									&&	this.figurs_v1.get(k).getX()>save_x && this.figurs_v1.get(k).getY()>ifyincorrect
									&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
									this.figurs_v1.get(k).getY()==j 
										)
								{
									System.out.println("TEST SLON 1");
									check_slon=true;
									break;
								}
							}
						}
						if(check_slon==true)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							System.out.println("Slon2");
						}
						else
						{
							
						}
					}
					else if(ifxincorrect<save_x && ifyincorrect<save_y) //vniz vpravo
					{
						int i,j;
						boolean check_slon=false;
						for(i=save_x,j=save_y;i>ifxincorrect && j>ifyincorrect && check_slon==false;i-=63,j-=63)
						{
							for(int k=this.figurs_v1.size()-1;k>=0;k--)
							{
								if(this.figurs_v1.get(k).getX()<save_x && this.figurs_v1.get(k).getY()<save_y
									&&	this.figurs_v1.get(k).getX()>ifxincorrect && this.figurs_v1.get(k).getY()>ifyincorrect
									&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
									this.figurs_v1.get(k).getY()==j
										)
								{
									check_slon=true;
									break;
								}
							}
						}
						
						
						if(check_slon==true)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							System.out.println("Slon3");
						}
						
					}
					else if(ifxincorrect<save_x && ifyincorrect>save_y)
					{
						int i,j;
						boolean check_slon=false;
						for(i=save_x,j=save_y;i>ifxincorrect && j<ifyincorrect && check_slon==false;i-=63,j+=63)
						{
							for(int k=this.figurs_v1.size()-1;k>=0;k--)
							{
								if(this.figurs_v1.get(k).getX()<save_x && this.figurs_v1.get(k).getY()<ifyincorrect
									&&	this.figurs_v1.get(k).getX()>ifxincorrect && this.figurs_v1.get(k).getY()>save_y
									&& 	this.figurs_v1.get(k)!=savefigur && this.figurs_v1.get(k).getX()==i &&
									this.figurs_v1.get(k).getY()==j
										)
								{
									check_slon=true;
									break;
								}
							}
						}
						
						
						if(check_slon==true)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							System.out.println("Slon3");
						}
					}
				}
				else if(save_x==ifxincorrect || save_y==ifyincorrect)
				{
					if(save_x!=ifxincorrect && save_y==ifyincorrect)
					{
						int check;
						int limit;
						if(save_x<ifxincorrect)
						{
							check=save_x;
						}
						else
						{
							check=ifxincorrect;
						}
						if(save_x>ifxincorrect)
						{
							limit=save_x;
						}
						else
						{
							limit=ifxincorrect;
						}
						
						for(int j=check;j<=limit;j+=63)
						{
							
							for(int i=this.figurs_v1.size()-1;i>=0;i--)
						{
						
							System.out.print("Vhozhu v usl: "+j+" ");
							if(this.figurs_v1.get(i).getY()==save_y
							   && this.figurs_v1.get(i).getX()==j && j!=save_x
									)
							{
								System.out.println("ERROR: ");
								save_x=ifxincorrect;
								save_y=ifyincorrect;
								break;
							}
							
						}
					}
						
					}
					else if(save_x==ifxincorrect && save_y!=ifyincorrect)
					{
						int check;
						int limit;
						if(save_y<ifyincorrect)
						{
							check=save_y;
						}
						else
						{
							check=ifyincorrect;
						}
						if(save_y>ifyincorrect)
						{
							limit=save_y;
						}
						else
						{
							limit=ifyincorrect;
						}
						
						for(int j=check;j<=limit;j+=63)
						{
							
							for(int i=this.figurs_v1.size()-1;i>=0;i--)
						{
						
							System.out.print("Vhozhu v usl: "+j+" ");
							if(this.figurs_v1.get(i).getX()==save_x
							   && this.figurs_v1.get(i).getY()==j && j!=save_y
									)
							{
								System.out.println("ERROR: ");
								save_x=ifxincorrect;
								save_y=ifyincorrect;
								break;
							}
							
						}
					}
					}
				}
				else
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
				}
			}
			else if(savefigur.getLogic()==KING)
			{
				//vverh vniz
				if(save_x==ifxincorrect && Math.abs(save_y-ifyincorrect)==63)
				{
					
				}
				//vpravo vlevo
				else if(save_y==ifyincorrect && Math.abs(save_x-ifxincorrect)==63)
				{
					
				}
				else if( Math.abs(save_x-ifxincorrect)==63 && Math.abs(save_y-ifyincorrect)==63)
				{
					
				}
				else
				{
					save_x=ifxincorrect;
					save_y=ifyincorrect;
				}
			}
			else if(savefigur.getLogic()==PESHKA)
			{
				if(savefigur.getType()==whitecolor)
				{
					
					
					if(ifyincorrect-save_y>63 )
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						
						
					}
					else	if(ifyincorrect-save_y==63 && save_x==ifxincorrect)// sverhu est cherniy
					{
						for(int i=this.figurs_v1.size()-1;i>=0;i--)
					{
						if(this.figurs_v1.get(i).getX()==save_x &&   this.figurs_v1.get(i).getY()==save_y && this.figurs_v1.get(i).getType()==blackcolor)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							
							break;
						}
					}
						
					}
					else	if(Math.abs(save_x-ifxincorrect)==63 && ifyincorrect-save_y==63)
					{
					
							for(int i=this.figurs_v1.size()-1;i>=0;i--)
							{
								if(this.figurs_v1.get(i).getX()==save_x &&   this.figurs_v1.get(i).getY()==save_y && this.figurs_v1.get(i).getType()==blackcolor)
								{
									flag_for_peshka_left=true;
									
									break;
								}
							}
							if(flag_for_peshka_left==false)
							{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							}
							else
							{
								
							}
						
					}
					else
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
					}
					
				
					
					
					
					
				}
				else if(savefigur.getType()==blackcolor)
				{

					if(save_y-ifyincorrect>63 )
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
						
						
					}
					else	if(save_y-ifyincorrect==63 && save_x==ifxincorrect)
					{
						for(int i=this.figurs_v1.size()-1;i>=0;i--)
					{
						if(this.figurs_v1.get(i).getX()==save_x &&   this.figurs_v1.get(i).getY()==save_y && this.figurs_v1.get(i).getType()==whitecolor)
						{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							
							break;
						}
					}
						
					}
					else	if(Math.abs(save_x-ifxincorrect)==63 && save_y-ifyincorrect==63)
					{
					
							for(int i=this.figurs_v1.size()-1;i>=0;i--)
							{
								if(this.figurs_v1.get(i).getX()==save_x &&   this.figurs_v1.get(i).getY()==save_y && this.figurs_v1.get(i).getType()==whitecolor)
								{
									flag_for_peshka_left=true;
									
									break;
								}
							}
							if(flag_for_peshka_left==false)
							{
							save_x=ifxincorrect;
							save_y=ifyincorrect;
							}
							else
							{
								
							}
						
					}
					else
					{
						save_x=ifxincorrect;
						save_y=ifyincorrect;
					}
					
					
					
				}
				
				
				
			}
			System.out.println("saveX: "+save_x+" savey: "+save_y);
			System.out.println("ifx: "+ifxincorrect+" ify: "+ifyincorrect);
			if(save_x!=0 && save_y!=0)
			{
			savefigur.setX(save_x);
			savefigur.setY(save_y);
			setCurrent_figur(savefigur);
		
			
			for(int i=this.figurs_v1.size()-1;i>=0;i--)
			{
				if(this.figurs_v1.get(i).getX()==savefigur.getX() && this.figurs_v1.get(i).getY()==savefigur.getY()
					&&	this.figurs_v1.get(i).getType()!=savefigur.getType()
						
				  )
				    {
					this.figurs_v1.remove(i);
					break;
				    }
			}
			/*
			 * 
			 * Search of king
			 */
			for(int i=this.figurs_v1.size()-1;i>=0;i--)
			{
				if(
					this.figurs_v1.get(i).getType()==blackcolor && this.figurs_v1.get(i).getLogic()==KING
						)
				{
					flag=true;
				}
				
			}
			for(int i=this.figurs_v1.size()-1;i>=0;i--)
			{
				if(
					this.figurs_v1.get(i).getType()==whitecolor && this.figurs_v1.get(i).getLogic()==KING
						)
				{
					flag2=true;
				}
				
			}
			
			
			if(is_server_exist==true)
			{
				write_inf_to_client_from_server();
				
			}
			if(is_client_exist==true)
			{
				write_inf_to_server_from_client();
				
			}
			this.panel.repaint();
			/*
			 * 
			 * Win
			 */
			if(flag==false  && is_server_exist==false && is_client_exist==false)
			{
				JOptionPane.showMessageDialog(this.panel, "WHITE WIN");
				checkforwin=true;
				sd.interrupt();
				panel.repaint();
				
			}
			else if(flag2==false && is_server_exist==false && is_client_exist==false)
			{
				JOptionPane.showMessageDialog(this.panel, "BLACK WIN");
				
				checkforwin=true;
				sd.interrupt();
				panel.repaint();
			}
			}
		 
			if(savefigur.getX()!=ifxincorrect || savefigur.getY()!=ifyincorrect )
			{
				
				
				
				
				cnt++;
				ifxincorrect=0;
				ifyincorrect=0;
				
			}
		}
		
      
    	   
		
		
		savefigur=null;
		xPR=0;
		yPR=0;
		yR=0;
		xR=0;
		tempx=0;
		tempy=0;
		setCurrent_figur(null);
		check_mates=false;
		flag_for_peshka_left=false;
		if_figur_is_save=false;
		implement_if_server=false;
		for_draw_x=0;
		
		for_draw_y=0;
		for_save_logic=0;
		for_save_color=0;
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(savefigur!=null)
		{
			
		
			
			savefigur.setX(e.getX()-tempX);
			savefigur.setY(e.getY()-tempY);
		
			
			
			this.panel.repaint();
		}
		
		
		
	}
	
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public static int get_draw_x()
	{
		if(for_draw_x!=0)
		{
			return for_draw_x;
		}
		return 0;
	}
	public static int get_draw_y()
	{
		if(for_draw_y!=0)
		{
			return for_draw_y;
		}
		return 0;
	}
	public static int get_logic()
	{
		if(for_save_logic!=0)
		{
			return for_save_logic;
		}
		return 0;
	}
	public static int get_color()
	{
		if(for_save_color!=0)
		{
			return for_save_color;
		}
		return 0;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("PLAY AGAIN"))
		{
			figurs_v1.clear();
			button.deleteframe();
			Sound.clip.stop();
			panel.repaint();
		button a=new button();
		
		
		
		}
		if(e.getSource().equals(on_off))
		{
			if(on_off.isSelected())
			{
				on_off.setText("OFF");
				on_off.setForeground(Color.red);
				sd.pause();
			}
			else
			{
				on_off.setText("ON");
				on_off.setForeground(Color.green.darker().darker());
				sd.resumemusic();
			}
		}
		if(e.getActionCommand().equals("Create Server"))
		{
			createdialog();
		}
		if(e.getActionCommand().equals("CREATE"))
		{
			createserver();
		}
		if(e.getActionCommand().equals("Connect to Server"))
		{
			createclient();
		}
		
	}


	public static void write_inf_to_client_from_server()
	{
		figurs new_figur=getCurrent_figur();
	
		if(new_figur!=null)
		{
		
			try {
				dout_server.writeInt(new_figur.get_number());
				dout_server.writeInt(new_figur.getX());
				dout_server.writeInt(new_figur.getY());
				boolean implement=true;
				dout_server.writeBoolean(implement);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	
	}
	public static void take_inf_from_server()
	{
		 try {
			 if(dinput_client.available()!=0)
			 {
				 boolean implement=false;
				 
				int number=dinput_client.read();
			figurs sohr_figur = null;
			boolean continue_search=false;
				for(int i=figurs_v1.size()-1;i>=0;i--)
	    		{
	         	if(figurs_v1.get(i).get_number()==number)
	         	{
	         		figurs_v1.get(i).setX(dinput_client.readInt());
	         		figurs_v1.get(i).setY(dinput_client.readInt());
	         		sohr_figur=figurs_v1.get(i);
	         		implement=dinput_client.readBoolean();
	         		continue_search=true;
	         		break;
	         	}
	    		}
               if(implement==true)
               {
            	   cnt++;
               }
				if(continue_search==true)
				{		
				for(int i=figurs_v1.size()-1;i>=0;i--)
				{
					if(figurs_v1.get(i).getX()==sohr_figur.getX() && figurs_v1.get(i).getY()==sohr_figur.getY()
							&& figurs_v1.get(i).getType()!=sohr_figur.getType()
							)
					{
						figurs_v1.remove(i);
						break;
					}
				}
		       }
				
		   panel.repaint();
		 }
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	}
	public static void write_inf_to_server_from_client()
	{
		figurs new_figur=getCurrent_figur();
		
		if(new_figur!=null)
		{
			
			try {
				boolean implement=true;
				dout_client.writeInt(new_figur.get_number());
				dout_client.writeInt(new_figur.getX());
				dout_client.writeInt(new_figur.getY());
				dout_client.writeBoolean(implement);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	public static void take_inf_from_client()
	{
	    try {
	        if(dinput_server.available()!=0)
	        {
	        	boolean implement=false;
			int number=dinput_server.readInt();
			figurs sohr_figur = null;
			boolean continue_search=false;
			for(int i=figurs_v1.size()-1;i>=0;i--)
    		{
         	if(figurs_v1.get(i).get_number()==number)
         	{
         		figurs_v1.get(i).setX(dinput_server.readInt());
         		figurs_v1.get(i).setY(dinput_server.readInt());
         		implement=dinput_server.readBoolean();
         		sohr_figur=figurs_v1.get(i);
         		continue_search=true;
         		break;
         	}
    		}
			if(implement==true)
			{
				cnt++;
			}
			if(continue_search==true)
			{		
			for(int i=figurs_v1.size()-1;i>=0;i--)
			{
				if(figurs_v1.get(i).getX()==sohr_figur.getX() && figurs_v1.get(i).getY()==sohr_figur.getY()
						&& figurs_v1.get(i).getType()!=sohr_figur.getType()
						)
				{
					figurs_v1.remove(i);
					break;
				}
			}
	       }
			
			
	    	panel.repaint();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    		
	}
private void createclient() {
		
		
			try{
				String g=JOptionPane.showInputDialog("Write port to connect:  ");
				
				
				int port_of_server=Integer.parseInt(g);
				String address = JOptionPane.showInputDialog("Write IP of server: ");
				client=new Socket(address,port_of_server);
		
				dinput_client = new DataInputStream(client.getInputStream());
				dout_client = new DataOutputStream(client.getOutputStream());
				
				is_client_exist=true;
				String name=JOptionPane.showInputDialog("Write your name: ");
				JOptionPane.showMessageDialog(panel, "You play for white ");
				dout_client.writeUTF(name);
				
				title_client=true;
				if(client.isConnected())
				{
					
					 new_client=new client();
					new_client.start();
					new_client.setDaemon(true);
					check_king_client=new king_is_alive_client();
					check_king_client.start();
					check_king_client.setDaemon(true);
				}
            
				
			}
			catch(Exception e)
			{
				
			}
		
		
		
	}
	private void createserver()   {
		String port;
		boolean successful=true;
		
		 port=field.getText();
		
		 int correctport=Integer.parseInt(port);
		 dialog2.setVisible(true);
		 if(port.length()<4 || correctport<1025 || port.length()>5 || correctport>65535)
		 {
			 
			// JOptionPane.showMessageDialog(null,"Invalid port","Try again", JOptionPane.ERROR_MESSAGE,null);
			 //JOptionPane.showMessageDialog(null, "   Client is loose!\n Press 'Ok' button to play again","Try again",JOptionPane.ERROR_MESSAGE);
			 JOptionPane.showMessageDialog(dialog, "<html><font color=red>Invalid port</font></html>\nTry Again","Try again",JOptionPane.ERROR_MESSAGE);
			 port=null;
			 field.setText("");
			 successful=false;
		 }
		
		if(successful)
		{
			
			dialog.dispose();
			
		}
		
		if(port!=null  )
		{
			
			
			int new_port=Integer.parseInt(port);
			
			
			try
			{
				
				socketserver=new ServerSocket(new_port);
				server=socketserver.accept();
				
				
				dialog2.dispose();
			
				 is_server_exist = true;
				
				 dinput_server=new DataInputStream(server.getInputStream());
				 dout_server=new DataOutputStream(server.getOutputStream());
				
			
				 JOptionPane.showMessageDialog(this.panel, dinput_server.readUTF()+" Connected"+ server.getRemoteSocketAddress());
				 JOptionPane.showMessageDialog(panel, "You play for black ");
				 title_server=true;
				if(server.isConnected())
				{
					
					new_soccet=new soccet();
					new_soccet.start();
					new_soccet.setDaemon(true);
					check_king_server=new king_is_alive_socket();
					check_king_server.start();
					check_king_server.setDaemon(true);
				}
				
				
				 
			}
			catch(IOException  e)
			{
				
			}
			
		}
		
	}
	private void createdialog() {
		dialog.setTitle("CREATION OF PORT");
		 dialog.setSize(400, 100);
		    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    dialog.setLocationRelativeTo(null);
		    dialog.setVisible(true);
			//dialog.setTitle("Zapis'");
			dialog.getContentPane().setLayout(null);
			field.setColumns(10);
			field.setBounds(120, 11, 152, 28);
			lab2.setBounds(42, 11, 118, 28);
			 JButton add=new JButton("CREATE");
			 JLabel support=new JLabel("(Ex. 6666,4567,7654)");
			 support.setOpaque(true);
			 support.setForeground(Color.green.darker());
			 support.setBounds(120, 35, 118, 28);
			add.addActionListener(this);
			dialog.add(field);
			dialog.add(lab2);
			dialog.add(support);
			add.setBounds(284, 11, 89, 28);
			dialog.add(add);
		
	}
	public static boolean getter_for_title_client()
	{
		return title_client;
	}
	public static boolean getter_for_title_server()
	{
		return title_server;
	}
	
	public static   figurs getCurrent_figur() {
		return current_figur;
	}
	public static void setCurrent_figur(figurs fig) {
		
		current_figur = fig;
	}
	public static void check_king_client()
	{
		if(destroy_client==false)
		{
		boolean check_black_king=false;
		boolean check_white_king=false;
		for(int i=figurs_v1.size()-1;i>=0;i--)
		{
			if(
				figurs_v1.get(i).getType()==blackcolor && figurs_v1.get(i).getLogic()==KING
					)
			{
				check_black_king=true;
			}
			
		}
		for(int i=figurs_v1.size()-1;i>=0;i--)
		{
			if(
				figurs_v1.get(i).getType()==whitecolor && figurs_v1.get(i).getLogic()==KING
					)
			{
				check_white_king=true;
			}
			
		}
		if(check_black_king==false  )
		{
			JOptionPane.showMessageDialog(panel, "WHITE WIN");
			checkforwin=true;
			
			destroy_client=true;
			
			
		}
		
		 if(check_white_king==false  )
		{
			JOptionPane.showMessageDialog(panel, "BLACK WIN");
			
			checkforwin=true;
			
			destroy_client=true;
		}
		}
		
	}
	public static void check_king_server()
	{
		if(destroy_soccet==false)
		{
		boolean check_black_king=false;
		boolean check_white_king=false;
		for(int i=figurs_v1.size()-1;i>=0;i--)
		{
			if(
				figurs_v1.get(i).getType()==blackcolor && figurs_v1.get(i).getLogic()==KING
					)
			{
				check_black_king=true;
			}
			
		}
		for(int i=figurs_v1.size()-1;i>=0;i--)
		{
			if(
				figurs_v1.get(i).getType()==whitecolor && figurs_v1.get(i).getLogic()==KING
					)
			{
				check_white_king=true;
			}
			
		}
		if(check_black_king==false  )
		{
			
			JOptionPane.showMessageDialog(panel, "WHITE WIN");
			checkforwin=true;
			
			destroy_soccet=true;
			panel.repaint();
			
		}
		
		
		 if(check_white_king==false  )
			{
				JOptionPane.showMessageDialog(panel, "BLACK WIN");
				
				checkforwin=true;
				
				destroy_soccet=true;
				panel.repaint();
			}
		}
	}
public static boolean get_win_value()
{
	return checkforwin;
}


}
