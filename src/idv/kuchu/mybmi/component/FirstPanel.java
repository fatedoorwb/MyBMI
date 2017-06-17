package idv.kuchu.mybmi.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

import idv.kuchu.mybmi.Main;
import idv.kuchu.mybmi.MainScreen;
import idv.kuchu.mybmi.data.DataManager;

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
		JLabel  lableGender = new JLabel ("性別");
		lableGender.setFont(font);
		lableGender.setBounds(X, Y, 64, 64);
		String[] chooseGender = new String[]{"請選擇","男","女"};
		JComboBox<String> selectGender = new JComboBox<String>(chooseGender); 
		selectGender.setFont(font);
		selectGender.setBounds(100+X, Y, 300, 64);
		
		JLabel  lableName = new JLabel("姓名");
		lableName.setFont(font);
		lableName.setBounds(X, 100+Y, 64, 64);
		JTextField  textboxName = new JTextField();
		textboxName.setBounds(100+X, 100+Y, 300, 64);
		textboxName.setFont(font);
		
		JLabel  lableBirthday = new JLabel ("生年");
		lableBirthday.setFont(font);
		lableBirthday.setBounds(X, 200+Y, 64, 64);
		JTextField textboxBirthdayY = new JTextField(); 
		textboxBirthdayY.setFont(font);
		textboxBirthdayY.setBounds(100+X, 200+Y, 300, 64);
		
		JButton finish = new JButton("完成");
		finish.setFont(font);
		finish.setBounds(BX, BY, 200, 64);
		finish.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectGender.getItemAt(selectGender.getSelectedIndex()).equals(chooseGender[0])){
					JOptionPane.showMessageDialog(null, "請選擇性別");
					return;
				}
				String Name = textboxName.getText();
				int Gender = 0;
				if(selectGender.getItemAt(selectGender.getSelectedIndex()).equals(chooseGender[1]))
					Gender = 1;
				if(selectGender.getItemAt(selectGender.getSelectedIndex()).equals(chooseGender[2]))
					Gender = 2;
				int year = 1900;
				try{
					year = Integer.valueOf(textboxBirthdayY.getText());
				}catch(NumberFormatException exception){
					JOptionPane.showMessageDialog(null, "請輸入正確的出生年份");
					return;
				}
				JSONObject object = new JSONObject();
				try {
					object.put("name", Name);
					object.put("gender", Name);
					object.put("year", year);
					DataManager.instance.wirte(new File(MainScreen.getCurrentFile(), "data/user.json"),object);
					JOptionPane.showMessageDialog(null, "初始資料已記錄，歡迎使用本系統");
					MainScreen.getInstance().disposeF();
					//MainScreen.getInstance().addF(new FirstPanel());
				} catch (JSONException exception) {
					JOptionPane.showMessageDialog(null, exception.toString());
					return;
				}
			}
			
		});
		//-----
		this.add(lableGender);
		this.add(selectGender);
		this.add(lableName);
		this.add(textboxName);
		this.add(lableBirthday);
		this.add(textboxBirthdayY);
		
		this.add(finish);
		MainScreen.log("Load First Panel Finish.");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}

}
