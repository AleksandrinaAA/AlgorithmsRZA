public class BlockRNM {   //Класс реализации блокирующего РНМ
    public ActWithComplex S;            //мощность по току и напряжению НП
    double S_angle = 0;
    double Inom = 0.322;   //значение номинального тока, протекающего в линии


    public double[] AreaBlock(Values values){
        //построение линии максимальной чувствительности по углу МЧ относительно вектора напряжения НП
        //угол максимальной чувствительности = 80 град.
        double LMCh = values.getU0().phase()-80;
        double Limit1 = LMCh - 85;
        double Limit2 = values.getU0().phase() +5;
        //Уставки по углам, формирующие область попадания внутреннего КЗ в область защиты
        return new double[] {Limit1,Limit2};
    }
    //Формирование области, где срабатывает блокирующее РНМ
    public double BLOCK_RNM(Values values){
        double trip = 0;
        //задание границ по блокирующему ОНМ
        double[] Limit = AreaBlock(values);
        Charts2.addAnalogData(10,0,Limit[0]);
        Charts2.addAnalogData(10,1,Limit[1]);
        //мощность рассчитывается путем произведения комплексных утроенных токов и напряжений НП
        //S = values.getU0().multiply(values.getI0());
        //угол по мощности
        S_angle = values.getU0().phase()-values.getI0().phase();
        //реализация дополнительных условий для работы с полярной плоскостью
        if ( S_angle>180){
            S_angle = S_angle-360;
        }
        //проверка на выполнение условий блокировки работы защиты органом ОНМ
        if (S_angle>Limit[0] & S_angle<Limit[1] & values.getI0().abs()>= 0.5*Inom){
            trip=1;
        }
        Charts2.addAnalogData(7,0,trip);
        return trip;
    }
}
