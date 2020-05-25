package pharmacie;

import java.util.Scanner;

class Pharmacie {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		Client[] clients = new Client[2];
		Medicament[] medicaments = new Medicament[2];

		clients[0] = new Client("Malfichu", 0.0);
		clients[1] = new Client("Palichon", 0.0);

		medicaments[0] = new Medicament("Aspiron", 20.40, 5);
		medicaments[1] = new Medicament("Rhinoplexil", 19.15, 5);

		int choix;

		do {
			choix = menu();

			switch (choix) {
			case 1:
				achat(clients, medicaments);
				break;
			case 2:
				approvisionnement(medicaments);
				break;
			case 3:
				affichage(clients, medicaments);
				break;
			case 4:
				quitter();
			}
		} while (choix < 4);
	}

	private static void quitter() {
		System.out.println("Programme terminé! ");
		System.exit(0);

	}

	private static void affichage(Client[] clients, Medicament[] medicaments) {
		System.out.println("Affichage des stocks");
		for (int i = 0; i < medicaments.length; i++) {
			System.out.println("Stock du médicament " + medicaments[i].getNom() + " :" + medicaments[i].getStock());
		}
		System.out.println("Affichage des crédits");
		for (int i = 0; i < clients.length; i++) {
			System.out.println("Stock du médicament " + clients[i].getNom() + " :" + clients[i].getCredit());
		}

	}

	private static void approvisionnement(Medicament[] medicaments) {
		System.out.println("Nom du medicament?:");
		Medicament medicament = lireMedicament(medicaments);
		System.out.println("Donner la Quantité :");
		int stock = scanner.nextInt();
		medicament.setStock(medicament.getStock() + stock);
	}

	private static void achat(Client[] clients, Medicament[] medicaments) {
		System.out.println("Nom du client?:");
		Client client = lireClient(clients);
		System.out.println(client);
		System.out.println("Nom du medicament?:");
		Medicament medicament = lireMedicament(medicaments);
		System.out.println("quel est le montant du paiement?");
		double price = Double.parseDouble(scanner.next());
		System.out.println("quelle est la quantité achetée?");
		int qty = scanner.nextInt();
		System.out.println(client);
		client.setCredit(
				new Double(
						client.getCredit() 
				+ (medicament.getPrix() *
						qty) - 
				price));
		if (medicament.getStock() >= qty) {
			medicament.setStock(
					medicament.getStock() - qty);
		} else {
			System.out.println("Quantité insuffisante");	 
		}
	}

	static int menu() {
		int choix = 0;
		System.out.println();
		System.out.println();
		System.out.println("1 : Achat de médicament");
		System.out.println("2 : Approvisionnement en  médicaments");
		System.out.println("3 : Etats des stocks et des crédits");
		System.out.println("4 : Quitter");
		while ((choix != 1) && (choix != 2) && (choix != 3) && (choix != 4)) {
			choix = scanner.nextInt();
		}
		return choix;
	}

	private static Client lireClient(Client[] clients) {
		String clientName = scanner.next();
		Client client = null;
		boolean res = false;
		for (int i = 0; i < clients.length; i++) {
			if (clientName.equals(clients[i].getNom())) {
				client = clients[i];
				res = true;
			}
		}
		if (res == false) { 
			System.out.println("Client inconnu. Veuilliez recommencer");
			lireClient(clients);
		}
		return client;
	}

	private static Medicament lireMedicament(Medicament[] medicaments) {
		String medicamentName = scanner.next();
		Medicament medicament = null;
		boolean res = false;
		for (int i = 0; i < medicaments.length; i++) {
			if (medicamentName.equals(medicaments[i].getNom())) {
				medicament = medicaments[i];
				res = true;
			}
		}
		if (res == false) {
			System.out.println("Medicament inconnu. Veuilliez recommencer");
			lireMedicament(medicaments);
		}
		return medicament;
	}
}