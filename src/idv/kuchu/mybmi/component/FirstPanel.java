package idv.kuchu.mybmi.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import idv.kuchu.mybmi.MainScreen;

public class FirstPanel extends Panel {
	
	Font font = new Font("微軟正黑體", Font.BOLD, 28);
	
	int X = ((MainScreen.SCREEN_W-16)-400)/2-8;
	int Y = 32;
	
	int BX = ((MainScreen.SCREEN_W-16)-200)/2-8;
	int BY = ((MainScreen.SCREEN_H-38)-64)-50;

	public FirstPanel() {
		super();
		MainScreen.log("Load First Panel...");
		//-----
		JLabel  lableName = new JLabel("姓名");
		lableName.setFont(font);
		lableName.setBounds(X, Y, 64, 64);
		JTextField  textboxName = new JTextField();
		textboxName.setBounds(100+X, Y, 300, 64);
		textboxName.setFont(font);
		
		JLabel  lableGender = new JLabel ("性別");
		lableGender.setFont(font);
		lableGender.setBounds(X, 100+Y, 64, 64);
		String[] chooseGender = new String[]{"請選擇","男","女"};
		JComboBox<String> selectGender = new JComboBox<String>(chooseGender); 
		selectGender.setFont(font);
		selectGender.setBounds(100+X, 100+Y, 300, 64);
		
		JLabel  lableBirthday = new JLabel ("生日");
		lableBirthday.setFont(font);
		lableBirthday.setBounds(X, 200+Y, 64, 64);
		JTextField textboxBirthdayY = new JTextField(); 
		textboxBirthdayY.setFont(font);
		textboxBirthdayY.setBounds(100+X, 200+Y, 100, 64);
		String[] chooseBirthdayM = new String[]{"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
		JComboBox<String> selectBirthdayM = new JComboBox<String>(chooseBirthdayM); 
		selectBirthdayM.setFont(font);
		selectBirthdayM.setBounds(200+X, 200+Y, 100, 64);
		JTextField textboxBirthdayD = new JTextField(); 
		textboxBirthdayD.setFont(font);
		textboxBirthdayD.setBounds(300+X, 200+Y, 100, 64);
		
		JButton finish = new JButton("完成");
		finish.setFont(font);
		finish.setBounds(BX, BY, 200, 64);
		//-----
		this.add(lableName);
		this.add(textboxName);
		this.add(lableGender);
		this.add(selectGender);
		this.add(lableBirthday);
		this.add(textboxBirthdayY);
		this.add(selectBirthdayM);
		this.add(textboxBirthdayD);
		
		this.add(finish);
		MainScreen.log("Load First Panel Finish.");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
