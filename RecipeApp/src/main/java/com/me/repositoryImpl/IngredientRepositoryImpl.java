package com.me.repositoryImpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.me.model.Ingrediants;
import com.me.model.UnitOfMeasure;
import com.me.repository.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class IngredientRepositoryImpl implements IngredientRepository {

	private SessionFactory sessionFactory;

	@Autowired
	public IngredientRepositoryImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	@Override
	public Ingrediants findByRecipeAndIngrediantsId(Long recipeId, Long ingrediantsId) {
		
		Session session = sessionFactory.openSession();
		
		Query<Ingrediants> query = session.createQuery(" SELECT I FROM Ingrediants I where I.recipe.recipeId =:recipeId and I.id =:ingrId ", Ingrediants.class);
		
		System.out.println("IN INGREDIENT REPOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
		
		query.setParameter("recipeId", recipeId);
		query.setParameter("ingrId", ingrediantsId);
		
		try{
			
			Ingrediants result = query.getSingleResult();
			return result;
		}
		catch(Exception ex){
			log.info(" no ingredient found ");
		}
		
		return null;
		
	}


	@Override
	public List<UnitOfMeasure> finadAllUoms() {

		Session session = sessionFactory.openSession();
		List<UnitOfMeasure> list = session.createQuery("FROM UnitOfMeasure I", UnitOfMeasure.class).getResultList();
		
		return list;
		
	}

}
