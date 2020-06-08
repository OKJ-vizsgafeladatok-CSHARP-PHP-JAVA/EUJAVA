import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EuMain {

	public static List<Eu> fajlBeolvas() {
		List<Eu> euLista = new ArrayList<>();

		try {

			List<String> sorok = Files.readAllLines(Paths.get("EUcsatlakozas.txt"));
			for (String sor : sorok.subList(0, sorok.size())) {
				String[] split = sor.split(";");

				Eu u = new Eu((split[0]), LocalDate.parse(split[1], DateTimeFormatter.ofPattern(("yyyy.MM.dd"))));
				euLista.add(u);

			}

		} catch (IOException ex) {

			System.err.println("Nem sikerült a fájl beolvasása!" + ex);

		}

		return euLista;

	}

	public static void main(String[] args) {

		List<Eu> euLista = fajlBeolvas();

		System.out.println("2.feladat: teljlesítve beolvasva!");

		for (Eu Europa : euLista) {

			System.out.println(Europa);

		}

		int AllamokSzama = euLista.size();
		System.out.println("\n3.feladat: Az Eu tagállamainak száma: " + AllamokSzama + " db");

		LocalDate csatlakozas2007 = LocalDate.of(2007, 01, 01); // 2007-01-01

		String legutoljaraCsatlakozottOrszag = "";
		boolean voltEMajus = false;
		LocalDate csatl = LocalDate.now();
		int Szamlalo = 0;

		for (Eu Europa : euLista) {

			if (Europa.getCsatlakozas_eve().equals(csatlakozas2007)) {
				Szamlalo++;
			}

			if (Europa.getOrszag_nev().equals("Magyarország")) {
				csatl = Europa.getCsatlakozas_eve();

			}

			if (Europa.getCsatlakozas_eve().getMonth().equals(Month.MAY)) {
				voltEMajus = true;
			}
		}

		System.out.println("4.feladat: " + Szamlalo + " db ország csatlakozott 2007-ben");

		System.out.println("5.feladat: " + csatl);

		if (voltEMajus) {
			System.out.println("Volt májusban csatlakozás!");
		} else {
			System.out.println("Nem volt májusban csatlakozás!");
		}
		
		LocalDate maxDate = euLista.stream(x->x.get Comparator.comparing( LocalDate::toEpochDay ) )
                .get();
		
		System.out.println("6. feladat: " + legutoljaraCsatlakozottOrszag);
	}

}
