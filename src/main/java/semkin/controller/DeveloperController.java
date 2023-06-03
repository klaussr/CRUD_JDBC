package semkin.controller;

import semkin.model.Developer;
import semkin.model.Status;
import semkin.repository.DeveloperRepository;
import semkin.repository.jdbc.JdbcDeveloperRepositoryImpl;
import semkin.service.DeveloperService;
import semkin.service.impl.DeveloperServiceImpl;

import java.util.List;

public class DeveloperController {

    private final DeveloperService developerService = new DeveloperServiceImpl();
    private final DeveloperRepository developerRepository = new JdbcDeveloperRepositoryImpl();


    public Developer getById(Long id) throws Exception {
        return developerService.getById(id);
    }

    public void save(String firstName, String lastName, String  skill, String  specialty) throws Exception {
        Developer developer = new Developer();
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkill(skill);
        developer.setSpecialty(specialty);
        developer.setStatus(Status.ACTIVE);
        developerRepository.save(developer);
    }

    public void update(Long id, String firstName, String lastName, String  skill, String  specialty) throws Exception {
        Developer developer = new Developer();
        developer.setId(id);
        developer.setFirstName(firstName);
        developer.setLastName(lastName);
        developer.setSkill(skill);
        developer.setSpecialty(specialty);
        developer.setStatus(Status.ACTIVE);
        developerRepository.update(developer);
    }

    public List<Developer> getAll() throws Exception {
        return developerService.getAll();
    }

    public void delete(Long id) throws Exception {
        developerService.delete(id);
    }
}
