package restoke.repository.category;

import restoke.dto.*;
import restoke.models.*;
import restoke.repository.*;

import java.util.*;

public interface CategoriesDtoRepository extends CrudRepository {
    List<CategoryDto> findAll();
    Category findByName(String name);
}
