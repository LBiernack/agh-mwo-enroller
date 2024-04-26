package com.company.enroller.persistence;

import java.util.Collection;

import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import com.company.enroller.model.Participant;

@Component("participantService")
public class ParticipantService {

	DatabaseConnector connector;

	public ParticipantService() {
		connector = DatabaseConnector.getInstance();
	}

	public Collection<Participant> getAll() {
		String hql = "FROM Participant";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Participant findByLogin(String login) {
		return connector.getSession().get(Participant.class, login);
	}

	public Participant findByLogin1(String login) {
		String hql = "FROM Participant where id = '" + login + "'";
		Query query = connector.getSession().createQuery(hql);
		return (Participant) query.uniqueResult();
	}

	public void add(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
	}

	public void delete(Participant participant) {
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().delete(participant);
		transaction.commit();
	}

	public void updatePassword(Participant participant, String newPassword) {
		participant.setPassword(newPassword);
		Transaction transaction = connector.getSession().beginTransaction();
		connector.getSession().save(participant);
		transaction.commit();
	}

	public Collection<Participant> sortByLogin(String sortOrder) {
		String hql = "FROM Participant p ORDER BY p.login " + sortOrder;
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}

	public Collection<Participant> findByLoginKey(String key) {
		String hql = "SELECT p FROM Participant p WHERE p.login like '%" + key + "%'";
		Query query = connector.getSession().createQuery(hql);
		return query.list();
	}
}
