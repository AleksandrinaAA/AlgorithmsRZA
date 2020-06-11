public class Logic {

    private double Kt=0.23;   //коэффициент торможения
    private double It0=1000/1000; //ток начала торможения
    private double Idiff0=1080/1000;   //начальный дифф ток
    private double Blust=0.15;   //уставка по блокировке по гармонике
    boolean TRIP;

    //в данном методе прописана логика срабатывания ПО защиты, когда все условия выполнены
    // срабатывает ИО ДЗТ и отсутствует блокровка защиты по 5 гармонике

    public boolean Process(ActWithComplex ia1, ActWithComplex ia1g, ActWithComplex ib1, ActWithComplex ib1g,
                        ActWithComplex ic1, ActWithComplex ic1g, ActWithComplex ia2, ActWithComplex ia2g,
                        ActWithComplex ib2, ActWithComplex ib2g, ActWithComplex ic2, ActWithComplex ic2g,
                        ActWithComplex ia3, ActWithComplex ia3g, ActWithComplex ib3, ActWithComplex ib3g,
                        ActWithComplex ic3, ActWithComplex ic3g, ActWithComplex ia4, ActWithComplex ia4g,
                        ActWithComplex ib4, ActWithComplex ib4g, ActWithComplex ic4, ActWithComplex ic4g) {

        double IdiffA = Math.abs((((ia1.plus(ia2)).plus(ia3)).plus(ia4)).abs()); //формирование дифференциального тока
        double IdiffB = Math.abs((((ib1.plus(ib2)).plus(ib3)).plus(ib4)).abs());
        double IdiffC = Math.abs((((ic1.plus(ic2)).plus(ic3)).plus(ic4)).abs());
        boolean trip2 = DZT(ia1, ib1, ic1, ia2, ib2, ic2, ia3, ib3, ic3, ia4, ib4, ic4, IdiffA, IdiffB, IdiffC);
        int trip3 = ProtectionBlock(ia1, ib1, ic1, ia2, ib2, ic2, ia3, ib3, ic3, ia4, ib4, ic4, ia1g, ib1g, ic1g,
                ia2g, ib2g, ic2g, ia3g, ib3g, ic3g, ia4g, ib4g, ic4g);
            if (trip2 & trip3==0) {   //проверка на выполнение условий срабатывания
                TRIP = true;
            }
            else {
                TRIP=false;
            }
        Charts2.addDiscreteData(0, TRIP);
        return TRIP;
    }

    public boolean DZT(ActWithComplex ia1, ActWithComplex ib1, ActWithComplex ic1, ActWithComplex ia2,
                   ActWithComplex ib2, ActWithComplex ic2, ActWithComplex ia3, ActWithComplex ib3,
                   ActWithComplex ic3, ActWithComplex ia4, ActWithComplex ib4, ActWithComplex ic4,
                   double IdiffA, double IdiffB, double IdiffC){
        boolean trip=false;
        double ItormA=(ia1.abs()+ia2.abs()+ia3.abs()+ia4.abs())/2;   //формирование тормозных токов
        double ItormB=(ib1.abs()+ib2.abs()+ib3.abs()+ib4.abs())/2;
        double ItormC=(ic1.abs()+ic2.abs()+ic3.abs()+ic4.abs())/2;

        Charts2.addAnalogData(0, 0, IdiffA);
        Charts2.addAnalogData(1, 0, IdiffB);
        Charts2.addAnalogData(2, 0, IdiffC);
        Charts2.addAnalogData(0, 1, ItormA);
        Charts2.addAnalogData(1, 1, ItormB);
        Charts2.addAnalogData(2, 1, ItormC);

        if (((IdiffA>=Kt*(ItormA-It0)+Idiff0) & (IdiffA>=Idiff0)) | ((IdiffB>=Kt*(ItormB-It0)+Idiff0) & (IdiffB>=Idiff0))
            | ((IdiffC>=Kt*(ItormC-It0)+Idiff0)& (IdiffC>=Idiff0))){  //условие попадания в область срабатывания защиты
            trip=true;
        }
        Charts2.addDiscreteData(1,trip);
        return  trip;
    }
    //проверка на необходимость блокировки защиты
    public int ProtectionBlock(ActWithComplex ia1, ActWithComplex ib1, ActWithComplex ic1, ActWithComplex ia2,
                                   ActWithComplex ib2, ActWithComplex ic2, ActWithComplex ia3, ActWithComplex ib3,
                                   ActWithComplex ic3, ActWithComplex ia4, ActWithComplex ib4, ActWithComplex ic4,
                                   ActWithComplex ia1g, ActWithComplex ib1g, ActWithComplex ic1g, ActWithComplex ia2g,
                                   ActWithComplex ib2g, ActWithComplex ic2g, ActWithComplex ia3g, ActWithComplex ib3g,
                                   ActWithComplex ic3g, ActWithComplex ia4g, ActWithComplex ib4g, ActWithComplex ic4g){
        int trip;
        if ((ia1g.abs()/ia1.abs()>Blust) & (ia2g.abs()/ia2.abs()>Blust) & (ia3g.abs()/ia3.abs()>Blust) & (ia4g.abs()/ia4.abs()>Blust) &
                (ib1g.abs()/ib1.abs()>Blust) & (ib2g.abs()/ib2.abs()>Blust) & (ib3g.abs()/ib3.abs()>Blust) &
                (ib4g.abs()/ib4.abs()>Blust) & (ic1g.abs()/ic1.abs()>Blust) & (ic2g.abs()/ic2.abs()>Blust) &
                (ic3g.abs()/ic3.abs()>Blust) & (ic4g.abs()/ic4.abs()>Blust) ){
            trip=1;
        }
        else {
            trip=0;
        }
        Charts2.addAnalogData(3,0,trip);
        return trip;
    }

//    public boolean DTO (double IdiffA,double IdiffB,double IdiffC) {
//        boolean trip=false;
//        if ( (IdiffA > IustDTO) | (IdiffB > IustDTO) | (IdiffC > IustDTO)) {
//            trip=true;
//        }
//        Charts2.addDiscreteData(1,trip);
//        return trip;
//    }

}
