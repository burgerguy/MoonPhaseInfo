package com.paradoxical.moonphaseinfo;

import java.util.Collection;
import java.util.Collections;

import com.github.burgerguy.hudtweaks.api.CustomHudElement;
import com.github.burgerguy.hudtweaks.api.HudTweaksApi;

public class HudTweaksApiImpl implements HudTweaksApi {

	@Override
	public Collection<CustomHudElement> getCustomElements() {
		return Collections.singleton(new PhaseIconHudElement(PhaseIcon.MATRIX_UPDATER));
	}
	
}
