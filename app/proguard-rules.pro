# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

# Optimizations: If you don't want to optimize, use the
# proguard-android.txt configuration file instead of this one, which
# turns off the optimization flags.  Adding optimization introduces
# certain risks, since for example not all optimizations performed by
# ProGuard works on all versions of Dalvik.  The following flags turn
# off various optimizations known to have issues, but the list may not
# be complete or up to date. (The "arithmetic" optimization can be
# used if you are only targeting Android 2.0 or later.)  Make sure you
# test thoroughly if you go this route.

# Copy for android SDK Tools v26.0.2 proguard
# Solve android support related code proguard class reference exception
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify

-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose

-dontskipnonpubliclibraryclassmembers
-keep,allowobfuscation @interface androidx.annotation.Keep

-keep @androidx.annotation.Keep class *
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

-ignorewarnings

-dontwarn org.slf4j.**
-dontwarn com.google.appengine.api.**
-dontwarn okio.**
-dontwarn org.apache.**
-dontwarn retrofit.android.**
-dontwarn retrofit.appengine.**
-dontwarn retrofit.client.**
-dontwarn rx.**
-dontwarn com.fasterxml.jackson.**


