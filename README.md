# MyFirstAndroidApp
My first Android App - Upcoming Movies

This is my first Android App. 
It's a application that connects to The Movie Database (TMDb) and show a list of upcoming movies on a list.

## 3rd Party Frameworks.
To connect to TMdb I have chosen to use Retrofit - http://square.github.io/retrofit/
I chose to use Retrofit 1.9.0 because I already had some code using this version.
Retrofit is a very useful library to be used as Rest Client.

## Dev Tool
Android  Studio

##  To build
1) set ANDROID_HOME
2) ./gradlew build


## Dificulties as Android Newbie (but senior 15+ years of Java Web Dev).

- Main of dificuteis: Design Patterns, I don't know if a lot of private classes (AssyncTasks by example) are the correct way.
- Another design issue: The correct moment of  download image's posters. (I chose to store the image in movie dto as the movies are being loaded, but maybe I should load only for current movies being displayed). 
- I discovered that I must control states by myself when I change my mobile orientation. (save state and retrieve state).
- Unit tests - Missing, not enough time.
- Very ugly: I need to get some tips on Android Layout. 

## Limitations
- Retrofit isn't keeping the order of json returned by TMdb even if I use a ordered list as returned type, It's not clear if I should sort the result  by date or another field.







