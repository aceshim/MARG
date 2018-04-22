import java.util.*;

public class Main{
	
	public static void main(String[] args) throws Exception{
		//Runtime.getRuntime().exec("clear");
		Path path = new Path();

		path.setFolderPath(System.getProperty("user.dir"));
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Audio File Name: ");
		path.setAudioFile(path.getFolderPath()+"/"+sc.nextLine()+".wav");
		//System.out.println("Enter Lyric File Name: ");
		path.setLyricFile(path.getAudioFile().replaceAll("wav", "txt"));
		path.setTimeFile(path.getLyricFile().replaceAll(".txt","time.txt"));
		
		System.out.println("======Music Start======");
		new Audio();
		new PlayLyrics();
		
		
	}
}
