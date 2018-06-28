package TestDemo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

/**
 * @Classname:DataCURD
 * @describe:熟悉jdbc方法,对Mysql数据库进行简单的增删改查
 * @author:蒋泽恩
 * @date:2018年6月14日 下午10:26:54
 */
public class DataCURD {
    private int id;
    private String name;
    private String sex;
    private String city;
    private String birthday;
    private  String major;

    public DataCURD(int id, String name, String sex, String city, String birthday, String major) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.city = city;
        this.birthday = birthday;
        this.major = major;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    static public Connection getconn() {
        String url = "jdbc:mysql://localhost/student?user=root&password=jiang.141201&useSSL=false";
        Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    private static int inset(DataCURD curd) {
        Connection conn = getconn();
        String sql = "insert into stuinfo(stu_id,stu_name,sex,province,city,birthday,department) "
                + "values(?,?,?,?,?,?,?)";
        int i = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
            preparedStatement.setInt(1, curd.getId());
            preparedStatement.setString(2, curd.getName());
            preparedStatement.setString(3, curd.getSex());
            preparedStatement.setString(4, curd.getCity());
            preparedStatement.setString(5, curd.getBirthday());
            i = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static void SelectAll() {
        Connection  conn = getconn();
        try {
            Statement statement = (Statement) conn.createStatement();
            String sql = "select * from stuinfo";
            ResultSet result = statement.executeQuery(sql);
            System.out.println("学号" + "\t\t姓名" + "\t\t性别" +"\t\t籍贯" + "\t\t出生年月" + "\t\t专业");
            while(result.next()) {
                int number=result.getInt("stu_id");
                String name=result.getString("stu_name");
                String sex=result.getString("sex");
                String city = result.getString("city");
                String birthday = result.getString("birthday");
                String major = result.getString("major");
                System.out.println(number +"\t" + name + "\t" + sex + "\t\t"
                        + city +"\t\t" + birthday +"\t" +major);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static int deleteData(int id) {
        Connection conn = getconn();
        String sql = "delete from stuinfo where stu_id ='" + id + "'";
        PreparedStatement preparedStatement;
        int i = 0;
        try {
            preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    private static void Update(DataCURD curd) {
        Connection conn = getconn();
        String sql = "update stuinfo set stu_name = '" + curd.getName() +"'where stu_id = '" +curd.getId()+"'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = (PreparedStatement)conn.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SelectAll();
    }

}
