package ru.javawebinar.topjava;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class SimpleRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(SimpleRule.class);

    private static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public Statement apply(Statement base, Description description) {

        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long a = System.nanoTime();
                long b = System.nanoTime();
                log.info(description.getMethodName() + " - " + (b - a) + " ns");
                arrayList.add(description.getMethodName() + (b - a));
            }
        };

    }

    public static ArrayList<String> getArrayList() {
        return arrayList;
    }
}
