package com.ibm.project.service.container.steps;

import com.ibm.project.service.container.SpringIntegrationTest;
import com.ibm.project.service.container.domains.Container;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class GenericHooks extends SpringIntegrationTest {

    private static final String SINGLE_CONTAINER_ID = "abc";
    private static final String SINGLE_CONTAINER_NAME = "name 1";
    private static final String CONTAINER_ID = "xyz";
    private static final String CONTAINER_NAME = "temp";
    private static final Long CONTAINER_VERSION = 1L;
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2016, Month.AUGUST, 22, 14, 30);
    private static final ZonedDateTime ZONED_DATE_TIME = LOCAL_DATE_TIME.atZone(ZoneId.of("Europe/Zurich"));

    @Before
    public void setupSingleData() {
        getContainerRepository().save(createContainer(SINGLE_CONTAINER_ID, SINGLE_CONTAINER_NAME,
                CONTAINER_VERSION));
    }

    @Before("not @smoke-tests")
    public void setupData() {
        List<Container> containers = new ArrayList<>();
        IntStream.range(0, 51).forEach(nbr -> containers.add(createContainer(CONTAINER_ID + nbr, CONTAINER_NAME,
                CONTAINER_VERSION)));
        getContainerRepository().saveAll(containers);
    }

    @After("not @smoke-tests")
    public void clearData() {
        IntStream.range(0, 51).forEach(nbr -> getContainerRepository().deleteById(CONTAINER_ID + nbr));
    }

    @After
    public void clearSingleData() {
        getContainerRepository().deleteById(SINGLE_CONTAINER_ID);
    }

    private Container createContainer(String containerId, String containerName, Long containerVersion) {
        return new Container(containerId, containerName, containerVersion, ZONED_DATE_TIME);
    }
}
