import java.applet.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Audio extends JApplet{
	private  AudioClip sound1, sound2, currentSound;
	private  JButton playJButton, loopJButton, stopJButton;
	private  JComboBox soundJComboBox;
	
	public  void main(String[] args){
		init();
	}
	
	public  void init(){
		setLayout(new FlowLayout());
		
		String choices[] = {"1","2"};
		soundJComboBox = new JComboBox(choices);
		
		soundJComboBox.addItemListener(
				new ItemListener(){
					public void itemStateChanged(ItemEvent e){
						currentSound.stop();
						currentSound = soundJComboBox.getSelectedIndex() == 0 ?
								sound1:sound2;
					}
				}
		);
		
		add(soundJComboBox);
		
		ButtonHandler handler = new ButtonHandler();
		
		playJButton = new JButton("Play");
		playJButton.addActionListener(handler);
		add(playJButton);
		
		loopJButton = new JButton("Loop");
		loopJButton.addActionListener(handler);
		add(loopJButton);
		
		stopJButton = new JButton("Stop");
		stopJButton.addActionListener(handler);
		add(stopJButton);
		System.out.println(getDocumentBase());
		sound1 = getAudioClip(getDocumentBase(),"1.wav");
		sound2 = getAudioClip(getDocumentBase(),"2.wav");
		currentSound = sound1;		
	}	//end method init
	
	//stop the sound when the user switches Web pages
	
	public void stop(){
		currentSound.stop();
	}
	
	private class ButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent actionEvent){
			if(actionEvent.getSource() == playJButton)
				currentSound.play();
			else if(actionEvent.getSource() == loopJButton)
				currentSound.loop();
			else if(actionEvent.getSource() == stopJButton)
				currentSound.stop();
		}
	}
}
