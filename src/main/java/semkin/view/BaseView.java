package semkin.view;


import semkin.model.Message;

import java.util.Scanner;

public abstract class BaseView {

    protected String message;
    protected Scanner scanner;

    abstract void create() throws Exception;

    abstract void edit() throws Exception;

    abstract void delete() throws Exception;

    abstract void print() throws Exception;

    void show() throws Exception {
        boolean isExit = false;
        do {
            System.out.println(Message.LINE.getMessage());
            System.out.println(message);
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
