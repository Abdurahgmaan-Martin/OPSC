import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    // Insert user into the database
    @Insert
    suspend fun insertUser(user: User)

    // Get user by username (for login verification)
    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?
}
