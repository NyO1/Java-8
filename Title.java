import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class Titolo
{
	public enum Allineamento { CX, SX, DX }
	private Allineamento allineamento;
	private List<Riga> righe;
	
	public Titolo(Allineamento a) { this(a, new ArrayList<>()); }
	public Titolo(Allineamento a, List<Riga> righe) { allineamento = a; this.righe = righe; }
	public void add(Riga r) { righe.add(r); }
	public boolean isCentered() { return allineamento == Allineamento.CX; }
	@Override public String toString() { return righe.toString(); }
	public Allineamento getAllineamento() { return allineamento; }
	public List<Riga> getRighe() { return new ArrayList<>(righe); }
	
	static public class Riga
	{
		private String riga;
		private int numero;
		
		public Riga(String riga, int numero) { this.riga = riga; this.numero = numero; }
		public Riga(String riga) { this(riga, -1); }
		@Override public String toString() { return (numero == -1 ? "" : numero+": ")+riga; }
	}
	
	public static void main(String[] args)
	{
		List<Titolo> lista = asList(
				new Titolo(Allineamento.CX, asList(new Riga("bc"), new Riga("def"))),
				new Titolo(Allineamento.CX, asList(new Riga("abc"), new Riga("def"))),
				new Titolo(Allineamento.DX, asList(new Riga("abc"), new Riga("def"))));

		// l'insieme dei primi 5 titoli con al più una riga
		lista.stream().filter(t -> t.getRighe().size() <= 1).limit(5).collect(toSet());
		
		// lista dei titoli centrati e in ordine alfabetico
		// con riferimenti a metodi
		List<Titolo> titoli = lista.stream()
				.filter(Titolo::isCentered)
				.sorted(Comparator.comparing(Titolo::toString))
				.collect(toList());
		// senza riferimenti a metodi
		titoli = lista.stream()
				.filter(t -> t.isCentered())
				.sorted(Comparator.comparing(t -> t.toString()))
				.collect(toList());
		
		// mappa dei titoli per allineamento
		// con riferimenti a metodi
		Map<Allineamento, List<Titolo>> titoliConAll = lista.stream()
				.collect(groupingBy(Titolo::getAllineamento));
		// senza riferimenti a metodi
		// ...

		// mappa da allineamento a insieme di titoli
		// con riferimenti a metodi
		Map<Allineamento, Set<Titolo>> titoliConAll2 = lista.stream()
				.collect(groupingBy(Titolo::getAllineamento, toSet()));
		// senza riferimenti a metodi
		// ...

		// mappa dei titoli da allineamento alla lista delle stringhe dei titoli
		// con riferimenti a metodi
		Map<Allineamento, List<String>> titoliConAllStringaTitol =
				lista.stream()
				.collect(groupingBy(Titolo::getAllineamento,
						mapping(Titolo::toString, toList())));
		// senza riferimenti a metodi
		// ...
		
		// mappa dei titoli da allineamento alla concatenazione delle stringhe dei titoli
		// con riferimenti a metodi
		Map<Allineamento, String> titoliConAllStringaTitol2 =
				lista.stream()
				.collect(groupingBy(Titolo::getAllineamento,
						mapping(Titolo::toString, joining(","))));
		// senza riferimenti a metodi
		// ...

		// insieme delle righe dei titoli nella lista sotto forma di stringhe
		// con riferimenti a metodi
		Set<String> set = 
				lista.stream().map(Titolo::getRighe) // Stream<List<Riga>>
				.flatMap(List::stream) // Stream<Riga>
				.map(Riga::toString) // Stream<String>
				.collect(toSet());
		// insieme delle righe dei titoli nella lista sotto forma di stringhe
		// senza riferimenti a metodi
		Set<String> set2 = 
				lista.stream().flatMap(t -> t.getRighe().stream()) // Stream<Riga>
				.map(r -> r.toString()) // Stream<String>
				.collect(toSet());

		// mappa conteggi delle parole
		// con riferimenti a metodi e usando groupingBy
		Map<String, Long> m = 
				set.stream()
						.map(r -> r.split(" ")) // Stream<String[]>
						.flatMap(Arrays::stream) // Stream<String>
						.collect(groupingBy(Function.identity(),
								counting()));
		
		// mappa conteggi delle parole
		// con riferimenti a metodi e usando toMap
		Map<String, Integer> m2 = 
				set.stream()
						.map(r -> r.split(" "))
						.flatMap(Arrays::stream)
						.collect(toMap(Function.identity(), 
								r -> 1, (i, r) -> i+1));
	}
}
