package com.regitiny.catiny.web.rest;

import com.regitiny.catiny.advance.service.AccountStatusAdvanceService;
import com.regitiny.catiny.advance.service.MasterUserAdvanceService;
import com.regitiny.catiny.domain.User;
import com.regitiny.catiny.repository.UserRepository;
import com.regitiny.catiny.security.SecurityUtils;
import com.regitiny.catiny.service.MailService;
import com.regitiny.catiny.service.UserService;
import com.regitiny.catiny.service.dto.AdminUserDTO;
import com.regitiny.catiny.service.dto.PasswordChangeDTO;
import com.regitiny.catiny.web.rest.errors.EmailAlreadyUsedException;
import com.regitiny.catiny.web.rest.errors.InvalidPasswordException;
import com.regitiny.catiny.web.rest.errors.LoginAlreadyUsedException;
import com.regitiny.catiny.web.rest.vm.KeyAndPasswordVM;
import com.regitiny.catiny.web.rest.vm.ManagedUserVM;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountResource
{

  private final Logger log = LoggerFactory.getLogger(AccountResource.class);
  private final UserRepository userRepository;
  private final UserService userService;
  private final MailService mailService;
  private final MasterUserAdvanceService masterUserAdvanceService;
  private final AccountStatusAdvanceService accountStatusAdvanceService;

  private static boolean isPasswordLengthInvalid(String password)
  {
    return (
      StringUtils.isEmpty(password) ||
        password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
        password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
    );
  }

  /**
   * {@code POST  /register} : register the user.
   *
   * @param managedUserVM the managed user View Model.
   * @throws InvalidPasswordException  {@code 400 (Bad Request)} if the password is incorrect.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
   */
  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
    if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
      throw new InvalidPasswordException();
    }
    User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());
    masterUserAdvanceService.createMasterUser(user.getLogin());
    mailService.sendActivationEmail(user);
  }

  /**
   * {@code GET  /activate} : activate the registered user.
   *
   * @param key the activation key.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
   */
  @GetMapping("/activate")
  public void activateAccount(@RequestParam(value = "key") String key) {
    Optional<User> user = userService.activateRegistration(key);
    if (!user.isPresent()) {
      throw new AccountResourceException("No user was found for this activation key");
    }
  }

  /**
   * {@code GET  /authenticate} : check if the user is authenticated, and return its login.
   *
   * @param request the HTTP request.
   * @return the login if the user is authenticated.
   */
  @GetMapping("/authenticate")
  public String isAuthenticated(HttpServletRequest request) {
    log.debug("REST request to check if the current user is authenticated");
    return request.getRemoteUser();
  }

  /**
   * {@code GET  /account} : get the current user.
   *
   * @return the current user.
   * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
   */
  @GetMapping("/account")
  public AdminUserDTO getAccount(Device device)
  {
    accountStatusAdvanceService.onlineStatus(device);
    return userService
      .getUserWithAuthorities()
      .map(AdminUserDTO::new)
      .orElseThrow(() -> new AccountResourceException("User could not be found"));
  }

  /**
   * {@code POST  /account} : update the current user information.
   *
   * @param userDTO the current user information.
   * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
   * @throws RuntimeException          {@code 500 (Internal Server Error)} if the user login wasn't found.
   */
  @PostMapping("/account")
  public void saveAccount(@Valid @RequestBody AdminUserDTO userDTO) {
    String userLogin = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new AccountResourceException("Current user login not found"));
    Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
    if (existingUser.isPresent() && (!existingUser.get().getLogin().equalsIgnoreCase(userLogin))) {
      throw new EmailAlreadyUsedException();
    }
    Optional<User> user = userRepository.findOneByLogin(userLogin);
    if (!user.isPresent()) {
      throw new AccountResourceException("User could not be found");
    }
    userService.updateUser(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail(), userDTO.getLangKey(), userDTO.getImageUrl());
  }

  /**
   * {@code POST  /account/change-password} : changes the current user's password.
   *
   * @param passwordChangeDto current and new password.
   * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
   */
  @PostMapping(path = "/account/change-password")
  public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
    if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
      throw new InvalidPasswordException();
    }
    userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
  }

  /**
   * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
   *
   * @param mail the mail of the user.
   */
  @PostMapping(path = "/account/reset-password/init")
  public void requestPasswordReset(@RequestBody String mail) {
    Optional<User> user = userService.requestPasswordReset(mail);
    if (user.isPresent()) {
      mailService.sendPasswordResetMail(user.get());
    } else {
      // Pretend the request has been successful to prevent checking which emails really exist
      // but log that an invalid attempt has been made
      log.warn("Password reset requested for non existing mail");
    }
  }

  /**
   * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
   *
   * @param keyAndPassword the generated key and the new password.
   * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
   * @throws RuntimeException         {@code 500 (Internal Server Error)} if the password could not be reset.
   */
  @PostMapping(path = "/account/reset-password/finish")
  public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
    if (isPasswordLengthInvalid(keyAndPassword.getNewPassword()))
    {
      throw new InvalidPasswordException();
    }
    Optional<User> user = userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

    if (!user.isPresent())
    {
      throw new AccountResourceException("No user was found for this reset key");
    }
  }

  private static class AccountResourceException extends RuntimeException
  {
    private AccountResourceException(String message)
    {
      super(message);
    }
  }
}
