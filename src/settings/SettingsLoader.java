package settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SettingsLoader {
	
	private String user;
	private String pw;
	private String ip;
	private String[] cameras = new String[4];
	
	//Getters & Setters
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String[] getCameras() {
		return cameras;
	}
	public void setCameras(String[] cameras) {
		this.cameras = cameras;
	}
	
	
	//Constructor
	public SettingsLoader() {
		super();
		Properties properties= new Properties();
		
		try {
			
		      properties.load(new FileInputStream(new File("src/settings.properties")));
		      
		      ip = properties.get("IP").toString();
		      user = properties.get("USER").toString();
		      pw = properties.get("PASSWORD").toString();
		      cameras = properties.get("CAMERAS").toString().split(",");
		      
		    } catch (FileNotFoundException e) {
		    	
		      e.printStackTrace();
		      
		    } catch (IOException e) {
		    	
		      e.printStackTrace();
		      
		    }
	}
	
}
