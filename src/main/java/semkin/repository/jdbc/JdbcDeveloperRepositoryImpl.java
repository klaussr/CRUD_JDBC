package semkin.repository.jdbc;


import semkin.model.Developer;
import semkin.model.Status;
import semkin.repository.DeveloperRepository;
import semkin.util.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcDeveloperRepositoryImpl implements DeveloperRepository {

    public Developer getById(final Long id) {
        return Objects.requireNonNull(getAll()).stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        String sql = "delete from developers where id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Developer update(Developer item) {
        List<Developer> currentDevelopers = getAll();
        for (Developer developer : Objects.requireNonNull(currentDevelopers)) {
            if (developer.getId().equals(item.getId())) {
                deleteById(developer.getId());
            }
        }
        save(item);
        return item;
    }

    public Developer save(Developer item) {
        String sql = "insert into developers (firstName, lastName, skills, specialtyId, status) values (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatementWithKeys(sql)) {
            preparedStatement.setString(1, item.getFirstName());
            preparedStatement.setString(2, item.getLastName());
            preparedStatement.setString(3, item.getSkill());
            preparedStatement.setString(4, item.getSpecialty());
            preparedStatement.setString(5, String.valueOf(item.getStatus()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }

    private Developer mapResultSetToDeveloper(ResultSet resultSet) {
        long id = 0;
        try {
            id = resultSet.getInt("id");
            String developerFirstName = resultSet.getString("firstName");
            String developerLastName = resultSet.getString("lastName");
            String developerSkill = resultSet.getString("skill");
            String developerSpecialty = resultSet.getString("specialty_name");
            String developerStatus = resultSet.getString("status_name");
            Developer developer = new Developer();
            developer.setId(id);
            developer.setFirstName(developerFirstName);
            developer.setLastName(developerLastName);
            developer.setSkill(developerSkill);
            developer.setSpecialty(developerSpecialty);
            developer.setStatus(Status.valueOf(developerStatus));

            return developer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Developer> getAll() {
        String sql = "select developers.id, developers.firstName, developers.lastName, s.specialty_name, s2.skill, s3.status_name from developers " +
                     " join specialties s on s.id = developers.specialtyId " +
                     " join skills s2 on s2.id = developers.skills " +
                     " join status s3 on s3.status_name = developers.status";
         List<Developer> developers = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                developers.add(mapResultSetToDeveloper(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return developers;
    }
}
