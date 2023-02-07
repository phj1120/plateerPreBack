package org.zerock.api01.common.image;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest
class ImageSchedulerTest {

    @Autowired
    ImageScheduler imageScheduler;

    @Test
    @Rollback(value = false)
    public void deleteImages() {
        List<String> result = imageScheduler.deleteImages();

        System.out.println("[Delete Image (" + result.size() + ")]: " + result);
    }

}