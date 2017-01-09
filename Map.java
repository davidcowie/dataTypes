import java.util.Map.Entry;

public interface Map<K,V> {
	
	public V get(K key);
	public V put(K key, V value); // if already has same key // V will be old value if already there or null if not in there
	public V remove(K key);
	public int size();
	public boolean isEmpty();
	Iterable<Entry<K,V>> entrySet();
	
	//three sets of iterators 
	
	//could have a containsKey which is a boolean

}
