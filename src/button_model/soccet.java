package button_model;

import sun.audio.*;
import java.awt.event.*;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class soccet extends Thread  {
	@Override 
	public void run()
	{
		do
		{
			change_place.take_inf_from_client();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		}while(change_place.destroy_soccet==false);
	}
	
}
class king_is_alive_client extends Thread{
	@Override
	public void run()
	{
		do
		{
			change_place.check_king_server();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(change_place.destroy_client==false);
	}
}
class king_is_alive_socket extends Thread{
	@Override
	public void run()
	{
		do
		{
			change_place.check_king_server();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(change_place.destroy_soccet==false);
	}
}

class client extends Thread{
	@Override 
	public void run()
	{
		do{
		change_place.take_inf_from_server();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}while(change_place.destroy_client==false);
		
		
		
	}
}
class Sound extends Thread{
	private  File LOSE =new  File("hs.wav");
	public static Clip clip=null;
	private static int lastFrame=0;
	@Override
	public void run()
	{
		playsound(LOSE);
	}
	 public void pause() {

         if (clip != null && clip.isRunning()) {
              lastFrame = clip.getFramePosition();
            
             clip.stop();
         }

     }
	  public static void  resumemusic() {

          if (clip != null && !clip.isRunning()) {
           
              if (lastFrame < clip.getFrameLength()) {
                  clip.setFramePosition(lastFrame);
              } else {
                  clip.setFramePosition(0);
              }
              
              clip.start();
          }
	  }
	public  void playsound(File sound)
	   {
		while(change_place.get_win_value()==false)
		{
		   try{
			    clip=AudioSystem.getClip();
			   clip.open(AudioSystem.getAudioInputStream(sound));
		       clip.start();
		       Thread.sleep(clip.getMicrosecondLength()/1000);
		   }catch(Exception e)
		   {
			   
		   }
		}
	   }
	
}

