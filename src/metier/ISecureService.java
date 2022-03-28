package metier;

import model.Student;

public interface ISecureService 
{
	String generatePassword(Student student);
	void encryptPassword(Student student);
	String decryptPassword(Student student);
}
