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

        Charts2.createAnalogChart("Zab", 0);    //Вывод сопротивления
        Charts2.addSeries("Zab", 0, 0);
        Charts2.createAnalogChart("Zbc", 1);    //Вывод сопротивления
        Charts2.addSeries("Zbc", 1, 0);
        Charts2.createAnalogChart("Zca", 2);    //Вывод сопротивления
        Charts2.addSeries("Zca", 2, 0);
        Charts2.createAnalogChart("EmergencyComponent", 3);  //Аварийная составляющая
        Charts2.addSeries("Iav", 3, 0);
        Charts2.createAnalogChart("TRIP", 4);       //вывод сигналов срабатывания/несрабат защиты
        Charts2.addSeries("TRIP", 4, 0);
        Charts2.createAnalogChart("ProtectionBlock", 5);
        Charts2.addSeries("ProtectionBlock", 5, 0);
        Charts2.createAnalogChart("TripI", 6);
        Charts2.addSeries("TripI", 6, 0);
        Charts2.createAnalogChart("TripII", 7);
        Charts2.addSeries("TripII", 7, 0);
        Charts2.createAnalogChart("TripIII", 8);
        Charts2.addSeries("TripIII", 8, 0);

        ChartsXY.createAnalogChart("Godograf" , 0);
        ChartsXY.addSeries("Zab",0,0);
        ChartsXY.addSeries("Zbc",0,1);
        ChartsXY.addSeries("Zca",0,2);
        ChartsXY.addSeries("G",0,3);
        ChartsXY.addSeries("GII",0,4);
        ChartsXY.addSeries("GIII ",0,5);


        inputData inD=new inputData();   //создание объекта класса InputData, где происходит парсинг данных с файлов
        inD.start();       //вызов метода данного класса
    }
}


