package com.remotegetaway.sakurarosea.config;

public class SakuraRoseaGeneralConfig {
	private boolean quarterLogs = true;
	private boolean oceanVolcanoes = true;

	public boolean areQuarterLogsEnabled() {
		return quarterLogs;
	}

	public boolean areOceanVolcanoesEnabled() {
		return oceanVolcanoes;
	}
}
