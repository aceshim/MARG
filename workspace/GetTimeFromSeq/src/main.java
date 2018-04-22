import java.io.*;
import java.util.*;
/*
 * This program reads lyric file and raw file to make a file with time stamps which indicate 
 * starting time of each lyrics. 
 * 
 * Destination of the lyric file needs to be indicated at the initialization.
 * The raw file needs to have [lyricfile]raw.txt format and to be placed in the same directory where the lyric file is at.
 * 
 * ex) lyric file - YH.txt, raw file - YHraw.txt
 * 
 * 
 * Jaehoon Shim (pensivej@naver.com)
 * Major in Computer Science, University of Illinois at Urbana-Champaign
 * Intern at Music and Audio Research Group (MARG), Seoul National University (Winter 2014)
 * last update 02/26/14
 */
public class main {
	
	private static String LYRIC = "/Users/jaehoonshim/Desktop/YH.txt"; // location of lyric file
	private static String RAW = LYRIC.replaceAll(".txt","raw.txt"); // raw file with time
	private static String OUTPUT = LYRIC.replaceAll(".txt", "time.txt"); // Output file with time stamp
	private static ArrayList<String> raws = new ArrayList<String>(); 
	private static ArrayList<String> lyrics = new ArrayList<String>();
	private static int offset = 0;
	private static int offsetraw = 0;
	private static int count = 0;
	private static int y = 0;
	/* 
	 * for later use
	public static boolean isFirst(int[] seqNu, int i, int j){
		if(j==5 && seqNu[i-j-1]==0)
			return true;
		else if(j==5)
			return false;
		else
			return isFirst(seqNu,i,++j);
	}
	*/
	/*
	 * This determines whether it has at least 5 (for fast music) or 10 (for slow music)
	 */
	public static boolean hasZeros(int[] seqNu, int i,int j){ //initial value of j always needs to be 0
		if(j==5 && seqNu[i-j-1]==0)
			return true;
		else if(j==5)
			return false;
		else
			return hasZeros(seqNu,i,++j);
	}
	/*
	 * This prints time appending to the output file
	 */
	public static void printTime(float[]time, int i) throws IOException{
		System.out.println(time[i]);
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT,true)));
		out.println(time[i]);
		out.close();
	}
	/*
	 * This function finds the first node 
	 */
	public static void findFirst(float[]time, int[] seqNu, int[]lyricNu) throws IOException{
		int x = 0;
		while(seqNu[x]!=1){
			x++;
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT)));
		out.print("");
		out.close();
		printTime(time,x);
		y=0;
	}
	/*
	 * This function changes offset using Breadth-first search algorithm
	 */
	public static void changeOffset(){
		count++;
		if(count%2==0){
			offset += count;
		}else{
			offset -= count;
		}
		
	}
	/*
	 * This function finds and return appropriate frame number with wanted sequence number. 
	 * This contains hasZeros and changeOffset algorithm.
	 */
	public static int findSeqNu(int[]seqNu, int[]lyricNu, int cnt){
		int x = 0;
		System.out.println(offset);
		offsetraw = offset;
		while(seqNu[x]!=offset){
			x++;
		//return x;
		}
		x++;
		y=x;
		while(seqNu[y]==offset||seqNu[y]==0){
			if(hasZeros(seqNu,y,0)&&seqNu[y]!=0){
				break;
			}else{
				y++;
			}
		}
		if(hasZeros(seqNu,y,0)){
			count=0;
			return y;
		}
		else{
			if(count<6){
				changeOffset();
				return findSeqNu(seqNu,lyricNu,cnt);
			}else
				return findSeqNu2(seqNu,lyricNu,cnt);
		}
	}
	/*
	 * This function finds the first frame with wanted sequence number.
	 * No other algorithms included.
	 */
	public static int findSeqNu2(int[]seqNu, int[]lyricNu, int cnt){
		int x = 0;
		System.out.println(offsetraw);
		while(seqNu[x]!=offsetraw){
			x++;
		}
		x++;
		return x;
	}
	/*
	 * This function calls findSeqNu and gets appropriate time. Then it calls printTime to print time.
	 */
	public static void getTime(float[] time, int[] seqNu, int[] lyricNu) throws IOException{
		findFirst(time,seqNu,lyricNu);
		for(int cnt = 1 ; cnt<lyricNu.length; cnt++){
			offset += lyricNu[cnt-1];
			int x = findSeqNu(seqNu,lyricNu,cnt);
			printTime(time,x);
		}
		printTime(time,time.length-1);
	}
	/*
	 * This function performs parsing from String to time, vowel, and sequence number.
	 * Then, it calls getTime()
	 */
	public static void parsing(String[] raw, int[] lyricNu) throws IOException{
		int cnt = 0;
		String tmp = null;
		final float[] time = new float[raw.length];
		final int[] vowel = new int[raw.length];
		final int[] seqNu = new int[raw.length];
		System.out.println("Parsing Starts");
		while(cnt<raw.length){
			tmp=raw[cnt];
			String delims = "[	]+";
			String[] tokens = tmp.split(delims);
			int i = 0;
			time[cnt] = Float.parseFloat(tokens[i]);
			i++;
			vowel[cnt] = Integer.parseInt(tokens[i]);
			i++;
			seqNu[cnt] = Integer.parseInt(tokens[i]);
			cnt++;
		}
		System.out.println("Parsing Done");
		
		getTime(time,seqNu,lyricNu);
	}
	public static void main(String[] args){
		try{
			// Imports raw file and puts into raws(ArrayList)
			File file = new File(RAW);
			BufferedReader reader;
			reader = new BufferedReader(new FileReader(file));
			
			String line = null;
			while((line = reader.readLine())!=null){
				raws.add(line);
			}
			reader.close();
			
			// Converts raws(ArrayList) to raw(String array)
			String[] raw = Arrays.asList(raws.toArray()).toArray
					(new String[raws.toArray().length]);
			
			// Imports lyric file and puts into lyrics(ArrayList)
			File file2 = new File(LYRIC);
			BufferedReader reader2;
			reader2 = new BufferedReader(new FileReader(file2));
			
			while((line = reader2.readLine())!=null){
				lyrics.add(line);
			}
			reader2.close();
			
			// Converts lyrics(ArrayList) to lyric(String array)
			String[] lyric = Arrays.asList(lyrics.toArray()).toArray
					(new String[lyrics.toArray().length]);
			
			// Makes lyricNu(Integer array) which has number of characters in each lines
			int[] lyricNu = new int[lyric.length];
			
			// Removes all spaces
			for(int i = 0; i<lyric.length;i++){
				lyricNu[i]=0;
				StringTokenizer st = new StringTokenizer(lyric[i]," ");
				while(st.hasMoreTokens()){
					String s = st.nextToken();
					System.out.print(s);
					lyricNu[i]+=s.length();
				}
				System.out.println();
			}
			
			// Parsing Starts
			parsing(raw,lyricNu);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
