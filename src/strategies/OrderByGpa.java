package strategies;

import main.Student;

public class OrderByGpa implements OrderBehaviour{

	/**
	 * Decreasing order of GPA
     * This overridden method is used to compare the GPA of the given two
     * student objects. It returns integer value.
     * It is used to compare and add the objects in increasing order.
     * @param leftKey It is the student object which is to be compared 
     * @param rightKey It is the student object which is to be compared with leftKey 
     * @return It returns an integer value depending on the comparison 
     * negative value if the given student is lexicographically greater than the current student
     * positive value if the given student is lexicographically lesser than the current student).
     */
	@Override
	public int compareTo(Object leftKey, Object rightKey) {
		return ((Student) leftKey).getGpa() > ((Student) rightKey).getGpa() ? -1 : 1;
	}
}
