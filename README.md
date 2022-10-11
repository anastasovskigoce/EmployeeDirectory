## Build tools & versions used
Version 1.0 of an Employee Directory app. The built system used is Gradle. The minSDK needed to run the app is 21 and the target SDK is 32. The URL that the app is using to retrieve the employee data was moved behind a build variable called API_BASE_URL. I did this in case we want to modify the URL in the future based on the different flavors of the app (develop environment, production environment....)

## Steps to run the app
There are no special steps needed to run the app other than needing a phone that is on at least Android Lollipop.

## What areas of the app did you focus on?
Most of my time was spent on clean architecture. I followed [Google's guide to app architecture](https://developer.android.com/topic/architecture) to create a Data and a Presentation/UI layer. The use case here was rather simple, thus I decided to not overengineer the app and did not create a Domain layer. Sometimes less code is better, or as developers jokingly say less code = less bugs and no code = no bugs :) Per latest Google recommendations, the app uses Coroutines and Flows as opposed to Threads (or RxJava) and LiveData. 

I wanted to set strong foundations for scaling and unit testing, thus I used Hilt as my DI framework. With Hilt taking care of keeping my dependencies separate I wrote unit tests for the view model. Mockito Kotlin was used for mocking in unit tests. With scaling in mind, I assumed the app will introduce more screens with time, therefore I used the Jetpack navigation component to handle navigation. For networking, I used Squares Retrofit and Moshi libraries. The data layer is encapsulated using the repository pattern.

Finally, the requirements asked for a loading screen and handling various error states (no employees, malformed response, HTTP 404). I used the [Facebook's shimmer library](https://facebook.github.io/shimmer-android/) to handle the loading states. The images were cached on disk using [Instacart's Coil image library](https://www.instacart.com/company/how-its-made/introducing-coil-kotlin-first-image-loading-on-android/). A picture of Homer Simpson was used as a placeholder image and Homer Simpson is used as a mascot of the app. See images below for a preview oh how the app looks in each state.

<img src="https://github.com/anastasovskigoce/EmployeeDirectory/blob/main/Loading.png" width="200">
<img src="https://github.com/anastasovskigoce/EmployeeDirectory/blob/main/Normal%20State.png" width="200">
<img src="https://github.com/anastasovskigoce/EmployeeDirectory/blob/main/Empty.png" width="200">
<img src="https://github.com/anastasovskigoce/EmployeeDirectory/blob/main/Error.png" width="200">
<img src="https://github.com/anastasovskigoce/EmployeeDirectory/blob/main/Placeholder%20Image.png" width="200">

## What was the reason for your focus? What problems were you trying to solve?
One of the reasons why I love working with the Android system is the fact that the Android system never stopped evolving. It is true that at times it feels like you have to run in order to stay in one place, but the constantly changing Android framework and how we write apps is what keeps the Android getting better. I focused on Hilt, Coroutines and Architecture because my goal was to create an efficient app that can be easily tested and scaled as the number of features and developers grow.

## How long did you spend on this project?
I spent 6-7 hours in total.

## Did you make any trade-offs for this project? What would you have done differently with more time?
If I had more time, I'd have introduced a Domain layer with use cases. I think the Domain layer with use cases would fit nicely with to code reuse i.e. we have a button that refreshes the screen. It might be too early to use Jetpack Compose in a production app since it is still evolving.  Finally, if I had more time I would have modularized the app so that the app builds faster due to gradle caching builds. Modularization would also help with team growth and ownership.

## What do you think is the weakest part of your project?
I think the units test could use a bit more love. I am new to unit testing using coroutines, thus I did not have time to write a unit test that determines if the "Loading" state shows when it is supposed to show.

## Did you copy any code or dependencies? Please make sure to attribute them here!
The code was 100% written by me, nothing was copied.

## Is there any other information you’d like us to know?
I enjoyed working on this project!
