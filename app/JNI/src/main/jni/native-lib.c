//
// Created by mac on 2019-11-27.
//

//下边的引用标签一定是.h的文件名家后缀，方法名一定要和.h文件中的方法名称一样
//以下两种方式


//方式一
#include"com_example_jni_JNIUtils.h"

JNIEXPORT jstring JNICALL Java_com_example_jni_JNIUtils_stringFromJNI
(JNIEnv *env, jobject ojb){

return (*env) -> NewStringUTF(env, "Hello, I'm from jni");

}


//方式二
//#include <jni.h>
//
//#ifndef _Included_com_example_ndkdemo_JNIUtils
//#define _Included_com_example_ndkdemo_JNIUtils
//#ifdef __cplusplus
//extern "C" {
//#endif
///*
// * Class:     com_example_ndkdemo_JNIUtils
// * Method:    stringFromJNI
// * Signature: ()Ljava/lang/String;
// */
//JNIEXPORT jstring JNICALL Java_com_example_ndkdemo_JNIUtils_stringFromJNI
//(JNIEnv *env, jobject ojb){
//
//return (*env) -> NewStringUTF(env,"Hello, I'm from jni");
//}
//
//#ifdef __cplusplus
//}
//#endif
//#endif

