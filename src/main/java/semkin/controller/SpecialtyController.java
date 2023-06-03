package semkin.controller;


import semkin.model.BaseEntity;
import semkin.model.Specialty;
import semkin.service.SpecialtyService;
import semkin.service.impl.SpecialtyServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SpecialtyController {

    private final SpecialtyService specialtyService = new SpecialtyServiceImpl();

    public Specialty getById(Long id) throws Exception {
        return specialtyService.getById(id);
    }

    public void create(String name) throws Exception {
        specialtyService.create(name);
    }

    public void update(Long id, String name) throws Exception {
        Specialty specialty = new Specialty();
        specialty.setId(id);
        specialty.setName(name);
        specialtyService.update(id, name);
    }

    public List<Specialty> getAll() throws Exception {
        List<Specialty> list = new ArrayList<>();
        list.addAll(specialtyService.getAll());
            list.sort(Comparator.comparing(BaseEntity::getId));
            return list;
    }

    public void delete(Long id) throws Exception {
        specialtyService.delete(id);
    }
}
