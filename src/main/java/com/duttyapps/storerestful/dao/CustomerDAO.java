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
package com.duttyapps.storerestful.dao;

import com.duttyapps.storerestful.bean.Customer;
import com.duttyapps.storerestful.domain.LoginCustomerRequest;
import com.duttyapps.storerestful.utils.MySQLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Carlos Arce Sherader <carce@duttyapps.com>
 */
@Repository
public class CustomerDAO {
    
    @Autowired
    private MySQLConnection db;
    
    public String login(LoginCustomerRequest rq) throws SQLException, Exception {
        
        String result = "";
        
        String user = rq.getUsername();
        String pass = rq.getPassword();
        
        Connection con = db.getConnection();
        
        String Query = "SELECT ID FROM customers WHERE USERNAME='" + user + "' AND PASSWORD='" + pass + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(Query);

        while (rs.next()) {
            result = rs.getString("ID");
        }

        db.closeConnection();
        
        return result;
    }
    
    public Customer profile(String id) throws SQLException, Exception {
        
        Customer result = new Customer();
        Connection con = db.getConnection();
        
        String Query = "SELECT * FROM customers WHERE ID='" + id + "'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(Query);

        while (rs.next()) {
            result.setName(rs.getString("NAME"));
        }

        db.closeConnection();
        
        return result;
    }
    
}
