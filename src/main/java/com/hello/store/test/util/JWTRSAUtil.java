package com.hello.store.test.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTRSAUtil {
    //加载jwt.jks文件
    private static InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;

    static {
        try {
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "testjwt".toCharArray());
            privateKey = (PrivateKey) keyStore.getKey("jwt", "testjwt".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(String subject, int expirationSeconds) {
        return Jwts.builder()
                .setClaims(null)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + expirationSeconds * 1000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }
    
    public static String getPublicKey() {
    	
//    	System.out.println("签名公钥内容："+Base64.encodeBase64String(publicKey.getEncoded()));
    	
		return Base64.encodeBase64String(publicKey.getEncoded());
    }
    
    public static String getPrivateKey() {
    	
//    	System.out.println("签名私钥内容："+Base64.encodeBase64String(privateKey.getEncoded()));
    	
    	return Base64.encodeBase64String(privateKey.getEncoded());
    }

    public static Claims parseToken(String token) {
//        String subject = null;
    	
    	Claims claims = null;
    	
        try {
            claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
//            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return claims;
    }

	/**
	 * 从cer格式的证书文件中获取publickey
	 * @param pubkeyfile
	 * @return
	 */
	public static String loadPubkey(String pubkeyfile){
		System.out.println("加载验签证书==>" + pubkeyfile);
		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			in = new FileInputStream(pubkeyfile);
			X509Certificate validateCert = (X509Certificate) cf.generateCertificate(in);
			
			PublicKey publicKey2 = validateCert.getPublicKey();
			
			return Base64.encodeBase64String(publicKey2.getEncoded());
		}catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
		return null;
	}

    
}
