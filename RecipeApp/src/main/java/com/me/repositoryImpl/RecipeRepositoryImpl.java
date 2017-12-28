package com.me.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.me.model.Recipe;
import com.me.model.UnitOfMeasure;
import com.me.repository.RecipeRepository;

@Repository
public class RecipeRepositoryImpl implements RecipeRepository {

	private SessionFactory sessionFactory;
	
	@Autowired
	public RecipeRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Recipe recipe) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(recipe);
		session.flush();
		session.getTransaction().commit();
	}

	@Override
	public List<Recipe> getRecipies() {
		Session session = sessionFactory.openSession();
		
		List<Recipe> list = session.createQuery("from Recipe r", Recipe.class).getResultList();
		return list;
	}

	@Override
	public UnitOfMeasure getUom(String uom) {
		Session session = sessionFactory.openSession();
		
		List<UnitOfMeasure> list = session.createQuery("from UnitOfMeasure r where r.unit = :unit ",UnitOfMeasure.class)
									.setParameter("unit", uom)
										.getResultList();
		
		return list.get(0);
		
	}

	@Override
	public Recipe findById(Long id) {

		Session session = sessionFactory.openSession();
		Recipe recipe = session.get(Recipe.class, id);
		
		return recipe;
	}

	

}
