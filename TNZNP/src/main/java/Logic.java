public class Logic {
    private double I_trip1 = 2.25;   //задание уставок по каждой ступени
    private double I_trip2= 1.7;
    private double I_trip3 = 1.39;
    private double I_trip4 = 0.61;
    private double I_trip5 = 0.06;
    int TRIP=0;
    //Координатные точки, характеризующие прямые, ормирующие характеристику срабатывания в виде многоугольника
    //для каждой ступени
    public BlockRNM block = new BlockRNM();
    public AllowRNM allow = new AllowRNM();
    public StepTime time = new StepTime();
    //в данном методе прописана логика срабатывания ПО защиты, когда все условия выполнены
    // срабатывает ИО ДЗ и отсутствует блокировка защиты по токам обратной последовательности

    public int Process(Values values) {

        double Allow = allow.ALLOW_RNM(values);
        double Block = block.BLOCK_RNM(values);
        //Проверка на срабатывание одной из ступеней и отсутствие блокировки защиты по углу
        //и наличия разрешения на срабатывание для направленных ступеней
        TRIP= (StepI(values)+StepII(values)+StepIII(values, Allow, Block)+StepIV(values, Allow, Block)+StepV(values, Allow, Block));
        if (TRIP > 1) {
            TRIP=1;
        }
        Charts2.addAnalogData(5,0,TRIP);
        return TRIP;
    }

    //Ниже прописан алгоритм реализации пяти ступеней защиты.
    public int StepI(Values values){
        int TripI=0;
        //условие превышения уставки по току НП и разрешения органа ОНМ
        if (values.getI0().abs()>I_trip1){
            TripI=time.Time(1)[0];
        }
        Charts2.addAnalogData(0,0,TripI);
        return TripI;
    }

    public int StepII(Values values){
        int TripII=0;
        //условие превышения уставки по току НП и разрешения органа ОНМ
        if (values.getI0().abs()>I_trip2){
            TripII=time.Time(2)[1];
        }
        Charts2.addAnalogData(1,0,TripII);
        return TripII;
    }

    public int StepIII(Values values, double Allow, double Block){
        int TripIII=0;
        //условие превышения уставки по току НП и разрешения органа ОНМ и отсутствия запрета со стороны блокировки
        if ((values.getI0().abs()>I_trip3 & Block==0) | (values.getI0().abs()>I_trip3 & Allow==1)){
            TripIII=time.Time(3)[2];
        }
        Charts2.addAnalogData(2,0,TripIII);
        return TripIII;
    }

    public int StepIV(Values values, double Allow, double Block){
        int TripIV=0;
        //условие превышения уставки по току НП
        if ((values.getI0().abs()>I_trip4 & Block==0) | (values.getI0().abs()>I_trip4 & Allow==1)){
            TripIV=time.Time(4)[3];
        }
        Charts2.addAnalogData(3,0,TripIV);
        return TripIV;
    }

    public int StepV(Values values, double Allow, double Block){
        int TripV=0;
        //условие превышения уставки по току НП
        if ((values.getI0().abs()>I_trip5 & Block==0) | (values.getI0().abs()>I_trip5 & Allow==1)){
            TripV=time.Time(5)[4];
        }
        Charts2.addAnalogData(4,0,TripV);
        return TripV;
    }
    }

