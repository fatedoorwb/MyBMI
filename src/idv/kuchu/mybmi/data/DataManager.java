package idv.kuchu.mybmi.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import idv.kuchu.mybmi.MainScreen;
import idv.kuchu.mybmi.component.FirstPanel;

public class DataManager {

	public final static DataManager instance = new DataManager();

	private DataManager() {
	}

	public boolean hasUserData() {
		File file;
		file = new File(MainScreen.getCurrentFile(), "data");
		if (!file.exists() || !file.isDirectory())
			file.mkdirs();
		file = new File(MainScreen.getCurrentFile(), "data/user.json");
		return file.exists() && !file.isDirectory();
	}

	public JSONObject getUserData() {
		File file = new File(MainScreen.getCurrentFile(), "data/user.json");
		return this.read(file);
	}

	public boolean hasData() {
		File file = new File(MainScreen.getCurrentFile(), "data/data.json");
		return file.exists() && !file.isDirectory();
	}

	public JSONObject getData() {
		File file = new File(MainScreen.getCurrentFile(), "data/data.json");
		return this.read(file);
	}
	
	public void reset(){
		File file = new File(MainScreen.getCurrentFile(), "data");
		if(file.isDirectory()){
			deleteAll(file);
			if(!this.hasUserData()){
				if(!(MainScreen.getInstance().getF() instanceof FirstPanel)){
					MainScreen.getInstance().addF(new FirstPanel());
				}
			}
		}
	}
	
	private void deleteAll(File path) {
        if (!path.exists()) {
            return;
        }
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAll(files[i]);
        }
        path.delete();
    }

	public boolean wirte(File file, JSONObject json) {
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}
		}
		if (file.isDirectory())
			return false;
		try {
			FileWriter fw = new FileWriter(file);
			fw.write(json.toString());
			fw.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return false;
		}
		return true;
	}

	public JSONObject read(File file) {
		if (!file.exists() || file.isDirectory()) {
			return null;
		}
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String result = "";
			while (br.ready()) {
				result += br.readLine();
			}
			fr.close();
			try {
				return new JSONObject(result);
			} catch (JSONException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return null;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.toString());
			return null;
		}
	}

}
