Hey there 👋🏼👋🏼👋🏼

This project contains an implementation of the Componentization idea as shown by the UI Engineering team at Netflix.

Resources: [blog](https://netflixtechblog.com/making-our-android-studio-apps-reactive-with-ui-components-redux-5e37aac3b244), [repo](https://github.com/julianomoraes/componentizationArch), [talk](https://www.droidcon.com/media-detail?video=362740979)

## Features
* Clean Architecture with MVI (Uni-directional data flow)
* View components
* Kotlin Coroutines with Flow
* Dagger Hilt
* GitHub actions for CI
* Light/Dark Mode Support

### OUTSTANDING TASK AND IMPROVEMENTS
- Increase Unit tests around current use of Mockwebserver
- More Unit tests
- Better Search view options
- Better arrangement of information current recyclerview
- Finishing adding PullToRefresh
- More robust regex testing
- Make a better UI

## Prerequisite
To build this project, you require:
- Android Studio Bumble bee
- Gradle 7.0.2

Run the following command in the root of the project to setup your Android Studio:
```
./setup.sh
```
This script will configure [ktlint](https://github.com/shyiko/ktlint)

<h2 align="left">Screenshots</h2>
<h4 align="center">
<img src="https://res.cloudinary.com/mathemandy/image/upload/v1626190805/Assesment/1-dark.png" width="30%" vspace="10" hspace="10">
<img src="https://res.cloudinary.com/mathemandy/image/upload/v1626190838/Assesment/2dark.png" width="30%" vspace="10" hspace="10">
<img src="https://res.cloudinary.com/mathemandy/image/upload/v1626190854/Assesment/3dark.png" width="30%" vspace="10" hspace="10">
<img src="https://res.cloudinary.com/mathemandy/image/upload/v1626190849/Assesment/2light.png" width="30%" vspace="10" hspace="10">
<img src="https://res.cloudinary.com/mathemandy/image/upload/v1626190849/Assesment/3Light.png" width="30%" vspace="10" hspace="10"><br>

## Libraries

- [Viewmodel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Presenter for persisting view state across config changes
- [FlowBinding](https://github.com/ReactiveCircus/FlowBinding) - converts traditional view click listeners and call backs to Kotlin flow
- [Room](https://developer.android.com/training/data-storage/room) - Provides abstraction layer over SQLite
- [Retrofit](https://square.github.io/retrofit/) - type safe http client and supports coroutines out of the box.  
- [Moshi](https://github.com/square/moshi) - JSON Parser,used to parse requests on the data layer for Entities and understands Kotlin non-nullable and default parameters
- [okhttp-logging-interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - logs HTTP request and response data.
- [kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines,provides `runBlocking` coroutine builder used in tests
- [Truth](https://truth.dev/) - Assertions Library,provides readability as far as assertions are concerned
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) - web server for testing HTTP clients ,verify requests and responses on the cats api with the retrofit client.
- [Robolectric](http://robolectric.org/) - Unit test on android framework.
- [Espresso](https://developer.android.com/training/testing/espresso) - Test framework to write UI Tests
- [Dagger Hilt](https://dagger.dev/hilt) - handles dependency injection
