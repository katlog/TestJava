// Enum that switches on its value to share code - questionable
package name.fw.effectivejava.examples.chapter06.item30;

enum PayrollDay0 {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY,
	SATURDAY, SUNDAY;

	private static final int HOURS_PER_SHIF = 8;

	double pay(double hoursWorked, double payRate) {
	    
	    double basePay = hoursWorked*payRate;
	    
	    double overtimePay;    // calculate overtime pay
	    switch (this) {
            case SATURDAY:case SUNDAY:
                overtimePay = hoursWorked*payRate/2;
            default:    // weekdays
                overtimePay = hoursWorked<=HOURS_PER_SHIF?
                    0:(hoursWorked - HOURS_PER_SHIF)*payRate/2;
                break;
        }
	    
		return basePay+overtimePay;
	}

	public static void main(String[] args) {
        System.out.println(PayrollDay0.SATURDAY.pay(10, 10));;
    }
}
