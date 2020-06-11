import java.util.ArrayList;

public class ProtectionBlock {     //Класс для реализации блокировки защиты по токам обратной последовательности
    private double bufferB[] = new double[80];
    private double bufferC[] = new double[80];
    private double NegSeq;
    private int counter=0;
    private int counterTime=0;
    private double BlockTrip = 1.5;   //Уставка блокировки по аварийным составляющим
    int trip = 0;
    ArrayList<Double> AV = new ArrayList<>();     //Лист токов обратной последовательсти размером, равным периоду

    public double NegativeSequence(Values values){   //Формирование токов обратной последовательности
        //Расчет токов ОП выполняется с момента прохождения одного периода
        if(counter==79) {
            for (int i = 1; i < 80; i++) {
                bufferB[i - 1] = bufferB[i];
                bufferC[i - 1] = bufferC[i];
            }
            bufferB[79] = values.getIb();
            bufferC[79] = values.getIc();
            //Расчет токов обратной последовательсти выполняется сложением токов всех фаз в моменты времени,
            // смещенные друг относительно друга на величины N/3 и 2N/3
            //Данное условие позволяет выделять токи обратной последовательсти
            NegSeq = 0.333 * (values.getIa() + bufferB[counter - 27] + bufferC[counter - 54]);
        }
        //До достижения одного периода выполняется заполнение массивов токов фаз В и С для дальнейшего к ним обращения
        else{
            bufferB[counter] = values.getIb();
            bufferC[counter] = values.getIc();
            counter++;
            NegSeq=0;
        }
        //Выделение аварийной составляющей токов обратной последовательности
        if (AV.size()==80){
            for (int i = 1; i < 80; i++) {
                AV.set(i-1, AV.get(i));
            }
            AV.set(79, NegSeq);
            return Math.abs(AV.get(79)-AV.get(0));
        }else{
            AV.add(NegSeq);
            return 0;
        }
    }

    public int Trip(double Iav) {   //Проверка уставки на отключение блокировки
        if (Iav > BlockTrip) {
            trip = 1;
            counterTime++;
        }
        else if(trip==1 && counterTime==4){   //Отключение блокировки происходит на время 1 сек
            trip = 0;
        } else{
            trip=0;
        }
        Charts2.addAnalogData(5,0,trip);
        return trip;
    }
}
