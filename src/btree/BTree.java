package btree;

import java.util.Collections;

/**
 * Die Implementierung eines B-Baums f&uuml;r Integerwerte mit den Operationen
 * Einf&uuml;gen und Suchen
 * 
 * @author Sebastian Schlatow
 * @version 2010-10-27
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
//		Iterator<Integer> iter = root.iterator(); 
//		
//		for (Integer integer: root) {
//			if (root.contains(key) && integer.intValue() == key) {
//				System.out.println(root.indexOf(integer));
//			} else {
//				if (iter.next() > key) {
//					
//				}
//			}
//		}
		
		if (root.indexOf(key) >= 0) {
			System.out.println(root.indexOf(key));
		} else {
			for (Integer integer: root) {
				if (integer > key) {
					child = root.getChildren().get(root.indexOf(key));
					child.contains(key);
				}
			}
		}
		
		return exists;
	}

	@Override
	public void insert(int key) {
		//Node node = new Node();
		Collections.sort(node);
	}

}
