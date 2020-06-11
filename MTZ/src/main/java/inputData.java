import java.io.*;

public class inputData {   //поставляет мгновенные значения синусоид
    private File comtrCfg,comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;
    private double k1[], k2[];

    private String comtrName = "PhBC20";
    private String path = "C:\\Users\\Александрина\\Desktop\\Магистратура\\2 семестр\\Алгоритмы\\1 Лабораторная\\Опыты\\Начало линии\\";
    private String cfgName = path+comtrName+".cfg";
    private String datName= path+comtrName+".dat";
    private SampleValues sv =new SampleValues();
    private RMSValues rms= new RMSValues();
    private Filter filter=new MiddleValue();
    private Logic logic =new Logic();

    public inputData() {
        comtrCfg= new File(cfgName);
        comtrDat=new File(datName);
    }

    public void start() throws IOException {
        filter.setSv(sv);
        filter.setRms(rms);
        logic.setRms(rms);
        br= new BufferedReader(new FileReader(comtrCfg));
        int lineNumber =0, count=0, numberData=100;
            while ((line = br.readLine()) !=null){
                lineNumber++;
                if (lineNumber==2) {
                    numberData=Integer.parseInt(line.split(",")[1].replaceAll("A",""));  //выделение чистла данных
                    k1= new double[numberData];
                    k2= new double[numberData];
                    System.out.println("Number Data: "+numberData);
                }
                if (lineNumber>2 && lineNumber<numberData+3) {
                    String spl[] = line.split(",");

                    k1[count]=Double.parseDouble(line.split(",")[5]);
                    k2[count]=Double.parseDouble(line.split(",")[6]);
                    count++;
                }
            }
            count=0;
            br= new BufferedReader((new FileReader(comtrDat)));
            while ((line = br.readLine()) !=null){
                count++;
                if(!(count > 0 && count < 2000)) continue;
                lineData=line.split(",");
                sv.setPhA(Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                sv.setPhB(Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                sv.setPhC(Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                sv.setTime(Double.parseDouble(lineData[1]));

                filter.calculate();
                logic.process();

                Charts.addAnalogData(0,0,sv.getPhB());
                Charts.addAnalogData(0,1,rms.getPhB());
            }
        }
}
