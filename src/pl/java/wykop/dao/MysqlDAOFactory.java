package pl.java.wykop.dao;

public class MysqlDAOFactory extends DAOFactory{

	@Override
	public DiscoveryDAO getDiscoveryDAO() {
		// TODO Auto-generated method stub
		return new DiscoveryDAOImpl();
	}

	@Override
	public UserDAO getUserDAO() {
		// TODO Auto-generated method stub
		return new UserDAOImpl();
	}

	@Override
	public VoteDAO getVoteDAO() {
		// TODO Auto-generated method stub
		return new VoteDAOImpl();
	}
	
}
