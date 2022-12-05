package cripel.jobway.exportexcel;

import java.util.LinkedHashSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * The class to create a header in an excel file TODO refactoring
 */
public class PersonExportHeader {

	/**
	 * Create a row of header in an excel file linked with {@link PersonExport}
	 * Better scrap this
	 *
	 * @param workbook the excel apache poi workbook
	 * @param row      the row where the header will be displayed
	 */
	public static int createHeader(XSSFWorkbook workbook, Row row) {
		int column = 0;
		LinkedHashSet<String> header = new LinkedHashSet<>();
		// Important Data
		header.add("id");
		header.add("Nom de famille");
		header.add("Prénom");
		header.add("Adresse");
		header.add("Code postale");
		header.add("Ville");
		header.add("Téléphone");
		header.add("Date de naissance");
		header.add("Sexe");
		header.add("Nationalité");
		header.add("Revenu");
		header.add("Date inscription Forem");
		header.add("Durée d'innocupation");
		header.add("Niveau d'éducation");
		header.add("Ménage");
		header.add("Nombre d'enfant à charge");
		header.add("Handicap reconnu");
		header.add("Autre difficultée");
		header.add("Date d'entrée");
		header.add("Date de sortie");
		header.add("Total d'heures");
		// Bonus Data
		header.add("Orientation");
		header.add("Note d'orientation");
		header.add("Réferent");
		header.add("NISS");
		header.add("E-mail");
		header.add("Date d'arrivée en Belgique");
		header.add("Pays d'origine");
		header.add("Situation sur le territoire");
		header.add("Identité du regroupant");
		header.add("Titre de séjour");
		header.add("N°Annexe/Autre");
		header.add("Date délivrance");
		header.add("Date Fin de validité");
		header.add("Nationalité belge obtenue");
		header.add("Etat Civil");
		header.add("Nationalité du regroupant");
		header.add("Nombre d'enfant");
		header.add("Nombre d'Adultes");
		header.add("Besoin de garde");
		header.add("Moyen de garde ISP");
		header.add("Inscription Mutuelle");
		header.add("Revenus");
		header.add("Travail");
		header.add("Inscription Forem");
		header.add("Remarques");
		header.add("Permis de conduire");
		header.add("Vehicule");
		header.add("Moyen de locomotion");
		header.add("Langue Maternelle");
		header.add("Langue de communication");
		header.add("Niveau de Français");
		header.add("Test de langue");
		header.add("Autre langue 1");
		header.add("Niveau Autre langue 1");
		header.add("Autre langue 2");
		header.add("Niveau Autre langue 2");
		header.add("Recherche active d'emploi");
		header.add("Secteur 1");
		header.add("Secteur 2");
		header.add("Secteur 3");
		header.add("Pourquoi pas en recherche");
		header.add("Disponibilité");
		header.add("Autre disponibilité");
		header.add("ok NewsLetter emploi");
		// TODO: remaining
		for (int i = 1; i < 4; i++) {
			String name = "XP PRO " + i + " ";
			header.add(name + "intitulé de fonction");
			header.add(name + "Durée");
			header.add(name + "Secteur");
			header.add(name + "Belgique?");
			header.add(name + "stage ou student");
			header.add(name + "Tâche 1");
			header.add(name + "Tâche 2");
			header.add(name + "Tâche 3");

		}

		for (String string : header) {
			PoiUtil.createCell(workbook, row, column++, string);
		}

		return column;
	}
}
