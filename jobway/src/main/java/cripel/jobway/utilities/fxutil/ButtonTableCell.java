package cripel.jobway.utilities.fxutil;

import java.util.function.Function;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/**
 * Custom javafx tablecell to add a button in a tablecell
 * https://stackoverflow.com/questions/29489366/how-to-add-button-in-javafx-table-view
 *
 * @param <S> the generic type
 */
public class ButtonTableCell<S> extends TableCell<S, Button> {

	/** The button. */
	private final Button button;

	/**
	 * Instantiates a new button table cell.
	 *
	 * @param label    the label displayed in the button
	 * @param style    css style class
	 * @param icon     ikonli literal to display an icon
	 * @param function the function
	 */
	public ButtonTableCell(String label, String style, String icon, Function<S, S> function) {

		this.button = new Button(label);
		this.button.getStyleClass().add(style);
		FontIcon fontIcon = new FontIcon();
		fontIcon.setIconLiteral(icon);
		fontIcon.setIconSize(20);
		this.button.setGraphic(fontIcon);
		this.button.setOnAction((ActionEvent event) -> 
			function.apply(getCurrentItem()));
	}

	/**
	 * Gets the current item.
	 *
	 * @return the current item
	 */
	public S getCurrentItem() {
		return getTableView().getItems().get(getIndex());
	}

	/**
	 * For table column.
	 *
	 * @param <S>      the generic type
	 * @param label    the label displayed in the button
	 * @param style    css style class
	 * @param icon     ikonli literal to display an icon
	 * @param function the function
	 * @return the callback
	 */
	public static <S> Callback<TableColumn<S, Button>, TableCell<S, Button>> forTableColumn(String label, String style,
			String icon, Function<S, S> function) {
		return param -> new ButtonTableCell<>(label, style, icon, function);
	}

	/**
	 * Update item.
	 *
	 * @param item  the item
	 * @param empty boolean if there is no item in cell
	 */
	@Override
	public void updateItem(Button item, boolean empty) {
		super.updateItem(item, empty);

		if (empty) {
			setGraphic(null);
		} else {
			setGraphic(button);
		}
	}
}
