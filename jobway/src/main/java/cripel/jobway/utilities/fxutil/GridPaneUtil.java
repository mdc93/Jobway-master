package cripel.jobway.utilities.fxutil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

/**
 * The Utilities class for JavaFx GridPane
 *
 */
public class GridPaneUtil {
	
	private GridPaneUtil(){}
	/**
	 * Create an index of GridPane children coordinate
	 *
	 * @param gridPane the GridPane
	 * @return a 2 dimension array with children coordinate
	 */
	public static Node[][] mapGridPane(GridPane gridPane) {
		Node[][] gridPaneNodes = null;
		gridPaneNodes = new Node[gridPane.getRowCount()][gridPane.getColumnCount()];
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) != null)
				gridPaneNodes[GridPane.getRowIndex(node)][GridPane.getColumnIndex(node)] = node;
		}
		return gridPaneNodes;
	}

	/**
	 * Create a map with a GridPane children coordinate
	 *
	 * @param listGridPane
	 * @return Hash map with a key GridPane and value an array with children
	 *         coordinate
	 */
	public static Map<GridPane, Node[][]> mapListGridPane(Collection<GridPane> listGridPane) {
		Map<GridPane, Node[][]> map = new HashMap<>();
		for (GridPane gridPane : listGridPane) {
			map.put(gridPane, mapGridPane(gridPane));
		}
		return map;
	}

	/**
	 * Create a map with a GridPane children coordinate
	 *
	 * @param listNode list of Node
	 * @return Hash map with a key GridPane and value an array with children
	 *         coordinate
	 */
	public static Map<GridPane, Node[][]> mapListNodeGridPane(Collection<Node> listNode) {
		Map<GridPane, Node[][]> map = new HashMap<>();
		for (Node node : listNode) {
			if (node instanceof GridPane) {
				map.put((GridPane) node, mapGridPane((GridPane) node));
			}
		}
		return map;
	}

}
