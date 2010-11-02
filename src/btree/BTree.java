package btree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Die Implementierung eines B-Baums f&uuml;r Integerwerte mit den Operationen
 * Einf&uuml;gen und Suchen
 * 
 * @author BuZZ-dEE
 * @version 2010-11-01
 *
 */
public class BTree implements IBTree {

	public int order;
	public Node root;
	public Node father;
	public Node child;
	public Node nodeR;
	public Node nodeL;
	public ArrayList<Node> fullNodes;
	
	public BTree(int order) {
		this.root = new Node();
		this.order = order;
	}
	
	@Override
	public boolean contains(int key) {
		
		boolean exists = false;
		
		for (Integer integer: root.keys) {
			if (integer == key) {
				exists = true;
				System.out.println(root.keys.indexOf(key));
			} else if (integer > key && root.children.get(root.keys.indexOf(integer)) != null) {
				contains(key, root.children.get(root.keys.indexOf(integer)));
			} else if (root.children.get(order) != null) {
				contains(key, root.children.get(order));
			} else {
				System.out.println("key not found");
			}
		}
		
		return exists;
	}
	
	/**
	 * Pr&uuml;ft, ob der gegebene Schl&uuml;ssel im Baum enthalten ist.
	 * 
	 * @param key, der Schl&uuml;ssel der immernoch gesucht wird
	 * @param node, der Konten in dem weiter nach dem Schl&uuml;ssel gesucht wird
	 * @return Liefert true, wenn der Schl&uuml;ssel im Baum enthalten ist, sonst false.
	 */
	public boolean contains(int key, Node node) {
		
		boolean exists = false;
		
		for (Integer integer: node.keys) {
			if (integer == key) {
				exists = true;
				System.out.println(node.keys.indexOf(key));
			} else if (integer > key && node.children.get(node.keys.indexOf(integer)) != null) {
				contains(key, node.children.get(node.keys.indexOf(integer)));
			} else if (node.children.get(order) != null) {
				contains(key, node.children.get(order));
			} else {
				System.out.println("key not found");
			}
		}
		
		return exists;
	}
	
	/**
	 * Die Methode teilt einen Knoten in zwei Knoten.
	 * @param node, der geteilt werden soll
	 */
	public Node split(Node node) {
		
		Node result = null;
		double halfKeys = (double) (order-1.0) / 2.0;
		
		nodeL = new Node();
		nodeR = new Node();
		
		if (node.keys.size() == order-1) {
			for (int i = 0; i < (int) Math.ceil(halfKeys) - 1; i++) {
				nodeL.keys.add(node.keys.get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i < order; i++) {
				nodeR.keys.add(node.keys.get(i));
			}
			for (int i = 0; i < (int) Math.ceil(halfKeys); i++) {
				nodeL.children.add(node.children.get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i <= order; i++) {
				nodeR.children.add(node.children.get(i));
			}
			
			if (node.father == null) {
				nodeR.father = node;
				nodeL.father = node;
				int keyBak = node.keys.get((int) Math.ceil(halfKeys) - 1);
				node.keys.clear();
				node.keys.add(keyBak);
				node.children.add(1, nodeR);
				node.children.set(0, nodeL);
				
				result = node;
			} else {
				nodeR.father = node.father;
				nodeL.father = node.father;
				node.father.keys.add(node.keys.get((int) Math.ceil(halfKeys)));
				Collections.sort(node.father.keys);
				node.father.children.set(node.father.keys.indexOf(node.keys.get((int) Math.ceil(halfKeys))), nodeL);
				node.father.children.add(node.father.keys.indexOf(node.keys.get((int) Math.ceil(halfKeys))) + 1, nodeR);
				
				result = node.father;
			}
		}
		return result;
	}

	@Override
	public void insert(int key) {
		//Node node = new Node();
		//Collections.sort(node);
		if (contains(key)) {
			//System.out.println("key already exists");
			insert(key++);
		} else {
			insert(key, root);
		}
	}
	
	public void insert(int key, Node node) {
		
		if (node.keys.size() == order - 1) {
			Node splittedNodeFather = split(node);
			insert(key, splittedNodeFather);
		} else if(node.isLeaf()) {
			node.keys.add(key);
			Collections.sort(node.keys);

//			for (int i = node.keys.indexOf(key) +1 ; i < order; i++) {
//				
//			}
		} else {
			for (Integer integer: node.keys) {
				if (integer > key && node.children.get(node.keys.indexOf(integer)) != null) {
					insert(key, node.children.get(node.keys.indexOf(integer)));
					break;
				} else if (node.children.get(order) != null) {
					insert(key, node.children.get(order));
					break;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		BTree btree = new BTree(4);
		int x_i = 65;
		btree.insert(x_i);
		for (int i = 0; i < 20; i++) {
			x_i = (57 * x_i + 74) % 1001;
			btree.insert(x_i);
		}
		
	}
}
