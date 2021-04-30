package com.swapi.starship.impl;

import com.swapi.error.TreatebleErrorException;

public interface Starships {
	
	/**
	 * 
	 * @return A {@link StarshipsInfo} object containing a collection of starships
	 * @throws Exception 
	 * @throws TreatebleErrorException 
	 */
	public StarshipsInfo get() throws TreatebleErrorException, Exception;
}
