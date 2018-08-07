package pl.java.wykop.dao;

import java.util.List;

import pl.java.wykop.model.Discovery;

public interface DiscoveryDAO extends GenericDAO<Discovery, Long> {
	List<Discovery> getAll();
}
