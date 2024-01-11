package org.sandbox.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXPERTISE_ANSWER")
public class ExpertiseAnswer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_ID")
    private ExpertiseQuestion question;

    @Column(name = "ANSWER")
    private Boolean answer;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "expertiseAnswer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ExpertiseAnswerPhoto> photos;

    @ManyToOne(fetch = FetchType.LAZY)
    private Expertise expertise;




}
