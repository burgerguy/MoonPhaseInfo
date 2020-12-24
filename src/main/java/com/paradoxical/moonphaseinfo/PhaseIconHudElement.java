package com.paradoxical.moonphaseinfo;

import com.github.burgerguy.hudtweaks.api.CustomHudElement;
import com.github.burgerguy.hudtweaks.api.MatrixUpdater;

import net.minecraft.client.MinecraftClient;

public class PhaseIconHudElement extends CustomHudElement {

	public PhaseIconHudElement(MatrixUpdater updater) {
		super("moonphase", updater);
	}

	@Override
	public int calculateWidth(MinecraftClient client) {
		return 10;
	}

	@Override
	public int calculateHeight(MinecraftClient client) {
		return 10;
	}

	@Override
	public int calculateDefaultX(MinecraftClient client) {
		return 0;
	}

	@Override
	public int calculateDefaultY(MinecraftClient client) {
		return 0;
	}
	
}
