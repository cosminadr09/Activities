package data;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class MonitoredData {

	public String activityLabel;
	public DateTime startTime;
	public DateTime endTime;
	
	public MonitoredData(String activityLabel, DateTime startTime,
			DateTime endTime) {
		super();
		this.activityLabel = activityLabel;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	

	public DateTime getStartTime() {
		return startTime;
	}



	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}



	public DateTime getEndTime() {
		return endTime;
	}



	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}



	public String getActivityLabel() {
		return activityLabel;
	}



	public void setActivityLabel(String activityLabel) {
		this.activityLabel = activityLabel;
	}



	@Override
	public String toString() {
		return "MonitoredData [activityLabel=" + activityLabel + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}
	
	
	public int getDay(){
		if (this.startTime.getDayOfMonth()==this.endTime.getDayOfMonth()){
			return this.startTime.getDayOfMonth();
		}
		return this.endTime.getDayOfMonth();
	}
	
	public int getDurationHours(){
		int h= Hours.hoursBetween(this.startTime, this.endTime).getHours()%24;
		int m=Minutes.minutesBetween(startTime, endTime).getMinutes()%60;
		int s=Seconds.secondsBetween(startTime, endTime).getSeconds()%60;
		//System.out.println("H: "+h+"M: "+m+"S: "+s);
		int duration = h*3600+m*60+s;//secunde
		return duration/3600;
/*
		DateTime date = new DateTime(startTime.getYear(),startTime.getMonthOfYear(),startTime.getDayOfMonth(),h,m,s);
		Date start = startTime.toDate();
		Date end = endTime.toDate();
		long diff=end.getTime()-start.getTime();
		long diffHours = diff / (60 * 60 * 1000)%24;
		return diffHours;*/
	}
	
	public int getDurationMinutes(){
		int m=Minutes.minutesBetween(startTime, endTime).getMinutes()%60;
		int s=Seconds.secondsBetween(startTime, endTime).getSeconds()%60;
		int duration =m*60+s;//secunde
		return duration/60;
	}
	

	
}
