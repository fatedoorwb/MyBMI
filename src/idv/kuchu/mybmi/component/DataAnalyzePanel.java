package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import idv.kuchu.mybmi.MainScreen;

public class DataAnalyzePanel extends Panel {

	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;

	public DataAnalyzePanel(int gender, int age, float height, float weight) {
		super();
		MainScreen.log("Load DataAnalyze Panel...");
		JLabel labelBMI = new JLabel("BMI");
		labelBMI.setFont(font);
		labelBMI.setBounds(X, Y, 164, 64);

		JTextField showBMI = new JTextField(String.valueOf(DataAnalyzePanel.BMI(height, weight)));// 計算
		showBMI.setFont(font);
		showBMI.setEditable(false);
		showBMI.setBounds(200 + X, Y, 200, 64);

		JLabel labelBodyFat = new JLabel("體脂");
		labelBodyFat.setFont(font);
		labelBodyFat.setBounds(X, 100 + Y, 164, 64);
		JTextField showBodyFat = new JTextField(String.valueOf(DataAnalyzePanel.BF(gender, age, height, weight)));// 計算
		showBodyFat.setFont(font);
		showBodyFat.setEditable(false);
		showBodyFat.setBounds(200 + X, 100 + Y, 200, 64);

		JLabel labelIdealweight = new JLabel("理想體重");
		labelIdealweight.setFont(font);
		labelIdealweight.setBounds(X, 200 + Y, 164, 64);

		float v2 = 0;
		if (gender == 1) {
			v2 = (height - 80) * 0.7f;
		} else if (gender == 2) {
			v2 = (height - 70) * 0.6f;
		}

		JTextField showIdealweight = new JTextField(String.valueOf(((int) (v2 * 100)) / 100f));// 計算
		showIdealweight.setFont(font);
		showIdealweight.setEditable(false);
		showIdealweight.setBounds(200 + X, 200 + Y, 200, 64);

		JButton back = new JButton("返回");
		back.setFont(font);
		back.setBounds(BX, BY, 200, 64);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
			}

		});

		this.add(labelBMI);
		this.add(showBMI);

		this.add(labelBodyFat);
		this.add(showBodyFat);

		this.add(labelIdealweight);
		this.add(showIdealweight);

		this.add(back);
		MainScreen.log("Load DataAnalyze Panel Finish.");
	}

	public static float BMI(float height, float weight) {
		return ((int) ((weight / ((height / 100) * (height / 100))) * 100)) / 100f;
	}

	public static float BF(float gender, float age, float height, float weight) {
		return ((int) ((1.2 * DataAnalyzePanel.BMI(height, weight) + 0.23 * age - 5.4 - 10.8 * gender) * 100)) / 100f;
	}

}
