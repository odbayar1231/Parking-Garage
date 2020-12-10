# Parking garage

System Design of Parking garage.

Solution: This is a Parking garage system implemented by Java and H2 database.

The objects in my application are following:

Parking garage - This is made up of 'n' number of floors and each floor has 'm' number of spots.

Levels – This has an independent floor number and the spots within it

Spots - The spots are considered as a model and each spot has its spot Id, and vehicle can be filled out with spot size.

Vehicles - Object with plate number, and their type. A vehicle has attribute of needed spot.

Methods: ParkInVehicle - This method puts a vehicle according with spot size.

ParkOutVehicle – This method removes the vehicle according with plate number and spot size.

I made ‘Parking Garage Entity’ that can be saved into H2 database. It saves vehicles start and stop parking times 
to the H2 database. I decided to create Level as a List instead of entity. Because Level class contains Spots, 
it contains vehicle. So, saving Level into the database is unproductive. If we persist Level to the database, 
it creates new table with each Level’s of objects to the database. It might be reason for database overloading 
in the future. 
 
Main program:

In the Main method I have created sample data with 2 floors and each floor has 2 row 5 column according with 2D array
plan. Later, user can change any floor number and spot size plan through the 2D array.
I have parked in 1 motorcycle and 3 buses. So, third bus couldn’t be fit in parking(parking full). 
Therefore, application gives us “No space for the vehicle for Bus 3” to the console. 

Spot Id assigned with floor number and spot id combination, for example: 
spotId = 01 means 0th floor with id 1. 
spotId = 10 means 1st floor with id 1.

I am willing to discuss further instruction and explanation after reviewing this assignment by the team. 

All questions are welcome.

