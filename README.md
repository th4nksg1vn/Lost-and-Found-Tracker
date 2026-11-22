# Lost-and-Found-Tracker
##### By Group B3 for CS213
---

A system that lets students report lost items and lets lost and found workers log, match and release them.

This project was created using Apache Maven.
Whilst the average user would not need to install it to run the program, it is recommended to use this [video](https://www.youtube.com/watch?v=T00NKLQvwYE&t=1749s) (up to the 28:00) to understand how it works.

## Run Instructions
### Windows
1. Open command prompt or powershell and navigate to the lost-and-found-tracker folder.
```cmd
cd C:\path\to\Lost and Found Tracker\lost-and-found-tracker
```
2. Run the following command

*command prompt*
```cmd
mvnw javafx:run
```
*powershell*
```cmd
.\mvnw javafx:run
```

### Mac/Linux
1. Open the terminal and navigate to the lost-and-found-tracker folder.
```bash
cd ~/path/to/"Lost and Found Tracker"/lost-and-found-tracker
```
2. Run the following command

```bash
./mvnw javafx:run
```

If that doesn't work, run the command directly from the shell to see the GUI
```bash
sh ./mvnw clean javafx:run
```
