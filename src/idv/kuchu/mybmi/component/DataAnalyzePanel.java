package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.JLabel;
import javax.swing.JTextField;

import idv.kuchu.mybmi.MainScreen;

public class DataAnalyzePanel extends Panel {
	
	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;
	
	public DataAnalyzePanel(int gender,float height,float weight){
		
		
		JLabel labelBMI = new JLabel("BMI");
		labelBMI.setFont(font);
		labelBMI.setBounds(X, Y, 64, 64);
		JLabel labelshowBMI = new JLabel(String.valueOf(weight/((height/100)*(height/100))));//計算
		labelshowBMI.setFont(font);
		labelshowBMI.setBounds(100 + X, Y, 300, 64);
		
		JLabel labelBodyFat = new JLabel("體脂");
		labelBodyFat.setFont(font);
		labelBodyFat.setBounds(X, 100 + Y, 64, 64);
		JLabel labelshowBodyFat = new JLabel("");//計算
		labelshowBodyFat.setFont(font);
		labelshowBodyFat.setBounds(100 + X, 100 + Y, 300, 64);

		JLabel labelIdealweight = new JLabel("理想體重");
		labelIdealweight.setFont(font);
		labelIdealweight.setBounds(X, 200 + Y, 64, 64);
		JLabel labelshowIdealweight = new JLabel("");//計算
		labelshowIdealweight.setFont(font);
		labelshowIdealweight.setBounds(100 + X, 200 + Y, 300, 64);

		
		this.add(labelBMI);
		this.add(labelshowBMI);
		
		this.add(labelBodyFat);
		this.add(labelshowBodyFat);
		
		this.add(labelIdealweight);
		this.add(labelshowIdealweight);
		

		
		
		
		
		
		
		//1男2女	
	}

}
