package com.gsp.app.context;

import com.gsp.app.model.User;

/**
 * fengzhix.xu on 2018/5/19.
 */
public class AppContext {
    private static final InheritableThreadLocal<User> user = new InheritableThreadLocal<>();

    public static void setUser(User u) {
        user.set(u);
    }

    public static User getUser() {
        return user.get();
    }

    public static void clear() {
        user.remove();
    }


}
