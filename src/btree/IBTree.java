package btree;

/**
 * Interface für einen B-Baum
 * 
 * @author Andrea Papst
 */
public interface IBTree {
	
	/**
	 * F&uuml;gt den gegebenen Schl&uuml;ssel in den Baum ein.
	 * @param key einzufügender Schluessel
	 */
	public void insert(int key);

	/**
	 * Pr&uuml;ft, ob der gegebene Schl&uuml;ssel im Baum enthalten ist.
	 * @param key zu suchender Schlüssel
	 * @return Liefert true, wenn der Schl&uuml;ssel im Baum enthalten ist, sonst false.
	 */
	public boolean contains(int key);
	
//	Entfernen von Knoten ist noch nicht nötig.
//	public boolean remove(int key);
	
}