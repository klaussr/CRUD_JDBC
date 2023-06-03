package semkin.service.impl;

import semkin.model.Specialty;
import semkin.repository.SpecialtyRepository;
import semkin.repository.jdbc.JdbcSpecialtyRepositoryImpl;
import semkin.service.SpecialtyService;

import java.util.List;

public class SpecialtyServiceImpl implements SpecialtyService {

    private final SpecialtyRepository specialtyRepository;


    public SpecialtyServiceImpl()    {
        specialtyRepository = new JdbcSpecialtyRepositoryImpl();
    }

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty getById(Long id)  {
        return specialtyRepository.getById(id);
    }

    @Override
    public void create(String name) {
        Specialty specialty = new Specialty();
        specialty.setName(name);
        specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Long id) {
        specialtyRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String name) {
        Specialty specialty = new Specialty();
        specialty.setId(id);
        specialty.setName(name);
        specialtyRepository.update(specialty);
    }

    @Override
    public List<Specialty> getAll() {
        return specialtyRepository.getAll();
    }
}
