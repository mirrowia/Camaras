package settings;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {

		Crypto crypto = new Crypto();
		
		System.out.println(crypto.encrypt("hello"));
		
		System.out.println(crypto.decrypt("y/JCB0a+qUnWsHJQz4unIQ=="));
		
	}

}
