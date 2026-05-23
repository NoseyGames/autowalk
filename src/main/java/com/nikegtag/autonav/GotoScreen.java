package com.nikegtag.autonav.gui;

import com.nikegtag.autonav.NavigationManager;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class GotoScreen extends Screen {

    private TextFieldWidget xField;
    private TextFieldWidget yField;
    private TextFieldWidget zField;

    public GotoScreen() {
        super(Text.literal("AutoNav - Go To"));
    }

    @Override
    protected void init() {

        int centerX = this.width / 2;
        int centerY = this.height / 2;

        xField = new TextFieldWidget(textRenderer, centerX - 100, centerY - 60, 200, 20, Text.literal("X"));
        yField = new TextFieldWidget(textRenderer, centerX - 100, centerY - 30, 200, 20, Text.literal("Y"));
        zField = new TextFieldWidget(textRenderer, centerX - 100, centerY, 200, 20, Text.literal("Z"));

        addDrawableChild(xField);
        addDrawableChild(yField);
        addDrawableChild(zField);

        addDrawableChild(
            ButtonWidget.builder(Text.literal("Start"), button -> {

                try {
                    double x = Double.parseDouble(xField.getText());
                    double y = Double.parseDouble(yField.getText());
                    double z = Double.parseDouble(zField.getText());

                    NavigationManager.setTarget(x, y, z);

                    this.close();

                } catch (NumberFormatException e) {
                    // invalid input, just ignore for now
                }

            }).dimensions(centerX - 50, centerY + 40, 100, 20).build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
