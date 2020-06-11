import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {


        Charts.createAnalogChart("VoltageA",0);    //Вывод графиков токов и напряжений по ФАЗАМ
        Charts.addSeries("Ua",0,0);
        Charts.addSeries("Ua_FF",0,1);
        Charts.createAnalogChart("VoltageB",1);
        Charts.addSeries("Ub",1,0);
        Charts.addSeries("Ub_FF",1,1);
        Charts.createAnalogChart("VoltageC",2);
        Charts.addSeries("Uc",2,0);
        Charts.addSeries("Uc_FF",2,1);
        Charts.createAnalogChart("CurrentA",3);
        Charts.addSeries("Ia",3,0);
        Charts.addSeries("Ia_FF",3,1);
        Charts.createAnalogChart("CurrentB",4);
        Charts.addSeries("Ub",4,0);
        Charts.addSeries("Ub_FF",4,1);
        Charts.createAnalogChart("CurrentC",5);
        Charts.addSeries("Uc",5,0);
        Charts.addSeries("Uc_FF",5,1);
        Charts.createAnalogChart("3I0",6);
        Charts.addSeries("3I0",6,0);
        Charts.createAnalogChart("3U0",7);
        Charts.addSeries("3U0",7,0);

        Charts2.createAnalogChart("TripI", 0);    //Вывод сопротивления
        Charts2.addSeries("TripI", 0, 0);
        Charts2.createAnalogChart("TripII", 1);    //Вывод сопротивления
        Charts2.addSeries("TripII", 1, 0);
        Charts2.createAnalogChart("TripIII", 2);    //Вывод сопротивления
        Charts2.addSeries("TripIII", 2, 0);
        Charts2.createAnalogChart("TripIV", 3);  //Аварийная составляющая
        Charts2.addSeries("TripIV", 3, 0);
        Charts2.createAnalogChart("TripV", 4);       //вывод сигналов срабатывания/несрабат защиты
        Charts2.addSeries("TripV", 4, 0);
        Charts2.createAnalogChart("TRIP", 5);       //вывод сигналов срабатывания/несрабат защиты
        Charts2.addSeries("TRIP", 5, 0);
        Charts2.createAnalogChart("ProtectionPermission", 6);
        Charts2.addSeries("ProtectionPermission", 6, 0);
        Charts2.createAnalogChart("ProtectionBlock", 7);
        Charts2.addSeries("ProtectionBlock", 7, 0);
        Charts2.createAnalogChart("S_angle", 8);
        Charts2.addSeries("S_angle", 8, 0);
        Charts2.createAnalogChart("LimitAllow", 9);
        Charts2.addSeries("LimitAl1", 9, 0);
        Charts2.addSeries("LimitAl2", 9, 1);
        Charts2.createAnalogChart("LimitBlock", 10);
        Charts2.addSeries("LimitB1", 10, 0);
        Charts2.addSeries("LimitB2", 10, 1);


        inputData inD=new inputData();   //создание объекта класса InputData, где происходит парсинг данных с файлов
        inD.start();       //вызов метода данного класса
    }
}


