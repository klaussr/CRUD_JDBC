package semkin.repository.jdbc;

import semkin.model.Specialty;
import semkin.repository.SpecialtyRepository;
import semkin.util.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcSpecialtyRepositoryImpl implements SpecialtyRepository {

    public Specialty getById(final Long id) {
        return Objects.requireNonNull(getAll()).stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        String sql = "delete from specialties where id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Specialty update(Specialty item) {
        List<Specialty> currentSpecialties = getAll();
        for (Specialty specialty : Objects.requireNonNull(currentSpecialties)) {
            if (specialty.getId().equals(item.getId())) {
                deleteById(specialty.getId());
            }
        }
        save(item);
        return item;
    }


    public Specialty save(Specialty item) {
        String sql = "insert into specialties (specialty_name) values (?)";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatementWithKeys(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.executeUpdate();
        }
         catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }

    private Specialty mapResultSetToSpecialties(ResultSet resultSet) {
        long id = 0;
        try {
            id = resultSet.getInt("id");
            String specialtyName = resultSet.getString("specialty_name");
            Specialty specialty = new Specialty();
            specialty.setId(id);
            specialty.setName(specialtyName);
            return specialty;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Specialty> getAll() {
        String sql = "select * from specialties ";
        List<Specialty> specialties = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                specialties.add(mapResultSetToSpecialties(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return specialties;
    }
}
