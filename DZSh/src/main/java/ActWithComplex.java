public class ActWithComplex {
    private double re;
    private double im;

    public ActWithComplex (double real, double imag){
        re=real;
        im=imag;
    }
    // return abs/modulus/magnitude
    public double abs() {
        return Math.sqrt(re*re+im*im);
    }

    // return a new Complex object whose value is (this + b)
    public ActWithComplex plus(ActWithComplex b) {
//        ActWithComplex a = this;             // invoking object
        double real = this.re + b.re;
        double imag = this.im + b.im;
        return new ActWithComplex(real, imag);
    }

}
