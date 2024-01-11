package org.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXPERTISE")
public class Expertise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "CAR_ID")
    private String carId;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @OneToMany(mappedBy = "expertise", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExpertiseAnswer> expertiseAnswers;




}
