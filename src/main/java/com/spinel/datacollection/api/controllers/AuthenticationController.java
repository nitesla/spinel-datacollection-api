package com.spinel.datacollection.api.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;


import com.spinel.datacollection.core.dto.request.auth.DataCollectionLoginRequest;
import com.spinel.datacollection.service.services.AuthenticationService;
import com.spinel.framework.loggers.LoggerUtil;
import com.spinel.framework.repositories.PermissionRepository;
import com.spinel.framework.security.AuthenticationWithToken;
import com.spinel.framework.service.*;
import com.spinel.framework.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Slf4j
@SuppressWarnings("All")
@RestController
@RequestMapping(Constants.APP_CONTENT+"authenticate")
public class AuthenticationController {

    @Value("${login.attempts}")
    private int loginAttempts;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ExternalTokenService externalTokenService;


    private final UserService userService;
    private final AuditTrailService auditTrailService;
    private final PermissionRepository permissionRepository;
    private final PermissionService permissionService;
    private final UserRoleService userRoleService;
    private final AuthenticationService authenticationService;


    public AuthenticationController(UserService userService, AuditTrailService auditTrailService,
                                    PermissionRepository permissionRepository, PermissionService permissionService,
                                    UserRoleService userRoleService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.auditTrailService = auditTrailService;
        this.permissionRepository = permissionRepository;
        this.permissionService = permissionService;
        this.userRoleService = userRoleService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid DataCollectionLoginRequest loginRequest, HttpServletRequest request) throws JsonProcessingException {
        return new ResponseEntity<>(authenticationService.login(loginRequest, request), HttpStatus.OK);
    }



//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) throws JsonProcessingException {
//
//        log.info(":::::::::: login Request %s:::::::::::::" + loginRequest.getUsername());
//        String loginStatus;
//        String ipAddress = Utility.getClientIp(request);
//        User user = userService.loginUser(loginRequest);
//        if (user != null) {
//            if (user.isLoginStatus()) {
//                //FIRST TIME LOGIN
//                if (user.getPasswordChangedOn() == null) {
//                    Response resp = new Response();
//                    resp.setCode(CustomResponseCode.CHANGE_P_REQUIRED);
//                    resp.setDescription("Change password Required, account has not been activated");
//                    return new ResponseEntity<>(resp, HttpStatus.ACCEPTED);//202
//                }
//                if (user.getIsActive()==false) {
//                    Response resp = new Response();
//                    resp.setCode(CustomResponseCode.FAILED);
//                    resp.setDescription("User Account Deactivated, please contact Administrator");
//                    return new ResponseEntity<>(resp, HttpStatus.INTERNAL_SERVER_ERROR);
//                }
//                if (user.getLoginAttempts() >= loginAttempts || user.getLockedDate() != null) {
//                    // lock account after x failed attempts or locked date is not null
//                    userService.lockLogin(user.getId());
//                    throw new LockedException(CustomResponseCode.LOCKED_EXCEPTION, "This account has been locked, Kindly contact support");
//                }
////                userService.validateGeneratedPassword(user.getId());
//            } else {
//                //update login failed count and failed login date
//                loginStatus = "failed";
//                auditTrailService
//                        .logEvent(loginRequest.getUsername(), "Login by username :" + loginRequest.getUsername()
//                                        + " " + loginStatus,
//                                AuditTrailFlag.LOGIN, "Failed Login Request by :" + loginRequest.getUsername(),1, ipAddress);
//                userService.updateFailedLogin(user.getId());
//                throw new UnauthorizedException(CustomResponseCode.UNAUTHORIZED, "Invalid Login details.");
//            }
//        } else {
//            //NO NEED TO update login failed count and failed login date SINCE IT DOES NOT EXIST
//            throw new UnauthorizedException(CustomResponseCode.UNAUTHORIZED, "Login details does not exist");
//        }
//
//        String accessList = permissionService.getPermissionsByUserId(user.getId());
//        AuthenticationWithToken authWithToken = new AuthenticationWithToken(user, null,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER,"+accessList));
//        String newToken = "Bearer" +" "+this.tokenService.generateNewToken();
//        authWithToken.setToken(newToken);
//        tokenService.store(newToken, authWithToken);
//        SecurityContextHolder.getContext().setAuthentication(authWithToken);
//        userService.updateLogin(user.getId());
//
//       String clientId= "";
//        String referralCode="";
//        String isEmailVerified="";
////        List<com.sabi.framework.dto.responseDto.PartnersCategoryReturn> partnerCategory= null;
//
//        AccessTokenWithUserDetails details = new AccessTokenWithUserDetails(newToken, user,
//                accessList,userService.getSessionExpiry(),clientId,referralCode,isEmailVerified);
//        auditTrailService
//                .logEvent(loginRequest.getUsername(), "Login by username : " + loginRequest.getUsername(),
//                        AuditTrailFlag.LOGIN, "Successful Login Request by : " + loginRequest.getUsername() , 1, ipAddress);
//        return new ResponseEntity<>(details, HttpStatus.OK);
//    }


//    @PostMapping("/login")
//    public ResponseEntity<?> loginEnumeratorOrProjectOwner(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) {
//        AccessTokenWithUserDetails details = authenticationService.loginUser(loginRequest, request);
//        return new ResponseEntity<>(details, HttpStatus.OK);
//    }
//
//    @PostMapping("/admin/login")
//    public ResponseEntity<?> loginAdminUser(@RequestBody @Valid LoginRequest loginRequest, HttpServletRequest request) throws JsonProcessingException {
//        AccessTokenWithUserDetails details = authenticationService.loginAdminUser(loginRequest, request);
//        return new ResponseEntity<>(details, HttpStatus.OK);
//    }


    @PostMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public boolean logout() {
        try {
            AuthenticationWithToken auth = (AuthenticationWithToken) SecurityContextHolder.getContext().getAuthentication();
            return tokenService.remove(auth.getToken());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            LoggerUtil.logError(log, ex);
        }
        return false;
    }



}
