package com.hj.mdmng.backend.integration.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by heiko on 15.03.15.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true, of = "id")
@Entity
@Table(name="MDM_TAG")

public class Tag extends AbstractDomainObject {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;


}
