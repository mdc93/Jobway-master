package cripel.jobway.ui.event;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class EventManagerTransition {
	
	private SequentialTransition sequentialTransitionTable;
	private SequentialTransition sequentialTransitionVBox;

	
	public void addEventAnimation(TableView<?> tableView,VBox vBox) {
		TranslateTransition tableTranslate =new TranslateTransition(Duration.millis(250),tableView);
		tableTranslate.setFromX(120);
		tableTranslate.setToX(0);
		ScaleTransition scaleTable = new ScaleTransition(Duration.millis(250),tableView);
		scaleTable.setFromX(1.5);
		scaleTable.setToX(1.0);
		ScaleTransition scaleVBox = new ScaleTransition(Duration.millis(250),vBox);
		scaleVBox.setFromX(0);
		scaleVBox.setToX(1);
		TranslateTransition translateVBox =new TranslateTransition(Duration.millis(250),vBox);
		translateVBox.setFromX(+100);
		translateVBox.setToX(0);
		
		this.sequentialTransitionTable=new SequentialTransition();
		sequentialTransitionTable.setAutoReverse(true);
		sequentialTransitionTable.setCycleCount(1);
		sequentialTransitionTable.getChildren().addAll(scaleTable,tableTranslate);
		
		this.sequentialTransitionVBox = new SequentialTransition();
		sequentialTransitionVBox.setAutoReverse(true);
		sequentialTransitionVBox.getChildren().addAll(scaleVBox,translateVBox);
		sequentialTransitionVBox.setCycleCount(1);
	}
	
	public void startAddEventAnimation() {
		sequentialTransitionTable.play();
		sequentialTransitionVBox.play();
		
		
	}

}
