public class MiddleValue implements Filter{

    private double bufferPh[] = new double[20];
    private double bufferPh5[] = new double[20];
    private int counter=0;
    private int counter3=0;
    private double sin[]= new double[20];
    private double cos[]= new double[20];
    private double sin5[]= new double[20];
    private double cos5[]= new double[20];
    private double Ampl=0;
    private double Kf=0.1;
    private double X=0;
    private double Y=0;
    private double X5=0;
    private double Y5=0;

    public MiddleValue() {   //расчет значений составляющих sin cos
        for (int i = 0; i < 20; i++){
            sin[i] = Math.sin(0.1*Math.PI*i);
            cos[i] = Math.cos(0.1*Math.PI*i);
            sin5[i] = Math.sin(0.5*Math.PI*i);
            cos5[i] = Math.cos(0.5*Math.PI*i);
        }
    }

    public double[] calculate(double Ph) {  //метод реализации ФФ для получений действующего значения тока и его
        X+=(Ph- bufferPh[counter])*cos[counter]*Kf;      //ортогональных составляющих
        Y+=(Ph- bufferPh[counter])*sin[counter]*Kf;
        bufferPh[counter]=Ph;
        Ampl = Math.abs(Math.sqrt((Math.pow(X, 2) + Math.pow(Y, 2))/2));
        if (++counter>=20) counter=0;
        return new double[] {Ampl,X,Y};

    }

    public double[] garmonicCalc(double Ph) {     //выделение ортогональных составляющих по 5 гармонике
        X5+=(Ph- bufferPh5[counter3])*cos5[counter3]*Kf;
        Y5+=(Ph- bufferPh5[counter3])*sin5[counter3]*Kf;
        bufferPh5[counter3]=Ph;
        if (++counter3>=20) counter3=0;
        return new double[] {X5, Y5};
    }
}
