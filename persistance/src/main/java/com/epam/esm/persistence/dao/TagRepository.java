package com.epam.esm.persistence.dao;

import com.epam.esm.persistence.entity.Tag;
import java.util.Optional;

/**
 * Interface that extends {@link SimpleCrudRepository} and parametrizes according
 * to {@link Tag} entity.
 *
 * @author Tamerlan Hurbanov
 * @see SimpleCrudRepository
 * @see Tag
 * @since 1.0
 */
public interface TagRepository extends SimpleCrudRepository<Tag, Long> {

  /**
   * Retrieves an entity by its name field.
   *
   * @param name must not be {@code null}.
   * @return the entity with the given name or {@link Optional#empty()} if none found.
   */
  Optional<Tag> findByName(String name);
}
