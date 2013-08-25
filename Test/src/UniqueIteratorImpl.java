import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;



public class UniqueIteratorImpl<E extends Number>  implements java.util.Iterator<E>{

	
	Set<E> set = new LinkedHashSet<E>();
	
    private Iterator<E> it ;
	public UniqueIteratorImpl(List<E> list){
		for(int i = 0;i< list.size(); i ++){
			E number = list.get(i);
			set.add(number);
		}
		it = set.iterator();	
	}
	
	@Override
	public boolean hasNext() {
		return it.hasNext();
	}

	@Override
	public E next() {
		return it.next();
	}

	@Override
	public void remove() {
		set.iterator().remove();
		
	}

	

}
