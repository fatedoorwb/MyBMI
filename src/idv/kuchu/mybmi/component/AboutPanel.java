package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;

import idv.kuchu.mybmi.MainScreen;

public class AboutPanel extends Panel {

	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 400) / 2 - 8;
	int Y = 32;
	
	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;
	
	public AboutPanel(){
		super();
		MainScreen.log("Load About Panel...");
		List<String> member = new ArrayList<String>();
		member.add("10515540"+"        "+"梨水竹");
		member.add("10515500"+"        "+"竹水梨");
		member.add("10515527"+"        "+"夜韹蝶");
		member.add("10515501"+"        "+"雨夏慧");
		for(int i=0;i<member.size()&&i<4;i++){
			JLabel label = new JLabel(member.get(i));
			label.setFont(font);
			label.setBounds(X+60, Y+80*i, 280, 64);
			this.add(label);
		}
		
		JButton close = new JButton("回上頁");
		close.setFont(font);
		close.setBounds(BX, BY, 200, 64);
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
			}
			
		});
		this.add(close);
		MainScreen.log("Load About Panel Finish.");
	}

}
