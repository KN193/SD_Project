package com.uow.assignment.model;

public class Reputation {

	private boolean likeOrDislike;

	/**
	 *  True is like, False is Dislike
	 * @return
	 */
	public boolean isLikeOrDislike() {
		return likeOrDislike;
	}
	
	/**
	 *  True is like, False is Dislike
	 * @param likeOrDislike
	 */
	public void setLikeOrDislike(boolean likeOrDislike) {
		this.likeOrDislike = likeOrDislike;
	}
}
