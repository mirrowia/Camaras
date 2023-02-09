package settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SettingsLoader {

	private String user;
	private String pw;
	private String decryptedPw;
	private String encryptedPw;
	private String ip;
	private String[] cameras = new String[4];
	private Properties properties;
	private final String path = "src/settings.properties";

	// Getters & Setters
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

	public String getDecryptedPw() {
		return decryptedPw;
	}

	public void setDecryptedPw(String decryptedPw) {
		this.decryptedPw = decryptedPw;
	}

	public String getEncryptedPw() {
		return encryptedPw;
	}

	public void setEncryptedPw(String encryptedPw) {
		this.encryptedPw = encryptedPw;
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

	// Constructor
	public SettingsLoader() {
		super();
		properties = new Properties();
		try {

			FileInputStream fileIn = new FileInputStream(path);
			properties.load(fileIn);

			ip = properties.get("IP").toString();
			user = properties.get("USER").toString();
			decryptedPw = (String) properties.get("DECRYPTEDPASSWORD");

			if (decryptedPw != null) {

				if (decryptedPw.equals("")) {
					
					pw = properties.get("PASSWORD").toString();

					Crypto crypto = new Crypto();

					properties.setProperty("DECRYPTEDPASSWORD", crypto.decrypt(pw));

					FileOutputStream fileOut = new FileOutputStream(path);
					properties.store(fileOut, null);
					fileOut.close();

				} else {

					Crypto crypto = new Crypto();

					properties.setProperty("PASSWORD", crypto.encrypt(decryptedPw));

					FileOutputStream fileOut = new FileOutputStream(path);
					properties.store(fileOut, null);
					fileOut.close();
				}

			}
			pw = (String) properties.get("PASSWORD");
					
			if (pw == null) {
				
				Crypto crypto = new Crypto();

				properties.setProperty("DECRYPTEDPASSWORD", crypto.encrypt(decryptedPw));

				FileOutputStream fileOut = new FileOutputStream(path);
				properties.store(fileOut, null);
				fileOut.close();
				
			}else {

				Crypto crypto = new Crypto();

				pw = crypto.decrypt(pw);
			}
			cameras = properties.get("CAMERAS").toString().split(",");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
