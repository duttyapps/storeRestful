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
package com.duttyapps.storerestful.service;

import com.duttyapps.storerestful.dao.CustomerDAO;
import com.duttyapps.storerestful.domain.LoginCustomerRequest;
import com.duttyapps.storerestful.domain.LoginCustomerResponse;
import com.duttyapps.storerestful.utils.Const;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Arce Sherader <carce@duttyapps.com>
 */
@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    public LoginCustomerResponse login(LoginCustomerRequest rq) {
        LoginCustomerResponse result = new LoginCustomerResponse();
        try {
            boolean login = customerDAO.login(rq);

            if (login) {
                result.setCode(Const.SUCCESS_COD);
                result.setMsg(Const.SUCCESS_MSG);
            } else {
                result.setCode(Const.ERROR_COD);
                result.setMsg(Const.ERROR_MSG);
            }

        } catch (SQLException ex) {
            result.setCode(Const.ERROR_COD);
            result.setMsg("Database error: " + ex.getMessage());
        } catch (Exception ex) {
            result.setCode(Const.ERROR_COD);
            result.setMsg("Error: " + ex.getMessage());
        }

        return result;
    }
}
