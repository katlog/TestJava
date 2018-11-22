package org.person.dfw.fx.practice.ui.widget;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatePickerMain {


    /**
     * JavaFX DatePicker允许从给定日历中选择一天。
     * DatePicker控件包含一个带有日期字段和日期选择器的组合框。
     * JavaFX DatePicker控件使用JDK 8日期时间API。
     */
    public static class Main1 extends Application {
        public static void main(String[] args) {
            launch(args);
        }
        @Override
        public void start(Stage stage) {
            VBox vbox = new VBox(20);
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);

            DatePicker checkInDatePicker = new DatePicker();

            vbox.getChildren().add(checkInDatePicker);

            stage.show();
        }
    }

    /**用setValue向结束DatePicker添加更多时间。*/
    public static class Main2 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            VBox vbox = new VBox(20);
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);
            DatePicker startDatePicker = new DatePicker();
            DatePicker endDatePicker = new DatePicker();

            startDatePicker.setValue(LocalDate.now());
            endDatePicker.setValue(startDatePicker.getValue().plusDays(1));

            vbox.getChildren().add(new Label("Start Date:"));
            vbox.getChildren().add(startDatePicker);
            vbox.getChildren().add(new Label("End Date:"));
            vbox.getChildren().add(endDatePicker);
            stage.show();
        }
    }
    /**
     * 自定义日期选择器
     *
     * 我们可以启用和禁用显示ISO周数在DatePicker中通过使用其setShowWeekNumbers方法。
     */
    public static class Main3 extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            String pattern = "yyyy-MM-dd";
            VBox vbox = new VBox(20);
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);
            DatePicker checkInDatePicker = new DatePicker();
            StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

                @Override
                public String toString(LocalDate date) {
                    if (date != null) {
                        return dateFormatter.format(date);
                    } else {
                        return "";
                    }
                }

                @Override
                public LocalDate fromString(String string) {
                    if (string != null && !string.isEmpty()) {
                        return LocalDate.parse(string, dateFormatter);
                    } else {
                        return null;
                    }
                }
            };
            checkInDatePicker.setConverter(converter);
            checkInDatePicker.setPromptText(pattern.toLowerCase());

            vbox.getChildren().add(checkInDatePicker);
            checkInDatePicker.requestFocus();
            stage.show();
        }
    }

    /**
     * DateCell
     *
     * 默认情况下，日历元素中的所有单元格都可供选择。我们可以使用日单元工厂禁用单元格。
     */
    public static class Main4 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            VBox vbox = new VBox(20);
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);
            DatePicker startDatePicker = new DatePicker();
            DatePicker endDatePicker = new DatePicker();
            startDatePicker.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item.isBefore(startDatePicker.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #EEEEEE;");
                            }
                        }
                    };
                }
            };
            endDatePicker.setDayCellFactory(dayCellFactory);
            endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
            vbox.getChildren().add(new Label("Start Date:"));
            vbox.getChildren().add(startDatePicker);
            vbox.getChildren().add(new Label("End Date:"));
            vbox.getChildren().add(endDatePicker);
            stage.show();
        }
    }

    /**为每个日期单元格安装工具提示。*/
    public static class Main5 extends Application {
        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage stage) {
            VBox vbox = new VBox(20);
            Scene scene = new Scene(vbox, 400, 400);
            stage.setScene(scene);
            final DatePicker startDatePicker = new DatePicker();
            DatePicker endDatePicker = new DatePicker();
            startDatePicker.setValue(LocalDate.now());
            final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);

                            long p = ChronoUnit.DAYS.between(startDatePicker.getValue(), item);
                            setTooltip(new Tooltip("You re about to stay for " + p + " days"));
                        }
                    };
                }
            };
            endDatePicker.setDayCellFactory(dayCellFactory);
            endDatePicker.setValue(startDatePicker.getValue().plusDays(1));
            vbox.getChildren().add(new Label("Start Date:"));
            vbox.getChildren().add(startDatePicker);
            vbox.getChildren().add(new Label("End Date:"));
            vbox.getChildren().add(endDatePicker);
            stage.show();
        }
    }
}
