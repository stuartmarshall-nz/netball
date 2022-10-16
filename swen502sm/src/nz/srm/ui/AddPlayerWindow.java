package nz.srm.ui;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class AddPlayerWindow extends Stage {

	public AddPlayerWindow() {
		super();
		super.setTitle("Add Player");
		this.initModality(Modality.APPLICATION_MODAL);
		Button add = new Button("Add");
		Button close = new Button("Close");
		
		close.setOnAction(event -> {
			super.close();
		});
		
		Pane root = new FlowPane(Orientation.VERTICAL);
		root.getChildren().addAll(close, add);
		Scene scene = new Scene(root);
		super.setScene(scene);
		super.show();
	}

}
