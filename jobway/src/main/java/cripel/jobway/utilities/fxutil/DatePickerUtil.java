package cripel.jobway.utilities.fxutil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

/**
 * Utilities class for {@link DatePicker}
 */
public class DatePickerUtil {
	private DatePickerUtil() {}
	/**
	 * Add a listeners to multiple {@link DatePicker} use
	 * {@link #validDate(DatePicker)}
	 *
	 * @param datePickers DatePicker target of the listeners
	 */
	public static void listenerValidDate(DatePicker... datePickers) {
		for (DatePicker datePicker : datePickers) {
			validDate(datePicker);
		}
	}

	/**
	 * Add a listener to a {@link DatePicker#focusedProperty()} to check if the
	 * value edited by the user is a valid date
	 *
	 * @param datePicker {@link DatePicker}
	 */
	private static void validDate(DatePicker datePicker) {
		datePicker.focusedProperty().addListener((obs, oldValue, newValue) -> {
			if (Boolean.FALSE.equals(newValue)) {
				try {
					datePicker.setValue(datePicker.getConverter().fromString(datePicker.getEditor().getText()));
				} catch (DateTimeParseException e) {
					datePicker.getEditor().setText(datePicker.getConverter().toString(datePicker.getValue()));
				}
			}
		});
	}

	/**
	 * Method to add a listener to disable the origin's DatePicker value previous
	 * days of the target DatePicker
	 *
	 * @param origin the DatePicker
	 * @param target
	 */
	public static void setLimit(DatePicker origin, DatePicker target) {
		final Callback<DatePicker, DateCell> dayCellFactory = datePicker -> 
			new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					if (origin.getValue() != null && item.isBefore(origin.getValue())) {
						setDisable(true);
						setStyle("-fx-base: grey;");
					}
			}

		};

		origin.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && target.getValue() != null && newValue.isAfter(target.getValue())) {
				target.setValue(newValue);

			} else if (newValue != null) {
				target.setDayCellFactory(dayCellFactory);
				target.setDisable(false);
				if (target.getValue() == null) {
					target.setValue(origin.getValue());
				}
			} else {
				target.setDisable(true);
				target.setValue(null);
			}
		});

		target.valueProperty().addListener((obs, oldValue, newValue) -> {
			if (newValue != null && newValue.isBefore(origin.getValue())) {
				target.setValue(oldValue);
			}
		});

	}
}
