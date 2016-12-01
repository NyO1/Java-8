package listalinkata;

import java.util.Iterator;

public class ListaLinkata implements Iterable<Integer>
{
	private Elemento first;
	
	public Iterator<Integer> iterator()
	{
		return new Iterator<Integer>() 
				{
					private Elemento current = first;
			
					@Override 
					public boolean hasNext()
					{
						return current != null;
					}
					
					public Integer next()
					{
						if (!hasNext()) return null;

						Integer v = current.val;
						current = current.next;
						return v;
					}
				};
	}
	
	static private class Elemento
	{
		private Integer val;
		private Elemento next;

		public Elemento(int val)
		{
			this.val = val;
		}
	}
	
	public void add(int k)
	{
		if (first == null) first = new Elemento(k);
		else
		{
			Elemento l = first;
			while(l.next != null) l = l.next;
			l.next = new Elemento(k);
		}
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();

		sb.append("[");
		
		Elemento e = first;
		while(e != null)
		{
			sb.append(e.val);
			if (e.next != null) sb.append(", ");
			e = e.next;
		}

		sb.append("]");
		
		return sb.toString();
	}
	
	public static void main(String[] args)
	{
		ListaLinkata l = new ListaLinkata();
		l.add(1);
		l.add(2);
		l.add(5);
		System.out.println(l);
	}
}
