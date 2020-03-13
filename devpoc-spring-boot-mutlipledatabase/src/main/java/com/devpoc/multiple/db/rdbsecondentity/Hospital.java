package com.devpoc.multiple.db.rdbsecondentity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Hospital")
public class Hospital {
    @Id
    @Column(name="HOSPITAL_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="HOSPITAL_NUMBER")
    private String hospitalNumber;
    @Column(name="BED_INFO")
    private String bedInfo;
}
