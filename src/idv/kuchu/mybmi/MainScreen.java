package idv.kuchu.mybmi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import idv.kuchu.mybmi.component.FirstPanel;
import idv.kuchu.mybmi.component.MainPanel;
import idv.kuchu.mybmi.component.NewDataPanel;
import idv.kuchu.mybmi.data.DataManager;

public class MainScreen extends JFrame {

	public static InputStream getPath(String path) throws Exception {
		return MainScreen.class.getResourceAsStream(path);
	}

	public static String getCurrentDirectory() {
		File now_directory = new File(".");
		String current_path = now_directory.getAbsolutePath().replaceAll("\\.", "");
		return current_path;
	}

	public static File getCurrentFile() {
		return new File(getCurrentDirectory());
	}

	public static byte[] readStream(String path) throws Exception {
		InputStream inStream = getPath(path);
		ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inStream.read(buffer)) != -1) {
			outSteam.write(buffer, 0, len);
		}
		outSteam.close();
		inStream.close();
		return outSteam.toByteArray();
	}

	public static void log(Object obj) {
		System.out.println("[" + getTime() + "] [INFO] " + obj.toString());
	}

	public static void err(Object obj) {
		System.err.println("[" + getTime() + "] [ERR] " + obj.toString());
	}

	private static String getTime() {
		SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}

	public static MainScreen getInstance() {
		return Main.core;
	}

	public static final int SCREEN_W = 600, SCREEN_H = 500;
	private boolean run = true;

	private List<Panel> panels = new ArrayList<Panel>();

	public void addF(Panel panel) {
		if (panel == null)
			return;
		this.removeFAll();
		panel.setLayout(null);
		this.panels.add(panel);
		this.getContentPane().add(panel);
	}

	private void removeFAll() {
		for (Panel panel : panels) {
			this.getContentPane().remove(panel);
		}
	}

	public Panel getF() {
		return this.panels.size() > 0 ? this.panels.get(this.panels.size() - 1) : null;
	}

	public void disposeF() {
		this.removeFAll();
		if (this.panels.size() > 0) {
			this.panels.remove(getF());
			if (this.panels.size() > 0) {
				this.getContentPane().add(getF());
			}
		}
	}

	public MainScreen() {
		super();
		this.setTitle("體重記錄小幫手");
		this.setBounds(100, 100, MainScreen.SCREEN_W, MainScreen.SCREEN_H);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addF(new MainPanel());
		if (!DataManager.instance.hasUserData()) {
			FirstPanel obj = new FirstPanel();
			this.addF(obj);
		}
		this.setVisible(true);
		new Thread(new SystemUpdate()).start();
	}

	BufferedImage screen = new BufferedImage(SCREEN_W, SCREEN_H, BufferedImage.TYPE_3BYTE_BGR);

	@Override
	public void paint(Graphics g) {
		Graphics2D screen_g2d = (Graphics2D) screen.getGraphics();
		screen_g2d.setColor(Color.white);
		screen_g2d.fillRect(0, 0, SCREEN_W, SCREEN_H);
		super.paint(screen_g2d);
		g.drawImage(screen, 0, 0, null);
	}

	class SystemUpdate implements Runnable {
		@Override
		public void run() {
			while (run && isVisible()) {
				try {
					Thread.sleep(50);
					repaint();
					revalidate();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
