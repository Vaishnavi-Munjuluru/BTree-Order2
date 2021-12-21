package bTree;
import java.util.*;
import java.util.function.Consumer;

import strategies.OrderBehaviour;

/**
 * Implementation of BTree with order-3 which extends AbstractSet abstract class.
 * @author Vaishnavi Munjuluru
 */
public class BTree <E> extends AbstractSet <E> {
    private Node root;
    private OrderBehaviour order;

    /**
     * The order strategy is set here when given runtime, to do the compareTo operation as per the strategy given.
     * @param orderingStrategy
     */
    public BTree(OrderBehaviour orderingStrategy) {
        this.order = orderingStrategy;
    }

    /**
     * This method adds element details into the node and keeps track of root node.
     * @param e this parameter accepts instance of type E which is the element that is to be added into the tree.
     */
    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node(e);
            return true;
        }
        return root.add(e);
    }

    /**
     * @return returns the size of the BTree
     */
    @Override
    public int size() {
        return toArray().length;
    }

    /**
     * @return 
     * returns true if BTree is empty
     * returns false if BTree is not empty
     */
    @Override
    public boolean isEmpty() {
        return size() > 1 ? false : true;
    }

    /**
     * @return it returns String of BTree elements
     */
    @Override
    public String toString() {
        if(root!=null) return root.toStringBuilder();
        return "";
    }
    
    /**
     * @return it returns the array of BTree elements
     */
    @Override
    public Object[] toArray() {
    	List<E> list = new ArrayList<E>() ;
    	if(root!=null) {
	    	Iterator iterator = this.iterator();
	    	while (iterator.hasNext()) {
	    		list.add((E) iterator.next());
	        }
	    return list.toArray();
    	}
        return new Object[0];
    }

    /**
     * It overrides the forEach method of Iterable class.
     * It does reverse in-order traversal
     * It Performs the given action for each element until all elements have been processed in the reverse in-order traversing.
     * @param action which is to be performed for each element
     */
    @Override
    public void forEach(Consumer action) {
    	Objects.requireNonNull(action);
        Stack < E > reverseNodes = new Stack < E > ();
        Iterator iterator = this.iterator();
        reverseNodes.clear();
        while (iterator.hasNext()) {
            reverseNodes.push((E) iterator.next());
        }
        while (!reverseNodes.isEmpty()) {
            action.accept(reverseNodes.pop());
        }
    }

    /**
     * It is an overridden method of Set and returns a btree iterator over the elements of the btree in in-order.
     * @return an iterator over the elements in-order
     */
    @Override
    public Iterator iterator() {
        return new BTreeIterator(root);
    }

   
    /* HELPER CLASSES */
     

    /**
     * This class iterates the BTree in in-order and overrides the hasNext and next methods from the Iterator interface.
     */
    private class BTreeIterator implements Iterator {
        Node currentNode;
        Stack < Node > nodeStack = new Stack < Node > ();
        Stack < Integer > counterStack = new Stack < Integer > ();
        BTreeIterator(Node root) {
            currentNode = root;
            pushLeftNodes(currentNode);
        }

        /**
         * This method recursively adds the left children to the nodeStack till the leaf node.
         * @param node
         */
        private void pushLeftNodes(Node node) {
            while (node.getKeyCount() > 0) {
                nodeStack.push(node);
                counterStack.push(0);
                node = node.childrenNode[0];
            }
        }

        /**
         * @return 
         * returns a boolean value false if the next element does not exist.
         * returns a boolean value true if the next element exist.
         */
        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty();
        }

        /**
         * if this method is called, it 
         * @return Returns the immediate next element of the given iterator instance.
         */
        @Override
        public E next() {
            Node node = nodeStack.peek();
            int counter = counterStack.pop();
            E result = (E) node.keyNode[counter];
            counter++;
            if (counter < node.getKeyCount())
                counterStack.push(counter);
            else
                nodeStack.pop();
            
            pushLeftNodes(node.childrenNode[counter]);
            return result;
        }
    }

    /**
     * This interface has method to make Non Null and Null Node have their own implementation of the count.
     * @param <E>
     */
    private interface BTreeNode<E> {
    	 int getKeyCount();
    }
    /**
     * This NullNode class establishes inheritance with Node class.
     * The instantiation happens when there a new node is created. All of the node's children are the instances of
     * NullNode class during the Node constructor invocation.
     */
     private class NullNode < E > extends Node {
        /**
         * It returns the count value as NullNode does not store any keys.
         * @return the count value.
         */
    	@Override
    	public int getKeyCount() {
        	return -1;
        }
    }
    
    /**
     * This Node class contains 2 keys and 3 child nodes(left, middle and right) satisfying the BTree with order-3 properties 
     * and a parent Node to map the respective children.
     */
     private class Node<E> implements BTreeNode<E> {
        E[] keyNode = (E[]) new Object[2];
        Node<E> [] childrenNode = new Node[3];
        private Node parentNode;
        int count;
        
        private Node() {
            this.count = 0;
        }

        private Node(E leftNode) {
            this.keyNode[0] = leftNode;
            this.count = 1;
            this.childrenNode[0] = new NullNode();
            this.childrenNode[1] = new NullNode();
            this.childrenNode[2] = new NullNode();
        }

        private Node(E leftNode, E rightNode) {
            this.keyNode[0] = leftNode;
            this.keyNode[1] = rightNode;
            this.count = 2;
            this.childrenNode[0] = new NullNode();
            this.childrenNode[1] = new NullNode();
            this.childrenNode[2] = new NullNode();
        }
        
        /**
         * It tracks the number of keys in each node
         * @return the count of keys.
         */
        @Override
        public int getKeyCount() {
        	return this.count;
        }
        
        /**
         * it is the method that is called from the Btree class where we create a root with this instance
         * @return the string value to Btree
         */
        private String toStringBuilder() {
        	Node currentNode = this;
        	StringBuilder stringBuilder = new StringBuilder() ;
        	toString(currentNode, stringBuilder);
        	return  stringBuilder.toString();
        }
        
        /**
         * Recursively calls for the Btree elements and appends to string builder.
         * @param node
         * @param string builder
         */
        private void toString(Node currentNode, StringBuilder stringBuilder) {
        	 if (currentNode.getKeyCount()>0) {
        		 toString(currentNode.childrenNode[0], stringBuilder);
	             if (currentNode.keyNode[0] != null) {
	            	 stringBuilder.append((E) currentNode.keyNode[0]);
	             }
	             toString(currentNode.childrenNode[1], stringBuilder);
	             if (currentNode.keyNode[1] != null) {
	            	 stringBuilder.append((E) currentNode.keyNode[1]);
	             }
	             toString(currentNode.childrenNode[2], stringBuilder);
        	 }
        }
        
        /**
         * Adds the current node to the BTree
         * @param currentKey
         * @return true if node is added
         */
        private boolean add(E currentKey) {
            Node nodeToInsert = getNode(currentKey,root);
            if (nodeToInsert.keyNode[1] == null) {
                if (order.compareTo(currentKey, nodeToInsert.keyNode[0]) < 0) {
                    nodeToInsert.keyNode[1] = nodeToInsert.keyNode[0];
                    nodeToInsert.keyNode[0] = currentKey;
                } else if (order.compareTo(currentKey, nodeToInsert.keyNode[0]) > 0)
                    nodeToInsert.keyNode[1] = currentKey;
                nodeToInsert.count = 2;
            } else {
                BufferNode bufferNode = null;
                if (order.compareTo(currentKey, nodeToInsert.keyNode[0]) <= -1) {
                    bufferNode = new BufferNode(currentKey, nodeToInsert.keyNode[0], nodeToInsert.keyNode[1]);
                } else if (order.compareTo(currentKey, nodeToInsert.keyNode[0]) >= 1 && order.compareTo(currentKey, nodeToInsert.keyNode[1]) <= -1) {
                    bufferNode = new BufferNode(nodeToInsert.keyNode[0], currentKey, nodeToInsert.keyNode[1]);
                } else if (order.compareTo(currentKey, nodeToInsert.keyNode[1]) >= 1) {
                    bufferNode = new BufferNode(nodeToInsert.keyNode[0], nodeToInsert.keyNode[1], currentKey);
                }
                root.insertBufferNode(nodeToInsert, bufferNode);
            }
            return true;
        }

        /**
         * This method provides the appropriate position for the new node to be added.
         * it begins from the root and executes recursively until it finds the correct leaf node.
         *  
         * @param currentKey indicates the new node to be added 
         * @param startNode indicates the root node (type: Node)
         * @return this method returns the found Node (position) 
         */
        private Node getNode(E currentKey, Node traversingNode) {
            if (traversingNode.childrenNode[0] instanceof NullNode)
                return traversingNode;

            if (order.compareTo(currentKey, traversingNode.keyNode[0]) < 0)
                return getNode(currentKey, traversingNode.childrenNode[0]);

            if (traversingNode.keyNode[1] == null) {
                return getNode(currentKey, traversingNode.childrenNode[1]);
            } else {
                if (order.compareTo(currentKey, traversingNode.keyNode[0]) > 0 && order.compareTo(currentKey, traversingNode.keyNode[1]) < 0)
                    return getNode(currentKey, traversingNode.childrenNode[1]);
                else
                    return getNode(currentKey, traversingNode.childrenNode[2]);
            }
        }

        /**
         * This method inserts the buffer node into the tree.
         * As the buffer node contains 3 keys, nodes has to be split and merged with the current parent before inserting.
         * Then insertBufferNode is called recursively till the root.
         * @param currentNode it is the tree node where buffer node has to be inserted (type: Node)
         * @param bufferNode it is the temporary node with 3 objects (type: BufferNode)
         */
        private void insertBufferNode(Node currentNode, BufferNode bufferNode) {
            Node splitResult = split(bufferNode);
            if (currentNode == root) {
                root = splitResult;
            } else {
                Node parent = currentNode.parentNode;
                BufferNode mergeResult = mergeNodes(parent, splitResult);
                if (mergeResult != null)
                    insertBufferNode(parent, mergeResult);
            }
        }

        /**
         * This method creates a new root node, its child nodes (left and new right nodes).
         * The buffer node's objects are added to the newly created nodes in the respective positions (lexicographically) while linking 
         * children and parent.
         * @param bufferNode: it is the buffer node where there are 3 objects (type: BufferNode).
         * @return it returns the a new node structure as split result (type: Node).
         */
        private Node split(BufferNode bufferNode) {
            Node newRootNode = new Node(bufferNode.keyNode[1]);
            Node newLeftNode = new Node(bufferNode.keyNode[0]);
            Node newRightNode = new Node(bufferNode.bufferKeyNode);

            //Set the new children to the NewRoot and vice versa.
            newRootNode.childrenNode[0] = newLeftNode;
            // System.out.println(newRootNode.childrenNode[0].getClass());

            newRootNode.childrenNode[1] = newRightNode;

            newLeftNode.parentNode = newRootNode;
            newRightNode.parentNode = newRootNode;

            //copy all the children of buffernode to the respective newly created node's child position.
            newLeftNode.childrenNode[0] = bufferNode.childrenNode[0];
            if (!(newLeftNode.childrenNode[0] instanceof NullNode))
                newLeftNode.childrenNode[0].parentNode = newLeftNode;

            newLeftNode.childrenNode[1] = bufferNode.childrenNode[1];
            if (!(newLeftNode.childrenNode[1] instanceof NullNode))
                newLeftNode.childrenNode[1].parentNode = newLeftNode;

            newRightNode.childrenNode[0] = bufferNode.bufferChildNode;
            if (!(newRightNode.childrenNode[0] instanceof NullNode)) {
                newRightNode.childrenNode[0].parentNode = newRightNode;
            }

            newRightNode.childrenNode[1] = bufferNode.childrenNode[2];
            if (!(newRightNode.childrenNode[1] instanceof NullNode))
                newRightNode.childrenNode[1].parentNode = newRightNode;

            return newRootNode;
        }

        /**
         * This method is executed when the key 2 position is empty.
         * Lexicographical comparison is made and the key is added to the appropriate position.
         * The bidirectional relinking of parent and children is done after the merge.
         * @param existingTreeNode this is the tree node which is structured already (type: Node).
         * @param nodeToBeMerged this is the node which has to be added to the existingTreeNode (type: Node).
         */
        private void mergeSecondNode(Node existingTreeNode, Node nodeToBeMerged) {
            if (order.compareTo(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[0]) <= -1) {
                existingTreeNode.keyNode[1] = existingTreeNode.keyNode[0];
                existingTreeNode.keyNode[0] = nodeToBeMerged.keyNode[0];
                existingTreeNode.count = 2;
                existingTreeNode.childrenNode[2] = existingTreeNode.childrenNode[1];
                existingTreeNode.childrenNode[1] = nodeToBeMerged.childrenNode[1];
                existingTreeNode.childrenNode[0] = nodeToBeMerged.childrenNode[0];

            } else if (order.compareTo(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[0]) >= 1) {
                existingTreeNode.keyNode[1] = nodeToBeMerged.keyNode[0];
                existingTreeNode.count = 2;
                existingTreeNode.childrenNode[2] = nodeToBeMerged.childrenNode[1];
                existingTreeNode.childrenNode[1] = nodeToBeMerged.childrenNode[0];

            }
            nodeToBeMerged.childrenNode[1].parentNode = existingTreeNode;
            nodeToBeMerged.childrenNode[0].parentNode = existingTreeNode;
        }

        /**
         * This method creates a buffer node with merge node's key1 (sorted in lexicographical order) in first position 
         * position and maps respective children.
         * @param existingTreeNode this is the tree node which is structured already (type: Node)
         * @param nodeToBeMerged this is the node which has to be added to the existingTreeNode (type: Node).
         * @param newBufferNode this is the buffer node to be created and link the respective children (type: BufferNode).
         * @return it returns the BufferNode after linking the children (type: BufferNode).
         */
        private BufferNode mergeNodeInLeft(Node existingTreeNode, Node nodeToBeMerged, BufferNode newBufferNode) {
            newBufferNode = new BufferNode(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[0], existingTreeNode.keyNode[1]);

            newBufferNode.childrenNode[0] = nodeToBeMerged.childrenNode[0];
            newBufferNode.childrenNode[1] = nodeToBeMerged.childrenNode[1];
            newBufferNode.bufferChildNode = existingTreeNode.childrenNode[1];
            newBufferNode.childrenNode[2] = existingTreeNode.childrenNode[2];

            return newBufferNode;
        }

        /**
         * This method creates a buffer node with merge node's key1 (sorted in lexicographical order) in third position 
         * position and maps respective children.
         * @param existingTreeNode this is the tree node which is structured already (type: Node).
         * @param nodeToBeMerged this is the node which has to be added to the existingTreeNode (type: Node).
         * @param newBufferNode this is the buffer node to be created and link the respective children (type: BufferNode)
         * @return it returns the BufferNode after linking the children (type: BufferNode).
         */
        private BufferNode mergeNodeInRight(Node existingTreeNode, Node nodeToBeMerged, BufferNode newBufferNode) {
            newBufferNode = new BufferNode(existingTreeNode.keyNode[0], existingTreeNode.keyNode[1], nodeToBeMerged.keyNode[0]);

            newBufferNode.childrenNode[0] = existingTreeNode.childrenNode[0];
            newBufferNode.childrenNode[1] = existingTreeNode.childrenNode[1];
            newBufferNode.bufferChildNode = nodeToBeMerged.childrenNode[0];
            newBufferNode.childrenNode[2] = nodeToBeMerged.childrenNode[1];
            return newBufferNode;
        }

        /**
         * This method creates a buffer node with merge node's key1 (sorted in lexicographical order) in second position 
         * position and maps respective children.
         * @param existingTreeNode this is the tree node which is structured already (type: Node).
         * @param nodeToBeMerged this is the node which has to be added to the existingTreeNode (type: Node).
         * @param newBufferNode this is the buffer node to be created and link the respective children (type: BufferNode)
         * @return it returns the BufferNode after linking the children (type: BufferNode).
         */
        private BufferNode mergeNodeInMiddle(Node existingTreeNode, Node nodeToBeMerged, BufferNode newBufferNode) {
            newBufferNode = new BufferNode(existingTreeNode.keyNode[0], nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[1]);
            newBufferNode.childrenNode[0] = existingTreeNode.childrenNode[0];
            newBufferNode.childrenNode[1] = nodeToBeMerged.childrenNode[0];
            newBufferNode.bufferChildNode = nodeToBeMerged.childrenNode[1];
            newBufferNode.childrenNode[2] = existingTreeNode.childrenNode[2];
            return newBufferNode;
        }

        /**
         * This method creates a merge structure and returns that merge result structure in the insertBufferNode method.
         * It has two different scenarios. 
         * If the tree node's key2 is empty, it will directly insert the node at that position and links the respective parents and children.
         * 
         * If the tree node's keys are full, it creates a new buffer node with 3 keys, compares and finds the position of the current 
         * key of where to insert (left, middle or right) and creates that buffer node in that respective 
         * order.
         * It links the all the tree node's and buffernode's children and parents bidirectional.
         * It also re-links the children of newBufferNode and make newBufferNode as their parent.
         * 
         * @param existingTreeNode: It is the parent node to which the the given node has to be merged (type: Node).
         * @param nodeToBeMerged: this is the node which has to be merged with the tree node (type: Node).
         * @return it returns the new merged buffer node structure to insert into the tree(type: BufferNode).
         */
        private BufferNode mergeNodes(Node existingTreeNode, Node nodeToBeMerged) {
            BufferNode newBufferNode = null;
            if (existingTreeNode.keyNode[1] == null) {
                mergeSecondNode(existingTreeNode, nodeToBeMerged);
            } else //If the node in the tree we are merging with is a full.
            {
                if (order.compareTo(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[0]) <= -1) {
                    newBufferNode = mergeNodeInLeft(existingTreeNode, nodeToBeMerged, newBufferNode);
                } else if (order.compareTo(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[0]) > 0 && order.compareTo(nodeToBeMerged.keyNode[0], existingTreeNode.keyNode[1]) < 0) {

                    newBufferNode = mergeNodeInMiddle(existingTreeNode, nodeToBeMerged, newBufferNode);

                } else {
                    newBufferNode = mergeNodeInRight(existingTreeNode, nodeToBeMerged, newBufferNode);
                }

                newBufferNode.childrenNode[0].parentNode = newBufferNode;
                newBufferNode.childrenNode[1].parentNode = newBufferNode;
                newBufferNode.bufferChildNode.parentNode = newBufferNode;
                newBufferNode.childrenNode[2].parentNode = newBufferNode;
                newBufferNode.count = 3;
            }
            return newBufferNode;
        }
    }

    /**
     * This BufferNode class establishes inheritance with Node class. It has a buffer key and buffer Child.
     * The instantiation happens when there is a need of inserting a third node to split and merge with the parent node.
     */
    private class BufferNode < E > extends Node {
        private E bufferKeyNode;
        private Node bufferChildNode;

        private BufferNode(E leftKeyNode, E rightKeyNode, E bufferKeynode) {
            super(leftKeyNode, rightKeyNode);
            this.bufferKeyNode = bufferKeynode;
            this.childrenNode[0] = new NullNode();
            this.childrenNode[1] = new NullNode();
            this.childrenNode[2] = new NullNode();
            bufferChildNode = new NullNode();

        }
    }
}