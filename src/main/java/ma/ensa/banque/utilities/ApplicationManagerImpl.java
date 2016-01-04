package ma.ensa.banque.utilities;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Repository;

@Repository("managementUtil")
public class ApplicationManagerImpl implements IApplicationManager {
	
	public String dateCaster(Date date){
		String formatedDate;
		DateFormat mediumDateFormatFR = DateFormat.getDateTimeInstance(
				DateFormat.MEDIUM,
				DateFormat.MEDIUM, new Locale("FR","fr"));
		formatedDate = mediumDateFormatFR.format(new Date());
		return formatedDate;
	}

}
