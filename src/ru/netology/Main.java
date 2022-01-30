package ru.netology;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Task> taskManager = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = "";
            while (!input.equals("0")) {
                actionList();
                input = scanner.nextLine();
                switch (input) {
                    case ("1"):
                        System.out.println("Добавляем задачу.");
                        System.out.println("Имя задачи : ");
                        String name = scanner.nextLine();
                        if (taskManagerAddTask(taskManager, new Task(name))) {
                            System.out.println("Задача добавлена!\n");
                        } else {
                            System.out.println("Что-то пошло не так...\n");
                        }
                        break;
                    case ("2"):
                        taskManagerShowList(taskManager);
                        break;
                    case ("3"):
                        if(taskManager.size() > 0) {
                            try { // отлавливаем исключение NumberFormatException при переводе из String в Integer
                                System.out.println("Удаляем задачу.");
                                System.out.println("Номер задачи : ");
                                int taskNumber = Integer.parseInt(scanner.nextLine()) - 1;
                                System.out.println(taskManagerRemoveTask(taskManager, taskNumber));
                            } catch (NumberFormatException e) {
                                System.out.println("Неверный формат ввода! Пожалуйста, введите номер задачи!\n");;
                            }
                        } else {
                            System.out.println("Нечего удалять! Список задач пуст!\n");
                        }
                        break;
                    default:
                        continue;
                }
            }
        System.out.println("Программа завершена!");
    }
    // список возможных действий
    public static void actionList() {
        System.out.println("""
                Ваши действия:
                1. Добавить задачу
                2. Вывести список задач
                3. Удалить задачу
                0. Выход""");
    }
    // список задач
    public static void taskManagerShowList(ArrayList<Task> taskManager) {
        if (taskManager.size() == 0) {
            System.out.println("Список задач пуст! Добавьте задачу!\n");
        } else {
            System.out.println("Список задач: ");
            for (int i = 0; i < taskManager.size(); i++) {
                String name = taskManager.get(i).getTaskName();
                System.out.println((i+1) + ". " + name);
            }
            System.out.println();
        }

    }
    // добавить задачу
    public static boolean taskManagerAddTask(ArrayList<Task> taskManager, Task task) {
        return taskManager.add(task);
    }
    // удалить задачу
    public static String taskManagerRemoveTask(ArrayList<Task> taskManager, int taskNumber) {
        if(taskManagerInRange(taskManager, taskNumber)) {
            taskManager.remove(taskNumber);
            return "Задача под номером " + (taskNumber + 1) + " удалена успешно!\n";
        }
        return "Нет задачи с таким номером!\n";
    }
    // проверка на вхождение в диапазон
    public static boolean taskManagerInRange(ArrayList<Task> taskManager, int taskNumber) {
        if(taskNumber >=0 && taskNumber <= taskManager.size() - 1) {
            return true;
        }
        return false;
    }
}
