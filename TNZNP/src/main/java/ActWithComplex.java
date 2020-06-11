public class ActWithComplex {     //Класс работы с комплексными числами
    private double re;
    private double im;

    public ActWithComplex (double real, double imag){
        re=real;
        im=imag;
    }
    //Выделение модуля комплексного числа
    public double abs() {
        return Math.sqrt(re*re+im*im);
    }
    //Выделение угла комплексного числа
    public double phase() {
        return Math.atan(im/re)*180/Math.PI;
    }

    //Сумма комплексных чисел
    public ActWithComplex plus(ActWithComplex b) {
        double real = this.re + b.re;
        double imag = this.im + b.im;
        return new ActWithComplex(real, imag);
    }
    //Разность комплексных чисел
    public ActWithComplex minus(ActWithComplex b) {
        double real = this.re - b.re;
        double imag = this.im - b.im;
        return new ActWithComplex(real, imag);
    }
    //Деление комплексных чисел
    public ActWithComplex divides(ActWithComplex b) {
        double real = (this.re * b.re + this.im * b.im)/(b.re*b.re + b.im*b.im);
        double imag = (this.im * b.re - this.re * b.im)/(b.re*b.re + b.im*b.im);
        return new ActWithComplex(real, imag);
    }
    //Произведение двух комплексных чисел
    public ActWithComplex multiply (ActWithComplex b) {
        double real = (this.re * b.re + this.im * b.im);
        double imag = (this.im * b.re - this.re * b.im);
        return new ActWithComplex(real, imag);
    }
    //Деление комплексного числа на постоянное число
    public ActWithComplex mult(double b) {
        double real = this.re/b;
        double imag = this.im/b;
        return new ActWithComplex(real, imag);
    }
    //Выделение действительной и мнимой частей
    public double re() { return re; }
    public double im() { return im; }
}
