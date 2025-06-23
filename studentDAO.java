/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
 
import model.Student; 
import java.util.List;
import java.util.ArrayList; 
import java.sql.*;
/**
 *
 * @author Kieu Tri Hung
 */
public class studentDAO   {
     public List<Student> getAll() {
          List<Student> list = new ArrayList<>();
          String sql = "select * from Student";
          try {
              PreparedStatement st=DBContext.getconnect().prepareStatement(sql);
              ResultSet rs=st.executeQuery();
              while(rs.next()){
                  Student stu=new Student(
                          rs.getString("SID"),
                          rs.getString("SName"),
                          rs.getString("StudentBOD"), 
                          rs.getString("UserName"),
                          rs.getString("Password")
                  );
                   list.add(stu);
              }
          } catch (SQLException e){
              System.out.print(e);
          }
          return list;
      }
        public int getSizeOfStu() {
          List<Student> list = new ArrayList<>();
           list = getAll();
           int size_of_stuList=list.size();
          return size_of_stuList;
      }
      
      
        public void addStu(Student stu) {
        String sql = "INSERT INTO Student (SID, SName, StudentBOD,UserName, Password)  VALUES (?, ?, ?,?,?)";
        
        try {
           
            PreparedStatement st = DBContext.getconnect().prepareStatement(sql);             
             st.setString(1, stu.getId());
            st.setString(2, stu.getName());
            st.setString(3, stu.getDoB()); 
             st.setString(4, stu.getName());
            st.setString(5, stu.getDoB()); 
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.print(e);
        }
    }
        
    public void deleStu(String stu_id) {
    String query = "DELETE FROM Student WHERE SID = ?"; // SQL để xóa hóa đơn có id tương ứng
     try( Connection conn=DBContext.getconnect()) {
           PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, stu_id); // Giả sử bill có phương thức getBillId() để lấy id của bill
        ps.executeUpdate(); 
    } catch (SQLException e) {
     System.out.print(e);
    }
} 
   public void updateStudent(Student student) {
    String sql = "UPDATE Student SET SName = ?, StudentBOD = ?, UserName = ?, Password = ? WHERE SID = ?";

    try (Connection conn = DBContext.getconnect();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, student.getName());
        ps.setString(2, student.getDoB());
        ps.setString(3, student.getUsername());
        ps.setString(4, student.getPassword());
        ps.setString(5, student.getId());

        ps.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace(); // tốt hơn để debug thay vì chỉ in ra System.out
    }
}

   
      public void updateStu(Student s,String old_stu_id) {
    String query = "UPDATE Student SET SID = ?, SName = ?, StudentBOD = ?, UserName = ?, Password = ? WHERE SID = ?";
             try( Connection conn=DBContext.getconnect()) {
           PreparedStatement ps = conn.prepareStatement(query);
              ps.setString(1, s.getId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDoB());
            ps.setString(4, s.getUsername()); 
            ps.setString(5, s.getPassword()); 
            ps.setString(6,old_stu_id); 
             ps.executeUpdate(); 
        } catch (SQLException e) {
           System.out.print(e);
        }
    }
      
      
      public static void main(String[] args) {
           studentDAO sDAO = new studentDAO();
     List<Student> list = sDAO.getAll();
     Student stu = new Student("11","1","1111-11-11","admin","280403");
     sDAO.addStu(stu);
    System.out.println(list.get(0).getId()); 
            System.out.println(sDAO.getSizeOfStu());  
   
    }
      
   
}
