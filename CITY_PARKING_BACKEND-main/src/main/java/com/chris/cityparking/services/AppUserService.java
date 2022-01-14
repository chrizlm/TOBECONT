package com.chris.cityparking.services;

import com.chris.cityparking.controllers.Forms.EmailPasswordForm;
import com.chris.cityparking.modules.AppUser;
import com.chris.cityparking.modules.LogInForm;
import com.chris.cityparking.modules.Role;

import java.util.List;
import java.util.Map;

public interface AppUserService {
    String userLogin(LogInForm logInForm);
    AppUser saveAppUser(AppUser appUser);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    AppUser getAppUser(String userName);
    List<AppUser> getAppUsers();
    void changeUserPassword(EmailPasswordForm emailPasswordForm);

}
