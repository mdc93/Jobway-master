package cripel.jobway.dao;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.controlsfx.control.Notifications;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.utilities.HibernateUtil;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Abstract class to manage all related DAO
 *
 * @param <T>
 */
public abstract class AbstractDao<T> implements Dao<T> {
	/**
	 * Logger logback
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private static final String READERROR = "Erreur dans la lecture de la base de donnée";

	/**
	 * The hibernatee session
	 */
	private Session session;
	/**
	 * Class of generic type
	 */
	private final Class<T> cla;

	/**
	 * Constructor of the abstract class which implements the interface Dao
	 *
	 * @param cla generic Class parameter name
	 */
	protected AbstractDao(Class<T> cla) {
		this.cla = cla;
	}

	@Override
	public void save(T t) {
		Transaction tx = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			tx = session.beginTransaction();
			session.save(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Erreur dans le save", e);
		} finally {
			session.close();
		}
	}

	@Override
	public void update(T t) {
		Transaction tx = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			tx = session.beginTransaction();
			session.update(t);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Erreur dans l'update", e);
		} finally {
			session.close();
		}
	}

	@Override
	public T selectById(int id) {
		T result = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			result = session.createQuery("From " + cla.getName() + " WHERE id" + cla.getSimpleName() + "=" + id, cla)
					.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
		} finally {
			session.close();
		}

		return result;

	}

	@Override
	public T selectByCriteria(String criteria) {
		T result = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			result = session.createQuery("From " + cla.getName() + " WHERE " + criteria, cla).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
		} finally {

			session.close();
		}
		return result;
	}

	@Override
	public void saveOrUpdate(T t,boolean showMessage) {
		Transaction tx = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			tx = session.beginTransaction();
			session.flush();
			session.clear();
			session.saveOrUpdate(t);
			tx.commit();
			if(showMessage)
				Notifications.create().title("Info").text("Sauvegarde effectuée").showInformation();
					
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Erreur dans le save or update", e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La sauvegarde a échouée");
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {
			session.close();
		}
	}

	@Override
	public void remove(T t,boolean showMessage) {
		Transaction tx = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			tx = session.beginTransaction();
			session.remove(t);
			tx.commit();
			if(showMessage)
				Notifications.create().title("Info").text("Suppression effectuée").showInformation();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			logger.error("Erreur dans la supression", e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("La suppression a échouée");
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {
			session.close();
		}
	}

	@Override
	public List<T> getList() {
		List<T> result = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			result = session.createQuery("From " + cla.getName(), cla).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(READERROR);
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {

			session.close();
		}
		return result;

	}

	@Override
	public List<T> getListDelete(boolean isDelete) {
		List<T> result = null;
		int integerIsDelete = 0;
		if (isDelete) {
			integerIsDelete = 1;
		}
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			Query<T> query = session.createQuery("From " + cla.getName() + " WHERE isDelete = " + integerIsDelete, cla);
			query.setCacheable(true);
			result = query.list();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(READERROR);
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {

			session.close();
		}
		return result;

	}

	@Override
	public List<T> getListDeleteOrdered(boolean isDelete, String column) {
		List<T> result = null;
		int integerIsDelete = 0;
		if (isDelete) {
			integerIsDelete = 1;
		}
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			Query<T> query = session.createQuery(
					"From " + cla.getName() + " WHERE isDelete = " + integerIsDelete + "ORDER BY " + column, cla);
			query.setCacheable(true);
			result=query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(READERROR);
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {

			session.close();
		}
		return result;

	}

	@Override
	public List<T> getListCustom(String query) {
		List<T> result = null;
		session = HibernateUtil.getSessionfactory().openSession();
		try {
			session.beginTransaction();
			result = session.createQuery(query, cla).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(READERROR);
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {

			session.close();
		}
		return result;

	}

	@Override
	public List<T> getListOrder(String column) {
		List<T> result = null;
		session = HibernateUtil.getSessionfactory().openSession();

		try {
			session.beginTransaction();
			result = session.createQuery("From " + cla.getName() + " c ORDER BY c." + column, cla).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error(READERROR, e);
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(READERROR);
			alert.setContentText(ExceptionUtils.getRootCauseMessage(e.getCause()));
			alert.show();
		} finally {
			session.close();

		}
		return result;
	}
}
