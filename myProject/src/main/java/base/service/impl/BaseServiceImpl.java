package base.service.impl;

import base.domain.BaseEntity;
import base.repository.BaseRepository;
import base.repository.impl.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<E extends BaseEntity<ID>,ID extends Serializable,R extends BaseRepositoryImpl<E,ID>>
 implements BaseRepository<E,ID> {

    protected R repository;
    protected EntityManager entityManager;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
        entityManager=repository.getEntityManager();
    }

    @Override
    public E save(E element) {
        entityManager.getTransaction().begin();
       E data= repository.save(element);
        entityManager.getTransaction().commit();
        return data;
    }

    @Override
    public E update(E element) {
        entityManager.getTransaction().begin();
       E data= repository.update(element);
       entityManager.getTransaction().commit();
       return data;
    }

    @Override
    public void delete(E element) {
      entityManager.getTransaction().begin();
      repository.delete(element);
      entityManager.getTransaction().commit();
    }

    @Override
    public Optional<E> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }
}
