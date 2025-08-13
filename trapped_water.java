
/*
 * Name: Shasha Alvares
 * Date: 2/15/25
 * Description: This program calculates the amount of water trapped after rainfall based on the different wall height that 
 * 				limit the amount of water that can be trapped
 */
public class trapped_water {
	
public static int maxWater(int[] arr) {
	int l = 1; //pointer used to move through array, starts at index 1 bc the left of index 0 does not exist so no comparison of height can be made
	int r = arr.length-2; //pointer used to move through array, starts at second to last index bc the right of the last index does not exist 
						  //so no comparison of height can be made
	int trappedWaterSum = 0; //total trapped water between walls
	
	/*
	 * maxLeft is initiated to point to the first element in the array and maxRight to the last element
	 * because we are keeping track of the wall heights since the minimum of the two limit the water 
	 * that is stored.
	 */
	int maxLeft = arr[0]; //keeps track of maximum height to the left of current index indicated by l pointer
	int maxRight = arr[arr.length-1]; //keeps track of maximum height to the right of current index indicated by r pointer
 
	
	while(l <= r) //loop will continue as long as the two pointers have not crossed while traversing the array
	{
		if(maxLeft <= maxRight) //left height is smaller than or equal to right and limits the water that can be stored 
		{
			trappedWaterSum += Math.max(0, maxLeft - arr[l]); //add water stored at current index based on limiting factor of left height
			maxLeft = Math.max(maxLeft, arr[l]); //update left max based on current index wall height compared to previously recorded maxLeft value
			l++; //update left pointer to next index
		}
		
		else //right height is smaller than left and limits the water that can be stored 
		{
			trappedWaterSum += Math.max(0, maxRight - arr[r]); //add water stored at current index based on limiting factor of right height
			maxRight = Math.max(maxRight, arr[r]); //update right max based on current index wall height compared to previously recorded maxRight value
			r--; //moves right pointer towards the front of the array
		}
	}
	return trappedWaterSum; //returns final sum of total water stored
}
	
public static void main(String[] args) {
		int[] firstArr = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(maxWater(firstArr));
		
		int[] secondArr = {3, 0, 1, 0, 4, 0, 2};
		System.out.println(maxWater(secondArr));
}
}