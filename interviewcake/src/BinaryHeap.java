import java.util.ArrayList;
import java.util.List;

public class BinaryHeap {
	public List<Integer> minHeap; 
	
	public BinaryHeap() {
		minHeap = new ArrayList<> ();
	}
	
	public BinaryHeap(int [] array) {
		Heapify(array);
	}
	
	public void insert(int x) {
		if(minHeap.size() == 0) {
			minHeap.add(x); 
			return;
		}
		
		//add to the bottom right most element 
		minHeap.add(x);
		//compare to parent and decide to bubble 
		int elemIdx = minHeap.size()-1;
		
		 //what if you have 0? 
		
		//repeat until you reach the root 
		while(elemIdx > 0) {

			int parentIdx = (elemIdx - 1)/2;
			//System.out.println(elemIdx + " " + parentIdx);
			if(minHeap.get(elemIdx) < minHeap.get(parentIdx)) {
				swap(elemIdx, parentIdx); 
			}
			
			elemIdx = parentIdx; 
		
		}
		
	}
	
	public void removeMin() {
	 //swap last element with the root 
	 minHeap.set(0, minHeap.get(minHeap.size()-1));
	 minHeap.remove(minHeap.size()-1);
	 
	 //bubble down
	 int parentIdx = 0; 
	 int leftChildIdx; 
	 int rightChildIdx;
	 
	 while(parentIdx < (minHeap.size()/2)) {
		 leftChildIdx = Math.abs(((2 * parentIdx) - 1));  
		 rightChildIdx = (2 * parentIdx) + 2; 
		 
		 if(minHeap.get(parentIdx) > minHeap.get(leftChildIdx) || minHeap.get(parentIdx) > minHeap.get(rightChildIdx)) {
			 if(minHeap.get(leftChildIdx) < minHeap.get(rightChildIdx)) {
				 swap(leftChildIdx, parentIdx);
				 parentIdx = leftChildIdx;
			 }else {
				 swap(rightChildIdx, parentIdx);
				 parentIdx = rightChildIdx;
			 }
		 }
		 
		 
	 }
	 
	}
	
	private void swap(int idx1 , int idx2) {
		 //validate index
		if(idx1 > minHeap.size() || idx1  < 0) return; 
		if(idx2 > minHeap.size() || idx2 < 0) return; 
		
		 int temp = minHeap.get(idx1);
		 minHeap.set(idx1, minHeap.get(idx2));
		 minHeap.set(idx2, temp);
	}
	
	public int getMin() {
		return minHeap.get(0);
	}
	
	public void Heapify(int [] array) {
		//sort the elements to make it a valid heap 
		//bubble down or up? 
		/*minHeap = new ArrayList<Integer>(); 
		for(int x : array) {
			insert(x);
		}*/
		
		//look at second to last level n/2 - right node .. 
		//compare to its children left and right. if its less greater than - we swap. 
		//we look at the node before that. swap if its greater than children. 
		//we eventually reach the root. keep swapping down until we reach the desired position. 
		
		for(int i=(array.length/2)-1; i>=0; i--) {
			int parent = i;
			while(parent < array.length/2 && parent >= 0) {
				int left = (parent*2) + 1; 
				int right = (parent*2) + 2; 
				
				boolean leftChild = true;
				boolean rightChild = true;
				if(left > array.length-1) leftChild = false;
				if(right > array.length-1) rightChild = false;
						
				if(leftChild && array[left] < array[parent]) {
					if(rightChild && array[left] > array[right]) {
						int temp = array[parent];
						array[parent] = array[right]; 
						array[right] = temp; 
						parent = right;
					}else {
						int temp = array[parent];
						array[parent] = array[left]; 
						array[left] = temp;
						parent= left;
					}
				} else {
					break;
				}
				
				 
			}
		}

		
		//print
		for(int x : array)
			System.out.print(x + " ");
	}
	
	public void print() { //print it in a tree like structure
		for(int i=0; i < minHeap.size();  i = (i*2) +1  ) {
			System.out.println("\nlevel of tree" + i);
			for(int j=i; j <= (i*2); j++) {
				if(j<minHeap.size())
					System.out.print(minHeap.get(j) + " ");
				else 
					return;
			}
			
		}
	}
	
	public static void main(String[] args) {
		//test
		BinaryHeap test = new BinaryHeap();
		//BinaryHeap test2 = new BinaryHeap(new int [] {10,5,3,2}); 
		//test2.print(); 
		
		test.insert(10);
		test.insert(5);
		test.insert(3);
		test.insert(2);
		test.print();
		
		System.out.println("get min : " +  test.getMin());
		test.removeMin();
		System.out.println("remove min : " + test.getMin());
		
		test.Heapify(new int [] {8,7,2,4,3,5});
	}

}
