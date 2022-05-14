package cc.w0rm.crypto.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CryptoServiceImplTest {

    static List<Integer> ints;

    static {
        ints = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            ints.add(i);
        }
    }

    @Test
    public void test1() throws Exception {
        Flux<Integer> flux = Flux.fromIterable(ints);
        long l = Instant.now().toEpochMilli();
        flux.subscribe(i -> {
            if (i == 9999) {
                System.out.println(Instant.now().toEpochMilli() - l);
            }
        });
    }

    @Test
    public void test2() throws Exception {
        long l = Instant.now().toEpochMilli();
        for (Integer i : ints) {
            if (i == 9999) {
                System.out.println(Instant.now().toEpochMilli() - l);
            }
        }
    }

}
