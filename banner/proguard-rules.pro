# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\android\android-sdk-windows/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn com.youth.banner.**
-keep class com.youth.banner.** {*;}

-dontwarn androidx.viewpager2.**
-keep androidx.viewpager2.** {*;}
-dontwarn androidx.recyclerview.widget.RecyclerView
-keep class androidx.recyclerview.widget.RecyclerView{*;}