package name.katlog.alg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


/**
 * @moudle: _1_ 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月9日 下午5:55:47
 *
 */
public class _1_ {
	
	/**
	 * Given a string, find the first non-repeating character in it and return it's index. 
	 * 	If it doesn't exist, return -1.
	 *	
	 *  Examples:
	 *		s = "leetcode"
	 *			return 0.
	 *	
	 *		s = "loveleetcode",
	 *			return 2.
	 *	Note: You may assume the string contain only lowercase letters.
	 */
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        int[] num = new int[26];
        for(int i=0; i<chars.length; i++)
            num[chars[i] - 'a']++; 
        
        //找到第1个
        for(int j=0; j<chars.length;j++){
            if( num[chars[j]-'a']==1)
                return j;
        }
        return -1;
        
    }
	
	public static List<Integer> lexicalOrder1(int n) {

        List<Integer> list = new ArrayList<>(n);
        int curr = 1;
        for (int i = 1; i <= n; i++) {
            list.add(curr);
            if (curr * 10 <= n) {
                curr *= 10;
            } else if (curr % 10 != 9 && curr + 1 <= n) {
                curr++;
            } else {
                while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
            }
        }
        return list;
	}
	
    
    public static List<Integer> lexicalOrder2(int n) {
        if(n<1)
            return null;
        //
        int curr = 1;
        List<Integer> list = new ArrayList<Integer>();
        for( int i =1 ;i<=n;i++){
            list.add(curr);
            
            if (curr*10<=n) {  // 1~n/10  ****
				curr *=10;
			}else if ( (curr %10!=9)&& curr <n) { //  除去 n和 
				curr++;
			}else{// n 和 ***9
				/*if (curr==n) {
					do {
						curr/=10;
					}while((curr/10)>=1);
					curr++;
				}else {  //  ***9  
					curr/=10;
					curr++;
				}*/
				
				while ((curr / 10) % 10 == 9) {
                    curr /= 10;
                }
                curr = curr / 10 + 1;
			}
            
            
        }
        return list;
     }
    public static List<Integer> lexicalOrder3(int n) {
        List<Integer> ans = new ArrayList<>(n);
        ans.add(1);
        for (int i = 1, prev = 1; i < n; ++i) {
            if (prev * 10 <= n) {
                prev *= 10;
            } else {
                while (prev % 10 == 9 || prev == n) prev /= 10;
                prev++;
            }
            ans.add(prev);
        }
        return ans;
    }
    
    
   /* public static List<Integer>  lexicalOrder4 (int n) {
    	
    	List<Integer> list = new ArrayList<Integer>();
    	
    	char[] chars = new char[]{'0','1','2','3','4','5','6','7','8','9'};
    	Integer i = new Integer(1);
    	
    	int len = 1;
    	while (n/10>=1) {
			len++;
		}

		StringBuffer str = new StringBuffer(1);

		for (int j = 0; j < len; j++) {

			for (char c : chars) {
				str.deleteCharAt(j).append(c);
			}
		}
			
    	
        return list;
	}
    */
    
	public static void main(String[] args) {

//		List<Integer> list = lexicalOrder(5000);
//		System.out.println(list);
		List<Integer> list1 = lexicalOrder1(192);
		System.out.println(list1);
		List<Integer> list2 = lexicalOrder2(192);
		System.out.println(list2);
		List<Integer> list3 = lexicalOrder3(192);
		System.out.println(list3);
	}
	    
	/**双指针遍历 速度慢*/
    public int[] twoSum1(int[] nums, int target) {
        int[] results = new int[2];
        for(int i = 0;i<nums.length-1;i++){
            for(int j = i+1; j<nums.length ;j++){
                if((nums[i]+nums[j])==target){
                    results[0] = i;
                    results[1] = j;
                }
            }
        }
        return results;
    }
    
    /**大神的方法*/  //还是不太清楚快在哪？
    public int[] twoSum2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i ;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    
    
    @Test public void twoSum(){
    	System.out.println(Arrays.toString(twoSum1(new int[]{1,3,45,4,2,7},7)));
    	System.out.println(Arrays.toString(twoSum2(new int[]{1,3,45,4,2,7},7)));
    }
    
    
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) { 
            
            int l = nums1.length+nums2.length;
            
            if(l==1||l==0)
                return (nums1[0]+nums2[0]);
            
            int m = l%2;
            int[] nums = new int[l];
            
            int n1 = 0;
            int n2 = 0;
            
            for( int i =0;i<l;i++){
            	
            	nums[i] = nums1[n1]>=nums2[n2]?nums2[n2++]:nums1[n1++];
            	
            	if(n1>=1)
            		n1 = n1== nums1.length-1?n1:n1-1;
            	if(n2>=1)
            		n2 = n2==nums2.length-1?n2:n2-1;
            }
            
            if(m==0)
                return ((nums[l/2-1]+nums[l/2])/2);
            else
                return nums[l/2-1];
    }
	
    @Test public void findMedianSortedArrays(){
    	int[] nums1 = new int[]{1,3};
    	int[] nums2 = new int[]{2};
    	System.out.println(findMedianSortedArrays1(nums1, nums2));
    }
}
