package com.sam.pages.gmail.main;

import com.sam.webelement.*;
import org.openqa.selenium.By;

public class GMailMainImpl extends WrapElementImpl implements GMailMain {

    private static final String MAIN_PAGE_CSSLOCATOR = "div.nH";

    private WrapElement title;
    private Button compose;
    private By composeBy;
    private WrapElement accountMenu;
    private Button logout;

    public GMailMainImpl() {
        super(By.cssSelector(MAIN_PAGE_CSSLOCATOR));
        title = this.findWrapElement(By.xpath("//a[@class ='gb_me gb_pc gb_ke']"));
        compose = (Button) this.findWrapElement(By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']"));
        composeBy = By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']");
        accountMenu = this.findWrapElement(By.xpath("//a[@class = 'gb_z gb_Ia gb_g']/span[@class =" +
                " 'gb_Ba gbii']"));
        logout = (Button) this.findWrapElement(By.xpath("//div[@class = 'gb_5f gb_sb']/div/a[@class =" +
                " 'gb_4 gb_8f gb_gg gb_Qe gb_tb']"));
    }

    @Override
    public void clickCompose() {
        //try to find element BUTTON
        //driver.findElement(...)
        //Click on BTN_NAME
        // WebElement el = ElementWaiters.waitForPresence(composeBy,20);
        ElementWaiters.wait(7);
        compose.click();
    }

    @Override
    public String getTitle() {
  //      WebElement titleElement = this.findElement(By.xpath("//a[@class ='gb_me gb_pc gb_ke']"));
        return title.getAttribute("title");
    }

    @Override
    public void openAccountMenu() {
        accountMenu.click();
    }

    @Override
    public void clickLogout() {
        logout.click();
    }

    @Override
    public Boolean existsDefElement() {
        ElementWaiters.wait(7);
        return existsDefElement(composeBy, 20);
    }

}
