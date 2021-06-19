package com.ibm.project.service.container.domains;

import java.time.ZonedDateTime;

import lombok.Builder;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Container entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Container {

    @Id
    private String id;

    private String name;

    private Long version;

    private ZonedDateTime created;
}
