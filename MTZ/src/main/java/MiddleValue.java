public class MiddleValue implements Filter{
    private double buffer[] = new double[20];
    private double sum=0;
    private int counter=0;
    private SampleValues sv;
    private RMSValues rms;
    private double Ak=0;
    private double Bk=0;

    private double X=0;
    private double Cc;
    private double Ss;


    public SampleValues getSv() {
        return sv;
    }

    public void setSv(SampleValues sv) {
        this.sv = sv;
    }

    public RMSValues getRms() {
        return rms;
    }

    public void setRms(RMSValues rms) {
        this.rms = rms;
    }

    public void calculate() {
        Cc=Math.cos(0.1*counter*Math.PI);
        System.out.println(Cc);
        Ss=Math.sin(0.1*counter*Math.PI);

        sum=sv.getPhB()- buffer[counter];
        Ak+=Cc*sum;
        Bk+=Ss*sum;
        buffer[counter]=sv.getPhB();
        X = Math.abs(Math.sqrt((Math.pow(Ak*0.1, 2) + Math.pow(Bk*0.1, 2))/2));
        rms.setPhB(X);
        if (++counter>=20) counter=0;
        rms.setTime(sv.getTime());

    }
}
