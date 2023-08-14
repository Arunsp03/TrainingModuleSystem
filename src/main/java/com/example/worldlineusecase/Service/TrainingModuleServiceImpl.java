package com.example.worldlineusecase.Service;

import com.example.worldlineusecase.Entity.TrainingModule;
import com.example.worldlineusecase.Repository.TrainingModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TrainingModuleServiceImpl implements TrainingModuleService{
    @Autowired
    private TrainingModuleRepo trainingModuleRepo;

    public void addTrainingmodule(TrainingModule trainingModule) {
        trainingModule.getList_of_topics().toString();
        trainingModuleRepo.save(trainingModule);
    }
    public void deleteModule(Long id)
    {
        trainingModuleRepo.deleteTrainingModule(id);
    };
    public void updateModule(TrainingModule trainingModule)
    {
        TrainingModule trainingModule1=trainingModuleRepo.findById(trainingModule.getId()).get();

        if(Objects.nonNull(trainingModule1.getName()) && !"".equalsIgnoreCase(trainingModule1.getName())){
            trainingModule1.setName(trainingModule.getName());
        }
        if(Objects.nonNull(trainingModule.getDescription()) && !"".equalsIgnoreCase(trainingModule.getDescription())) {
            trainingModule1.setDescription(trainingModule.getDescription());
        }
        trainingModuleRepo.save(trainingModule1);
    };
    public List<TrainingModule> searchTrainingModuleByName(String searchText){
        return (trainingModuleRepo.findByName(searchText));

    }
    public List<TrainingModule>searchTrainingModuleByTopic(String searchText){
        return  (trainingModuleRepo.findByTopic(searchText));
    }

}
