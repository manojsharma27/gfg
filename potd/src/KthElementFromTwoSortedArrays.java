/**
 * Problem : https://practice.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1#
 */
public class KthElementFromTwoSortedArrays {
    public long kthElement( int a[], int b[], int n, int m, int k) {

        if(n>m) {
            return kthElement(b, a, m, n, k);
        }

        int l = 0;
        int h = Math.min(n, k);
        while(l<=h) {
            int c1 = l + ((h-l)/2);
            int c2 = k - c1;

            if(c2>m) {
                l+=c2-m;
                continue;
            }

            int l1 = c1<=0 ? Integer.MIN_VALUE : a[c1-1];
            int l2 = c2<=0 ? Integer.MIN_VALUE : b[c2-1];

            int r1 = c1>=n ? Integer.MAX_VALUE : a[c1];
            int r2 = c2>=m ? Integer.MAX_VALUE : b[c2];

            if(l1 <= r2 && l2 <= r1) {
                return Math.max(l1, l2);
            }

            if(l1 > r2) {
                h = c1 - 1;
            } else {
                l = c1 + 1;
            }

        }

        return -1L;
    }

    public static void main(String[] args) {
        KthElementFromTwoSortedArrays obj = new KthElementFromTwoSortedArrays();
        int arr1[] = {2, 3, 6, 7, 9};
        int arr2[] = {1, 4, 8, 10};
        int k = 5;
        long result = obj.kthElement(arr1, arr2, arr1.length, arr2.length, k);
        System.out.println("Result : " + result);
    }
}
