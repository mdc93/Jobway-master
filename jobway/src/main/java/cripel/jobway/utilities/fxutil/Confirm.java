package cripel.jobway.utilities.fxutil;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * The Utilities class confirm to create a popup yes/no when needed
 */
public class Confirm {

	/**
	 * Method to create a confirmation POPUP
	 *
	 * @param choice
	 * @return
	 */
	public boolean confirmPopUp(boolean choice) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("En cours");

		alert.setContentText("Voulez-vous effectuer cette action ? ");

		Optional<ButtonType> option = alert.showAndWait();

		if (option.get() == null || option.get() == ButtonType.CANCEL) {

			choice = false;

		} else if (option.get() == ButtonType.OK) {
			choice = true;
		}

		return choice;
	}

	public static boolean confirmSave() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Êtes-vous sûr de vouloir quitter?");
		alert.showAndWait();
		return alert.getResult() == ButtonType.OK;

	}

	public static boolean confirmQuit() {
		Alert alert = new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setContentText("Sauvegarde en cours, quitter la fenêtre?");
		alert.showAndWait();
		return alert.getResult() == ButtonType.YES;

	}

}
