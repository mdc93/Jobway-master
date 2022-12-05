package cripel.jobway.utilities;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class to log and show message for Unhandled Exception
 *
 */
public class CustomUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		logger.error("Uncaught Exception in thread " + t.getName(), e);
		if (Platform.isFxApplicationThread()) {
			Platform.runLater(()->showError(e));
				
		} else {
			Platform.exit();
			System.exit(1);
		}
	}

	/**
	 * SHow error message in an {@link Alert}
	 *
	 * @param e Exception
	 */
	public void showError(Throwable e) {
		Alert alert = new Alert(AlertType.ERROR, "Erreur");
		alert.setHeaderText("Une erreur inattendue s'est produite");
		alert.setContentText(ExceptionUtils.getRootCauseMessage(e));
		alert.showAndWait();
		Platform.exit();
		System.exit(1);

	}

}
