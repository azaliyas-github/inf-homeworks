package restoke.repository.users;

import restoke.models.*;
import restoke.repository.*;

public interface UsersRepository extends CrudRepository<User>{
     boolean verifyUser(String email, String password);
}
