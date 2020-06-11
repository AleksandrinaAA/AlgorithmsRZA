import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class inputData {   //поставляет мгновенные значения синусоид
    private File comtrCfg,comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;
    private double k1[], k2[];
    ArrayList<Double> F = new ArrayList<>();

    private String comtrName = "KzBC";
//private String comtrName = "KzA";
//private String comtrName = "Vkl";
    private String path = "C:\\Users\\Александрина\\Desktop\\Магистратура\\2 семестр\\Алгоритмы\\2 Лабораторная\\ОпытыComtrade\\DPB\\4 sections\\";
    private String cfgName = path+comtrName+".cfg";
    private String datName= path+comtrName+".dat";

    public inputData() {
        comtrCfg= new File(cfgName);
        comtrDat=new File(datName);
    }
    public void start() throws IOException {
        MiddleValue filter1=new MiddleValue();    //формирование объектов класса MiddleValue()
        MiddleValue filter2=new MiddleValue();    //для каждого присоединения, пофазно
        MiddleValue filter3=new MiddleValue();
        MiddleValue filter4=new MiddleValue();
        MiddleValue filter5=new MiddleValue();
        MiddleValue filter6=new MiddleValue();
        MiddleValue filter7=new MiddleValue();
        MiddleValue filter8=new MiddleValue();
        MiddleValue filter9=new MiddleValue();
        MiddleValue filter10=new MiddleValue();
        MiddleValue filter11=new MiddleValue();
        MiddleValue filter12=new MiddleValue();

        Logic logic=new Logic();              //формирование объекта класса Logic()

        br= new BufferedReader(new FileReader(comtrCfg));
        int lineNumber =0, count=0, numberData=100;
            while ((line = br.readLine()) !=null){   //работа с файлами
                lineNumber++;
                if (lineNumber==2) {
                    numberData=Integer.parseInt(line.split(",")[1].replaceAll("A",""));  //выделение чистла данных
                    k1= new double[numberData];
                    k2= new double[numberData];
                    System.out.println("Number Data: "+numberData);
                }
                if (lineNumber>2 && lineNumber<numberData+3) {
                    String spl[] = line.split(",");

                    k1[count]=Double.parseDouble(line.split(",")[5]);  //определение значний к-нтов из файла
                    k2[count]=Double.parseDouble(line.split(",")[6]);
                    count++;
                }
            }

            count=0;
            br= new BufferedReader((new FileReader(comtrDat)));
            while ((line = br.readLine()) !=null){
                count++;
                lineData=line.split(",");

                //ниже прописан код для получений действующих значений токов на каждом присоединении,
                //их ортогональных составляющих, и составляющих по 5 гармонике

                double[] f1= filter1.calculate(Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                ActWithComplex Ia1= new ActWithComplex(f1[1],f1[2]);
                double[] ort1= filter1.garmonicCalc(Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                ActWithComplex Ia1g= new ActWithComplex(ort1[0],ort1[1]);
                Charts.addAnalogData(0,0,Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                Charts.addAnalogData(0,1,f1[0]);
//                Charts.addAnalogData(0,0,Ia1.abs());
//                Charts.addAnalogData(0,1,Ia1g.abs());

                double[] f2= filter2.calculate(Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                ActWithComplex Ib1= new ActWithComplex(f2[1],f2[2]);
                double[] ort2= filter2.garmonicCalc(Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                ActWithComplex Ib1g= new ActWithComplex(ort2[0],ort2[1]);
                Charts.addAnalogData(1,0,Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                Charts.addAnalogData(1,1,f2[0]);
//                Charts.addAnalogData(1,0,Ib1.abs());
//                Charts.addAnalogData(1,1,Ib1g.abs());

                double[] f3= filter3.calculate(Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                ActWithComplex Ic1= new ActWithComplex(f3[1],f3[2]);
                double[] ort3= filter3.garmonicCalc(Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                ActWithComplex Ic1g= new ActWithComplex(ort3[0],ort3[1]);
                Charts.addAnalogData(2,0,Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                Charts.addAnalogData(2,1,f3[0]);
//                Charts.addAnalogData(2,0,Ic1.abs());
//                Charts.addAnalogData(2,1,Ic1g.abs());

                double[] f4= filter4.calculate(Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                ActWithComplex Ia2= new ActWithComplex(f4[1],f4[2]);
                double[] ort4= filter4.garmonicCalc(Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                ActWithComplex Ia2g= new ActWithComplex(ort4[0],ort4[1]);
                Charts.addAnalogData(3,0,Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                Charts.addAnalogData(3,1,f4[0]);

                double[] f5= filter5.calculate(Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                ActWithComplex Ib2= new ActWithComplex(f5[1],f5[2]);
                double[] ort5= filter5.garmonicCalc(Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                ActWithComplex Ib2g= new ActWithComplex(ort5[0],ort5[1]);
                Charts.addAnalogData(4,0,Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                Charts.addAnalogData(4,1,f5[0]);

                double[] f6= filter6.calculate(Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                ActWithComplex Ic2= new ActWithComplex(f6[1],f6[2]);
                double[] ort6= filter6.garmonicCalc(Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                ActWithComplex Ic2g= new ActWithComplex(ort6[0],ort6[1]);
                Charts.addAnalogData(5,0,Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                Charts.addAnalogData(5,1,f6[0]);

                double[] f7= filter7.calculate(Double.parseDouble(lineData[8])*k1[6]+k2[6]);
                ActWithComplex Ia3= new ActWithComplex(f7[1],f7[2]);
                double[] ort7= filter7.garmonicCalc(Double.parseDouble(lineData[8])*k1[6]+k2[6]);
                ActWithComplex Ia3g= new ActWithComplex(ort7[0],ort7[1]);
                Charts.addAnalogData(6,0,Double.parseDouble(lineData[8])*k1[6]+k2[6]);
                Charts.addAnalogData(6,1,f7[0]);

                double[] f8= filter8.calculate(Double.parseDouble(lineData[9])*k1[7]+k2[7]);
                ActWithComplex Ib3= new ActWithComplex(f8[1],f8[2]);
                double[] ort8= filter8.garmonicCalc(Double.parseDouble(lineData[9])*k1[7]+k2[7]);
                ActWithComplex Ib3g= new ActWithComplex(ort8[0],ort8[1]);
                Charts.addAnalogData(7,0,Double.parseDouble(lineData[9])*k1[7]+k2[7]);
                Charts.addAnalogData(7,1,f8[0]);

                double[] f9= filter9.calculate(Double.parseDouble(lineData[10])*k1[8]+k2[8]);
                ActWithComplex Ic3= new ActWithComplex(f9[1],f9[2]);
                double[] ort9= filter9.garmonicCalc(Double.parseDouble(lineData[10])*k1[8]+k2[8]);
                ActWithComplex Ic3g= new ActWithComplex(ort9[0],ort9[1]);
                Charts.addAnalogData(8,0,Double.parseDouble(lineData[10])*k1[8]+k2[8]);
                Charts.addAnalogData(8,1,f9[0]);

                double[] f10= filter10.calculate(Double.parseDouble(lineData[11])*k1[9]+k2[9]);
                ActWithComplex Ia4= new ActWithComplex(f10[1],f10[2]);
                double[] ort10= filter10.garmonicCalc(Double.parseDouble(lineData[11])*k1[9]+k2[9]);
                ActWithComplex Ia4g= new ActWithComplex(ort10[0],ort10[1]);
                Charts.addAnalogData(9,0,Double.parseDouble(lineData[11])*k1[9]+k2[9]);
                Charts.addAnalogData(9,1,f10[0]);

                double[] f11= filter11.calculate(Double.parseDouble(lineData[12])*k1[10]+k2[10]);
                ActWithComplex Ib4= new ActWithComplex(f11[1],f11[2]);
                double[] ort11= filter11.garmonicCalc(Double.parseDouble(lineData[12])*k1[10]+k2[10]);
                ActWithComplex Ib4g= new ActWithComplex(ort11[0],ort11[1]);
                Charts.addAnalogData(10,0,Double.parseDouble(lineData[12])*k1[10]+k2[10]);
                Charts.addAnalogData(10,1,f11[0]);

                double[] f12= filter12.calculate(Double.parseDouble(lineData[13])*k1[11]+k2[11]);
                ActWithComplex Ic4= new ActWithComplex(f12[1],f12[2]);
                double[] ort12= filter12.garmonicCalc(Double.parseDouble(lineData[13])*k1[11]+k2[11]);
                ActWithComplex Ic4g= new ActWithComplex(ort12[0],ort12[1]);
                Charts.addAnalogData(11,0,Double.parseDouble(lineData[13])*k1[11]+k2[11]);
                Charts.addAnalogData(11,1,f12[0]);

                //получение значения тока наиболее нагруженного присоединения в нормальном режиме
                if (count==500){
                    F.add(f1[0]); F.add(f2[0]); F.add(f3[0]); F.add(f4[0]);
                    F.add(f5[0]); F.add(f6[0]); F.add(f7[0]); F.add(f8[0]);
                    F.add(f9[0]); F.add(f10[0]); F.add(f11[0]); F.add(f12[0]);
                    double I=Collections.max(F);
                    System.out.println(I);
                    System.out.println(F);
                }
                //в методе Process() класса Logic() осуществляется сравнение величин с уставками
                boolean Log= logic.Process(Ia1, Ia1g, Ib1, Ib1g, Ic1, Ic1g, Ia2, Ia2g, Ib2, Ib2g, Ic2, Ic2g, Ia3, Ia3g,
                        Ib3, Ib3g, Ic3, Ic3g, Ia4, Ia4g, Ib4, Ib4g, Ic4, Ic4g);
            }
        }
}
