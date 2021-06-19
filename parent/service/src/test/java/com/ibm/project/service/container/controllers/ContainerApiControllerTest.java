package com.ibm.project.service.container.controllers;

import com.ibm.project.service.container.configuration.PaginationConfiguration;
import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import com.ibm.project.service.container.services.ContainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for ContainerApiController.
 */
@TestPropertySource(properties = {"spring.cloud.config.fail-fast=false"})
@RunWith(SpringRunner.class)
@WebMvcTest(secure = false)
public class ContainerApiControllerTest {

    private static final String CONTAINER_ID = "abc";
    private static final String CONTAINER_ID2 = "abcd";
    private static final String CONTAINER_NAME = "name 1";
    private static final Long CONTAINER_VERSION = 1L;
    private static final ZonedDateTime CONTAINER_CREATED = ZonedDateTime.now(ZoneOffset.UTC);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContainerService containerService;

    @MockBean
    private PaginationConfiguration paginationConfiguration;

    @Test
    public void findContainerByVersionOkStatus() throws Exception {
        final ContainerSearchResult result = new ContainerSearchResult();
        result.setTotalSize(1L);
        List<ContainerModel> results = new ArrayList<>();
        ContainerModel containerModel = new ContainerModel();
        containerModel.setId(CONTAINER_ID);
        containerModel.setName(CONTAINER_NAME);
        containerModel.setVersion(CONTAINER_VERSION);
        containerModel.setCreated(CONTAINER_CREATED);
        results.add(containerModel);
        result.setResult(results);

        given(containerService.findContainerByVersion(anyLong(), any())).willReturn(result);

        mockMvc.perform(get("/container/findByVersion")
                .param("version", String.valueOf(CONTAINER_VERSION))
                .param("pageSize", "20")
                .param("pageNumber", "0")
                .param("sortProperty", "name")
                .param("sortOrder", "desc"))
                .andExpect(jsonPath("totalSize", is(1)))
                .andExpect(jsonPath("result[0].id", is(CONTAINER_ID)))
                .andExpect(jsonPath("result[0].name", is(CONTAINER_NAME)))
                .andExpect(jsonPath("result[0].version", is(1)))
                .andExpect(jsonPath("result[0].created", is(CONTAINER_CREATED.toString())))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findContainerByVersionMultipleOkStatus() throws Exception {
        final ContainerSearchResult result = new ContainerSearchResult();
        result.setTotalSize(2L);
        List<ContainerModel> results = new ArrayList<>();
        ContainerModel containerModel = new ContainerModel();
        containerModel.setId(CONTAINER_ID);
        containerModel.setName(CONTAINER_NAME);
        containerModel.setVersion(CONTAINER_VERSION);
        containerModel.setCreated(CONTAINER_CREATED);
        results.add(containerModel);
        result.setResult(results);
        containerModel = new ContainerModel();
        containerModel.setId(CONTAINER_ID2);
        containerModel.setName(CONTAINER_NAME);
        containerModel.setVersion(CONTAINER_VERSION);
        containerModel.setCreated(CONTAINER_CREATED);
        results.add(containerModel);
        result.setResult(results);

        given(containerService.findContainerByVersion(anyLong(), any())).willReturn(result);

        mockMvc.perform(get("/container/findByVersion")
                .param("version", String.valueOf(CONTAINER_VERSION))
                .param("pageSize", "20")
                .param("pageNumber", "0")
                .param("sortProperty", "name")
                .param("sortOrder", "desc"))
                .andExpect(jsonPath("totalSize", is(2)))
                .andExpect(jsonPath("result[0].id", is(CONTAINER_ID)))
                .andExpect(jsonPath("result[1].id", is(CONTAINER_ID2)))
                .andExpect(jsonPath("result[0].name", is(CONTAINER_NAME)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findContainerByVersionInvalidSortField() throws Exception {
        given(paginationConfiguration.getPageSize()).willReturn(25);
        mockMvc.perform(get("/container/findByVersion")
                .param("version", String.valueOf(CONTAINER_VERSION))
                .param("sortProperty", "abc"))
                .andExpect(status().isOk());
    }

    @Test
    public void findContainerByVersionInvalidParam() throws Exception {
        mockMvc.perform(get("/container/findByVersion")
                .param("version", "abc"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void findContainerByVersionMissingParam() throws Exception {
        mockMvc.perform(get("/container/findByVersion"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getContainerOkStatus() throws Exception {
        final ContainerModel containerModel = new ContainerModel();
        containerModel.setId(CONTAINER_ID);
        containerModel.setName(CONTAINER_NAME);
        containerModel.setVersion(CONTAINER_VERSION);
        containerModel.setCreated(CONTAINER_CREATED);

        given(containerService.getContainer(anyString())).willReturn(containerModel);

        mockMvc.perform(get("/container/{}", CONTAINER_ID))
                .andExpect(jsonPath("id").value(CONTAINER_ID))
                .andExpect(jsonPath("name").value(CONTAINER_NAME))
                .andExpect(jsonPath("version").value(CONTAINER_VERSION))
                .andExpect(status().isOk());

    }

    @Test
    public void getContainerNotFound() throws Exception {
        given(containerService.getContainer(anyString())).willThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/container/{}", CONTAINER_ID)).andExpect(status().isNotFound());
    }

}
