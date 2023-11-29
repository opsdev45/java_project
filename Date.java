package org.example;

public class Date {
    private int _day;
    private int _month;
    private int _year;
    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;
    private final int MIN_DAY=1;
    private final int MIN_MONTH=1;
    private final int MAX_MONTH=12;
    private final int MIN_YEAR=1000;
    private boolean validate_date=false;

    public Date(int day, int month, int year) {
        validateDate(day,month,year);
        if ( !validate_date){
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }
    }
    public Date(Date other){
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

//    getDay(), getMonth(), getYear()
    public int getDay(){
        return _day;
    }
    public int getMonth(){
        return _month;
    }
    public int getYear(){
        return _year;
    }
//    setDay(int dayToSet), setMonth(int monthToSet),setYear(int yearToSet).
    public void setDay(int dayToSet){
        validateDate(dayToSet,_month,_year);
        if (validate_date){
            _day=dayToSet;
        }
    }
    public void setMonth(int monthToSet){
        validateDate(_day,monthToSet,_year);
        if (validate_date){
            _month=monthToSet;
        }
    }
    public void setYear(int yearToSet){
            validateDate(_day,_month,yearToSet);
            if (validate_date){
                _year=yearToSet;
            }
    }
    public boolean equals (Date other){
        return (this.calculateDate(_day,_month,_year) == other.calculateDate(other._day,other._month,other._year));
    }
    public boolean before (Date other){
        return (this.calculateDate(_day,_month,_year) < other.calculateDate(other._day,other._month,other._year));
    }
    public boolean after (Date other){
        return other.before(this);
    }
    public int difference (Date other){
        return Math.abs(this.calculateDate(_day,_month,_year) - other.calculateDate(other._day,other._month,other._year));
    }
    public String toString(){
        String dayStr = String.format("%02d", _day);
        String monthStr = String.format("%02d", _month);
        return dayStr + "/" + monthStr + "/" + _year;

    }
    public Date addYearsToDate(int num){
        _year = _year + num;
        if (_month==2 && isLeapYear(_year) && _day==28)
            _day = 29;
        else if (_month==2 && !isLeapYear(_year) && _day==29)
            _day=28;

        return new Date(_day,_month,_year);
    }

    private boolean validateDate(int day,int month,int year){
        if (year >= MIN_YEAR && month >= MIN_MONTH && month <= MAX_MONTH && day >= MIN_DAY && day <= 31) {
            if ((month == 4 || month == 6 || month == 9 || month == 11) && (day < MIN_DAY || day > 30)) {
              return validate_date=false;

            } else if (month == 2) {
                if (isLeapYear(year) && (day < MIN_DAY || day > 29)) {
                    return validate_date=false;

                } else if (!isLeapYear(year) && (day < MIN_DAY || day > 28)) {
                    return validate_date=false;
                } else {
                    _day = day;
                    _month = month;
                    _year = year;
                    return validate_date=true;
                }

            } else {
                _day = day;
                _month = month;
                _year = year;
                return validate_date=true;
            }
        } else {
            return validate_date=false;
        }
    }

    // checks if the year is a leap year
    private boolean isLeapYear (int y)
    {
        return (y%4==0 && y%100!=0) || (y%400==0) ? true : false;
    }

    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year) {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    }
}










 class DateTest {
    public static void main(String[] args) {
        // Test cases
        Date validDate = new Date(25, 12, 2022);
        Date validDate1 = new Date(25, 12, 2022);
        Date validDate2 = new Date(24, 12, 2022);
        Date invalidDate = new Date(28, 2, 2006);
        Date changeDate = new Date(20, 5, 2035);
        Date d1 = new Date(5, 5, 2000);
        Date d2 = new Date(28, 2, 2000);
        Date d3 = new Date(29, 2, 2004);
        changeDate.setDay(15);
        d1.addYearsToDate(2);
        d2.addYearsToDate(8);
        d3.addYearsToDate(1);

//        System.out.println(validDate1.equals(validDate));
//        System.out.println(validDate1.equals(invalidDate));
//        System.out.println(validDate1.equals(validDate2));
//        System.out.println(validDate2.before(validDate1));
//        System.out.println(validDate1.after(validDate2));
//        System.out.println(validDate1.difference(validDate2));
        System.out.println(d1.addYearsToDate(2));
        System.out.println(d2.addYearsToDate(8));
        System.out.println(d3.addYearsToDate(1));



        // Print the results

//        System.out.println(invalidDate.toString());
//        System.out.println(changeDate.toString());
//        System.out.println(d1.toString());
//        System.out.println(invalidDate.toString());
    }
}
