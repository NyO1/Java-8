import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class EsercizioRistorante
{
	public enum TipoRistorante
	{
		PIZZERIA,
		RISTO,
		BISTRO,
		VEGETARIANO
	}

	static public class Ristorante
	{
		private String nome;
		private TipoRistorante tipo;
		private int coperti;
		
		public Ristorante(String nome, TipoRistorante tipo, int coperti)
		{
			this.nome = nome; this.coperti = coperti; this.tipo = tipo;
		}
		
		public String getNome() { return nome; }
		public TipoRistorante getTipo() { return tipo; }
		public int getCoperti() { return coperti; }

		@Override
		public String toString() { return nome+":"+tipo+":"+coperti; }
	}
	
	public static void main(String[] args)
	{
		List<Ristorante> risto = Arrays.asList(
				new Ristorante("La pergola", TipoRistorante.RISTO, 55),
				new Ristorante("L'etico", TipoRistorante.PIZZERIA, 25),
				new Ristorante("Da Rossi", TipoRistorante.RISTO, 47),
				new Ristorante("Da Gigi", TipoRistorante.PIZZERIA, 42),
				new Ristorante("Giggetto", TipoRistorante.PIZZERIA, 80),
				new Ristorante("Da Ivo", TipoRistorante.PIZZERIA, 150),
				new Ristorante("Romolo e Luigi", TipoRistorante.PIZZERIA, 50),
				new Ristorante("La terrazza", TipoRistorante.RISTO, 40)
				);
		
		risto.stream().sorted(Comparator.comparing(Ristorante::getCoperti))
					.forEach(System.out::println);
	}
}
