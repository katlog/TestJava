package org.person.dfw.fx.practice.ui.widget;

import javafx.scene.control.PasswordField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Test;

public class TooltipMain {

    /**
     * 当鼠标光标悬停在控件上时，将出现工具提示。
     * 工具提示用于显示有关UI控件的其他信息。
     * 我们可以通过调用setTooltip方法来安装工具提示。
     * 工具提示有两种不同的状态:激活和显示。在激活状态和显示状态之间存在一些延迟。
     */
    /**
     * 创建工具提示
     *
     * 以下代码将工具提示安装到密码字段。它调用默认构造函数的Tooltip类。
     */
    @Test
    public void create() {
        PasswordField pf = new PasswordField();
        Tooltip tooltip = new Tooltip();
        tooltip.setText("info");
        pf.setTooltip(tooltip);
    }

    /**
     * javafx.scene.control 包中的每个UI控件都有setTooltip方法。
     * 我们还可以通过使用Tooltip构造函数传递一个文本标题来创建一个Tooltip对象。
     * Tooltip类扩展 Labeled 类也可以有一个文本标题作为图形图标。
     * 以下代码会在工具提示中安装一个图标。
     */
    @Test
    public void test() {
        // Image image = new Image(getClass().getResourceAsStream("warn.png"));
        // tooltip.setGraphic(new ImageView(image));
    }
}
