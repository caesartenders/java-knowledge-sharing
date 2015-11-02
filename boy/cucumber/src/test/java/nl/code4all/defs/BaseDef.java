package nl.code4all.defs;

import nl.code4all.selenium.Selenium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration("classpath:cucumber.xml")
public abstract class BaseDef {

    @Autowired
    protected Selenium selenium;
}
