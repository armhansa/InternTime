package com.armhansa.app.testaac.model;

import java.util.Date;

public interface Comment {

    int getId();
    int getProductId();
    String getText();
    Date getPostedAt();

}
