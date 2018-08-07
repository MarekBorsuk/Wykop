package pl.java.wykop.dao;

import pl.java.wykop.model.Vote;

public interface VoteDAO extends GenericDAO<Vote, Long> {
	public Vote getVoteByUserIdDiscoveryId(long userId, long discoveryId);
}
