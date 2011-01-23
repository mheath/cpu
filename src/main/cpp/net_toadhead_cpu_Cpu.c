#define _GNU_SOURCE
#include <jni.h>
#include <utmpx.h>
#include <sched.h>

#include "net_toadhead_cpu_Cpu.h"

JNIEXPORT jint JNICALL Java_net_toadhead_cpu_Cpu_getCurrentCpuId0
  (JNIEnv *env, jclass class)
{
	return (jint)sched_getcpu();
}

JNIEXPORT jint JNICALL Java_net_toadhead_cpu_Cpu_setCurrentThreadAffinity0
  (JNIEnv *env, jclass class, jint cpu)
{
	cpu_set_t mask;
	CPU_ZERO(&mask);
	CPU_SET(cpu, &mask);
	return sched_setaffinity(0, sizeof(mask), &mask);
}