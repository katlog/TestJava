package org.person.dfw.fx.practice.property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.*;

/***
 * JavaFX教程 - JavaFX集合
 *
 *
 * JavaFX中的集合由javafx.collections包定义，javafx.collections包由以下接口和类组成：
 * 接口
 *
 * 接口	描述
 * ObservableList	允许我们跟踪更改的列表
 * ListChangeListener	接收更改通知的接口
 * ObservableMap	允许我们跟踪更改的映射
 * MapChangeListener	从ObservableMap接收更改通知的接口
 * 类
 *
 * 类	描述
 * FXCollections	实用程序类映射到java.util.Collections
 * ListChangeListener.Change	表示对ObservableList所做的更改
 * MapChangeListener.Change	表示对ObservableMap所做的更改
 */
public class CollectionMain {

    /**以下代码显示如何使用ObservableList，ObservableMap和FXCollections。*/
    public static class Main1 {

        public static void main(String[] args) {
            List<String> list = new ArrayList<String>();

            ObservableList<String> observableList = FXCollections.observableList(list);
            observableList.addListener(new ListChangeListener() {
                @Override
                public void onChanged(Change change) {
                    System.out.println("change!");
                }
            });
            observableList.add("item one");
            list.add("item two");
            System.out.println("Size: " + observableList.size());

        }
    }


    /**以下代码显示了如何监听ObservableMap上的更改。*/
    public static class Main2 {

        public static void main(String[] args) {
            Map<String, String> map = new HashMap<String, String>();
            ObservableMap<String, String> observableMap = FXCollections
                    .observableMap(map);
            observableMap.addListener(new MapChangeListener() {
                @Override
                public void onChanged(Change change) {
                    System.out.println("change! ");
                }
            });
            observableMap.put("key 1", "value 1");
            map.put("key 2", "value 2");

        }
    }

    /**以下代码显示了如何找出已更改的内容。*/
    public static class Main3 {

        public static void main(String[] args) {
            List<String> list = new ArrayList<String>();

            ObservableList<String> observableList = FXCollections.observableList(list);
            observableList.addListener(new ListChangeListener() {

                @Override
                public void onChanged(Change change) {
                    System.out.println("Detected a change! ");
                    while (change.next()) {
                        System.out.println("Was added? " + change.wasAdded());
                        System.out.println("Was removed? " + change.wasRemoved());
                        System.out.println("Was replaced? " + change.wasReplaced());
                        System.out.println("Was permutated? " + change.wasPermutated());
                    }
                }
            });
            observableList.add("item one");
            list.add("item two");
            System.out.println("Size: " + observableList.size());

        }
    }




}
