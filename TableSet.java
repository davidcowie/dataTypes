import java.util.ArrayList;
import java.util.Iterator;

public class TableSet<K> implements Set<K> {

	private ArrayList<K> data;
	public TableSet() {
		data = new ArrayList<>();
	}
	
	private int indexOf(K key){
		for(int i=0;i<data.size();i++){
			if(data.get(i).equals(key)){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public boolean add(K k) {
		int index = indexOf(k);
		if(index == -1){
			data.add(k);
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(K k) {
		return data.contains(k);
	}

	@Override
	public boolean remove(K k) {
		int index = indexOf(k);
		if(index == -1){
			return false;
		}
		data.remove(index);
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return data.isEmpty();
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return data.iterator();
	}
	
	public String toString(){
		String s = "[";
		for(K k : data){
			s+= k + ",";
		}
		s = s.substring(0,s.length()-1);
		return s;
	}

}
