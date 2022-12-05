package cripel.jobway.dao;

import java.util.List;

/**
 * The Interface which implements the concept of DAO to to read or write in the
 * database.
 *
 * @param <T> the generic type from model
 */
public interface Dao<T> {

	/**
	 * Method to save the data in the database.
	 *
	 * @param t the generic type from model
	 */
	void save(T t);

	/**
	 * Method to update the data in the database.
	 *
	 * @param t the generic type from model
	 */
	void update(T t);

	/**
	 * Method to save or update in the database.
	 *
	 * @param t the generic type from model
	 * @param showMessage Enable a message on success
	 */
	void saveOrUpdate(T t,boolean showMessage);

	/**
	 * Method to remove in the database.
	 *
	 * @param t the generic type from model
	 * @param showMessage Enable a message on success
	 */

	void remove(T t,boolean showMessage);

	/**
	 * Select by id.
	 *
	 * @param id the id
	 * @return the the generic type from model
	 */

	T selectById(int id);

	/**
	 * Method to select something from the database with certain criteria. The
	 * criteria is an HQL Where condition.
	 *
	 * @param criteria the criteria exemple idPerson=1;
	 * @return the generic type from model
	 */
	T selectByCriteria(String criteria);

	/**
	 * Gets the list.
	 *
	 * @return the list
	 */
	List<T> getList();

	/**
	 * Gets the list from an HQL query.
	 *
	 * @param query the HQL query
	 * @return the list custom
	 */
	List<T> getListCustom(String query);

	/**
	 * Method to get a list of data where isDelete is true or false.
	 *
	 * @param isDelete the is delete
	 * @return the list
	 */
	List<T> getListDelete(boolean isDelete);

	/**
	 * Method to get a list of data where isDelete is true or false and ordered by
	 * column.
	 *
	 * @param isDelete boolean
	 * @param column   Argument of ORDER BY query
	 * @return the list delete ordered
	 */
	List<T> getListDeleteOrdered(boolean isDelete, String column);

	/**
	 * Method to get an ordered list from the database.
	 *
	 * @param column the column of the database
	 * @return the list order
	 */
	List<T> getListOrder(String column);
}