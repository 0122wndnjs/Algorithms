public class test {

  public static void partition(int[] arr) {
	  int pind = arr.length-1;	// pivot index is the last index
		int j = -1;
		for(int i = 0; i < pind; i++) {
			if(arr[i] < arr[pind]) {
				j++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
		}
		j++;
		int temp2 = arr[j];
		arr[j] = arr[pind];
		arr[pind] = temp2;
	}

  public static void main(String[] args) {
    int[] a = {4,5,8,0,1,2,5,6,7,8,9,6};
    partition(a);
    for(int i = 0; i<a.length; i++)
      System.out.println(a[i]);
  }
}
