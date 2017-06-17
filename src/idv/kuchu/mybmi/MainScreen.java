package idv.kuchu.mybmi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

public class MainScreen extends JFrame {

	public static InputStream getPath(String path) throws Exception {
		return MainScreen.class.getResourceAsStream(path);
	}

	public static String getCurrentDirectory() {
		File now_directory = new File(".");
		String current_path = now_directory.getAbsolutePath().replaceAll("\\.", "");
		return current_path;
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

	public static final int SCREEN_W = 800, SCREEN_H = 600;
	private boolean run = true;

	private List<JFrame> frames = new ArrayList<JFrame>();

	private void addF(JFrame frame) {
		frames.add(frame);
	}

	private JFrame getF() {
		return this.frames.size() > 0 ? this.frames.remove(this.frames.size() - 1) : null;
	}

	private JFrame disposeF() {
		if(this.frames.size() > 0){
			this.frames.get(this.frames.size() - 1).dispose();
			return this.frames.remove(this.frames.size() - 1);
		}
		return  null;
	}

	public MainScreen() {
		super();
		this.setTitle("體位記錄小幫手");
		this.setBounds(0, 0, MainScreen.SCREEN_W, MainScreen.SCREEN_H);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		new Thread(new SystemUpdate()).start();
	}

	BufferedImage screen = new BufferedImage(SCREEN_W, SCREEN_H, BufferedImage.TYPE_3BYTE_BGR);

	public void paint(Graphics g) {
		Graphics2D screen_g2d = (Graphics2D) screen.getGraphics();
		screen_g2d.setColor(Color.white);
		screen_g2d.fillRect(0, 0, SCREEN_W, SCREEN_H);
		if(getF()!=null){
			getF().paint(screen_g2d);
		}else{
			super.paint(screen_g2d);
		}
		g.drawImage(screen, 0, 0, null);
	}

	class SystemUpdate implements Runnable {
		public void run() {
			while (run) {
				try {
					Thread.sleep(50);
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
