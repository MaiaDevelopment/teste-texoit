package com.textoit.avaliacao.jemerson.dto;

public class Winner {

	private String producer;
	private Integer interval;
	private Short previousWin;
	private Short followingWin;
	
	public Winner() {
		
	}

	public Winner(String producer, Integer interval, Short previousWin, Short followingWin) {
		super();
		this.producer = producer;
		this.interval = interval;
		this.previousWin = previousWin;
		this.followingWin = followingWin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Short getPreviousWin() {
		return previousWin;
	}

	public void setPreviousWin(Short previousWin) {
		this.previousWin = previousWin;
	}

	public Short getFollowingWin() {
		return followingWin;
	}

	public void setFollowingWin(Short followingWin) {
		this.followingWin = followingWin;
	}

}
