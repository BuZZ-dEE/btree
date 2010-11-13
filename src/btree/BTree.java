package btree;

import java.util.Collections;

/**
 * Die Implementierung eines B-Baums f&uuml;r Integerwerte mit den Operationen
 * Einf&uuml;gen und Suchen
 * 
 * @author BuZZ-dEE
 * @version 2010-11-12
 * 
 */
public class BTree implements IBTree {

	public int order;
	public Node root;

	/**
	 * Einen B-Baum einer bestimmten Ordnung erstellen, beginnend mit dem Wurzelknoten.
	 * Die Ordung bezeichnet in diesem Fall den maximalen Verzweigungsgrad eines Knotens.
	 * Damit kann ein Knoten maximal "Ordnung - 1" Schl&uuml;ssel besitzen.
	 * 
	 * @param order, die Ordnung des B-Baumes
	 */
	public BTree(int order) {
		this.order = order;
		this.root = new Node(order);
	}

	@Override
	public boolean contains(int key) {
		
		boolean exists = false;
		exists = contains(key, root);
		return exists;
	}

	/**
	 * Pr&uuml;ft, ob der gegebene Schl&uuml;ssel im Baum enthalten ist.
	 * 
	 * @param key
	 *            , der Schl&uuml;ssel der immernoch gesucht wird
	 * @param node
	 *            , der Konten in dem weiter nach dem Schl&uuml;ssel gesucht
	 *            wird
	 * @return Liefert true, wenn der Schl&uuml;ssel im Baum enthalten ist,
	 *         sonst false.
	 */
	public boolean contains(int key, Node node) {

		boolean exists = false;

		if (node.equals(null)) {
			System.out.println("no keys");
		} else {
			for (Integer integer : node.getKeys()) {
				if (integer == key) {
					exists = true;
					System.out.println(node.getKeys().indexOf(key));
					break;
				} else if (integer > key && !node.isLeaf()/* node.getChildren().get(node.getKeys().indexOf(integer)) != null */) {
					contains(key, node.getChildren().get(node.getKeys().indexOf(integer)));
					break;
				} else if (node.getKeys().get(node.getKeys().size() - 1) < key && !node.isLeaf()/* node.getChildren().get(node.getKeys().size()) != null */) {
					contains(key, node.getChildren().get(node.getKeys().size()));
				} else {
					System.out.println("key not found");
				}
			}
		}

		return exists;
	}

	/**
	 * Die Methode teilt einen Knoten in zwei Knoten.
	 * 
	 * @param node
	 *            , der geteilt werden soll
	 * @return der Vaterknoten der des in zwei Knoten aufgeteilten Knoten
	 */
	public Node split(Node node) {

		Node result = null;
		double halfKeys = (double) (order - 1.0) / 2.0;
		int keyUp = node.getKeys().get((int) Math.ceil(halfKeys) - 1);

		Node nodeL = new Node(order);
		Node nodeR = new Node(order);

		if (node.getKeys().size() == order - 1) {
			for (int i = 0; i < (int) Math.ceil(halfKeys) - 1; i++) {
				nodeL.getKeys().add(node.getKeys().get(i));
			}
			for (int i = (int) Math.ceil(halfKeys); i < order - 1; i++) {
				nodeR.getKeys().add(node.getKeys().get(i));
			}
			for (int i = 0; i < (int) Math.ceil((node.getChildren().size() - 1) / 2); i++) {
				nodeL.getChildren().add(node.getChildren().get(i));
			}
			for (int i = (int) Math.ceil((node.getChildren().size() - 1) / 2); i < node.getChildren().size(); i++) {
				nodeR.getChildren().add(node.getChildren().get(i));
			}

			if (node.getFather() == null) {
				nodeL.setFather(node);
				nodeR.setFather(node);
				node.getKeys().clear();
				node.getKeys().add(keyUp);
				node.getChildren().clear();
				node.getChildren().add(nodeL);
				node.getChildren().add(nodeR);

				result = node;
			} else {
				nodeL.setFather(node.getFather());
				nodeR.setFather(node.getFather());
				node.getFather().getKeys().add(keyUp);
				Collections.sort(node.getFather().getKeys());
				node.getFather().getChildren().set(node.getFather().getKeys().indexOf(node.getKeys().get((int) Math.ceil(halfKeys) - 1)), nodeL);
				System.out.println("father-children-size: " + node.getFather().getChildren().size());
				System.out.println("father-keys-size: " + node.getFather().getKeys().size());
				System.out.println("node-keys-size: " + node.getKeys().size());
				System.out.println("father-keys-index: " + node.getFather().getKeys().indexOf(node.getKeys().get((int) Math.ceil(halfKeys) - 1)) + 1);
				node.getFather().getChildren().add(node.getFather().getKeys().indexOf(node.getKeys().get((int) Math.ceil(halfKeys) - 1)) + 1, nodeR);

				result = node.getFather();
			}
		}
		return result;
	}

	@Override
	public void insert(int key) {

//		if (contains(key)) {
//			System.out.println("key already exists");
//			insert(key++);
//		} else {
			insert(key, root);
//		}
	}

	/**
	 * F&uuml;gt den gegebenen Schl&uuml;ssel in den Baum ein.
	 * 
	 * @param key, der Schl&uuml;ssel der in den Baum eingef&uuml;gt werden soll.
	 * @param node, der Knoten f&uuml;r den gepr&uuml;ft wird, ob das Einf&uuml;gen in diesen Knoten erlaubt ist.
	 */
	public void insert(int key, Node node) {

		if (node.getKeys().size() == order - 1) {
			insert(key, split(node));
		} else if (node.isLeaf()) {
			node.getKeys().add(key);
			Collections.sort(node.getKeys());
		} else {
			System.out.println("keysize: " + node.getKeys().size());
			System.out.println("childrensize: " + node.getChildren().size());
			System.out.println("key: " + key);
			System.out.println("letzter schlüssel: " + node.getKeys().get(node.getKeys().size() - 1));
			if (node.getKeys().get(node.getKeys().size() - 1) < key) {
				insert(key, node.getChildren().get(node.getKeys().size()));
			} else {
				for (Integer integer : node.getKeys()) {
					if (integer > key) {
						insert(key, node.getChildren().get(node.getKeys().indexOf(integer)));
						break;
					}
				}
			}
		}
	}

	/**
	 * Die Main-Methode erstellt einen neuen B-Baum und f&uuml;gt Werte nach einem 
	 * bestimmten Schema in den B-Baum ein.
	 * 
	 * @param args, nicht in Benutzung
	 */
	public static void main(String[] args) {
		BTree btree = new BTree(4);
		int x_i = 65;
		btree.insert(x_i);
		for (int i = 0; i < 20; i++) {
			x_i = (57 * x_i + 74) % 1001;
			btree.insert(x_i);
		}
		System.out.println("Ende");
	}
}
