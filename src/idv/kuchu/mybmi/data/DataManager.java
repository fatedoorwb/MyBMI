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
		return file.exists();
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
