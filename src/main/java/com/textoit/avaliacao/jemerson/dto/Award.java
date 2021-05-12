package com.textoit.avaliacao.jemerson.dto;

public class Award {

	private Winner[] min;
	private Winner[] max;
	
	public Award() {
		min = new Winner[]{};
		max = new Winner[]{};
	}

	public Winner[] getMin() {
		return min;
	}

	public void setMin(Winner[] min) {
		this.min = min;
	}

	public Winner[] getMax() {
		return max;
	}

	public void setMax(Winner[] max) {
		this.max = max;
	}

}
