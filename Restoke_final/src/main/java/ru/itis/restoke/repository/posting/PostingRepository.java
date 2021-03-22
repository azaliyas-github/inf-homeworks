package ru.itis.restoke.repository.posting;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;

@Repository
public interface PostingRepository extends JpaRepository<PostingDbo, Long> {

}
