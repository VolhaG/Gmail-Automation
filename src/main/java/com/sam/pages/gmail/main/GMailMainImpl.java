package com.sam.pages.gmail.main;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailMainImpl extends WrapElementImpl implements GMailMain {

    private static final String MAIN_PAGE_CSSLOCATOR = "div.nH";
    private static Logger log = LogManager.getLogger();
    private WrapElement title;
    private Button compose;
    private By composeBy;
    private WrapElement accountMenu;
    private Button logout;

    public GMailMainImpl() {
        super(By.cssSelector(MAIN_PAGE_CSSLOCATOR));
        title = findWrapElement("title", By.xpath("//a[@class ='gb_me gb_pc gb_ke']"), ElementType.DEFAULT);
        compose = findWrapElement("compose", By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']"), ElementType.BUTTON);
        composeBy = By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']");
        accountMenu = findWrapElement("accountMenu", By.xpath("//a[@class = 'gb_z gb_Ia gb_g']/span[@class =" +
                " 'gb_Ba gbii']"), ElementType.DEFAULT);
        logout = findWrapElement("logout", By.xpath("//div[@class = 'gb_5f gb_sb']/div/a[@class =" +
                " 'gb_4 gb_8f gb_gg gb_Qe gb_tb']"), ElementType.BUTTON);
    }

    @Override
    public void clickCompose() {
        //try to find element BUTTON
        //driver.findElement(...)
        //Click on BTN_NAME
        ElementWaiters.wait(7);
        log.info("Click on " + compose.getElementName());
        compose.click();
    }

    @Override
    public String getTitle() {
        return title.getAttribute("title");
    }

    @Override
    public void openAccountMenu() {
        log.info("Click on " + accountMenu.getElementName());
        accountMenu.click();
    }

    @Override
    public void clickLogout() {
        log.info("Click on " + logout.getElementName());
        logout.click();
    }

    @Override
    public Boolean existsDefElement() {
        ElementWaiters.wait(7);
        return existsDefElement(composeBy, 20);
    }

}
