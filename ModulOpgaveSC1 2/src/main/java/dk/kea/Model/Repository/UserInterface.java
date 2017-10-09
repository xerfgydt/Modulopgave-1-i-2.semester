package dk.kea.Model.Repository;

import dk.kea.Model.Entites.User;

import java.util.ArrayList;

public interface UserInterface {

    //User login(String email, String password);

    User getUser();

    void create(User u);

    void delete(int id);

    User loginDB(String username, String password);

    User read(int id);

    void updatedb(User u, String newPassword);

    //void update(User u, String newPassword);

    User greenLight(String password, int id);

    void byebye();
}
