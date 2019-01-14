package utilities;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommonUtils {
	
	
	
	
	public static String getNextDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 1);
		
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
		
	}
	
	public static String getDateAfter3Weeks() {
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 21);
		
		return new SimpleDateFormat("MM/dd/yyyy").format(cal.getTime());
		
	}
	
	

}
