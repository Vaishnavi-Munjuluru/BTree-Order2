package strategies;

/**
 * This interface has the compareTo method which can be overridden by the classes with the implementation
 * to pass the order strategy to order the btree.
 */
public interface OrderBehaviour {
	public int compareTo(Object leftNode, Object rightNode);
}
