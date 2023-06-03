package semkin.service.impl;

import semkin.model.BaseEntity;
import semkin.model.Developer;
import semkin.model.Status;
import semkin.repository.DeveloperRepository;
import semkin.repository.jdbc.JdbcDeveloperRepositoryImpl;
import semkin.service.DeveloperService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl() {
        developerRepository = new JdbcDeveloperRepositoryImpl();
    }

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    public Developer getById(Long id)  {
        return developerRepository.getById(id);
    }

    @Override
    public void delete(Long id) {
        developerRepository.deleteById(id);
    }

    @Override
    public List<Developer> getAll()  {
        List<Developer> list = new ArrayList<>();
        for (Developer developer : developerRepository.getAll()) {
            if (developer.getStatus().equals(Status.ACTIVE)){
                list.add(developer);
            }
        }
        list.sort(Comparator.comparing(BaseEntity::getId));
        return list;
    }
}
