package Array;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindrome {
	public static void main(String[] args) {
		String s = "dbabdcd";
		String res = findLongestPlalindromeStringII(s);
		System.out.println("s最长的回文子字符串为：");
		System.out.println(res);
	}
	
	//给定一个字符串，输出其最长的回文子字符串
	
	// 预处理字符串，在两个字符之间加上#,首位也加上#
    private static String preHandleString(String s) {
        StringBuffer sb = new StringBuffer();
        int len = s.length();
        sb.append('#');
        for(int i = 0; i < len; i++) {
            sb.append(s.charAt(i));
            sb.append('#');
        }
        return sb.toString();
    }

    // 寻找最长回文字串
    public static String findLongestPlalindromeString(String s) {
        // 先预处理字符串
        String str = preHandleString(s);
        // 处理后的字串长度
        int len = str.length();
        // 右边界
        int rightSide = 0;
        
        // 右边界对应的回文串中心,也就是当前i为中心的回文字符串的中心
        int rightSideCenter = 0;
        
        // 保存以每个字符为中心的回文长度一半（向下取整）,数组中所有元素初始化的时候为0，保存的是不算元素本身的一侧的回文字符串的长度
        //比如aba，第一个元素a，以他为中心的回文串，一侧长度为0，第二个元素b，以他为中心的回文串一侧的长度为1。。。。
        int[] halfLenArr = new int[len];
        
        // 记录回文中心，是当前最长回文子字符串的中心
        int center = 0;
        
        // 记录最长回文长度，是以center为中心的回文字符串一侧的长度，最后输出的时候就要根据center和longestHalf来输出
        int longestHalf = 0;
        
        for(int i = 0; i < len; i++) {
            // 是否需要中心扩展
            boolean needCalc = true;
            // 如果在右边界的覆盖之内
            if(i < rightSide) {
                // 计算i相对rightSideCenter的对称位置leftCenter
                int leftCenter = 2 * rightSideCenter - i;
                // 根据回文性质得到的结论，先给他一个值，后面需要调整的话再进行覆盖
                halfLenArr[i] = halfLenArr[leftCenter];
                // 如果超过了右边界，进行调整，这个调整也只是暂时的调整，因为在右边界之外的部分我们并不知道。
                if(i + halfLenArr[i] > rightSide) {
                    halfLenArr[i] = rightSide - i;
                }
                // 如果根据已知条件计算得出的最长回文小于右边界，则不需要扩展了，就使用对称点halfLenArr[leftCenter]这个值即可
                if(i + halfLenArr[leftCenter] < rightSide) {
                    // 直接推出结论
                    needCalc = false;
                }
            }
            // 中心扩展
            if(needCalc) {
//            	System.out.println("-----------");
                while(i - 1 - halfLenArr[i] >= 0 && i + 1 + halfLenArr[i] < len) {
                    if(str.charAt(i + 1 + halfLenArr[i]) == str.charAt(i - 1 - halfLenArr[i])) {
                        halfLenArr[i]++;
                    } else {
                        break;
                    }
                }
                // 更新右边界及中心
                rightSide = i + halfLenArr[i];
                rightSideCenter = i;
                // 记录最长回文串
                if(halfLenArr[i] > longestHalf) {
                    center = i;
                    longestHalf = halfLenArr[i];
                }
            }
        }
        // 去掉之前添加的#
        StringBuffer sb = new StringBuffer();
        for(int i = center - longestHalf + 1; i <= center + longestHalf; i += 2) {
            sb.append(str.charAt(i));
        }
        return sb.toString();
    }
	
    public static String findLongestPlalindromeStringII(String s){
    	if (s.length() == 0)
    		return "";
    	//我自己写的关于找出最长回文字符串的时间复杂度O(n)算法
    	//第一步：预处理,加上特殊字符，让其成为奇数的长度,每一个字符的左右均要加
    	String ss = preHandleString(s);
    	System.out.println("原字符串："+s);
    	System.out.println("预处理后的新字符串为"+ss);
    	int center = 0;    //当前最长回文串的中心，时时要更新的
    	int right_side = 0; // 当前最长回文串的右边界，也是时时更新的。
    	int[] Len = new int[ss.length()];  //记录以当前位置i为中心的回文串折叠起来的长度，比如“aba”，第二个元素b为中心，回文串折叠起来长度为2
    	//且Len有个特性，Len[i]-1的值就是原字符串中以i为中心的回文串的长度，如何证明？？
    	//证明如下：以i为中心的回文半径为Len[i]，那么可知以i为中心的回文串长度为2*Len[i]-1，我们知道，#的数量总是比字符数量多1，那么设字符数量为x
    	//也就是x+x+1=2*Len[i]-1，解得x=Len[i]-1.
    	Len[0] = 1;  //第一个元素自身肯定是一个回文串，且折叠起来长度为1
    	for (int i = 1; i < ss.length(); i++) {
    		//先计算出i关于center的对称点
    		int left_mirror = 2*center - i;
    		boolean is_kuozhan = true;
			if(i < right_side){ //如果i在当前最远右边界之内，找到i关于center的对称点，求出其已知回文的长度
				int len_ = Len[left_mirror];
				//已知的长度加上当前位置，看看是否超过了当前最远的右边界,i离right_side有right_side-i+1个数字，包括i本身
				if(i+len_ > right_side)
					//暂时只能赋值到当前最远边界的地方，后面的还需要扩展，当i>right_left的时候也需要扩展，所以用一个标记记录，后面统一扩展
					Len[i] = right_side - i + 1; 
				else{
					Len[i] = Len[left_mirror];   //否则，冲不出当前最远的边界，就和对称的地方的len值一样
					is_kuozhan = false;         //这里就直接可以确定的了，不用扩展了
				}
			}
			//执行到这里，说明i>=right_side了，需要扩展，但是上面if内的i+len_ > right_side也需要扩展，所以先判断下扩展标记
			//扩展也是从中间扩展，慢慢往两遍发散
			if(is_kuozhan){
				while(i-Len[i] >= 0 && i+Len[i] < ss.length() && ss.charAt(i-Len[i]) == ss.charAt(i+Len[i]))
					Len[i]++;
				//扩展调整完毕，还要判断是否需要更新当前的最长回文串的右边界和当前最长回文串的中心
				if(Len[i] > Len[center]){
					right_side = i+Len[i]-1;
					center = i;
				}
			}
		}
    	//执行到这里，就可以判断出最长回文字符串的中心点为center，折叠起来的长度为Len[center],最右边界为right_side
    	System.out.println("中心点(在新字符串中)："+center);
    	System.out.println("折叠起来的长度为(在新字符串中)："+Len[center]);
    	System.out.println("最右边界为(在新字符串中)："+right_side);
    	//输出最长回文字符串
    	StringBuffer sub = new StringBuffer();
    	for (int j = center-(Len[center]-1)+1;j<=center+(Len[center]-1);j+=2) {
			sub.append(ss.charAt(j));
		}
    	return sub.toString();
    	
    }
}
