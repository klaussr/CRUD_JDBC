package semkin.service.impl;

import semkin.model.Skill;
import semkin.repository.SkillRepository;
import semkin.repository.jdbc.JdbcSkillRepositoryImpl;
import semkin.service.SkillService;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl() {
        skillRepository = new JdbcSkillRepositoryImpl();
    }

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill getById(Long id)  {
        return skillRepository.getById(id);
    }

    @Override
    public void create(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        skillRepository.save(skill);
    }

    @Override
    public void delete(Long id) {
        skillRepository.deleteById(id);
    }

    @Override
    public void update(Long id, String name) {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skillRepository.update(skill);
    }

    @Override
    public List<Skill> getAll(){
        return skillRepository.getAll();
    }
}
