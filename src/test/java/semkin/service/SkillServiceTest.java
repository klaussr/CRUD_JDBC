package semkin.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import semkin.model.Skill;
import semkin.repository.SkillRepository;
import semkin.service.impl.SkillServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SkillServiceTest {
    private static final Long TEST_SKILL_ID = 1L;

    @Mock
    SkillRepository skillRepositoryMock;


    @InjectMocks
    SkillServiceImpl skillServiceMock;
    Skill testSkill;
    Skill testSkill1;
    List<Skill> testListSkills;

    @Captor
     ArgumentCaptor<Long> idCaptor;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(skillRepositoryMock);
        testSkill = new Skill();
        testSkill.setId(TEST_SKILL_ID);
        testSkill.setName("test");

        testSkill1 = new Skill();
        testSkill1.setId(TEST_SKILL_ID+1);
        testSkill1.setName("test1");

        testListSkills = Arrays.asList(testSkill, testSkill1);
    }

    @Test
    void create() {
        doReturn(testSkill).when(skillRepositoryMock).save(testSkill);
        skillServiceMock.create(testSkill.getName());
        assertEquals(testSkill.getName(), "test");
    }

    @Test
    void deleteById()  {
       skillRepositoryMock.deleteById(TEST_SKILL_ID);
       Mockito.verify(skillRepositoryMock, times(1)).deleteById(anyLong());
       Mockito.verify(skillRepositoryMock).deleteById(idCaptor.capture());
       assertEquals(TEST_SKILL_ID, idCaptor.getValue());
    }

    @Test
    void update()  {
        when(skillRepositoryMock.update(testSkill)).thenReturn(testSkill);
        assertEquals(testSkill, skillRepositoryMock.update(testSkill));
    }

    @Test
    void getById() {
        when(skillServiceMock.getById(TEST_SKILL_ID)).thenReturn(testSkill);
        assertEquals(testSkill, skillServiceMock.getById(TEST_SKILL_ID));
        Mockito.verify(skillRepositoryMock).getById(TEST_SKILL_ID);

    }

    @Test
    void getAll() {
        when(skillRepositoryMock.getAll()).thenReturn(testListSkills);
        List<Skill> currentList = skillServiceMock.getAll();
        Mockito.verify(skillRepositoryMock).getAll();
        assertEquals(testListSkills, currentList);
    }
}
