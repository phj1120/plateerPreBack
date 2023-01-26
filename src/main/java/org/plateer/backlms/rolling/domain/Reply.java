package org.plateer.backlms.rolling.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reply")
@EqualsAndHashCode(callSuper = false, of = "id")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @OneToOne
    private Member writer;

    @ManyToOne
    private Rolling rolling;

    public Reply(String title, String content, Member writer, Rolling rolling) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.rolling = rolling;
    }
}
