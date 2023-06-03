package semkin.service;

import semkin.model.Specialty;

public interface SpecialtyService extends GenericService<Specialty, Long> {

    void create(String name);

    void update(Long id, String name);

}
