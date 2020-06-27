package problems.calendar;

public class NextDayBefore {

    public String getNextDay(int year,int month,int day){
        String nextDate;
        int nextDateYear=year;
        int nextDateMonth=month;
        int nextDateDay=day;
        //判断日期合法性
        if(!judgeDate(year,month,day)){
            nextDate="日期不符合规则";
            return nextDate;
        }
        //获取下一天日期
        if(day == getDayNum(year,month)){
            nextDateDay=1;
            if(month==12){
                nextDateMonth=1;
                nextDateYear++;
            }
            else {
                nextDateMonth++;
            }
        }
        else {
            nextDateDay++;
        }
        nextDate=nextDateYear+"年"+nextDateMonth+"月"+nextDateDay+"日";
        return nextDate;

    }

    //判断输入的日期是否合法
    //年范围1900-2100，月范围1-12，日范围1-31
    //判断是否存在该年月日
    public boolean judgeDate(int year,int month,int day){
        if(year<1900||year>2100){
            System.out.println("年超出范围");
            return false;
        }
        if(getDayNum(year,month)==0){
            System.out.println("不存在该月份");
            return false;
        }
        if(day<0||day>getDayNum(year,month)){
            System.out.println("不存在该日期"+year+month+day);
            return false;
        }
        return true;
    }

    //获取该年改越对应的天数
    public int getDayNum(int year,int month){
        switch (month){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if(year%4==0){
                    return 29;
                }
                else return 28;
            default:
                return 0;
        }
    }
}
