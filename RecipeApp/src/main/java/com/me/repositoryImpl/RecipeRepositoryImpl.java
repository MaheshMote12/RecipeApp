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
	public Recipe save(Recipe recipe) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.saveOrUpdate(recipe);
		session.flush();
		session.getTransaction().commit();
		
		return recipe;
	
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

	@Override
	public List<String> findCategories() {

		Session session = sessionFactory.openSession();
		List<String> categories = session.createQuery("select c.categoryName FROM Category c", String.class)
				.getResultList();
		
		System.out.println("TTTTTTTOOOOOOOOOOPPPPPPPPPP " +categories.size());
		
		return categories;
	}

	@Override
	public void deleteById(Long l) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Recipe recipe = session.load(Recipe.class, l);
		
		session.delete(recipe);
	/*	List<Ingrediants> list = recipe.getIngrediants();
		for (Ingrediants ingrediants : list) {
			session.delete(ingrediants);
		}
*/		
		session.getTransaction().commit();
	}

	

}
