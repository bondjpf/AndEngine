#include "BufferUtils.h"
#include <stdio.h>
#include <stdlib.h>

// ===========================================================
// org.anddev.andengine.opengl.util.BufferUtils
// ===========================================================
	
JNIEXPORT void JNICALL Java_org_andengine_opengl_util_BufferUtils_jniPut(JNIEnv *env, jclass, jobject pBuffer, jfloatArray pData, jint pLength, jint pOffset) {
	float* dataAddress = (float*)env->GetPrimitiveArrayCritical(pData, 0);
	float* bufferAddress = (float*)env->GetDirectBufferAddress(pBuffer);
	
	memcpy(bufferAddress, dataAddress + pOffset, pLength << 2);
	env->ReleasePrimitiveArrayCritical(pData, dataAddress, 0);
}

JNIEXPORT jobject JNICALL Java_org_andengine_opengl_util_BufferUtils_jniAllocateDirect(JNIEnv *env, jclass, jint pCapacity) {
	jbyte* bytebuffer = (jbyte*) malloc(sizeof(jbyte) * pCapacity);
	return env->NewDirectByteBuffer(bytebuffer, pCapacity);
} 