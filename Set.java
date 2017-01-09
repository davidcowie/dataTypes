import java.util.Iterator;

public interface Set<K> extends Iterable<K>{

	boolean add(K k);
	boolean contains(K k);
	boolean remove(K k);
	
	int size();
	boolean isEmpty();
	Iterator<K> iterator();
}
