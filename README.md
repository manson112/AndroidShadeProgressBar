# AndroidShadeProgressBar
[![](https://jitpack.io/v/manson112/AndroidShadeProgressBar.svg)](https://jitpack.io/#manson112/AndroidShadeProgressBar)
A Android Custom ProgressBar

# GIF

# Setup
```Gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
        implementation 'com.github.manson112:AndroidShadeProgressBar:1.0.1'
}
```

# Usage
- app:src is necessary. (drawable) 
```xml
 <me.manson112.custom.shadeprogressbar.ShadeProgressBar
      android:id="@+id/fill_image"
      android:layout_width="100dp"
      android:layout_height="100dp"
      app:sideCornerRadius="120dp"
      app:shadeRadius="200"
      app:src="@drawable/background"
      app:backgroundColor="#000000"
      app:shadeColor="#FFFFFF"
      app:percentage="70"
      app:duration="900"
      app:itemSize="100dp"
      app:shadeSize="10dp"
      app:layout_constraintStart_toStartOf="parent"
      app:layout_constraintEnd_toEndOf="parent"
      app:layout_constraintTop_toTopOf="parent"
      app:layout_constraintBottom_toBottomOf="parent"
      />
```
# ScreenShot

