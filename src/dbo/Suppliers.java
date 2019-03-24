/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbo;

import java.util.Vector;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Hoang Pham
 */
public class Suppliers extends Vector<Supplier> {

    public Suppliers() {
        super();
    }

    public int find(String supCode) {
        for (int i = 0; i < this.size(); i++) {
            if (supCode.equals(this.get(i).getSupCode())) {
                return i;
            }
        }
        return -1;
    }

    public Supplier findSupplier(String supCode) {
        int i = find(supCode);
        return i < 0 ? null : this.get(i);
    }

    public void loadFromDB(ItemDBAccess dbObj) {
        String supCode = "";
        String supName = "";
        String address = "";
        boolean collab;

        String sql = "select * from Suppliers";
        try {
            ResultSet rs = dbObj.executeQuery(sql);
            while (rs.next()) {
                supCode = rs.getString(1);
                supName = rs.getString(2);
                address = rs.getString(3);
                collab = rs.getBoolean(4);
                
                Supplier supplier = new Supplier(supCode, supName, address, collab);
                this.add(supplier);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }
}
