package semkin.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.*;
import semkin.model.Specialty;
import semkin.repository.SpecialtyRepository;
import semkin.service.impl.SpecialtyServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SpecialtyServiceTest {
    private static final Long TEST_SPECIALTY_ID = 1L;

    @Mock
    SpecialtyRepository specialtyRepositoryMock;


    @InjectMocks
    SpecialtyServiceImpl specialtyServiceMock;
    Specialty testSpecialty;
    Specialty testSpecialty1;
    List<Specialty> testListSpecialties;

    @Captor
     ArgumentCaptor<Long> idCaptor;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeEach
    void setUp() {
        Mockito.reset(specialtyRepositoryMock);
        testSpecialty = new Specialty();
        testSpecialty.setId(TEST_SPECIALTY_ID);
        testSpecialty.setName("test");

        testSpecialty1 = new Specialty();
        testSpecialty1.setId(TEST_SPECIALTY_ID+1);
        testSpecialty1.setName("test1");

        testListSpecialties = Arrays.asList(testSpecialty, testSpecialty1);
    }

    @Test
    void create() {
        doReturn(testSpecialty).when(specialtyRepositoryMock).save(testSpecialty);
        specialtyServiceMock.create(testSpecialty.getName());
        assertEquals(testSpecialty.getName(), "test");
    }

    @Test
    void deleteById()  {
       specialtyRepositoryMock.deleteById(TEST_SPECIALTY_ID);
       Mockito.verify(specialtyRepositoryMock, times(1)).deleteById(anyLong());
       Mockito.verify(specialtyRepositoryMock).deleteById(idCaptor.capture());
       assertEquals(TEST_SPECIALTY_ID, idCaptor.getValue());
    }

    @Test
    void update()  {
        when(specialtyRepositoryMock.update(testSpecialty)).thenReturn(testSpecialty);
        assertEquals(testSpecialty, specialtyRepositoryMock.update(testSpecialty));
    }

    @Test
    void getById() {
        when(specialtyServiceMock.getById(TEST_SPECIALTY_ID)).thenReturn(testSpecialty);
        assertEquals(testSpecialty, specialtyServiceMock.getById(TEST_SPECIALTY_ID));
        Mockito.verify(specialtyRepositoryMock).getById(TEST_SPECIALTY_ID);

    }

    @Test
    void getAll() throws Exception {
        when(specialtyRepositoryMock.getAll()).thenReturn(testListSpecialties);
        List<Specialty> currentList = specialtyServiceMock.getAll();
        Mockito.verify(specialtyRepositoryMock).getAll();
        assertEquals(testListSpecialties, currentList);
    }
}
