package com.hj.mdmng.backend.integration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Created by heiko on 01.03.15.
 */

@Data
@ToString(exclude="columns")
@EqualsAndHashCode(callSuper = true, of="id")
@Entity
@Table(name="MDM_TABLE")
@JsonIgnoreProperties({"primaryKeyOrThrowException"})
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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "table_tags",  joinColumns = {
            @JoinColumn(name = "TABLE_ID", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "TAG_ID",
                    nullable = false, updatable = false) })
    private Set<Tag> tags;


    public Optional<MdmColumn> getPrimaryKeyColumn() {
        for(MdmColumn column : columns) {
            if(column.isPrimaryKey()) {
                return Optional.of(column);
            }
        }

        return Optional.empty();
    }

    public MdmColumn getPrimaryKeyOrThrowException () {
        return getPrimaryKeyColumn().
                orElseThrow(() -> new RuntimeException("no primary Key Column defined!"));
    }

    public boolean hasColumn(MdmColumn column) {
        return columns.contains(column);
    }


    public MdmColumn getColumnByName(String name) {
        for(MdmColumn column : columns) {
            if(column.getName().equals(name)) {
                return column;
            }
        }

        return null;
    }


}
