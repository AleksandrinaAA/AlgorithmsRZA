public class ZeroComponent {
    public ActWithComplex I0;
    public ActWithComplex U0;

    public void ZeroSequence(Values values){
        //расчет действующего значения утроенных токов и напряжений НП путем сложения фазных величин
        I0 = ((values.getIa().plus(values.getIb())).plus(values.getIc())).mult(Math.sqrt(2));
        U0 = (values.getUa().plus(values.getUb())).plus(values.getUc()).mult(Math.sqrt(2));
        values.setI0(I0);
        values.setU0(U0);

        Charts.addAnalogData(6,0,I0.abs());
        Charts.addAnalogData(7,0,U0.abs());
    }
}
