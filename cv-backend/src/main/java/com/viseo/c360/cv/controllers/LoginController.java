/**
 * Created by GEH3641 on 19/07/2017.
 */

package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public UsersEntity login(@RequestParam(value="mail")     @NotEmpty String mail,
                             @RequestParam(value="password") @NotEmpty String password) {

        return accountService.exist(mail, password);
    }
}
