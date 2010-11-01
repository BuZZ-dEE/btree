package btree;

import java.util.ArrayList;

/**
 * Die Klasse Node repräsentiert einen Knoten eines B-Baums
 * 
 * @author BuZZ-dEE
 *
 */
public class Node {

	public int min_size;
	public int max_size;
	public Node father;
	public ArrayList<Node> children;
	public ArrayList<Integer> keys;
	
	public Node(int min_size, int max_size) {
		this.min_size = min_size;
		this.max_size = max_size;
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
}