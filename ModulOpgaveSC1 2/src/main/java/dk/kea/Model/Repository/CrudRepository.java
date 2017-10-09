package dk.kea.Model.Repository;

import dk.kea.Model.Entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
@Repository
public class CrudRepository implements UserInterface {

    ArrayList<User> user = new ArrayList<User>();
    ArrayList<User> users = new ArrayList<User>();

    @Autowired
    private JdbcTemplate jdbc;

    /*@Override
    public User login(String email, String password) {
        user.clear();

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                user.add(u);
                users.remove(u);
                return u;
            }
        }
        return null;
    }*/

    @Override
    public User getUser() {
        for (User u : user) {
            return u;
        }
        return null;
    }

    @Override
    public void create(User u) {
        //Integer sizeOfArray = users.size() + 1;
        //u.setId(sizeOfArray);
        //users.add(u);
        jdbc.update("INSERT INTO user_table(name, password, address,email) VALUES('" + u.getName() + "','" + u.getPassword() + "','" + u.getAddress() + "','" + u.getEmail() + "')");

    }

    //@Override
    //public void delete() { user.clear(); }

    @Override
    public void delete(int id) {


        jdbc.update("DELETE FROM user_schema.user_table WHERE user_id = " + id);





    }


    @Override
    public User greenLight(String password, int id)
    {
    SqlRowSet sqlRowSet;
    sqlRowSet = jdbc.queryForRowSet("SELECT * FROM user_table WHERE password='" + password + "' AND user_id='" + id + "'");
        if (sqlRowSet.next())
        {
            return new User(sqlRowSet.getInt("user_id"), sqlRowSet.getString("name"), sqlRowSet.getString("password"), sqlRowSet.getString("address"), sqlRowSet.getString("email"));
        }
        return null;
    }


    @Override
    public void updatedb(User u, String newPassword) {
        jdbc.update("Update user_table SET password =  '" + newPassword + "' WHERE user_id = " + u.getId() + "");
    }

    /*@Override
    public void update(User u, String newPassword) {
        u.setPassword(newPassword);
        users.add(u);
    }*/

    @Override
    public void byebye() {
        User u = getUser();
        users.add(u);
    }


    public User loginDB(String username, String password) // kan laese alle brugere
    {
        SqlRowSet sqlRowSet;
        //ArrayList<User>users = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT * FROM user_table WHERE email ='" + username + "'AND password='" + password+"'");
        if (sqlRowSet.next()){
            //indhold af database sqlRowSet ned i arrayliste
            //users.add(new User(sqlRowSet.getInt("user_id"), sqlRowSet.getString("name"), sqlRowSet.getString("password"), sqlRowSet.getString("address"), sqlRowSet.getString("email")));

            return new User(sqlRowSet.getInt("user_id"), sqlRowSet.getString("name"), sqlRowSet.getString("password"), sqlRowSet.getString("address"), sqlRowSet.getString("email"));


        }
    return null;
}




    @Override
    public User read(int id) /* kan laese 1 bruger */  //razz
    {
        SqlRowSet sqlRowSet;
        //ArrayList<User>users = new ArrayList<>();
        sqlRowSet = jdbc.queryForRowSet("SELECT  * FROM user_table WHERE user_id =" + id + " ");
        if (sqlRowSet.next()) {


            //indhold af database sqlRowSet ned i arrayliste
            return new User(sqlRowSet.getInt("user_id"), sqlRowSet.getString("name"), sqlRowSet.getString("password"), sqlRowSet.getString("address"), sqlRowSet.getString("email"));
        }
        return null;

    }
}