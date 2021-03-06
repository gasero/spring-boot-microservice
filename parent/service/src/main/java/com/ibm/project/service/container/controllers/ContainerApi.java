/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.2).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.ibm.project.service.container.controllers;

import com.ibm.project.service.container.models.ContainerModel;
import com.ibm.project.service.container.models.ContainerSearchResult;
import com.ibm.project.service.container.models.ModelApiResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-04T10:19:50" +
        ".633+01:00")

@Api(value = "container", description = "the container API")
public interface ContainerApi {

    @ApiOperation(value = "Finds container by name and version", nickname = "findContainerByNameAndVersion", notes =
            "", response = ContainerSearchResult.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags = {"container",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ContainerSearchResult.class),
            @ApiResponse(code = 400, message = "Invalid version value", response = ModelApiResponse.class),
            @ApiResponse(code = 500, message = "An unexpected error occured.", response = ModelApiResponse.class)})
    @RequestMapping(value = "/container/findByNameAndVersion",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ContainerSearchResult findContainerByNameAndVersion(@NotNull @ApiParam(value = "Container name " +
            "that needs to be considered for filter", required = true) @Valid @RequestParam(value = "name", required
            = true) String name, @NotNull @ApiParam(value = "Version value that needs to be considered for filter",
            required = true) @Valid @RequestParam(value = "version", required = true) Long version, @ApiParam(value =
            "Number of records returned") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @ApiParam(value = "Page number") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber, @ApiParam(value = "Sort by this property name") @Valid @RequestParam(value = "sortProperty", required = false) String sortProperty, @ApiParam(value = "Sort order:  * asc - Ascending, from A to Z.  * desc - Descending, from Z to A. ", allowableValues = "asc, desc") @Valid @RequestParam(value = "sortOrder", required = false) String sortOrder);


    @ApiOperation(value = "Finds container by version", nickname = "findContainerByVersion", notes = "", response =
            ContainerSearchResult.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags = {"container",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ContainerSearchResult.class),
            @ApiResponse(code = 400, message = "Invalid version value", response = ModelApiResponse.class),
            @ApiResponse(code = 500, message = "An unexpected error occured.", response = ModelApiResponse.class)})
    @RequestMapping(value = "/container/findByVersion",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ContainerSearchResult findContainerByVersion(@NotNull @ApiParam(value = "Version value that needs" +
            " to be considered for filter", required = true) @Valid @RequestParam(value = "version", required = true) Long version, @ApiParam(value = "Number of records returned") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize, @ApiParam(value = "Page number") @Valid @RequestParam(value = "pageNumber", required = false) Integer pageNumber, @ApiParam(value = "Sort by this property name") @Valid @RequestParam(value = "sortProperty", required = false) String sortProperty, @ApiParam(value = "Sort order:  * asc - Ascending, from A to Z.  * desc - Descending, from Z to A. ", allowableValues = "asc, desc") @Valid @RequestParam(value = "sortOrder", required = false) String sortOrder);


    @ApiOperation(value = "Find container by ID", nickname = "getContainerById", notes = "Returns a single container"
            , response = ContainerModel.class, authorizations = {
            @Authorization(value = "Bearer")
    }, tags = {"container",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "successful operation", response = ContainerModel.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied", response = ModelApiResponse.class),
            @ApiResponse(code = 404, message = "The specified resource was not found", response =
                    ModelApiResponse.class),
            @ApiResponse(code = 500, message = "An unexpected error occured.", response = ModelApiResponse.class)})
    @RequestMapping(value = "/container/{containerId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ContainerModel getContainerById(@ApiParam(value = "ID of container to return", required = true) @PathVariable("containerId") String containerId);

}
