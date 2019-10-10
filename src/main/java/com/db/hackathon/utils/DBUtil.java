package com.db.hackathon.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.sql.*;

public class DBUtil {

    private Connection getConnection(){
        String userName = "Techadmin";
        String password = "Buggers123";
        Connection conn = null;
        String url = "jdbc:sqlserver://test12345.database.windows.net:1433;databaseName=EESP";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println(conn.getMetaData());
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //test
    public String executeQuery(String query) throws SQLException {

        Connection con = getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        JSONArray json = new JSONArray();
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            int numColumns = rsmd.getColumnCount();
            JSONObject obj = new JSONObject();
            for (int i=1; i<=numColumns; i++) {
                String column_name = rsmd.getColumnName(i);
                obj.put(column_name, rs.getObject(column_name));
            }
            json.put(obj);
        }
        //return json;
        return json.toString();
    }

    public String prepareSelectQuery(String mode, String type){
        String query = "select * from EESP_BASE1 where mode='"+mode+"' and type='"+type+"'";
        //String query = "select * from INFORMATION_SCHEMA.TABLES";
        return query;
    }

    public static void main(String args[]){
        DBUtil util = new DBUtil();
        try {
            String result =util.executeQuery(util.prepareSelectQuery("expense","fuel"));
            System.out.println("Result JSON is : "+ result);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
