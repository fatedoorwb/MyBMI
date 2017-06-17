package idv.kuchu.mybmi.component;

<<<<<<< HEAD
public class AllDataAnalyzePanel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
=======
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JButton;

import idv.kuchu.mybmi.MainScreen;

public class AllDataAnalyzePanel extends Panel{
	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;
	
	public AllDataAnalyzePanel(){
		
		JButton BMI = new JButton("BMI");
		BMI.setFont(font);
		BMI.setBounds(X, Y, 64, 64);
		
		JButton BodyFat = new JButton("體脂");
		BodyFat.setFont(font);
		BodyFat.setBounds(100 + X, Y, 64, 64);

		
		
		
		//圖
		
		
		
		
		JButton back = new JButton("返回");
		back.setFont(font);
		back.setBounds(BX, BY, 200, 64);
		
		
		
		
		
		
		this.add(BMI);
		this.add(BodyFat);

		this.add(back);
>>>>>>> 26f6205b37af65fc242a51f67e9eda5978eb8e1f

	}

}
