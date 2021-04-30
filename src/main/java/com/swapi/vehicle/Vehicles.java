package com.swapi.vehicle;

/**
 * Gets a Star Wars Vehicles collection
 * @author Eli James Aguiar
 */
public interface Vehicles {
	/**
	 * Get a collection of Star Wars vehicles
	 * @return
	 * @throws Exception
	 */
	public VehiclesInfo get() throws Exception;
}
