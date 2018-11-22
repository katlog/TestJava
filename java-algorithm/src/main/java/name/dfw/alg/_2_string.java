package name.dfw.alg;


/**
 * @moudle: _2_string 
 * @version:v1.0
 * @author: 丰伟
 * @date: 2017年8月10日 下午5:01:55
 *
 */
public class _2_string {

	/**
	 * 1个小时
	 * 10多次错误，边界条件很多次，
	 */
	public int lengthOfLongestSubstring1(String s) {

		if (s.length() < 1)
			return 0;

		int mxl = 1;
		for (int i = 0; i < s.length(); i++) {
			char[] chars = new char[256];

			for (int j = i; j < s.length(); j++) {
				if (chars[s.charAt(j) - (char) (0)] == 1) {
					break;
				} else {
					chars[s.charAt(j) - (char) (0)]++;
				}
				mxl = mxl > (j - i + 1) ? mxl : (j - i + 1);
			}

		}

		return mxl;
	}
	
}	
