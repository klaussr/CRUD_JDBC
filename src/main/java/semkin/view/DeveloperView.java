package semkin.view;

import semkin.controller.DeveloperController;
import semkin.controller.SkillController;
import semkin.controller.SpecialtyController;
import semkin.model.Developer;
import semkin.model.Message;
import semkin.model.Skill;
import semkin.model.Specialty;

import java.util.Scanner;

public class DeveloperView extends BaseView {

    private final String mainMenuMessage = "Выберите действие над разработчиками:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список разработчиков:\n";


    private final String createMenuMessage = "Создание разработчика.\n";

    private final String editMenuMessage = "Редактирование разработчика.\n";

    private final String deleteMenuMessage = "Удаление разработчика\n";

    private final String addSkillMessage = "Введите id навыка";

    private final String addSpecialtyMessage = "Введите название специальности";

    private final DeveloperController developerController = new DeveloperController();
    private final SkillController skillController = new SkillController();
    private final SpecialtyController specialtyController = new SpecialtyController();

    private final Scanner scanner = new Scanner(System.in);

    public DeveloperView() {

    }

    public void getById() throws Exception {
        System.out.println(Message.ID.getMessage());
        Long id = scanner.nextLong();
        developerController.getById(id);
    }

    public void create() throws Exception {
        System.out.println(createMenuMessage);
        System.out.println(Message.FIRST_LASTNAME.getMessage());
        String firstName = scanner.next();
        String lastName = scanner.next();

        for (Skill skill : skillController.getAll()) {
            System.out.println(skill.getId() + " " + skill.getName());
        }
        System.out.println(addSkillMessage);
        String  skill = scanner.next();

        for (Specialty specialty : specialtyController.getAll()) {
            System.out.println(specialty.getId() + " " + specialty.getName());
        }
        System.out.println(addSpecialtyMessage);
        String specialty = scanner.next();

        developerController.save(firstName, lastName, skill, specialty);
        System.out.println();
    }

    @Override
    void edit() throws Exception {
        System.out.println(editMenuMessage);
        System.out.println(Message.ID.getMessage());
        long id = Long.parseLong(scanner.next());
        System.out.println(Message.FIRST_LASTNAME.getMessage());
        String newFirstName = scanner.next();
        String newLastName = scanner.next();
        System.out.println(addSkillMessage);
        String skillName = scanner.next();
        System.out.println(addSpecialtyMessage);
        String specialtyName = scanner.next();
        developerController.update(id, newFirstName, newLastName, skillName, specialtyName);
    }

    @Override
    public void delete() throws Exception {
        System.out.println(deleteMenuMessage);
        System.out.println(Message.ID.getMessage());
        Long id = Long.parseLong(scanner.next());
        developerController.delete(id);
    }


    @Override
    void print() throws Exception {
        System.out.println(printMenuMessage);
        for (Developer developer : developerController.getAll()) {
            System.out.println(developer.getId() + "\tName&Surname: " + developer.getFirstName() + " " + developer.getLastName() + "\tskills: " +
                    developer.getSkill() + "\tspecialty: " + developer.getSpecialty());
        }
    }

    @Override
    public void show() throws Exception {
        boolean isExit = false;
        do {
            System.out.println(Message.LINE.getMessage());
            System.out.println(mainMenuMessage);
            System.out.println(Message.LINE.getMessage());
            String response = scanner.next();
            switch (response) {
                case "1":
                    create();
                    break;

                case "2":
                    edit();
                    break;
                case "3":
                    delete();
                    break;
                case "4":
                    print();
                    break;
                case "5":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
    }
}
