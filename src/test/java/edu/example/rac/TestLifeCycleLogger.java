package edu.example.rac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;



@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifeCycleLogger {

    static final Logger log = LogManager.getLogger(TestLifeCycleLogger.class);

    @BeforeAll
    default void beforeAllTests(TestInfo info) {
        log.info(String.format("START tests: [%s]", info.getTestClass().get()));
    }

    @AfterAll
    default void afterAllTests(TestInfo info) {
        log.info(String.format("END tests: [%s]", info.getTestClass().get()));
    }

    @BeforeEach
    default void beforeEachTest(TestInfo info) {
        log.info(() -> String.format("START test: [%s]", info.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo info) {
        log.info(() -> String.format("END test: [%s]", info.getDisplayName()));
    }
}
