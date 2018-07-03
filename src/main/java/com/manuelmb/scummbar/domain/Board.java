package com.manuelmb.scummbar.domain;

import org.apache.commons.lang3.Validate;

import javax.persistence.*;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private Board() {
    }

    public Board(String name) {
        Validate.notNull(name);
        Validate.notEmpty(name);
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
