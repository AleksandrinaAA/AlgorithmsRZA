import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {
        Charts.createAnalogChart("Фаза B",0);
        Charts.addSeries("Фаза B (мгновенные значения)",0,0);
        Charts.addSeries("Фаза B (отфильтрованные значения)",0,1);
        Charts.addSeries("Уставка",0,2);

        Charts.createDiscreteChart("Срабатывание/несрабатывание", 0);
        inputData inD=new inputData();
        inD.start();
    }
}


