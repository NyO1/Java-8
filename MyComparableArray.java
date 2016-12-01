import java.util.Comparator;

public class MyComparableArray<T extends Comparable<? super T>> implements Comparable<MyComparableArray<T>>
{
	private T[] array;
	
	private Comparator<MyComparableArray<T>> comparator = Comparator.comparing(MyComparableArray::length);
	
	public void setComparator(Comparator<MyComparableArray<T>> comparator)
	{
		this.comparator = comparator;
	}
	
	public MyComparableArray(T... array)
	{
		this.array = array;
	}

	@Override
	public int compareTo(MyComparableArray<T> arg0)
	{
		return comparator.compare(this, arg0);
	}
	
	public int length() { return array.length; }
	
	static public <S extends Comparable<? super S>> int compareWithArray(MyComparableArray<S> a, MyComparableArray<S> b)
	{
		S[] array = a.array;
		S[] array2 = b.array;
		int min = Math.min(array.length, array2.length);
		for (int k = 0; k < min; k++)
		{
			int v = array[k].compareTo(array2[k]);
			if (v != 0) return v;
		}
		
		return array.length-array2.length;
	}
	
	public static void main(String[] args)
	{
		Comparator<MyComparableArray<Integer>> comparator2 = MyComparableArray::compareWithArray;
		Comparator<MyComparableArray<Integer>> comparator3 = 
				comparator2.reversed();
		
		MyComparableArray<Integer> a1 = new MyComparableArray<>(2, 5, 10, 1, 100);
		MyComparableArray<Integer> a2 = new MyComparableArray<>(2, 5, 10, 1, 100, 5);
		MyComparableArray<Integer> a3 = new MyComparableArray<>(2, 4, 10, 1, 100);
		System.out.println(a1.compareTo(a2));
		System.out.println(a1.compareTo(a3));

		a1.setComparator(comparator2);
		
		System.out.println(a1.compareTo(a2));
		System.out.println(a1.compareTo(a3));

		a1.setComparator(comparator3);
		
		System.out.println(a1.compareTo(a2));
		System.out.println(a1.compareTo(a3));
	}
}
