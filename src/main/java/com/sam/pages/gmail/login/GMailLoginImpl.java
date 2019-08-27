package com.sam.pages.gmail.login;

import com.sam.webelement.Button;
import com.sam.webelement.ElementType;
import com.sam.webelement.Input;
import com.sam.webelement.WrapElementImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailLoginImpl extends WrapElementImpl implements GMailLogin {

    private static Logger log = LogManager.getLogger(GMailLoginImpl.class);
    private static final String LOGIN_PAGE_CSSLOCATOR = "div[id= 'view_container']";
    private static final By initialElement = By.cssSelector(LOGIN_PAGE_CSSLOCATOR);
    private static final int DELAY_TIME = 1;
    private Button continueBtn;
    private Input login;
    private Input password;

    GMailLoginImpl() {
        super(initialElement);
        continueBtn = findWrapElement("continue", By.cssSelector("span.CwaK9"), ElementType.BUTTON);
        login = findWrapElement("login", By.cssSelector("input[id = 'identifierId']"), ElementType.INPUT);
        password = findWrapElement("password", By.cssSelector("input[type= 'password']"), ElementType.INPUT);
    }

    public boolean existsDefElement() {
        log.info("Wait for login page identifier.. ");
        return existsDefElement(By.cssSelector("span.CwaK9"), DELAY_TIME);
    }

    @Override
    public void clickNext() {
        log.info("Click on " + continueBtn.getElementName());
        continueBtn.click();
    }

    @Override
    public void inputEmail(String email) {
        log.info("Set text " + email + "' in " + login.getElementName());
        login.setText(email);
    }

    @Override
    public void inputPassword(String pwd) {
        log.info("Set text " + pwd + "' in " + password.getElementName());
        password.setText(pwd);
    }

}
