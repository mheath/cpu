//*********************************************************************
//Copyright 2010 Intellectual Reserve, Inc. All rights reserved.
//This notice may not be removed.
//*********************************************************************
package net.toadhead.cpu;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author Mike Heath <heathma@ldschurch.org>
 */
public class Cpu {

	private static final Cpu[] cpus;

	static {
		System.loadLibrary("cpu");

		int cpuCount = Runtime.getRuntime().availableProcessors();
		cpus = new Cpu[cpuCount];
		for (int i = 0; i < cpuCount; i++) {
			cpus[i] = new Cpu(i);
		}
	}

	public static Cpu getCurrentCpu() {
		return cpus[getCurrentCpuId()];
	}

	public static Cpu getCpu(int id) {
		return cpus[id];
	}

	private static native int getCurrentCpuId0();

	private static int getCurrentCpuId() {
		int id = getCurrentCpuId0();
		if (id >= 0) {
			return id;
		}
		throw new Error("This Linux kernel does not implement getcpu(2)");
	}

	private static native int setCurrentThreadAffinity0(int cpu);

	private static void setCurrentThreadAffinity(int cpu) {
		if (setCurrentThreadAffinity0(cpu) < 0) {
			throw new Error("Unable to set CPU affinity");
		}
	}

	private final int id;

	final Map<CpuLocal, Object> cpuLocals = new IdentityHashMap<CpuLocal, Object>();

	private Cpu(int id) {
		this.id = id;
	}

	public void setCurrentThreadAffinity() {
		setCurrentThreadAffinity(id);
	}

	@Override
	public String toString() {
		return "CPU: " + id;
	}

}
