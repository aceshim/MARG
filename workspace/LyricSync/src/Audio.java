import java.applet.*;
import java.net.*;

public class Audio extends Applet implements Runnable{
	Thread t;
	Audio(){
		t = new Thread(this, "Audio");
		System.out.println("Audio Starts");
		t.start();
	}
	public void run(){
		try{
			Path path = new Path();
			AudioClip clip = newAudioClip(new URL("file://"+path.getAudioFile()));
			
			clip.play();
		}catch (MalformedURLException murle){
			System.out.println(murle);
		}
	}
}
