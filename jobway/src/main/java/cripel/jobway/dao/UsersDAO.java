package cripel.jobway.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.model.User;
import cripel.jobway.utilities.HibernateUtil;

public class UsersDAO extends AbstractDao<User> {

	private static final Logger loggerUserDAO = LoggerFactory.getLogger(UsersDAO.class);

	static Session session;

	public UsersDAO() {
		super(User.class);

	}

	// **************************************************************************************************
	// READ
	// **************************************************************************************************

	/**
	 * Get the list of users
	 *
	 * @return the results
	 */
	public static List<User> getListUsers() {
		List<User> result = null;
		try {
			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			String query = "FROM User u LEFT JOIN FETCH u.userlevel LEFT JOIN FETCH u.employee";
			result = session.createQuery(query, User.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			loggerUserDAO.error("Erreur", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}


	/**
	 * Select a single user by his name
	 *
	 * @param username
	 * @return result
	 */
	public static User selectSingleByUserName(String username) {

		User result = null;

		try {

			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();

			result = session.createQuery(
					"SELECT u FROM User u LEFT JOIN FETCH u.userlevel LEFT JOIN FETCH u.employee WHERE u.userName = '"
							+ username + "' AND u.userIsDelete=0",
					User.class).getSingleResult();

			session.getTransaction().commit();

		} catch (HibernateException e) {
			loggerUserDAO.error("HibernateException", e);
		} catch (NoResultException e) {
			loggerUserDAO.error("No result exception", e);
		} finally {

			if (session != null) {
				session.close();
			}
		}

		return result;

	}

	// **************************************************************************************************
	// DELETE
	// **************************************************************************************************

	/**
	 * Delete a single user by his ID
	 *
	 * @param id
	 * @return result
	 */
	public static boolean deleteSingle(int id) {

		boolean result = false;

		try {

			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();

			User tmp = session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.userlevel WHERE u.id = '" + id + "'",
					User.class).getSingleResult();
			tmp.setUserIsDelete(true);

			session.getTransaction().commit();
			result = true;

		} catch (HibernateException e) {
			loggerUserDAO.error("HibernateException", e);
		} finally {

			if (session != null) {
				session.close();
			}
		}

		return result;

	}

}
