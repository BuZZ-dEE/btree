package btree;

/**
 * Die Implementierung eines B-Baums f&uuml;r Integerwerte mit den Operationen
 * Einf&uuml;gen und Suchen
 * 
 * @author BuZZ-dEE
 * @version 2010-11-01
 *
 */
public class BTree implements IBTree {

	public int order = 4;
	public Node root;
	public Node father;
	public Node child;
	public Node nodeR;
	public Node nodeL;
	public BTree btree;
	
	@Override
	public boolean contains(int key) {
		
		boolean exists = false;
		
		for (Integer integer: root.keys) {
			if (integer == key) {
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
	
	public void split(Node node) {
		
		double halfKeys = (double) (order-1.0) / 2.0;
		
		if (node.keys.size() == order-1) {
			for (int i = 0; i < (int) Math.ceil(halfKeys); i++) {
				nodeR.keys.add(node.keys.get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i < order; i++) {
				nodeL.keys.add(node.keys.get(i));
			}
			for (int i = 0; i < (int) Math.ceil(halfKeys) + 1; i++) {
				nodeR.children.add(node.children.get(i));
			}
			for (int i = (int) Math.ceil(halfKeys) + 1; i <= order; i++) {
				nodeL.children.add(node.children.get(i));
			}
			nodeR.father = ;
			nodeL.father = ;
		}
	}

	@Override
	public void insert(int key) {
		//Node node = new Node();
		//Collections.sort(node);
		if (contains(key)) {
			//System.out.println("key already exists");
			key++;
		} else {
			
		}
	}
}
