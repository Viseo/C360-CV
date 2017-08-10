/**
 * Created by GEH3641 on 19/07/2017.
 */

package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/login")
    public UsersEntity login(@RequestParam(value="mail")     @NotEmpty String mail,
                             @RequestParam(value="password") @NotEmpty String password) {

        return this.accountService.exist(mail, password);
    }

    @RequestMapping(path = "/register", method = POST)
    public UsersEntity register(@RequestBody @Valid UserDto user) {
        return this.accountService.add(user);
    }


    @RequestMapping(path = "/testRegister", method = POST)
    public UsersEntity testRegister(@RequestBody @Valid UserDto user) {
        UsersEntity existenceTest = this.accountService.mailExist(user.getMail());
        return existenceTest;
    }

    @RequestMapping("/getUser")
    public UsersEntity getUser(@RequestParam(value="id") @NotEmpty int id) {

        return this.accountService.getUserById(id);
    }

    @RequestMapping(path = "/getUsers", method = GET)
    public List<UsersEntity> getAll(){
        return this.accountService.getAll();

    }
}
