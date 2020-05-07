package person.katlog.alg.interview;

import java.util.ArrayList;
import java.util.List;

public class NPolygonK {

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public List<Point> equalParts(List<Point> nPolygon,int k){
        List<Integer> lens = new ArrayList<>(nPolygon.size());
        int allLen = 0;

        List<Point> result = new ArrayList<>();

        // 1.找每段的长度
        for (int i = 0; i < nPolygon.size()-1; i++) {
            Point a = nPolygon.get(i);
            Point b = nPolygon.get(i + 1);
            int len = a.x == b.x ? Math.abs(b.y - a.y) : Math.abs(a.x - b.x);
            allLen += len;
            lens.add(len);
        }
        int splitLen = allLen / k;

        int moveLen = 0;
        for (int i = 0,j=0 ;  i < k; i++) {
            moveLen += lens.get(j) ;
            if (moveLen < i * splitLen) {
                j++;
            }
            Point a = nPolygon.get(j);
            Point b = nPolygon.get(j - 1);
            if (a.x == b.x) {
                result.add(new Point(a.x, a.y - (moveLen - i * splitLen)));
            } else {
                result.add(new Point(a.x - (moveLen - i * splitLen), a.y));
            }
        }
        return result;
    }
}
