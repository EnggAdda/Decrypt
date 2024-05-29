package org.example.decrypt;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.crypto.RSADecrypter;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@SpringBootApplication
public class DecryptApplication {
	private static RSAPrivateKey privateKey ;

	private static final String PRIVATE_KEY_STRING = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRH7TGtUEie5weQKDNQSCm8uTLTO/F1gM78fCdewyNCUbPMPyvjBvmozxm4x/DASAoXEeWeC82lQ4fhdJMbAw10kbuq4f0kcczf9i3XQi3a3sCqbYWFr4XQMh+bDaYQgZ+vDntohspaoggjx2hjbwg0fnlHqrYMKZ7R1Km11hx5LGQ9WIfyXtLNx3PXVzRvTQJmDW4LUH3uEtC6ZKTvcVqMRE173EvFeKsCh5UICC36tLVLT2PLVvGgeWPnN3/T4bGXMjsj1+uybGAtMHlV2hnE7lHVyL1oKmSwYLR2OqKGTif4IJXD1otluxIsbzIk4qRzaDRqhbUVNjaQ75WJGhjAgMBAAECggEAAlB2kYe6UWrgOl6Xgd4Al1adzs5jcSNUBJzB/4tGH6xWVKmqn44OVahBXV7LGT80cS3nXummI6rGvBHLj/MPt8D8mMAjjN8tPufTIcTNayuQgFPD7dQYfh7UlaLrRxZrholrSSSRZn7hfOT6WM2+BoZKlaJ7o22hg874UCLsS7UGH9NpcQkPscqy2Anm5LsGnp78Lpq619TAjoJCkj5skI7CprDjdCfVzZkz/egK7yl72V+d71LpGe8dIKCB+Gq52TU+mHKdeqPc8eUINRqlxBVAJ/UfH5UUZF7o5XPujjFkWw0ms73CCaI6dTVQrygWMc+xuuIxlH4FFJUJ6WuXbQKBgQDUielkerxqfKTjUl1X3mjqOrVm+MX53Y2Oyy62FAv8od+XRFVJkdhfpqA8Uy1ihRGJEG9hGn1Kseb5/lmla5mB7npgvS4f+SoUNxuG4fSPwWDtZlRnoLG6cD0lpM5b1YcGIH1pO4WBiqQcVwsRlqvQUVe2EhMF5Ai1GRTKJbfETwKBgQD74wgDhsr1uC0EFi4RALEr/UOrOSFQfj0wYmuhgzTIkr+bbyCQoFWqXH8bhLr9syl8h1ZQIfOgsgMLMNW/M8CDqR9dBLHFZPKSNx11ouaHmVGvWjJLi5xoTIMDxEAvomlpipnqLmCliJkCJYn/pIs5VjUX8Kj2dZEFcRiU6/yRrQKBgQCTzslSSI0KHV7qfFhQCGu8IVIQp4e/KhYNLT5x/+UKL2T4pNwPSu9ILfAXo9zXPCWixZw/0pU5whkY2IttoDPkiX3wEwCl2bkqz9xD5JYY92o6D0U5wCZ5ux67NC8S7J93Ylb1PSCuo0VYNCyNcdRRDpDKHS2/lgN8NlIf9zUivwKBgQC4H5LiEQwXZhPx4sy1vQ3FnBJ0/QPvNPhBgBAxNhnFbBAH7MmfLrSwHYEEepe9j9OJpdxgIxFVrAoxifcVFx1ooJ2+fwn7wvjWDNfJ/9QH1vkw9vvA4ULpstQqUrCgoVXwwlIULuaS2jEOsqYXaw4iUXPqFg9MaVAi7DMN2Rr82QKBgDPqLMBxpQ0WD3ae1D2An+wvtVHTPd/FFsJzDhNhUJ5BuJC4WvYNkHQoFBQgonQDjOx8+cCNEoU14EY82GCdHtroE+l7zBSEhMIOKOeaZbBkFXbJo49nQE43dj/uN9MaPs9I0b2aLzUg03/SGupVu5HheO7WRjzqknxeAcZvTPHZ";

	public static void initFromStrings(){
		try{
			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY_STRING));
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			privateKey = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		}catch (Exception ignored){}
	}
	private static byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	public static void main(String[] args) {
		SpringApplication.run(DecryptApplication.class, args);
		initFromStrings();
		String encryptedData = "eyJjdHkiOiJ0ZXh0XC9wbGFpbiIsImVuYyI6IkEyNTZHQ00iLCJhbGciOiJSU0EtT0FFUC0yNTYifQ.Q-t0IBG0awzVHLHjzAERoGez69ewdkcP2IktNBEe7LfX1JrEDQpK9t8tt4Wi-_V-ViZ5AVDy6J4BvMP6EdGMyv2xvUSfGEBv09mVzK-FFKNdkFHP1x2hP4-3VfPGsDnV7dR4ZVATVE7EUYbB-mONJW-qYI-ZmkTZL0FQpvDpdwZh61jbybo4VJkYhsuSskibZYOomCP4F4VnKffbC_2r0unV1QfMmix3k9WLw11U6bz0h8sAOB3QdROVG2FmjJ5Jynl1zQ6yplTENlvizx2CDeMR3524XX1wNJPJQXZzcyJWgakVeCM-4-L1cTWxpJPsdDvYxpHk9jshC2t9vskZEg.RWNsIOWVFhznrxXP.yX5LvIEb8wGiI4rJ9g.LAq05dz03wxg8ompoaYaWA";

        try {
            String decryptedData  =decryptData(encryptedData);
			System.out.println(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	public static String decryptData(String encryptedData) throws Exception {
		// Parse the JWE object

		JWEObject jweObject = JWEObject.parse(encryptedData);

		// Create decrypter with the public key
		RSADecrypter decrypter = new RSADecrypter(privateKey);

		// Decrypt the JWE object
		jweObject.decrypt(decrypter);

		// Extract payload
		return jweObject.getPayload().toString();
	}

}
