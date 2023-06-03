package semkin.view;

import semkin.controller.SkillController;
import semkin.model.Message;
import semkin.model.Skill;

import java.util.Scanner;

public class SkillView extends BaseView {

    private final String mainMenuMessage = "Выберите действие над навыками:\n" +
            " 1. Создать\n" +
            " 2. Редактировать\n" +
            " 3. Удалить\n" +
            " 4. Вывести список навыков\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список навыков:\n";

    private final String createMenuMessage = "Создание навыков.\n";

    private final String editMenuMessage = "Редактирование навыка.\n";

    private final String deleteMenuMessage = "Удаление навыка\n";


    private final SkillController skillController = new SkillController();
    private final Scanner scanner = new Scanner(System.in);

    public SkillView() {

    }

    public void getById() throws Exception {
        System.out.println(Message.ID.getMessage());
        Long id = scanner.nextLong();
        skillController.getById(id);
    }

    public void create() throws Exception {
        System.out.println(createMenuMessage);
        System.out.println(Message.NAME.getMessage());
        String name = scanner.next();
        skillController.create(name);
    }

    @Override
    void edit() throws Exception {
        System.out.println(editMenuMessage);
        System.out.println(Message.ID.getMessage());
        Long id = Long.parseLong(scanner.next());
        System.out.println(Message.NAME.getMessage());
        String newName = scanner.next();
        skillController.update(id, newName);
    }

    @Override
    public void delete() throws Exception {
        System.out.println(deleteMenuMessage);
        System.out.println(Message.ID.getMessage());
        Long id = Long.parseLong(scanner.next());
        skillController.delete(id);
    }

    @Override
    void print() throws Exception {
        System.out.println(printMenuMessage);
        for (Skill skill: skillController.getAll()){
            System.out.println(skill.getId() + "\t" + skill.getName());
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
