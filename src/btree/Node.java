package btree;

import java.util.ArrayList;

/**
 * Die Klasse Node repr&auml;sentiert einen Knoten eines B-Baums
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
	
	public Node() {

	}
	
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
}