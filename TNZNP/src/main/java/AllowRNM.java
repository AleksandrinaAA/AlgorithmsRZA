public class AllowRNM {     //Класс реализации разрешающего РНМ
    public ActWithComplex S;        //мощность по току и напряжению НП
    double S_angle = 0;
    double Inom = 0.322;      //значение номинального тока, протекающего в линии

    public double[] AreaAllow(Values values){
        //построение линии максимальной чувствительности по углу МЧ относительно вектора напряжения НП
        //угол максимальной чувствительности = 80 град.
        double LMCh = values.getU0().phase()-80;
        double Limit1 = 360 + LMCh - 95;
        double Limit2 = values.getU0().phase() +15;
        //Уставки по углам, формирующие область попадания внутреннего КЗ в область защиты
        return new double[] {Limit1,Limit2};
    }
    //Формирование области, где срабатывает разрешающее РНМ
    public double ALLOW_RNM(Values values){
        double trip = 0;
        //задание границ по разрешающему ОНМ
        double[] Limit = AreaAllow(values);
        Charts2.addAnalogData(9,0,Limit[0]);
        Charts2.addAnalogData(9,1,Limit[1]);
        //мощность рассчитывается путем произведения комплексных утроенных токов и напряжений НП
        //S = values.getU0().multiply(values.getI0());
        //угол по мощности
        S_angle = values.getU0().phase()-values.getI0().phase();
        Charts2.addAnalogData(8,0,S_angle);
        //реализация дополнительных условий для работы с полярной плоскостью
        if ( S_angle <0){
            S_angle = 360 + S_angle;
        }

        //проверка на выполнение условий разрешения работы защиты органом ОНМ
        if (S_angle>Limit[1] & S_angle<Limit[0] & values.getI0().abs()>= 0.5*Inom){
            trip=1;
        }
        Charts2.addAnalogData(6,0,trip);
        return trip;
    }
}
