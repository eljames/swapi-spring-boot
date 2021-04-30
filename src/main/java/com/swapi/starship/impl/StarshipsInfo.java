package com.swapi.starship.impl;

import java.util.List;

public class StarshipsInfo {

	
	public int count;
    public String next;
    public Object previous;
    public List<StarshipInfo> results;
    
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public Object getPrevious() {
		return previous;
	}
	public void setPrevious(Object previous) {
		this.previous = previous;
	}
	public List<StarshipInfo> getResults() {
		return results;
	}
	public void setResults(List<StarshipInfo> results) {
		this.results = results;
	}
}
