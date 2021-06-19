package com.ibm.project.service.container.repositories;

import com.ibm.project.service.container.domains.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * MongoDB repository for the Container.
 */
public interface ContainerRepository extends MongoRepository<Container, String> {

    /**
     * Find by name container.
     *
     * @param name the name
     * @return the container
     */
    Optional<Container> findByName(String name);

    /**
     * Find by version page.
     *
     * @param version  the version
     * @param pageable the pageable
     * @return the page
     */
    Page<Container> findByVersion(Long version, Pageable pageable);

    /**
     * Find by id and version page.
     *
     * @param name       the name
     * @param version  the version
     * @param pageable the pageable
     * @return the page
     */
    Page<Container> findByNameLikeAndVersion(String name, Long version, Pageable pageable);

}
