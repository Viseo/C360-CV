package com.viseo.c360.cv.controllers;

import com.viseo.c360.cv.converters.UserToDtoConverter;
import com.viseo.c360.cv.models.dto.ClientDto;
import com.viseo.c360.cv.models.dto.UserDto;
import com.viseo.c360.cv.models.entities.UsersEntity;
import com.viseo.c360.cv.services.AccountService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Value("$(jwt.secret)")
    private String secret;

    private String compactJws;
    private static final Map<String, UserDto> mapUserCache = new ConcurrentHashMap<>();

    private String createSecurityToken(UserDto user){
        return Jwts.builder()
                .setSubject(user.getMail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .claim("admin", user.getAdmin())
                .claim("id", user.getId())
                .claim("login", user.getLogin())
                .claim("mail", user.getMail())
                .claim("birth_date", user.getBirth_date())
                .claim("experience", user.getExperience())
                .claim("hobbies", user.getHobbies())
                .claim("missions", user.getMissions())
                .claim("languages", user.getLanguages())
                .claim("position", user.getPosition())
                .claim("picture",user.getPicture())
                .claim("telephone", user.getTelephone())
                //.signWith(SignatureAlgorithm.HS512, generateKey())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "mail") @NotEmpty String mail,
                             @RequestParam(value = "password") @NotEmpty String password) {
        UserDto user = new UserToDtoConverter().convert(this.accountService.exist(mail, password));
        compactJws = createSecurityToken(user);
        this.mapUserCache.put(compactJws, user);
        return compactJws;
    }

    @CrossOrigin (origins =  "${server.front}")
    @RequestMapping(path = "/register", method = POST)
    public String register(@RequestBody @Valid UserDto user){
        try{
            user.setAdmin(false);
            this.accountService.add(user);
            compactJws = createSecurityToken(user);
            this.mapUserCache.put(compactJws, user);
            return compactJws;
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
    @RequestMapping(path = "/identification", method = POST)
    @ResponseBody
    public String checkIsAlreadyConnected(@RequestBody String token) {
        System.out.println("Request successfully received! Received token : " + token);
        try{
            token = token.replace("=", "");
            UserDto user = mapUserCache.get(token);
            if (user != null){
                if (user.getAdmin()){
                    return "admin";
                }
                else{
                    return "notAdmin";
                }
            }
            else{
                return null;
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
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
