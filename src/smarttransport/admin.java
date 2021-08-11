/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smarttransport;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.*;

/**
 *
 * @author mursalat
 */
public class admin extends javax.swing.JFrame  {

    /**
     * Creates new form admin
     */
    public admin() {
        initComponents();
        d = new dateMan();
        
//        System.out.println(d.today()+" "+ d.numToDate(d.today()));
//        System.out.println(d.dateToNum("31/12"));
        try
        {
            con = DriverManager.getConnection(server,"root", "awd@3456");
            stmt = (Statement) con.createStatement();
            stmt2 = (Statement) con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
            stmt3 = (Statement) con.createStatement();
            JOptionPane.showMessageDialog(null, "Connected");
            
            stmt.execute("use smart;");
            r1 = stmt.executeQuery("select busID from flight where day = "+d.today()+";");
            
            while(r1.next())
            {
                stmt2.execute("update bus set available = true where busID ="+r1.getInt(1)+";");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Connection Failed");
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    static dateMan d = null;
    static ResultSet result = null;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resetDB = new javax.swing.JButton();
        dropDB = new javax.swing.JButton();
        populate = new javax.swing.JButton();
        flights = new javax.swing.JButton();
        cleanFlight = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        clearCustomer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        resetDB.setText("Reset Database");
        resetDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetDBActionPerformed(evt);
            }
        });
        getContentPane().add(resetDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 208, 88));

        dropDB.setText("Drop database");
        dropDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropDBActionPerformed(evt);
            }
        });
        getContentPane().add(dropDB, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 229, 88));

        populate.setText("Psudo populate Cust. DB");
        populate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                populateActionPerformed(evt);
            }
        });
        getContentPane().add(populate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, 208, 87));

        flights.setText("Prepare 2 days flights");
        flights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flightsActionPerformed(evt);
            }
        });
        getContentPane().add(flights, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 208, 80));

        cleanFlight.setText("Clear Flight database");
        cleanFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanFlightActionPerformed(evt);
            }
        });
        getContentPane().add(cleanFlight, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, 229, 80));

        Back.setText("Back to Home");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });
        getContentPane().add(Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 148, 45));

        clearCustomer.setText("Clear Customer DB");
        clearCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearCustomerActionPerformed(evt);
            }
        });
        getContentPane().add(clearCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 350, 229, 87));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/admin.png"))); // NOI18N
        jLabel1.setText("back");
        jLabel1.setMaximumSize(new java.awt.Dimension(8000, 6000));
        jLabel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetDBActionPerformed
        try
        {
            stmt.execute("drop database smart;");
            JOptionPane.showMessageDialog(null, "database dropped");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
        try
        {
            stmt.execute("create database smart;");
            JOptionPane.showMessageDialog(null,"database created");
            stmt.execute("show databases;");
            stmt.execute("use smart;");
            // bus table
            stmt.execute("create table bus (busID int not null auto_increment, available boolean, primary key(busID));");
            // customer table
            stmt.execute("create table customer (name varchar(50),NID int, address varchar(50), sex varchar(5),phone int);");
            // ticket table
            stmt.execute("create table ticket (ticketNo int not null auto_increment, flightID int, NID int, seats varchar(15), primary key(ticketNO));");
            // route table
            stmt.execute("create table route (routeID int, start varchar(20), end varchar(20), price int);");
            // flight table
            stmt.execute("create table flight ( flightID int not null auto_increment, primary key(flightID), routeID int, busID int,day int, shift int, seat int);");
            
            // creating buses
            String code = "";
            int i,j;
            
            for(i=0;i<90;i++)
            {
                code = "insert into bus(available) values(true);";
                stmt.execute(code);
            }
            
            // populating static route table
            
            
            String[] loc = new String[] {"Dhaka", "Chittagong","Sylhet"};
            
            for(i = 0;i<loc.length;i++)
            {
                for(j = 0;j<loc.length;j++)
                {
                    code = "";
                    if(i!=j)
                    {
                        code += "insert into route values ("+((i+1)*10+(j+1))+",'"+loc[i]+"','"+loc[j]+"',"+((loc[i].length()+loc[j].length())*75)+");";
                        stmt.execute(code);
                    }
                }
            }
            
            // testing testing
            
            JOptionPane.showMessageDialog(null, "All tables created and prepared");
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_resetDBActionPerformed

    private void dropDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropDBActionPerformed
        // TODO add your handling code here:
        try
        {
            stmt.execute("drop database smart;");
            JOptionPane.showMessageDialog(null, "Done");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Database doesnt exist yet!!");
        }
    }//GEN-LAST:event_dropDBActionPerformed

    private void populateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_populateActionPerformed
        // TODO add your handling code here:
        
        Random rr = new Random();
        
        int i;
        char s;
        String code;
        try
        {
            for(i = 0;i<10;i++)
            {
                code="insert into customer values('";
                code+=rr.nextInt(99999)+"', '"+(i+10000)+"', '"+rr.nextInt(8888)+"', '"+(i%2==0? 'm':'f')+"',"+rr.nextInt(8888)+");";
                System.out.println(code);
                stmt.execute(code);
            }
            JOptionPane.showMessageDialog(null, "Customer table populated");
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }//GEN-LAST:event_populateActionPerformed

    private void flightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flightsActionPerformed
        // TODO add your handling code here:
        int i=0;
        int j=0;
        int k = 0;
        int c;
        int t = 0;
        int d;
        String code;
        
        try
        {
            stmt.execute("use smart;");
            r1 = stmt.executeQuery("select max(day), day from flight;");
            r1.next();
            d = r1.getInt(1);
            if(d==0)
            {
                d = new dateMan().today();
            }
            else
            {
                d++;
            }
            r1 = stmt.executeQuery("select count(routeID) from route;");
            r1.next();
            c = r1.getInt(1);
            
            for(i=0;i<2;i++)
            {
                for(j=1;j<3;j++)
                {
                    r1 = stmt2.executeQuery("select * from bus order by available desc;");
                    r2 = stmt.executeQuery("select routeID from route;");
                    for(k = 0;k<c;k++)
                    {
                        t++;
                        r1.next();
                        if(!r1.getBoolean(2))
                        {
                            throw new Exception("No available buses for further schedule");
                        }
                        r2.next();
                        code = "insert into flight(routeID,busID,day,shift,seat) values(";
                        code += r2.getInt(1)+", "+r1.getInt(1)+", "+d+", "+j+", "+"0);";
                        stmt3.execute(code);
                        stmt3.execute("update bus set available = false where busID ="+r1.getInt(1)+";");
                    }
                }
                d++;
            }
            JOptionPane.showMessageDialog(null,"2 days flight schedule created");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_flightsActionPerformed

    private void cleanFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanFlightActionPerformed
        // TODO add your handling code here:
        try
        {
            stmt.execute("use smart;");
            stmt.execute("delete from flight");
            JOptionPane.showMessageDialog(null, "done clearing");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
                    
    }//GEN-LAST:event_cleanFlightActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new LandingPage().setVisible(true);
    }//GEN-LAST:event_BackActionPerformed

    private void clearCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearCustomerActionPerformed
        // TODO add your handling code here:
        
        try
        {
            stmt.execute("use smart;");
            stmt.execute("delete from customer");
            JOptionPane.showMessageDialog(null, "done clearing");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_clearCustomerActionPerformed

    /**
     * @param args the command line arguments
     */
    
    static ResultSet r1 = null;
    static ResultSet r2 = null;
    static Connection con = null;
    static Statement stmt = null;
    static Statement stmt2 = null;
    static Statement stmt3 = null;
    static String server = "jdbc:mysql://localhost:3306";
    public static void main(String args[]) {
        
        
        
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JButton cleanFlight;
    private javax.swing.JButton clearCustomer;
    private javax.swing.JButton dropDB;
    private javax.swing.JButton flights;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton populate;
    private javax.swing.JButton resetDB;
    // End of variables declaration//GEN-END:variables

    
}