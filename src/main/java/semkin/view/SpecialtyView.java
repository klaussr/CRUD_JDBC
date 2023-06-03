package semkin.view;

import semkin.controller.SpecialtyController;
import semkin.model.Message;
import semkin.model.Specialty;

import java.util.Scanner;

public class SpecialtyView extends BaseView {
    private final String mainMenuMessage = "Выберите действие над специальностями:\n" +
            " 1. Создать специальность\n" +
            " 2. Редактировать специальность\n" +
            " 3. Удалить специальность\n" +
            " 4. Вывести список специальностей\n" +
            " 5. Выход";

    private final String printMenuMessage = "Список специальностей\n";

    private final String createMenuMessage = "Создание специальности.";

    private final String editMenuMessage = "Редактирование специальности.\n";

    private final String deleteMenuMessage = "Удаление специальности.\n";

    private final SpecialtyController specialtyController = new SpecialtyController();

    private final Scanner scanner = new Scanner(System.in);

    public SpecialtyView() {

    }

    public void getById() throws Exception {
        System.out.println(Message.ID);
        Long id = scanner.nextLong();
        specialtyController.getById(id);
    }

    public void create() throws Exception {
        System.out.println(createMenuMessage);
        System.out.println(Message.NAME.getMessage());
        String name = scanner.next();
        specialtyController.create(name);
    }

    @Override
    void edit() throws Exception {
        System.out.println(editMenuMessage);
        System.out.println(Message.ID.getMessage());
        Long id = Long.parseLong(scanner.next());
        System.out.println(Message.NAME.getMessage());
        String name = scanner.next();
        specialtyController.update(id, name);

    }

    @Override
    void delete() throws Exception {
        System.out.println(deleteMenuMessage);
        System.out.println(Message.ID);
        Long id = Long.parseLong(scanner.next());
        specialtyController.delete(id);
    }

    @Override
    void print() throws Exception {
        System.out.println(printMenuMessage);
        for (Specialty specialty : specialtyController.getAll()) {
            System.out.println("id: " + specialty.getId() + "\tname: " + specialty.getName());
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


