package com.ibm.project.service.container.services;

import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import org.springframework.data.domain.PageRequest;

/**
 * The Container service.
 */
public interface ContainerService {

    /**
     * Search container by version container search result.
     *
     * @param version     the version
     * @param pageRequest the page request
     * @return the container search result
     */
    ContainerSearchResult findContainerByVersion(Long version, PageRequest pageRequest);

    /**
     * Gets container.
     *
     * @param containerId the container id
     * @return the container
     */
    ContainerModel getContainer(String containerId);

    /**
     * Gets container by name.
     *
     * @param containerName the container name
     * @return the container by name
     */
    ContainerModel getContainerByName(String containerName);

    /**
     * Find container by name and version container search result.
     *
     * @param name        the name
     * @param version     the version
     * @param pageRequest the page request
     * @return the container search result
     */
    ContainerSearchResult findContainerByNameAndVersion(String name, Long version, PageRequest pageRequest);
}
