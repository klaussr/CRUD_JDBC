package semkin.repository.jdbc;

import semkin.model.Skill;
import semkin.repository.SkillRepository;
import semkin.util.JdbcUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcSkillRepositoryImpl implements SkillRepository {
    public Skill getById(final Long id) {
        return Objects.requireNonNull(getAll()).stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(Long id) {
        String sql = "delete from skills where id = ?";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql)) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Skill update(Skill item) {
        List<Skill> currentSkills = getAll();
        for (Skill skill : Objects.requireNonNull(currentSkills)) {
            if (skill.getId().equals(item.getId())) {
                deleteById(skill.getId());
            }
        }
        save(item);
        return item;
    }


    public Skill save(Skill item) {
        String sql = "insert into skills (skill) values (?)";
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatementWithKeys(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return item;
    }

    private Skill mapResultSetToSkills(ResultSet resultSet) {
        long id = 0;
        try {
            id = resultSet.getInt("id");
            String skillName = resultSet.getString("skill");
            Skill skill = new Skill();
            skill.setId(id);
            skill.setName(skillName);
            return skill;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Skill> getAll() {
        String sql = "select * from skills ";
        List<Skill> skills = new ArrayList<>();
        try (PreparedStatement preparedStatement = JdbcUtils.preparedStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                skills.add(mapResultSetToSkills(resultSet));
            }
        } catch (SQLException e) {
            System.out.println(e);

        }
        return skills;
    }
}
