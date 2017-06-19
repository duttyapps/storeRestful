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

import com.duttyapps.storerestful.bean.Categories;
import com.duttyapps.storerestful.bean.Category;
import com.duttyapps.storerestful.dao.CategoryDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Arce Sherader <carce@duttyapps.com>
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryDAO categoryDAO;
    
    public Categories getCategories() {
        Categories categories = new Categories();
        categoryDAO = new CategoryDAO();
        try {
            ArrayList<Category> mCategories = categoryDAO.getCategories();
            
            for (int i = 0; i < mCategories.size(); i++) {
                Category cat = new Category();
                
                cat.setId(mCategories.get(i).getId());
                cat.setName(mCategories.get(i).getName());
                categories.getCategories().add(cat);
            }

            categories.setCode("00");
            
        } catch (ClassNotFoundException | SQLException ex) {
            categories.setCode("-1");
            categories.setMsg(ex.getMessage());
        }
        
        return categories;
    }
}
