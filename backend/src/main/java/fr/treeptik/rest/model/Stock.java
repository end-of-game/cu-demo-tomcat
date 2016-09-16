package fr.treeptik.rest.model;

import java.io.Serializable;
import java.util.Random;

public final class Stock implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;

	public Stock(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return (new Random()).nextDouble();

	}

	public Double getChange() {
		return (new Random()).nextDouble();
	}

	public Integer getShare() {
		return (new Random()).nextInt(1000);
	}

	public Double getValue() {
		return (new Random()).nextDouble();
	}
}
