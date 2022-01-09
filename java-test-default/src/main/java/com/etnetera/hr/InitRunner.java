package com.etnetera.hr;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.etnetera.hr.data.HypeLevel;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

@Component
public class InitRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitRunner.class);

    @Autowired
    private JavaScriptFrameworkRepository frameworkRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        logger.info("initializing users");

        var u1 = new JavaScriptFramework("React", "1.2.3", LocalDate.of(2020, 12, 31), HypeLevel.LOW);
        frameworkRepository.save(u1);

        var u2 = new JavaScriptFramework("Angular", "3.4.5", LocalDate.of(2021, 12, 31), HypeLevel.MEDIUM);
        frameworkRepository.save(u2);

        var u3 = new JavaScriptFramework("React", "2.3.3", LocalDate.of(2022, 12, 31), HypeLevel.HIGH);
        frameworkRepository.save(u3);
    }
}