package cripel.jobway.dao;

import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cripel.jobway.model.Person;
import cripel.jobway.utilities.HibernateUtil;

public class PersonDAO extends AbstractDao<Person> {

	public PersonDAO() {
		super(Person.class);

	}

	private static final Logger loggerPersonDAO = LoggerFactory.getLogger(PersonDAO.class);

	static Session session;

	/**
	 * Database entry queried with a left join fetch to avoid lazy exception and
	 * improve performance
	 *
	 * @param person person queried from the database
	 * @return
	 */
	public static Person fetchPerson(Person person) {
		Person result = null;
		int id = person.getIdPerson();
		try {
			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			String query = "SELECT p FROM Person p LEFT JOIN FETCH p.employee LEFT JOIN FETCH p.city c LEFT JOIN FETCH c.postalcode cp LEFT JOIN FETCH cp.cities"
					+ " LEFT JOIN FETCH p.countries cou LEFT JOIN FETCH cou.countrytype LEFT JOIN FETCH p.residencepermits rp LEFT JOIN FETCH p.household LEFT JOIN FETCH p.situationterritory"
					+ " LEFT JOIN FETCH p.incometypes LEFT JOIN FETCH p.other LEFT JOIN FETCH p.perDrL LEFT JOIN FETCH p.locomotionmeans"
					+ " LEFT JOIN FETCH p.disability LEFT JOIN FETCH p.perLans LEFT JOIN FETCH p.familyreunions LEFT JOIN FETCH p.frenchTest"
					+ " LEFT JOIN FETCH p.worksearch ws LEFT JOIN FETCH ws.availabilities LEFT JOIN FETCH ws.workSectors LEFT JOIN FETCH"
					+ " p.formations fo LEFT JOIN FETCH p.workexperiences exp LEFT JOIN FETCH exp.workexptype LEFT JOIN FETCH exp.worktasks LEFT JOIN FETCH fo.formationtype LEFT JOIN FETCH"
					+ " rp.residencepermittype LEFT JOIN FETCH p.civilstatus LEFT JOIN FETCH p.countryByIdCountry LEFT JOIN FETCH p.countryByIdCountryReunionNationality WHERE p.idPerson="
					+ id;
			result = session.createQuery(query, Person.class).getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			loggerPersonDAO.error("Erreur", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return result;
	}

	/**
	 * Check if the NISS is already in the database
	 *
	 * @param id   person id
	 * @param nISS belgian security social number
	 * @return
	 */
	public static boolean isNISSUnique(int id, String nISS) {
		List<String> result = null;
		boolean condition = false;
		try {

			session = HibernateUtil.getSessionfactory().openSession();
			session.beginTransaction();
			result = session.createQuery(
					"SELECT p.personNiss FROM Person p WHERE p.personNiss='" + nISS + "' AND p.idPerson!=" + id,
					String.class).list();
			session.getTransaction().commit();
			if (result.isEmpty())
				condition = true;
		} catch (Exception e) {
			loggerPersonDAO.error("Erreur", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return condition;
	}
}
