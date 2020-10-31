# Red Screen Of Death

## What

A simple screen that is shown when your app gets crashed instead of the normal crash dialog.

It's very similar to the one in Flutter/React Native.



<img src="https://github.com/rajatkdhamija/RedScreenOfDeath/blob/main/error.gif" width="35%">


## Install

`RedScreenOfDeath` is distributed via `jitkpack.io`.
Add the dependencies to your `build.gradle` file.

In your  `build.gradle`:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.rajatkdhamija:RedScreenOfDeath:1.4'
}
```

In your  `Application`  class:

- Kotlin
```kotlin
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RedScreenOfDeath.initRSOD(this, BuildConfig.BUILD_TYPE)
    }
}
```

- Java
```java
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RedScreenOfDeath.initRSOD(this, BuildConfig.BUILD_TYPE);
    }
}
```

And you are done!

## Disclaimer
This will only work in debug build, to make it work on release app pass "release" as second parameter in initRSOD