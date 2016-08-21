package com.devblogs.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devblogs.model.Category;
import com.devblogs.model.Item;
import com.devblogs.model.Order;
import com.devblogs.model.Provider;
import com.devblogs.model.Role;
import com.devblogs.service.ItemService;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {
	private Log log = LogFactory.getLog(ItemServiceImpl.class);
	
	@PersistenceContext	
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return em.createQuery("from Item", Item.class).getResultList();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Item> findByCategory(Category category) {
		TypedQuery<Item> query = em.createQuery("from Item i where i.category = :category", Item.class);
		query.setParameter("category", category);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public Item findById(Long id) {
		if (id > 0) {
			TypedQuery<Item> query = em.createQuery("from Item i where i.id = :id", Item.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		}
		return null;
	}
	
	@Override
	@Transactional(readOnly = false)
	public Item save(Item item) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(Item item) {
		Item mergedItem = em.merge(item);
		em.remove(mergedItem);
	}
}