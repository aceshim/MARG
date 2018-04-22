import java.applet.*;
import java.net.*;

public class Main{
	public static void main(String[] args){
		try{
			AudioClip clip = Applet.newAudioClip(new 
			URL("file:///Users/jaehoonshim/Desktop/ex.wav"));
			
			clip.play();
		}catch (MalformedURLException murle){
			System.out.println(murle);
		}
	}
}