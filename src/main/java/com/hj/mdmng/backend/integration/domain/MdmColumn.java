package com.hj.mdmng.backend.integration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by heiko on 01.03.15.
 */

@Data
@ToString(exclude = "table")
@EqualsAndHashCode(callSuper = true, exclude = "table")
@Entity
@Table(name="MDM_COLUMN")
public class MdmColumn extends AbstractDomainObject {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="display_name")
    private String displayName;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="table_id")
    @JsonIgnore
    private MdmTable table;

    @NotNull
    @Column(name="DATA_TYPE")
    @Enumerated(EnumType.STRING)
    private DataType dataType;


    @NotNull
    @Column(name="IS_PRIMARY_KEY")
    private boolean primaryKey;


}
