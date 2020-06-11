import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args) throws IOException {
        ArrayList<String> signalName = new ArrayList<>();   //формирование списка наименований сигналов токов
        for (int n = 1; n < 5; n++){
            for (char c = 'A'; c <= 'C'; c++) {
                signalName.add(c + String.valueOf(n));
            }
        }
        for (int i=0; i<12; i++) {       //вывод значений токов на присоединениях с фильтра Фурье
            Charts.createAnalogChart(signalName.get(i),i);
            Charts.addSeries(signalName.get(i),i,0);
            Charts.addSeries("FF",i,1);
        }
        Charts2.createAnalogChart("CurrentA", 0);    //вывод аналог сигналов дифференциальных и тормозных токов
        Charts2.addSeries("IdiffA", 0, 0);               //по фазам А В С
        Charts2.addSeries("ItormA", 0, 1);
        Charts2.createAnalogChart("CurrentB", 1);
        Charts2.addSeries("IdiffB", 1, 0);
        Charts2.addSeries("ItormB", 1, 1);
        Charts2.createAnalogChart("CurrentC", 2);
        Charts2.addSeries("IdiffC", 2, 0);
        Charts2.addSeries("ItormC", 2, 1);
        Charts2.createDiscreteChart("ProtectionAct", 0);  //вывод сигналов срабатывания/несрабат защиты
        Charts2.createDiscreteChart("DZT", 1);
        Charts2.createAnalogChart("ProtectionBlock",3);
        Charts2.addSeries("ProtectionBlock", 3, 0);
        inputData inD=new inputData();   //создание объекта класса InputData, где происходит парсинг данных с файлов
        inD.start();       //вызов метода данного класса
    }
}


