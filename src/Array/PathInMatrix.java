package Array;

import java.util.ArrayList;

public class PathInMatrix {
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        if(matrix.length == 0 && str.length == 0)
            return true;
        if(matrix.length == 0 && str.length != 0)
            return false;
        ArrayList<ArrayList<Integer>> points = new ArrayList<>();
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(matrix[i*cols+j] == str[0]){
                    ArrayList<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    points.add(tmp);
                }
            }
        }
        for(int k=0;k<points.size();k++){
            ArrayList<Integer> point = points.get(k);
            //找到每一个可能的起始点之后，遍历每一个，便利之前先把是否访问标识都设为false
            boolean[][] isVisited = new boolean[rows][cols];
            int x = point.get(0);
            int y = point.get(1);
            isVisited[x][y] = true; //该元素已经访问过
            if(helper(matrix, rows, cols, x, y, 1, str, isVisited))
            	return true;
        }
        return false;
    }
   
    public static boolean helper(char[] matrix, int rows, int cols, int x, int y, int length, char[] str, boolean[][] isVisited){
        if(length == str.length)
            return true;
        //开始向四周寻找出路
        System.out.println("x: "+x+" y: "+y);
        for(int i=0;i<4;i++){
            int x_=0;
            int y_=0;
            switch(i){
                case 0:
                    x_ = x-1;
                    y_ = y;
                    break;
                case 1:
                    x_ = x+1;
                    y_ = y;
                    break;
                case 2:
                    x_ = x;
                    y_ = y+1;
                    break;
                case 3:
                    x_ = x;
                    y_ = y-1;
                    break;
            }
            System.out.println("x_: "+x_+" y_: "+y_);
            if(x_>=0 && x_<rows && y_>=0 && y_<cols && matrix[x_*cols+y_] == str[length] && isVisited[x_][y_] == false){
                isVisited[x_][y_] = true;
                if (helper(matrix, rows, cols, x_, y_, length+1, str, isVisited))
                	return true;
                isVisited[x_][y_] = false;
            }
        }
        return false;
    }
    public static void main(String[] args) {
		char[] matrix = {'a','b','c','e','s','f','c', 's', 'a', 'd','e','e'};
		char[] str = {'b', 'c','e','s', 'e','e'};
		boolean is_has = hasPath(matrix, 3, 4, str);
		System.out.println(is_has);
	}

}
