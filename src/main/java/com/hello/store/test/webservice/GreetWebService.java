package com.hello.store.test.webservice;

import javax.jws.WebService;

@WebService
public interface GreetWebService {

    String greeting(String hello);
}
