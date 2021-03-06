package org.dyndns.jkiddo.dmp;

import java.util.Collection;

import org.dyndns.jkiddo.dmp.model.Database;

public interface ILibrary
{

	/**
	 * Returns the current revision of this library.
	 */
	public abstract int getRevision();

	/**
	 * Sets the name of this Library. Note: Library must be open or an <tt>IllegalStateException</tt> will be thrown
	 */

	/**
	 * Returns the name of this Library
	 */
	public abstract String getName();

	/**
	 * @return
	 */
	public abstract Collection<Database> getDatabases();

	/**
	 * Adds database to this Library (<b>NOTE</b>: only one Database per Library is supported by iTunes!)
	 * 
	 * @param database
	 * @throws DaapTransactionException
	 */
	public abstract void addDatabase(Database database);

	/**
	 * Removes database from this Library
	 * 
	 * @param database
	 * @throws DaapTransactionException
	 */

	/**
	 * Returns true if this Library contains database
	 * 
	 * @param database
	 * @return
	 */





	public abstract String toString();



	public abstract Database getDatabase(long databaseId);

}