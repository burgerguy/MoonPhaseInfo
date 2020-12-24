package com.paradoxical.moonphaseinfo;

import com.github.burgerguy.hudtweaks.api.MatrixUpdater;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

public class PhaseIcon extends DrawableHelper {
    private static final Identifier INVENTORY = new Identifier("textures/gui/container/inventory.png");
    private static final Identifier ICONS = new Identifier(MoonPhaseInfoMod.MOD_ID, "textures/environment/moon_phases_icons.png");
    public static final MatrixUpdater MATRIX_UPDATER = new MatrixUpdater();

    private static final Formatting[] FULLNESS_COLOR = new Formatting[]
            {Formatting.RED, Formatting.GOLD, Formatting.YELLOW, Formatting.GREEN, Formatting.DARK_GREEN};

    void drawPhaseIcon(MatrixStack matricies) {
    	MATRIX_UPDATER.onStartRender(matricies);
    	
        MinecraftClient mc = MinecraftClient.getInstance();
        int windowWidth = mc.getWindow().getScaledWidth();

        mc.getTextureManager().bindTexture(INVENTORY);
        int a = 24;
        int x = windowWidth / 2 - a - 1;
        int y = 1;
        drawTexture(matricies, x, y, 141, 166, a, a);

        RenderSystem.enableBlend();
        mc.getTextureManager().bindTexture(ICONS);

        int a2 = 18;
        int d = a / 2 - a2 / 2;
        x += d;
        y += d;

        int phase = mc.world.getMoonPhase();
        int u = ((4 - phase % 4) % 4) * a2; //{0, 3, 2, 1, 0, 3, 2, 1}[phase] * side length
        int v = phase >= 1 && phase <= 4 ? a2 : 0;
        drawTexture(matricies, x, y, u, v, a2, a2, 72, 36);
        RenderSystem.disableBlend();

        //fullness
        int sizePercent = (int)(mc.world.getMoonSize() * 100f);
        mc.textRenderer.drawWithShadow(matricies,
        		String.format("%s%d%%%s",
                FULLNESS_COLOR[sizePercent / 25],
                sizePercent,
                phase <= 3 ? Formatting.RED + "↓" : Formatting.DARK_GREEN + "↑"),
                windowWidth / 2 + 1, 5, 0xffffff);

        //time left
        long daySecondsLeft = (24000 - mc.world.getTimeOfDay() % 24000) / 20;
        long dayMinutesLeft = daySecondsLeft / 60;
        boolean atLeast60s = daySecondsLeft >= 60;
        mc.textRenderer.drawWithShadow(matricies, atLeast60s ? dayMinutesLeft + "min" : daySecondsLeft + "s",
                windowWidth / 2 + 1, 5 + mc.textRenderer.fontHeight, Formatting.DARK_AQUA.getColorValue());
        
        MATRIX_UPDATER.onEndRender(matricies);
    }
}
