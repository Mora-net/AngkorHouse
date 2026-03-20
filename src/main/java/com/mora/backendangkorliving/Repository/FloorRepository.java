package com.mora.backendangkorliving.Repository;

import com.mora.backendangkorliving.model.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FloorRepository extends JpaRepository<Floor, Long> {
    List<Floor> findAllByOrderByIdAsc();
}
