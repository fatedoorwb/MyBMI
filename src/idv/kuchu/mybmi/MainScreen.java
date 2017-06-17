package idv.kuchu.mybmi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

public class MainScreen extends JFrame{
	
	public static InputStream getPath(String path) throws Exception{
		return MainScreen.class.getResourceAsStream(path);
	}  
	
	public static byte[] readStream(String path) throws Exception{
		InputStream inStream = getPath(path);
	    ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
	    byte[] buffer = new byte[1024];  
	    int len = -1;  
	    while((len = inStream.read(buffer)) != -1){  
	        outSteam.write(buffer,0,len);  
	    }  
	    outSteam.close();  
	    inStream.close();  
	    return outSteam.toByteArray();  
	}
	
	public static void log(Object obj){
		System.out.println("["+getTime()+"] [INFO] "+obj.toString());
	}
	
	public static void err(Object obj){
		System.err.println("["+getTime()+"] [ERR] "+obj.toString());
	}
	
	private static String getTime(){
		SimpleDateFormat sdFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = sdFormat.format(date);
		return strDate;
	}
	
	private static final int SCREEN_W = 800 ,SCREEN_H = 600;
	private boolean run = true;
	
	public MainScreen(){
		super();
		this.setTitle("體位記錄小幫手");
        this.setBounds(0,0,SCREEN_W,SCREEN_H);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        new Thread(new SystemUpdate()).start();
	}
	
	BufferedImage screen = new BufferedImage(SCREEN_W,SCREEN_H,BufferedImage.TYPE_3BYTE_BGR);
	
	public void paint(Graphics g){
		Graphics2D screen_g2d = (Graphics2D) screen.getGraphics();
		screen_g2d.setColor(Color.white);
		screen_g2d.fillRect(0,0,SCREEN_W,SCREEN_H);
		super.paint(screen_g2d);
		g.drawImage(screen,0,0,null);
	}
	
	class SystemUpdate implements Runnable{
		public void run(){
			while(run){
				try{
					Thread.sleep(50);
					repaint();
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

}
