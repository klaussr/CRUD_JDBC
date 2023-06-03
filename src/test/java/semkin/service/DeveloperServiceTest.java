package semkin.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import semkin.model.Developer;
import semkin.model.Status;
import semkin.repository.DeveloperRepository;
import semkin.service.impl.DeveloperServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DeveloperServiceTest {
    private static final Long TEST_DEVELOPER_ID = 1L;

    @Mock
    DeveloperRepository developerRepositoryMock;


    @InjectMocks
    DeveloperServiceImpl developerServiceMock;
    Developer testDeveloper;
    Developer testDeveloper1;
    List<Developer> testDevelopersList;

    @Captor
     ArgumentCaptor<Long> idCaptor;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(developerRepositoryMock);
        testDeveloper = new Developer();
        testDeveloper.setId(TEST_DEVELOPER_ID);
        testDeveloper.setFirstName("Alex");
        testDeveloper.setLastName("Semkin");
        testDeveloper.setSkill("Maven");
        testDeveloper.setSpecialty("java_developer");
        testDeveloper.setStatus(Status.ACTIVE);

        testDeveloper1 = new Developer();
        testDeveloper1.setId(TEST_DEVELOPER_ID +1);
        testDeveloper1.setFirstName("Eugene");
        testDeveloper1.setLastName("Suleimanov");
        testDeveloper1.setSkill("SQL");
        testDeveloper1.setSpecialty("java_developer");
        testDeveloper1.setStatus(Status.ACTIVE);

        testDevelopersList = Arrays.asList(testDeveloper, testDeveloper1);
    }

    @Test
    void create()  {
        doReturn(testDeveloper).when(developerRepositoryMock).save(testDeveloper);
        developerRepositoryMock.save(testDeveloper);
        assertEquals(testDeveloper.getFirstName(), "Alex");
        assertEquals(testDeveloper.getLastName(), "Semkin");
        assertEquals(testDeveloper.getSkill(), "Maven");
        assertEquals(testDeveloper.getSpecialty(), "java_developer");
    }

    @Test
    void deleteById() {
       developerRepositoryMock.deleteById(TEST_DEVELOPER_ID);
       Mockito.verify(developerRepositoryMock, times(1)).deleteById(anyLong());
       Mockito.verify(developerRepositoryMock).deleteById(idCaptor.capture());
       assertEquals(TEST_DEVELOPER_ID, idCaptor.getValue());
    }

    @Test
    void update() {
        when(developerRepositoryMock.update(testDeveloper)).thenReturn(testDeveloper);
        assertEquals(testDeveloper, developerRepositoryMock.update(testDeveloper));
    }

    @Test
    void getById() {
        when(developerServiceMock.getById(TEST_DEVELOPER_ID)).thenReturn(testDeveloper);
        assertEquals(testDeveloper, developerServiceMock.getById(TEST_DEVELOPER_ID));
        Mockito.verify(developerRepositoryMock).getById(TEST_DEVELOPER_ID);

    }

    @Test
    void getAll() {
        when(developerRepositoryMock.getAll()).thenReturn(testDevelopersList);
        List<Developer> currentList = developerServiceMock.getAll();
        Mockito.verify(developerRepositoryMock).getAll();
        assertEquals(testDevelopersList, currentList);
    }
}
