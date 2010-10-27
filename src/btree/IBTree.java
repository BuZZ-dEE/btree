package btree;

/**
 * Interface für einen B-Baum
 * 
 * @author Andrea Papst
 */
public interface IBTree {
	
	/**
	 * Fügt den gegebenen Schluessel in den Baum ein.
	 * @param key einzufügender Schluessel
	 */
	public void insert(int key);

	/**
	 * Prüft, ob der gegebene Schlüssel im Baum enthalten ist.
	 * @param key zu suchender Schlüssel
	 * @return Liefert true, wenn der Schlüssel im Baum enthalten ist, sonst false.
	 */
	public boolean contains(int key);
	
//	Entfernen von Knoten ist noch nicht nötig.
//	public boolean remove(int key);
	
}