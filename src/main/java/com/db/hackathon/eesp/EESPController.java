package com.db.hackathon.eesp;

import com.db.hackathon.utils.DBUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@RestController
public class EESPController {
    @CrossOrigin(origins="*", allowedHeaders="*")
    @RequestMapping("/getExpenseControlInfo")
    public String getExpenseControlInfo() {
        DBUtil utils = new DBUtil();
        String json = null;
        try {
            json = utils.executeQuery(utils.prepareSelectQuery("expense","fuel"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return json;
    }

    @CrossOrigin(origins="*", allowedHeaders="*")
    @RequestMapping("/getIncomeControlInfo")
    public String getIncomeControlInfo() {
        DBUtil utils = new DBUtil();
        String json = null;
        try {
            json = utils.executeQuery(utils.prepareSelectQuery("income",null));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return json;
    }
}
