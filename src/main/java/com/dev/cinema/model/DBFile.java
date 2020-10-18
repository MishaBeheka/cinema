package com.dev.cinema.model;

import javax.persistence.*;
@Entity
@Table(name = "files")
public class DBFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
