package com.example.worldlineusecase.Service;

import com.example.worldlineusecase.Entity.TrainingModule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingModuleService {
public void addTrainingmodule(TrainingModule trainingModule);

    void deleteModule(Long id);
    void updateModule(TrainingModule trainingModule);

    List<TrainingModule> searchTrainingModuleByName(String searchText);
    List<TrainingModule>searchTrainingModuleByTopic(String searchText);
}
