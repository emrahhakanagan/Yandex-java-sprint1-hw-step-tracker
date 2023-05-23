import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char inputData = '\u27A5';
        char inputDataFalse = '\u2718';

        Scanner scanner = new Scanner(System.in);
        int userInput = 1111111111;
        printMainMenu(userInput, inputDataFalse); // в начале программы сразу показываем Главное Меню

        // int userInput = scanner.nextInt(); - здесь немного меняю логику показа меню зависимо от ввода пользователя
        userInput = scanner.nextInt();

       // Создаем объекты из классов
        StepTracker stepTracker = new StepTracker();
        Converter converter = new Converter();

        while (userInput != 0) {
            // обработка разных случаев
            if (userInput==1)
                stepTracker.inputNewStep(scanner, stepTracker, inputData, inputDataFalse);
// метод перенесен в класс StepTracker по рекомендации как внизу; только как здесь отдельный метод чтобы все не загружать в один метод и разбираться проще
 /*
 inputNewStep(scanner, stepTracker, inputData, inputDataFalse);
отлично, хорошее решение перенести логику внутрь метода целиком - это делает данный класс менее нагруженным
 */
            if (userInput==2)
                stepTracker.inputStatisticsMonth(scanner, stepTracker, converter);
            if (userInput==3)
                inputChangingTargetDayStep(scanner, stepTracker, inputDataFalse, stepTracker.adviceTargetDayStep); // изменяется цель количества шагов в день

            printMainMenu(userInput, inputDataFalse); // показываем Главное Меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Программа завершена");
    }

    public static void printMainMenu(int userInput, char inputDataFalse) {
        if (((userInput>=0) && (userInput<=3)) || userInput==1111111111)
            System.out.println("Пожалуйста выберите интересующиеся Вас выбор:");
        else
            System.out.println(inputDataFalse + " Извините, такой команды нет! Пожалуйста введите соответствующую команду:");

        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }

    public static void inputChangingTargetDayStep(Scanner scanner, StepTracker stepTracker, char inputNotTrue, int adviceTargetDayStep) { // КОМАНДА ==> 3 -- Пользователь меняет цель кол-ча шагов
        int inputNewTargetDayStep; // объявил данную переменную здесь при команде <3> чтобы она была создана чтобы просто не занимала место в памяти
        stepTracker.printAdviceTargetDayStep();
        System.out.print("Пожалуйста введите новую желаемую цель количества шагов за день --> ");
        inputNewTargetDayStep = scanner.nextInt();
        while (inputNewTargetDayStep<=0) { // Пока новая цель не больше нуля, запрос зависимо от условия повторяется
            if (inputNewTargetDayStep<0) // Новая цель не может быть меньше нуля
                System.out.print(inputNotTrue + " Не верное число! Пожалуйста введите << НЕ ОТРИЦАТЕЛЬНОЕ >> число --> ");
            else // Новая цель не может быть равна нулю
                System.out.print(inputNotTrue + " Не верное число! Пожалуйста введите число << БОЛЬШЕ НУЛЯ >> --> ");

            inputNewTargetDayStep = scanner.nextInt();
        }
        while(inputNewTargetDayStep == adviceTargetDayStep) { // цикл для контроля того, что новая цель введена одинаково с установленной или нет
            String choose;
            System.out.println(stepTracker.warningMark + " Ввели цель ранее установленную!");
            System.out.print(stepTracker.warningMark + " Если хотите << оставить >> текущую цель, введите << да >> / если хотите << изменить >> введите << нет >> --> ");
            choose = scanner.next();
            if ((!choose.equals("да")) && (!choose.equals("нет"))) { // если пользователь ввел что-то иное, предлагается 1 из 2х вариантов
                System.out.print(inputNotTrue + " Пожалуйста введите один из вариантов чтобы подтвердить введите << да >> или << нет >>  --> ");
                choose = scanner.next();
            }
            if (choose.equals("нет")) { //если здесь использовать else if, то любой вариант да или нет цикл заново запускает
                System.out.print("Пожалуйста введите новую желаемую цель количества шагов за день --> ");
                inputNewTargetDayStep = scanner.nextInt();
            }
            else break;
        }

        stepTracker.changeTargetDayStep(inputNewTargetDayStep);
    }

    public static void inputToMonthStringNameMonth(int inputToMonth, char inputData) { // переврашаем введенное число месяца в название соответствующего месяца для ** команды 1 **
        String monthName;
        switch (inputToMonth) {
            case 0:  monthName = "Январь";
                break;
            case 1:  monthName = "Февраль";
                break;
            case 2:  monthName = "Март";
                break;
            case 3:  monthName = "Апрель";
                break;
            case 4:  monthName = "Май";
                break;
            case 5:  monthName = "Июнь";
                break;
            case 6:  monthName = "Июль";
                break;
            case 7:  monthName = "Август";
                break;
            case 8:  monthName = "Сентябрь";
                break;
            case 9: monthName = "Октябрь";
                break;
            case 10: monthName = "Ноябрь";
                break;
            case 11: monthName = "Декабрь";
                break;
            default: monthName = "Не знаем такого";
                break;
        }
        System.out.print(inputData + " В месяце < " + monthName + " > в какой < день > хотите сохранить (1-30) --> ");
    }

}
