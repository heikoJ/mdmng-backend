package com.hj.mdmng.backend.integration.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by heiko on 01.03.15.
 */

@Data
@ToString(exclude="columns")
@EqualsAndHashCode(callSuper = true, exclude="columns")
@Entity
@Table(name="MDM_TABLE")
public class MdmTable extends AbstractDomainObject {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="display_name")
    private String displayName;

    @OneToMany(mappedBy="table",fetch = FetchType.EAGER )
    private Set<MdmColumn> columns;

    public MdmColumn getPrimaryKeyColumn() {
        for(MdmColumn column : columns) {
            if(column.isPrimaryKey()) {
                return column;
            }
        }

        throw new IllegalStateException("No primary key defined!");
    }


}
