//*********************************************************************
//Copyright 2010 Intellectual Reserve, Inc. All rights reserved.
//This notice may not be removed.
//*********************************************************************
package net.toadhead.cpu;

import java.util.Map;

/**
 * @author Mike Heath <heathma@ldschurch.org>
 */
public class CpuLocal<T> {

	public CpuLocal() {
	}

	protected T initialValue() {
	    return null;
	}

	public T get() {
		Cpu c = Cpu.getCurrentCpu();
		Map<CpuLocal,Object> cpuLocals = c.cpuLocals;
		synchronized (cpuLocals) {
			T value = (T) cpuLocals.get(this);
			if (value == null) {
				value = initialValue();
				cpuLocals.put(this, value);
			}
			return value;
		}
	}

	public void set(T value) {
		Cpu c = Cpu.getCurrentCpu();
		Map<CpuLocal, Object> cpuLocals = c.cpuLocals;
		synchronized (cpuLocals) {
			cpuLocals.put(this, value);
		}
    }

	public void remove() {
		Cpu c= Cpu.getCurrentCpu();
		Map<CpuLocal, Object> cpuLocals = c.cpuLocals;
		synchronized (cpuLocals) {
			cpuLocals.remove(this);
		}
	}
}
