package org.plateer.backlms.rolling.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_member")
@EqualsAndHashCode(callSuper = false, of = "id")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    private String memberId;
    private String password;
    private String memberName;
    private LocalDate createDt;
    private LocalDate updateDt;

    public Member(String memberId, String password, String memberName) {
        this.memberId = memberId;
        this.password = password;
        this.memberName = memberName;
        this.createDt = LocalDate.now();
    }
}
