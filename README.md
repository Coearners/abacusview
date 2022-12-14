
# AbacusView

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

<p align="center">
  <img width="300px" height="267px" src="https://user-images.githubusercontent.com/65446143/187054125-1367669a-6eef-4a9b-8107-9e24a844d59e.png" />
</p>


Easy abacus control to add in Android Studio Projects.
The AbacusView provides you prebuilt basic Abacus in your projects
wherever you need.


## Demo
<p align="center">
   <img src="https://user-images.githubusercontent.com/65446143/187054278-ab206378-d308-49a9-ac30-2c91f1b42ecf.gif" width="200px" height="300px"/>
</p>


## Installation

To start using AbacusView in your project, just add it to dependecy
and add `com.coearners.android:AbacusView:1.0.0`

### Apache Maven
```xml
<dependency>
  <groupId>com.coearners.android</groupId>
  <artifactId>AbacusView</artifactId>
  <version>1.0.0</version>
  <type>aar</type>
</dependency>
```

### Gradle/Groovy DSL
```gradle
  dependencies{
  ..
    implementation 'com.coearners.android:AbacusView:1.0.0'
  ..
  }
```
    
## Documentation

[Documentation and API Reference](https://github.com/Coearners/abacusview/blob/master/README.md)


## Maven Repository
[Maven Repository Link](https://search.maven.org/artifact/com.coearners.android/AbacusView/1.0.0/aar)

## Usage/Examples

### Adding AbacusView to your layout.
Setting `layout_width` and `layout_height` to `wrap_content` automatically handles 
the width of the width and height of AbacusView according to screen size.   
The changes made in `layout_height` property doesn't change the scale of the controls.
The default and fixed height for AbacusView is `200sp`.
```xml
<com.coearners.android.abacusview.AbacusView
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"/>
```

### Getting the value of AbacusView
```java
AbacusView abacusView = findViewById(R.id.abacusView);
// Get the value of abacus.
...
/*
getValue() returns the value of the abacus to maximum  
3 digits of precision.
*/
double value = abacusView.getValue();
...
```
### Getting truncated string value of AbacusView
```java
// Getting string value.
...
/*
getStringValue() returns the value in string data type.   
It also takes care of integral values sent by   
getValue() value function and reduces the precision   
i.e. it truncates the decimal points if not required.
e.g. 
    For value = 1.212
        getValue() returns 1.212 as double  
        getStringValue() returns 1.212 as string
    For value = 1.000
        getValue() returns 1.0 as double
        getStringValue() retunrs 1 as string
*/
String value = abacusView.getStringValue();
...
```
### Resetting the AbacusView
```java
// Resetting the abacus
...
/*
reset() function can be used to reset the beads of the abacus  
to their default positions
*/
abacusView.reset();
...
```

## License
This application licensed under the terms of the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.txt):  
http://www.apache.org/licenses/LICENSE-2.0.txt
