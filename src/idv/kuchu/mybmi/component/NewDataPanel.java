package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import idv.kuchu.mybmi.MainScreen;
import idv.kuchu.mybmi.data.DataManager;

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
		JLabel tipHeight = new JLabel("(單位:cm)");
		tipHeight.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tipHeight.setBounds(100 + X, 64 + Y, 300, 26);
		JLabel labelWeight = new JLabel("體重");
		labelWeight.setFont(font);
		labelWeight.setBounds(X, 100 + Y, 64, 64);
		JTextField textboxWeight = new JTextField();
		textboxWeight.setFont(font);
		textboxWeight.setBounds(100 + X, 100 + Y, 300, 64);
		JLabel tipWeight = new JLabel("(單位:kg)");
		tipWeight.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tipWeight.setBounds(100 + X, 164 + Y, 300, 26);

		JLabel labelDate = new JLabel("日期");
		labelDate.setFont(font);
		labelDate.setBounds(X, 200 + Y, 64, 64);
		JTextField textboxDate = new JTextField();
		textboxDate.setFont(font);
		textboxDate.setBounds(100 + X, 200 + Y, 300, 64);
		JLabel tipDate = new JLabel("格式:年/月/日         ex:1998/08/21");
		tipDate.setFont(new Font("微軟正黑體", Font.BOLD, 16));
		tipDate.setBounds(100 + X, 264 + Y, 300, 26);

		JButton back = new JButton("取消");
		back.setFont(font);
		back.setBounds(BX + 116, BY, 200, 64);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
			}

		});
		JButton add = new JButton("新增");
		add.setFont(font);
		add.setBounds(BX - 116, BY, 200, 64);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float height = 0;
				try {
					height = Float.valueOf(textboxHeight.getText());
				} catch (NumberFormatException exception) {
				}
				if (height <= 0) {
					JOptionPane.showMessageDialog(null, "請輸入正確數值的身高");
					return;
				}
				float weight = 0;
				try {
					weight = Float.valueOf(textboxWeight.getText());
				} catch (NumberFormatException exception) {
				}
				if (weight <= 0) {
					JOptionPane.showMessageDialog(null, "請輸入正確數值的體重");
					return;
				}
				{
					// 日期檢測
					String Date = textboxDate.getText();
					if(Date.length()<1){
						JOptionPane.showMessageDialog(null, "日期格式不正確");
						return;
					}
					String[] Dates;
					Dates = Date.split("/");
					if(Dates.length!=3)
						Dates = Date.split("\\\\");
					if(Dates.length!=3)
						Dates = Date.split("-");
					if(Dates.length!=3)
						Dates = Date.split(" ");
					if(Dates.length!=3){
						JOptionPane.showMessageDialog(null, "日期格式不正確");
						return;
					}
					int[] cday = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
					int year = 1900;
					try {
						year = Integer.valueOf(Dates[0]);
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(null, "日期格式不正確");
						return;
					}
					if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
						cday[1] = 29;
					}
					int month = 1;
					try {
						month = Integer.valueOf(Dates[1]);
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(null, "日期格式不正確");
						return;
					}
					int day = 1;
					try {
						day = Integer.valueOf(Dates[2]);
					} catch (NumberFormatException exception) {
						JOptionPane.showMessageDialog(null, "日期格式不正確");
						return;
					}
					if(month<1||month>12){
						JOptionPane.showMessageDialog(null, "月份數值不正確");
						return;
					}
					if(day<1||day>cday[month-1]){
						if(month==2&&day==29){
							JOptionPane.showMessageDialog(null, "不是潤年的年份");
						}else{
							JOptionPane.showMessageDialog(null, "日期數值不正確");
						}
						return;
					}
				}
				JSONObject user = DataManager.instance.getUserData();
				try {
					MainScreen.getInstance()
							.addF(new DataAnalyzePanel(user.getInt("gender"), 2017 - user.getInt("year"), height, weight));
				} catch (JSONException exception) {
					JOptionPane.showMessageDialog(null, exception.toString());
					return;
				}
			}

		});

		this.add(labelHeight);
		this.add(tipHeight);
		this.add(textboxHeight);

		this.add(labelWeight);
		this.add(tipWeight);
		this.add(textboxWeight);

		this.add(labelDate);
		this.add(tipDate);

		this.add(textboxDate);
		this.add(back);
		this.add(add);

	}

}
