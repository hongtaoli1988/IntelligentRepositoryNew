package com.yunouhui.intelligent.teaching.jni;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface TestClientJNA extends Library {
	public abstract int startPipeConnect();
	TestClientJNA INSTANCE = (TestClientJNA)Native.loadLibrary("pipeClient", TestClientJNA.class);
}
