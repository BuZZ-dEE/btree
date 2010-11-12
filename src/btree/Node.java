package btree;

import java.util.ArrayList;

/**
 * The class node represents a b-tree node.
 * 
 * @author BuZZ-dEE
 *
 */
public class Node {

	private Node father;
	private ArrayList<Node> children;
	private ArrayList<Integer> keys;
	
	public Node(int order) {
		//this.father = new Node();
		this.children = new ArrayList<Node>(order);
		this.keys = new ArrayList<Integer>(order - 1);
	}
	
	/**
	 * @return true, if node is a leaf
	 */
	public boolean isLeaf() {
		return children.isEmpty();
	}

	/**
	 * @return the father
	 */
	public Node getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Node father) {
		this.father = father;
	}

	/**
	 * @return the children
	 */
	public ArrayList<Node> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	/**
	 * @return the keys
	 */
	public ArrayList<Integer> getKeys() {
		return keys;
	}

	/**
	 * @param keys the keys to set
	 */
	public void setKeys(ArrayList<Integer> keys) {
		this.keys = keys;
	}
}