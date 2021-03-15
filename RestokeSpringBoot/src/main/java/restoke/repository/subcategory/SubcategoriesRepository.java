package restoke.repository.subcategory;

import restoke.models.*;
import restoke.repository.*;

public interface SubcategoriesRepository extends CrudRepository {
    SubCategory findByName(String name);
}
