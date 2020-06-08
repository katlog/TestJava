package org.person.dfw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 抽奖算法
 * Created by fw on 2019/4/1
 * @author difengwei
 */
public class LuckyDrawAlgorithm {

    /** 按概率获取
     * @param probabilityData*/
    public <D> Tuple<Integer,D> obtainedByProbability(Map<Box<D>, Double> probabilityData){

        if (probabilityData == null || probabilityData.size() < 1) {
            throw new IllegalArgumentException("illegal access :" + probabilityData);
        }
        // 获取的总概率
        double totalProbability = 0;
        List<Double> probabilities = new ArrayList<>();
        List<Map.Entry<Box<D>, Double>> all = new ArrayList<>();

        for (Map.Entry<Box<D>, Double> entry : probabilityData.entrySet()) {
            if (entry.getValue() > 100 || entry.getValue() < 0) {
                throw new IllegalArgumentException("every probability should under 100 :" + probabilityData);
            }
            totalProbability += entry.getValue();
            if (totalProbability > 100) {
                throw new IllegalArgumentException("total probability should under 100 :" + probabilityData);
            }
            probabilities.add(entry.getValue());
            all.add(entry);
        }
        RandomHelper helper = new RandomHelper(probabilities);
        int index = helper.randomIndex();
        Map.Entry<Box<D>, Double> result = all.get(index);
        return new Tuple<>(index, result.getKey().getData());
    }

    public static class Tuple<A,B>{
        private Tuple(A element1, B element2) {
            this.column = element1;
            this.item = element2;
        }
        private A column;
        private B item;
        public A getColumn() {
            return column;
        }
        public B getItem() {
            return item;
        }
        @Override
        public String toString() {
            return "Tuple{" + "column=" + column + ", item=" + item + '}';
        }
    }

    class DoubleSpan {
        private double minDouble;
        private double maxDouble;
        DoubleSpan(double minElement, double maxElement) {
            if (minElement > maxElement) {
                throw new IllegalArgumentException("区间不合理，minElement不能大于maxElement！");
            }
            this.minDouble = minElement;
            this.maxDouble = maxElement;
        }
    }
    public class RandomHelper {
        /**
         * 构造抽奖集合
         * @param list 为奖品的概率
         */
        RandomHelper(List<Double> list){
            lotteryList = new ArrayList<DoubleSpan>();
            if(list.size() == 0){
                throw new IllegalArgumentException("抽奖集合不能为空！");
            }
            double minElement = 0d;
            DoubleSpan continuousList = null;
            for(Double d : list){
                minElement = maxElement;
                maxElement = maxElement + d;
                continuousList = new DoubleSpan(minElement, maxElement);
                lotteryList.add(continuousList);
            }
        }

        //概率连续集合
        private List<DoubleSpan> lotteryList;
        //这里只需要最大值，最小值默认为0.0
        private double maxElement;

        /**
         * 进行抽奖操作
         * 返回：奖品的概率list集合中的下标
         */
        public int randomIndex() {
            int index = -1;
            Random r = new Random();
            //生成0-1间的随机数
            double d = r.nextDouble() * maxElement;
            if (d == 0d) {
                //防止生成0.0
                d = r.nextDouble() * maxElement;
            }
            int size = lotteryList.size();
            for (int i = 0; i < size; i++) {
                DoubleSpan cl = lotteryList.get(i);
                boolean flag = false;
                if (d > cl.minDouble && d <= cl.maxDouble) {
                    flag = true;
                }
                if (flag) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                throw new IllegalArgumentException("概率集合设置不合理！");
            }
            return index;
        }
    }

}
