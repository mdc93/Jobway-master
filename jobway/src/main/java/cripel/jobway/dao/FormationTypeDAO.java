package cripel.jobway.dao;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.model.FormationType;
import cripel.jobway.utilities.HibernateUtil;

public class FormationTypeDAO extends AbstractDao<FormationType> {

	public FormationTypeDAO() {
		super(FormationType.class);

	}

	private static final Logger logger = LoggerFactory.getLogger(FormationType.class);

	static Session session;

	/**
	 * Get a list of formationType of diploma type
	 *
	 * @return
	 */
	public static List<FormationType> getListFormationTypeDiploma() {
		List<FormationType> result = null;
		try {
			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			String query = "FROM FormationType f where f.isFormation = 0 and isDelete=0";
			result = session.createQuery(query, FormationType.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erreur", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

	/**
	 * Get a list of formationType of formation type
	 *
	 * @return
	 */
	public static List<FormationType> getListFormationTypeFormation() {
		List<FormationType> result = null;
		try {
			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			String query = "FROM FormationType f where f.isFormation = 1 and isDelete=0";
			result = session.createQuery(query, FormationType.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			logger.error("Erreur", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

}
