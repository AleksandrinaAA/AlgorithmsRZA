public class Logic {

    private double tripPoint=3.5;
    private RMSValues rms;
    private OutputData od=new OutputData();

    public void process(){
        if (rms.getPhB()> tripPoint) od.trip(true); else  od.trip(false);
        Charts.addAnalogData(0,2,tripPoint);

    }
    public RMSValues getRms() {
        return rms;
    }

    public void setRms(RMSValues rms) {
        this.rms = rms;
    }



}
