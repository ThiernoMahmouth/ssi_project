package metier;

import java.util.Random;

import model.Student;

public class SecureService implements ISecureService
{
	private static final int length = 10;
	private CryptoService cryptoService = new CryptoService(1024);
	
	@Override
	public String generatePassword(Student student) 
	{
		StringBuilder data = new StringBuilder(), password = new StringBuilder();
		data.append(student.getPrenom());
		data.append(student.getNom());
		data.append(student.getDateNaissance());
		Random random = new Random();
		int i = 0;
		while (i<length)
		{
			int index = random.nextInt(data.toString().length());
			password.append(data.charAt(index));
			i++;
		}
		student.setPassword(password.toString());
		return  password.toString();
	}
	@Override
	public void encryptPassword(Student student) 
	{
		student.setPasswordEncrypted(cryptoService.rsa_encrypt(student.getPassword()));
	}
	@Override
	public String decryptPassword(Student student)
	{
		return cryptoService.rsa_decrypt(student.getPasswordEncrypted());
	}
}
