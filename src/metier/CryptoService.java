package metier;

import java.math.BigInteger;
import java.security.SecureRandom;

public  class CryptoService
{
    private  BigInteger modulo, privateKey, publicKey;

    public CryptoService(int bits) 
    {
        generateKeys(bits);
    }

    public  synchronized String rsa_encrypt(String message) 
    {
        return (new BigInteger(message.getBytes())).modPow(publicKey, modulo).toString();
    }

    public  synchronized BigInteger rsa_encrypt(BigInteger message)
    {
        return message.modPow(publicKey, modulo);
    }

    public  synchronized String rsa_decrypt(String encryptedMessage) 
    {
        return new String((new BigInteger(encryptedMessage)).modPow(privateKey, modulo).toByteArray());
    }
    public  synchronized BigInteger rsa_decrypt(BigInteger encryptedMessage) 
    {
        return encryptedMessage.modPow(privateKey, modulo);
    }
    //Generate a new public and private key set
    public synchronized void generateKeys(int bits) 
    {
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bits / 2, 100, r);
        BigInteger q = new BigInteger(bits / 2, 100, r);
        modulo = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        publicKey = new BigInteger("3");
        while (m.gcd(publicKey).intValue() > 1)
        {
            publicKey = publicKey.add(new BigInteger("2"));
        }
        privateKey = publicKey.modInverse(m);
    }
    public boolean passwordMatches(String pass1, String pass2)
    {
    	return pass1.equals(pass2);
    }
}