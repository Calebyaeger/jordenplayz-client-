
package com.jordenplayz.client;

import com.jordenplayz.client.module.Module;
import com.jordenplayz.client.module.Modules;

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

    private static final int GUI_WIDTH = 520;
    private static final int GUI_HEIGHT = 300;
    private static final int PANEL_WIDTH = 94;
    private static final int PANEL_HEIGHT = 220;

    public ClickGuiScreen() {
        super(Text.literal("JordenPlayz Click GUI"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, width, height, 0x99000000);

        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;

        context.fill(x, y, x + GUI_WIDTH, y + GUI_HEIGHT, BACKGROUND);
        context.fill(x, y, x + GUI_WIDTH, y + 3, PURPLE);

        context.drawTextWithShadow(
            textRenderer, "JORDENPLAYZ CLIENT",
            x + 16, y + 14, PURPLE
        );

        context.drawTextWithShadow(
            textRenderer, "1.21.11 | Fabric | v0.2.0",
            x + 16, y + 30, GRAY
        );

        Module.Category[] categories = Module.Category.values();

        int panelY = y + 60;

        for (int i = 0; i < categories.length; i++) {
            int panelX = x + 12 + (i * 100);

            context.fill(
                panelX, panelY,
                panelX + PANEL_WIDTH, panelY + PANEL_HEIGHT,
                PANEL
            );

            context.fill(
                panelX, panelY,
                panelX + PANEL_WIDTH, panelY + 2,
                i % 2 == 0 ? PURPLE : BLUE
            );

            String categoryName = switch (categories[i]) {
                case COMBAT -> "Combat";
                case MOVEMENT -> "Movement";
                case RENDER -> "Render";
                case PLAYER -> "Player";
                case MISCELLANEOUS -> "Misc";
            };

            context.drawCenteredTextWithShadow(
                textRenderer,
                categoryName,
                panelX + PANEL_WIDTH / 2,
                panelY + 12,
                WHITE
            );

            var modules = Modules.MANAGER.getModulesByCategory(categories[i]);

            int moduleY = panelY + 34;

            for (Module module : modules) {
                int buttonColor = module.isEnabled() ? BLUE : 0xFF292938;
                int textColor = module.isEnabled() ? WHITE : GRAY;

                context.fill(
                    panelX + 6,
                    moduleY,
                    panelX + PANEL_WIDTH - 6,
                    moduleY + 18,
                    buttonColor
                );

                context.drawCenteredTextWithShadow(
                    textRenderer,
                    module.getName(),
                    panelX + PANEL_WIDTH / 2,
                    moduleY + 5,
                    textColor
                );

                moduleY += 22;
            }
        }

        context.drawTextWithShadow(
            textRenderer,
            "Right Shift: Toggle GUI",
            x + 16,
            y + GUI_HEIGHT - 18,
            BLUE
        );

        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button != 0) {
            return super.mouseClicked(mouseX, mouseY, button);
        }

        int x = (width - GUI_WIDTH) / 2;
        int y = (height - GUI_HEIGHT) / 2;
        int panelY = y + 60;

        Module.Category[] categories = Module.Category.values();

        for (int i = 0; i < categories.length; i++) {
            int panelX = x + 12 + (i * 100);

            var modules = Modules.MANAGER.getModulesByCategory(categories[i]);

            int moduleY = panelY + 34;

            for (Module module : modules) {
                boolean insideButton =
                    mouseX >= panelX + 6 &&
                    mouseX <= panelX + PANEL_WIDTH - 6 &&
                    mouseY >= moduleY &&
                    mouseY <= moduleY + 18;

                if (insideButton) {
                    module.toggle();
                    return true;
                }

                moduleY += 22;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}
