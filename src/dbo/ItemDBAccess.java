/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

/**
 *
 * @author Hoang Pham
 */
public class ItemDBAccess extends DBAccess {

    final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final String url = "";

    public ItemDBAccess() {
        super();
        connectDB(driver, url);
    }
}
