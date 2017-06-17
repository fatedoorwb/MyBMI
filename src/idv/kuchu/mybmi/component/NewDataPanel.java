package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import idv.kuchu.mybmi.MainScreen;

public class NewDataPanel extends Panel {

	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;

	public NewDataPanel() {

		JLabel labelHeight = new JLabel("身高");
		labelHeight.setFont(font);
		labelHeight.setBounds(X, Y, 64, 64);
		JTextField textboxHeight = new JTextField();
		textboxHeight.setFont(font);
		textboxHeight.setBounds(100 + X, Y, 300, 64);

		JLabel labelWeight = new JLabel("體重");
		labelWeight.setFont(font);
		labelWeight.setBounds(X, 100 + Y, 64, 64);
		JTextField textboxWeight = new JTextField();
		textboxWeight.setFont(font);
		textboxWeight.setBounds(100 + X, 100 + Y, 300, 64);

		JLabel labelDate = new JLabel("日期");
		labelDate.setFont(font);
		labelDate.setBounds(X, 200 + Y, 64, 64);
		JTextField textboxDate = new JTextField();
		textboxDate.setFont(font);
		textboxDate.setBounds(100 + X, 200 + Y, 300, 64);

		JLabel tipDate = new JLabel("格式:年/月/日         ex:1998/08/21");
		tipDate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tipDate.setBounds(100 + X, 264 + Y, 300, 16);

		JButton add = new JButton("新增");
		add.setFont(font);
		add.setBounds(BX, BY, 200, 64);

		this.add(labelHeight);
		this.add(textboxHeight);
		this.add(labelWeight);
		this.add(textboxWeight);
		this.add(labelDate);
		this.add(tipDate);
		this.add(textboxDate);
		this.add(add);

	}

}
