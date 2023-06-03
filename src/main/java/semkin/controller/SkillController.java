package semkin.controller;

import semkin.model.BaseEntity;
import semkin.model.Skill;
import semkin.service.SkillService;
import semkin.service.impl.SkillServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SkillController {

    private final SkillService skillService = new SkillServiceImpl();

    public Skill getById(Long id) throws Exception {
        return skillService.getById(id);
    }

    public void create(String name) throws Exception {
        skillService.create(name);
    }

    public List<Skill> getAll() throws Exception {
        List<Skill> list = new ArrayList<>();
        list.addAll(skillService.getAll());
        list.sort(Comparator.comparing(BaseEntity::getId));
        return list;
    }

    public void update(Long id, String name) throws Exception {
        Skill skill = new Skill();
        skill.setId(id);
        skill.setName(name);
        skillService.update(id, name);
    }

    public void delete(Long id) throws Exception {
        skillService.delete(id);
    }
}

