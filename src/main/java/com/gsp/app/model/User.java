package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

/**
 * Created by finup on 2018/5/19.
 */
@Data
@ToString
public class User {
    private Integer id;
    private String user;
    private String password;

}
