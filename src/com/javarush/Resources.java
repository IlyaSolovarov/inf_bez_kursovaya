package com.javarush;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Resources {
    static String algoDescription = "<html><center>MISTY1 - Блочный алгоритм шифрования, <br>" +
            "созданный на основе «вложенных» сетей Фейстеля в 1995 году<br>" +
            "криптологом Мицуру Мацуи совместно с группой специалистов <br>" +
            "для компании Mitsubishi Electric. MISTY - это аббревиатура <br>" +
            "Mitsubishi Improved Security Technology, а также инициалы <br>" +
            "создателей алгоритма: в разработке алгоритма также приняли <br>" +
            "участие Тэцуя Итикава, Дзюн Соримати, Тосио Токита и Ацухиро <br>" +
            "Ямагиси. Алгоритм был разработан в 1995 году, однако прошел <br>" +
            "лицензирование и был опубликован уже в 1996 году.</center></html>";
    static String aboutProgramm = "<html><center>" +
            "Программная реализация алгоритма MISTY1<br><br>" +
            "выполнил студент группы БИСТ-19-1<br><br>" +
            "Соловаров Илья Станиславович<br><br>" +
            "ИТКН НИТУ МИСИС<br><br>" +
            "МОСКВА 2021" +
            "</center><html>";
    static String default_key = "00 11 22 33 44 55 66 77 88 99 aa bb cc dd ee ff";

    static String keyErrorMsg = "Некорректный ввод ключа! Введите 16 фрагментов по два 16-ричных числа, разделенных пробелами.";

    static String Encrypt_Successful = "Зашифровка прошла успешно";

    static String Decrypt_Successful = "Расшифровка прошла успешно";

}
