package org.plateer.backlms.rolling.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_rolling")
@EqualsAndHashCode(callSuper=false, of="id")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rolling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rno")
    private Long id;

    private String title;

    private String writer;

    private String target;

    private String imgSrc;

    private LocalDate createDt;

    private LocalDate updateDt;
}
