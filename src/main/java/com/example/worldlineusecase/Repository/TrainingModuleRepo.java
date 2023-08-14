package com.example.worldlineusecase.Repository;

import com.example.worldlineusecase.Entity.TrainingModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TrainingModuleRepo extends JpaRepository<TrainingModule,Long> {
    @Query(
            nativeQuery = true,
            value = "delete from training_module where id=:id"
    )
    @Modifying
    @Transactional
    public void deleteTrainingModule(Long id);
    @Query(
            nativeQuery = true,
            value="select * from training_module where name=:search"
    )
    List<TrainingModule> findByName(String search);
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM training_module WHERE list_of_topics @> CAST(ARRAY[:search] AS character varying[])"
    )
    List<TrainingModule>findByTopic(String search);
}
