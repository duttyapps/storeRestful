/*
 * The MIT License
 *
 * Copyright 2017 Carlos Arce Sherader <carce@duttyapps.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.duttyapps.storerestful.controller;

import com.duttyapps.storerestful.bean.Customer;
import com.duttyapps.storerestful.domain.LoginCustomerRequest;
import com.duttyapps.storerestful.domain.LoginCustomerResponse;
import com.duttyapps.storerestful.service.CustomerService;
import com.duttyapps.storerestful.utils.Const;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos Arce Sherader <carce@duttyapps.com>
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces="application/json", consumes="application/json")
    public LoginCustomerResponse login(@RequestBody LoginCustomerRequest rq, HttpSession session) {
        LoginCustomerResponse result = new LoginCustomerResponse();
        result = customerService.login(rq);
        
        if(result.getCode().equals(Const.SUCCESS_COD)) {
            session.setAttribute("user_id", result.getId());
        }
        
        return result;
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.POST, headers = "Accept=application/json")
    public Customer getCategoryById(HttpSession session) {
        String id = session.getAttribute("user_id").toString();
        Customer cat = customerService.profile(id);
        return cat;
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces="application/json")
    public LoginCustomerResponse logout(HttpSession session) {
        LoginCustomerResponse result = new LoginCustomerResponse();
        
        String response = destroySessionCustomer(session);
        
        if(response.equals(Const.SUCCESS_COD)) {
            result.setCode(Const.SUCCESS_COD);
            result.setMsg(Const.SUCCESS_MSG);
        } else {
            result.setCode(Const.ERROR_COD);
            result.setMsg(response);
        }
        
        return result;
    }
    
    private String destroySessionCustomer(HttpSession session) {
        try {
            session.removeAttribute("user_id");
            return Const.SUCCESS_COD;
        } catch(Exception ex) {
            return ex.getMessage();
        }
    }
    
}
