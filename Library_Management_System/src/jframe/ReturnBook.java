/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import com.sun.jdi.connect.spi.Connection;
import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class ReturnBook extends javax.swing.JFrame {

    /**
     * Creates new form IssueBoo
     */
    public ReturnBook() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    // to fetch the book details from the database and display it to the panel
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try {
            java.sql.Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_studentName.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));

            }else{
                lbl_bookError.setText("Invalid Book ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        // to fetch the student details from the database and display it to the panel
        public void getStudentDetails(){
        int studentId = Integer.parseInt(txt_studentId.getText());
        
        try {
            java.sql.Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_studentId.setText(rs.getString("student_id"));
                jLabel5.setText(rs.getString("name"));
                lbl_department.setText(rs.getString("department"));
                lbl_program.setText(rs.getString("program"));
            }else{
                lbl_studentError.setText("Inavlid Student ID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        // insert issue book details to database
        public boolean issueBook(){
            boolean isIssued = false;
            int bookId = Integer.parseInt(txt_bookId.getText());
            int studentId = Integer.parseInt(txt_studentId.getText());
            String bookName = lbl_bookName.getText();
            String studentName = jLabel5.getText();
            
            Date uIssueDate = date_issueDate.getDatoFecha();
            Date uDueDate = date_dueDate.getDatoFecha();
            
            Long issueDate = uIssueDate.getTime();
            Long dueDate = uDueDate.getTime();
            
            java.sql.Date sIssueDate = new java.sql.Date(issueDate);
            java.sql.Date sDueDate = new java.sql.Date(dueDate);
            
            try {
                java.sql.Connection con = DBConnection.getConnection();
                String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,"
                        + " issue_date, due_date, status)values(?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, bookId);
                pst.setString(2, bookName);
                pst.setInt(3, studentId);
                pst.setString(4, studentName);
                pst.setDate(5, sIssueDate);
                pst.setDate(6, sDueDate);
                pst.setString(7, "pending");
                
                int rowCount = pst.executeUpdate(); 
                if (rowCount > 0) {
                    isIssued = true;
                }else{
                    isIssued = false;
                }

                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        return isIssued;
        
        }
        // updating book count
        public void updateBookCount(){
            int bookId = Integer.parseInt(txt_bookId.getText());
            
            try {
                java.sql.Connection con = DBConnection.getConnection();
                String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1,bookId);
                
                int rowCount = pst.executeUpdate();
                
                if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "book count updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount-1));
                
                }else{
                JOptionPane.showMessageDialog(this, "can't update book count");
                }  
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        // checking if the book is already issue to the same student
        
        public boolean isAlreadyIssued(){
            
            boolean isAlreadyIssued = false;
            int bookId = Integer.parseInt(txt_bookId.getText());
            int studentId = Integer.parseInt(txt_studentId.getText());
            
            try {
            java.sql.Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    isAlreadyIssued = true;
                }else{
                    isAlreadyIssued = false;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return isAlreadyIssued;
            
        }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_issueDate = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_bookId = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_studentId = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_main.setBackground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle2.setText("RETURN BOOK");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 460, 360, 60));

        jPanel1.setBackground(new java.awt.Color(153, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel1.setText("  Book Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 260, 90));

        lbl_quantity.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_quantity.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 170, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 260, 5));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Book name:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 110, 30));

        lbl_bookName.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 170, 30));

        lbl_bookId.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 170, 30));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Student Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 140, 30));

        lbl_studentName.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 170, 30));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Due Date:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 490, 110, 30));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Issue Date:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 110, 30));

        lbl_issueDate.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        lbl_issueDate.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 170, 30));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Book ID:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 80, 30));

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 390, 830));

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 22)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel12.setText("Return Book");
        panel_main.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 110, 200, 120));

        jPanel7.setBackground(new java.awt.Color(153, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        panel_main.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 200, 350, 5));

        jPanel8.setBackground(new java.awt.Color(255, 51, 51));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 35)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("x");
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 30, 30));

        panel_main.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 103, 42));

        txt_bookId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_bookId.setForeground(new java.awt.Color(153, 153, 153));
        txt_bookId.setText("Enter Book ID");
        txt_bookId.setToolTipText("");
        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        txt_bookId.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                txt_bookIdAncestorResized(evt);
            }
        });
        txt_bookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bookIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 240, 270, 40));

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 0, 0));
        jLabel9.setText("Book ID :");
        panel_main.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 250, 110, 30));

        txt_studentId.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txt_studentId.setForeground(new java.awt.Color(153, 153, 153));
        txt_studentId.setText("Enter Student ID");
        txt_studentId.setToolTipText("");
        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_studentId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentIdFocusLost(evt);
            }
        });
        txt_studentId.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                txt_studentIdAncestorResized(evt);
            }
        });
        txt_studentId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_studentIdActionPerformed(evt);
            }
        });
        panel_main.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 310, 270, 40));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Student ID :");
        panel_main.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 320, 110, 30));

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel8.setText("Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 120, 30));

        panel_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(0, 0, 204));
        rSMaterialButtonCircle3.setText("FIND");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        panel_main.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 380, 360, 60));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 20)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/library-2.png"))); // NOI18N
        jLabel3.setText("Book ID:");
        panel_main.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 190, 560, 430));

        getContentPane().add(panel_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 820));

        setSize(new java.awt.Dimension(1370, 824));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel18MouseClicked

    private void txt_bookIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusGained
        if (txt_bookId.getText().equals("Enter Book ID")) {
            txt_bookId.setText(""); // Clear the placeholder
            txt_bookId.setForeground(Color.black); // Set the text color to black (or any visible color)
        }
    }//GEN-LAST:event_txt_bookIdFocusGained

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (txt_bookId.getText().isEmpty()) {
            txt_bookId.setText("Enter Book ID");
            txt_bookId.setForeground(Color.GRAY);
        
        }
             getBookDetails();
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_bookIdAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txt_bookIdAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdAncestorResized

    private void txt_bookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bookIdActionPerformed

    private void txt_studentIdFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusGained
        if (txt_studentId.getText().equals("Enter Student ID")) {
            txt_studentId.setText(""); // Clear the placeholder
            txt_studentId.setForeground(Color.black); 
        }
    }//GEN-LAST:event_txt_studentIdFocusGained

    private void txt_studentIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentIdFocusLost
        if (txt_studentId.getText().isEmpty()) {
            txt_studentId.setText("Enter Student ID");
            txt_studentId.setForeground(Color.GRAY);      
        }
        getStudentDetails();
    }//GEN-LAST:event_txt_studentIdFocusLost

    private void txt_studentIdAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_txt_studentIdAncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdAncestorResized

    private void txt_studentIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_studentIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_studentIdActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (lbl_quantity.getText().equals("0")) {
            JOptionPane.showMessageDialog(this,"Book is Not Available");
            
        }else{
            if (isAlreadyIssued() == false) {
            
            if (issueBook() ==true){
                JOptionPane.showMessageDialog(this,"book issued successfully!");
                updateBookCount();
            }else{
                JOptionPane.showMessageDialog(this,"can't issue the book!");   
            }    
        }else{
            JOptionPane.showMessageDialog(this, "this student already have this book");
        }
        }

    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReturnBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_issueDate;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panel_main;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private javax.swing.JTextField txt_bookId;
    private javax.swing.JTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
