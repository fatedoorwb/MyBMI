package idv.kuchu.mybmi.data;

import java.io.File;

import idv.kuchu.mybmi.MainScreen;

public class DataManager {
	
	public final static DataManager instance = new DataManager();
	
	private DataManager(){
	}
	
	public boolean hasUserData(){
		File file;
		file = new File(MainScreen.getCurrentFile(),"data");
		if(!file.exists() || !file.isDirectory())
			file.mkdirs();
		file = new File(MainScreen.getCurrentFile(),"data/user.json");
		return file.exists();
	}

}
