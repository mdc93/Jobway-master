package cripel.jobway.utilities.fxutil;

import javafx.scene.control.Spinner;

/** Utilities for spinner */
public class DurationPicker{
	
	
	private DurationPicker() {}
	
	/**
	 * Link two spinner to increment value of the target and reset value of the
	 * caller
	 * @param spinnerBase Spinner that need their value reseted and incremented in the target
	 * @param spinnerTarget The target of spinnerBase
	 * @param maxValue the value the listener trigger at
	 */
	public static void linkSpinner(Spinner<Integer> spinnerBase, Spinner<Integer> spinnerTarget, int maxValue) {
		spinnerBase.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == maxValue) {
				spinnerBase.getValueFactory().setValue(0);
				spinnerTarget.increment();
			}
		});
	}

	/** Method to avoid string input in a Spinner
	 * @param spinner the spinner
	 * @param digit the number of digit allowed in the spinner
	 */
	public static void avoidString(Spinner<Integer> spinner, int digit) {
		spinner.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.matches("\\d*") || newValue.length() > digit) {
				spinner.getEditor().setText(oldValue);
			}

		});
		spinner.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue == null)
				spinner.getValueFactory().setValue(0);

		});
	}
}
