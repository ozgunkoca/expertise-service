package org.sandbox.model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXPERTISE_ANSWER_PHOTO")
public class ExpertiseAnswerPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ANSWER_ID")
    private ExpertiseAnswer expertiseAnswer;

    @Column(name = "PHOTO_URL")
    private String photoUrl;

}
