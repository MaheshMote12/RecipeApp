package com.me.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.me.model.UnitOfMeasure;
import com.me.repository.UnitOfMeasureRepo;

@Repository
public class UnitOfMeasureRepoImpl implements UnitOfMeasureRepo {

	private SessionFactory sessionFactory;
	
	@Autowired
	public UnitOfMeasureRepoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	public UnitOfMeasure findById(long id) {

		Session session = sessionFactory.openSession();
		
		UnitOfMeasure unitOfMeasure = session.get(UnitOfMeasure.class, id);
		
		session.close();
		return unitOfMeasure;
	}


	@Override
	public UnitOfMeasure findByUnit(String unit) {

		Session session = sessionFactory.openSession();
		List<UnitOfMeasure> resultList = session.createQuery( "SELECT U FROM UnitOfMeasure U WHERE U.unit = :UNIT", UnitOfMeasure.class )
				.setParameter("UNIT" , unit)
				 .getResultList();
		
		return resultList.iterator().next();
		
	}

}
