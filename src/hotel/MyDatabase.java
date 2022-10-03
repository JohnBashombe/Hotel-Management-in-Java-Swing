/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ntavigwa
 */
public class MyDatabase {

    public static Connection connection;
    public static PreparedStatement prepare;
    public static Statement statement;
    public static ResultSet result;

    public static String url = "jdbc:mysql://localhost:3306/HotelManagement";
    public static String user = "root";
    public static String pass = "";

//    static String name, price, capac;
    public MyDatabase() {

        try {

            connection = DriverManager.getConnection(url, user, pass);

        } catch (SQLException exception) {

            JOptionPane.showMessageDialog(null, "Can't connect to the Database, \n"
                    + "The error is : " + exception.getMessage());

        }

    }

    public static void userList() {

        try {

            String query = "INSERT INTO UserList(Name, Username, Password,Position) VALUES(?,?,?,?)";

            prepare = connection.prepareStatement(query);
            prepare.setString(1, Home.jTextField4.getText());
            prepare.setString(2, Home.jTextField6.getText());
            prepare.setString(3, Home.jPasswordField2.getText());
            prepare.setString(4, Home.jComboBox3.getSelectedItem().toString());

            prepare.execute();
            JOptionPane.showMessageDialog(null, "User Successfully Added in the System");

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Exception is : " + exception.getMessage());
        }

    }

    public static void selectUser() {

        try {

            String query = "SELECT * FROM UserList";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            DefaultTableModel tbl = (DefaultTableModel) Home.userTable.getModel();

            int n = tbl.getRowCount() - 1;

            for (int i = n; i >= 0; i--) {

                tbl.removeRow(i);

                if (result.isBeforeFirst()) {
                    while (result.next()) {

                        Object[] obj = {result.getString("ID"), result.getString("Name"), result.getString("Username"),
                            result.getString("Position")};
                        tbl.addRow(obj);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }

    }

    public static void selectGuest() {

        try {

            String query = "SELECT * FROM guestList";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            DefaultTableModel tbl = (DefaultTableModel) Home.guestTable.getModel();

            int n = tbl.getRowCount() - 1;

            for (int i = n; i >= 0; i--) {

                tbl.removeRow(i);

                if (result.isBeforeFirst()) {
                    while (result.next()) {

                        Object[] obj = {result.getInt("GID"), result.getString("FirstName"), result.getString("LastName"),
                            result.getString("Passport"), result.getString("Payment"), result.getString("Capacity"), result.getString("meal"),
                            result.getString("Email"), result.getString("DaysIn"), result.getString("RoomNum")};
                        tbl.addRow(obj);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is for that table is : " + e.getMessage());
        }

    }

    public static void guestList() {

        try {

            String query = "INSERT INTO guestList(FirstName, LastName, Passport,Payment,meal,Email,DaysIn,RoomNum,Price,Capacity)"
                    + " VALUES(?,?,?,?,?,?,?,?,?,?)";

            prepare = connection.prepareStatement(query);
            prepare.setString(1, Home.jTextField8.getText());
            prepare.setString(2, Home.jTextField9.getText());
            prepare.setString(3, Home.jTextField10.getText());
            prepare.setString(4, Home.jComboBox6.getSelectedItem().toString());
            prepare.setString(5, Home.jComboBox5.getSelectedItem().toString());
            prepare.setString(6, Home.jTextField5.getText());
            prepare.setString(7, Home.jTextField7.getText());
            prepare.setString(8, Home.jLabel173.getText());
            prepare.setString(9, Home.jLabel181.getText());
            prepare.setString(10, Home.jLabel174.getText());

            prepare.execute();
            // updateRoomStatus();
            JOptionPane.showMessageDialog(null, "Guest Successfully Added in the System");

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Exception is : " + exception.getMessage());
        }

    }

    public static void updateRoomStatus() {
        try {
            String status = "Occupied";
            String query = "UPDATE RoomConfig SET Status = '" + status + "' WHERE Name = '" + Home.jLabel64.getText() + "'";

            prepare = connection.prepareStatement(query);
            prepare.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }
    }

    public static void updateRoomStatus2(String id) {
        try {
            String status = "Available";
            String query = "UPDATE RoomConfig SET Status = '" + status + "' WHERE Name = '" + id + "'";

            prepare = connection.prepareStatement(query);
            prepare.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }
    }

    public static void selectRoom() {

        try {

            String query = "SELECT * FROM RoomConfig";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            DefaultTableModel tbl = (DefaultTableModel) Home.roomsTable.getModel();

            int n = tbl.getRowCount() - 1;

            for (int i = n; i >= 0; i--) {

                tbl.removeRow(i);

                if (result.isBeforeFirst()) {
                    while (result.next()) {

                        Object[] obj = {result.getInt("ID"), result.getString("Name"), result.getString("Price"),
                            result.getString("Capacity"), result.getString("Status")};
                        tbl.addRow(obj);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }

    }

    public static void setRoomsInCbo() {

        try {
            String query = "SELECT Name FROM RoomConfig";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            while (result.next()) {

                String name = result.getString("Name");

//                Object[] obj = {name};
                Home.cboRoomList.addItem(name);

            }

        } catch (SQLException e) {
        }

        // setRoomsInCbo2();
    }

    public static void updateRoomConfig() {
        try {

            String query = "UPDATE RoomConfig SET Price = '" + Home.jTextField2.getText() + "',"
                    + "Capacity = '" + Home.jTextField15.getText() + "' , Status = '" + Home.jComboBox1.getSelectedItem() + "'"
                    + " WHERE Name = '"
                    + Home.jLabel39.getText() + "'";
            prepare = connection.prepareStatement(query);
            prepare.execute();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error is : " + e.getMessage());
        }
    }

    public static void addRoom() {
        try {
            String query = "INSERT INTO Rooms(Name, Price, Capacity,Status)"
                    + " VALUES(?,?,?,?)";

            prepare = connection.prepareStatement(query);
            prepare.setString(1, Home.jTextField14.getText());
            prepare.setString(2, Home.jTextField11.getText());
            prepare.setString(3, Home.jTextField13.getText());
            prepare.setString(4, Home.jComboBox8.getSelectedItem().toString());

            prepare.execute();
            Home.erase();
            JOptionPane.showMessageDialog(null, "Successfully Added");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error is : " + e.getMessage());
        }
    }

    public static void deleteGuestRoom(String id) {
        try {

            String query = "DELETE FROM GuestList WHERE RoomNum = '" + id + "'";
            prepare = connection.prepareStatement(query);
            prepare.execute();
            clearAll();
            JOptionPane.showMessageDialog(null, "Guest Successfully Deleted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error is : " + e.getMessage());
        }
    }

    public static void clearAll() {

        Home.jLabel110.setText("Null");
        Home.jLabel109.setText("Null");
        Home.jLabel111.setText("Null");
        Home.jLabel112.setText("Null");
        Home.jLabel160.setText("Null");
        Home.jLabel113.setText("Null");
        //Home.jLabel183.setText("");
    }

    public static void DeleteUser(String id) {
        try {
            String query = "DELETE FROM UserList WHERE Name = '" + id + "'";

            prepare = connection.prepareStatement(query);
            prepare.execute();
            selectUser();
        } catch (SQLException e) {
        }
    }

    public static void lookFromCombo() {
        try {

            String choice = Home.cboRoomList.getSelectedItem().toString();
            String query = "SELECT * FROM GuestList WHERE RoomNum = '" + choice + "'";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if (result.isBeforeFirst()) {
                while (result.next()) {

                    Home.rooms_table.setVisible(false);
                    Home.rooms_details.setVisible(true);
                    Home.jLabel64.setText(choice);
                    Home.jLabel110.setText(result.getString("FirstName") + " " + result.getString("LastName"));
                    Home.jLabel109.setText(result.getString("Email"));
                    Home.jLabel111.setText(result.getString("Passport"));
                    Home.jLabel112.setText(result.getString("Payment"));
                    Home.jLabel160.setText(result.getString("Capacity"));
                    Home.jLabel113.setText(result.getString("DaysIn"));
                    Home.jLabel176.setText(result.getString("Price"));
                    Home.jLabel177.setText(result.getString("Capacity"));

                }

            } else {
                Home.rooms_table.setVisible(false);
                Home.cboRoomList.setVisible(false);
                Home.rooms_details.setVisible(true);
                clearAll();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error is : " + e.getMessage());
        }
    }

    public static String roomDetailsFromStatus(String id) {
        try {
            String query = "SELECT * FROM `guestlist` WHERE RoomNum = '" + id + "'";

            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if (result.isBeforeFirst()) {
                while (result.next()) {

                    Home.jLabel64.setText(id);
                    Home.jLabel110.setText(result.getString("FirstName") + " " + result.getString("LastName"));
                    Home.jLabel109.setText(result.getString("Email"));
                    Home.jLabel111.setText(result.getString("Passport"));
                    Home.jLabel112.setText(result.getString("Payment"));
                    Home.jLabel160.setText(result.getString("Capacity"));
                    Home.jLabel113.setText(result.getString("DaysIn"));
                    //Home.jLabel176.setText(result.getString("Price"));
                    //Home.jLabel177.setText(result.getString("Capacity"));
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nothing to display My big boss");
            }

            //JOptionPane.showMessageDialog(null, "No Details for this Room");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error is : " + e.getMessage());
        }
        return id;
    }

    public static Color panelChangeColor1(String id, JLabel label, JLabel label2) {

        Color color = null;

        try {
            String query = "SELECT Status,Capacity FROM RoomConfig WHERE Name = '" + id + "'";

            statement = connection.createStatement();
            result = statement.executeQuery(query);

            if (result.isBeforeFirst()) {
                while (result.next()) {

                    if ("Available".equals(result.getString("Status"))) {
                        color = new Color(61, 148, 62);
                        //label.setText("Available(" + result.getString("Capacity") + ")");
                        javax.swing.ImageIcon i = new javax.swing.ImageIcon("C:\\Users\\Ntavigwa\\Hotel Update\\src\\icons8_Available_Updates_15px.png");
                        
                        label.setIcon(i);
                        label.setText("Available");
                        label2.setForeground(new Color(61, 148, 62));
                    }
                    if ("Occupied".equals(result.getString("Status"))) {
                        color = new Color(182, 0, 0);
                        //label.setText("Occupied(" + result.getString("Capacity") + ")");
                        javax.swing.ImageIcon i = new javax.swing.ImageIcon("C:\\Users\\Ntavigwa\\Hotel Update\\src\\icons8_User_15px.png");
                        
                        label.setIcon(i);
                        label.setText("Occupied");
                        label2.setForeground(new Color(182, 0, 0));
                    }
                    if ("Cleaning".equals(result.getString("Status"))) {
                        color = new Color(0, 106, 255);
                        javax.swing.ImageIcon i = new javax.swing.ImageIcon("C:\\Users\\Ntavigwa\\Hotel Update\\src\\icons8_Housekeeper_20px.png");
                        
                        label.setIcon(i);
                        //label.setText("Cleaning(" + result.getString("Capacity") + ")");
                        label.setText("Cleaning");
                        label2.setForeground(new Color(0, 106, 255));
                    }
                    if ("Has Problem".equals(result.getString("Status"))) {
                        color = new Color(255, 155, 0);
                        //label.setText("Problem(" + result.getString("Capacity") + ")");
                        javax.swing.ImageIcon i = new javax.swing.ImageIcon("C:\\Users\\Ntavigwa\\Hotel Update\\src\\icons8_Error_20px_1.png");
                        
                        label.setIcon(i);
                        label.setText("Problem");
                        label2.setForeground(new Color(255, 155, 0));
                    }

                }

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }
        return color;
    }

    public static void panelColor1() {

        Home.jPanel2.setBackground(panelChangeColor1(Home.jLabel81.getText(), Home.jLabel10, Home.jLabel81));
        Home.jPanel7.setBackground(panelChangeColor1(Home.jLabel85.getText(), Home.jLabel12, Home.jLabel85));
        Home.jPanel8.setBackground(panelChangeColor1(Home.jLabel83.getText(), Home.jLabel14, Home.jLabel83));
        Home.jPanel9.setBackground(panelChangeColor1(Home.jLabel84.getText(), Home.jLabel16, Home.jLabel84));
        Home.jPanel10.setBackground(panelChangeColor1(Home.jLabel82.getText(), Home.jLabel18, Home.jLabel82));
        Home.jPanel11.setBackground(panelChangeColor1(Home.jLabel74.getText(), Home.jLabel20, Home.jLabel74));
        Home.jPanel12.setBackground(panelChangeColor1(Home.jLabel80.getText(), Home.jLabel22, Home.jLabel80));
        Home.jPanel13.setBackground(panelChangeColor1(Home.jLabel75.getText(), Home.jLabel24, Home.jLabel75));
        Home.jPanel14.setBackground(panelChangeColor1(Home.jLabel72.getText(), Home.jLabel26, Home.jLabel72));
        Home.jPanel15.setBackground(panelChangeColor1(Home.jLabel73.getText(), Home.jLabel28, Home.jLabel73));
        Home.jPanel16.setBackground(panelChangeColor1(Home.jLabel77.getText(), Home.jLabel30, Home.jLabel77));
        Home.jPanel17.setBackground(panelChangeColor1(Home.jLabel76.getText(), Home.jLabel32, Home.jLabel76));
        Home.jPanel18.setBackground(panelChangeColor1(Home.jLabel78.getText(), Home.jLabel34, Home.jLabel78));
        Home.jPanel19.setBackground(panelChangeColor1(Home.jLabel79.getText(), Home.jLabel36, Home.jLabel79));

    }

    public static void roomStatus() {
        try {
            String query = "SELECT * FROM RoomConfig WHERE Name = '" + Home.jLabel64.getText() + "'";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            while (result.next()) {

                //Home.jLabel173.setText(result.getString("Name"));
                Home.jLabel181.setText(result.getString("Price"));
                Home.jLabel174.setText(result.getString("Capacity"));
                Home.jLabel175.setText(result.getString("Status"));

            }

        } catch (SQLException e) {
        }
    }

    public static void deleteGuestRoomUpdate(String id) {
        try {

            String query = "DELETE FROM GuestList WHERE Name = '" + id + "'";
            prepare = connection.prepareStatement(query);
            prepare.execute();
        } catch (SQLException e) {
        }
    }

    public static String selectCapacity(String id) {

        try {

            String query = "SELECT * FROM RoomConfig WHERE Name = '" + id + "'";
            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                Home.jLabel64.setText(result.getString("Name"));
                Home.jLabel177.setText(result.getString("Capacity"));
                Home.jLabel183.setText(result.getString("Status"));
                Home.jLabel176.setText(result.getString("Price"));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }
        return id;
    }

    public static void archiveTable() {

        try {

            String query = "SELECT * FROM Archive";

            statement = connection.createStatement();

            result = statement.executeQuery(query);

            DefaultTableModel tbl = (DefaultTableModel) Home.archiveTableData.getModel();

            int n = tbl.getRowCount() - 1;

            for (int i = n; i >= 0; i--) {

                tbl.removeRow(i);

                if (result.isBeforeFirst()) {
                    while (result.next()) {

                        Object[] obj = {result.getString("Name"), result.getString("FirstName"), result.getString("LastName"),
                            result.getString("Passport"), result.getString("Payment"), result.getString("Number"),
                            result.getString("DaysIn"), result.getString("DateIn")};
                        tbl.addRow(obj);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is for that table is : " + e.getMessage());
        }

    }

    public static void addToArchiveTable() {

        try {

            String query = "INSERT INTO Archive(Name,FirstName, LastName, Passport,Payment,Number,DaysIn)"
                    + " VALUES(?,?,?,?,?,?,?)";

            prepare = connection.prepareStatement(query);
            prepare.setString(1, Home.jLabel173.getText());
            prepare.setString(2, Home.jTextField8.getText());
            prepare.setString(3, Home.jTextField9.getText());
            prepare.setString(4, Home.jTextField10.getText());
            prepare.setString(5, Home.jComboBox6.getSelectedItem().toString());
            prepare.setString(6, Home.jLabel174.getText());
            prepare.setString(7, Home.jTextField7.getText());

            prepare.execute();
            // updateRoomStatus();
            // JOptionPane.showMessageDialog(null, "Guest Successfully Added in the System");

        } catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Exception is : " + exception.getMessage());
        }

    }

    public static void AllCOuntRoom() {
        try {

            int all = 0, available = 0, occupied = 0, cleaning = 0, problem = 0;

            String query = "SELECT Status FROM RoomConfig";

            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {

                all += 1;

                if ("Available".equals(result.getString("Status"))) {
                    available += 1;
                }
                if ("Occupied".equals(result.getString("Status"))) {
                    occupied += 1;
                }
                if ("Cleaning".equals(result.getString("Status"))) {
                    cleaning += 1;
                }
                if ("Has Problem".equals(result.getString("Status"))) {
                    problem += 1;
                }

                Home.jLabel94.setText(String.valueOf(all));
                Home.jLabel54.setText(String.valueOf(all));
                Home.jLabel95.setText(String.valueOf(available));
                Home.jLabel96.setText(String.valueOf(occupied));
                Home.jLabel102.setText(String.valueOf(cleaning));
                Home.jLabel103.setText(String.valueOf(problem));

                Home.jLabel55.setText(String.valueOf(available));
                Home.jLabel57.setText(String.valueOf(occupied));
                Home.jLabel61.setText(String.valueOf(cleaning));
                Home.jLabel59.setText(String.valueOf(problem));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Counting " + e.getMessage());
        }
    }

    public static void AllCOuntGuest() {
        try {

            int count = 0;

            String query = "SELECT * FROM GuestList";

            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {

                count += 1;

                Home.jLabel97.setText(String.valueOf(count));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Counting " + e.getMessage());
        }
    }

    public static void totalAmount() {
        try {

//            int sum;
//            String plus;
            String query = "SELECT Price FROM GuestList";

            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                int plus = result.getInt(1);
//                sum = Integer.parseInt(plus);
//                sum += sum;
                Home.jLabel156.setText(String.valueOf(plus));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Bad Formula " + e.getMessage());
        }
    }

    public static void totalAmountExpected() {
        try {

            //int sum;
            // String plus;
            String query = "SELECT Sum(Price) FROM RoomConfig";

            statement = connection.createStatement();
            result = statement.executeQuery(query);

            while (result.next()) {
                int plus = result.getInt(1);
//                sum = Integer.parseInt(plus);
//                sum += sum;
                Home.jLabel157.setText(String.valueOf(plus));

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Bad Formula " + e.getMessage());
        }
    }

    public static void login() {
        try {
            String query = "SELECT Name, Password, Position FROM UserList WHERE Username = '" + Home.jTextField3.getText() + "' AND "
                    + "Password = '" + Home.jPasswordField1.getText() + "'";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if (result.isBeforeFirst()) {
                while (result.next()) {

                    String position = result.getString("Position");
                    Home.jLabel162.setText(result.getString("Name"));
                    if (position.compareTo("Administrator") == 0) {
                        Home.login.setVisible(false);
                        Home.content.setVisible(true);
                        Home.status.setVisible(true);
                        Home.menu.setVisible(true);
                        Home.jLabel8.setVisible(true);
                        Home.jPanel42.setVisible(true);
                    }
                    if (position.compareTo("Receptionist") == 0) {
                        Home.login.setVisible(false);
                        Home.content.setVisible(true);
                        Home.status.setVisible(true);
                        Home.menu.setVisible(true);
                        Home.jLabel8.setVisible(false);
                        Home.jPanel42.setVisible(false);

                    }

                }
            } else {
                Home.login.setVisible(true);
                Home.header1.setVisible(true);
                Home.jPanel1.setVisible(true);
                JOptionPane.showMessageDialog(null, "User not found in the System");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "The error is : " + e.getMessage());
        }
    }

}
