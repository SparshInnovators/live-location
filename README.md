# live-location

**Step 1** :

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
  **Step 2:**
  ```
  dependencies {
	        implementation 'com.github.SparshInnovators:live-location:1.0.3'
	}
  ```
  **Step 3:**
  Get the location from **```GetLocationCoordinates()```**
  ```
  GetLocationCoordinates.getNewLocation(applicationContext, this@MainActivity)
  ```
  
  **Step 4:**
  Listen to the MutableLiveData, which will be called everytime the new location is received.
  
```
GetLocationCoordinates.locationMutableLiveData.observe(this, Observer { location ->
            //Your location is here
        })
```
  
  
