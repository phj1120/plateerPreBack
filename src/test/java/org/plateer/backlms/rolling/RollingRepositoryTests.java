package org.plateer.backlms.rolling;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.repository.RollingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RollingRepositoryTests {
    @Autowired
    RollingRepository rollingRepository;

    @Test
    public void testRollingInsert() {
        IntStream.rangeClosed(1, 125).forEach(i -> {
            Rolling rolling = Rolling.builder()
                    .title("플래티어 프로젝트 .. [" + i + "]")
                    .writer("홍길동.." + i)
                    .target("이순신" + i)
                    .imgSrc("https://source.unsplash.com/random/" + i)
                    .createDt(LocalDate.now().plusDays(i))
                    .build();

            rollingRepository.save(rolling);
        });
    }
}
