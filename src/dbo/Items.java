/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.util.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoang Pham
 */
public class Items extends Vector<Item> {
    
    final int SUPPLYING;
    final int NOTSUPPLYING;
    
    public Items() {
        SUPPLYING = 1;
        NOTSUPPLYING = 2;
    }
    
    public Items(int SUPPLYING, int NOTSUPPLYING) {
        this.SUPPLYING = SUPPLYING;
        this.NOTSUPPLYING = NOTSUPPLYING;
    }
    
    public int find(String itemCode) {
        for (int i = 0; i < this.size(); i++) {
            if (itemCode.equals(this.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }
    
    public Item findItem(String itemCode) {
        int i = find(itemCode);
        return i < 0 ? null : this.get(i);
    }
    
    public void loadFromDB(ItemDBAccess dbObj, Suppliers suppliers, int supply) {
        String itemCode = "";
        String itemName = "";
        String supplierCode = "";
        String unit = "";
        String sql = "";
        int price = 0;
        boolean supplying;
        if (supply == SUPPLYING) {
            sql = "select * from Items where supplying=true";
            
        } else if (supply == NOTSUPPLYING) {
            sql = "select * from Items where supplying=false";
            
        } else {
            sql = "select * from Items";
        }
        try {
            ResultSet rs = dbObj.executeQuery(sql);
            while (rs.next()) {
                itemCode = rs.getString(1);
                itemName = rs.getString(2);
                supplierCode = rs.getString(3);
                Supplier supplier = suppliers.findSupplier(supplierCode);
                unit = rs.getString(4);
                price = rs.getInt(5);
                supplying = rs.getBoolean(6);
                
                Item item = new Item(itemCode, itemName, supplier, unit, price, supplying);
                this.add(item);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }
}
