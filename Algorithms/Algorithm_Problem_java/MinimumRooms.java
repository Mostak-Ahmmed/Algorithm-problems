

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Activity {
    int start;
    int end;

    Activity(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

public class MinimumRooms {
    public static int findMinimumRooms(Activity[] activities) {
        Arrays.sort(activities, Comparator.comparingInt(a -> a.start));

        PriorityQueue<Activity> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));
        minHeap.add(activities[0]);
        int minRooms = 1;

        for (int i = 1; i < activities.length; i++) {
            Activity current = activities[i];
            Activity earliest = minHeap.peek();

            if (current.start >= earliest.end) {
                minHeap.poll();
            } else {
                minRooms++;
            }

            minHeap.add(current);
        }

        return minRooms;
    }

    public static void main(String[] args) {
        Activity[] activities = {
            new Activity(1, 5),
            new Activity(2, 7),
            new Activity(4, 6),
             new Activity(8, 9),
          
        };

        int minimumRooms = findMinimumRooms(activities);
        System.out.println("Minimum number of rooms required: " + minimumRooms);
    }
}
