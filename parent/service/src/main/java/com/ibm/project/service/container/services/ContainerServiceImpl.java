package com.ibm.project.service.container.services;

import com.ibm.project.service.container.domains.Container;
import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import com.ibm.project.service.container.repositories.ContainerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * The type Container service.
 */
@Service
@AllArgsConstructor
@Slf4j
public class ContainerServiceImpl implements ContainerService {

    private ContainerRepository containerRepository;

    private ModelMapper modelMapper;

    @Override
    public ContainerSearchResult findContainerByVersion(Long version, PageRequest pageRequest) {
        Page<Container> containers = containerRepository.findByVersion(version, pageRequest);

        ContainerSearchResult result = new ContainerSearchResult();
        result.setTotalSize(containers.getTotalElements());

        log.info("Found {} containers", containers.getTotalElements());

        java.lang.reflect.Type targetListType = new TypeToken<List<ContainerModel>>() {
        }.getType();
        result.setResult(modelMapper.map(containers.getContent(), targetListType));

        return result;
    }

    @Override
    public ContainerSearchResult findContainerByNameAndVersion(String name, Long version, PageRequest pageRequest) {
        Page<Container> containers = containerRepository.findByNameLikeAndVersion(name, version, pageRequest);

        ContainerSearchResult result = new ContainerSearchResult();
        result.setTotalSize(containers.getTotalElements());

        log.info("Found {} containers", containers.getTotalElements());

        java.lang.reflect.Type targetListType = new TypeToken<List<ContainerModel>>() {
        }.getType();
        result.setResult(modelMapper.map(containers.getContent(), targetListType));

        return result;
    }

    @Override
    public ContainerModel getContainer(String containerId) {
        Container container = containerRepository.findById(containerId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No container found for id: %s",
                        containerId)));

        log.info("Found container {}", container);

        return modelMapper.map(container, ContainerModel.class);
    }

    @Override
    public ContainerModel getContainerByName(String containerName) {
        Container container = containerRepository.findByName(containerName).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("No container found for name: %s",
                        containerName)));

        log.info("Found container {}", container);

        return modelMapper.map(container, ContainerModel.class);
    }
}
