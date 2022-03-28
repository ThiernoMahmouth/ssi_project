package presentation;

import javax.swing.*;

import dao.IStudentDao;
import dao.StudentDao;
import metier.ISecureService;
import metier.SecureService;
import model.Student;

public class Application 
{
    public static void main(String[] args) 
    {
    	ISecureService secureService = new SecureService();
    	IStudentDao studentDao = new StudentDao();
    	
        String prenomETnom = JOptionPane.showInputDialog("Donner le nom et le prenom de l'etudiant:");
        
        Student student = studentDao.findByFullName(prenomETnom);
        
        if (student != null)
        {
        	String motDePasse = secureService.generatePassword(student);
            JOptionPane.showMessageDialog(null, "Mot de passe généré: " + motDePasse);

        	secureService.encryptPassword(student);
            JOptionPane.showMessageDialog(null, "Mot de passe crypté: " + student.getPasswordEncrypted());
            

            JOptionPane.showMessageDialog(null, "Message décrypté: " + secureService.decryptPassword(student));
        }
        else
            JOptionPane.showMessageDialog(null, "Vous n'êtes pas un étudiant de DIC3 ");
    }
}