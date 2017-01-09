import java.util.Iterator;

public class DynamicArray<T> implements Iterable<T>{

	protected T[] data;
	protected int size;
	
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		
		data = (T[]) new Object[16];
		size = 0;
	}

	public void add(int i, T t){
		if(i<0 || i > size){
			throw new IndexOutOfBoundsException("i: " + i);
		}
		if(size == data.length){
			increaseCapacity();
		}
		for(int j = size;j>i;j--){
			data[j] = data[j-1];
		}
		data[i] = t;
		size++;
	}
	@SuppressWarnings("unchecked")
	protected void increaseCapacity() {
		T[] newData = (T[]) new Object[data.length*2];
		for(int i=0;i<size;i++){
			newData[i] = data[i];
		}
		data = newData;
	}
	public T remove(int i){
		checkIndex(i);

		T removedData = data[i];
		for(int j = i+1; j < size; j++){
			data[j-1] = data[j];
		}
		data[size-1] = null;
		size--;
		
		return removedData;
	}
	
	@SuppressWarnings("unchecked")
	public void clear(){
		data = (T[]) new Object[16];
		size = 0;
	}
	
	public void sort(){
		qsort(data,0,size-1);
	}
	
	@SuppressWarnings("unchecked")
	private void qsort(T[] data2, int lower, int upper) {
	        if (lower < upper) {
	            int i = lower, j = upper;
	            T x = data2[(i + j) / 2];

	            do {
	                while (((Comparable<T>) data2[i]).compareTo(x) < 0){ 
	                	i++;
	                }
	                while (((Comparable<T>) x).compareTo(data2[j]) < 0){
	                	j--;
	                }

	                if ( i <= j) {
	                    T tmp = data2[i];
	                    data2[i] = data2[j];
	                    data2[j] = tmp;
	                    i++;
	                    j--;
	                }

	            } while (i <= j);

	            qsort(data2, lower, j);
	            qsort(data2, i, upper);
	        }
	    }


	public void add(T t){
		add(size, t);
	}
	public void set(int i, T t){
		checkIndex(i);
		data[i] = t;
	}
	public T get(int i){ // throws IndexOutOfBoundsException
		checkIndex(i);
		return data[i];
	}
	
	protected void checkIndex(int i) throws IndexOutOfBoundsException{
		if(i >= size || i < 0){
			throw new IndexOutOfBoundsException("i: " + i);
		}
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public String toString(){
		if(size == 0){
			return "[]";
		}
		String s = "[";
		for(int i=0;i<size; i++){
			s += data[i]+",";
		}
		s = s.substring(0,s.length()-1);
		s+= "]";
		return s;
	}
	
	private class ArrayIterator implements Iterator<T>{

		private int index;
		private boolean canRemove;
		
		public ArrayIterator() {
			index = 0;
			canRemove = false;
		}
		
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public T next() {
			if(!hasNext()){
				throw new IndexOutOfBoundsException("There is no next. Good try kiddo");
			}
			canRemove = true;
			T t = DynamicArray.this.data[index];
			index++;
			return t;
		}
		@Override
		public void remove(){
			if(!canRemove){
				throw new IllegalStateException("STAPHHHHH");
			}
			
			DynamicArray.this.remove(index-1);
			canRemove = false;
			index--;
		}
		
		
		
	}

	@Override
	public Iterator<T> iterator() {
		return new ArrayIterator();
	}


}
