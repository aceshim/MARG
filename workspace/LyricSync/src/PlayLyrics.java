import java.io.*;
import java.util.*;

public class PlayLyrics extends Thread implements Runnable{
	private static Path path = new Path();
	private static String LYRIC = path.getLyricFile();
	private static String SLEEPTIME = path.getTimeFile();
	private static ArrayList<String> lyrics = new ArrayList<String>();
	private static ArrayList<Float> times = new ArrayList<Float>();
	Thread t;
	
	PlayLyrics(){
		t = new Thread(this, "Play Lyrics");
		t.start();	
	}
	
	public void run() {
		try{
			System.out.println("getting Lyrics");
			File file = new File(LYRIC);
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(file));
			
			String line=null;
			while((line = reader.readLine()) != null){
				lyrics.add(line);
			}
			reader.close();
			String[] lyric = Arrays.asList(lyrics.toArray()).toArray(new String[lyrics.toArray().length]);

			File file2 = new File(SLEEPTIME);
			BufferedReader reader2;
			reader2 = new BufferedReader(new FileReader(file2));
			while((line = reader2.readLine()) != null){
				times.add(Float.parseFloat(line));
			}
			reader2.close();
			Float[] time = Arrays.asList(times.toArray()).toArray(new Float[times.toArray().length]);
			//parseTime();
			System.out.println();
			
			float[] sleeptime = new float[time.length];
			sleeptime[0]=time[0];
			for(int i=1;i<lyric.length;i++){
				sleeptime[i]=time[i]-time[i-1];
			}
			
			for(int i = 0;i<lyric.length;i++){
				if(lyric[i]!=null&&sleeptime[i]>0){
					//System.out.println(sleeptime[i]);
					Thread.sleep((long)(1000*sleeptime[i]));
					System.out.println(lyric[i]);
					}
				else break;
			}
			System.out.println();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}