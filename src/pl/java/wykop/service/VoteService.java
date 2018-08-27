package pl.java.wykop.service;

import java.sql.Timestamp;
import java.util.Date;

import pl.java.wykop.dao.DAOFactory;
import pl.java.wykop.dao.VoteDAO;
import pl.java.wykop.model.Vote;
import pl.java.wykop.model.VoteType;

public class VoteService {
	
	public Vote addVote(long discoveryId, long userId, VoteType voteType) {
		Vote vote = new Vote();
		vote.setDiscoveryId(discoveryId);
		vote.setUserId(userId);
		vote.setDate(new Timestamp(new Date().getTime()));
		vote.setVoteType(voteType);
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = daoFactory.getVoteDAO();
		vote = voteDAO.create(vote);
		return vote;
	}
	
	public Vote updateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote voteToUpdate = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		if (voteToUpdate != null) {
			voteToUpdate.setVoteType(voteType);
			voteDAO.update(voteToUpdate);
		}
		return voteToUpdate;
		
	}
	
	public Vote addOrUpdateVote(long discoveryId, long userId, VoteType voteType) {
		DAOFactory factory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = factory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		Vote resultVote = null;
		if (vote == null) {
			resultVote = addVote(discoveryId, userId, voteType);
		}else {
			resultVote = updateVote(discoveryId, userId, voteType);
		}
		return resultVote;
	}
	
	public Vote getVoteByDiscoveryUserId(long discoveryId, long userId) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		VoteDAO voteDAO = daoFactory.getVoteDAO();
		Vote vote = voteDAO.getVoteByUserIdDiscoveryId(userId, discoveryId);
		return vote;
	}
	
}
