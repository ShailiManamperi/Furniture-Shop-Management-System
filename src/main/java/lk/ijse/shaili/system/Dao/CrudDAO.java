package lk.ijse.shaili.system.Dao;


import lk.ijse.shaili.system.Dao.exception.ConstraintViolationException;
import lk.ijse.shaili.system.Entity.SuperEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudDAO <T extends SuperEntity,ID extends Serializable> extends SuperDAO{

    T save(T entity) throws ConstraintViolationException;

    T update(T entity) throws ConstraintViolationException;

    boolean deleteByPk(ID pk) throws ConstraintViolationException;

    List<T> findAll() ;

    Optional<T> findByPk(ID pk) ;

    boolean existByPk(ID pk) ;

    long count() ;
}
