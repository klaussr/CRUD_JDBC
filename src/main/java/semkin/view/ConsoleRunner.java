package semkin.view;

import semkin.model.Message;

import java.util.Scanner;

public class ConsoleRunner {

    private BaseView skillView;
    private BaseView specialtyView;
    private BaseView developerView;

    private final String damagedDataMessage = "Данные повреждены!";

    private final String menuMessage = "Выберите действие:\n" +
                                        "1. Навыки\n" +
                                        "2. Разработчики\n" +
                                        "3. Специальности\n" +
                                        "4. Выход";


    private final Scanner scanner = new Scanner(System.in);

    public ConsoleRunner(){
        try {
            //create views
            skillView = new SkillView();
            developerView = new DeveloperView();
            specialtyView = new SpecialtyView();
        }
        catch (Exception ex)
        {
            System.out.println(damagedDataMessage);
        }
    }

    public void run() throws Exception {
        boolean isExit = false;
        do {
            System.out.println(Message.LINE.getMessage());
            System.out.println(menuMessage);
            System.out.println(Message.LINE.getMessage());
            String response = scanner.next();
            switch (response) {
                case "1":
                    skillView.show();
                    break;
                case "2":
                    developerView.show();
                    break;
                case "3":
                    specialtyView.show();
                    break;
                case "4":
                    isExit = true;
                    break;
                default:
                    System.out.println(Message.ERROR_INPUT.getMessage());
                    break;
            }
        } while (!isExit);
        scanner.close();
    }
}
