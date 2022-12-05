module cripel.jobway {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires org.controlsfx.controls;
	requires java.sql;
	requires java.naming;
	requires javafx.base;
	requires org.slf4j;
	requires spring.security.crypto;
	requires org.kordamp.ikonli.javafx;
	requires org.kordamp.ikonli.bootstrapicons;
	requires org.kordamp.ikonli.fontawesome5;
	requires org.apache.poi.poi;
	requires org.apache.poi.ooxml;
	requires org.apache.commons.collections4;
	requires org.apache.commons.lang3;

	opens cripel.jobway.utilities to org.hibernate.orm.core, javafx.base;
	opens cripel.jobway to javafx.base, javafx.graphics, javafx.fxml;
	opens cripel.jobway.ui to javafx.base, javafx.graphics, javafx.fxml, org.kordamp.ikonli.javafx;
	opens cripel.jobway.ui.forms to javafx.base, javafx.graphics, javafx.fxml;
	opens cripel.jobway.ui.tablemngmt to javafx.base, javafx.graphics, javafx.fxml;
	opens cripel.jobway.ui.login to javafx.base, javafx.graphics, javafx.fxml;
	opens cripel.jobway.ui.event to javafx.base, javafx.graphics, javafx.fxml;
	opens cripel.jobway.model to org.hibernate.orm.core, javafx.base;
	opens cripel.jobway.dao to org.hibernate.orm.core;
}