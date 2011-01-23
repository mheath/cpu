#!/usr/bin/make -f
#
# Makefile for C code
#

# C sources to compile

TARGET_DIR := ../../../target
TARGET := $(TARGET_DIR)/libcpu.so

WORKING_DIR := $(TARGET_DIR)/jni

JAVA_BUILD_DIR := $(TARGET_DIR)/classes

JAVA_HOME ?= /opt/java/jdk
JVM_SHARED_LIB := $(JAVA_HOME)/jre/lib/amd64/server

INCLUDES := -I $(JAVA_HOME)/include -I $(JAVA_HOME)/include/linux -I $(WORKING_DIR)

# classpath for javah
ifdef CLASSPATH
JAVAH_CLASSPATH = $(JAVA_BUILD_DIR):$(CLASSPATH)
else
JAVAH_CLASSPATH = $(JAVA_BUILD_DIR)
endif

all: $(TARGET)

$(TARGET): net_toadhead_cpu_Cpu.c $(WORKING_DIR)/net_toadhead_cpu_Cpu.h
	gcc -O2 -shared -fPIC -L$(JVM_SHARED_LIB) -ljvm -lrt $(INCLUDES) net_toadhead_cpu_Cpu.c -o $(TARGET)

$(WORKING_DIR)/net_toadhead_cpu_Cpu.h: $(TARGET_DIR)/classes/net/toadhead/cpu/Cpu.class
	mkdir -p $(TARGET_DIR)/jni
	javah -force -classpath $(JAVAH_CLASSPATH) -d $(WORKING_DIR) net.toadhead.cpu.Cpu
