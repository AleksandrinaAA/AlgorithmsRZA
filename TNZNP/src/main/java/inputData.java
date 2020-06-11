import java.io.*;

public class inputData {   //Класс работы с comtrade файлами. Также свзяывает в себе работу всех классов
                           // для реализации правильного алгоритма работы ДЗ
    private File comtrCfg,comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;
    private double k1[], k2[];
    public Values values = new Values();

    private String comtrName = "KZ1";
    private String path = "D:\\Магистратура\\2 семестр\\Алгоритмы\\4 Лабораторная\\Опыты\\";
    private String cfgName = path+comtrName+".cfg";
    private String datName= path+comtrName+".dat";

    public inputData() {
        comtrCfg= new File(cfgName);
        comtrDat=new File(datName);
    }
    public void start() throws IOException {
        MiddleValue filter1=new MiddleValue();    //формирование объектов класса MiddleValue()
        MiddleValue filter2=new MiddleValue();    //для каждой величины тока и напряжения
        MiddleValue filter3=new MiddleValue();
        MiddleValue filter4=new MiddleValue();
        MiddleValue filter5=new MiddleValue();
        MiddleValue filter6=new MiddleValue();

        Logic logic=new Logic();              //формирование объекта класса Logic()
        ZeroComponent ZC = new ZeroComponent();   //формирование объекта класса ZeroComponent()

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

                //ниже прописан код для получений действующих значений токов и напряжений пофазно,
                //их ортогональных составляющих

                double[] f1_Ua= filter1.calculate(Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                ActWithComplex Ua= new ActWithComplex(f1_Ua[1],f1_Ua[2]);
                values.setUa(Ua);
                Charts.addAnalogData(0,0,Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                Charts.addAnalogData(0,1,f1_Ua[0]);

                double[] f2_Ub= filter2.calculate(Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                ActWithComplex Ub = new ActWithComplex(f2_Ub[1],f2_Ub[2]);
                values.setUb(Ub);
                Charts.addAnalogData(1,0,Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                Charts.addAnalogData(1,1,f2_Ub[0]);

                double[] f3_Uc = filter3.calculate(Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                ActWithComplex Uc = new ActWithComplex(f3_Uc[1],f3_Uc[2]);
                values.setUc(Uc);
                Charts.addAnalogData(2,0,Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                Charts.addAnalogData(2,1,f3_Uc[0]);

                double[] f4_Ia= filter4.calculate(Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                ActWithComplex Ia= new ActWithComplex(f4_Ia[1],f4_Ia[2]);
                values.setIa(Ia);
                Charts.addAnalogData(3,0,Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                Charts.addAnalogData(3,1,f4_Ia[0]);

                double[] f5_Ib= filter5.calculate(Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                ActWithComplex Ib= new ActWithComplex(f5_Ib[1],f5_Ib[2]);
                values.setIb(Ib);
                Charts.addAnalogData(4,0,Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                Charts.addAnalogData(4,1,f5_Ib[0]);

                double[] f6_Ic= filter6.calculate(Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                ActWithComplex Ic= new ActWithComplex(f6_Ic[1],f6_Ic[2]);
                values.setIc(Ic);
                Charts.addAnalogData(5,0,Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                Charts.addAnalogData(5,1,f6_Ic[0]);

                //В методе ZeroSequence() прописан алгоритм по расчету нулевой последовательности
                ZC.ZeroSequence(values);

                //В методе Process() класса Logic() осуществляется сравнение величин с уставками и
                // проверка выполнения/невыполнения условий на блокировку/разрешение работы защиты
                logic.Process(values);
            }
        }
}
