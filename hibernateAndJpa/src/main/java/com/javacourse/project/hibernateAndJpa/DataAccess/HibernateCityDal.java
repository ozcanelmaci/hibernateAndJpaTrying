package com.javacourse.project.hibernateAndJpa.DataAccess;

import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.javacourse.project.hibernateAndJpa.Entities.City;

@Repository
public class HibernateCityDal implements ICityDal{

	private EntityManager entityManager; //Session nesnemize karşılık gelen şey budur.
	
	@Autowired  //hibernate injection yapıyor, session ve session factory diye daha önce kullandığımız kısmı otomatik oluşturuyor.
	public HibernateCityDal(EntityManager entityManager) {
		this.entityManager = entityManager; //Entity Manager değişkenimizi uygun injeciton ile beraber dolduruyor.
	}
	
	@Override
	@Transactional //AOP - Aspect Oriented Programming //Bu annotation getAll function ın başında ve sonunda transaction işlemini yapar.
	public List<City> getAll() {
		Session session = entityManager.unwrap(Session.class);//Spring Framework bize hibernate session ı verdi bizim için nesne oluşturdu.
		List<City> cities = session.createQuery("from City", City.class).getResultList();
		return cities;
	}

	@Override
	public void add(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);	
	}

	@Override
	public void update(City city) {
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);	
	}

	@Override
	public void delete(City city) {
		Session session = entityManager.unwrap(Session.class);
		City cityToDelete = session.get(City.class, city.getId());
		session.delete(cityToDelete);	
	}

	@Override
	public City getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		City city = session.get(City.class, id);
		return city;
	}

}
