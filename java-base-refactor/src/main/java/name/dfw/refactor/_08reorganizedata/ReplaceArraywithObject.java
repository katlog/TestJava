package name.dfw.refactor._08reorganizedata;

/**
 * Created by fw on 2018/4/24
 */
public class ReplaceArraywithObject {

    class Before {

        // 数组的声明
        String[] row = new String[3];

        public void use() {
            // 数组的使用
            row [0] = "Liverpool";
            row [1] = "15";
            String name = row[0];
            int wins = Integer.parseInt(row[1]);
        }
    }

    static class Refacotor{
        Performance row = new Performance();

        class Performance{
            public String[] _data = new String[3];

            public String getName() {
                return _data[0];
            }
            public void setName(String arg) {
                _data[0] = arg;
            }
            public int getWins() {
                return Integer.parseInt(_data[1]);
            }
            public void setWins(String arg) {
                _data[1] = arg;
            }

        }

        public void use() {
            row._data [0] = "Liverpool";
            row._data [1] = "15";
            String name = row._data[0];
            int wins = Integer.parseInt(row._data[1]);
        }
    }

}
