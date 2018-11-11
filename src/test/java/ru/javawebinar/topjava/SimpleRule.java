package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(SimpleRule.class);


    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                log.info("1");
                base.evaluate();
                log.info("2");
            }
        };

    }
}
