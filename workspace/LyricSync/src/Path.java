
public class Path {
	
	public Path(){
		
	}
	public String getFolderPath(){
		return folderPath;
	}
	
	public void setFolderPath(String folderPath){
		this.folderPath = folderPath;
	}
	public String getAudioFile(){
		return audioFile;
	}
	
	public void setAudioFile(String audioFile){
		this.audioFile = audioFile;
	}
	
	public String getLyricFile(){
		return lyricFile;
	}
	
	public void setLyricFile(String lyricFile){
		this.lyricFile = lyricFile;
	}
	
	public String getTimeFile(){
		return timeFile;
	}
	
	public void setTimeFile(String timeFile){
		this.timeFile = timeFile;
	}
	
	private	static String folderPath="";
	private static String audioFile="";
	private static String lyricFile="";
	private static String timeFile="";
}
