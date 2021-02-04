# live-location

This library will only fetch the coordinated and check for the permissions. If the permission if not granted then it will ask for the permission. GPS activation dialog box is not implemented.
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
	        implementation 'com.github.SparshInnovators:live-location:1.0.7'
	}
  ```
  **Step 3:**
  Get the location from **```GetLocationCoordinates()```**
  ```
  GetLocationCoordinates.getNewLocation(applicationContext, this)
  ```
  
  **Step 4:**
  Listen to the MutableLiveData, which will be called everytime the new location is received.
  
```
GetLocationCoordinates.locationMutableLiveData.observe(this, Observer { location ->
            //Your location is here
        })
```

**Step 5:**
You can unregister the livedata in the owner
```
override fun onDestroy() {
        super.onDestroy()
        GetLocationCoordinates.locationMutableLiveData.removeObservers(this@MainActivity)
    }
```

***NOTE : ALWAYS PASS ACTIVITY CONTEXT WHEREEVER ACTIVITY SPECIFIC TASK IS PERFORMED***
  
