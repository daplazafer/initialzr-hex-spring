package com.ggroupid.aartifactid.h2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hello_world_locales")
public class HelloWorldEntity {

    @Id
    private Long id;
    private String languageCode;
    private String message;

}
