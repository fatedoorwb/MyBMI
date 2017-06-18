package idv.kuchu.mybmi.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import idv.kuchu.mybmi.MainScreen;

public class AllDataAnalyzePanel extends Panel {
	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;

	public AllDataAnalyzePanel() {

		JButton BMI = new JButton("BMI");
		BMI.setFont(font);
		BMI.setBounds(BX - 116, Y, 200, 64);

		JButton BodyFat = new JButton("體脂");
		BodyFat.setFont(font);
		BodyFat.setBounds(BX + 116, Y, 200, 64);

		// 圖

		JButton back = new JButton("返回");
		back.setFont(font);
		back.setBounds(BX, BY, 200, 64);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
			}

		});

		this.add(BMI);
		this.add(BodyFat);

		this.add(back);

	}

}
