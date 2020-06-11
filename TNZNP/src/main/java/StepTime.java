import java.util.ArrayList;

public class StepTime {
    //счетчик по каждой ступени, для фиксации устойчивого срабатывания
    private int counterI = 0;
    private int counterII = 0;
    private int counterIII = 0;
    private int counterIV = 0;
    private int counterV = 0;

    public int[] Time(int i){    //метод реализует выдержку времени по каждой ступени
        int trip1 = 0;    //логическая переменная: 1 - сигнал на срабатывание ступени через выдержку времени
        int trip2 = 0;
        int trip3 = 0;
        int trip4 = 0;
        int trip5 = 0;
        switch (i) {
            case 1:    //первая ступень
                counterI++;
                if (counterI >= 79 * i) {
                    trip1 = 1;
                }
                break;
            case 2:    //вторая ступень
                counterII++;
                if (counterII >= 79 * i) {
                    trip2 = 1;
                }
                break;
            case 3:    //третья ступень
                counterIII++;
                if (counterIII >= 79 * i) {
                    trip3 = 1;
                }
                break;
            case 4:    //четвертая ступень
                counterIV++;
                if (counterIV >= 79 * i) {
                    trip4 = 1;
                }
                break;
            case 5:    //пятая ступень
                counterV++;
                if (counterV >= 79 * i) {
                    trip5 = 1;
                }
                break;
            default:
                break;
        }
        return new int[] {trip1,trip2,trip3,trip4,trip5};
    }
}
