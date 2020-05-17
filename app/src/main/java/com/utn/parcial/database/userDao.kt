package com.utn.parcial.database
import androidx.room.*
import com.utn.parcial.entities.User



@Dao
public interface userDao {

    @Query("SELECT * FROM users ORDER BY id")
    fun loadAllPersons(): MutableList<User?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPerson(user: User?)

    @Update
    fun updatePerson(user: User?)

    //@Delete
  //  fun delete(user: User?)

    @Query("SELECT * FROM users WHERE id = :id")
    fun loadPersonById(id: Int): User?

    @Query("SELECT * FROM users WHERE user = :user")
    fun loadPersonByUser(user: String): User?

    @Query("SELECT COUNT(*) FROM users WHERE user = :user")
    fun cantUser(user: String): Int?

    @Query("SELECT id FROM users WHERE user = :user")
    fun getIdByUser(user: String): Int?

    @Query("SELECT password FROM users WHERE user = :user")
    fun getPasswordByUser(user: String): String?
/*
    @Query("SELECT COUNT(*) FROM users")    //ojo es la anteultima
    fun proximaId(): Int*/
}