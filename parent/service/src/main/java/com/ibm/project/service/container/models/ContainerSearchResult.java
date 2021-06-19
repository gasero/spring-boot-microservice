package com.ibm.project.service.container.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ContainerSearchResult
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-03-01T11:45:15.266+01:00")

public class ContainerSearchResult {
    @JsonProperty("totalSize")
    private Long totalSize = null;

    @JsonProperty("result")
    @Valid
    private List<ContainerModel> result = null;

    public ContainerSearchResult totalSize(Long totalSize) {
        this.totalSize = totalSize;
        return this;
    }

    /**
     * Get totalSize
     *
     * @return totalSize
     **/
    @ApiModelProperty(value = "")


    public Long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }

    public ContainerSearchResult result(List<ContainerModel> result) {
        this.result = result;
        return this;
    }

    public ContainerSearchResult addResultItem(ContainerModel resultItem) {
        if (this.result == null) {
            this.result = new ArrayList<ContainerModel>();
        }
        this.result.add(resultItem);
        return this;
    }

    /**
     * Get result
     *
     * @return result
     **/
    @ApiModelProperty(value = "")

    @Valid

    public List<ContainerModel> getResult() {
        return result;
    }

    public void setResult(List<ContainerModel> result) {
        this.result = result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainerSearchResult containerSearchResult = (ContainerSearchResult) o;
        return Objects.equals(this.totalSize, containerSearchResult.totalSize) &&
                Objects.equals(this.result, containerSearchResult.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalSize, result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ContainerSearchResult {\n");

        sb.append("    totalSize: ").append(toIndentedString(totalSize)).append("\n");
        sb.append("    result: ").append(toIndentedString(result)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

