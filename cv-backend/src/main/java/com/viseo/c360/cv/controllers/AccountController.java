package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class AccountController {


    @Autowired
    private AccountService accountService;

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping("/login")
    public UsersEntity login(@RequestParam(value = "mail") @NotEmpty String mail,
                             @RequestParam(value = "password") @NotEmpty String password) {
        return this.accountService.exist(mail, password);
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/register", method = POST)
    public UsersEntity register(@RequestBody @Valid UserDto user){
        try{
            UsersEntity usersEntity = this.accountService.add(user);
            return usersEntity;
        }
        catch (DataIntegrityViolationException e){
            throw new RuntimeException(e.getCause().getCause());
        }
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getUser", method = GET)
    public UsersEntity getUser(@RequestParam(value = "id") @NotEmpty int id) {

        return this.accountService.getUserById(id);
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/getUsers", method = GET)
    public List<UsersEntity> getAll() {
        return this.accountService.getAll();
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/updateUser", method = POST)
    public UsersEntity updateUser(@RequestBody @Valid UserDto user) {

        return this.accountService.updateUser(user);
    }
}
