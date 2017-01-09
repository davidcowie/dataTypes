import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class HashTableSet<K> implements Set<K> {

	
	private TableSet<K>[] data; // each bucket is going to be an array of the tableset class
	private int size;
	// for compression function going to use the multiply add divide technique
	private int prime, scale, shift;
	
	@SuppressWarnings("unchecked")
	public HashTableSet(int tableSize) {
		data = (TableSet<K>[]) new TableSet[tableSize]; // need to cast because java generics are dumb
		prime = 109345121;
		Random r = new Random();
		scale = 1 + r.nextInt(prime - 1); // range: 1-(prime-1)
		shift = r.nextInt(prime);
		size = 0;
	}
	
	public HashTableSet() {
		this(17);
	}
	
	
	
	//find out which bucket it goes in then add it to the correct array
	//return value is did i put the element in the list-- only false if it is already in the set
	@Override
	public boolean add(K k) {
		//first need to figure out bucket
		int bucket = whichBucket(k);
		//if there isnt a set there
		if(data[bucket] == null){// so should definetly add it 
			data[bucket] = new TableSet<K>();
		}
		
		boolean b = data[bucket].add(k);
		if(b){
			size++;
		}
		// if load factor is too high then want to resize array
		if((float)(size)/data.length > 0.75){
			resize(data.length*2 + 1); // will make sure we always get an odd size array
		}
		return b;

	}
	

	private void resize(int newSize) {
		System.out.println("RESIZING");
		ArrayList<K> oldData = new ArrayList<>();
		for(K k : this){
			oldData.add(k);
		}
		data = (TableSet<K>[]) new TableSet[newSize];
		for(K k : oldData){
			add(k);
		}
	}

	private int whichBucket(K k){
		return ((k.hashCode()*scale + shift) % prime) % data.length;
	}
	
	@Override
	public boolean contains(K k) {
		int bucket = whichBucket(k);
		if(data[bucket] == null){
			return false;
		}
		return data[bucket].contains(k);
	}

	@Override
	public boolean remove(K k) {
		int bucket = whichBucket(k);
		if(data[bucket] == null){
			return false;
		}
		
		boolean b = data[bucket].remove(k);
		if(b){
			size--;
		}
		return b;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size ==0;
	}

	@Override
	public Iterator<K> iterator() {
		return new HashTableSetIterator<>();
	}
	
	private class HashTableSetIterator<K> implements Iterator<K>{

		private int bucket;
		private Iterator<K> bucketIterator;
		
		public HashTableSetIterator() {
			for(bucket =0;bucket<data.length;bucket++){
				if(data[bucket] != null){
					bucketIterator = (Iterator<K>) data[bucket].iterator();
					if(bucketIterator.hasNext()){
						return;
					}
				}
			}
			bucketIterator = null;
		}
		
		@Override
		public boolean hasNext() {
			return bucketIterator != null;
		}

		@Override
		public K next() {
			K ret = bucketIterator.next();
			if(bucketIterator.hasNext()){
				return ret;
			}
			// otherwise find a new bucket
			for(bucket = bucket + 1; bucket < data.length; bucket++){
				if(data[bucket] != null){
					bucketIterator = (Iterator<K>) data[bucket].iterator();
					if(bucketIterator.hasNext()){
						return ret;
					}
				}
			}
			bucketIterator = null;
			return ret;
		}
		
	}
	

}
