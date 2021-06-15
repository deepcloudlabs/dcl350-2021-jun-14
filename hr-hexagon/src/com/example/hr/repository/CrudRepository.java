package com.example.hr.repository;

import java.util.Optional;

public interface CrudRepository<E, ID> {
	boolean exists(E e);
	Optional<E> findByIdentity(ID id);
	E insert(E e);
	E save(E e);
	Optional<E> removeByIdentity(ID id);
}
