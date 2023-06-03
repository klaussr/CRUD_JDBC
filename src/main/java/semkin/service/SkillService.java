package semkin.service;

import semkin.model.Skill;

public interface SkillService extends GenericService<Skill, Long> {

    void create(String name);

    void update(Long id, String name);
}