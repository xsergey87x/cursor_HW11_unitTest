package com.cursor;

import com.cursor.entity.Student;
import com.cursor.entity.StudentsGroup;
import org.junit.Assume;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


class Hw9ApplicationTests {
	@Test
	void testAddStudentsToGroup() {

		StudentsGroup studentsGroup = new StudentsGroup("first");
		Student student = new Student("John", "Smith","male",33);
		studentsGroup.addStudent(student);
		Assume.assumeTrue(studentsGroup.getStudents().contains(student));
	}

}
