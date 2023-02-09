package settings;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;


public class Crypto {
	
	String key = "Lestian";
	
	
	public Crypto() {	
		
	}

	//Generar clave de encriptacion /desencriptacion
	public SecretKeySpec createKey(String key) {
		
		try {
			
			byte[] string = key.getBytes("UTF-8");
			
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			
			string = md.digest(string);
			
			string = Arrays.copyOf(string, 16);
			
			SecretKeySpec secreyKeySpec = new SecretKeySpec(string, "AES") ;
			
			return secreyKeySpec;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
		}
		
	}

	//Encriptar
	public String encrypt (String toEncript) {
		
		try {
			SecretKeySpec secretKeySpec = createKey(key);
			
			Cipher cipher = Cipher.getInstance("AES");
			
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
			
			byte[] string = toEncript.getBytes("UTF-8");
			
			byte[] encrypted = cipher.doFinal(string);
			
			String encryptedString = Base64.getEncoder().encodeToString(encrypted);
			
			return encryptedString;
			
		}catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		}
	}
	
	//Desencriptar
	public String decrypt (String toDecrypt) {
			
			try {
				SecretKeySpec secretKeySpec = createKey(key);
				
				Cipher cipher = Cipher.getInstance("AES");
				
				cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
				
				byte[] string = Base64.getDecoder().decode(toDecrypt);
				
				byte[] decrypted = cipher.doFinal(string);
				
				String decryptedString = new String(decrypted);
				
				return decryptedString;
				
			}catch (Exception e) {
				
				e.printStackTrace();
				
				return null;
				
			}
		}
		
}
