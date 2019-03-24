package dataStructure.binaryHeap;


public class TestMaxHeap {
	public static void main(String[] args) {
		MaxHeap<Integer> maxHeap = new MaxHeap<>();
		
		
		maxHeap.Insert(41);
		maxHeap.Insert(44);
		maxHeap.Insert(490);
		maxHeap.Insert(4329);
		maxHeap.Insert(465);
		maxHeap.Insert(4234);
		maxHeap.Insert(4332);
		maxHeap.Insert(444);
		
		System.out.println(maxHeap.getSize());
		
		while(!maxHeap.isEmpty()){
			System.out.print(maxHeap.remove()+" ");   //从大到小输出
		}
		
		System.out.println();
		//创建数组
		Integer[] array = {43,54,656,765,1,87,76,4234,676};

		MaxHeap<Integer> maxHeap1 = new MaxHeap<>(array);
		System.out.println(maxHeap1.getSize());

		while (!maxHeap1.isEmpty()) {
			System.out.print(maxHeap1.remove() + " "); // 从大到小输出
		}
		
	}

}
