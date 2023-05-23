public class Converter {

    double distKmForOneStep = 0.00075; // Дистанция --> 1 шаг = 75 см - 100.000см = 1км
    double kiloCalForOneStep = 0.05; // Количество сожжённых килокалорий --> 1 шаг = 50 калорий / 1 Килокалория = 1 000 Калорий.


    double kiloCalForSumStepsMonth(int sumSteps) { //  Подсчет >>Килокалории<< за общ кол шагов в месяц для ** команды 2 **

        return sumSteps * kiloCalForOneStep;

        /*
        // изменено по рекомендации сразу с возвратом не создавая новую переменную -- >> как вверху!
        double kiloCalForSumStepsMonth = sumSteps * kiloCalForOneStep;
        return kiloCalForSumStepsMonth;
         */
    }

    double distanceForSumStepsMonth(int sumSteps) { //  Подсчет >>Дистанции<< за общ кол шагов в месяц для ** команды 2 **

        return sumSteps * distKmForOneStep;

        /*
        // изменено по рекомендации сразу с возвратом не создавая новую переменную -- >> как вверху!
        double distanceForSumStepsMonth = sumSteps * distKmForOneStep;
        return distanceForSumStepsMonth;
        */
    }

}
