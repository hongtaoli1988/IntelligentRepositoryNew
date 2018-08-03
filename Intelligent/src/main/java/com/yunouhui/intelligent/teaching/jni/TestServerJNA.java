package com.yunouhui.intelligent.teaching.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface TestServerJNA extends Library {
	public abstract void startPipeServer();
	public abstract void sendCmd(int type);
	TestServerJNA INSTANCE = (TestServerJNA)Native.loadLibrary("pipeServer", TestServerJNA.class);
}
