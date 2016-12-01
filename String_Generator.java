import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GeneraStringhe
{
	private Set<Character> chars;
	
	public GeneraStringhe(Collection<Character> chars)
	{
		this.chars = new HashSet<>(chars);
	}
	
	public Set<String> genera(int k)
	{
		Set<String> stringheGenerate = new HashSet<>();
		genera(k, "", stringheGenerate);
		return stringheGenerate;
	}
	
	private void genera(int k, String s, Set<String> stringhe)
	{
		if (k == 0)
		{
			stringhe.add(s);
			return;
		}
		
		for (char c : chars)
			genera(k-1, s+c, stringhe);
	}
	

	
	public Set<String> generaMenoEfficiente(int k)
	{
		return generaMenoEfficiente(k, "");
	}
	
	private Set<String> generaMenoEfficiente(int k, String s)
	{
		if (k == 0)
			return Collections.singleton(s);
		
		Set<String> stringhe = new HashSet<>();
		for (char c : chars)
			stringhe.addAll(generaMenoEfficiente(k-1, s+c));
		return stringhe;
	}

	
	public static void main(String[] args)
	{
		System.out.println(new GeneraStringhe(
				Arrays.asList('a', 'b', 'c')
				).generaMenoEfficiente(2));
		
	}
}
