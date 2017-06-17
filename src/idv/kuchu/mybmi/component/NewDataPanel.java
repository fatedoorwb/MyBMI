package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JTextField;

import idv.kuchu.mybmi.MainScreen;

public class NewDataPanel extends Panel{

	Font font = new Font("微軟正黑體", Font.BOLD, 28);


	int X = ((MainScreen.SCREEN_W-16)-400)/2-8;
	int Y = 32;
	
	int BX = ((MainScreen.SCREEN_W-16)-200)/2-8;
	int BY = ((MainScreen.SCREEN_H-38)-64)-50;

	public NewDataPanel() {
		
		JLabel  lableHeight = new JLabel ("身高");
		lableHeight.setFont(font);
		lableHeight.setBounds(X, Y, 64, 64);
		JTextField  textboxHeight = new JTextField();
		textboxHeight.setBounds(100+X, 100+Y, 300, 64);
		textboxHeight.setFont(font);
		
		JLabel  lableWeight = new JLabel ("體重");
		lableWeight.setFont(font);
		lableWeight.setBounds(X, Y, 64, 64);
		JTextField  textboxWeight = new JTextField();
		textboxWeight.setBounds(100+X, 100+Y, 300, 64);
		textboxWeight.setFont(font);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		this.add(lableHeight);
		this.add(lableWeight);

		
		
		
		

		
	}
	
	
	
	
	
	
	
}
