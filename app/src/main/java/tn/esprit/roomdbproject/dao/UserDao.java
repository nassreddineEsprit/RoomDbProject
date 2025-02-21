package tn.esprit.roomdbproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.roomdbproject.entity.User;

@Dao
public interface UserDao {

    @Insert
    void inserOne(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user")
    List<User>getAll();

}
