package al1enum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 
 * @author al1enum
 *
 */

public class DigitalSignature {

	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, IOException, SignatureException {
		
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter your text :");
		byte[] input = stdin.readLine().getBytes();
		
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
		keyPairGen.initialize(2048);
		KeyPair pair = keyPairGen.generateKeyPair();
		
		
		Signature signature = Signature.getInstance("SHA256withDSA");
		
		PrivateKey privateKey = pair.getPrivate();
		signature.initSign(privateKey);
		signature.update(input);
		byte[] signBytes = signature.sign();
		
		System.out.println("Signature: "+ new String(signBytes));
		
		System.out.println("Verification....");
		signature.initVerify(pair.getPublic());
		signature.update(input);
		
		if(signature.verify(signBytes)) {
			
			System.out.println("[+] The signature is valid.");
		}
		else {
			
			System.err.println("[!] The signature is fake.");
		}

	}

}
