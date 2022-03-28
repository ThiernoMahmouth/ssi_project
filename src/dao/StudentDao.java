package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.Student;

public class StudentDao implements IStudentDao
{
	Scanner read;
	@Override
	public Student findByFullName(String prenomETnom)
	{	
		try 
		{
			read  = new Scanner(new File("src\\DIC3.txt"));
			while (read.hasNextLine())
			{
				String data = read.nextLine().toLowerCase();
				String fullName = prenomETnom.toLowerCase();
				if (data.contains(fullName))
				{
					String[] tab = data.split(" ");
					Student etudiant = new Student();
					etudiant.setDateNaissance(tab[tab.length -1]);
					etudiant.setNom(tab[tab.length -2].toUpperCase());
					StringBuilder prenom = new StringBuilder();
					for (int i = 0; i<tab.length - 2; i++)
					{
						prenom.append(tab[i]);
					}
					etudiant.setPrenom(prenom.toString());
					return etudiant;
				}		
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
