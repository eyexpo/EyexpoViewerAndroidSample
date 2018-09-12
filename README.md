# Eyexpo Android VR SDK

This is a code sample of how to using Eyexpo Android VR SDK

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- This SDK only supports Android 4.1 (API Level 16) and up
- Kotlin plugin is required in Android studio

### Import SDK

1. This SDK is written in Kotlin. To ensure Android studio can recognize Kotlin code.
Go to Tool → Kotlin → Configure Kotlin in Project. When prompted, select "All Modules".

2. Download eyexpo360-v1.0.0.aar and move it to <project root>/app/libs/. If libs folder does not exist, create one

3. In project level build.gradle, add the following:


```
allprojects {
    repositories {
        ...
        flatDir {
            dirs "libs"
        }
    }
}
```

4. In app level build.gradle, add
```
dependencies {
    ...
    implementation (name:'eyexpovr', ext:'aar')
}
```

### Use SDK

1. Add widget to desired layout location
```
<com.eyexpo.vr.PanoramaWidget
    android:id="@+id/pano_widget"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

6. In activity or view holder, use the following to get the panorama widget
```
val widget: PanoramaWidget = view.findViewById(R.id.pano_widget)
```

7. Then, the height and width of the widget can be adjusted using the following:
```
widget.width = "200dp"
widget.height = "200dp"
```

8. OnClick listener can be set using the following:
```
widget.setOnClickListener {
    ...
}
```

9. To load a panorama image, use:
```
widget.setImageBitmap(bitmap)
```

10. For different lifecycle events, call the widget's respective lifecycle event methods:
```
override fun onResume() {
    super.onResume()
    widget?.onResume()
}

override fun onPause() {
    widget?.onPause()
    super.onPause()
}

override fun onDestroy() {
    widget?.onDestroy()
    super.onDestroy()
}
```


## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Authors

* **[Ashton Chen](https://github.com/ashton-d-chen)**

