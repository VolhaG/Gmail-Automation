package com.sam.components;

import com.sam.webelement.WrapElement;
import com.sam.webelement.WrapElementImpl;
import org.openqa.selenium.By;

public interface Content extends WrapElement {

   default Boolean existsDefElement(By by){
       return WrapElementImpl.exists(by);
   }

   Boolean existsDefElement();
}
