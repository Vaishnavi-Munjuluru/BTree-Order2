package strategies;

import main.Student;

public class OrderByName<E> implements OrderBehaviour{

    /**
     * Lexicographical order by name
     * This overridden method is used to compare the names of the given two
     * student object. It returns integer value.
     * 
     * @param leftKey It is the student object which is to be compared 
     * @param rightKey It is the student object which is to be compared with leftKey 
     * @return It returns an integer value depending on the comparison 
     * negative value if the given student is lexicographically lesser than the current student
     * positive value if the given student is lexicographically greater than the current student).
     */
	@Override
	public int compareTo(Object leftKey, Object rightKey) {
		return ((Student) leftKey).getName().compareTo(((Student) rightKey).getName());
	}
	
}
