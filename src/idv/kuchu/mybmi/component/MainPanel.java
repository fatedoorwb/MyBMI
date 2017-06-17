package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import idv.kuchu.mybmi.MainScreen;

public class MainPanel extends Panel {
	
	Font font = new Font("微軟正黑體", Font.BOLD, 28);
	
	int BX = ((MainScreen.SCREEN_W - 16) - 432) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 200) - 20;
	
	public MainPanel(){
		super();
		MainScreen.log("Load Main Panel...");
		JButton BTL = new JButton("新增資料");
		BTL.setFont(font);
		BTL.setBounds(BX, BY-228, 200, 200);
		BTL.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().addF(new NewDataPanel());
			}
			
		});
		JButton BTR = new JButton("整體分析");
		BTR.setFont(font);
		BTR.setBounds(BX+232, BY-228, 200, 200);
		JButton BBL = new JButton("尚無功能");
		BBL.setFont(font);
		BBL.setBounds(BX, BY, 200, 200);
		BBL.setEnabled(false);
		JButton BBR = new JButton("關於我們");
		BBR.setFont(font);
		BBR.setBounds(BX+232, BY, 200, 200);
		BBR.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().addF(new AboutPanel());
			}
			
		});
		this.add(BTL);
		this.add(BTR);
		this.add(BBL);
		this.add(BBR);
		MainScreen.log("Load Main Panel Finish.");
	}

}
