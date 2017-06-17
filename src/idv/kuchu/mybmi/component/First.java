package idv.kuchu.mybmi.component;

import javax.swing.JFrame;

import idv.kuchu.mybmi.MainScreen;

public class First extends JFrame{
	
	public First(){
		super();
		this.setTitle("體位記錄小幫手 - First");
		this.setBounds(0, 0, MainScreen.SCREEN_W, MainScreen.SCREEN_H);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
