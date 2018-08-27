package pl.java.wykop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.java.wykop.model.Discovery;
import pl.java.wykop.model.User;
import pl.java.wykop.model.Vote;
import pl.java.wykop.model.VoteType;
import pl.java.wykop.service.DiscoveryService;
import pl.java.wykop.service.VoteService;

/**
 * Servlet implementation class VoteController
 */
@WebServlet("/vote")
public class VoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggerUser = (User) request.getSession().getAttribute("user");
		if (loggerUser != null) {
			VoteType voteType = VoteType.valueOf(request.getParameter("vote"));
			long userId = loggerUser.getId();
			long discoveryId = Long.parseLong(request.getParameter("discovery_id"));
			updateVote(userId, discoveryId, voteType);
		}
		response.sendRedirect(request.getContextPath() + "/");
	}



	private void updateVote(long userId, long discoveryId, VoteType voteType) {
		VoteService voteService = new VoteService();
		Vote existingVote = voteService.getVoteByDiscoveryUserId(discoveryId, userId);
		Vote updatedVote = voteService.addOrUpdateVote(discoveryId, userId, voteType);
		if (existingVote != updatedVote || !updatedVote.equals(existingVote)) {
			updateDiscovery(discoveryId,existingVote,updatedVote);
		}
		
	}



	private void updateDiscovery(long discoveryId, Vote oldVote, Vote newVote) {
		DiscoveryService discoveryService = new DiscoveryService();
		Discovery discoveryById = discoveryService.getDiscoveryById(discoveryId);
		Discovery updatedDiscovery = null;
		if (oldVote == null && newVote != null) {
			updatedDiscovery = addDiscoveryVote(discoveryById, newVote.getVoteType());
		}else if (oldVote != null && newVote != null) {
			updatedDiscovery = removeDiscoveryVote(discoveryById, oldVote.getVoteType());
			updatedDiscovery = addDiscoveryVote(updatedDiscovery, newVote.getVoteType());
		}
		discoveryService.updateDiscovery(updatedDiscovery);
		
	}



	private Discovery removeDiscoveryVote(Discovery discovery, VoteType voteType) {
		Discovery discoveryCopy = new Discovery(discovery);
		if (voteType == VoteType.VOTE_UP) {
			discoveryCopy.setUpVote(discoveryCopy.getUpVote() -1);
		}else if (voteType == VoteType.VOTE_DOWN) {
			discoveryCopy.setDownVote(discoveryCopy.getDownVote() -1);
		}
		return discoveryCopy;
	}



	private Discovery addDiscoveryVote(Discovery discovery, VoteType voteType) {
		Discovery discoveryCopy = new Discovery(discovery);
		if (voteType == VoteType.VOTE_UP) {
			discoveryCopy.setUpVote(discoveryCopy.getUpVote() + 1);
		}else if (voteType == VoteType.VOTE_DOWN) {
			discoveryCopy.setDownVote(discoveryCopy.getDownVote() + 1);
		}
		return discoveryCopy;
	}

}
