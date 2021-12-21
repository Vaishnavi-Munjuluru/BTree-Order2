package main;
/**
 * This is the student class where the required details of Student name, RedID and GPA are declared.
 * It also contains the getter and setter methods which can be used for accessing variables to 
 * provide encapsulation.
 */
public class Student {
    String name;
    int redId;
	float gpa;

	public Student(String name,int redId, float gpa){
        this.name = name;
        this.redId = redId;
        this.gpa = gpa;
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRedId() {
		return redId;
	}

	public void setRedId(int redId) {
		this.redId = redId;
	}

	public float getGpa() {
		return gpa;
	}

	public void setGpa(float gpa) {
		this.gpa = gpa;
	}
	
    /**
	 * This method is to convert the object into string.
	 */
    public String toString() {
        return "Student Name: " + name + "\nRed ID: " + redId + "\nGPA: " + gpa;
    }

}
