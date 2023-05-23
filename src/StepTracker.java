import java.util.Arrays;
import java.util.Scanner;

public class StepTracker {

    class MonthData {
        // Заполните класс самостоятельно
        int[] days;

        public MonthData() {
            days = new int[30];
        }

    }
    int adviceTargetDayStep = 10000; // по умолчанию в первом запуске установлено кол-во шагов в день
    int sumSteps = 0;
    char inputNotTrue = '\u2718';
    char afterSaving = '\u2611';
    char warningMark = '\u2A3B';
    MonthData[] monthToData; // все данные о кол-ве шагов сохраняется в этом массиве т.е. как Database!

    public StepTracker() { // присваивает месяцы и в них добавляет 30дней
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    // метод inputNewStep перенесен сюда из класса Main по рекомендации как внизу;
 /*
 inputNewStep(scanner, stepTracker, inputData, inputDataFalse);
отлично, хорошее решение перенести логику внутрь метода целиком - это делает данный класс менее нагруженным
 */
    void inputNewStep(Scanner scanner, StepTracker stepTracker, char inputData, char inputDataFalse) { // КОМАНДА ==> 1  --  Пользователь введет пройденные шаги
        int inputToMonth = -1;
        int inputToDay = 0;
        int inputNewSteps = 0;


        System.out.println("Пожалуйста введите необходимые данные для сохранения;");
        System.out.print(inputData + " Номер месяца (0 Янв, 1 Фев, 2 Мар, ... 11 Дек) --> ");
        inputToMonth = scanner.nextInt();

        while ((inputToMonth < 0) || (inputToMonth > 11)) {
            System.out.print(inputDataFalse + " Не корректно ввели данные, пожалуйста повторяйте еще раз << в диапазоне (0 Янв, 1 Фев, 2 Мар, ... 11 Дек) >> --> ");
            inputToMonth = scanner.nextInt();
        }

        Main.inputToMonthStringNameMonth(inputToMonth, inputData); // информационная фраза сохранена в методе
        inputToDay = scanner.nextInt();
        while  ((inputToDay < 1) || (inputToDay > 30)) { // ***** ОБЪЯСНЕНИЕ РЕВЬЮЕРУ *****: НОВЫЙ ВВОД РАЗРЕШЕН ЦИФРОЙ 0 ТАК, КАК ДАЕМ ВОЗМОЖНОСТЬ ИСПРАВИТЬ ПОЛЬЗАВАТЕЮ В СЛУЧАЕ ОШИБОЧНОГО ВВОДА ЕСЛИ ТЕКУЩИЙ ДЕНЬ ВООБЩЕ ПУСТОЙ
            System.out.print(inputDataFalse + " Не корректно ввели данные! Пожалуйста повторяйте еще раз << в диапазоне (1 - 30) >> --> ");
            inputToDay = scanner.nextInt();
        }

        System.out.print(inputData + " Пройденное количество шагов --> ");
        inputNewSteps = scanner.nextInt();
        while (inputNewSteps < 0) {
            System.out.print(inputDataFalse + " Не корректно ввели данные! Пожалуйста введите << положительное >> количество шагов --> ");
            inputNewSteps = scanner.nextInt();
        }

        saveInputSteps(inputToMonth, inputToDay, inputNewSteps);

        // напечатает на экран после сохранения данных для убиждения пользователя
        afterSavingPrintInfoInputtedSteps(inputToMonth, inputToDay, inputNewSteps); // для контроля передаются ли в класс на отработку
    }
    void saveInputSteps(int inputToMonth, int inputToDay, int inputNewSteps){ // сохранение данный пользователем команда ==> 1
        monthToData[inputToMonth].days[inputToDay-1] = inputNewSteps; // days[inputToDay-1] --> здесь -1 так, как индекс дня с нуля начинается!
    }
    void afterSavingPrintInfoInputtedSteps(int inputToMonth, int inputToDay, int inputNewSteps){ // для проверки ланных после введнеия пользователем

        System.out.print(afterSaving + " Данные сохранены введенные Вами;  ");
        System.out.println("Месяц: " + inputToMonth +  "  День: " + inputToDay + "  Шаги: " + monthToData[inputToMonth].days[inputToDay-1]);
        System.out.println("----------------------------------------\n");
        // days[inputToDay-1] --> здесь -1 так, как индекс дня с нуля начинается!
    }
    void printAdviceTargetDayStep() { // Показывает кол-чо шагов при первом запуске прог
        if(adviceTargetDayStep == 10000) // в программе по умолчанию установлено 10.000, при этом показывается такое сообщение
            System.out.println("Цель количества шагов за день по умолчанию: " + adviceTargetDayStep + " (Рекомендована).");
        else
            System.out.println("Цель количества шагов за день: " + adviceTargetDayStep + " (Ранее Вами установлена).");
    }
    void changeTargetDayStep(int inputNewTargetDayStep) { // команда ==> 3
        adviceTargetDayStep = inputNewTargetDayStep;
        System.out.println("\n===========================================================");
        System.out.println(afterSaving + " Новая цель количества шагов за день (обновлена!): " + adviceTargetDayStep);
        System.out.println("===========================================================");
        System.out.println();
    }
    void stepsDayMonth(int numberMonthStatistics) { // получает дни и шаги дня указанного месяца для ** команды 2 **
        int d = 1;
        System.out.println("Дни месяца и Количество шагов: \n--------------------------------");
        for (int i = 0; i<monthToData[numberMonthStatistics].days.length; i++) {
            System.out.print(d + " День: " + monthToData[numberMonthStatistics].days[i] + ", ");
            d++;
        }
        System.out.println("\n----------------------------------------");
    }
    int sumStepsMonth(int numberMonthStatistics) { // получает общее количество указанного месяца для ** команды 2 **

        sumSteps = 0; // Здесь эту переменную именно обнулили чтобы в каждый раз при получении статистики сохраненное кол-во не прибавлялось
        for (int i = 0; i<monthToData[numberMonthStatistics].days.length; i++) {
            sumSteps = sumSteps + monthToData[numberMonthStatistics].days[i];
        }
        return sumSteps;
    }
    void maxStepsMonth(int numberMonthStatistics) { // находит >>Максимальное<< кол-во шагов указанного месяца для  ** команды 2 **
        int maxSteps = 0;
        for (int i = 0; i<monthToData[numberMonthStatistics].days.length; i++) {
            if(monthToData[numberMonthStatistics].days[i] > maxSteps) {
                maxSteps = maxSteps + monthToData[numberMonthStatistics].days[i];
                maxSteps = monthToData[numberMonthStatistics].days[i];
            }
        }
        System.out.print("Пройдено << Максимальное >> кол-во шагов: " + maxSteps);
        System.out.println("\n----------------------------------------");
    }
    void avgStepsMonth(int numberMonthStatistics) { // находит >>Среднее<< кол-во шагов указанного месяца для  ** команды 2 **
        double avgSteps = 0;
        for (int i = 0; i<monthToData[numberMonthStatistics].days.length; i++) {
                avgSteps = avgSteps + monthToData[numberMonthStatistics].days[i];
        }
        avgSteps = avgSteps / monthToData[numberMonthStatistics].days.length;
        System.out.print("Пройдено << Среднее >> кол-во шагов: " + avgSteps);
        System.out.println("\n----------------------------------------");
    }
    // ***ИСПРАВЛЕНИЕ*** Метод >>> bestSeriesStepsMonth <<<  По рекомендации алгоритм сократился
    void bestSeriesStepsMonth(int numberMonthStatistics) { // находит >>Лучшую Серию<< кол-ва шагов указанного месяца для  ** команды 2 **
        int countBestSeriesSteps = 0;
        int k = 0;
        for (int i = 0; i < monthToData[numberMonthStatistics].days.length; i++) {
            if (adviceTargetDayStep != 0) {
                if (monthToData[numberMonthStatistics].days[i] >= adviceTargetDayStep) {
                    k++;
                } else {
                    k = 0;
                }
                if (k > countBestSeriesSteps) {
                    countBestSeriesSteps = countBestSeriesSteps + 1;
                }
            }
        }
        System.out.print("Количество << Лучшая Серия >> пройденных шагов: " + countBestSeriesSteps);
        System.out.println("\n----------------------------------------");
    }
    void stringNameMonth(int numberMonthStatistics) { // переврашаем введенное число месяца в название соответствующего месяца для ** команды 2 **
        String monthName;
        switch (numberMonthStatistics) {
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
        System.out.println("\n====================================================");
        System.out.println("Статистика в месяце << " + monthName + " >> перечислена внизу;");
        System.out.println("====================================================");

    }

    void printKiloCalForSumStepsMonth(Converter converter) { //  Подсчет >>Килокалории<< за общ кол шагов в месяц для ** команды 2 **
        System.out.println("Сожжённых Килокалорий (килокалория) : " + converter.kiloCalForSumStepsMonth(sumSteps));
        System.out.println("----------------------------------------");
    }

    void printDistanceForSumStepsMonth(Converter converter) { //  Подсчет >>Дистанции<< за общ кол шагов в месяц для ** команды 2 **
        System.out.println("Пройденная Дистанция (км) : " + converter.distanceForSumStepsMonth(sumSteps));
        System.out.println("----------------------------------------\n");
    }

    public void inputStatisticsMonth(Scanner scanner, StepTracker stepTracker, Converter converter) { // КОМАНДА ==> 2 -- Пользователь печатает статистику указанного определьенного месяца

        System.out.print("Пожалуйста введите номер нужного месяца для статистики (0- Янв, 1- Фев, ... 11-Дек) --> ");
        int numberMonthStatistics = scanner.nextInt();
        while ((numberMonthStatistics < 0) || (numberMonthStatistics > 11)) {
            System.out.print(inputNotTrue + " Не корректно ввели число месяца! Пожалуйста повторяйте еще раз << в диапазоне (0 Янв, 1 Фев, 2 Мар, ... 11 Дек) >> --> ");
            numberMonthStatistics = scanner.nextInt();
        }
        // sumStepsMonth метод сразу в начале запускается, чтобы метод работал и понять переменная sumSteps выбранного месяца пустая или нет.
        sumStepsMonth(numberMonthStatistics); // получает общее количество указанного месяца для ** команды 2 **
        if (sumSteps != 0) { // Если выбранный месяц НЕ пустой, то печатается статистика на экран;
            stringNameMonth(numberMonthStatistics); // превращаем введенное число месяца в название соответствующего месяца для ** команды 2 **
            stepsDayMonth(numberMonthStatistics); // получает дни и шаги дня указанного месяца  для ** команды 2 **
            System.out.print("Пройдено << Всего >> кол-во шагов: " + sumSteps); // печатает общее количество указанного месяца для ** команды 2 **
            System.out.println("\n----------------------------------------");
            maxStepsMonth(numberMonthStatistics); // находит >>Максимальное<< кол-во шагов указанного месяца для  ** команды 2 **
            avgStepsMonth(numberMonthStatistics); // находит >>Среднее<< кол-во шагов указанного месяца для  ** команды 2 **
            bestSeriesStepsMonth(numberMonthStatistics); // находит >>Лучшую Серию<< кол-ва шагов указанного месяца для  ** команды 2 **
            printKiloCalForSumStepsMonth(converter);  //  Подсчет >>Килокалории<< за общ кол шагов в месяц для ** команды 2 **
            printDistanceForSumStepsMonth(converter); //  Подсчет >>Дистанции<< за общ кол шагов в месяц для ** команды 2 **
        }

        else { // Если выбранный месяц пустой, то цикл запускается для направления и контроля пользователя
            int backToMainMenu = 12; // чтобы прекратить цикл при ситуации все месяцы если пустые, а то цикл может бесконечным
            // (если месяцы начались бы с 1, то здесь вместо цифры "12", указана цифра "0" была бы)
            while (numberMonthStatistics != backToMainMenu) { // Пока не выходим и не вернемся в Глав Меню, продолжается запрос статистики
                    System.out.println(warningMark + " В этом месяце << Отсутствуют Данные >> пройденных шагов!");
                    System.out.println("Пожалуйста введите номер << другого >> месяца для статистики (0- Янв, 1- Фев, ... 11-Дек)");
                    System.out.print("(чтобы вернуться в << Главное Меню >> введите << 12 >>)  --> ");
                    numberMonthStatistics = scanner.nextInt();
                }
            System.out.println("--------------------------------------------------------");
            }
        }


    }