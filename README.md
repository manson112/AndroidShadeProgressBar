# AndroidShadeProgressBar
[![](https://jitpack.io/v/manson112/AndroidShadeProgressBar.svg)](https://jitpack.io/#manson112/AndroidShadeProgressBar)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Mozilla
A Android Custom ProgressBar

## GIF
- Filling

![Filling Animation](https://github.com/manson112/AndroidShadeProgressBar/blob/master/gif/anim_fill.gif)

- Turning

![Turning Animation](https://github.com/manson112/AndroidShadeProgressBar/blob/master/gif/anim_progress.gif)
## Setup
```Gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
dependencies {
        implementation 'com.github.manson112:AndroidShadeProgressBar:x.y.z'
}
```

## Usage
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
## ScreenShot

## License

```
MIT License

Copyright (c) 2019 İbrahim Süren

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
