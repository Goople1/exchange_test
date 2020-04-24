package com.examplesb.demosb;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import net.befitca.authserver.oauth2.util.UserGenerator;
//import net.befitca.authserver.oauth2.util.UserGenerator2;

public class PasswordEncoderTest {

  private final static String SECRET = "MYSECRET";
  private final static String PASSWORD = "123456";
  private final static String WRONG_PASSWORD = "WRONG_PASSWORD";

  private PasswordEncoder target;


  @Before
  public void setUp() throws Exception {
    target = new BCryptPasswordEncoder();
  }

  @Test
  public void successMatchPasswordTest() {

    String encodedPassword = target.encode(PASSWORD);
    System.out.println(encodedPassword);
    assertTrue(target.matches(PASSWORD, encodedPassword));

  }

  @Test
  public void failMatchPasswordTest() {

    String encodedPassword = target.encode(PASSWORD);
    assertFalse(target.matches(WRONG_PASSWORD, encodedPassword));

  }

/*  @Test
  public void userGenerator() throws Exception {

    UserGenerator userGenerator = new UserGenerator();
    userGenerator.script(100);
  }

  @Test
  public void  userGenerator2() throws Exception {

    UserGenerator2 userGenerator2 = new UserGenerator2();
    userGenerator2.script(200);
  }*/
}

