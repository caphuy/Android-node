package com.caphuy.smartclassroom.Presenter.Login;

import com.caphuy.smartclassroom.View.Login.ViewLoginHandler;

/**
 * Created by cabhuy on 10/11/2017.
 */

public class PresenterLogicLoginHandler implements PresenterImpLoginHandler {

    private ViewLoginHandler viewLoginHandler;

    public PresenterLogicLoginHandler(ViewLoginHandler viewLoginHandler) {
        this.viewLoginHandler = viewLoginHandler;
    }

    @Override
    public boolean oauth(String username, String password) {
        if (username.equals("a")) {
            viewLoginHandler.oauthSuccess();
            return  true;
        }
        viewLoginHandler.oauthFail();
        return false;
    }
}
