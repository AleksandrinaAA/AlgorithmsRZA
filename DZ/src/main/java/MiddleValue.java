public class MiddleValue implements Filter{     //Класс реализации фильтра Фурье

    private double bufferPh[] = new double[80];
    private int counter=0;
    private double sin[]= new double[80];
    private double cos[]= new double[80];

    private double Ampl=0;     //Действующее значение на выходе ФФ
    private double Kf=0.025;   //Коэффициент Фурье
    private double X=0;        //Ортогональные составляющие
    private double Y=0;

    public MiddleValue() {   //расчет значений составляющих sin cos
        for (int i = 0; i < 80; i++){
            sin[i] = Math.sin(0.025*Math.PI*i);
            cos[i] = Math.cos(0.025*Math.PI*i);
        }
    }

    public double[] calculate(double Ph) {  //метод реализации ФФ для получений действующего значения тока и его
        X+=(Ph- bufferPh[counter])*cos[counter]*Kf;      //ортогональных составляющих
        Y+=(Ph- bufferPh[counter])*sin[counter]*Kf;
        bufferPh[counter]=Ph;
        Ampl = Math.abs(Math.sqrt((Math.pow(X, 2) + Math.pow(Y, 2))/2));
        if (++counter>=80) counter=0;
        return new double[] {Ampl,X,Y};

    }
}
