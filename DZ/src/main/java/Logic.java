public class Logic {
    private double Rust1=17.84;   //значения уставок
    private double Rust2=17.84;
    private double Rust3=17.84;
    int TRIP=0;
    int TRIP2=0;
    //Координатные точки, характеризующие прямые, ормирующие характеристику срабатывания в виде многоугольника
    //для каждой ступени
    private double K1_trip3=-3.73;
    private double K2_trip3=4.01;
    private double K3_trip3=-0.09;
    private double K4_trip3=-0.09;
    private double B1_trip3=-8.32;
    private double B2_trip3=-71.54;
    private double B3_trip3=-24.63;
    private double B4_trip3=246.3;

    private double K1_trip2=K1_trip3;
    private double K2_trip2=K2_trip3;
    private double K3_trip2=K3_trip3;
    private double K4_trip2=K4_trip3;
    private double B1_trip2=B1_trip3;
    private double B2_trip2=B2_trip3;
    private double B3_trip2=B3_trip3;
    private double B4_trip2=B4_trip3/2.47;

    private double K1_trip1=K1_trip3;
    private double K2_trip1=K2_trip3;
    private double K3_trip1=K3_trip3;
    private double K4_trip1=K4_trip3;
    private double B1_trip1=B1_trip3;
    private double B2_trip1=B2_trip3;
    private double B3_trip1=B3_trip3;
    private double B4_trip1=B4_trip3/3.2;
    private int counterI=0;
    private int counterII=0;
    private int counterIII=0;
    int TripI=0;    //логические органы, выявляющие срабатывание одной из ступеней
    int TripII=0;
    int TripIII=0;

    public ProtectionBlock block = new ProtectionBlock();

    //в данном методе прописана логика срабатывания ПО защиты, когда все условия выполнены
    // срабатывает ИО ДЗ и отсутствует блокировка защиты по токам обратной последовательности

    public int Process(ActWithComplex Zab, ActWithComplex Zbc, ActWithComplex Zca, double Iav) {

        Charts2.addAnalogData(0,0,Zab.abs());
        Charts2.addAnalogData(1,0,Zbc.abs());
        Charts2.addAnalogData(2,0,Zca.abs());
//        Charts2.addAnalogData(6,0,Zab.phase());
//        Charts2.addAnalogData(7,0,Zbc.phase());
//        Charts2.addAnalogData(8,0,Zca.phase());

        //Проверка на срабатывание одной из ступеней по междуфазным сопротивлениям и отсутствие блокировки защиты по токам
        // обратной последовательности
        TRIP= (Trip1(Zab,Zbc,Zca)+Trip2(Zab,Zbc,Zca)+Trip3(Zab,Zbc,Zca))*block.Trip(Iav);
        if (TRIP != 0) {
            TRIP2=1;
        }
        Charts2.addAnalogData(4,0,TRIP2);
        return TRIP2;
    }


    //Ниже прописаны условия попадания точки в зону срабатывания многоугольной характеристики по трем ступеням
    public int Trip1(ActWithComplex Zab,ActWithComplex Zbc,ActWithComplex Zca){
        int trip=0;
        if (Zab.im()>=K1_trip1*Rust1+B1_trip1 && Zab.im()>=K2_trip1*Rust1+B2_trip1 &&
                Zab.im()>=K3_trip1*Rust1+B3_trip1 && Zab.im()<=K4_trip1*Rust1+B4_trip1){
            trip=1;
        }
        if (Zbc.im()>=K1_trip1*Rust1+B1_trip1 && Zbc.im()>=K2_trip1*Rust1+B2_trip1 &&
                Zbc.im()>=K3_trip1*Rust1+B3_trip1 && Zbc.im()<=K4_trip1*Rust1+B4_trip1){
            trip=1;
        }
        if (Zca.im()>=K1_trip1*Rust1+B1_trip1 && Zca.im()>=K2_trip1*Rust1+B2_trip1 &&
                Zca.im()>=K3_trip1*Rust1+B3_trip1 && Zca.im()<=K4_trip1*Rust1+B4_trip1){
            trip=1;
        }

        if(trip==1 ){
            counterI++;
            if (counterI>=79) {
                TripI = 1;
            }
        }else {
            TripI = 0;
            counterI=0;
        }
        Charts2.addAnalogData(6,0,TripI);
        return  TripI;
    }

    public int Trip2(ActWithComplex Zab,ActWithComplex Zbc,ActWithComplex Zca){
        int trip2ab=0;
        int trip2bc=0;
        int trip2ca=0;
        if (Zab.im()>=K1_trip2*Rust2+B1_trip2 && Zab.im()>=K2_trip2*Rust2+B2_trip2 &&
                Zab.im()>=K3_trip2*Rust2+B3_trip2 && Zab.im()<=K4_trip2*Rust2+B4_trip2){
            trip2ab=1;
        }
        if (Zbc.im()>=K1_trip2*Rust2+B1_trip2 && Zbc.im()>=K2_trip2*Rust2+B2_trip2 &&
                Zbc.im()>=K3_trip2*Rust2+B3_trip2 && Zbc.im()<=K4_trip2*Rust2+B4_trip2){
            trip2bc=1;
        }
        if (Zca.im()>=K1_trip2*Rust2+B1_trip2 && Zca.im()>=K2_trip2*Rust2+B2_trip2 &&
                Zca.im()>=K3_trip2*Rust2+B3_trip2 && Zca.im()<=K4_trip2*Rust2+B4_trip2){
            trip2ca=1;
        }
        if(trip2ab==1 || trip2bc==1 ||trip2ca==1 ){
            counterII++;
            if (counterII>=200) {
                TripII = 1;
            }
        }else {
            TripII = 0;
            counterII=0;
        }
        Charts2.addAnalogData(7,0,TripII);
        return  TripII;
    }

    public int Trip3(ActWithComplex Zab,ActWithComplex Zbc,ActWithComplex Zca){
        int trip3=0;
        if (Zab.im()>=K1_trip3*Rust3+B1_trip3 && Zab.im()>=K2_trip3*Rust3+B2_trip3 &&
                Zab.im()>=K3_trip3*Rust3+B3_trip3 && Zab.im()<=K4_trip3*Rust3+B4_trip3){
            trip3=1;
        }
        if (Zbc.im()>=K1_trip3*Rust3+B1_trip3 && Zbc.im()>=K2_trip3*Rust3+B2_trip3 &&
                Zbc.im()>=K3_trip3*Rust3+B3_trip3 && Zbc.im()<=K4_trip3*Rust3+B4_trip3){
            trip3=1;
        }
        if (Zca.im()>=K1_trip3*Rust3+B1_trip3 && Zca.im()>=K2_trip3*Rust3+B2_trip3 &&
                Zca.im()>=K3_trip3*Rust3+B3_trip3 && Zca.im()<=K4_trip3*Rust3+B4_trip3) {
            trip3 = 1;
        }
        if(trip3==1){
            counterIII++;
            if (counterIII>=500) {
                TripIII = 1;
            }
        }else {
            TripIII = 0;
            counterIII=0;
        }
        Charts2.addAnalogData(8,0,TripIII);
        return  TripIII;
    }
}
