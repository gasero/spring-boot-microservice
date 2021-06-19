package com.ibm.project.service.container.controllers;

import com.ibm.project.service.container.configuration.PaginationConfiguration;
import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import com.ibm.project.service.container.services.ContainerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Optional;

@RestController
@Validated
@AllArgsConstructor
@Slf4j
public class ContainerApiController implements ContainerApi {

    private ContainerService containerService;

    private PaginationConfiguration paginationConfiguration;

    @Override
    public ContainerSearchResult findContainerByNameAndVersion(@NotNull @Valid String name,
                                                               @NotNull @Valid Long version, @Valid Integer pageSize,
                                                               @Valid Integer pageNumber, @Valid String sortProperty,
                                                               @Valid String sortOrder) {
        Integer pageNumberO = Optional.ofNullable(pageNumber).orElse(0);
        Integer pageSizeO = Optional.ofNullable(pageSize).orElse(paginationConfiguration.getPageSize());
        PageRequest pageRequest = Optional.ofNullable(sortProperty)
                .map(s -> PageRequest.of(pageNumberO, pageSizeO,
                        Sort.by(Sort.Direction.fromOptionalString(sortOrder).orElse(Sort.Direction.ASC), s)))
                .orElse(PageRequest.of(pageNumberO, pageSizeO));
        return containerService.findContainerByNameAndVersion(name, version, pageRequest);
    }

    @Override
    public ContainerSearchResult findContainerByVersion(@NotNull @Valid Long version, @Valid Integer pageSize,
                                                        @Valid Integer pageNumber, @Valid String sortProperty,
                                                        @Valid String sortOrder) {
        Integer pageNumberO = Optional.ofNullable(pageNumber).orElse(0);
        Integer pageSizeO = Optional.ofNullable(pageSize).orElse(paginationConfiguration.getPageSize());
        PageRequest pageRequest = Optional.ofNullable(sortProperty)
                .map(s -> PageRequest.of(pageNumberO, pageSizeO,
                        Sort.by(Sort.Direction.fromOptionalString(sortOrder).orElse(Sort.Direction.ASC), s)))
                .orElse(PageRequest.of(pageNumberO, pageSizeO));
        return containerService.findContainerByVersion(version, pageRequest);
    }

    @Override
    public ContainerModel getContainerById(String containerId) {
        return containerService.getContainer(containerId);
    }
}
