package dao;

import model.Student;

public interface IStudentDao 
{
	Student findByFullName(String fulname);
}
