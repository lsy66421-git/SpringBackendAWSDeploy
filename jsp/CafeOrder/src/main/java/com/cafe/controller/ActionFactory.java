package com.cafe.controller;

import com.cafe.action.Action;
import com.cafe.action.JoinAction;
import com.cafe.action.JoinFormAction;
import com.cafe.action.LoginAction;
import com.cafe.action.LoginFormAction;
import com.cafe.action.LogoutAction;
import com.cafe.action.ProductListAction;

public class ActionFactory {
    private static ActionFactory instance = new ActionFactory();
    private ActionFactory() {}
    public static ActionFactory getInstance() { return instance; }

    public Action getAction(String command) {
        Action action = null;
        if (command.equals("login_form")) {
            action = new LoginFormAction();
        } else if (command.equals("login")) {
            action = new LoginAction();
        } else if (command.equals("join_form")) {
            action = new JoinFormAction();
        } else if (command.equals("join")) {
            action = new JoinAction();
        } else if (command.equals("logout")) {
            action = new LogoutAction();
        } else if (command.equals("product_list")) {
            action = new ProductListAction();
        }
        return action;
    }
}
