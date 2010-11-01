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
	public Node child;
	public BTree btree;
	
	@Override
	public boolean contains(int key) {
		
		boolean exists = false;
		
		for (Integer integer: root.keys) {
			if (integer == key) {
				System.out.println(root.keys.indexOf(key));
			} else if (integer > key && root.children.get(root.keys.indexOf(key)) != null) {
				contains(key, root.children.get(root.keys.indexOf(key)));
			} else if (root.children.get(root.keys.indexOf(key+1)) != null) {
				contains(key, root.children.get(root.keys.indexOf(key+1)));
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
			} else if (integer > key && root.children.get(root.keys.indexOf(key)) != null) {
				contains(key, node.children.get(node.keys.indexOf(key)));
			} else if (root.children.get(root.keys.indexOf(key+1)) != null) {
				contains(key, node.children.get(node.keys.indexOf(key+1)));
			} else {
				System.out.println("key not found");
			}
		}
		
		return exists;
	}

	@Override
	public void insert(int key) {
		//Node node = new Node();
		//Collections.sort(node);
	}
}
