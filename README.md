# EspressoTestDemo
This project consists an android app that measures pressure and temperature if the device has those feautures. The app has three modules, 
namely presentation, domain and data. In the data layer the data is collected and emitted from Observable class. The domain layer consists 
of pure java objects and use cases used to make the request data from the repository interface in the data layer. 

The presentation layer consists of a presenter class that makes the request received from the activity so the UI is completely isolated 
from the data fetching and other tasks. The presenter also observes the observable data and feeds the activity. The apps uses rxAndroid 
observable and observer pattern to collect the temperature and pressure data and update the UI in clean architecture design. When the user 
is not interested in collecting data anymore or the activity is destroyed, they can unsubscribe/unsubscribed automatically.

The activity is tested using automated and manual test case with espresso. 
