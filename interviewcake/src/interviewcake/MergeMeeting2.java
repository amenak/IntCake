package interviewcake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class MergeMeeting2 {

    public static class Meeting implements Comparable<Meeting>{

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
        
        @Override
		public int compareTo(Meeting o) {
			if(this.startTime < o.getStartTime())
				return -1;
			if(this.startTime > o.getStartTime())
				return 1;
			
			return 0;
		}

    }

    public static List<Meeting> mergeRanges(List<Meeting> meetings) {
    	if(meetings.size() < 2) throw new IllegalArgumentException("not enough meetings to merge");
    	//copy the meetings into a new array and sort the meetings 
    	//while loop through sorted meetings. start1, end1, start2, end2.
    	//sub while loop and find meetings if start2 < end1. then end1=end2 only if end2>end1. ignores meetings that touch;
    	//merged meetings array

        
        List<Meeting> sortedMeetings = new ArrayList <Meeting> (meetings);
        Collections.sort(sortedMeetings);
    	for(Meeting x : meetings) System.out.print(x.toString() + " , ");
        System.out.println("sorted input^");
    	List<Meeting> merged = new ArrayList<Meeting> ();
    	
    	int newRangeIdx = 0;   	
    	while(newRangeIdx < sortedMeetings.size()){
    		int start1 = sortedMeetings.get(newRangeIdx).getStartTime();
    		int end1 =  sortedMeetings.get(newRangeIdx).getEndTime();
    		
    		newRangeIdx = newRangeIdx + 1; 
    		for(int k=newRangeIdx; k<sortedMeetings.size()-1; k++) {
    			int start2 = sortedMeetings.get(k).getStartTime();
    			int end2 = sortedMeetings.get(k).getEndTime();
    			
    			if(start2 <= end1) {
    				if(end2 > end1) end1 = end2; 
    			} else if (start2 > end1){ //changed here
    				newRangeIdx = k;
    				break;
    			}
    			
    			if(k == sortedMeetings.size()-2) newRangeIdx = k+1;
    			
    		}
    		
    		System.out.println("before last" + start1 + "	" + end1 + "	" + newRangeIdx);
    		
			if(newRangeIdx == sortedMeetings.size()-1) { //last index 
				int start2 = sortedMeetings.get(newRangeIdx).getStartTime();
				int end2 = sortedMeetings.get(newRangeIdx).getEndTime();
				newRangeIdx++;
				
				if(start2 <= end1) {
    				if(end2 > end1) end1 = end2;
    			} else if (start2 > end1){ //changed here
    	    		Meeting m1 = new Meeting(start1, end1);
    	    		merged.add(m1);
    				Meeting m = new Meeting(start2, end2);
    	    		merged.add(m);
    	    		continue;
    			}
			}
			
			System.out.println(start1 + "	" + end1 + "	" + newRangeIdx);
    		Meeting m = new Meeting(start1, end1);
    		merged.add(m);
    	}
    	
    	
    	for(Meeting x : merged) System.out.print(x.toString() + " , ");
        System.out.println("merged^");
    	return merged;
        
    }


















    // tests

    @Test
    public void meetingsOverlapTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(2, 4));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 4));
        assertEquals(expected, actual);
    }

    @Test
    public void  meetingsTouchTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(5, 6), new Meeting(6, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(5, 8));
        assertEquals(expected, actual);
    }

    @Test
    public void meetingContainsOtherMeetingTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 8), new Meeting(2, 5));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
        assertEquals(expected, actual);
    }

    @Test
    public void meetingsStaySeparateTest() {
        final List<Meeting> meetings = Arrays.asList(new Meeting(1, 3), new Meeting(4, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
            new Meeting(1, 3), new Meeting(4, 8)
        );
        assertEquals(expected, actual);
    }

    @Test
    public void multipleMergedMeetingsTest() {
        final List<Meeting> meetings = Arrays.asList(
            new Meeting(1, 4), new Meeting(2, 5), new Meeting (5, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(new Meeting(1, 8));
        assertEquals(expected, actual);
    }

    @Test
    public void meetingsNotSortedTest() {
        final List<Meeting> meetings = Arrays.asList(
            new Meeting(5, 8), new Meeting(1, 4), new Meeting(6, 8));
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
            new Meeting(1, 4), new Meeting(5, 8)
        );
        assertEquals(expected, actual);
    }

    @Test
    public void oneLongMeetingContainsSmallerMeetingsTest() {
        final List<Meeting> meetings = Arrays.asList(
            new Meeting(1, 10), new Meeting(2, 5), new Meeting(6, 8),
            new Meeting(9, 10), new Meeting(10, 12)
        );
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
            new Meeting(1, 12)
        );
        assertEquals(expected, actual);
    }

    @Test
    public void sampleInputTest() {
        final List<Meeting> meetings = Arrays.asList(
            new Meeting(0, 1), new Meeting(3, 5), new Meeting(4, 8),
            new Meeting(10, 12), new Meeting(9, 10)
        );
        final List<Meeting> actual = mergeRanges(meetings);
        final List<Meeting> expected = Arrays.asList(
            new Meeting(0, 1), new Meeting(3, 8), new Meeting(9, 12)
        );
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MergeMeeting2.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
