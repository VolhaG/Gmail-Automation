package com.sam.pages.gmail.sent;

import com.sam.pages.base.sent.Sent;
import com.sam.utils.gmail.GMailTableColumns;

import java.util.List;

public interface GMailSent extends Sent {

    List<GMailTableColumns> getGMailTable();

}
