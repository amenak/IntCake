import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



public class Solution {

    public static class Meeting{

        private int startTime;
        private int endTime;

        public Meeting(int startTime, int endTime) {
            // number of 30 min blocks past 9:00 am
            this.startTime = startTime;
            this.endTime   = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Meeting)) {
                return false;
            }
            final Meeting meeting = (Meeting) o;
            return startTime == meeting.startTime && endTime == meeting.endTime;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + startTime;
            result = result * 31 + endTime;
            return result;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", startTime, endTime);
        }
        
     /*   @Override
		public int compare(Meeting o1, Meeting o2) {
			if(o1.startTime < o2.startTime)
				return -1;
			if(o1.startTime < o2.startTime)
				return 1;
			if(o1.startTime == o2.startTime)
				return 0;
			
			return 0;
        }*/
       
        //looks weird - why is there a method inside of a method?
        public static Comparator<Meeting> orderByTime ()
        {   
         Comparator<Meeting> comp = new Comparator<Meeting>(){
             @Override
             public int compare(Meeting o1, Meeting o2) {
     			if(o1.startTime < o2.startTime)
     				return -1;
     			if(o1.startTime < o2.startTime)
     				return 1;
     			if(o1.startTime == o2.startTime)
     				return 0;
     			
     			return 0;
             }        
         };
         return comp;
        }  
    	
    }
    public static List<Meeting> mergeRanges(List<Meeting> meetings) {

        if(meetings.size() == 0) return meetings; 
   
        //sort the arraylist by starting time        
         Collections.sort(meetings, Meeting.orderByTime());
         
         List<Meeting> merged = new ArrayList<Meeting> ();


            int start = meetings.get(0).startTime; 
            int end = meetings.get(0).endTime; 
            
        for(int i=1; i < meetings.size(); i++) {
        	Meeting m = meetings.get(i);
        	if(m.startTime <= end)
        	{
        		if(m.endTime > end) {
        			end = m.endTime; 
        		}
        	}else {
        		merged.add(new Meeting(start, end));
        		start = m.startTime;
        		end = m.endTime; 
        	}
        }
        
        merged.add(new Meeting(start, end));
        return merged;
    }
    
    public static void main(String[] args) {
       /* final List<Meeting> meetings = Arrays.asList(
                new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
                new Meeting(10, 12), new Meeting(9, 10)
            );*/
        /*final List<Meeting> meetings = Arrays.asList(
                new Meeting(1, 3), new Meeting(2, 4)
            );*/
        
    	/*final List<Meeting> meetings = Arrays.asList(
                new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8),
                new Meeting(9, 10), new Meeting(10, 12)
            );*/
    	
       final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
        
        final List<Meeting> actual = mergeRanges(meetings);
            System.out.println("\n\nprinting meetings");
            for(Meeting m: actual) {
            	System.out.println(m.startTime + " " + m.endTime);
            }
    }
}