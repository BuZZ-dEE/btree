package btree;

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
//	public Node father;
//	public Node child;
//	public Node nodeR;
//	public Node nodeL;
//	public ArrayList<Node> fullNodes;
	
	public BTree(int order) {
		this.root = new Node();
		this.order = order;
	}
	
	@Override
	public boolean contains(int key) {
		
		boolean exists = false;
		
		for (Integer integer: root.getKeys()) {
			if (integer == key) {
				exists = true;
				System.out.println(root.getKeys().indexOf(key));
			} else if (integer > key && root.getChildren().get(root.getKeys().indexOf(integer)) != null) {
				contains(key, root.getChildren().get(root.getKeys().indexOf(integer)));
			} else if (root.getChildren().get(order) != null) {
				contains(key, root.getChildren().get(order));
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
		
		for (Integer integer: node.getKeys()) {
			if (integer == key) {
				exists = true;
				System.out.println(node.getKeys().indexOf(key));
			} else if (integer > key && node.getChildren().get(node.getKeys().indexOf(integer)) != null) {
				contains(key, node.getChildren().get(node.getKeys().indexOf(integer)));
			} else if (node.getChildren().get(order) != null) {
				contains(key, node.getChildren().get(order));
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
		
		Node nodeL = new Node();
		Node nodeR = new Node();
		
		if (node.getKeys().size() == order-1) {
			for (int i = 0; i < (int) Math.ceil(halfKeys) - 1; i++) {
				nodeL.getKeys().add(node.getKeys().get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i < order; i++) {
				nodeR.getKeys().add(node.getKeys().get(i));
			}
			for (int i = 0; i < (int) Math.ceil(halfKeys); i++) {
				nodeL.getChildren().add(node.getChildren().get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i <= order; i++) {
				nodeR.getChildren().add(node.getChildren().get(i));
			}
			
			if (node.getFather() == null) {
				nodeR.setFather(node);
				nodeL.setFather(node);
				int keyBak = node.getKeys().get((int) Math.ceil(halfKeys) - 1);
				node.getKeys().clear();
				node.getKeys().add(keyBak);
				node.getChildren().add(1, nodeR);
				node.getChildren().set(0, nodeL);
				
				result = node;
			} else {
				nodeR.setFather(node.getFather());
				nodeL.setFather(node.getFather());
				node.getFather().getKeys().add(node.getKeys().get((int) Math.ceil(halfKeys)));
				Collections.sort(node.getFather().getKeys());
				node.getFather().getChildren().set(node.getFather().getKeys().indexOf(node.getKeys().get((int) Math.ceil(halfKeys))), nodeL);
				node.getFather().getChildren().add(node.getFather().getKeys().indexOf(node.getKeys().get((int) Math.ceil(halfKeys))) + 1, nodeR);
				
				result = node.getFather();
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
		
		if (node.getKeys().size() == order - 1) {
			Node splittedNodeFather = split(node);
			insert(key, splittedNodeFather);
		} else if(node.isLeaf()) {
			node.getKeys().add(key);
			Collections.sort(node.getKeys());

//			for (int i = node.getKeys().indexOf(key) +1 ; i < order; i++) {
//				
//			}
		} else {
			for (Integer integer: node.getKeys()) {
				if (integer > key && node.getChildren().get(node.getKeys().indexOf(integer)) != null) {
					insert(key, node.getChildren().get(node.getKeys().indexOf(integer)));
					break;
				} else if (node.getChildren().get(order) != null) {
					insert(key, node.getChildren().get(order));
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
