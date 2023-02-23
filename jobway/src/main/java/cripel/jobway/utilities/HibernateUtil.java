package cripel.jobway.utilities;

import java.io.File;
import java.util.Properties;

import cripel.jobway.ui.login.Login;
import org.controlsfx.control.Notifications;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Utilities class to manage hibernate
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory = buildSessionFactory();

	private static Logger logger = LoggerFactory.getLogger(SessionFactory.class);
	

	/**
	 * Method to build a session with hibernate
	 *
	 * @return the sessionFactory
	 */
	private static SessionFactory buildSessionFactory() {

		SessionFactory sessionFactory = null;
		Configuration config = new Configuration();
		final String PATH = System.getProperty("user.home") + File.separator + "JobWay" + File.separator + "config.properties";
		Properties prop=PropertiesUtil.loadProperties(PATH);
		config.setProperty("hibernate.hikari.dataSource.url",
				prop.getProperty("DBURL"));
		config.setProperty("hibernate.hikari.dataSource.user",
				prop.getProperty("DBUSER"));
		config.setProperty("hibernate.hikari.dataSource.password",
				prop.getProperty("DBPASSWORD"));
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySettings(config.getProperties()).configure().build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			
			Platform.runLater(()->Notifications.create().title("Info").text("Connexion à la base de données établie.").showInformation());
		
		} catch (Exception e) {
			Platform.runLater(()->Notifications.create().title("Erreur").text("La connexion à la base de données n'a pas pu être établie.")
					.showError());
			StandardServiceRegistryBuilder.destroy(registry);

			logger.error("Erreur dans la connexion à la base de données: ", e);
		}
		return sessionFactory;
	}

	/**
	 * Rebuild the session factory
	 */
	public static void restart() {
		sessionFactory = buildSessionFactory();

	}

	/**
	 * Session's getter
	 *
	 * @return the sessionFactory
	 */
	public static SessionFactory getSessionfactory() {
		return sessionFactory;
	}

}
