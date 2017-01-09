import java.util.ArrayList;
import java.util.Map.Entry;

public class TableMap<K,V> implements Map<K,V>{

	
	private static class MapEntry<K,V> implements Entry<K,V>{

		private K key;
		private V value;
		
		public MapEntry(K k, V v){
			key = k;
			value = v;
		}
		
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V arg0) {
			V old = value;
			value = arg0;
			return old;
		}
		
	}
	
	private ArrayList<Entry<K,V>> data;
	
	public TableMap() {
		data = new ArrayList<>();
	}
	private int indexOf(K key){
		for(int i =0;i<data.size();i++){
			if(data.get(i).getKey().equals(key)){
				return i;
			}
		}
		return -1;
	}
	
	
	@Override
	public V get(K key) {
		int index = indexOf(key);
		if(index == -1){
			return null;
		}
		return data.get(index).getValue();
	}

	@Override
	public V put(K key, V value) {
		int index = indexOf(key);
		if(index == -1){
			data.add(new MapEntry<K,V>(key,value));
			return null;
		}

		return data.get(index).setValue(value);
		
	}
	


	@Override
	public V remove(K key) {
		int index = indexOf(key);
		if(index == -1){
			return null;
		}
		
		V old = data.get(index).getValue();
		data.remove(index);
		return old;
	}

	@Override
	public int size() {
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		 return data.isEmpty();
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return data;
	}

	

	

}
