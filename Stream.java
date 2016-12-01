import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

public class EserciziStream
{
	private int getNumeroCaratteriDiversiJava7(String s)
	{
		Set<Character> set = new HashSet<>();
		for (int k = 0; k < s.length(); k++)
			set.add(s.charAt(k));
		return set.size();
	}
	
	private long getNumeroCaratteriDiversiJava8(String s)
	{
		// versione ideale da preferire
		return s.chars().distinct().count();
		
		// versione con split("") -> String[]
		// return Arrays.stream(s.split("")).distinct().count();
	}

	public List<String> getPrime3ConVincoli(List<String> lista)
	{
		return 
				lista.stream().filter(s 
						-> s.length() >= 4 
							&& getNumeroCaratteriDiversiJava8(s) >= 2)
					.limit(3).collect(toList());
	}
	
	public Set<Integer> getLunghezze(List<String> lista)
	{
		return lista.stream().map(String::length).collect(toCollection(() -> new TreeSet<>()));
	}
	
	public Map<Integer, List<String>> getStringhePerLunghezza(List<String> stringhe)
	{
		return stringhe.stream().collect(groupingBy(String::length, 
					TreeMap::new, 
					toList()));
	}
	
	public static void main(String[] args)
	{
		List<String> lista = Arrays.asList("abcd", "aaaaa", "xa", "aabb", "afejoerkoekro");
		
		EserciziStream e = new EserciziStream();
		List<String> l = e.getPrime3ConVincoli(lista);
		Map<Integer, List<String>> m = e.getStringhePerLunghezza(lista);
		
		System.out.println(l);
		System.out.println(m);
	}
}
