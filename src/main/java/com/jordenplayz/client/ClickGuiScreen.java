
package com.jordenplayz.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ClickGuiScreen extends Screen {

    private static final int BACKGROUND = 0xF012121C;
    private static final int PANEL = 0xFF1B1B2A;
    private static final int PURPLE = 0xFF9B59FF;
    private static final int BLUE = 0xFF438CFF;
    private static final int WHITE = 0xFFFFFFFF;
    private static final int GRAY = 0xFFAAAAAA;

    public ClickGuiScreen() {
        super(Text.literal("JordenPlayz Click GUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY,
                       float delta) {

        context.fill(0, 0, width, height, 0x99000000);

        int guiWidth = 520;
        int guiHeight = 300;

        int x = (width - guiWidth) / 2;
        int y = (height - guiHeight) / 2;

        context.fill(x, y, x + guiWidth, y + guiHeight, BACKGROUND);

        context.fill(x, y, x + guiWidth, y + 3, PURPLE);

        context.drawTextWithShadow(
                textRenderer,
                "JORDENPLAYZ CLIENT",
                x + 16,
                y + 14,
                PURPLE
        );

        context.drawTextWithShadow(
                textRenderer,
                "1.21.11 | Fabric | v0.2.0",
                x + 16,
                y + 30,
                GRAY
        );

        String[] categories = {
                "Combat",
                "Movement",
                "Render",
                "Player",
                "Miscellaneous"
        };

        int panelWidth = 94;
        int panelHeight = 220;
        int panelY = y + 60;

        for (int i = 0; i < categories.length; i++) {

            int panelX = x + 12 + (i * 100);

            context.fill(
                    panelX,
                    panelY,
                    panelX + panelWidth,
                    panelY + panelHeight,
                    PANEL
            );

            context.fill(
                    panelX,
                    panelY,
                    panelX + panelWidth,
                    panelY + 2,
                    i % 2 == 0 ? PURPLE : BLUE
            );

            context.drawCenteredTextWithShadow(
                    textRenderer,
                    categories[i],
                    panelX + panelWidth / 2,
                    panelY + 12,
                    WHITE
            );

            context.drawCenteredTextWithShadow(
                    textRenderer,
                    "Coming Soon",
                    panelX + panelWidth / 2,
                    panelY + 60,
                    GRAY
            );
        }

        context.drawTextWithShadow(
                textRenderer,
                "Right Shift: Toggle GUI",
                x + 16,
                y + guiHeight - 18,
                BLUE
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
