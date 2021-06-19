package com.ibm.project.service.container.services;

import com.ibm.project.service.container.domains.Container;
import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import com.ibm.project.service.container.repositories.ContainerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ContainerServiceImplTest {

    private static final String CONTAINER_ID = "abc";
    private static final String CONTAINER_ID2 = "abc2";
    private static final String CONTAINER_NAME = "Container 1";
    private static final Long CONTAINER_VERSION = 1L;
    private static final ZonedDateTime CONTAINER_CREATED = ZonedDateTime.now(ZoneOffset.UTC);
    private final PageRequest page = PageRequest.of(0, 3);

    private ContainerService containerService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Mock
    private ContainerRepository containerRepository;

    @Before
    public void setUp() {
        this.containerService = new ContainerServiceImpl(containerRepository, modelMapper);
    }

    @After
    public void tearDown() {
        this.containerService = null;
    }

    @Test
    public void findContainer() {
        List<Container> containersList = new ArrayList<>();
        containersList.add(createContainer(CONTAINER_ID, CONTAINER_NAME, CONTAINER_CREATED));
        containersList.add(createContainer(CONTAINER_ID2, CONTAINER_NAME, CONTAINER_CREATED));
        Page<Container> containerPage = new PageImpl<>(containersList, page, containersList.size());

        when(containerRepository.findByVersion(anyLong(), any())).thenReturn(containerPage);

        ContainerSearchResult results = containerService.findContainerByVersion(CONTAINER_VERSION, page);

        assertNotNull("Response shouldn't be null", results);
        assertNotNull("List shouldn't be null", results.getResult());
        assertEquals("Size is invalid: ", Long.valueOf(2L), results.getTotalSize());
        assertEquals("Id is invalid: ", CONTAINER_ID, results.getResult().get(0).getId());
        assertEquals("Id is invalid: ", CONTAINER_ID2, results.getResult().get(1).getId());
        assertEquals("Name is invalid: ", CONTAINER_NAME, results.getResult().get(0).getName());
        assertEquals("Name is invalid: ", CONTAINER_NAME, results.getResult().get(1).getName());
        assertEquals("Version is invalid: ", CONTAINER_VERSION, results.getResult().get(0).getVersion());
        assertEquals("Version is invalid: ", CONTAINER_VERSION, results.getResult().get(1).getVersion());
        assertEquals("Created is invalid: ", CONTAINER_CREATED, results.getResult().get(0).getCreated());
        assertEquals("Created is invalid: ", CONTAINER_CREATED, results.getResult().get(1).getCreated());
    }

    // TODO: find with null values, find with multiple pages

    @Test
    public void getContainerByName() {
        final Container container = createContainer(CONTAINER_ID, CONTAINER_NAME, CONTAINER_CREATED);

        when(containerRepository.findByName(CONTAINER_NAME)).thenReturn(Optional.of(container));

        ContainerModel result = containerService.getContainerByName(CONTAINER_NAME);

        ContainerModel expectedContainerModel = modelMapper.map(container, ContainerModel.class);

        assertEquals("Response should match expected", expectedContainerModel, result);
    }

    @Test
    public void getContainer() {
        final Container container = createContainer(CONTAINER_ID, CONTAINER_NAME, CONTAINER_CREATED);

        when(containerRepository.findById(CONTAINER_ID)).thenReturn(Optional.of(container));

        ContainerModel result = containerService.getContainer(CONTAINER_ID);

        ContainerModel expectedContainerModel = modelMapper.map(container, ContainerModel.class);

        assertEquals("Response should match expected", expectedContainerModel, result);
    }

    @Test
    public void getContainerNotFound() {
        when(containerRepository.findById(CONTAINER_ID)).thenReturn(Optional.of(createContainer(CONTAINER_ID,
                CONTAINER_NAME, CONTAINER_CREATED)));
        try {
            containerService.getContainer("xxx");
            fail("Exception should be thrown in case of container is not found");
        } catch (ResponseStatusException e) {
            assertEquals("Exception doesn't match", "No container found for id: xxx", e.getReason());
        }

    }

    @Test(expected = ResponseStatusException.class)
    public void getContainerNotFound2() {
        when(containerRepository.findById(CONTAINER_ID)).thenReturn(Optional.of(createContainer(CONTAINER_ID,
                CONTAINER_NAME, CONTAINER_CREATED)));
        containerService.getContainer("xxx");
    }

    private Container createContainer(String containerId, String containerName, ZonedDateTime containerCreated) {
        Container container = new Container();
        container.setId(containerId);
        container.setName(containerName);
        container.setVersion(CONTAINER_VERSION);
        container.setCreated(containerCreated);

        return container;
    }

}
