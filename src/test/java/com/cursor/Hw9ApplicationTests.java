package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import com.cursor.entity.Teacher;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class Hw9ApplicationTests {

      StudentsGroup studentsGroup;
	  Student student;
	  Teacher teacher;

	@Test
	public void testStudentConstructor()
	{
		Student firstStudent = new Student();
		StudentsGroup firstStudentsGroup = new StudentsGroup();
		StudentsGroup secondGroup = new StudentsGroup("group");
		Student secondStudent = new Student(1L,"Peter","Toretto","male",55,firstStudentsGroup);
		StudentsGroup studentsGroup1 = secondStudent.getStudentsGroup();
		Long stId = secondStudent.getId();
		int stAge = secondStudent.getAge();
		String stSex = secondStudent.getGender();
		String stFirstName = secondStudent.getFirstName();
		String stLastName = secondStudent.getLastName();
	}

	@Test
	public void testTeacherConstructor()
	{
		Teacher teacherForTest = new Teacher("Will","Peterson","male",44);

		Long teacherId = teacherForTest.getId();
		int teacherAge = teacherForTest.getAge();
		String stSex = teacherForTest.getGender();
		String stFirstName = teacherForTest.getFirstName();
		String stLastName = teacherForTest.getLastName();
	}


	@BeforeEach
	public void initStudentToGroup()
	{
		studentsGroup = new StudentsGroup("first");
		student = new Student("John", "Smith","male",33);
		studentsGroup.addStudent(student);
	}

	@Test
	void testAddStudentsToGroup() {
		studentsGroup.addStudent(student);
		Assumptions.assumeTrue(studentsGroup.getStudents().contains(student));
	}

	@Test
	public void deleteStudentsFromGroup()
	{
		studentsGroup.deleteStudent(student);
		Assumptions.assumeFalse(studentsGroup.getStudents().contains(student));
	}

	@BeforeEach
	public void initGroupToTeacher()
	{
		studentsGroup = new StudentsGroup("first");
		teacher = new Teacher("Will","Peterson","male",44);
		teacher.addStudentsGroup(studentsGroup);
	}

	@Test
	public void testAddGroupToTeacher() {

		Assumptions.assumeTrue(teacher.getStudentsGroups().contains(studentsGroup));
	}

	@Test
	public void testDeleteGroupFromTeacher()
	{
		teacher.deleteStudentsGroup(studentsGroup);
		Assumptions.assumeFalse(teacher.getStudentsGroups().contains(studentsGroup));
	}

}
