public class Graf {

    public double[] Xx = {-69.95,-1.43,16.69,77.52,-69.95,77.52};   //задаем координаты по Х для построения прямых
    public double[] XxII = {-29.68,-1.43,16.69,41.77,-29.68,41.77};   //задаем координаты по Х для построения прямых
    public double[] XxIII = {-23.43,-1.43,16.69,36.95,-23.43,36.95};   //задаем координаты по Х для построения прямых

    public void GrafDraw(){
        for (int i=0; i<Xx.length-1;i++){
            for (double x=Xx[i]; x<=Xx[i+1]; x=x+0.1) {
                double y=0;
                switch (i){       //каждой прямой характеристики соответствует своя функция y(x)
                    case (0):
                        y = -3.73 * x - 8.32;
                        break;
                    case (1):
                        y = -0.09 * x - 3.1;
                        break;
                    case (2):
                        y = 4.01 * x - 71.54;
                        break;
                    case (4):
                        y = -0.09 * x + 246.3;
                        break;
                    default:
                        break;
                }
                ChartsXY.addAnalogData(0, 3, x, y);  //вывод графика
            }
        }
        for (int i=0; i<XxII.length-1;i++){
            for (double xII=XxII[i]; xII<=XxII[i+1]; xII=xII+0.1) {
                double yII=0;
                switch (i){       //каждой прямой характеристики соответствует своя функция y(x)
                    case (0):
                        yII = -3.73 * xII - 8.32;
                        break;
                    case (1):
                        yII = -0.09 * xII - 3.1;
                        break;
                    case (2):
                        yII = 4.01 * xII - 71.54;
                        break;
                    case (4):
                        yII = -0.09 * xII + 99.72;
                        break;
                    default:
                        break;
                }
                ChartsXY.addAnalogData(0, 4, xII, yII);  //вывод графика
            }
        }
        for (int i=0; i<XxIII.length-1;i++){
            for (double xII=XxIII[i]; xII<=XxIII[i+1]; xII=xII+0.1) {
                double yII=0;
                switch (i){       //каждой прямой характеристики соответствует своя функция y(x)
                    case (0):
                        yII = -3.73 * xII - 8.32;
                        break;
                    case (1):
                        yII = -0.09 * xII - 3.1;
                        break;
                    case (2):
                        yII = 4.01 * xII - 71.54;
                        break;
                    case (4):
                        yII = -0.09 * xII + 76.97;
                        break;
                    default:
                        break;
                }
                ChartsXY.addAnalogData(0, 5, xII, yII);  //вывод графика
            }
        }
    }
}
