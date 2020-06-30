package problems.TelegramFee;

public class TelegramFee {
    public double calculate(int minute,int month){
        double discount=0;
        if(isLegalInput(minute,month)){
            if(minute<=60&&month<=1){
                discount=0.01;
            }
            else if(60<minute&&minute<=120&&month<=2){
                discount=0.015;
            }
            else if(120<minute&&minute<=180&&month<=3){
                discount=0.02;
            }
            else if(180<minute&&minute<300&&month<=3){
                discount=0.025;
            }
            else if(minute>300&&month<=6){
                discount=0.03;
            }
            else {
                discount=0;
            }
            return 25+minute*0.15*(1-discount);
        }
        else return -1;

    }
    //判断输入是否合法
    public boolean isLegalInput(int minute,int month){
        return minute>=0&&minute<=44640&&month>=0&&month<12;
    }

}

